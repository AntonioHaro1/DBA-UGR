/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p3;

/**
 *
 * @author Marcos
 */
public class MovimientoEste implements Movimiento{
    
    private Entorno entorno;
    private AgenteP3 agente;
    
    public MovimientoEste (Entorno entorno, AgenteP3 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaEste = -1;
        
        if (entorno.movimientoPosible(entorno.getFilaAgente(),entorno.getColumnaAgente()+1))
            distanciaEste = CalcularDistancia(entorno, entorno.getFilaAgente(),entorno.getColumnaAgente()+1, agente.getCaminoRecorrido());
        
        return distanciaEste;
    }
    
    @Override
    public int getFila(){
        return entorno.getFilaAgente();
    }
    
    @Override
    public int getColumna(){
        return entorno.getColumnaAgente()+1;
    }    
}
