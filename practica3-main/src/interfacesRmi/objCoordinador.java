package interfacesRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface objCoordinador extends Remote {

    
    int iniMonitor(String id) throws RemoteException; //Devuelve el intervalo de tiempo--la llama el monitor

    void loadMonitor(String loadavg) throws RemoteException; //Obtiene loadavg--lo llama el monitor

    int iniClient() throws RemoteException; //Devuelve la cantidad de monitores en funcionamiento--la llama el cliente

    String getLoadAvg() throws RemoteException; //Devuelve el valor de loadavg--la llama el cliente
}
