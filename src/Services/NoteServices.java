/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Note;
import Entity.Recette;
import Entity.SessionUser;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aziz
 */
public class NoteServices {

    private ConnectionRequest con;
    Note note = null ;
    Double moyenne = 0.0 ;

    public NoteServices() {
    }
    
    public Note rechercheNote(Recette recette)
    {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo1/ScriptPHP/Recettes/NoteRecette.php?uid="+SessionUser.getId()+"&rid="+recette.getIdRec()); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    try {
                        JSONParser j = new JSONParser();
                        Map<String, Object> Notes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        System.out.println(Notes);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) Notes.get("info");
                        System.out.println("info : " + list);
                        if( list!=null && !list.isEmpty())
                        {
                            for (Map<String, Object> obj : list) { 
                                note = new Note();
                                note.setNote(Double.parseDouble(obj.get("note").toString()));
                                note.setRecette(recette);
                            }
                        }
                    } catch (IOException ex) {
                    }
                }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return note;
    }
    
    public void AjouterNote(Note note)
    {
        System.out.println("Services.NoteServices.AjouterNote() ajout tttttttttttttt"+ note.getNote());
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo1/ScriptPHP/Recettes/AjouterNote.php?uid="+SessionUser.getId()+"&rid="+note.getRecette().getIdRec()+"&note="+note.getNote()); 
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
                        Dialog.show("Echec", "note existe deja", "Ok", null);
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void ModifierNote(Note note)
    {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo1/ScriptPHP/Recettes/ModifierNote.php?uid="+SessionUser.getId()+"&rid="+note.getRecette().getIdRec()+"&note="+note.getNote()); 
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
                        Dialog.show("Echec", "note existe deja", "Ok", null);
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public double MoyenneNote(int idRec){

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo1/ScriptPHP/Recettes/MoyenneNote.php?&rid="+idRec); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    try {
                        JSONParser j = new JSONParser();
                        Map<String, Object> Notes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        System.out.println(Notes);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) Notes.get("info");
                        System.out.println("info : " + list);
                        if( list!=null && !list.isEmpty())
                        {
                            for (Map<String, Object> obj : list) { 
                                moyenne = Double.parseDouble(obj.get("note").toString());
                            }
                        }
                    } catch (IOException ex) {
                    }
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return moyenne ;
    }
}
