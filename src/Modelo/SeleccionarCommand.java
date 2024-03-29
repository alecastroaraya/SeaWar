/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.InterfazCliente;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author aleja
 */
public class SeleccionarCommand implements ICommand{
    private InterfazCliente clientePantalla;
    private ArrayList<Luchador> listaLuchadores;

    public SeleccionarCommand(InterfazCliente clientePantalla) {
        this.clientePantalla = clientePantalla;
        this.listaLuchadores = (ArrayList<Luchador>)FileManager.readObject("src/Archivos/listaluchadores.dat");
    }
    
    public Luchador seleccionar(String nombreLuchador){
        for (int i = 0; i < listaLuchadores.size(); i++){
            if (listaLuchadores.get(i).getNombre().equals(nombreLuchador))
                return listaLuchadores.get(i);
        }
        return null;
    }
    
    public void ponerStats(Luchador newLuchador){
        int numPeleadores = clientePantalla.getRefCliente().getHiloCliente().getContadorPeleadores();
        int contadorAtaques = clientePantalla.getRefCliente().getHiloCliente().getContadorAtaques();
        
        double numCasillas = 600 * ((double)newLuchador.getPorcentajeHumanidad() / 100);
        System.out.println(numCasillas);
        
        for (double i = 0; i < numCasillas; i++){
            JLabel newLabel = new JLabel("    ");
            newLabel.setForeground(new java.awt.Color(255, 255, 255));
            newLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            if (numPeleadores == 1)
                newLabel.setBackground(Color.cyan);
            else if (numPeleadores == 2)
                newLabel.setBackground(Color.orange);
            else if (numPeleadores == 3)
                newLabel.setBackground(Color.magenta);
            newLabel.setOpaque(true);
            newLabel.setVisible(true);
            clientePantalla.getPnlTablero().add(newLabel);
            Casilla newCasilla = new Casilla(newLabel, newLuchador);
            newLuchador.getCasillasLuchador().add(newCasilla);
            this.clientePantalla.getRefCliente().getHiloCliente().getCivilizacion().add(newCasilla);
        }
        
        double casillasVivas = 0.0;
        for (int i = 0; i < newLuchador.getCasillasLuchador().size(); i++){
            if (newLuchador.getCasillasLuchador().get(i).isViva())
                casillasVivas = casillasVivas + 1;
        }
        
        casillasVivas = ((double)casillasVivas / newLuchador.getCasillasLuchador().size()) * 100;
        
        if (numPeleadores == 1){
            this.clientePantalla.getLblImagenLuchador1().setIcon(new ImageIcon(newLuchador.getImagenNombre()));
            this.clientePantalla.getLblHumanidadLuchador1().setText("Porcentaje humanidad: " + newLuchador.getPorcentajeHumanidad() + "%");
            this.clientePantalla.getLblNombreLuchador1().setText("Nombre: " + newLuchador.getNombre());
            this.clientePantalla.getLblAtaque1Luchador1().setText("Grupo #1: " + newLuchador.getAtaques().get(0).getNombre());
            
            if (contadorAtaques == 2){
                this.clientePantalla.getLblAtaque2Luchador1().setText("Grupo #2: " + newLuchador.getAtaques().get(1).getNombre());
                this.clientePantalla.getLblAtaque2Luchador1().setVisible(true);
            }
           
            this.clientePantalla.getLblPoderLuchador1().setText("Porcentaje poder: " + newLuchador.getPorcentajePoder() + "%");
            this.clientePantalla.getLblResistenciaLuchador1().setText("Porcentaje resistencia: " + newLuchador.getPorcentajeResistencia() + "%");
            this.clientePantalla.getLblAutosanidadLuchador1().setText("Porcentaje autosanidad: " + newLuchador.getPorcentajeAutosanidad() + "%");
            this.clientePantalla.getLblNombre2Luchador1().setText(newLuchador.getNombre());
            newLuchador.setPorcentajeCasillasVivas(casillasVivas);
            this.clientePantalla.getLblVidaLuchador1().setText("Vida: " + casillasVivas + "%");
            newLuchador.setNumCasillasVivas(numCasillas);
            this.clientePantalla.getLblNumCasillasLuchador1().setText("Casillas: " + numCasillas + "/" + newLuchador.getCasillasLuchador().size());
            this.clientePantalla.getLblVidaLuchador1().setVisible(true);
            this.clientePantalla.getLblNumCasillasLuchador1().setVisible(true);
        }
        
        if (numPeleadores == 2){
            this.clientePantalla.getLblImagenLuchador2().setIcon(new ImageIcon(newLuchador.getImagenNombre()));
            this.clientePantalla.getLblHumanidadLuchador2().setText("Porcentaje humanidad: " + newLuchador.getPorcentajeHumanidad() + "%");
            this.clientePantalla.getLblNombreLuchador2().setText("Nombre: " + newLuchador.getNombre());
            this.clientePantalla.getLblAtaque1Luchador2().setText("Grupo #1: " + newLuchador.getAtaques().get(0).getNombre());
            
            if (contadorAtaques == 2){
                this.clientePantalla.getLblAtaque2Luchador2().setText("Grupo #2: " + newLuchador.getAtaques().get(1).getNombre());
                this.clientePantalla.getLblAtaque2Luchador2().setVisible(true);
            }
           
            this.clientePantalla.getLblPoderLuchador2().setText("Porcentaje poder: " + newLuchador.getPorcentajePoder() + "%");
            this.clientePantalla.getLblResistenciaLuchador2().setText("Porcentaje resistencia: " + newLuchador.getPorcentajeResistencia() + "%");
            this.clientePantalla.getLblAutosanidadLuchador2().setText("Porcentaje autosanidad: " + newLuchador.getPorcentajeAutosanidad() + "%");
            this.clientePantalla.getLblNombre2Luchador2().setText(newLuchador.getNombre());
            newLuchador.setPorcentajeCasillasVivas(casillasVivas);
            this.clientePantalla.getLblVidaLuchador2().setText("Vida: " + casillasVivas + "%");
            newLuchador.setNumCasillasVivas(numCasillas);
            this.clientePantalla.getLblNumCasillasLuchador2().setText("Casillas: " + numCasillas + "/" + newLuchador.getCasillasLuchador().size());
            this.clientePantalla.getLblVidaLuchador2().setVisible(true);
            this.clientePantalla.getLblNumCasillasLuchador2().setVisible(true);
        }
        
        if (numPeleadores == 3){
            this.clientePantalla.getLblImagenLuchador3().setIcon(new ImageIcon(newLuchador.getImagenNombre()));
            this.clientePantalla.getLblHumanidadLuchador3().setText("Porcentaje humanidad: " + newLuchador.getPorcentajeHumanidad() + "%");
            this.clientePantalla.getLblNombreLuchador3().setText("Nombre: " + newLuchador.getNombre());
            this.clientePantalla.getLblAtaque1Luchador3().setText("Grupo #1: " + newLuchador.getAtaques().get(0).getNombre());
            
            if (contadorAtaques == 2){
                this.clientePantalla.getLblAtaque2Luchador3().setText("Grupo #2: " + newLuchador.getAtaques().get(1).getNombre());
                this.clientePantalla.getLblAtaque2Luchador3().setVisible(true);
            }
           
            this.clientePantalla.getLblPoderLuchador3().setText("Porcentaje poder: " + newLuchador.getPorcentajePoder() + "%");
            this.clientePantalla.getLblResistenciaLuchador3().setText("Porcentaje resistencia: " + newLuchador.getPorcentajeResistencia() + "%");
            this.clientePantalla.getLblAutosanidadLuchador3().setText("Porcentaje autosanidad: " + newLuchador.getPorcentajeAutosanidad() + "%");
            this.clientePantalla.getLblNombre2Luchador3().setText(newLuchador.getNombre());
            newLuchador.setPorcentajeCasillasVivas(casillasVivas);
            this.clientePantalla.getLblVidaLuchador3().setText("Vida: " + casillasVivas + "%");
            newLuchador.setNumCasillasVivas(numCasillas);
            this.clientePantalla.getLblNumCasillasLuchador3().setText("Casillas: " + numCasillas + "/" + newLuchador.getCasillasLuchador().size());
            this.clientePantalla.getLblVidaLuchador3().setVisible(true);
            this.clientePantalla.getLblNumCasillasLuchador3().setVisible(true);
        }
        
    }

