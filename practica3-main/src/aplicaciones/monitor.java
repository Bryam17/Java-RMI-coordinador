package aplicaciones;

import interfacesRmi.objCoordinador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class monitor {

    public static void main(String[] args) {

        int segundos;
        String ip = "localhost";
        String id;
        String loadavg;

        //Pedimos la ip del servidor
          ip = JOptionPane.showInputDialog("Ingrese la direccion IP del servidor:");
      
        try {
            //Registramos las funciones
            Registry registry = LocateRegistry.getRegistry(ip, 1099);
            objCoordinador miCoordinador = (objCoordinador) registry.lookup("miCoordinador");

            //EXTRAER VALOR DEL FICHERO HOSTNAME
        
            id = InetAddress.getLocalHost().getHostAddress();
         
            JOptionPane.showMessageDialog(null,"Monitor con IP: "+id);
            System.out.println("Monitor con IP: " + id+" en ejecucion");
            
           //Obtengo los segundos del coordinador y envio el id del monitor
            segundos = miCoordinador.iniMonitor(id);

            while (true) {

                //Extraemos el valor de loadavg
                BufferedReader br = new BufferedReader(new FileReader(new File("/proc/loadavg")));
                loadavg = br.readLine();

                //Mandamos el valor de loadavg
                miCoordinador.loadMonitor(id + ": " + loadavg);
                
                //Se hace una espera segun la cantidad de segundos
                Thread.sleep(segundos * 1000);
             
            }
          
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
       
        }
       
    }
                    
}
