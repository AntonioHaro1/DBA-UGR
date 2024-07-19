/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import java.util.Scanner;

/**
 *
 * @author antonio
 */
public class Ejercicio4 extends Agent{
    
    int cantidad,suma;
    double media;
    
    private class Comportamiento_PedirNumero extends OneShotBehaviour {
        //boolean terminado = false;
         int numero;
         //int iteracion;
        @Override
        public void action()
        {   
            System.out.print("Introduce un numero:  ");
            Scanner scanner = new Scanner(System.in);
  
            if(scanner.hasNextInt())
            {
                numero = scanner.nextInt();
            }else{
                System.out.println("No has introducido un numero valido");
            }
            
            System.out.println("numero elegido es: " + numero);
            
            
            suma = suma + numero;
            //iteracion++;
        }
        /*
        @Override
        public boolean done(){
        
            if(iteracion == cantidad)
            {
                return true;
            }else
            {
                return false;
            }
        }
*/

    }
    
     private class Comportamiento_Media extends OneShotBehaviour {
       //boolean terminado = false;
        @Override
        public void action() {
            
           media = (double) suma/cantidad;
           
           System.out.println("La media es:" + media);
           addBehaviour(new Terminar_Agente());
           //terminado = true;
        }
/*
        @Override
        public boolean done() {
 
            return terminado;
        }
*/
    }
     
     private class Terminar_Agente extends OneShotBehaviour{
         
         @Override
         public void action(){
             System.out.println("Mi trabajo ha terminado");
             doDelete();
         }
/*
        @Override
        public boolean done() {
          return true;
        }
 */ 
     }
    
    @Override
    protected void setup()
    {
        suma = 0;
        media = 0.0;
        //iteracion = 0;
        // Pedimos al usuario cuantos numeros va a introducir
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Cuantos numeros vas a introducir?");
        
        cantidad = scanner.nextInt();
        
      
        
        // Creamos la secuencia de comportamientos
        SequentialBehaviour sequentialBehaviour = new SequentialBehaviour();
        
        //introducimos los comportamientos requeridos para pedir todos los numeros
        // que va  introducir el usuario
        for(int i = 0; i < cantidad ; i++)
        {
            sequentialBehaviour.addSubBehaviour(new Comportamiento_PedirNumero());
        }
        //Añadimos el comportamiento que hace la media al final
        sequentialBehaviour.addSubBehaviour(new Comportamiento_Media());
        
        // Activamos la secuencia de comportamientos
        addBehaviour(sequentialBehaviour);
    }
    
    protected void takeDown()
    {
        System.out.println( " Terminating agent...");
    }

}
