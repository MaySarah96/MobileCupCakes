/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Produit;
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
 * @author escobar
 */
public class ProduitService {
    
    private ConnectionRequest con;

    public List<Produit> findUser()
    {
        List<Produit> recettes = new ArrayList<>();
        
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Utilisateur/listeProduitSucree.php"); 
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
                                Produit prod = new Produit();
                               prod.setNomProd(obj.get("nomProd").toString());
                                prod.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                                prod.setPrixProd(Integer.parseInt(obj.get("prixProd").toString()));
                                recettes.add(prod);
                                
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


