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
public class BaseFormFormateur extends Form {

    public BaseFormFormateur() {
    }

    public BaseFormFormateur(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseFormFormateur(String title, Layout contentPaneLayout) {
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
        Image img = res.getImage("profile-background.jpg");
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

        tb.addMaterialCommandToSideMenu("Acceuil", FontImage.MATERIAL_UPDATE, e -> new NewsfeedFormFormateur(res).show());
        tb.addMaterialCommandToSideMenu("Profil", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
        tb.addMaterialCommandToSideMenu("Formation", FontImage.MATERIAL_BUSINESS_CENTER, e -> new Formateur.FormateurFormation(res).show());
        tb.addMaterialCommandToSideMenu("Ajout Promotion", FontImage.MATERIAL_MONEY_OFF, e -> {
            try {
                new Formateur.FormateurAjout(res).show();
            } catch (IOException ex) {
            }
        });
         tb.addMaterialCommandToSideMenu("Afficher Promotion", FontImage.MATERIAL_MONEY_OFF, e -> {
            try {
                new Formateur.Formateuraffiche(res).show();
            } catch (IOException ex) {
            }
        });
      //  tb.addMaterialCommandToSideMenu("Recette", FontImage.MATERIAL_RESTAURANT_MENU, e -> new Client.ClientRecette(res).show());        
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());

    }
}
