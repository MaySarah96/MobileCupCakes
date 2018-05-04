/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entity.Recette;
import Services.CommandeServices;
import Services.RecetteServices;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.List;
import Entity.Commande;
import Entity.SessionUser;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.uikit.cleanmodern.BaseForm;
import java.io.IOException;

/**
 *
 * @author escobar
 */
public class ClientCommande extends BaseForm {

    Resources res;

    EncodedImage enc;
    Container cnt, cntForm;

    public ClientCommande(Resources res) {
        super("Commandes", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        this.res = res;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Commandes");
        getContentPane().setScrollVisible(false);
        Container button = new Container();

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
        CommandeServices cs = new CommandeServices();
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Prete", barGroup);
        all.setUIID("SelectBar");
        cntForm = new Container(BoxLayout.y());
        List<Commande> Prete = cs.findCommandePrete(SessionUser.getId());
        all.addActionListener(e -> {
            if (Prete != null) {
                cntForm.removeAll();
                cntForm = new Container(BoxLayout.y());
                add(cntForm);
                for (Commande c : Prete) {
                    addButton(res.getImage("pdf.png"), c.getAddLiv(), c.getMontantCmd(), c);
                }
            } else {
                cntForm.removeAll();
                cntForm = new Container(BoxLayout.y());
                Dialog.show("Info", "pas de commande en Prete", "ok", null);
                add(cntForm);

            }
        });
        List<Commande> Livre = cs.findCommandeLivre(SessionUser.getId());
        RadioButton Formation = RadioButton.createToggle("Livrée", barGroup);
        Formation.setUIID("SelectBar");
        Formation.addActionListener(e -> {
            if (Livre != null) {
                cntForm.removeAll();
                cntForm = new Container(BoxLayout.y());
                add(cntForm);
                for (Commande c : Livre) {
                    addButton(res.getImage("pdf.png"), c.getAddLiv(), c.getMontantCmd(), c);
                }
            } else {
                cntForm.removeAll();
                cntForm = new Container(BoxLayout.y());
                Dialog.show("Info", "pas de commande Livree", "ok", null);
                add(cntForm);

            }

        });
        List<Commande> Cour = cs.findCommandeEnCour(SessionUser.getId());
        RadioButton Promotion = RadioButton.createToggle("En Cours", barGroup);
        Promotion.setUIID("SelectBar");
        Promotion.addActionListener(e -> {
            if (Cour != null) {
                cntForm.removeAll();
                cntForm = new Container(BoxLayout.y());
                add(cntForm);
                for (Commande c : Cour) {
                    addButton(res.getImage("cmdd.png"), c.getAddLiv(), c.getMontantCmd(), c);
                }
            } else {
                cntForm.removeAll();
                cntForm = new Container(BoxLayout.y());
                add(cntForm);
                Dialog.show("Info", "pas de commande en cour", "ok", null);
            }

        });

        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all, Formation, Promotion),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(Formation, arrow);
        bindButtonSelection(Promotion, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        if (Prete != null) {
            cntForm.removeAll();
            cntForm = new Container(BoxLayout.y());
            add(cntForm);
            for (Commande c : Prete) {
                addButton(res.getImage("pdf.png"), c.getAddLiv(), c.getMontantCmd(), c);
            }
        } else {
            cntForm.removeAll();
            cntForm = new Container(BoxLayout.y());
            Dialog.show("Info", "pas de commande en Prete", "ok", null);
            add(cntForm);

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

        swipe.addTab("", page1);
    }

    private void addButton(Image img, String title, Double d, Commande c) {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        cnt = BorderLayout.west(image);

        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        TextArea tb = new TextArea(d.toString());
        tb.setUIID("NewsTopLine");
        tb.setEditable(false);
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta, tb
                ));
        cntForm.add(cnt);
        image.addActionListener(e -> {
            try {
                new Client.DetailCmdClient(res, c).show();
                //clientTemplate.startClientCommande();
            } catch (IOException ex) {
            }
        });

    }

    private void addMessage(String title) {
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        cnt = BorderLayout.west(ta);
        cnt.setLeadComponent(ta);
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta
                ));
        cntForm.add(cnt);

    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);

            }

        });
    }
}
