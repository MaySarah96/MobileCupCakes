/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entity.Educate;
import Entity.Formation;
import Entity.Session;
import Services.EducateServices;
import Services.FormationServices;
import Services.SessionServices;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author escobar
 */
public class SignleFormation extends BaseForm {

    Resources res;

    EncodedImage enc;
    Container cnt, cntForm;

    public SignleFormation(Resources res, Formation f) {
        super("session", BoxLayout.y());
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("session ");
        getContentPane().setScrollVisible(false);
        Container button = new Container();

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("allR.jpg"), spacer1, "  ", "", " ");
        //addTab(swipe, res.getImage("dog.jpg"), spacer2, "  ", "", "");

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
        cntForm = new Container(BoxLayout.y());
        RadioButton session = RadioButton.createToggle("Liste", barGroup);
        session.setUIID("SelectBar");
        session.addActionListener(e -> {
            cntForm.removeAll();
            cntForm = new Container(BoxLayout.y());
            add(cntForm);
            SessionServices serviceses = new SessionServices();
            FormationServices servifor = new FormationServices();
            System.out.println("id formation mzl loula " + f.getIdFor());
          
            //tester si la formation existe ou non dans la table session

            List<Session> listS = serviceses.findSessionByIdFor(f.getIdFor());
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
                ex.printStackTrace();
            }   
            String Notif = "";
            for (Session s : listS) {
                EducateServices es = new EducateServices();
                SessionServices ss = new SessionServices();
                List<Educate> listE = es.ListeIncriptionClient();
               
                for (Educate ed : listE) {
                    System.out.println("id session" + s.getIdSes());
                    System.out.println("non notifie" + es.SearchNonNotifie(s.getIdSes()));
                    if (!es.SearchNonNotifie(s.getIdSes()).isEmpty()) {
                        System.out.println("true");
                       
                        //notification
                        Notif += "\n vous avez une session le " + s.getDateDebSes();

                        es.ModifieEtatNotif(s.getIdSes());
                    }

                }
                System.out.println("west el boucle session");
                System.out.println(s.getNomSes() + "" + s.getDateDebSes() + "" + s.getDateFinSes());
                Image image = URLImage.createToStorage(enc, s.getImagesess(), "http://localhost:8088/final/web/public/uploads/brochures/Formateur/" + s.getImagesess());
                System.out.println("id session " + s.getIdSes());
                addButtonSession(s.getIdSes(),s.getPrixSes(), image, s.getNomSes(), s.getDateDebSes(), s.getDateFinSes());
            //el notification
            
            }
            if(!Notif.equals(""))
                Dialog.show("Warning", Notif, "OK", null);
           

        });
        RadioButton SessionEncours = RadioButton.createToggle("En Cours", barGroup);
        SessionEncours.setUIID("SelectBar");
        SessionEncours.addActionListener(e -> {

            cntForm.removeAll();
            cntForm = new Container(BoxLayout.y());
            add(cntForm);
            SessionServices sessionencours = new SessionServices();
            List<Session> listS = sessionencours.findSessionENCOURS();
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            for (Session s : listS) {
                Image image = URLImage.createToStorage(enc, s.getImagesess(), "http://localhost:8088/final/web/public/uploads/brochures/Formateur/" + s.getImagesess());

                addButtonSessionCoursFinie(image, s.getNomSes(), s.getDateDebSes(), s.getDateFinSes());
            }

        });
        RadioButton SessionFinies = RadioButton.createToggle("Finie", barGroup);
        SessionFinies.setUIID("SelectBar");
        SessionFinies.addActionListener(e -> {
            cntForm.removeAll();
            cntForm = new Container(BoxLayout.y());
            add(cntForm);
            SessionServices sessionencours = new SessionServices();
            List<Session> listS = sessionencours.findSessionFINIES();
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            for (Session s : listS) {
                Image image = URLImage.createToStorage(enc, s.getImagesess(), "http://localhost:8088/final/web/public/uploads/brochures/Formateur/" + s.getImagesess());
                addButtonSessionCoursFinie(image, s.getNomSes(), s.getDateDebSes(), s.getDateFinSes());

            }

        }
        );
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, session, SessionEncours, SessionFinies),
                FlowLayout.encloseBottom(arrow)
        ));

        session.setSelected(false);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(session, arrow);
        });
        bindButtonSelection(session, arrow);
        bindButtonSelection(SessionEncours, arrow);
        bindButtonSelection(SessionFinies, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        add(cntForm);
            SessionServices serviceses = new SessionServices();
            FormationServices servifor = new FormationServices();
            System.out.println("id formation mzl loula " + f.getIdFor());
          
            //tester si la formation existe ou non dans la table session

            List<Session> listS = serviceses.findSessionByIdFor(f.getIdFor());
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
                ex.printStackTrace();
            }   
            String Notif = "";
            for (Session s : listS) {
                EducateServices es = new EducateServices();
                SessionServices ss = new SessionServices();
                List<Educate> listE = es.ListeIncriptionClient();
               
                for (Educate ed : listE) {
                    System.out.println("id session" + s.getIdSes());
                    System.out.println("non notifie" + es.SearchNonNotifie(s.getIdSes()));
                    if (!es.SearchNonNotifie(s.getIdSes()).isEmpty()) {
                        System.out.println("true");
                       
                        //notification
                        Notif += "\n vous avez une session le " + s.getDateDebSes();

                        es.ModifieEtatNotif(s.getIdSes());
                    }

                }
                System.out.println("west el boucle session");
                System.out.println(s.getNomSes() + "" + s.getDateDebSes() + "" + s.getDateFinSes());
                Image image = URLImage.createToStorage(enc, s.getImagesess(), "http://localhost:8088/final/web/public/uploads/brochures/Formateur/" + s.getImagesess());
                System.out.println("id session " + s.getIdSes());
                addButtonSession(s.getIdSes(), s.getPrixSes(),image, s.getNomSes(), s.getDateDebSes(), s.getDateFinSes());
            //el notification
            
            }
            if(!Notif.equals(""))
                Dialog.show("Warning", Notif, "OK", null);
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

    private void addButtonSession(int idSes, Double prixs,Image img, String nomSes, String dateDeb, String dateFin) {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        cnt = BorderLayout.west(image);

        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(nomSes);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        TextArea datedeb = new TextArea(dateDeb);
        datedeb.setUIID("NewsTopLine");
        datedeb.setEditable(false);

        TextArea datefin = new TextArea(dateFin);
        datefin.setUIID("NewsTopLine");
        datefin.setEditable(false);
        
        TextArea prix_ses = new TextArea(String.valueOf(prixs));
        prix_ses.setUIID("NewsTopLine");
        prix_ses.setEditable(false);
        
        
        Button inscri = new Button("Inscription");
        inscri.getStyle().setFgColor(000000);
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta, datedeb, datefin,prix_ses
                ));

        cntForm.add(cnt).add(inscri);

        //inscription client
        inscri.addActionListener(e -> {
            System.out.println("fi west el inscription event");
            EducateServices es = new EducateServices();
            System.out.println("fi west el event id ses=" + idSes);
            //inscription client
            es.InscriptionClient(idSes);
        });

    }

    private void addButtonSessionCoursFinie(Image img, String nomSes, String dateDeb, String dateFin) {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        cnt = BorderLayout.west(image);

        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(nomSes);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        TextArea datedeb = new TextArea(dateDeb);
        datedeb.setUIID("NewsTopLine");
        datedeb.setEditable(false);

        TextArea datefin = new TextArea(dateFin);
        datefin.setUIID("NewsTopLine");
        datefin.setEditable(false);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta, datedeb, datefin
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
