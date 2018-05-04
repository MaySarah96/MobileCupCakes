/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Note;
import Entity.SessionUser;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aziz
 */
public class ThreadServices {
    
    private ConnectionRequest con;
    boolean result ;
    
    public void AjouterThread(String idRec)
    {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo1/ScriptPHP/Recettes/AjouterThread.php?rid="+idRec); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData(); 
                    String s = new String(data);  
                }
            });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public boolean findThread(String idRec)
    {
        result = true ;
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Demo1/ScriptPHP/Recettes/ThreadRecettes.php?rid="+idRec); 
        con.addResponseListener(l->{
            try {
                JSONParser j = new JSONParser();
                Map<String, Object> Notes = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(Notes);
                List<Map<String, Object>> list = (List<Map<String, Object>>) Notes.get("info");
                System.out.println("info : " + list);
                if( list!=null && !list.isEmpty())
                {
                    result = false;
                }
            } catch (IOException ex) {
            }
                    
                
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
}
