/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.JLabel;

/**
 *
 * @author aleja
 */
public class Casilla {
    private int vida;
    private JLabel lblCasilla;
    private Luchador luchadorCasilla;
    private boolean viva = true;

    public Casilla(JLabel lblCasilla, Luchador luchadorCasilla) {
        this.lblCasilla = lblCasilla;
        this.luchadorCasilla = luchadorCasilla;
        this.vida = 100;
    }

    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public JLabel getLblCasilla() {
        return lblCasilla;
    }

    public void setLblCasilla(JLabel lblCasilla) {
        this.lblCasilla = lblCasilla;
    }

    public Luchador getLuchadorCasilla() {
        return luchadorCasilla;
    }

    public void setLuchadorCasilla(Luchador luchadorCasilla) {
        this.luchadorCasilla = luchadorCasilla;
    }
    
    
}
