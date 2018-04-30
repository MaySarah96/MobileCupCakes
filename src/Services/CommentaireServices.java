/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.CategorieRec;
import Entity.Commentaire;
import Entity.SessionUser;
import Entity.Utilisateur;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aziz
 */
public class CommentaireServices {
    
    private ConnectionRequest con;
    
    public void AjouterCommentaire(String idRec , String body){
        con = new ConnectionRequest();
        con.setUrl("http://localhost/MobileCupCakes/ScriptPHP/Recettes/AjouterCommentaire.php?uid="+SessionUser.getId()+"&rid="+idRec+"&body="+body); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData(); 
                    String s = new String(data);  
                    if (s.equals("success")) {
                        Dialog.show("Succés", "ajout effectué", "Ok", null);
                        
                       /* if (user.getRoles()=="")
                            new UserAccueil().startAccueil(user);
                        else
                            new UserAccueil2().startAccueil(user);*/
                    } 
                    else {
                        Dialog.show("Echec", "comment existe deja", "Ok", null);
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void ModifierCommentaire(Commentaire comment){
        con = new ConnectionRequest();
        con.setUrl("http://localhost/MobileCupCakes/ScriptPHP/Recettes/ModifierCommentaire.php?idCmnt="+comment.getIdCmnt()+"&body="+comment.getBody()); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData(); 
                    String s = new String(data);  
                    if (s.equals("success")) {
                        Dialog.show("Succés", "Modification effectué", "Ok", null);
                        
                       /* if (user.getRoles()=="")
                            new UserAccueil().startAccueil(user);
                        else
                            new UserAccueil2().startAccueil(user);*/
                    } 
                    else {
                        Dialog.show("Echec", "Erreur", "Ok", null);
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void AjouterReply(String idRec , String body,String ancestors){
        con = new ConnectionRequest();
        con.setUrl("http://localhost/MobileCupCakes/ScriptPHP/Recettes/AjouterReply.php?uid="+SessionUser.getId()+"&rid="+idRec+"&body="+body+"&ancestors="+ancestors); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData(); 
                    String s = new String(data);  
                    if (s.equals("success")) {
                        Dialog.show("Succés", "ajout effectué", "Ok", null);
                        
                       /* if (user.getRoles()=="")
                            new UserAccueil().startAccueil(user);
                        else
                            new UserAccueil2().startAccueil(user);*/
                    } 
                    else {
                        Dialog.show("Echec", "comment existe deja", "Ok", null);
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public List<Commentaire>findComment(int idRec){
        List<Commentaire> listComment = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/MobileCupCakes/ScriptPHP/Recettes/AfficherCommentaire.php?rid="+idRec); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    try {
                        JSONParser j = new JSONParser();
                        Map<String, Object> Commentaires = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        System.out.println(Commentaires);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) Commentaires.get("info");
                        System.out.println("info : " + list);
                        if( list!=null)
                        {
                            for (Map<String, Object> obj : list) { 
                                Commentaire comment = new Commentaire();
                                comment.setIdCmnt(Integer.parseInt(obj.get("idCmnt").toString()));
                                comment.setAncestors(""+obj.get("ancestors").toString());
                                System.out.println(".actionPerformed() : "+comment.getAncestors());
                                comment.setBody(obj.get("body").toString());
                                comment.setCreatedAt(obj.get("created_at").toString());
                                Utilisateur user = new Utilisateur(Integer.parseInt(obj.get("idUser").toString()));
                                user.setUsername(obj.get("username").toString());
                                comment.setIdUser(user);
                                
                                listComment.add(comment);
                                
                            }
                        }
                    } catch (IOException ex) {
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComment;
    }
    
    public List<Commentaire>findReplyComment(String ancestors){
        List<Commentaire> listComment = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/MobileCupCakes/ScriptPHP/Recettes/AfficherReplyCommentaire.php?ancestors="+ancestors); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    try {
                        JSONParser j = new JSONParser();
                        Map<String, Object> Commentaires = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        System.out.println(Commentaires);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) Commentaires.get("info");
                        System.out.println("info : " + list);
                        if( list!=null)
                        {
                            for (Map<String, Object> obj : list) { 
                                Commentaire comment = new Commentaire();
                                comment.setIdCmnt(Integer.parseInt(obj.get("idCmnt").toString()));
                                comment.setAncestors(obj.get("ancestors").toString());
                                comment.setBody(obj.get("body").toString());
                                comment.setCreatedAt(obj.get("created_at").toString());
                                Utilisateur user = new Utilisateur(Integer.parseInt(obj.get("idUser").toString()));
                                user.setUsername(obj.get("username").toString());
                                comment.setIdUser(user);
                                
                                listComment.add(comment);
                                
                            }
                        }
                    } catch (IOException ex) {
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComment;
    }
    
    public void DeleteComment(int idCmnt){
        con = new ConnectionRequest();
        con.setUrl("http://localhost/MobileCupCakes/ScriptPHP/Recettes/DeleteCommentaire.php?idCmnt="+idCmnt); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData(); 
                    String s = new String(data);  
                    if (s.equals("success")) {
                        Dialog.show("Succés", "suppression effectué", "Ok", null);
                        
                       /* if (user.getRoles()=="")
                            new UserAccueil().startAccueil(user);
                        else
                            new UserAccueil2().startAccueil(user);*/
                    } 
                    else {
                        Dialog.show("Echec", "erreur", "Ok", null);
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
