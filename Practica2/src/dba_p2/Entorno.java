package dba_p2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ignaciotd
 */
public class Entorno {
    
    Mapa mapa;
    int filAgente, colAgente;
    int filMeta, colMeta;    
    
    public Entorno(Mapa mapaEntorno, int filaAgente, int columnaAgente,
                    int filaMeta, int columnaMeta) {
        
        mapa = mapaEntorno;
        filAgente = filaAgente;
        colAgente = columnaAgente;
        filMeta = filaMeta;
        colMeta = columnaMeta;
    }
    
    public void mostrarEnTorno() {
        // Imprimir la matriz
        System.out.println("Numero filas: " + mapa.filas);
        System.out.println("Numero columnas: " + mapa.columnas);
        for (int i = 0; i < mapa.filas; i++) {

            for (int j = 0; j < mapa.columnas; j++) {
                if (filAgente == i && colAgente == j){
                    System.out.print("33\t");
                }
                else if (filMeta == i && colMeta == j) {
                    System.out.print("X\t");
                }
                else {
                    System.out.print(mapa.mapa[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
    
    public boolean movimientoPosible(int filaMovimiento, int columnaMovimiento) {
        boolean posible = false;
        
        if (0 > filaMovimiento || filaMovimiento >= mapa.filas)
            posible = false;
        else if (0 > columnaMovimiento || columnaMovimiento >= mapa.columnas)
            posible = false;
        else if (mapa.mapa[filaMovimiento][columnaMovimiento] == 0)
            posible = true;
        
        return posible;    
    }
    
    public boolean movimientoPosibleDiagonal(int filaMovimiento, int columnaMovimiento, DIRECCIONES direccion) {
        //Comprobamos que la casilla a la que se mueve es una casilla válida
        boolean posible = movimientoPosible(filaMovimiento, columnaMovimiento);
        
        //Como es un movimiento en diagonal hay que hacer más comprobaciones
        if (posible) {
            switch (direccion) {
                case NORESTE:
                    posible = (mapa.mapa[filAgente-1][colAgente] == 0 && mapa.mapa[filAgente][colAgente+1] == 0);
                    break;
                case NOROESTE:
                    posible = (mapa.mapa[filAgente-1][colAgente] == 0 && mapa.mapa[filAgente][colAgente-1] == 0);
                    break;
                case SURESTE:
                    posible = (mapa.mapa[filAgente+1][colAgente] == 0 && mapa.mapa[filAgente][colAgente+1] == 0);
                    break;
                case SUROESTE:
                    posible = (mapa.mapa[filAgente+1][colAgente] == 0 && mapa.mapa[filAgente][colAgente-1] == 0);
                    break;
            }
        }
        
        return posible;
    }    
}
