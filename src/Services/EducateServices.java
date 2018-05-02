/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Educate;
import Entity.SessionUser;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FERIEL FADHLOUN
 */
public class EducateServices {
    
      
    private ConnectionRequest con;
    
    //inscription client
    public void InscriptionClient(int idSes)
    {
        con = new ConnectionRequest();
        con.setUrl("http://localhost:8088/MobileAziz/ScriptPHP/Utilisateur/InscriptionClient.php?uid=" +idSes+""); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
          
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData(); 
                    String s = new String(data);  
                    if (s.equals("success")) {
                        Dialog.show("Succés", "inscription effectuée avec succes", "Ok", null);
                       
                    } 
                    else {
                        Dialog.show("Echec", "client deja inscrit ", "Ok", null);
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    //verifier si le client nest pa notifie
    public List<Educate> SearchNonNotifie(int idSes) {
     
        java.util.Date today = new java.util.Date();
        java.util.Date tomorrow = new java.util.Date(today.getTime() + (1000 * 60 * 60 * 24));
        SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
        String date=formater.format(tomorrow);
        System.out.println("today="+today);
        System.out.println("tomorrow="+tomorrow);
        System.out.println("date eli nheb"+date);
        System.out.println("search non notif");
        List<Educate> listInscri = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost:8088/MobileAziz/ScriptPHP/Utilisateur/SearchNonNotif.php?uid="+idSes+"&date="+date);
    
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Users = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Users);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Users.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Educate educate = new Educate();
                            educate.setDateIscri(obj.get("dateIscri").toString());
                            educate.setEtatEduc(obj.get("etatEduc").toString());
                           
                            listInscri.add(educate);
                            System.out.println("www"+listInscri);
                        }
                      
                    }
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
       return listInscri;
    }
   
     
     //modification etat de notification 
    public void ModifieEtatNotif(int idSes)
    {
        con = new ConnectionRequest();
        con.setUrl("http://localhost:8088/MobileAziz/ScriptPHP/Utilisateur/ModifieEtatNotif.php?idses="+idSes+"&iduser="+1); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
          
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData(); 
                    String s = new String(data);  
                    if (s.equals("success")) {
                        //Dialog.show("Succés", "inscription effectuée avec succes", "Ok", null);
                        System.out.println("modifcation effectuée avec succes");
                    } 
                    else {
                       // Dialog.show("Echec", "client deja inscrit ", "Ok", null);
                        System.out.println("non modifie");
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    //liste des incription du client donne
    public List<Educate> ListeIncriptionClient() {
        System.out.println("west el liste client ");
        
        List<Educate> listInscri = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost:8088/MobileAziz/ScriptPHP/Utilisateur/ListInscriptionClient.php?iduser="+SessionUser.getId()+"");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Users = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Users);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Users.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                         Educate educate = new Educate();
                        for (Map<String, Object> obj : list) {
                            educate.setDateIscri(obj.get("dateIscri").toString());
                            educate.setEtatEduc(obj.get("etatEduc").toString());
                            educate.setEtatNotif(obj.get("etatNotif").toString());
                            //educate.setUtilisateur(Integer.parseInt(obj.get("idUser").toString()));
                           listInscri.add(educate);
                        }
                    }
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
       return listInscri;
    }
}
