/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p2;

/**
 *
 * @author Marcos
 */
public class MovimientoSur implements Movimiento{
    
    private Entorno entorno;
    private DBA_P2 agente;
    
    public MovimientoSur (Entorno entorno, DBA_P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSur = -1;
        
        if (entorno.movimientoPosible(entorno.filAgente+1,entorno.colAgente)) 
            distanciaSur = CalcularDistancia(entorno, entorno.filAgente+1,entorno.colAgente, agente.caminoRecorrido);
        
        return distanciaSur;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente+1;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente;
    }
}
