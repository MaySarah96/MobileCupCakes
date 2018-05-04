/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entity.Commentaire;
import Entity.Note;
import Entity.Recette;
import Entity.SessionUser;
import Entity.Utilisateur;
import Services.CommentaireServices;
import Services.NoteServices;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.WebBrowser;
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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.uikit.cleanmodern.BaseForm;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author aziz
 */
public class SingleRecette extends BaseForm{
    Resources res;
    Label moyenne ;
    EncodedImage enc;
    Container cnt,cntForm,cntComment ;
    public Recette recette ;
    
    public SingleRecette (Resources res, Recette recette) throws IOException {
        super("SingleRecette", BoxLayout.y());
        this.recette = recette;
        this.res=res;
        start();
    }
    
    public void start() throws IOException{
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle(recette.getNomRec());
        getContentPane().setScrollVisible(false);
        Container button = new Container();
        System.out.println("Client.ProduitSingle.<init>()"+recette);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
        Tabs swipe = new Tabs();
        enc = EncodedImage.create("/giphy.gif");

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        System.out.println("Client.SingleRecette.<init>()"+recette.getImageRec());
        //Image image = URLImage.createImage("http://localhost/final/web/public/uploads/brochures/Recettes/"+recette.getImageRec());
        Image image = URLImage.createToStorage(enc, recette.getImageRec(), "http://localhost/final/web/public/uploads/brochures/Recettes/"+recette.getImageRec());
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
        add(LayeredLayout.encloseIn(swipe, radioContainer,button));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle(recette.getIdCatRec().getNomCatRec(), barGroup);
        all.setUIID("SelectBar");
        cntForm=new Container(BoxLayout.y());
        Container contMoy = new Container(BoxLayout.x());
        
        NoteServices noteService = new NoteServices();
        CommentaireServices commentService = new CommentaireServices();
        
        Double note = noteService.MoyenneNote(recette.getIdRec());
        moyenne = new Label("Note : "+note.toString()+"/5");
        
        Label nomRec = new Label(recette.getNomRec());
        contMoy.add(nomRec).add(moyenne);

        cntForm.add(contMoy);
        System.out.println(recette.getDescriptionRec());
        WebBrowser browser = new WebBrowser();
        //browser.setPreferredSize(new Dimension(500, 200));
        browser.setEnabled(false);
        browser.setTactileTouch(false);
        browser.setPage(recette.getDescriptionRec(), "");
        //browser.add(recette.getDescriptionRec());
        //browser.setPage("", "");
        Container cBrowser = new Container();
        //browser.setPreferredH(recette.getDescriptionRec().length());
        cBrowser.add(browser);
        cntForm.add(createStarRankSlider());

        cntForm.add(cBrowser);

        /*all.addActionListener(e -> {
                cntForm.removeAll();
                cntForm=new Container(BoxLayout.y());
                
                
                //addButton(res.getImage("news-item.jpg"), recette.getNomRec());
            

        });*/
        
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
        TextArea CommentText = new TextArea(2, 3);
        CommentText.setHint("Ajouter un commentaire");
        cntForm.add(CommentText);
        
        // commentaire
        Container cBtnComment = new Container(new FlowLayout(Component.RIGHT,Component.CENTER));
        Button btnComment = new Button("Commenter");
        btnComment.getStyle().setPadding(1, 1, 1, 1);
        Validator valid=new Validator(); 
        valid.addConstraint(CommentText, new LengthConstraint(2));
        valid.addSubmitButtons(btnComment);
        valid.setShowErrorMessageForFocusedComponent(true); 
        btnComment.addActionListener(l->{
            commentService.AjouterCommentaire(recette.getIdRec().toString(), CommentText.getText());
            removeAll();
            try {
                start();
            } catch (IOException ex) {
            }
        });
        
        cBtnComment.add(btnComment);
        cntForm.add(cBtnComment);
        List<Commentaire> comments = commentService.findComment(recette.getIdRec());
        AfficherComment(comments,"",commentService);
        
        
        add(cntForm);
    }
    
    
    private void AfficherComment(List<Commentaire> listCom ,String ancestors,CommentaireServices commentService){
        while(listCom.size() != 0)
        {
            Commentaire comment = listCom.get(0);
            if (comment.getAncestors().equals("") || comment.getAncestors().equals(null))
                ancestors = comment.getIdCmnt().toString();
            else               
                ancestors = comment.getAncestors() + "/" + comment.getIdCmnt().toString();
            Container contCom = new Container(BoxLayout.y());
            Label username = new Label(comment.getIdUser().getUsername());
            username.setUIID("LabelComment");
            Label created_at = new Label(comment.getCreatedAt());
            created_at.setUIID("LabelDate");
            Label body = new Label(comment.getBody());
            body.setUIID("LabelBody");
            
            Container contNameDate = new Container(BoxLayout.x());
            Container contDeleteUpdate = new Container(BoxLayout.x());
            Container contReply = new Container(BoxLayout.y());
            
            contNameDate.getStyle().setMargin(4, 0, 0, 0);
            contNameDate.add(username).add(created_at);
            Button Delete = new Button(res.getImage("Corbeille.png").scaled(15, 15));
            Button Update = new Button(res.getImage("refresh.png").scaled(15, 15));
            if(listCom.get(0).getIdUser().getId() == SessionUser.getId())
            {
                
                Delete.getStyle().setPadding(1, 1, 1, 1);

                
                Update.getStyle().setPadding(1, 1, 1, 1);
                //Label Update = new Label(res.getImage("corbeille.png"));

                Delete.addActionListener(l->{
                    commentService.DeleteComment(comment.getIdCmnt());
                    try {
                        removeAll();
                        start();
                    } catch (IOException ex) {
                    }
                });

                Update.addActionListener(l->{
                    Update.remove();
                    TextArea commentUpdate = new TextArea(comment.getBody());
                    Button Modifier = new Button("Modifier");
                    contReply.add(commentUpdate).add(Modifier);
                    Validator valid=new Validator(); 
                    valid.addConstraint(commentUpdate, new LengthConstraint(2));
                    valid.addSubmitButtons(Modifier);
                    valid.setShowErrorMessageForFocusedComponent(true); 
                    Modifier.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent l) {
                            CommentaireServices commentService = new CommentaireServices();
                            comment.setBody(commentUpdate.getText());
                            commentService.ModifierCommentaire(comment);
                            removeAll();
                            try {
                                start();
                            } catch (IOException ex) {
                            }
                        }
                    });

                });
            }
            contDeleteUpdate.add(body);
            if(listCom.get(0).getIdUser().getId() == SessionUser.getId())
                contDeleteUpdate.add(Update).add(Delete);
            contDeleteUpdate.getStyle().setMargin(0, 0, 0, 0);
            contCom.add(contNameDate).add(contDeleteUpdate);
            listCom.remove(0);
            contCom.setUIID("containerCommentaire");
            contCom.getStyle().setMarginLeft(comment.getAncestors().length() *3);
            
            
            Container contBtnReply = new Container(new FlowLayout(Component.RIGHT,Component.CENTER));
            Button Reply = new Button("Reply");
            Reply.getStyle().setPadding(1, 1, 1, 1);
            Reply.addActionListener(l->{
                Reply.remove();
                TextArea CommentReply = new TextArea(2, 3);
                CommentReply.setHint("Répondre un commentaire");
                
                Button Repondre = new Button("Répondre");
                Repondre.getStyle().setPadding(1, 1, 1, 1);
                String anc = comment.getIdCmnt().toString();
                if (!comment.getAncestors().equals(""))
                    anc = comment.getAncestors()+"/"+comment.getIdCmnt().toString();
                Container contR = new Container(new FlowLayout(Component.RIGHT,Component.CENTER));
                contR.add(Repondre);
                AjouterReply(Repondre,anc , CommentReply);
                contReply.add(CommentReply);
                contReply.add(contR);
            });
            contBtnReply.add(Reply);
            contReply.add(contBtnReply);
            
            //contCom.getStyle().setBorder(Border.createLineBorder(1));
            cntForm.add(contCom);
            cntForm.add(contReply);
            List<Commentaire> lisComment = commentService.findReplyComment(ancestors);
            if(lisComment.size()!= 0){
                System.out.println("Client.SingleRecette.AfficherComment() d5al c bon lel reply");
                AfficherComment(lisComment, ancestors, commentService);}
        }
    }
    
    public void AjouterReply(Button btnReply,String ancestors,TextArea body)
    {
        CommentaireServices commentService = new CommentaireServices();
        Validator valid=new Validator(); 
        valid.addConstraint(body, new LengthConstraint(2));
        valid.addSubmitButtons(btnReply);
        valid.setShowErrorMessageForFocusedComponent(true); 
        btnReply.addActionListener(l->{
            System.out.println("body :" + body );
            commentService.AjouterReply(recette.getIdRec().toString(), body.getText(), ancestors);
            removeAll();
            try {
                start();
            } catch (IOException ex) {
            }
        });
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        img = img.scaled(1000, 1000);
        if (img.getHeight() < size) {
            System.out.println("Client.SingleRecette.addTab() 1");
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
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            System.out.println("Client.SingleRecette.addTab() 2");
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        overlay,
                        image,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void addButton(Image img, String title) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        cnt = BorderLayout.west(image);
              

        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta
                       
                        ) );
        cntForm.add(cnt);
      
        
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);

            }

        });
    }
    
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
    
    private Container createStarRankSlider() {
        Container cRate = new Container();
        cRate.setSize(new Dimension(500, 50));
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte)0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        NoteServices noteService = new NoteServices();
        Note note = noteService.rechercheNote(recette);
        if (note != null)
            starRank.setProgress(note.getNote().intValue());
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() *5 , fullStar.getHeight()-1));
        starRank.addActionListener(l->{
            int rate = starRank.getProgress();
            System.out.println("Client.SingleRecette.createStarRankSlider() " + rate );
            

            if (note == null )
            {
                noteService.AjouterNote(new Note(Double.parseDouble(String.valueOf(rate)),recette,new Utilisateur(SessionUser.getId())));
            }
            else
            {
                noteService.ModifierNote(new Note(Double.parseDouble(String.valueOf(rate)),recette,new Utilisateur(SessionUser.getId())));
            }
            Double notes = noteService.MoyenneNote(recette.getIdRec());
            moyenne.setText("Note : "+notes.toString()+"/5");
        });
        cRate.add(starRank);
        return cRate;
    }
    
}
