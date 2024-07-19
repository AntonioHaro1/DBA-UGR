/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p2;

import jade.core.behaviours.Behaviour;

/**
 *
 * @author ignaciotd
 */
public class MovimientoBehaviour extends Behaviour {
    
    Entorno entorno;
    int filaMovimiento;
    int colMovimiento;
    DBA_P2 agente;
    
    public MovimientoBehaviour(Entorno entornoAgente, DBA_P2 agente) {
        entorno = entornoAgente;
        this.agente = agente;
    }
    
    public void action() {
        filaMovimiento = (!agente.caminoRecorrido.isEmpty()) ? agente.caminoRecorrido.get(agente.caminoRecorrido.size()-1).get(0) : -1;
        colMovimiento = (!agente.caminoRecorrido.isEmpty()) ? agente.caminoRecorrido.get(agente.caminoRecorrido.size()-1).get(1) : -1;
        
        if (entorno.movimientoPosible(filaMovimiento,colMovimiento)) {
        entorno.filAgente = filaMovimiento;
        entorno.colAgente = colMovimiento;
        }
        
        entorno.mostrarEnTorno();      
    }
    
    public boolean done() {
        if (entorno.filAgente == entorno.filMeta && entorno.colAgente == entorno.colMeta) {
            agente.doDelete();
            return true;
        }
        else
            return false;
    }
}
