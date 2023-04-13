package com.example.monquran;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class LecteurActivity extends AppCompatActivity {
    private Button playButton;
    private Button pauseButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecteur);

        playButton = findViewById(R.id.play);
        pauseButton = findViewById(R.id.pause);

        // Créer une instance de MediaPlayer avec l'URL du fichier audio
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("http://server7.mp3quran.net/shur/001.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Préparer la lecture asynchrone du fichier audio
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Prêt à jouer le fichier audio
            }
        });
        mediaPlayer.prepareAsync();

        // Ajouter un écouteur d'événements pour le bouton Play/Pause
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    // Le MediaPlayer est en train de jouer, mettre en pause la lecture
                    mediaPlayer.pause();
                    playButton.setText("Play");
                } else {
                    // Le MediaPlayer est en pause, reprendre la lecture
                    mediaPlayer.start();
                    playButton.setText("Pause");
                }
            }
        });
    }
}
