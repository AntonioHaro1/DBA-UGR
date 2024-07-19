/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

/**
 *
 * @author antonio
 */
public class Ejercicio3 extends Agent{
        
    private class Comportamiento extends SimpleBehaviour
{
    
    @Override
    public void action()
    {
        System.out.println("Este comportamiento se repite cada 2 segundos");
        
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
        }
    }
//Se podria hacer con cyclic
    @Override
    public boolean done() {
        return false;
    }
    
}

    @Override
    protected void setup()
    {
        addBehaviour(new Comportamiento());    
    }
}
