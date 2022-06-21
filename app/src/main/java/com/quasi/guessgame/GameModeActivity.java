package com.quasi.guessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GameModeActivity extends AppCompatActivity {

    private Button startGame;
    private RadioGroup difficulty;

    private RadioButton easy, medium, hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        difficulty = findViewById(R.id.difficulty);
        startGame = findViewById(R.id.startGame);

        easy = findViewById(R.id.difficultyOne);
        medium = findViewById(R.id.difficultyTwo);
        hard = findViewById(R.id.difficultyThree);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!easy.isChecked() && !medium.isChecked() && !hard.isChecked()){
                    Toast.makeText(getApplicationContext(), "Select an Option", Toast.LENGTH_LONG).show();
                }else{
                    Intent i = new Intent(GameModeActivity.this, MainGame.class);

                    if(easy.isChecked()){
                        i.putExtra("difficulty", 1);
                    }else if(medium.isChecked()){
                        i.putExtra("difficulty", 2);
                    }else{
                        i.putExtra("difficulty", 3);
                    }

                    startActivity(i);
                    finish();
                }
            }
        });
    }
}