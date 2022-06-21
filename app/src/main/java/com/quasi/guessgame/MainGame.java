package com.quasi.guessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainGame extends AppCompatActivity {
    private TextView lastGuess, hint;
    private EditText guess;
    private Button submit;

    private boolean textVisibility;

    private int difficulty;

    private Random r;
    private int number;

    private int[] guesses;
    private int attempts;
    private final int MAXIMUMATTEMPTS=10;

    private void gameWon(int a){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainGame.this);
        builder.setTitle("You Won guessing game");
        builder.setMessage("You took "+a+" attempts!");
        builder.setCancelable(false);
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(MainGame.this, GameModeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        builder.create().show();
    };

    private void gameLost(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainGame.this);
        builder.setTitle("You Lost guessing game");
        builder.setMessage("You ran out of guesses!");
        builder.setCancelable(false);
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(MainGame.this, GameModeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        builder.create().show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        lastGuess=findViewById(R.id.lastGuess);
        hint=findViewById(R.id.hint);

        guess=findViewById(R.id.guess);
        submit=findViewById(R.id.submit);

        this.textVisibility=false;
        difficulty=getIntent().getIntExtra("difficulty", 3);

        guesses=new int[MAXIMUMATTEMPTS];
        attempts=0;


        r=new Random();
        number=r.nextInt((int) Math.pow(10, difficulty)+1);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if no input was given
                if(guess.getText().length()==0)
                    return;

                //maximum attempt reached
                if(attempts==MAXIMUMATTEMPTS)
                    return;
                attempts++;

                if(Integer.parseInt(guess.getText().toString()) == number){
                    //correct guess
                    lastGuess.setText("You Won!");
                    hint.setText("Attempts left "+(MAXIMUMATTEMPTS-attempts));
                    gameWon(attempts);
                }else{
                    //incorrect guess
                    if(attempts<MAXIMUMATTEMPTS){
                        //attempts yet remaining
                        guesses[attempts-1]=Integer.parseInt(guess.getText().toString());
                    }else{
                        //no more attempt left
                        lastGuess.setText("You ran out of guesses!");
                        hint.setText("Attempts left "+(MAXIMUMATTEMPTS-attempts));

                        gameLost();
                        return;
                    }
                    if(!textVisibility){
                        lastGuess.setVisibility(View.VISIBLE);
                        hint.setVisibility(View.VISIBLE);
                        textVisibility=true;
                    }
                    lastGuess.setText("Your last guess "+guess.getText().toString());
                    guess.setText("");

                    //hint manipulation
                    if(guesses[attempts-1]>number){
                        hint.setText("Decrease your guess\n"+"attempt left "+(MAXIMUMATTEMPTS-attempts));
                    }else{
                        hint.setText("Increase your guess\n"+"attempt left "+(MAXIMUMATTEMPTS-attempts));
                    }
                    Toast.makeText(getApplicationContext(),Integer.toString(number), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}