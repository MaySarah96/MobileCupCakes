/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entity.LinePromo;
import Services.PromotionService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;




/**
 *
 * @author escobar
 */
public class PromoSingle extends BaseForm {

    Resources res;

    EncodedImage enc;
    Container cnt, cntForm;

    public PromoSingle(Resources res, LinePromo p) throws IOException {
        super("PromoSingle", BoxLayout.y());
        PromotionService promoService = new PromotionService();
        Label countViews = new Label(String.valueOf(promoService.countView(p)));
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Detail");
        enc = EncodedImage.create("/giphy.gif");
        getContentPane().setScrollVisible(false);
        SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
        Container button = new Container();
        System.out.println("Client.ProduitSingle.<init>()" + p);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        Image image = URLImage.createToStorage(enc, p.getIdProd().getImageprod(), "http://localhost/final/web/public/uploads/brochures/Produit/" + p.getIdProd().getImageprod());
        addTab(swipe, image, spacer1, "  ", "", " ");

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
        RadioButton all = RadioButton.createToggle(p.getIdProd().getNomProd(), barGroup);
        all.setUIID("SelectBar");
        cntForm = new Container(BoxLayout.y());
        all.addActionListener(e -> {
            
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
        cntForm.removeAll();
            cntForm = new Container(BoxLayout.y());
            add(cntForm);
            /* Date date = format.parse(p.getDateFin().toString());*/
            Image image2 = URLImage.createToStorage(enc, p.getIdProd().getImageprod(), "http://localhost/final/web/public/uploads/brochures/Produit/" + p.getIdProd().getImageprod());
            addButton(image2, p.getIdProd().getQteStockProd(), p.getIdProd().getPrixProd(), p.getIdProd().getNvPrix(),p.getDateDeb(),p.getDateFin(), countViews);
        Button btnpartage= new Button("Partage fb");

        this.add(btnpartage);
            btnpartage.addActionListener((l) -> {
               // ConnectionRequest request = new ConnectionRequest();
               // request.setUrl("https://graph.facebook.com//v3.0/page-id/feed");
               //EAACEdEose0cBAETGZC4mn0cXtIDwYUDgUKOZBsvaimpMZC5XmeSB3nZA9DR1OtkHCLsCz23E6EYLfNI7tHoBFVTt5X4ZCRpLZAzFGh35m4Kh41WwgafEsjUuISw0VpInF8Hd0mBcHtN7U95peJPvMouQNgrLBZBnIZAtwcbvMBpDNnHAZCZAz1opkqjqvETTowRc8zh3e1oWdBQwZDZD
                String token = "EAAayMZCipBkEBAMA8ZCiZCgGdPpRZCvWcN5bXJSmdAs2CfApIf5HVZBZCLluhew88xog8s2ujYAGjBHiCrhAQ2ABTBehwVJm7nWxhPTpB65rICoaPJCF24XWaNubjMiZAyul07TnV2Sj1BMQZBNqKFXmkfSuUArIKFqiAmTIIxTNKzrCl95sZCFYTEdbK3KrhuNI1yDKnZCm0pJQZDZD";
                FacebookClient fb = new DefaultFacebookClient(token);
                InputStream fis = null;
                
                         try {
                             fis = Storage.getInstance().createInputStream(p.getIdProd().getImageprod());
                             System.out.println("limaaaaaaaaaaaaaaaaaaage"+fis);
                         } catch (IOException ex) {
                            
                         }
                FacebookType r = fb.publish("me/photos", FacebookType.class, BinaryAttachment.with("photo.jpg",fis),Parameter.with("message", "Cher client !!! une nouvelle promotion est ajouter sur le produit  "+p.getIdProd().getNomProd()+".. la quantité dans stock est "+ p.getIdProd().getQteStockProd()+" ... l'ancien prix est : "+ p.getIdProd().getPrixProd()+" et le nouveau prix est :  "+ p.getIdProd().getNvPrix()+"!!!!! cette promotion commance le : "+p.getDateDeb()+" et se termine le "+p.getDateFin()+"pour plus des informations consulter notre application mobile !! Merci "));
                System.out.println("fb.com" + r.getId());

            });
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        img = img.scaled(1200, 1200);
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

    private void addButton(Image img, Double d, Integer k, Integer l, Date m, Date n, Label o)  {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea tz = new TextArea("Stock :" + String.valueOf(d));
        tz.setUIID("NewsTopLine");
        tz.setEditable(false);
        TextArea tb = new TextArea("Prix :" + String.valueOf(k));
        tb.setUIID("NewsTopLine");
        tb.setEditable(false);
        TextArea tm = new TextArea("Nouveau Prix :" + String.valueOf(l));
        tm.setUIID("NewsTopLine");
        tm.setEditable(false);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        TextArea tn = new TextArea("Date Debut :" + formatter.format(m));
        tn.setUIID("NewsTopLine");
        tn.setEditable(false);
        TextArea tp = new TextArea("Date fin :" + formatter.format(n));
        tp.setUIID("NewsTopLine");
        tp.setEditable(false);
        /*TextArea to = new TextArea("vues :" + String.valueOf(o));
        to.setUIID("NewsTopLine");
        to.setEditable(false);*/
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        tb, tm, tn, tp, tz, o
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
