package aplicaciones;

import interfacesRmi.objCoordinador;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class cliente {

    public static void main(String[] args) {

        int segundos, i = 1;
        String ip = "localhost";

        //Pedimos la ip del servidor
       
        ip = JOptionPane.showInputDialog("Ingrese la direccion IP del servidor:");

        try {
            //Registro de las funciones
            Registry registry = LocateRegistry.getRegistry(ip, 1099);
            objCoordinador miCoordinador = (objCoordinador) registry.lookup("miCoordinador");

            //Mensaje en pantalla de cuantos monitores estan funcionando
            System.out.println("Monitores funcionando: " + miCoordinador.iniClient());

            if (miCoordinador.iniClient() > 0) {

                //Pedimos los segundos 
               
                segundos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el intervalo de segundos: "));
               

                while (true) {
                  
                    System.out.println("Monitores funcionando: " + miCoordinador.iniClient());
                    //Imprimo loadavg
                    System.out.println(i + ": " + miCoordinador.getLoadAvg());
                    i++;

                    
                    Thread.sleep(segundos * 1000);
                }
            } else {
                System.out.println("Saliendo...");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
