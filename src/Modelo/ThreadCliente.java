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
import java.util.ArrayList;
import Vista.InterfazCliente;


public class ThreadCliente extends Thread implements Serializable{
    
    transient private Socket socketRef;
    transient public DataInputStream reader;
    transient public DataOutputStream writer;
    transient public ObjectOutputStream objWriter;
    private String nombre;
    protected boolean quebrado = false;
    private boolean running = true;
    private boolean conectado = false;
    private boolean listo = false;
    transient public InterfazCliente refPantalla;
    private int contador100 = 0;
    private int contador75 = 0;
    private int contador50 = 0;
    private ArrayList<Luchador> peleadores;
    private int contadorPeleadores = 0;
    private int contadorAtaques = 0;
    private int humanidadTotal = 0;
    private ArrayList<Casilla> civilizacion;
    private boolean turnosStarted = false;

    public ThreadCliente(Socket socketRef, InterfazCliente refPantalla) throws IOException {
        this.socketRef = socketRef;
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        objWriter = new ObjectOutputStream(socketRef.getOutputStream());
        this.refPantalla = refPantalla;
        this.civilizacion = new ArrayList<Casilla>();
        this.peleadores = new ArrayList<Luchador>();
    }

    public ArrayList<Casilla> getCivilizacion() {
        return civilizacion;
    }

    public boolean isTurnosStarted() {
        return turnosStarted;
    }

    public void setTurnosStarted(boolean turnosStarted) {
        this.turnosStarted = turnosStarted;
    }
    
    

    public void setCivilizacion(ArrayList<Casilla> civilizacion) {
        this.civilizacion = civilizacion;
    }

    public int getHumanidadTotal() {
        return humanidadTotal;
    }

    public void setHumanidadTotal(int humanidadTotal) {
        this.humanidadTotal = humanidadTotal;
    }

    public boolean isListo() {
        return listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }
    
    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public int getContadorPeleadores() {
        return contadorPeleadores;
    }

    public void setContadorPeleadores(int contadorPeleadores) {
        this.contadorPeleadores = contadorPeleadores;
    }

    public int getContadorAtaques() {
        return contadorAtaques;
    }

    public void setContadorAtaques(int contadorAtaques) {
        this.contadorAtaques = contadorAtaques;
    }
    
    
    
    public void run (){
        
        int instruccionId = 1;
        while (running){
            try {
                String usuario = "";
                String mensaje;
                instruccionId = reader.readInt(); // esperar hasta que reciba un entero
                
                switch (instruccionId){
                    case 1: // recibe el turno del jufador 1
                        refPantalla.getLblTurno().setText(reader.readUTF());
                    break;
                    case 2: // pasan un mensaje por el chat
                        usuario = reader.readUTF();
                        mensaje = reader.readUTF();
                        //System.out.println("CLIENTE Recibido mensaje: " + mensaje);
                        refPantalla.getTxaChat().append(" > " + usuario + ": " + mensaje + '\n');
                    break;
                    case 3: // pasan un mensaje por el chat
                        usuario = reader.readUTF();
                        int dado1 = reader.readInt();
                        int dado2 = reader.readInt();
                        String turno = reader.readUTF();
                        
                        refPantalla.pintarLanzamientoDados(dado1, dado2, usuario);
                        refPantalla.addMensaje(usuario+">  Lanzamiento de dados (" + dado1 + ", " + dado2 + ")");
                        refPantalla.pintarTurno(turno);
                    break;                    
                    case 4: // Se inicia la partida
                        refPantalla.setInicioPartida();
                        
                    break;
                    case 5: // se guardan los datos de cada jugador
                        System.out.println("test");
                    break;
                    
                    case 6:
                        mensaje = reader.readUTF();
                        refPantalla.getTxaConsola().append(mensaje + '\n');
                    break;
                    case 7:
                        int numListo = 0;
                        if (this.isListo()){
                            numListo = 1;
                        }
                        writer.writeInt(numListo);
                    break;
                    case 8:
                        refPantalla.setNombreTurno(reader.readUTF());
                    break;
                    case 9:
                        refPantalla.getLblStatusPartida().setText("Partida Iniciada");
                        refPantalla.getLblTurno().setText(reader.readUTF());
                        break;
                }
            } catch (IOException ex) {
                
            }
        }
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public boolean isQuebrado() {
        return quebrado;
    }

    public void setQuebrado(boolean quebrado) {
        this.quebrado = quebrado;
    }

    public DataInputStream getReader() {
        return reader;
    }

    public void setReader(DataInputStream reader) {
        this.reader = reader;
    }

    public DataOutputStream getWriter() {
        return writer;
    }

    public void setWriter(DataOutputStream writer) {
        this.writer = writer;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public InterfazCliente getRefPantalla() {
        return refPantalla;
    }

    public void setRefPantalla(InterfazCliente refPantalla) {
        this.refPantalla = refPantalla;
    }

    public int getContador100() {
        return contador100;
    }

    public void setContador100(int contador100) {
        this.contador100 = contador100;
    }

    public int getContador75() {
        return contador75;
    }

    public void setContador75(int contador75) {
        this.contador75 = contador75;
    }

    public int getContador50() {
        return contador50;
    }

    public void setContador50(int contador50) {
        this.contador50 = contador50;
    }

    public ArrayList<Luchador> getPeleadores() {
        return peleadores;
    }

    public void setPeleadores(ArrayList<Luchador> peleadores) {
        this.peleadores = peleadores;
    }
    
    
}
