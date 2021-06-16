package clasesRmi;

import aplicaciones.coordinador;

import interfacesRmi.objCoordinador;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class classCoordinador extends UnicastRemoteObject implements objCoordinador {

    //Guardara el valor que obtenga de loadavg
    String loadavg = "loadavg"; 
   

    public classCoordinador() throws RemoteException {
        super(); 
    }

    @Override
    public int iniMonitor(String id) throws RemoteException {
        
        //Registra monitor en la lista
        coordinador.listaMonitor.add(id);
        System.out.println("Monitor: " + id + " ha sido agregado");

        //Devuelvo el tiempo
        return coordinador.tiempo;
    }

    @Override
    public void loadMonitor(String loadavg) throws RemoteException {
        //Guarda el valor de loadvg
        this.loadavg = loadavg; 
    }

    @Override
    public int iniClient() throws RemoteException {
      
        //Tamanio de la lista--cantidad de monitores funcionando
        int i =0;
        int cont=0;
        
         //Hacer un ping para saber la cantidad de monitores funcionando
        for(;i<coordinador.listaMonitor.size();i++)
        {
            
            String ipAddress = coordinador.listaMonitor.get(i);
           InetAddress inet = null;
            try {
               
                inet = InetAddress.getByName(ipAddress);
            } catch (UnknownHostException ex) {
                Logger.getLogger(classCoordinador.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Hacer ping a al direccion ip del monitor
            try {
              // System.out.println(inet.isReachable(1099) ? cont : cont-1 );
              
               if(inet.isReachable(5000)){
                   //Si el ping se realizo con exito cont no cambia su valor
                   cont= coordinador.listaMonitor.size();
               
               }
               else{
                   //Si el ping no se realizo con exito cont disminuye en 1
                   cont =coordinador.listaMonitor.size()-1;
               }
               
            } catch (IOException ex) {
                Logger.getLogger(classCoordinador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
            
        return cont;
    }

    @Override
    public String getLoadAvg() throws RemoteException {
        //Manda el ultimo valor capturado por loadavg
        return loadavg; 
    }

}
