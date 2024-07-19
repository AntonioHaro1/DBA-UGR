package dba_p3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ignaciotd
 */
public class Entorno {
    
    private Mapa mapa;
    private int filAgente, colAgente;
    private int filMeta, colMeta;    
    
    public Entorno(Mapa mapaEntorno, int filaAgente, int columnaAgente,
                    int filaMeta, int columnaMeta) {
        
        mapa = mapaEntorno;
        filAgente = filaAgente;
        colAgente = columnaAgente;
        filMeta = filaMeta;
        colMeta = columnaMeta;
    }
    
    public int getFilaAgente(){
        return filAgente;
    }
    
    public int getFilaMeta(){
        return filMeta;
    }
    
    public int getColumnaAgente(){
        return colAgente;
    }
    
    public int getColumnaMeta(){
        return colMeta;
    }
    
    public void setFilaAgente(int fila){
        filAgente = fila;
    }
    
    public void setColumnaAgente(int col){
        colAgente = col;
    }
    
    public void setFilaMeta(int fil){
        filMeta = fil;
    }
    
    public void setColumnaMeta(int col){
        colMeta = col;
    }
    
    public void modificarPosAgente(int fila, int columna){
        setFilaAgente(fila);
        setColumnaAgente(columna);
    }
    
    public void modificarPosMeta(int fila, int columna){
        setFilaMeta(fila);
        setColumnaMeta(columna);
    }
    
    public void mostrarEnTorno() {
        // Imprimir la matriz
        System.out.println("Numero filas: " + mapa.getFilas());
        System.out.println("Numero columnas: " + mapa.getColumnas());
        for (int i = 0; i < mapa.getFilas(); i++) {

            for (int j = 0; j < mapa.getColumnas(); j++) {
                if (filAgente == i && colAgente == j){
                    System.out.print("33\t");
                }
                else if (filMeta == i && colMeta == j) {
                    System.out.print("X\t");
                }
                else {
                    System.out.print(mapa.getMapa()[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
    
    public boolean movimientoPosible(int filaMovimiento, int columnaMovimiento) {
        boolean posible = false;
        
        if (0 > filaMovimiento || filaMovimiento >= mapa.getFilas())
            posible = false;
        else if (0 > columnaMovimiento || columnaMovimiento >= mapa.getColumnas())
            posible = false;
        else if (mapa.getMapa()[filaMovimiento][columnaMovimiento] == 0)
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
                    posible = (mapa.getMapa()[filAgente-1][colAgente] == 0 && mapa.getMapa()[filAgente][colAgente+1] == 0);
                    break;
                case NOROESTE:
                    posible = (mapa.getMapa()[filAgente-1][colAgente] == 0 && mapa.getMapa()[filAgente][colAgente-1] == 0);
                    break;
                case SURESTE:
                    posible = (mapa.getMapa()[filAgente+1][colAgente] == 0 && mapa.getMapa()[filAgente][colAgente+1] == 0);
                    break;
                case SUROESTE:
                    posible = (mapa.getMapa()[filAgente+1][colAgente] == 0 && mapa.getMapa()[filAgente][colAgente-1] == 0);
                    break;
            }
        }
        
        return posible;
    }    
}
