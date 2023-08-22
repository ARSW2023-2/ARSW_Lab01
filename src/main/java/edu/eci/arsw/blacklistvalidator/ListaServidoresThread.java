package edu.eci.arsw.blacklistvalidator;

import java.lang.Thread;
import java.util.LinkedList;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class ListaServidoresThread extends Thread {

    private int inicioSeccion;
    private int finSeccion;
    //cantidad de ocurrencias de la ip en la lista negra
    private int cantOcurrencias;
    private String ipaddress;
    //Cantidad de servidores que se encuentran en la lista negra
    private int cantServidores;
    private LinkedList<Integer> blackListOcurrences;

    public ListaServidoresThread(int inicioSeccion, int finSeccion, String ipaddress) {
        super();
        this.inicioSeccion = inicioSeccion;
        this.finSeccion = finSeccion;
        this.ipaddress = ipaddress;
        this.blackListOcurrences=new LinkedList<>();

    }

    public int getCanOcurrencias() {
        return cantOcurrencias;
    }

    public int getCantServidores() {
        return cantServidores;
    }

    public LinkedList<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }

    @Override
    public void run(){
        for (int i = inicioSeccion; i < finSeccion; i++) {
            if (HostBlacklistsDataSourceFacade.getInstance().isInBlackListServer(i, ipaddress)) {
                blackListOcurrences.add(i);
                cantOcurrencias++;
            }
            cantServidores++;
        }
    }
     
}
