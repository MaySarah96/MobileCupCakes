
package com.codename1.uikit.cleanmodern;

import Entity.SessionUser;
import Entity.Utilisateur;
import Services.UtilisateurServices;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class WalkthruForm extends BaseForm {

    public WalkthruForm (Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        signIn.addActionListener(e->
        {
            if ((username.getText()=="") && (password.getText()==""))
                Dialog.show("Error!", "Saisie le nom d'utilisateur et mot de passe", "Ok", null);
            else
            {
                UtilisateurServices userService = new UtilisateurServices();
                Utilisateur user = userService.findUser(username.getText());
                if (user.getId() == null)
                {
                    username.setText("");
                    password.setText("");
                    Dialog.show("Error!", "Login ou mot de passe incorrect!", "Ok", null);
                }
                else
                {
                    String hashed =user.getPassword().substring(0, 2)+"a"+user.getPassword().substring(3);
                    if (BCrypt.checkpw(password.getText(),hashed)) 
                    {
                        SessionUser.setAddresse(user.getAddresse());
                        SessionUser.setEmail(user.getEmail());
                        SessionUser.setId(user.getId());
                        SessionUser.setNom(user.getNom());
                        SessionUser.setPrenom(user.getPrenom());
                        SessionUser.setPassword(user.getPassword());
                        SessionUser.setPhoneNumber(user.getPhoneNumber());
                        SessionUser.setRoles(user.getRoles());
                        SessionUser.setImageProfil(user.getImageProfil());
                        if (user.getRoles().equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"))
                        {
                            new NewsfeedForm(res).show();
                        }
                    }

                }
                
            }
        });
      
    }
    
}
