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
public interface Movimiento {
    
    public double calculaMovimiento();
    
    public int getFila();
    
    public int getColumna();
    
    default double CalcularDistancia(Entorno entorno, int fila, int columna, ArrayList<ArrayList<Integer>> caminoRecorrido) {
        int distancia_y = Math.abs(entorno.filMeta - fila);
        int distancia_x = Math.abs(entorno.colMeta - columna);
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        posicion.add(fila);
        posicion.add(columna);
        
        int castigo = 0;
        int indicePos = caminoRecorrido.lastIndexOf(posicion);
        
        if (indicePos != -1)
            castigo = 99 - (caminoRecorrido.size() - indicePos);
            
        return (distancia + castigo);
    }
}
