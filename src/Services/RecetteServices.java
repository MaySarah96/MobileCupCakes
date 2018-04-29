/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.CategorieRec;
import Entity.Recette;
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
public class RecetteServices {
    
    private ConnectionRequest con;

    public List<Recette> findRecette(int idUser)
    {
        List<Recette> recettes = new ArrayList<>();
        System.out.println("Services.RecetteServices.findRecette() "+idUser);
        con = new ConnectionRequest();
        con.setUrl("http://localhost/MobileCupCakes/ScriptPHP/Recettes/test.php?uid="+idUser+""); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    try {
                        JSONParser j = new JSONParser();
                        Map<String, Object> Recettes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        System.out.println(Recettes);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) Recettes.get("info");
                        System.out.println("info : " + list);
                        if( list!=null)
                        {
                            for (Map<String, Object> obj : list) { 
                                Recette recette = new Recette();
                                recette.setNomRec(obj.get("nomRec").toString());
                                recette.setIdRec(Integer.parseInt(obj.get("idRec").toString()));
                                recette.setDescriptionRec(obj.get("descriptionRec").toString());
                                recette.setIdCatRec(new CategorieRec(Integer.parseInt(obj.get("idCatRec").toString()), obj.get("nomCatRec").toString()));
                                recette.setImageRec(obj.get("imageRec").toString());
                                recettes.add(recette);
                                
                            }
                        }
                    } catch (IOException ex) {
                    }
                }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return recettes;
    }
}
