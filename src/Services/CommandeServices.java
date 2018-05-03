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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.BrowserNavigationCallback;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author escobar
 */
public class CommandeServices {

    private ConnectionRequest con;
    String commandes = "";

    public String AjouterCommande(Commande commande) {
        con = new ConnectionRequest();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String dateL = dt.format(commande.getDateLivCmd());
        con.setUrl("http://localhost/Demo/ScriptPHP/Commande/AjouterCommande.php?uid=" + SessionUser.getId() + "&dateLiv=" + dateL + "&addLiv=" + commande.getAddLiv() + "&montantCmd=" + commande.getMontantCmd());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                try {
                    commandes = new String(con.getResponseData(), "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                }
                if (s.equals("Error")) {
                    Dialog.show("Echec", "erreur", "Ok", null);

                } else {
                    Dialog.show("Succés", "ajout effectué", "Ok", null);

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return commandes;
    }

    public void AjouterFeedback(int id, String sujet, String Desc) {
        con = new ConnectionRequest();

        con.setUrl("http://localhost/Demo/ScriptPHP/Commande/ajouterFeedback.php?uid=" + id + "&sujet=" + sujet + "&description=" + Desc);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                if (s.equals("success")) {
                    Dialog.show("Succés", "ajout effectué", "Ok", null);

                } else {
                    Dialog.show("Echec", "erreur", "Ok", null);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public List<Commande> findCommandeEnCour(int id) {
        List<Commande> commandes = new ArrayList<>();
        id = SessionUser.getId();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Commande/listeCommandeencour.php?uid=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Commandes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("a " + Commandes);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Commandes.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Commande cmd = new Commande();
                            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateL = dt.parse(obj.get("dateLivCmd").toString());
                            Date dateC = dt.parse(obj.get("dateCmd").toString());
                            cmd.setDateCmd(dateC);
                            cmd.setDateLivCmd(dateL);
                            cmd.setIdCmd(Integer.parseInt(obj.get("idCmd").toString()));
                            cmd.setMontantCmd(Double.parseDouble(obj.get("montantCmd").toString()));
                            cmd.setAddLiv(obj.get("addLiv").toString());
                            commandes.add(cmd);
                        }
                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commandes;
    }

    public List<Commande> findCommandeLivre(int id) {
        List<Commande> commandes = new ArrayList<>();
        id = SessionUser.getId();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Commande/listeCommandeLivree.php?uid=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Commandes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("a " + Commandes);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Commandes.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Commande cmd = new Commande();
                            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateL = dt.parse(obj.get("dateLivCmd").toString());
                            Date dateC = dt.parse(obj.get("dateCmd").toString());
                            cmd.setDateCmd(dateC);
                            cmd.setDateLivCmd(dateL);
                            cmd.setIdCmd(Integer.parseInt(obj.get("idCmd").toString()));
                            cmd.setMontantCmd(Double.parseDouble(obj.get("montantCmd").toString()));
                            cmd.setAddLiv(obj.get("addLiv").toString());
                            commandes.add(cmd);
                        }
                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commandes;
    }

    public List<Commande> findCommandePrete(int id) {
        List<Commande> commandes = new ArrayList<>();
        id = SessionUser.getId();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Commande/listeCommandePrete.php?uid=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Commandes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("a " + Commandes);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Commandes.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Commande cmd = new Commande();
                            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateL = dt.parse(obj.get("dateLivCmd").toString());
                            Date dateC = dt.parse(obj.get("dateCmd").toString());
                            cmd.setDateCmd(dateC);
                            cmd.setDateLivCmd(dateL);
                            cmd.setIdCmd(Integer.parseInt(obj.get("idCmd").toString()));
                            cmd.setMontantCmd(Double.parseDouble(obj.get("montantCmd").toString()));
                            cmd.setAddLiv(obj.get("addLiv").toString());
                            commandes.add(cmd);
                        }
                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commandes;
    }

    public List<LineCmd> findCommandeEnCourPat(int id) {
        List<LineCmd> commandes = new ArrayList<>();
        id = SessionUser.getId();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Commande/listeCommandeencourPat.php?uid=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Commandes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("a " + Commandes);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Commandes.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            LineCmd cmd = new LineCmd();
                            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateL = dt.parse(obj.get("dateLivCmd").toString());
                            Date dateC = dt.parse(obj.get("dateCmd").toString());
                            Commande c = new Commande((Integer.parseInt(obj.get("idCmd").toString())), dateC, (Double.parseDouble(obj.get("montantCmd").toString())), dateL, (obj.get("addLiv").toString()));
                            Produit p = new Produit();
                            p.setNomProd(obj.get("nomProd").toString());
                            p.setImageprod(obj.get("imageprod").toString());
                            cmd.setCommande(c);
                            cmd.setProduit(p);
                            cmd.setQteAcheter(Integer.parseInt(obj.get("qteAcheter").toString()));
                            commandes.add(cmd);
                        }
                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commandes;
    }

    public List<LineCmd> findCommandeLivrePat(int id) {
        List<LineCmd> commandes = new ArrayList<>();
        id = SessionUser.getId();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Commande/listeCommandeLivreePat.php?uid=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Commandes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("a " + Commandes);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Commandes.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            LineCmd cmd = new LineCmd();
                            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateL = dt.parse(obj.get("dateLivCmd").toString());
                            Date dateC = dt.parse(obj.get("dateCmd").toString());
                            Commande c = new Commande((Integer.parseInt(obj.get("idCmd").toString())), dateC, (Double.parseDouble(obj.get("montantCmd").toString())), dateL, (obj.get("addLiv").toString()));
                            Produit p = new Produit();
                            p.setNomProd(obj.get("nomProd").toString());
                            p.setImageprod(obj.get("imageprod").toString());
                            cmd.setCommande(c);
                            cmd.setProduit(p);
                            cmd.setQteAcheter(Integer.parseInt(obj.get("qteAcheter").toString()));
                            commandes.add(cmd);
                        }
                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commandes;
    }

    public List<LineCmd> findCommandePretePat(int id) {
        List<LineCmd> commandes = new ArrayList<>();
        id = SessionUser.getId();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Commande/listeCommandePretePat.php?uid=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Commandes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("a " + Commandes);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Commandes.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            LineCmd cmd = new LineCmd();
                            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateL = dt.parse(obj.get("dateLivCmd").toString());
                            Date dateC = dt.parse(obj.get("dateCmd").toString());
                            Commande c = new Commande((Integer.parseInt(obj.get("idCmd").toString())), dateC, (Double.parseDouble(obj.get("montantCmd").toString())), dateL, (obj.get("addLiv").toString()));
                            Produit p = new Produit();
                            p.setNomProd(obj.get("nomProd").toString());
                            p.setImageprod(obj.get("imageprod").toString());
                            cmd.setCommande(c);
                            cmd.setProduit(p);
                            cmd.setQteAcheter(Integer.parseInt(obj.get("qteAcheter").toString()));
                            commandes.add(cmd);
                        }
                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commandes;
    }

}
