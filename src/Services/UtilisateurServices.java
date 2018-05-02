/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Utilisateur;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aziz
 */
public class UtilisateurServices {
    
    private ConnectionRequest con;
    
    public Utilisateur findUser(String login)
     {
        Utilisateur user = new  Utilisateur();
        user.setId(null);
        con = new ConnectionRequest();
        con.setUrl("http://localhost:8088/MobileCupCakes/ScriptPHP/Utilisateur/info_id.php?uid='" +login+"'"); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    try {
                        JSONParser j = new JSONParser();
                        Map<String, Object> Users = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        System.out.println(Users);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) Users.get("info");
                        System.out.println("info : " + list);
                        if( list!=null)
                        {
                            for (Map<String, Object> obj : list) { 
                                user.setId(Integer.parseInt(obj.get("id").toString()));
                                user.setUsername(obj.get("username").toString());
                                user.setPassword(obj.get("password").toString());
                                user.setEmail(obj.get("email").toString());
                                user.setImageProfil(obj.get("imageProfil").toString());
                                user.setAddresse(obj.get("addresse").toString());
                                user.setRoles(obj.get("roles").toString());
                                user.setPhoneNumber(obj.get("phoneNumber").toString());
                                user.setNom(obj.get("nom").toString());
                                user.setPrenom(obj.get("prenom").toString());
                            }
                        }
                    } catch (IOException ex) {
                    }
                }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user;
    }
    
}
