/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.LinePromo;
import Entity.Produit;
import Entity.Promotion;
import Entity.SessionUser;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hamdi fathallah
 */
public class PromotionService {

    private ConnectionRequest con;
    boolean test;
    int count ;
    
    public List<LinePromo> findUser() {
        List<LinePromo> lpromotions = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/promotion.php");
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
                            LinePromo promotion = new LinePromo();
                            Produit p = new Produit();
                            p.setNomProd(obj.get("nomProd").toString());
                            p.setImageprod(obj.get("imageprod").toString());
                            p.setNvPrix(Integer.parseInt(obj.get("nv_prix").toString()));
                            p.setPrixProd(Integer.parseInt(obj.get("prixProd").toString()));
                            p.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                            //EEE MMM dd HH:mm:ss zzz yyyy
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateDb = format.parse(obj.get("dateDeb").toString());
                            promotion.setId(Integer.parseInt(obj.get("idLinePromo").toString()));
                            promotion.setDateDeb(dateDb);
                            Date datefn = format.parse(obj.get("dateFin").toString());
                            promotion.setDateFin(datefn);
                            // promotion.setDateDeb(obj.get("dateDeb").toString());
//                            promotion.setViews(Double.parseDouble(obj.get("views").toString()));
                            // promotion.setDateFin(obj.get("dateFin").toString());
                            promotion.setIdProd(p);
                            lpromotions.add(promotion);
                        }
                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return lpromotions;
    }

    public void ajoutpromoproduit(LinePromo pr) {
        con = new ConnectionRequest();
        System.out.println("produit" + pr.getIdProd());
        System.out.println("promo" + pr.getIdPromo());
        System.out.println("date" + pr.getDateDeb().toString());
        System.out.println("datef" + pr.getDateFin().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateDeb = formatter.format(pr.getDateDeb()
        );
        String dateFin = formatter.format(pr.getDateFin());
        System.out.println("date" + dateDeb);
        System.out.println("dateFin " + dateFin);

        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/ajoutpromo.php?idProd=" + pr.getIdProd().getIdProd() + "&dateDeb='" + dateDeb + "'&dateFin='" + dateFin + "'&idPromo=" + pr.getIdPromo().getIdPromo());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.out.println(".actionPerformed() " + s);
                if (s.equals("success")) {

                    Dialog.show("Succes", "ajout effectué", "Ok", null);
//                    sms();
                    System.out.println("message avec succee");
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

    public void calculepromo(Produit pr) {
        con = new ConnectionRequest();

        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/calculepromo.php?nv_prix=" + pr.getNvPrix() + "&idProd=" + pr.getIdProd());
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

    public List<Produit> findPromoByUser() {
        List<Produit> lproduit = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/affichepromouser.php?uid="+SessionUser.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Produit p = new Produit();
                            p.setNomProd(obj.get("nomProd").toString());
                            p.setIdProd(Integer.parseInt(obj.get("idProd").toString()));
                            p.setImageprod(obj.get("imageprod").toString());
                            //  p.setNvPrix(Integer.parseInt(obj.get("nv_prix").toString()));
                            p.setPrixProd(Integer.parseInt(obj.get("prixProd").toString()));
                            p.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                            p.setNvPrix(Integer.parseInt(obj.get("nv_prix").toString()));
                            lproduit.add(p);

                        }
                    }
                } catch (IOException ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return lproduit;
    }

    public boolean userviews(LinePromo p) {
        test = false;
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/selectuserpromo.php?uid=" + SessionUser.getId() + "&uid1=" + p.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]   ) evt.getMetaData();
                String s = new String(data);
                System.out.println(".actionPerformed() " + s);
                if (s.equals("success")) {
                    test = true;
                    // Dialog.show("Succes", "ajout effectué", "Ok", null);

                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return test;
    }

    public boolean insertviwes(LinePromo p) {
        test = false;
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/insertviews.php?uid=" + SessionUser.getId() + "&uid1=" + p.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.out.println(".actionPerformed() " + s);
                if (s.equals("success")) {
                    test = true;
                    // Dialog.show("Succes", "ajout effectué", "Ok", null);

                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return test;
    }

    public int countView(LinePromo p) {
        con = new ConnectionRequest();
        count = 0;
        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/calculeviews.php?uid=" + p.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                                count = Integer.parseInt(obj.get("count").toString());
                                System.out.println(count);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return count ;
    }
    
      public void updateetat(LinePromo pr) {
        con = new ConnectionRequest();

        con.setUrl("http://localhost/Demo/ScriptPHP/Promotion/updateetatpromo.php?nid"+pr.getId());
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
