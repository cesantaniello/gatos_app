/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platzi.gatos_app;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author carlos
 */
public class GatosService {
   public static void verGatos() throws IOException{
       //Vamos a trar los datos de la API
       OkHttpClient client = new OkHttpClient();
       
       Request request = new Request.Builder()
        .url("https://api.thecatapi.com/v1/images/search")
        .method("GET", null)
        .build();
       
       Response response = client.newCall(request).execute();
       
       String elJson = response.body().string();
       
       //Corta los corchetes de elJson
       elJson = elJson.substring(1, elJson.length());       
       elJson = elJson.substring(0, elJson.length()-1);

       //Crea un objeto de la clase Gson
       Gson gson = new Gson();
       Gatos gatos = gson.fromJson(elJson, Gatos.class);
       
       //Redimensionar en caso de necesitar
       Image image = null;
       try{
           URL url = new URL(gatos.getURL());
           image = ImageIO.read(url);
           
           ImageIcon fondoGato = new ImageIcon(image);
           
           if(fondoGato.getIconWidth() > 800){
               //redimensionamos
               Image fondo = fondoGato.getImage();
               Image modificada = 
                fondogetScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
               fondoGato = new ImageIcon(modificada);
           }
       }catch(IOException e){
           System.out.println(e);
       }
   }

    private static Image fondogetScaledInstance(int i, int i0, int SCALE_SMOOTH) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
