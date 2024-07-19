/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p3;

import jade.core.behaviours.Behaviour;

/**
 *
 * @author ignaciotd
 */
public class MovimientoBehaviour extends Behaviour {
    
    private Entorno entorno;
    private int filaMovimiento;
    private int colMovimiento;
    private AgenteP3 agente;
    
    public MovimientoBehaviour(Entorno entornoAgente, AgenteP3 agente) {
        entorno = entornoAgente;
        this.agente = agente;
    }
    
    public void action() {
        if (agente.getHayObjetivo()) {
            filaMovimiento = (!agente.getCaminoRecorrido().isEmpty()) ? agente.getCaminoRecorrido().get(agente.getCaminoRecorrido().size()-1).get(0) : -1;
            colMovimiento = (!agente.getCaminoRecorrido().isEmpty()) ? agente.getCaminoRecorrido().get(agente.getCaminoRecorrido().size()-1).get(1) : -1;

            if (entorno.movimientoPosible(filaMovimiento,colMovimiento)) {
                entorno.modificarPosAgente(filaMovimiento, colMovimiento);
            }

            entorno.mostrarEnTorno();
            
            //Actualizamos los flags cuando llegamos al objetivo
            if (entorno.getFilaAgente() == entorno.getFilaMeta() && entorno.getColumnaAgente() == entorno.getColumnaMeta()) {
                agente.setEnviarMensajeRudolph(true);
                agente.setEnviarMensajeSanta(true);
                agente.setHayObjetivo(false);
                agente.reiniciaCaminoRecorrido();
            }
        }
    }
    
    public boolean done() {
        return false;
    }
}
