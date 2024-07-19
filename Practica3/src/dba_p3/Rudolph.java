/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p3;

import jade.core.Agent;
import java.util.ArrayList;

/**
 *
 * @author ignaciotd
 */


public class Rudolph extends Agent{
    private ArrayList<int[]> listaRenos = new ArrayList<>();

    
    @Override
    protected void setup() {
        
        listaRenos.add(new int[]{2,2});
        listaRenos.add(new int[]{10,15});
        listaRenos.add(new int[]{5,6});
        listaRenos.add(new int[]{13,12});
        listaRenos.add(new int[]{35,21});
        listaRenos.add(new int[]{32,10});
        listaRenos.add(new int[]{26,21});
        listaRenos.add(new int[]{12,25});
        
         addBehaviour(new MensajeRudolph(listaRenos));
        
    }
}
