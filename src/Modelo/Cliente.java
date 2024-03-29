/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.InterfazCliente;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import javax.swing.JOptionPane;


public class Cliente implements Serializable{
    
    transient Socket socketRef;
    InterfazCliente refPantalla;
    public ThreadCliente hiloCliente;

    public Cliente(InterfazCliente refPantalla) {
        this.refPantalla = refPantalla;
        refPantalla.setRefCliente(this);
    }
    
    public void conectar(){     // Para conectarse al servidor
 
        try{
            String nombre = JOptionPane.showInputDialog("Escriba el nombre de la civilización:");
            socketRef = new Socket("localhost", 35577);
            hiloCliente = new ThreadCliente(socketRef, refPantalla);
            hiloCliente.start();
            refPantalla.setTitle("War on the Seas - Nombre de civilización: " + nombre);       // Se pone el titulo de la ventana del jugador
            refPantalla.setNombreJugador(nombre);    // Se pone el nombre del jugador
            hiloCliente.writer.writeInt(1); //instruccion para el switch del thraed servidor
            hiloCliente.writer.writeUTF(nombre); //instruccion para el switch del thraed servidor
            hiloCliente.setNombre(nombre);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        
    }
    
    public void conectar(String nombre){     // Para conectarse al servidor
 
        try{
            socketRef = new Socket("localhost", 35577);
            hiloCliente = new ThreadCliente(socketRef, refPantalla);
            hiloCliente.start();
            refPantalla.setTitle("War on the Seas - Nombre de civilización: " + nombre);       // Se pone el titulo de la ventana del jugador
            refPantalla.setNombreJugador(nombre);    // Se pone el nombre del jugador
            hiloCliente.writer.writeInt(1); //instruccion para el switch del thraed servidor
            hiloCliente.writer.writeUTF(nombre); //instruccion para el switch del thraed servidor
            hiloCliente.setNombre(nombre);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        
    }
    
    public int getCommandPosition(String str) {
    int i = 0;
    for (i = 0; i < str.length(); i++) {
        if (str.charAt(i) == ' ') {
            return i;
        }
    }
    return i;
    }
    
    public String getCommand(String str){
        int position = getCommandPosition(str);
        String command = str.substring(0, position);
        return command;
    }
    
    public int getFirstParameterPosition(String texto){
        for (int i = 0; i < texto.length(); i++) {
        if (texto.charAt(i) == ' ') {
            return i;
        }
    }
    return 0;
    }
    
    public String getFirstParameter(String texto){
        int endPosition = getFirstParameterPosition(texto);
        String parameter = texto.substring(0,endPosition);
        return parameter;
    }
    
    public int getSecondParameterPosition(String texto){
        for (int i = 0; i < texto.length(); i++) {
        if (texto.charAt(i) == '-') {
            return i+1;
        }
    }
    return 0;
    }
    
    public int getSecondParameterEnd(String texto){
        int i = 0;
        for (i = 0; i < texto.length(); i++) {
 
        if (texto.charAt(i) == ' ') {
            return i;
        }
    }
    return i;
    }

    public String getSecondParameter(String texto){
        int startPosition = getSecondParameterPosition(texto);
        String restOfText = texto.substring(startPosition);
        int parameterEnd = getSecondParameterEnd(restOfText);
        String parameter = restOfText.substring(0,parameterEnd);
        return parameter;
    }
    
    public String getChatMsg(String texto){
        int startPosition = getSecondParameterPosition(texto);
        String restOfText = texto.substring(startPosition);
        String parameter = restOfText.substring(0);
        return parameter;
    }
    
    public int getEnd(String texto){
        int i = 0;
        for (i = 0; i < texto.length(); i++) {
 
        if (texto.charAt(i) == ' ') {
            return i;
        }
    }
    return i;
    }
    
    public String getThirdParameter(String texto){
        int startPosition = getSecondParameterPosition(texto);
        String restOfText = texto.substring(startPosition);
        int parameterEnd = getSecondParameterEnd(restOfText);
        String restOfText2 = restOfText.substring(parameterEnd+2);
        String parameter = restOfText2.substring(0);
        int endPos = getEnd(parameter);
        parameter = restOfText2.substring(0,endPos);
        return parameter;
    }
    
    public String getFourthParameter(String texto){
        int startPosition = getSecondParameterPosition(texto);
        String restOfText = texto.substring(startPosition);
        int parameterEnd = getSecondParameterEnd(restOfText);
        String restOfText2 = restOfText.substring(parameterEnd+2);
        int parameterEnd2 = getSecondParameterEnd(restOfText2);
        String restOfText3 = restOfText2.substring(parameterEnd2+2);
        String parameter = restOfText3.substring(0);
        int endPos = getEnd(parameter);
        parameter = restOfText3.substring(0, endPos);
        return parameter;
    }
    
    public String getFifthParameter(String texto){
        int startPosition = getSecondParameterPosition(texto);
        String restOfText = texto.substring(startPosition);
        int parameterEnd = getSecondParameterEnd(restOfText);
        String restOfText2 = restOfText.substring(parameterEnd+2);
        int parameterEnd2 = getSecondParameterEnd(restOfText2);
        String restOfText3 = restOfText2.substring(parameterEnd2+2);
        int parameterEnd3 = getSecondParameterEnd(restOfText3);
        String restOfText4 = restOfText3.substring(parameterEnd3+2);
        String parameter = restOfText4.substring(0);
        int endPos = getEnd(parameter);
        parameter = restOfText4.substring(0, endPos);
        return parameter;
    }
    
    public String getSixthParameter(String texto){
        int startPosition = getSecondParameterPosition(texto);
        String restOfText = texto.substring(startPosition);
        int parameterEnd = getSecondParameterEnd(restOfText);
        String restOfText2 = restOfText.substring(parameterEnd+2);
        int parameterEnd2 = getSecondParameterEnd(restOfText2);
        String restOfText3 = restOfText2.substring(parameterEnd2+2);
        int parameterEnd3 = getSecondParameterEnd(restOfText3);
        String restOfText4 = restOfText3.substring(parameterEnd3+2);
        int parameterEnd4 = getSecondParameterEnd(restOfText4);
        String restOfText5 = restOfText4.substring(parameterEnd4+2);
        String parameter = restOfText5.substring(0);
        int endPos = getEnd(parameter);
        parameter = restOfText5.substring(0, endPos);
        return parameter;
    }
    
    public String getSeventhParameter(String texto){
        int startPosition = getSecondParameterPosition(texto);
        String restOfText = texto.substring(startPosition);
        int parameterEnd = getSecondParameterEnd(restOfText);
        String restOfText2 = restOfText.substring(parameterEnd+2);
        int parameterEnd2 = getSecondParameterEnd(restOfText2);
        String restOfText3 = restOfText2.substring(parameterEnd2+2);
        int parameterEnd3 = getSecondParameterEnd(restOfText3);
        String restOfText4 = restOfText3.substring(parameterEnd3+2);
        int parameterEnd4 = getSecondParameterEnd(restOfText4);
        String restOfText5 = restOfText4.substring(parameterEnd4+2);
        int parameterEnd5 = getSecondParameterEnd(restOfText5);
        String restOfText6 = restOfText5.substring(parameterEnd5+2);
        String parameter = restOfText6.substring(0);
        int endPos = getEnd(parameter);
        parameter = restOfText6.substring(0, endPos);
        return parameter;
    }
    
    public String getEightParameter(String texto){
        int startPosition = getSecondParameterPosition(texto);
        String restOfText = texto.substring(startPosition);
        int parameterEnd = getSecondParameterEnd(restOfText);
        String restOfText2 = restOfText.substring(parameterEnd+2);
        int parameterEnd2 = getSecondParameterEnd(restOfText2);
        String restOfText3 = restOfText2.substring(parameterEnd2+2);
        int parameterEnd3 = getSecondParameterEnd(restOfText3);
        String restOfText4 = restOfText3.substring(parameterEnd3+2);
        int parameterEnd4 = getSecondParameterEnd(restOfText4);
        String restOfText5 = restOfText4.substring(parameterEnd4+2);
        int parameterEnd5 = getSecondParameterEnd(restOfText5);
        String restOfText6 = restOfText5.substring(parameterEnd5+2);
        int parameterEnd6 = getSecondParameterEnd(restOfText6);
        String restOfText7 = restOfText6.substring(parameterEnd6+2);
        String parameter = restOfText7.substring(0);
        int endPos = getEnd(parameter);
        parameter = restOfText3.substring(0, endPos);
        return parameter;
    }

    public InterfazCliente getRefPantalla() {
        return refPantalla;
    }

    public void setRefPantalla(InterfazCliente refPantalla) {
        this.refPantalla = refPantalla;
    }

    public ThreadCliente getHiloCliente() {
        return hiloCliente;
    }

    public void setHiloCliente(ThreadCliente hiloCliente) {
        this.hiloCliente = hiloCliente;
    }
    
    
}
