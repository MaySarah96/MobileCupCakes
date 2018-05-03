/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Categorie;
import Entity.Produit;

import Entity.SessionUser;
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
 * @author escobar
 */
public class ProduitService {

    private ConnectionRequest con;

    public List<Produit> findProduitSucree(int idUser) {
        List<Produit> recettes = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Produits/listeProduitSucree.php?uid=" + SessionUser.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Recettes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Recettes);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Recettes.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Produit prod = new Produit();
                            prod.setIdProd(Integer.parseInt(obj.get("idProd").toString()));
                            prod.setNomProd(obj.get("nomProd").toString());
                            prod.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                            prod.setPrixProd(Integer.parseInt(obj.get("prixProd").toString()));
                            prod.setImageprod(obj.get("imageprod").toString());
                            Categorie cat = new Categorie(Integer.parseInt(obj.get("idCat").toString()), obj.get("nomCat").toString());
                            prod.setIdCat(cat);
                            prod.setQteAcheter(Integer.parseInt(obj.get("QteAcheter").toString()));
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

    public List<Produit> findProduitSalee(int idUser) {
        List<Produit> listprod = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Produits/listeProduitSalee.php?uid=" + SessionUser.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Produits.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Produit prod = new Produit();
                            prod.setIdProd(Integer.parseInt(obj.get("idProd").toString()));
                            prod.setNomProd(obj.get("nomProd").toString());
                            prod.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                            prod.setPrixProd(Integer.parseInt(obj.get("prixProd").toString()));
                            prod.setImageprod(obj.get("imageprod").toString());
                            Categorie cat = new Categorie(Integer.parseInt(obj.get("idCat").toString()), obj.get("nomCat").toString());
                            prod.setIdCat(cat);
                            prod.setQteAcheter(Integer.parseInt(obj.get("QteAcheter").toString()));
                            listprod.add(prod);

                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listprod;
    }

    public List<Produit> AfficherListProduits() {
        List<Produit> listP = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Produits/listProduit.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Produits.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Produit Produit = new Produit();
                            Produit.setIdProd(Integer.parseInt(obj.get("idProd").toString()));

                            Produit.setNomProd(obj.get("nomProd").toString());
                            Produit.setImageprod(obj.get("imageprod").toString());
                            Produit.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                            Produit.setPrixProd(Integer.parseInt(obj.get("prixProd").toString()));
                            Categorie cat = new Categorie(Integer.parseInt(obj.get("idCat").toString()), (obj.get("nomCat").toString()));
                            Produit.setIdCat(cat);
                            Produit.setQteAcheter(Integer.parseInt(obj.get("QteAcheter").toString()));
                            listP.add(Produit);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Services.ProduitServices.AfficherListProduits()" + listP);
        return listP;
    }

    public List<Produit> findSucree() {
        List<Produit> listP = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Produits/listeProduitSucreeClient.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Produits.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Produit Produit = new Produit();
                            Produit.setIdProd(Integer.parseInt(obj.get("idProd").toString()));

                            Produit.setNomProd(obj.get("nomProd").toString());
                            Produit.setImageprod(obj.get("imageprod").toString());
                            Produit.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                            Produit.setPrixProd(Integer.parseInt(obj.get("prixProd").toString()));
                            Utilisateur user = new Utilisateur(Integer.parseInt(obj.get("idUser").toString()), obj.get("nom").toString());
                            Produit.setIdUser(user);
                            Produit.setQteAcheter(Integer.parseInt(obj.get("QteAcheter").toString()));
                            Categorie cat = new Categorie(Integer.parseInt(obj.get("idCat").toString()), (obj.get("nomCat").toString()));
                            Produit.setIdCat(cat);
                            listP.add(Produit);

                        }

                    }

                } catch (IOException ex) {
                    ex.printStackTrace();

                }
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        System.out.println("Services.ProduitServices.AfficherListProduits()" + listP);
        return listP;

    }

    public List<Produit> findSalee() {
        List<Produit> listP = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Produits/listeProduitSaleeClient.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Produits.get("info");
                    System.out.println("info : " + list);
                    if (list != null && !list.isEmpty()) {
                        for (Map<String, Object> obj : list) {
                            Produit Produit = new Produit();
                              Produit.setIdProd(Integer.parseInt(obj.get("idProd").toString()));

                            Produit.setNomProd(obj.get("nomProd").toString());
                            Produit.setImageprod(obj.get("imageprod").toString());
                            Produit.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                            Produit.setPrixProd(Integer.parseInt(obj.get("prixProd").toString()));
                            Utilisateur user = new Utilisateur(Integer.parseInt(obj.get("idUser").toString()), obj.get("nom").toString());
                            Produit.setIdUser(user);
                            Categorie cat = new Categorie(Integer.parseInt(obj.get("idCat").toString()), (obj.get("nomCat").toString()));
                            Produit.setIdCat(cat);
                            Produit.setQteAcheter(Integer.parseInt(obj.get("QteAcheter").toString()));
                            listP.add(Produit);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();

                }
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Services.ProduitServices.AfficherListProduits()" + listP);
        return listP;
    }

    public List<Produit> AfficherListProduitsPatisserie(int idUser) {
        List<Produit> listP = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo/ScriptPHP/Produits/listProduitPatisserie.php?uid=" + SessionUser.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override

            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Produits;

                    Produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println();
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Produits.get("info");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Produit Produit = new Produit();
                            Produit.setIdProd(Integer.parseInt(obj.get("idProd").toString()));

                            Produit.setNomProd(obj.get("nomProd").toString());
                            Produit.setImageprod(obj.get("imageprod").toString());
                            Produit.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                            Produit.setQteStockProd(Double.parseDouble(obj.get("qteStockProd").toString()));
                            Produit.setPrixProd(Integer.parseInt(obj.get("prixProd").toString()));
                            Categorie cat = new Categorie(Integer.parseInt(obj.get("idCat").toString()), obj.get("nomCat").toString());
                            Produit.setIdCat(cat);
                            Produit.setQteAcheter(Integer.parseInt(obj.get("QteAcheter").toString()));
                            listP.add(Produit);

                        }

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Services.ProduitServices." + listP);
        return listP;

    }
}
