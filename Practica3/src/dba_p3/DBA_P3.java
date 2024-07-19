/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dba_p3;
import jade.core.Agent;
import static java.lang.System.exit;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class DBA_P3 {

    /**
     * @param args the command line arguments
*/
    
    public static void main(String[] args){
        
        //Ejecutamos
        String host = "localhost";
        String containerName = "container-3";
        
        //Nombres agentes
        String agentName1 = "McLovin";
        String agentName2 = "Santa";
        String agentName3 = "Rudolph";
        
        try {
        jade.core.Runtime rt = jade.core.Runtime.instance();
        
        //Creamos un contenedor de agentes
        Profile p = new ProfileImpl();
        
        p.setParameter(Profile.MAIN_HOST, host);
        p.setParameter(Profile.CONTAINER_NAME, containerName);
        
        ContainerController cc = rt.createAgentContainer(p);
        
        //Creamos los agentes nuevos y ejecutamos
        AgentController ac1 = cc.createNewAgent(agentName1,
                AgenteP3.class.getCanonicalName(), null);
        
        AgentController ac2 = cc.createNewAgent(agentName2,
                Santa.class.getCanonicalName(), null);
        
        AgentController ac3 = cc.createNewAgent(agentName3,
                Rudolph.class.getCanonicalName(), null);
        
        ac1.start();
        ac2.start();
        ac3.start();
        
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
   
