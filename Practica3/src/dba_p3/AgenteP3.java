/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p3;

import static java.lang.System.exit;
import jade.core.Agent;

import java.util.ArrayList;

/**
 *
 * @author ignaciotd
 */
public class AgenteP3 extends Agent{
    private Mapa mapa;
    private Entorno entorno;
    private String codigo;
    private boolean enviarMensajeSanta;
    private boolean enviarMensajeRudolph;
    private boolean hayObjetivo;
    private boolean renosEncontrados;
    private ArrayList<ArrayList<Integer>> caminoRecorrido;
    
    protected void setup() {
        // Inicializamos el agente
        mapa = new Mapa();
        String ruta = "./Pr1-maps/mapaP3.txt";
        
        caminoRecorrido = new ArrayList<ArrayList<Integer>>();
        
        //Leemos el mapa
        mapa.leermapa(ruta);
        
        //Inicializamos el entorno del Agente
        //entorno = new Entorno(mapa, 3, 9, 1, 9);
        entorno = new Entorno(mapa, 0, 6, 9, 6);
        //entorno = new Entorno(mapa, 0, 6, 9, 6);
        entorno.mostrarEnTorno();
        
        //Inicializamos los flags
        enviarMensajeSanta = true;
        enviarMensajeRudolph = false;
        hayObjetivo = false;
        renosEncontrados = false;
        
        addBehaviour(new enviarMensajeASanta());
        addBehaviour(new enviarMensajeARudolph());
        addBehaviour(new MejorMovimientoBehaviour(entorno, this));
        addBehaviour(new MovimientoBehaviour(entorno, this));
    }
    
    public ArrayList<ArrayList<Integer>> getCaminoRecorrido(){
        return caminoRecorrido;
    }
    
    public void takeDown() {
        System.out.println("Agente terminado");
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String pass) {
        codigo = pass;
    }
    
    public boolean getEnviarMensajeSanta() {
        return enviarMensajeSanta;
    }
    
    public void setEnviarMensajeSanta(boolean valor) {
        enviarMensajeSanta = valor;
    }
    
    public boolean getEnviarMensajeRudolph() {
        return enviarMensajeRudolph;
    }
    
    public void setEnviarMensajeRudolph(boolean valor) {
        enviarMensajeRudolph = valor;
    }
    
    public boolean getHayObjetivo() {
        return hayObjetivo;
    }
    
    public void setHayObjetivo(boolean valor) {
        hayObjetivo = valor;
    }
    
    public boolean getRenosEncontrados() {
        return renosEncontrados;
    }
    
    public void setRenosEncontrados(boolean valor) {
        renosEncontrados = valor;
    }
    
    public Entorno getEntorno() {
        return entorno;
    }
    
    public void reiniciaCaminoRecorrido() {
        caminoRecorrido.clear();
    }
}

