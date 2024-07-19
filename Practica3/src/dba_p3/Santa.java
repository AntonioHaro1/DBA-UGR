/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p3;

import jade.core.Agent;

/**
 *
 * @author ignaciotd
 */
public class Santa extends Agent {
    private static final String CODIGOSECRETO = "EsUnNIñoBueno:)";
    private int contadorRenos;
    private int filaSanta;
    private int colSanta;
    
    /////////////////////////////////////////////////
    // Funciones GET
    //////////////////////////////////////////////
    public String getCodigo() {
        return CODIGOSECRETO;
    }
    
    public int getContador() {
        return contadorRenos;
    } 
    
    public int getFila() {
        return filaSanta;
    }
    
    public int getColumna() {
        return colSanta;
    }
    
    ////////////////////////////////////////////////
    // Funcion SET
    ////////////////////////////////////////////////
    public void sumaContadorRenos () {
        contadorRenos++;
    }
    
    @Override
    protected void setup() {
        //Inicializamos el agente
        contadorRenos = 0;
        filaSanta = 5;
        colSanta = 26;
        
        //LLamamos behaviour
        addBehaviour(new recibirMensajeAgenteBehaviour(this));
    }
    
  
    @Override
    public void takeDown() {
        if(contadorRenos > 0){
            System.out.println("Santa: Gracias a ti vivira la magia de la navidad HOHOHOHO");
        }else{
            System.out.println("Santa: Por culpa tuya no hay navidah");
        }
    }
}
