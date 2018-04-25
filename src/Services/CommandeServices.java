/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author escobar
 */
public class CommandeServices {
     private ConnectionRequest con;

    public List<Commande> findCommande()
    {
        List<Commande> commandes = new ArrayList<>();
        
        con = new ConnectionRequest();
        con.setUrl("http://localhost/MobileCupCakes/ScriptPHP/Utilisateur/listeCommande.php"); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    try {
                        JSONParser j = new JSONParser();
                        Map<String, Object> Commandes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        System.out.println("a " +Commandes);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) Commandes.get("info");
                        System.out.println("info : " + list);
                        if( list!=null)
                        {
                            for (Map<String, Object> obj : list) { 
                                Commande cmd = new Commande();
                                cmd.setMontantCmd(Double.parseDouble(obj.get("montantCmd").toString()));
                                cmd.setAddLiv(obj.get("addLiv").toString());
                                cmd.setEtatLivCmd(obj.get("etatLivCmd").toString());
                                commandes.add(cmd);
                            }
                        }
                    } catch (IOException ex) {
                    }
                }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commandes;
    }
}
