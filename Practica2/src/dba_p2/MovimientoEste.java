/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p2;

/**
 *
 * @author Marcos
 */
public class MovimientoEste implements Movimiento{
    
    private Entorno entorno;
    private DBA_P2 agente;
    
    public MovimientoEste (Entorno entorno, DBA_P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaEste = -1;
        
        if (entorno.movimientoPosible(entorno.filAgente,entorno.colAgente+1))
            distanciaEste = CalcularDistancia(entorno, entorno.filAgente,entorno.colAgente+1, agente.caminoRecorrido);
        
        return distanciaEste;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente+1;
    }    
}
