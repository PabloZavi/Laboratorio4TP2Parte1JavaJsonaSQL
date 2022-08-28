package main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controlador.Controlador;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import modelo.Pais;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws SQLException, ParseException {

        for (int i = 1; i <= 300; i++) {

            try {
                //creamos una URL donde esta nuestro webservice
                URL url = new URL("https://restcountries.com/v2/callingcode/" + i);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //indicamos por que verbo HTML ejecutaremos la solicitud
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                //si la respuesta del servidor es distinta al codigo 200 lanzaremos una Exception
                //(El código 200 es que la página funciona)
                System.out.println(i + " El país no existe");
                //throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
                if (conn.getResponseCode() == 200) { //Si el servidor responde con 200 la página existe
                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                    //creamos un StringBuilder para almacenar la respuesta del web service
                    StringBuilder sb = new StringBuilder();
                    int cp;
                    while ((cp = br.read()) != -1) {
                        sb.append((char) cp);
                    }
                    //en la cadena output almacenamos toda la respuesta del servidor
                    String jsonString = sb.toString();

                    //El Json devuelto es un array aunque tenga sólo un objeto, entonces convertimos el
                    //Json a un array de Json
                    JSONArray myJson = new JSONArray(jsonString);
                    //Ahora sacamos el objeto Json del array (está en la posición 0) y lo 
                    //convertimos a un JSONObject
                    JSONObject object = myJson.getJSONObject(0);

//                //Pruebas de que el Json funciona accediendo a través de sus llaves
//                System.out.println("Código país: " + object.get("callingCodes"));
//                System.out.println("Nombre: " + object.get("name"));
//                System.out.println("Capital: " + object.get("capital"));
//                System.out.println("Region: " + object.get("region"));
//                System.out.println("Poblacion: " + object.get("population"));
//                System.out.println("Latitud: " + object.get("latlng"));
//                System.out.println("Longitud: " + object.get("latlng"));
                    //Convertimos al objeto Json a un String
                    String JsonToStringPais = object.toString();

                    //Pasamos a la clase país los atributos de determinadas llaves
                    //Tomar en cuenta que al crear el país, los atributos se tienen que llamar
                    //igual que las llaves del Json. 
                    Pais pais = new Gson().fromJson(JsonToStringPais, Pais.class);

//                //Probamos que podemos acceder a los atributos del país
//                //Tomar en cuenta que el Json me da los CallingCodes como un array,
//                //entonces accedo a la primera posición de ese array
//                System.out.println(pais.getCallingCodes().get(0));
//                System.out.println(pais.getName());
//                System.out.println(pais.getCapital());
//                System.out.println(pais.getRegion());
//                System.out.println(pais.getPopulation());
//                //La latitud y la longitud se guardan en un mismo array llamado latlng", entonces accedo
//                //a la latitud en su primera posición y a la longitud en su segunda posición.
//                System.out.println(pais.getLatlng().get(0));
//                System.out.println(pais.getLatlng().get(1));
                    Controlador contr = new Controlador();
                    if (contr.existePais(pais.getCallingCodes().get(0))) {
                        
                        contr.actualizarPais(pais.getCallingCodes().get(0), pais.getName(), pais.getCapital(),
                                pais.getRegion(), pais.getPopulation(), pais.getLatlng().get(0), pais.getLatlng().get(1));
                        System.out.println(i + " País actualizado");
                    } else {
                        if(pais.getCapital() != null){
                            contr.insertarPais(pais.getCallingCodes().get(0), pais.getName(), pais.getCapital(),
                                pais.getRegion(), pais.getPopulation(), pais.getLatlng().get(0), pais.getLatlng().get(1));
                            System.out.println(i + " País insertado");
                        }
                        
                    }

                    conn.disconnect();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
