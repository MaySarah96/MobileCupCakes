/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Session;
import Entity.SessionUser;
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
 * @author FERIEL FADHLOUN
 */
public class SessionServices {

    private ConnectionRequest con;

    ////afficher miste des sessions par id formation
    public List<Session> findSessionByIdFor(int idFor) {
        List<Session> listS = new ArrayList<Session>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Utilisateur/listSessionFor.php?uid='" + idFor + "'");
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
                            Session session = new Session();
                            session.setIdSes(Integer.parseInt(obj.get("idSes").toString()));
                            session.setNomSes(obj.get("nomSes").toString());
                            session.setImagesess(obj.get("imagesess").toString());
                            session.setDateDebSes(obj.get("dateDebSes").toString());
                            session.setDateFinSes(obj.get("dateFinSes").toString());
                            listS.add(session);
                        }
                    }
                    if (listS.isEmpty()) {
                            Dialog.show("Echec", "aucune sesion trouvée ", "Ok", null);
                        }
                   

                } catch (IOException ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listS;
    }

    //liste sessions client finies
    public List<Session> findSessionENCOURS() {

        List<Session> listS = new ArrayList<Session>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Utilisateur/ListSesENCOURS.php?uid='" + SessionUser.getId() + "'");
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
                            Session session = new Session();
                            session.setImagesess(obj.get("imagesess").toString());
                            session.setNomSes(obj.get("nomSes").toString());
                            session.setDateDebSes(obj.get("dateDebSes").toString());
                            session.setDateFinSes(obj.get("dateFinSes").toString());
                            listS.add(session);
                        }
                         if (listS.isEmpty()) {
                        Dialog.show("Echec", "Client n'a aucune sesssion en cours ", "Ok", null);
                    }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listS;
    }

    //liste sessions client finies
    public List<Session> findSessionFINIES() {

        List<Session> listS = new ArrayList<Session>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Utilisateur/ListSesFINIES.php?uid='" + SessionUser.getId() + "'");
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
                            Session session = new Session();
                            session.setImagesess(obj.get("imagesess").toString());
                            session.setNomSes(obj.get("nomSes").toString());
                            session.setDateDebSes(obj.get("dateDebSes").toString());
                            session.setDateFinSes(obj.get("dateFinSes").toString());
                            listS.add(session);
                        }
                    }
                     if (listS.isEmpty()) {
                        Dialog.show("Echec", "Client n'a aucune sesssion finie ", "Ok", null);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listS;
    }

    //liste de toutes les sessions u user connecte
    public List<Session> AfficherLesSessionsByIdFormateur() {

        List<Session> listS = new ArrayList<Session>();
        System.out.println("id user"+SessionUser.getId());
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Formateur/listSessionsByIdFormateur.php?iduser=" + SessionUser.getId() + "");
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
                            Session session = new Session();
                            session.setImagesess(obj.get("imagesess").toString());
                            session.setNomSes(obj.get("nomSes").toString());
                            session.setDateDebSes(obj.get("dateDebSes").toString());
                            session.setDateFinSes(obj.get("dateFinSes").toString());
                            session.setPrixSes(Double.parseDouble((String) obj.get("prix_ses")));
                            listS.add(session);
                        }
                         if (listS.isEmpty()) {
                        Dialog.show("Echec", "Client n'a aucune sesssion en cours ", "Ok", null);
                    }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listS;
    }

    //afficher les sessions a partir de lid formation du formateur connecte
    public List<Session> findSessionFormationByIdFormateur(int idFor) {
        List<Session> listS = new ArrayList<Session>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Formateur/listSessionsFormationByIdFormateur.php?iduser="+SessionUser.getId()+"&idfor='" + idFor + "'");
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
                            Session session = new Session();
                            session.setIdSes(Integer.parseInt(obj.get("idSes").toString()));
                            session.setNomSes(obj.get("nomSes").toString());
                            session.setImagesess(obj.get("imagesess").toString());
                            session.setDateDebSes(obj.get("dateDebSes").toString());
                            session.setDateFinSes(obj.get("dateFinSes").toString());
                            session.setPrixSes(Double.parseDouble((String) obj.get("prix_ses")));
                            listS.add(session);
                        }
                    }
                    if (listS.isEmpty()) {
                            Dialog.show("Echec", "aucune sesion trouvée ", "Ok", null);
                        }
                   

                } catch (IOException ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listS;
    }
}
