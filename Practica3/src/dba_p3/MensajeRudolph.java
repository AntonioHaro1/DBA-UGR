/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
/**
 *
 * @author antonio
 */
public class MensajeRudolph extends Behaviour {
    
    private int step = 0;
    private boolean finish = false;
    private String contrasenia = "EsUnNIñoBueno:)";
    private ArrayList<int[]> listaRenos = new ArrayList<>();
    private int contadorRenos= 0;
    
    
    public MensajeRudolph(ArrayList<int[]> Renos) {
        listaRenos = Renos;
    }
    
    @Override
    public void action() {
      switch(this.step){
        case 0 -> {
            ACLMessage msg = myAgent.blockingReceive();
            System.out.println(msg);
            if(msg.getPerformative() == ACLMessage.REQUEST)
            {
                if(msg.getContent().equals(contrasenia)){
                     ACLMessage replay = msg.createReply(ACLMessage.AGREE);
                  
                      int[] valores = listaRenos.get(0);
                      String contenidoMensaje = valores[0] + "," + valores[1] + " Esa es la posicion del primer reno";
                  
                      replay.setContent(contenidoMensaje);
                      this.myAgent.send(replay);
                      this.step = 1;
                      this.contadorRenos++;
                }else{
                      ACLMessage replay = msg.createReply(ACLMessage.REFUSE);
                      replay.setContent("Esa no es la contraseña");
                }   
            }else{
                  System.out.println("Error en el protocolo - step" + 1);
            }    
        }
        case 1 -> {
            ACLMessage msg = myAgent.blockingReceive();
            System.out.println(msg);
            
            if(msg.getPerformative() == ACLMessage.REQUEST){
                // debe comprobar la posicion del agente con la posicion del reno
                
                ACLMessage replay = msg.createReply(ACLMessage.AGREE);
                  
                int[] valores = listaRenos.get(contadorRenos);
                String contenidoMensaje = valores[0] + "," + valores[1] + " Esa es la posicion del siguiente reno";
                  
                replay.setContent(contenidoMensaje);
                this.myAgent.send(replay);
                contadorRenos++;
                
                if(contadorRenos == 8){
                        this.step = 2;
                }
            }else{
                System.out.println("Error en el protocolo - step" + 2);
            }
        }
        
        case 2 -> {
            ACLMessage msg = myAgent.blockingReceive();
            System.out.println(msg);
            
            if(msg.getPerformative() == ACLMessage.REQUEST){
                /* debe comprobar la posicion del agente con la posicion del reno
                    if(listaRenos.get(1).get(0) == )
                */
                ACLMessage replay = msg.createReply(ACLMessage.REFUSE);
                String contenidoMensaje = "Has econtrado a todos los renos";
                  
                replay.setContent(contenidoMensaje);
                this.myAgent.send(replay);
                this.finish = true;
            }else{
                System.out.println("Error en el protocolo - step" + 3);
            }
        
        }
      }
    }
    
    @Override
    public boolean done() {
         if (finish) {
            myAgent.doDelete();
            return true;
        }
        else
            return false;
    }
}

