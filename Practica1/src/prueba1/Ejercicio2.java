/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

/**
 *
 * @author antonio
 */
public class Ejercicio2 extends Agent{
    
    private class Comportamiento1 extends OneShotBehaviour
{
    @Override
    public void action()
    {
        System.out.println("Este comportamiento solo se hace una vez");
        doDelete();
    }
    protected void takeDown()
    {
        System.out.println( " Terminating agent...");
    }
    
    
}
    @Override
    protected void setup()
    {
        addBehaviour(new Comportamiento1());
        
       
    }
    
    @Override
    protected void takeDown()
    {
        System.out.println( " Terminating agent...");
    }
    
    
        


}