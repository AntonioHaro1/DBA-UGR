/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p2;

/**
 *
 * @author Marcos
 */
public class MovimientoSureste implements Movimiento{
    
    private Entorno entorno;
    private DBA_P2 agente;
    
    public MovimientoSureste (Entorno entorno, DBA_P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSureste = -1;
        
        if (entorno.movimientoPosibleDiagonal(entorno.filAgente+1,entorno.colAgente+1, DIRECCIONES.SURESTE)) 
            distanciaSureste = CalcularDistancia(entorno, entorno.filAgente+1,entorno.colAgente+1, agente.caminoRecorrido);
        
        return distanciaSureste;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente+1;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente+1;
    }    
}