    @Override
    public void execute(String texto, InterfazCliente vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute(String texto) {
        if (clientePantalla.getRefCliente().getHiloCliente() == null){
            clientePantalla.getTxaConsola().append("Error: Debe estar conectado al servidor para seleccionar un personaje." + '\n');
            return;
        }
        
        String firstParameter = "";
        String secondParameter = "";
        String thirdParameter = "";
        String fourthParameter = "";
        String fifthParameter = "";
        int porcentajePoder = 0;
        int porcentajeResistencia = 0;
        int porcentajeAutosanidad = 0;
        int porcentajeHumanidad = 0;
        
        try{
            firstParameter = clientePantalla.getRefCliente().getSecondParameter(texto);
            //System.out.println(firstParameter);
            secondParameter = clientePantalla.getRefCliente().getThirdParameter(texto);
            //System.out.println(secondParameter);
            thirdParameter = clientePantalla.getRefCliente().getFourthParameter(texto);
            //System.out.println(thirdParameter);
            fourthParameter = clientePantalla.getRefCliente().getFifthParameter(texto);
            //System.out.println(fourthParameter);
            fifthParameter = clientePantalla.getRefCliente().getSixthParameter(texto);
            //System.out.println(fifthParameter);
            
        }
        catch(StringIndexOutOfBoundsException e){
            clientePantalla.getTxaConsola().append("Error: Comando inválido." + '\n');
            return;
        }
        
        try{
            porcentajePoder = Integer.parseInt(secondParameter);
            porcentajeResistencia = Integer.parseInt(thirdParameter);
            porcentajeAutosanidad = Integer.parseInt(fourthParameter);
            porcentajeHumanidad = Integer.parseInt(fifthParameter);
        }
        catch(NumberFormatException e){
            clientePantalla.getTxaConsola().append("Error: Los porcentajes deben ser números enteros." + '\n');
            return;
        }

        int contador50 = clientePantalla.getRefCliente().getHiloCliente().getContador50();
        int contador75 = clientePantalla.getRefCliente().getHiloCliente().getContador75();
        int contador100 = clientePantalla.getRefCliente().getHiloCliente().getContador100();
        int humanidadTotal = clientePantalla.getRefCliente().getHiloCliente().getHumanidadTotal();

        if (this.seleccionar(firstParameter) != null){
            if (porcentajeHumanidad > 98){
            this.clientePantalla.getTxaConsola().append("Error: Porcentaje de humanidad invalido. Los 3 porcentajes de humanidad sumados deben ser 100.\n");
            return;
            }
            
            if (porcentajeHumanidad + humanidadTotal > 100){
            this.clientePantalla.getTxaConsola().append("Error: Porcentaje de humanidad invalido. Los 3 porcentajes de humanidad sumados deben ser 100.\n");
            return;
            }
            
            if (porcentajePoder == 50){
                
                if (contador50 + 1 > 3){
                    this.clientePantalla.getTxaConsola().append("Error: No puede usar el valor 50 más de tres veces.\n");
                    return;
                }
                contador50 = contador50 + 1;
                clientePantalla.getRefCliente().getHiloCliente().setContador50(contador50);
            }
            else if (porcentajePoder == 75){
                
                if (contador75 + 1 > 3){
                    this.clientePantalla.getTxaConsola().append("Error: No puede usar el valor 75 más de tres veces.\n");
                    return;
                }
                contador75 = contador75 + 1;
                clientePantalla.getRefCliente().getHiloCliente().setContador75(contador75);
            }
            else if (porcentajePoder == 100){
                
                if (contador100 + 1 > 3){
                    this.clientePantalla.getTxaConsola().append("Error: No puede usar el valor 100 más de tres veces.\n");
                    return;
                }
                contador100 = contador100 + 1;
                clientePantalla.getRefCliente().getHiloCliente().setContador100(contador100);
            }
            else{
                clientePantalla.getRefCliente().getRefPantalla().getTxaConsola().append("Error: El porcentaje de poder debe ser 50, 75, o 100.\n");
                return;
            }
            
            if (porcentajeResistencia == 50){
                
                if (contador50 + 1 > 3){
                    this.clientePantalla.getTxaConsola().append("Error: No puede usar el valor 50 más de tres veces.\n");
                    return;
                }
                contador50 = contador50 + 1;
                clientePantalla.getRefCliente().getHiloCliente().setContador50(contador50);
            }
            else if (porcentajeResistencia == 75){
                
                if (contador75 + 1 > 3){
                    this.clientePantalla.getTxaConsola().append("Error: No puede usar el valor 75 más de tres veces.\n");
                    return;
                }
                contador75 = contador75 + 1;
                clientePantalla.getRefCliente().getHiloCliente().setContador75(contador75);
            }
            else if (porcentajeResistencia == 100){
                
                if (contador100 + 1 > 3){
                    this.clientePantalla.getTxaConsola().append("Error: No puede usar el valor 100 más de tres veces.\n");
                    return;
                }
                contador100 = contador100 + 1;
                clientePantalla.getRefCliente().getHiloCliente().setContador100(contador100);
            }
            else{
                clientePantalla.getRefCliente().getRefPantalla().getTxaConsola().append("Error: El porcentaje de resistencia debe ser 50, 75, o 100.\n");
                return;
            }
            
            if (porcentajeAutosanidad == 50){
                
                if (contador50 + 1 > 3){
                    this.clientePantalla.getTxaConsola().append("Error: No puede usar el valor 50 más de tres veces.\n");
                    return;
                }
                contador50 = contador50 + 1;
                clientePantalla.getRefCliente().getHiloCliente().setContador50(contador50);
            }
            else if (porcentajeAutosanidad == 75){
                
                if (contador75 + 1 > 3){
                    this.clientePantalla.getTxaConsola().append("Error: No puede usar el valor 75 más de tres veces.\n");
                    return;
                }
                contador75 = contador75 + 1;
                clientePantalla.getRefCliente().getHiloCliente().setContador75(contador75);
            }
            else if (porcentajeAutosanidad == 100){
                
                if (contador100 + 1 > 3){
                    this.clientePantalla.getTxaConsola().append("Error: No puede usar el valor 100 más de tres veces.\n");
                    return;
                }
                contador100 = contador100 + 1;
                clientePantalla.getRefCliente().getHiloCliente().setContador100(contador100);
            }
            else{
                clientePantalla.getRefCliente().getRefPantalla().getTxaConsola().append("Error: El porcentaje de autosanidad ser 50, 75, o 100.\n");
                return;
            }
            
            humanidadTotal = humanidadTotal + porcentajeHumanidad;
            clientePantalla.getRefCliente().getHiloCliente().setHumanidadTotal(humanidadTotal);
            System.out.println(humanidadTotal);
            
            Luchador newLuchador = this.seleccionar(firstParameter);
            newLuchador.setPorcentajePoder(porcentajePoder);
            newLuchador.setPorcentajeResistencia(porcentajeResistencia);
            newLuchador.setPorcentajeAutosanidad(porcentajeAutosanidad);
            newLuchador.setPorcentajeHumanidad(porcentajeHumanidad);
            this.clientePantalla.getRefCliente().getHiloCliente().getPeleadores().add(newLuchador);
            int numPeleadores = clientePantalla.getRefCliente().getHiloCliente().getContadorPeleadores();
            int contadorAtaques = clientePantalla.getRefCliente().getHiloCliente().getContadorAtaques();
            clientePantalla.getRefCliente().getHiloCliente().setContadorPeleadores(numPeleadores+1);
            this.ponerStats(newLuchador);
            clientePantalla.getTxaConsola().append("Se agregó el luchador " + firstParameter + " con " + porcentajePoder + "% de poder, " + porcentajeResistencia + "% de resistencia, " + porcentajeAutosanidad +
                    "% de autosanidad, y " + porcentajeHumanidad + "% de humanidad." +'\n');
        }
        else{
            clientePantalla.getTxaConsola().append("Error: El guerrero solicitado no existe." + '\n');
        }
        
    }
    
}
