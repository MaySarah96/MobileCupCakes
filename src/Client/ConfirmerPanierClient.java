/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entity.Commande;
import Entity.Produit;
import Entity.SessionUser;
import Services.CommandeServices;
import Services.LineCmdService;
import Services.PanierService;
import com.codename1.components.FloatingHint;
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
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author escobar
 */
public class ConfirmerPanierClient extends BaseForm {

    Resources res;

    EncodedImage enc;
    Container cnt, cntForm;

    public ConfirmerPanierClient(Resources res) throws IOException {
        super("Commandes", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Commandes");
        getContentPane().setScrollVisible(false);
        Container button = new Container();

        super.addSideMenu(res);

        Tabs swipe = new Tabs();
        enc = EncodedImage.create("/giphy.gif");
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        Image img = res.getImage("cupcake.jpg");
        addTab(swipe, img, spacer1, "  ", "", " ");

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
        RadioButton all = RadioButton.createToggle("Confirmer Panier", barGroup);
        all.setUIID("SelectBar");
        cntForm = new Container(BoxLayout.y());
        all.addActionListener(e -> {
            cntForm.removeAll();
            cntForm = new Container(BoxLayout.y());
            add(cntForm);
            //Image img = URLImage.createToStorage(enc, p.getImageprod(), "http://localhost/CupCakesF/web/public/uploads/brochures/Produit/" + p.getImageprod());

            /*addButton(res.getImage("img9.png")
                    , p.getPrixProd(), p.getIdCat().getNomCat(), p.getIdUser().getNom(),p);*/
        });
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, all),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        TextField adresse = new TextField(SessionUser.getAddresse(), "Adresse de livraison", 20, TextField.ANY);
        Label Datelab = new Label("Date de Livraison");
        Picker dateLiv = new Picker();
        adresse.getStyle().setFgColor(0000);
        dateLiv.setUIID("TextboxLivraiseon");
        PanierService panierService = PanierService.getInstance();
        Container content = new Container(BoxLayout.y());
        Label montant = new Label(String.valueOf(panierService.calculerPanier()));
        montant.setUIID("LabelPanier");
        Button Commande = new Button("Valider");
        content.add(createLineSeparator()).add("Le Montant de votre Commande est").add(createLineSeparator()).add(montant).add(createLineSeparator()).add(new FloatingHint(adresse)).add(createLineSeparator()).add(Datelab).add(createLineSeparator()).add(dateLiv).add(createLineSeparator()).add(Commande);
        //content.setScrollableY(true);
        Commande.addActionListener(c -> {
            CommandeServices commandeService = new CommandeServices();
            Commande commande = new Commande();
            commande.setAddLiv(adresse.getText());
            commande.setDateLivCmd(dateLiv.getDate());
            commande.setMontantCmd(Double.valueOf(panierService.calculerPanier()));
            String id = commandeService.AjouterCommande(commande);
            System.out.println("id lekhra passer est " + id);
            Map<Produit, Integer> panier = panierService.Afficher();
            System.out.println(panier.size());
            for (Map.Entry<Produit, Integer> ps : panier.entrySet()) {
                System.out.println(ps);
                LineCmdService line = new LineCmdService();
                line.AjouterLineCommande(Integer.parseInt(id), ps.getValue(), ps.getKey().getIdProd());

            }
            panierService.closePanier();
            new Client.ClientCommande(res).show();
        });
        add(content);
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

        swipe.addTab("", page1);
    }

    private void addButton(Image img, int i, String c, String u, Produit p) {

        int height = Display.getInstance().convertToPixels(8f);
        int width = Display.getInstance().convertToPixels(10f);
        Button image = new Button("ajouter au panier", img.fill(width, height));
        image.setUIID("Label");
        cnt = BorderLayout.south(image);
        cnt.setLeadComponent(image);
        TextArea tc = new TextArea(String.valueOf(i));
        tc.setUIID("NewsTopLine");
        tc.setEditable(false);
        TextArea td = new TextArea(u);
        td.setEditable(false);
        td.setUIID("NewsTopLine");
        TextArea tb = new TextArea(c);
        tb.setEditable(false);
        tb.setUIID("NewsTopLine");
        TextArea tf = new TextArea("Patisserie");
        tf.setEditable(false);
        tf.setUIID("NewsTopLine");
        TextArea te = new TextArea("Prix");
        te.setEditable(false);
        te.setUIID("NewsTopLine");
        TextArea tp = new TextArea("Categorie");
        tp.setEditable(false);
        tp.setUIID("NewsTopLine");

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        BoxLayout.encloseX(te, tc), BoxLayout.encloseX(tp, tb), BoxLayout.encloseX(tf, td)
                ));
        cntForm.add(cnt);
        image.addActionListener(e -> {
            PanierService pan = PanierService.getInstance();
            pan.ajouterArticle(p, 1);
            System.out.println("ajouta");
            System.out.println(pan.Afficher());
        });
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);

            }

        });
    }
}
