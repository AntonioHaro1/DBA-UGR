/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p3;

/**
 *
 * @author Marcos
 */
public class MovimientoSur implements Movimiento{
    
    private Entorno entorno;
    private AgenteP3 agente;
    
    public MovimientoSur (Entorno entorno, AgenteP3 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSur = -1;
        
        if (entorno.movimientoPosible(entorno.getFilaAgente()+1,entorno.getColumnaAgente())) 
            distanciaSur = CalcularDistancia(entorno, entorno.getFilaAgente()+1,entorno.getColumnaAgente(), agente.getCaminoRecorrido());
        
        return distanciaSur;
    }
    
    @Override
    public int getFila(){
        return entorno.getFilaAgente()+1;
    }
    
    @Override
    public int getColumna(){
        return entorno.getColumnaAgente();
    }
}
