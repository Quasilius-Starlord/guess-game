package com.quasi.guessgame;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.quasi.guessgame.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ImageView guessNumberImage;
    private Button getStarted;

    Animation ImageAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

         binding = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());
         setSupportActionBar(binding.toolbar);

         guessNumberImage = findViewById(R.id.guessGameImage);
         getStarted = findViewById(R.id.getStarted);

        ImageAnimation = AnimationUtils.loadAnimation(this, R.anim.game_image_amimation);
        guessNumberImage.setAnimation(ImageAnimation);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GameModeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}