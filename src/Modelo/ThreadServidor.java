/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadServidor extends Thread implements Serializable{
    
    transient private Socket socketRef;
    protected DataInputStream reader;
    protected DataOutputStream writer;
    protected ObjectOutputStream objWriter;
    public String nombre;
    private boolean running = true;
    Servidor server;

    public ThreadServidor(Socket socketRef, Servidor server) throws IOException {
        this.socketRef = socketRef;
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        objWriter = new ObjectOutputStream(socketRef.getOutputStream());
        this.server = server;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Servidor getServer() {
        return server;
    }

    public void setServer(Servidor server) {
        this.server = server;
    }
    
    
    
    public void run (){
        
        int instruccionId = 1;
        while (running){
            try {
                instruccionId = reader.readInt(); // esperar hasta que reciba un entero
                String mensaje; 
                
                switch (instruccionId){
                    case 1: // pasan el nombre del usuario
                        nombre = reader.readUTF();
                        server.enviarMensaje("Se ha conectado el usuario " + nombre + ".");             
                    break;
                    case 2: // pasan un mensaje por el chat
                        mensaje = reader.readUTF();
                        
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(2);
                            current.writer.writeUTF(nombre);
                            current.writer.writeUTF(mensaje);
                        }
                    break;
                    case 3:
                        int dado1 = (new Random()).nextInt(6)+1;                // Codigo placeholder para los dados, en realidad deberian tener su propia clase y estar al iniciar la partida
                        int dado2 = (new Random()).nextInt(6)+1;
                        String next = server.getNextTurno();
                        
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(3);
                            current.writer.writeUTF(nombre);
                            current.writer.writeInt(dado1);
                            current.writer.writeInt(dado2);
                            current.writer.writeUTF(next);
                        }
                    break;
                    case 4: // iniciar partida
                        server.iniciarPartida();
                        // al iniciar la partida se deberían tirar los dados para determinar el orden
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(4);
                        }
                        
                    break;
                    case 5:
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(5);
                        }
                        break;
                    case 6:
                        mensaje = reader.readUTF();
                        
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(6);
                            current.writer.writeUTF(mensaje);
                        }
                        break;
                    case 7:
                        int jugadoresMax = this.getServer().getLimiteMax();
                        int numListo = 1;
                        
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(7);
                            numListo = reader.readInt();
                        }
                        
                        if (numListo == 1){
                            if (server.conexiones.size() != jugadoresMax){
                                return;
                            }
                                
                            
                            String turnoInicial = server.numTurnoInicial();
                            
                            for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(9);
                            current.writer.writeUTF("Turno de: " + turnoInicial);
                            }
                            if (server.isTurnosStarted() == true){
                                return;
                            }
                            
                            this.server.enviarMensaje("Todos los jugadores están listos. Iniciando partida...");
                            this.server.enviarMensaje("La partida ha sido iniciada.");
                            this.server.enviarMensaje("Se ha elegido aleatoriamente que el turno inicial es del jugador: " + turnoInicial);
                        }
                        break;
                }
                        
                }catch (IOException ex) {
        }
                
            }
        }
    }
