package com.example.monquran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SourateActivity extends AppCompatActivity implements IListenerApi, AdapterView.OnItemClickListener {

    private ListView listViewSourate;
    private ArrayList<Sourate> sourates;
    private SourateBaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_sourate);

        listViewSourate = findViewById(R.id.listSourate);
        listViewSourate.setOnItemClickListener(this);

        // Appel à l'API pour récupérer les noms des sourates
        ServerApi.getSourate(this, this);
    }

    // Cette méthode est appelée lorsque l'API a terminé de récupérer les noms des sourates
    @Override
    public void receiveSourate(ArrayList<Sourate> souratesFromApi) {
        sourates = new ArrayList<>();
        for (int i = 0; i < 114; i++) {
            Sourate sourate = souratesFromApi.get(i);
            if(i < 10){
                sourates.add(new Sourate(sourate.getName(), "http://server7.mp3quran.net/shur/00" + (i + 1) + ".mp3", "Shuraim", "0" + (i + 1)));
            } else if (i < 99) {
                sourates.add(new Sourate(sourate.getName(), "http://server7.mp3quran.net/shur/0" + (i + 1) + ".mp3", "Shuraim", "" + (i + 1)));
            }
            else {
                sourates.add(new Sourate(sourate.getName(), "http://server7.mp3quran.net/shur/" + (i + 1) + ".mp3", "Shuraim", "" + (i + 1)));
            }
        }
        adapter = new SourateBaseAdapter(sourates, this);
        listViewSourate.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Sourate sourate = (Sourate) adapterView.getItemAtPosition(position);
        Intent intent = new Intent(this, LecteurActivity.class);
        intent.putExtra("url", sourate.getLien());
        startActivity(intent);
    }
}
