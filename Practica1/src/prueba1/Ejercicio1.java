/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba1;

import jade.core.Agent;

/**
 *
 * @author antonio
 */
public class Ejercicio1 extends Agent{
    
    @Override
    protected void setup()
    {
        System.out.println(" Hola estoy probando");
        doDelete();
    }
    
    protected void takeDown()
    {
        System.out.println( " Terminating agent...");
    }

}
