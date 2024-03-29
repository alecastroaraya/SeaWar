/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.InterfazCliente;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author aleja
 */
public class AgregarCommand implements ICommand, Serializable{
    private InterfazCliente clientePantalla;

    public AgregarCommand(InterfazCliente clientePantalla) {
        this.clientePantalla = clientePantalla;
    }
    
    public void agregar(String nombre, String imagenNombre, GrupoAtaque ataque){
        Luchador nuevoLuchador = new Luchador(nombre, imagenNombre);
        nuevoLuchador.getAtaques().add(ataque);
        ArrayList<Luchador> listaLuchadores = new ArrayList<Luchador>();
        listaLuchadores.add(nuevoLuchador);
        FileManager.writeObject(listaLuchadores, "src/Archivos/listaluchadores.dat");
        System.out.println(nombre);
        System.out.println(imagenNombre);
        System.out.println(ataque.getNombre());
        ArrayList<Luchador> lista = (ArrayList<Luchador>)FileManager.readObject("src/Archivos/listaluchadores.dat");
        System.out.println(lista.get(0).getNombre());
        System.out.println(lista.get(0).getAtaques().get(0).getNombre());
        System.out.println(lista.get(0).getImagenNombre());
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
        String firstParameter = "";
        String secondParameter = "";
        String thirdParameter = "";
        GrupoAtaque ataqueInicial = null;

        
        ArrayList<String> nombreAtaques = new ArrayList<String>();
        nombreAtaques.add("ThundersUnderTheSea");
        nombreAtaques.add("FishTelepathy");
        nombreAtaques.add("ReleaseTheKraken");
        nombreAtaques.add("WavesControl");
        nombreAtaques.add("TheTrident");
        nombreAtaques.add("UnderseaVolcanoes");

        try{
            firstParameter = clientePantalla.getRefCliente().getSecondParameter(texto);
            System.out.println(firstParameter);
            secondParameter = clientePantalla.getRefCliente().getThirdParameter(texto);
            System.out.println(secondParameter);
            thirdParameter = clientePantalla.getRefCliente().getFourthParameter(texto);
            System.out.println(thirdParameter);
            
        }
        catch(StringIndexOutOfBoundsException e){
            clientePantalla.getTxaConsola().append("Error: Comando inválido." + '\n');
            return;
        }
        
        if (nombreAtaques.contains(thirdParameter)){
            if (thirdParameter.equals("ThundersUnderTheSea"))
                ataqueInicial = new ThundersUnderTheSea("Thunders Under the Sea");
            else if (thirdParameter.equals("FishTelepathy"))
                ataqueInicial = new FishTelepathy("Fish Telepathy");
            else if (thirdParameter.equals("ReleaseTheKraken"))
                ataqueInicial = new ReleaseTheKraken("Release the Kraken");
            else if (thirdParameter.equals("WavesControl"))
                ataqueInicial = new WavesControl("Waves Control");
            else if (thirdParameter.equals("TheTrident"))
                ataqueInicial = new TheTrident("The Trident");
            else if (thirdParameter.equals("UnderseaVolcanoes"))
                ataqueInicial = new UnderseaVolcanoes("Undersea Volcanoes");
        }
        else{
            clientePantalla.getTxaConsola().append("Error: Grupo de ataque inválido." + '\n');
            return;
        }
        
        this.agregar(firstParameter, secondParameter, ataqueInicial);
    }
    
}
