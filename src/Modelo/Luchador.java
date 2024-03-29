/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author aleja
 */
public class Luchador implements Serializable{
    private String nombre;
    private String imagenNombre;
    private int porcentajeHumanidad;
    private ArrayList<GrupoAtaque> ataques;
    private int porcentajePoder;
    private int porcentajeResistencia;
    private int porcentajeAutosanidad;
    private ArrayList<Casilla> casillasLuchador;
    private double numCasillasVivas = 0;
    private double porcentajeCasillasVivas = 0;

    public Luchador(String nombre, String imagenNombre) {
        this.nombre = nombre;
        this.imagenNombre = imagenNombre;
        this.porcentajeHumanidad = 0;
        this.ataques = new ArrayList<GrupoAtaque>();
        this.porcentajePoder = 0;
        this.porcentajeResistencia = 0;
        this.porcentajeAutosanidad = 0;
        this.casillasLuchador = new ArrayList<Casilla>();
    }

    public double getNumCasillasVivas() {
        return numCasillasVivas;
    }

    public void setNumCasillasVivas(double numCasillasVivas) {
        this.numCasillasVivas = numCasillasVivas;
    }

    public double getPorcentajeCasillasVivas() {
        return porcentajeCasillasVivas;
    }

    public void setPorcentajeCasillasVivas(double porcentajeCasillasVivas) {
        this.porcentajeCasillasVivas = porcentajeCasillasVivas;
    }

    
    
    public ArrayList<Casilla> getCasillasLuchador() {
        return casillasLuchador;
    }

    public void setCasillasLuchador(ArrayList<Casilla> casillasLuchador) {
        this.casillasLuchador = casillasLuchador;
    }

    public ArrayList<GrupoAtaque> getAtaques() {
        return ataques;
    }

    public void setAtaques(ArrayList<GrupoAtaque> ataques) {
        this.ataques = ataques;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenNombre() {
        return imagenNombre;
    }

    public void setImagenNombre(String imagenNombre) {
        this.imagenNombre = imagenNombre;
    }

    public int getPorcentajeHumanidad() {
        return porcentajeHumanidad;
    }

    public void setPorcentajeHumanidad(int porcentajeHumanidad) {
        this.porcentajeHumanidad = porcentajeHumanidad;
    }

    public int getPorcentajePoder() {
        return porcentajePoder;
    }

    public void setPorcentajePoder(int porcentajePoder) {
        this.porcentajePoder = porcentajePoder;
    }

    public int getPorcentajeResistencia() {
        return porcentajeResistencia;
    }

    public void setPorcentajeResistencia(int porcentajeResistencia) {
        this.porcentajeResistencia = porcentajeResistencia;
    }

    public int getPorcentajeAutosanidad() {
        return porcentajeAutosanidad;
    }

    public void setPorcentajeAutosanidad(int porcentajeAutosanidad) {
        this.porcentajeAutosanidad = porcentajeAutosanidad;
    }

    
    
}
