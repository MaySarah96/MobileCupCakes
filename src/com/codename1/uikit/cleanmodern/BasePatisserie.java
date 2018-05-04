package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BasePatisserie extends Form {

    public BasePatisserie() {
    }

    public BasePatisserie(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BasePatisserie(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profilbg.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        tb.addMaterialCommandToSideMenu("Acceuil", FontImage.MATERIAL_UPDATE, e -> new NewsFeedFormP(res).show());
        tb.addMaterialCommandToSideMenu("Profil", FontImage.MATERIAL_SETTINGS, e -> {
            new ProfilPatiiserie(res).show();
        });
        tb.addMaterialCommandToSideMenu("Produit", FontImage.MATERIAL_SHOP, e -> {
            try {
                new Patisserie.PatisserieProduit(res).show();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        });
        tb.addMaterialCommandToSideMenu("Commande", FontImage.MATERIAL_SHOPPING_CART, e -> {
            try {
                new Patisserie.PatisserieCommande(res).show();
            } catch (IOException ex) {
            }
        });
        tb.addMaterialCommandToSideMenu("Ajout Promotion", FontImage.MATERIAL_MONEY_OFF, e -> {
            try {
                new Patisserie.PatisserieAjout(res).show();
            } catch (IOException ex) {
            }
        });
         tb.addMaterialCommandToSideMenu("Afficher Promotion", FontImage.MATERIAL_MONEY_OFF, e -> {
            try {
                new Patisserie.patisserieaffiche(res).show();
            } catch (IOException ex) {
            }
        });
        tb.addMaterialCommandToSideMenu("Statistique", FontImage.MATERIAL_DASHBOARD, e -> {
            try {
                new Patisserie.StatVente(res).show();
            } catch (IOException ex) {
            }
        });       
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
        
    }
}
