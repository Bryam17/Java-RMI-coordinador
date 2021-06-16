package aplicaciones;

import clasesRmi.classCoordinador;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class coordinador {

    public static int tiempo;//variable de tiempo
    
    //Se crea la lista de monitores
    public static ArrayList<String> listaMonitor = new ArrayList<String>();

    public static void main(String[] args) {

        try {
            //Se hace el registro
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("miCoordinador", new classCoordinador());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            System.exit(0);
        }

        //Pedimos el intervalo de segundos 
        //Que luego seran enviados al monitor
        tiempo= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el intervalo de medicion de los monitores: "));
        
        //---
        System.out.println("Servidor en ejecucion...");
    }
}
