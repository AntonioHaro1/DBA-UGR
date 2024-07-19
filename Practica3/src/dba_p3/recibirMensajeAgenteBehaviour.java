/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p3;

/**
 *
 * @author ignaciotd
 */

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import static jade.lang.acl.ACLParserConstants.CONVERSATION_ID;
import java.util.Random;

public class recibirMensajeAgenteBehaviour extends Behaviour {
    
    private int step = 0;
    private boolean finish = false;
    private Santa santa;
    
    public recibirMensajeAgenteBehaviour (Santa agente) {
        santa = agente;
    }
    
    @Override 
    public void action() {
       switch (this.step) {
            case 0 -> {
                ACLMessage msg =
                myAgent.blockingReceive();System.out.println(msg);
                if (msg.getPerformative() == ACLMessage.PROPOSE) {
                    Random rand = new Random();
                    int n = rand.nextInt(10);
                    if (n < 8) { //Aceptamos la proposicion y le enviamos el código secreto
                        ACLMessage replay = msg.createReply(ACLMessage.ACCEPT_PROPOSAL);
                        replay.setContent(santa.getCodigo());
                    
                        this.myAgent.send(replay);
                        this.step = 1;
                    } else { //Rechazamos la proposición y terminamos el agente
                        
                        ACLMessage replay = msg.createReply(ACLMessage.REJECT_PROPOSAL);
                        replay.setContent("Eres un niño malo :(");
                    
                        this.myAgent.send(replay);
                        System.out.println("Santa dice: Este año no hay regalos HOHOHOHO\n");
                        this.myAgent.doDelete();
                    }
                    
                } else {
                    System.out.println("Error in the coversation protocol - step" + 0);
                    myAgent.doDelete();
                }
            }
            case 1 -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);
                if (msg.getPerformative() == ACLMessage.INFORM) {
                    System.out.println("Mensaje de Agente: " + msg.getContent());
                    santa.sumaContadorRenos();
                    System.out.println("Contador: " + santa.getContador());
                    //Si ha encontrado todos los renos le respondemos con mis coordenadas
                    if (santa.getContador() == 8) {
                        this.step = 2;
                    }
                } else {
                    System.out.println("Error in the coversation protocol - step" + 1);
                    myAgent.doDelete();
                }
            }
            case 2 -> { 
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);
                if (msg.getPerformative() == ACLMessage.REQUEST) {
                    System.out.println("Mensaje de Agente: " + msg.getContent());
                    //Si ha encontrado todos los renos le respondemos con mis coordenadas
                    ACLMessage replay = msg.createReply(ACLMessage.AGREE);
                    //String contenido =  "HOHOHOHO FELIZ NAVIDAD!!";
                    String contenido = santa.getFila() + "," + santa.getColumna() + " Perfecto! Traeme los renos";
                    replay.setContent(contenido);
                    this.myAgent.send(replay);
                    this.step = 3;
                } else {
                    System.out.println("Error in the coversation protocol - step" + 2);
                    myAgent.doDelete();
                }
            }
            case 3 -> { 
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);
                if (msg.getPerformative() == ACLMessage.INFORM) {
                    System.out.println("Mensaje de Agente: " + msg.getContent());
                    //Si ha encontrado todos los renos le respondemos con mis coordenadas
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    String contenido =  "HOHOHOHO FELIZ NAVIDAD!!";
                    replay.setContent(contenido);
                    this.myAgent.send(replay);
                    this.finish = true;
                    myAgent.doDelete();
                } else {
                    System.out.println("Error in the coversation protocol - step" + 3);
                    myAgent.doDelete();
                }
            }
        }
    }
    
    @Override
    public boolean done() {
        return (finish == true);
    } 
   
    
}
