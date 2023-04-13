package com.example.monquran;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServerApi {
    private static String URL_API = "https://raw.githubusercontent.com/mehdi-stark/Coran-Quran/master/quran.json";

    public static void getSourate(Context context, final IListenerApi listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList<Sourate> sourates= new ArrayList<Sourate>();
                            JSONArray sourateArray = new JSONArray(response);
                            for(int i=0; i< sourateArray.length(); i++){
                                JSONObject sourateObject = sourateArray.getJSONObject(i);
                                String name = sourateObject.getString("name");
                                sourates.add(new Sourate(name));
                            }
                            listener.receiveSourate(sourates);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("SERVERAPI", "error lors de la requete");
                    }
                });
        queue.add(stringRequest);
    }

}
