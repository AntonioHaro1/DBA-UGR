/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p2;

import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public class MovimientoNorte implements Movimiento {
    
    private Entorno entorno;
    private DBA_P2 agente;
    
    public MovimientoNorte (Entorno entorno, DBA_P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaNorte = -1;
        
        if (entorno.movimientoPosible(entorno.filAgente-1,entorno.colAgente)) 
            distanciaNorte = CalcularDistancia(entorno, entorno.filAgente-1,entorno.colAgente, agente.caminoRecorrido);
        
        return distanciaNorte;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente-1;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente;
    }
}
