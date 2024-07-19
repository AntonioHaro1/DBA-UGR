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
public class enviarMensajeASanta extends Behaviour {
    
    private int step = 0;
    private boolean finish = false;
    private String CONVERSATION_ID = "mensajeSanta";
    private ACLMessage ultimoMsg;
    
    @Override
    public void action() {
        //Comprobamos si la flag para enviar un mensaje a Santa está activo
        if (((AgenteP3)myAgent).getEnviarMensajeSanta()) {
            switch (this.step) {
                //Enviamos una propuesta a Santa
                case 0 -> {
                    System.out.println("Enviando propuesta a Santa");
                    ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
                    msg.addReceiver(new AID("Santa", AID.ISLOCALNAME));
                    msg.setContent("Hola, ¿puedo buscar a los renos?");
                    msg.setConversationId(CONVERSATION_ID);
                    myAgent.send(msg);
                    this.step = 1;
                }
                //Comprobamos si nos han aceptado o rechazado
                case 1 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERSATION_ID)) {
                        if (msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
                            System.out.println("Santa ha aceptado la propuesta. La contraseña es: " + msg.getContent());
                            ((AgenteP3)myAgent).setCodigo(msg.getContent());
                            this.step = 2;
                            ((AgenteP3)myAgent).setEnviarMensajeSanta(false);
                            ((AgenteP3)myAgent).setEnviarMensajeRudolph(true);
                            
//Almacenamos el mensaje para responder más adelante
                            ultimoMsg = msg;
                        }
                        else {
                            System.out.println("Santa no me dió el privilegio :(");
                            System.out.println("Santa me dijo: " + msg.getContent());
                            myAgent.doDelete();
                        }
                    } else {
                        System.out.println("ERROR - Error en la conversación con Santa");
                        myAgent.doDelete();
                    }
                }
                //Mandamos un mensaje a Santa de que hemos encontrado un reno
                case 2 -> {
                    
                    //Si hemos encontrado un reno y todavía no están todos
                    if (!((AgenteP3)myAgent).getRenosEncontrados()) {
                        ACLMessage msg = ultimoMsg.createReply(ACLMessage.INFORM);
                        msg.setContent("Se ha encontrado un nuevo reno");
                        //Desactivamos el flag de la conversación
                        ((AgenteP3)myAgent).setEnviarMensajeSanta(false);
                        myAgent.send(msg);
                    }
                    else {
                        System.out.println("He encontrado todos los renos, espero coordenadas de Santa");
                        ACLMessage msg = ultimoMsg.createReply(ACLMessage.REQUEST);
                        msg.setContent("Se han econtrado todos los renos, ¿dónde estás?");
                        //Desactivamos el flag de la conversación
                        myAgent.send(msg);
                        this.step = 3;
                    }      
                }
                //Esperamos a que Santa nos envíe sus coordenadas
                case 3 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERSATION_ID)) {
                        if (msg.getPerformative() == ACLMessage.AGREE) {
                            System.out.println("Santa me ha dicho: " + msg.getContent());
                            
                            //conseguimos las coordenadas, el mensaje esperado es del tipo:
                            //"fila,columna Mensaje cualquiera"
                            String[] partes = msg.getContent().split(",");
                            int fila = Integer.parseInt(partes[0]);
                            int col = Integer.parseInt(partes[1].split(" ")[0]);
                            
                            //Actualizamos el objetivo
                            ((AgenteP3)myAgent).getEntorno().setFilaMeta(fila);
                            ((AgenteP3)myAgent).getEntorno().setColumnaMeta(col);
                            
                            //Activamos el flag de que hay un objetivo y desactivamos el de Santa
                            ((AgenteP3)myAgent).setEnviarMensajeSanta(false);
                            ((AgenteP3)myAgent).setHayObjetivo(true);
                            
                            //Almacenamos el último mensaje
                            ultimoMsg = msg;
                            
                            this.step = 4;
                        }
                    } else {
                        System.out.println("ERROR - Error en la conversación con Santa");
                        myAgent.doDelete();
                    }
                }
                //Enviamos a Santa que hemos llegado a su posición
                case 4 -> {
                    ACLMessage msg = ultimoMsg.createReply(ACLMessage.INFORM);
                    msg.setContent("He llegado");
                    myAgent.send(msg);
                    this.step = 5;
                }
                //Esperamos a que Santa nos envíe el mensaje final
                case 5 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if(msg.getConversationId().equals(CONVERSATION_ID) &&
                            msg.getPerformative() == ACLMessage.INFORM) {
                        System.out.println("Santa me ha dicho: " + msg.getContent());
                        System.out.println("Terminando...");
                        finish = true;
                        myAgent.doDelete();
                    }
                    else {
                        System.out.println("ERROR - Error en la conversación con Santa");
                        myAgent.doDelete();
                    }
                }
                default -> {
                    System.out.println("ERROR - Error en la conversación con Santa");
                    myAgent.doDelete();
                }
            }
        }
    }
    
    public boolean done() {
        return finish;
    }
}
