/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entity.Produit;
import Entity.Recette;
import Services.PanierService;
import Services.ProduitService;
import Services.RecetteServices;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.uikit.cleanmodern.BaseForm;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author escobar
 */
public class ClientPanier extends BaseForm {

    Resources res;

    EncodedImage enc;
    Container cnt, cntForm;
    PanierService pan = PanierService.getInstance();

    public ClientPanier(Resources res) throws IOException {
        super("Panier", BoxLayout.y());
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Panier");
        getContentPane().setScrollVisible(false);
        Container button = new Container();
        cntForm = new Container(BoxLayout.y());

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("panier.jpg"), spacer1, "  ", "", " ");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        cnt = new Container();
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer, button));

        ButtonGroup barGroup = new ButtonGroup();
        enc = EncodedImage.create("/giphy.gif");
        RadioButton Sucre = RadioButton.createToggle("Votre Panier", barGroup);
        Sucre.setUIID("SelectBar");

        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, Sucre),
                FlowLayout.encloseBottom(arrow)
        ));

        Sucre.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(Sucre, arrow);
        });
        bindButtonSelection(Sucre, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        add(cntForm);
        Map<Produit, Integer> panier = pan.Afficher();

        for (Map.Entry<Produit, Integer> ps : panier.entrySet()) {
            Image img = URLImage.createToStorage(enc, ps.getKey().getImageprod(), "http://localhost/CupCakesF/web/public/uploads/brochures/Produit/" + ps.getKey().getImageprod());
            addButton(img, ps.getKey().getNomProd(), ps.getValue(), ps.getKey());
         
        }
        if (panier.isEmpty()) {
            Dialog.show("Vide", "Votre panier est vide ajouter des produit", "ok", null);
        } 
        else {
            Button validerP = new Button("Valider mon panier");
            validerP.addActionListener(p -> {
                try {
                    new ConfirmerPanierClient(res).show();
                } catch (IOException ex) {
                }
            });
            add(validerP);

        }

    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );
        likes.addPointerDraggedListener(e -> {

        });
        swipe.addTab("", page1);
    }

    private void addButton(Image img, String title, double d, Produit p) {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Image supp = res.getImage("delete.png");
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");

        //Container cnt2 = new Container(BoxLayout.x());
        Container c = new Container();
        cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea("Nom Produit" + title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        TextArea tb = new TextArea("Qte" + String.valueOf(d));
        tb.setUIID("NewsTopLine");
        tb.setEditable(false);
        Button suprimer = new Button(supp.fill(10, 10));
        suprimer.addActionListener(e -> {
            pan.supprimerArticle(p);
            System.out.println("tfasakh");
            cntForm.revalidate();

        });
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseX(
                        BoxLayout.encloseY(ta, tb)
                ));
        c.add(cnt).add(suprimer);
        cntForm.add(c);
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);

            }

        });
    }
}
