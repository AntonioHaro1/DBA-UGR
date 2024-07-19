/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p2;

/**
 *
 * @author Marcos
 */
public class MovimientoOeste implements Movimiento{
    
    private Entorno entorno;
    private DBA_P2 agente;
    
    public MovimientoOeste (Entorno entorno, DBA_P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaOeste = -1;
        
        if (entorno.movimientoPosible(entorno.filAgente,entorno.colAgente-1)) 
            distanciaOeste = CalcularDistancia(entorno, entorno.filAgente,entorno.colAgente-1, agente.caminoRecorrido);
        
        return distanciaOeste;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente-1;
    }
}
