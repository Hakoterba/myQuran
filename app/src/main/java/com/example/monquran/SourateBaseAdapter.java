package com.example.monquran;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class SourateBaseAdapter extends BaseAdapter {
    private ArrayList<Sourate> sourates;
    private Context context;
    private MediaPlayer mediaPlayer;
    private int currentPosition = -1;

    public SourateBaseAdapter(ArrayList<Sourate> sourates, Context context) {
        this.sourates = sourates;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sourates.size();
    }

    @Override
    public Object getItem(int position) {
        return sourates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_sourate, null);
        }
        TextView name = (TextView) view.findViewById(R.id.nomSourate);
        TextView numero = (TextView) view.findViewById(R.id.numeroSourate);
        Button button = (Button) view.findViewById(R.id.playSourate);
        name.setText(sourates.get(position).getName());
        numero.setText(sourates.get(position).getSuras());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = sourates.get(position).getLien();

                // Réinitialiser le texte de tous les boutons
                for (int i = 0; i < parent.getChildCount(); i++) {
                    View childView = parent.getChildAt(i);
                    Button childButton = childView.findViewById(R.id.playSourate);
                    childButton.setText("▶");
                }

                // Arrêter la lecture en cours si on clique sur le même bouton
                if (currentPosition == position && mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    button.setText("▶");
                } else {
                    // Arrêter la lecture en cours s'il y en a une
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    // Créer un nouvel objet MediaPlayer pour jouer l'audio
                    mediaPlayer = new MediaPlayer();

                    // Ajouter un écouteur pour libérer les ressources lors de la fin de l'audio
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            mediaPlayer = null;
                            button.setText("▶");
                        }
                    });

                    try {
                        Log.d("DEBUG", "using URL:" + url);
                        // Définir la source audio à jouer
                        mediaPlayer.setDataSource(url);
                        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .build());
                        // Préparer l'audio
                        mediaPlayer.prepare();

                        // Démarrer la lecture de l'audio
                        mediaPlayer.start();
                        currentPosition = position;
                        button.setText("⏹");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Changer le texte du bouton si la position actuelle correspond à la position de cet élément
        if (currentPosition == position && mediaPlayer != null && mediaPlayer.isPlaying()) {
            button.setText("⏹");
        } else {
            button.setText("▶");
        }

        return view;
    }
}

