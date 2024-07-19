/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p2;

/**
 *
 * @author Marcos
 */
public class MovimientoSuroeste implements Movimiento{
    
    private Entorno entorno;
    private DBA_P2 agente;
    
    public MovimientoSuroeste (Entorno entorno, DBA_P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSuroeste = -1;
        
        if (entorno.movimientoPosibleDiagonal(entorno.filAgente+1,entorno.colAgente-1, DIRECCIONES.SUROESTE)) 
            distanciaSuroeste = CalcularDistancia(entorno, entorno.filAgente+1,entorno.colAgente-1, agente.caminoRecorrido);
        
        return distanciaSuroeste;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente+1;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente-1;
    }
}
