/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
import Entity.LineCmd;
import Entity.Produit;
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
 * @author escobar
 */
public class LineCmdService {
        private ConnectionRequest con;
  public void AjouterLineCommande(int id,int qte,int idp)
    {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Commande/AjouterLineCmd.php?uid="+id+"&qteAcheter="+qte+"&idProd="+idp); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData(); 
                    String s = new String(data);  
                    if (s.equals("Error")) {
                        Dialog.show("Echec", "erreur", "Ok", null);
                        
                    } 
                    
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
  
     public List<LineCmd> findLine(int id)
    {
        List<LineCmd> commandes = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Commande/LineCmdListe.php?uid=" +id); 
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
                                LineCmd cmd = new LineCmd();
                                cmd.setQteAcheter(Integer.parseInt(obj.get("qteAcheter").toString()));
                                Produit p = new Produit(Integer.parseInt(obj.get("idProd").toString()));
                                p.setImageprod(obj.get("imageprod").toString());
                                p.setNomProd(obj.get("nomProd").toString());
                                p.setPrixProd(Integer.parseInt(obj.get("prixProd").toString()));
                                p.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                                p.setTypeProd(obj.get("typeProd").toString());
                                cmd.setProduit(p);
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
