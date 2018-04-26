/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entity.Recette;
import Services.RecetteServices;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.List;

/**
 *
 * @author escobar
 */
public class ClientPanier {
      private Resources theme;
    private Form current, f1;
    private Container c1;
    private UIBuilder uiBuilder;


    public ClientPanier() {
        theme = UIManager.initFirstTheme("/theme");
        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);
    }
    
    public void startClientPanier()
    {
        uiBuilder = new UIBuilder (); 
        c1 =uiBuilder.createContainer(theme, "ListTest");
        
        RecetteServices recetteService = new RecetteServices();
        List<Recette> listR = recetteService.findRecette(0);
        for(Recette r : listR)
        {
            Label lab = new Label(r.getNomRec());
            c1.add(lab);
        }
        f1=(Form)c1;
        f1.show();
    }
    
}
