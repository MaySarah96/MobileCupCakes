/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formateur;

import Patisserie.*;
import Client.PromoSingle;
import Client.SessionPromoSingle;
import Entity.LinePromo;
import Entity.Linepromoses;
import Entity.Produit;
import Entity.Promotion;
import Entity.Session;
import Services.ProduitService;
import Services.PromotionService;
import Services.SessionPromoService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.uikit.cleanmodern.BaseFormFormateur;
import java.io.IOException;
import java.util.Date;

//import java.sql.Date;
import java.util.List;

/**
 *
 * @author escobar
 */
public class FormateurAjout extends BaseFormFormateur {

    Resources res;
    EncodedImage enc;
    Container cnt, cntForm;
    private Resources theme;

    public FormateurAjout(Resources res) throws IOException {

        super("Promotion", BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");

        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        enc = EncodedImage.create("/giphy.gif");
        getTitleArea().setUIID("Container");
        setTitle("Promotion");
        getContentPane().setScrollVisible(false);
        Container button = new Container();

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        Image img = theme.getImage("may.jpg");
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
//hne chnbdl kol chy 

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Promtion", barGroup);
        all.setUIID("SelectBar");
        cntForm = new Container(BoxLayout.y());
        all.addActionListener(e -> {
            cntForm.removeAll();
            cntForm = new Container(BoxLayout.y());
            add(cntForm);
            Linepromoses p = new Linepromoses();
            SessionPromoService promotionservice = new SessionPromoService();
            ComboBox cmp = new ComboBox();
            SessionPromoService sessionService = new SessionPromoService();
            List<Session> listsesUser = sessionService.findSessionByUser();
            cmp.setModel(new DefaultListModel(listsesUser));
            //combo y3abi le promo
            ComboBox cmp1 = new ComboBox();
            /*cmp.setUIID("DatePicker");
            cmp1.setUIID("DatePicker");*/
            List<Promotion> listPromotion = promotionservice.findPromo();
            cmp1.setModel(new DefaultListModel(listPromotion));

            Picker datePicker = new Picker();
            datePicker.setUIID("DatePicker");
            datePicker.setType(Display.PICKER_TYPE_DATE);
            // datePicker.setDate(new Date());
            Date date = new Date();

            Picker datePicker1 = new Picker();
            datePicker1.setUIID("DatePicker");
            datePicker1.setType(Display.PICKER_TYPE_DATE);
            //    datePicker1.setDate(new Date());

            Button Test = new Button("Ajouter");
            Test.addActionListener(l -> {
                Session ses = (Session) cmp.getSelectedItem();
                Promotion promo = (Promotion) cmp1.getSelectedItem();
                System.out.println(ses.getIdSes());
                System.out.println(promo.getTauxPromo());
                System.out.println(cmp.getSelectedItem());
                System.out.println(cmp1.getSelectedItem());
                System.out.println(datePicker.getDate().toString());
                System.out.println(datePicker1.getDate().toString());
                p.setIdSes(ses);
                p.setDateDeb(datePicker.getDate());
                p.setDateFin(datePicker1.getDate());
                p.setIdPromo(promo);
                int check = (int) (p.getDateDeb().getTime()-date.getTime());
                int check2= (int) (p.getDateFin().getTime()-p.getDateDeb().getTime()); 
                if (check<0||check2<0)
                { 
                 Dialog.show("Attention", "date non valide", "Ok", null);

                }
                else 
                {
                promotionservice.ajoutpromosession(p);
                System.out.println("ajout avec succée");
                System.out.println("prix prod = " + ses.getPrixSes()+ " taux = " + promo.getTauxPromo());
                Double pourcentage = (double) (ses.getPrixSes()- ((ses.getPrixSes() / 100) * promo.getTauxPromo()));
                ses.setNvPrixSes(pourcentage);
                promotionservice.calculepromoses(ses);
                System.out.println("calcule promo avec succee!");
                //sms(promo, prod);
                //mail
                System.out.println("nvvvv" + pourcentage);
                Message m = new Message("une nouvelle promotion est ajouté sur une nouvelle session "+p.getIdSes().getNomSes()+p.getIdSes().getImagesess()+"de taux de reduction "+promo.getTauxPromo()+"consultere notre application mobile pour plus d'info!!! merci ");
               
                Display.getInstance().sendMessage(new String[]{"hamdimatador9@gmail.com"}, "promotion", m);
                /*Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
                m.setMimeType(Message.MIME_HTML);
                
                // notice that we provide a plain text alternative as well in the send method
                boolean success = m.sendMessageViaCloudSync("Codename One", "destination@domain.com", "Name Of User", "Message Subject",
                "Check out Codename One at https://www.codenameone.com/");*/
                System.out.println("mail avec succee");
//sms
                }
                });
            cntForm.add(cmp).add(datePicker).add(datePicker1).add(cmp1).add(Test);

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
        
        cntForm = new Container(BoxLayout.y());
            add(cntForm);
            Linepromoses p = new Linepromoses();
            SessionPromoService promotionservice = new SessionPromoService();
            ComboBox cmp = new ComboBox();
            SessionPromoService sessionService = new SessionPromoService();
            List<Session> listsesUser = sessionService.findSessionByUser();
            cmp.setModel(new DefaultListModel(listsesUser));
            //combo y3abi le promo
            ComboBox cmp1 = new ComboBox();
            /*cmp.setUIID("DatePicker");
            cmp1.setUIID("DatePicker");*/
            List<Promotion> listPromotion = promotionservice.findPromo();
            cmp1.setModel(new DefaultListModel(listPromotion));

            Picker datePicker = new Picker();
            datePicker.setUIID("DatePicker");
            datePicker.setType(Display.PICKER_TYPE_DATE);
            // datePicker.setDate(new Date());
            Date date = new Date();

            Picker datePicker1 = new Picker();
            datePicker1.setUIID("DatePicker");
            datePicker1.setType(Display.PICKER_TYPE_DATE);
            //    datePicker1.setDate(new Date());

            Button Test = new Button("Ajouter");
            Test.addActionListener(l -> {
                Session ses = (Session) cmp.getSelectedItem();
                Promotion promo = (Promotion) cmp1.getSelectedItem();
                System.out.println(ses.getIdSes());
                System.out.println(promo.getTauxPromo());
                System.out.println(cmp.getSelectedItem());
                System.out.println(cmp1.getSelectedItem());
                System.out.println(datePicker.getDate().toString());
                System.out.println(datePicker1.getDate().toString());
                p.setIdSes(ses);
                p.setDateDeb(datePicker.getDate());
                p.setDateFin(datePicker1.getDate());
                p.setIdPromo(promo);
                int check = (int) (p.getDateDeb().getTime()-date.getTime());
                int check2= (int) (p.getDateFin().getTime()-p.getDateDeb().getTime()); 
                if (check<0||check2<0)
                { 
                 Dialog.show("Attention", "date non valide", "Ok", null);

                }
                else 
                {
                promotionservice.ajoutpromosession(p);
                System.out.println("ajout avec succée");
                System.out.println("prix prod = " + ses.getPrixSes()+ " taux = " + promo.getTauxPromo());
                Double pourcentage = (double) (ses.getPrixSes()- ((ses.getPrixSes() / 100) * promo.getTauxPromo()));
                ses.setNvPrixSes(pourcentage);
                promotionservice.calculepromoses(ses);
                System.out.println("calcule promo avec succee!");
                //sms(promo, prod);
                //mail
                System.out.println("nvvvv" + pourcentage);
                Message m = new Message("une nouvelle promotion est ajouté sur une nouvelle session "+p.getIdSes().getNomSes()+p.getIdSes().getImagesess()+"de taux de reduction "+promo.getTauxPromo()+"consultere notre application mobile pour plus d'info!!! merci ");
               
                Display.getInstance().sendMessage(new String[]{"hamdimatador9@gmail.com"}, "promotion", m);
                /*Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
                m.setMimeType(Message.MIME_HTML);
                
                // notice that we provide a plain text alternative as well in the send method
                boolean success = m.sendMessageViaCloudSync("Codename One", "destination@domain.com", "Name Of User", "Message Subject",
                "Check out Codename One at https://www.codenameone.com/");*/
                System.out.println("mail avec succee");
//sms
                }
                });
            cntForm.add(cmp).add(datePicker).add(datePicker1).add(cmp1).add(Test);


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

    private void addButton(Image img, String title, double d, LinePromo p) {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        TextArea tb = new TextArea("Prix :" + String.valueOf(d));
        tb.setUIID("NewsTopLine");
        tb.setEditable(false);
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta, tb
                ));
        cntForm.add(cnt);
        image.addActionListener(e -> {
            try {
                new PromoSingle(res, p).show();
                p.setViews(p.getViews() + 1);
            } catch (IOException ex) {
            }

        });

    }

    private void addButton2(Image img, String title, double d, Linepromoses pr) {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        TextArea tb = new TextArea("Prix :" + String.valueOf(d));
        tb.setUIID("NewsTopLine");
        tb.setEditable(false);
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta, tb
                ));
        cntForm.add(cnt);
        image.addActionListener(e -> {
            try {
                new SessionPromoSingle(res, pr).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);

            }

        });
    }

    //36JTM9FSGDw-b8CnDcZLi08EMrnn2TZBEwUUHofRww
    /*   public void sms(Promotion promo , Produit prod) {
    try {
   // Construct data
   String apiKey = "apikey=" + "36JTM9FSGDw-b8CnDcZLi08EMrnn2TZBEwUUHofRww";
   String message = "&message=" + "une nouvelle promotion de pourcentage"+ promo.getTauxPromo()+"est ajouter sur le produit"+prod.getNomProd();
   String sender = "&sender=" + "CupCakes";
   String numbers = "&numbers=" + "+21658176702";
    
   // Send data
   HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
   String data = apiKey + numbers + message + sender;
   conn.setDoOutput(true);
   conn.setRequestMethod("POST");
   conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
   conn.getOutputStream().write(data.getBytes("UTF-8"));
   final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
   final StringBuffer stringBuffer = new StringBuffer();
   String line;
   while ((line = rd.readLine()) != null) {
    //stringBuffer.append(line);
    JOptionPane.showMessageDialog(null, "message"+line);
   }
   rd.close();
    
   //return stringBuffer.toString();
  } catch (Exception e) {
   //System.out.println("Error SMS "+e);
    JOptionPane.showMessageDialog(null, e);
   //return "Error "+e;
    }
    }*/
}
