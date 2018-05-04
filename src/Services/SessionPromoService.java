/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Formation;
import Entity.LinePromo;
import Entity.Linepromoses;
import Entity.Produit;
import Entity.Promotion;
import Entity.Session;
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
 * @author hamdi fathallah
 */
public class SessionPromoService {

    private ConnectionRequest con;

    public List<Linepromoses> findUser() {
        List<Linepromoses> lpromotions = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/sessionpromo.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> promotions = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(promotions);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) promotions.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Linepromoses promotion = new Linepromoses();
                            Session p = new Session();
                            p.setNomSes(obj.get("nomSes").toString());
                            p.setNvPrixSes(Double.parseDouble(obj.get("nv_prix_ses").toString()));
                            p.setPrixSes(Double.parseDouble(obj.get("prix_ses").toString()));
                            p.setCapaciteSes(Integer.parseInt(obj.get("capaciteSes").toString()));
                       p.setImagesess(obj.get("imagesess").toString());
                            promotion.setIdSes(p);
                            lpromotions.add(promotion);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lpromotions;
    }

    public List<Session> findSessionByUser() {
        List<Session> lsession = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/listsessionuser.php?uid="+SessionUser.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> sessions = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(sessions);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) sessions.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Session p = new Session();
                            Formation f = new Formation();
                            p.setNomSes(obj.get("nomSes").toString());
                            p.setIdSes(Integer.parseInt(obj.get("idSes").toString()));
                            //  p.setNvPrix(Integer.parseInt(obj.get("nv_prix").toString()));
                            p.setPrixSes(Double.parseDouble(obj.get("prix_ses").toString()));
                            p.setCapaciteSes(Integer.parseInt(obj.get("capaciteSes").toString()));
                            f.setIdFor(Integer.parseInt(obj.get("idFor").toString()));

                            p.setIdFor(f);
                            lsession.add(p);

                        }
                    }
                } catch (IOException ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return lsession;
    }

    public void ajoutpromosession(Linepromoses pr) {
        con = new ConnectionRequest();
        System.out.println("session" + pr.getIdSes());
        System.out.println("promo" + pr.getIdPromo());
        System.out.println("date" + pr.getDateDeb().toString());
        System.out.println("datef" + pr.getDateFin().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateDeb = formatter.format(pr.getDateDeb());
        String dateFin = formatter.format(pr.getDateFin());
        System.out.println("date" + dateDeb);
        System.out.println("dateFin " + dateFin);

        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/ajoutpromoses.php?idSes=" + pr.getIdSes().getIdSes() + "&dateDeb='" + dateDeb + "'&dateFin='" + dateFin + "'&idPromo=" + pr.getIdPromo().getIdPromo());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.out.println(".actionPerformed() " + s);
                if (s.equals("success")) {

                    Dialog.show("Succes", "ajout effectué", "Ok", null);

                    /* if (user.getRoles()=="")
                            new UserAccueil().startAccueil(user);
                        else
                            new UserAccueil2().startAccueil(user);*/
                } else {

                    Dialog.show("Echec", "echec", "Ok", null);
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public List<Promotion> findPromo() {
        List<Promotion> lpromotions = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/findPromo.php ");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> promotions = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(promotions);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) promotions.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Promotion p = new Promotion();
                            p.setTauxPromo(Double.parseDouble(obj.get("tauxPromo").toString()));
                            lpromotions.add(p);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return lpromotions;
    }

    public void calculepromoses(Session pr) {
        con = new ConnectionRequest();

        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/calculepromoses.php?nv_prix_ses=" + pr.getNvPrixSes() + "&idSes=" + pr.getIdSes());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.out.println(".actionPerformed() " + s);
                if (s.equals("success")) {

                    Dialog.show("Succes", "ajout effectué", "Ok", null);

                    /* if (user.getRoles()=="")
                            new UserAccueil().startAccueil(user);
                        else
                            new UserAccueil2().startAccueil(user);*/
                } else {

                    Dialog.show("Echec", "echec", "Ok", null);
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
        public List<Session> findPromosesByUser()
    {
        List<Session> lsession = new ArrayList<>();
        
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/affichepromosesuser.php?uid="+SessionUser.getId()); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    try {
                        JSONParser j = new JSONParser();
                        Map<String, Object> sessions = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        System.out.println(sessions);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) sessions.get("info");
                        System.out.println("info : " + list);
                        if( list!=null)
                        {
                            for (Map<String, Object> obj : list) { 
                                Session p = new Session();
                                p.setNomSes(obj.get("nomSes").toString());
                                p.setIdSes(Integer.parseInt(obj.get("idSes").toString()));
                                p.setImagesess(obj.get("imagesess").toString());
                              //  p.setNvPrix(Integer.parseInt(obj.get("nv_prix").toString()));
                                p.setPrixSes(Double.parseDouble(obj.get("prix_ses").toString()));
                                p.setCapaciteSes(Integer.parseInt(obj.get("capaciteSes").toString()));
                                p.setNvPrixSes(Double.parseDouble(obj.get("nv_prix_ses").toString()));
                                lsession.add(p);

                            }
                        }
                    } catch (IOException ex) {
                    }
                }
        });
       
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lsession;
    }
        
        
         public void updateetat(Linepromoses pr) {
        con = new ConnectionRequest();

        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/updateetatses.php?id"+pr.getIdLine());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.out.println(".actionPerformed() " + s);
                if (s.equals("success")) {

                    Dialog.show("Succes", "ajout effectué", "Ok", null);

                    /* if (user.getRoles()=="")
                            new UserAccueil().startAccueil(user);
                        else
                            new UserAccueil2().startAccueil(user);*/
                } else {

                    Dialog.show("Echec", "echec", "Ok", null);
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        
}
