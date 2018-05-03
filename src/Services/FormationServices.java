/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Formation;
import Entity.SessionUser;
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
 * @author FERIEL FADHLOUN
 */
public class FormationServices {

    private ConnectionRequest con;

    //Afficher liste des formations disponible 
    public List<Formation> AfficherListFormations() {
        List<Formation> listF = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Utilisateur/listformation.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Formations = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Formations);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Formations.get("info");
                    System.out.println("info : " + list);

                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Formation formation = new Formation();
                            formation.setIdFor(Integer.parseInt(obj.get("idFor").toString()));
                            formation.setNomFor(obj.get("nomFor").toString());
                            formation.setDescriptionFor(obj.get("descriptionFor").toString());
                            formation.setImageform(obj.get("imageform").toString());
                            formation.setAtitude(obj.get("atitude").toString());
                            formation.setLongitude(obj.get("longitude").toString());
                            formation.setDateFor(obj.get("dateFor").toString());
                            listF.add(formation);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Services.FormationServices.AfficherListFormations()" + listF);
        return listF;
    }

    //Afficher liste des formations disponible 
    public List<Formation> AfficherListFormationsByIdFormateur() {
        List<Formation> listF = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Formateur/listformationByIdFormateur.php?iduser="+SessionUser.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Formations = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Formations);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Formations.get("info");
                    System.out.println("info : " + list);

                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Formation formation = new Formation();
                            formation.setIdFor(Integer.parseInt(obj.get("idFor").toString()));
                            formation.setNomFor(obj.get("nomFor").toString());
                            formation.setDescriptionFor(obj.get("descriptionFor").toString());
                            formation.setImageform(obj.get("imageform").toString());
                            formation.setDateFor(obj.get("dateFor").toString());
                            formation.setEtatFor(obj.get("etatFor").toString());
                            listF.add(formation);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Services.FormationServices.AfficherListFormationsByIdFormateur()" + listF);
        return listF;
    }
   
}
