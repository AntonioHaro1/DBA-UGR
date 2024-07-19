/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Fernando
 */
public class enviarMensajeARudolph extends Behaviour {
    
    private int step = 0;
    private boolean finish = false;
    private String CONVERSATION_ID = "ConversacionRudolph";
    private ACLMessage ultimoMsg;
    
    public void action() {
        if (((AgenteP3)myAgent).getEnviarMensajeRudolph()) {
            switch(this.step) {
                //Enviamos a Rudolph la contraseña
                case 0 -> {
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.addReceiver(new AID("Rudolph", AID.ISLOCALNAME));
                    msg.setConversationId(CONVERSATION_ID);
                    msg.setContent(((AgenteP3)myAgent).getCodigo());
                    myAgent.send(msg);
                    this.step = 1;
                }
                //Esperamos a que nos acepte Rudolph
                case 1 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERSATION_ID)) {
                        if (msg.getPerformative() == ACLMessage.AGREE) {
                            System.out.println("Rudolph me ha dicho: " + msg.getContent());
                            
                            //conseguimos las coordenadas, el mensaje esperado es del tipo:
                            //"fila,columna Mensaje cualquiera"
                            String[] partes = msg.getContent().split(",");
                            int fila = Integer.parseInt(partes[0]);
                            int col = Integer.parseInt(partes[1].split(" ")[0]);
                            
                            //Actualizamos el objetivo
                            ((AgenteP3)myAgent).getEntorno().setFilaMeta(fila);
                            ((AgenteP3)myAgent).getEntorno().setColumnaMeta(col);
                            
                            //Activamos el flag de que hay un objetivo y desactivamos el de Santa
                            ((AgenteP3)myAgent).setEnviarMensajeRudolph(false);
                            ((AgenteP3)myAgent).setHayObjetivo(true);
                            
                            //Almacenamos el último mensaje
                            ultimoMsg = msg;
                            
                            this.step = 2;
                        }
                        else {
                            System.out.println("Rudolph no me dió el privilegio :(");
                            myAgent.doDelete();
                        }
                    }
                    
                    else {
                        System.out.println("ERROR - Error en la conversación con Rudoplh");
                        myAgent.doDelete();
                    }
                }
                //Pedimos el siguiente reno e informamos de que hemos encontrado uno
                case 2 -> {
                    ACLMessage msg = ultimoMsg.createReply(ACLMessage.REQUEST);
                    msg.setContent("He encontrado a un nuevo reno. ¿Dónde está el siguiente?");
                    myAgent.send(msg);
                    this.step = 3;
                }
                //Esperamos la respuesta de Rudolph para un nuevo reno
                case 3 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERSATION_ID)) {
                        if (msg.getPerformative() == ACLMessage.AGREE) {
                            System.out.println("Rudolph me ha dicho: " + msg.getContent());
                            
                            //conseguimos las coordenadas, el mensaje esperado es del tipo:
                            //"fila,columna Mensaje cualquiera"
                            String[] partes = msg.getContent().split(",");
                            int fila = Integer.parseInt(partes[0]);
                            int col = Integer.parseInt(partes[1].split(" ")[0]);
                            
                            //Actualizamos el objetivo
                            ((AgenteP3)myAgent).getEntorno().setFilaMeta(fila);
                            ((AgenteP3)myAgent).getEntorno().setColumnaMeta(col);
                            
                            //Activamos el flag de que hay un objetivo y desactivamos el de Santa
                            ((AgenteP3)myAgent).setEnviarMensajeRudolph(false);
                            ((AgenteP3)myAgent).setHayObjetivo(true);
                            
                            //Almacenamos el último mensaje
                            ultimoMsg = msg;
                            
                            this.step = 2;
                        }
                        else {
                            //Si nos ha mandado un REFUSE quiere decir que ya hemos encontrado todos
                            System.out.println("Rudolph me ha dicho: " + msg.getContent());
                            finish = true;
                            
                            //Activamos el flag de que hemos encontrado a todos los renos
                            ((AgenteP3)myAgent).setRenosEncontrados(true);
                            ((AgenteP3)myAgent).setEnviarMensajeRudolph(false);
                            ((AgenteP3)myAgent).setEnviarMensajeSanta(true);
                        }
                    }
                    else {
                        System.out.println("ERROR - Error en la conversación de Rudolph");
                        myAgent.doDelete();
                    }
                }
                default -> {
                    System.out.println("ERROR - Error en la conversación de Rudolph");
                    myAgent.doDelete();
                }
            }
        }
    }
    
    public boolean done() {
        return finish;
    }
}
