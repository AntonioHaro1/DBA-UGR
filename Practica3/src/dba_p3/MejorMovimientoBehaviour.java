package dba_p3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import java.lang.Thread;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ignaciotd
 */

public class MejorMovimientoBehaviour extends Behaviour{
    private Entorno entorno;
    private double mejorDistancia = 99999999;
    private int filaMovimiento, colMovimiento;
    private AgenteP3 miAgente;
    
    private ArrayList<Movimiento> movimientos;
    
    public MejorMovimientoBehaviour(Entorno entornoAgente, AgenteP3 agente) {
        entorno = entornoAgente;
        miAgente = agente;
        movimientos = new ArrayList<Movimiento>();
        
        movimientos.add(new MovimientoNorte(entornoAgente, agente));
        movimientos.add(new MovimientoSur(entornoAgente, agente));
        movimientos.add(new MovimientoEste(entornoAgente, agente));
        movimientos.add(new MovimientoOeste(entornoAgente, agente));
        movimientos.add(new MovimientoNoreste(entornoAgente, agente));
        movimientos.add(new MovimientoNoroeste(entornoAgente, agente));
        movimientos.add(new MovimientoSureste(entornoAgente, agente));
        movimientos.add(new MovimientoSuroeste(entornoAgente, agente));
    }
    
    @Override
    public void action() {
       if(miAgente.getHayObjetivo()) {
            mejorDistancia = 99999999;
           //Comprobamos que movimiento es mejor

           for (int i = 0; i < movimientos.size(); i++) {
               double distancia = movimientos.get(i).calculaMovimiento();
               if (distancia != -1) {
                   if (distancia < mejorDistancia) {
                       mejorDistancia = distancia;
                       filaMovimiento = movimientos.get(i).getFila();
                       colMovimiento = movimientos.get(i).getColumna();
                   }
               }
           }

           try {
               Thread.sleep(500);
           } catch (InterruptedException e) {
               System.out.print(e);
           }

           ArrayList<Integer> posicion = new ArrayList<Integer>();
           posicion.add(filaMovimiento);
           posicion.add(colMovimiento);

           miAgente.getCaminoRecorrido().add(posicion);  
       }
    }
    
    @Override
    public boolean done() {
        return false;
    }
}
