package com.enigma.skrelan.tictactoe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0 ; // 0 => yellow 1=> red
    int gameState[] = {2,2,2,2,2,2,2,2,2}; //2=> not played
    int winningPos[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsActive = true;
    int reds = 4 , yellows = 4;
    int helpON = 0; //flag for the how to play menu

    public void dropIn ( View view ){
        //OM SAI RAM
        TextView redCoins = (TextView)findViewById(R.id.redCoins);
        TextView yellowCoins = (TextView)findViewById(R.id.yellowCoins);
        TextView player = (TextView)findViewById(R.id.player);

        ImageView counter  = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && gameIsActive && ( (reds >0 && activePlayer == 1) || (yellows >0  && activePlayer == 0) ) ){  //gameIsActive

            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
                yellows = yellows - 1;
                player.setText("Current Player: Red   ");
                player.setTextColor(0xFFFA0707);
                System.out.println("yellows:" + yellows);
            }
            else if(activePlayer == 1){

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
                reds = reds - 1;
                player.setText("Current Player: Yellow");
                player.setTextColor(0xFFFDD303);
                System.out.println("reds:"+ reds);
            }
            counter.animate().translationYBy(1000f).rotationBy(360f).setDuration(500);
         for (int[] winning : winningPos){

             if(gameState[winning[0]]==gameState[winning[1]] &&
                    gameState[winning[1]]==gameState[winning[2]] &&
                    gameState[winning[0]]!=2){

                System.out.println(gameState[winning[0]]);
                //Some has won
                gameIsActive = false;
                String winner = "Red";
                int color = 0xFFFF0101;
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                 if(gameState[winning[0]]==0){

                    winner = "Yellow";
                    color = 0xFFFEF503;
                }

                winnerMessage.setText(winner+" has won");

                LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                layout.setTranslationX(-1000f);
                layout.setVisibility(View.VISIBLE);
                layout.setBackgroundColor(color);
                layout.animate().translationXBy(1000f).rotationBy(360f).setDuration(1000);

                /*
                layout.setTranslationX(-1000f);
                layout.animate().alpha(1F).translationXBy(1000f).rotationBy(360).setDuration(600); //43 mins
                */
            }
             else{ //draw

                boolean gameIsOver = true;
                for (int counterState : gameState){

                    if ( counterState == 2 ) {
                        gameIsOver = false;
                    }

                }
                    if (gameIsOver){
                        //draws(view);


                        /*TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("Draw");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setTranslationX(-1000f);
                        layout.setVisibility(View.VISIBLE);
                        layout.setBackgroundColor(0xFFD0D0CD);
                        layout.animate().translationXBy(1000f).rotationBy(360f).setDuration(1000);*/


                }
            }

         }
        }
        else if (gameState[tappedCounter]== activePlayer && gameIsActive ){

            gameState[tappedCounter] = 2 ;

            GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
            //counter.animate().translationYBy(-1000f).setDuration(1000);
            ((ImageView) gridLayout.getChildAt(tappedCounter)).setImageDrawable(null); //resets image

            System.out.println((tappedCounter + 1) + " is reset ");

            if (activePlayer == 0) {
                activePlayer = 1;
                yellows = yellows + 1;
                System.out.println("yellows:"+yellows);
                player.setText("Current Player: Red   ");
                player.setTextColor(0xFFFA0707);
            }
            else {
                activePlayer = 0;
                reds = reds + 1;
                System.out.println("reds:"+reds);
                player.setText("Current Player: Yellow");
                player.setTextColor(0xFFFDD303);
            }

        }
        redCoins.setText("Red coins left: "+reds);
        yellowCoins.setText("Yellow coins left: " + yellows);
    }

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0 ;
        gameIsActive = true;
        reds = 4;
        yellows = 4;
        for (int i = 0 ; i < gameState.length ; i ++)
        {

            gameState[i] = 2 ;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0 ; i <gridLayout.getChildCount(); i ++ ){
            ((ImageView) gridLayout.getChildAt(i)).setImageDrawable(null); //resets image
        }
        TextView redCoins = (TextView)findViewById(R.id.redCoins);
        TextView yellowCoins = (TextView)findViewById(R.id.yellowCoins);
        TextView player = (TextView)findViewById(R.id.player);
        redCoins.setText("Red coins left: "+reds);
        yellowCoins.setText("Yellow coins left: "+yellows);
        player.setText("Current Player: Yellow");
        player.setTextColor(0xFFFDD303);
    }
    public void draws(View view){


    }
    public void howToPlay(View view){
        //int i =0;
        LinearLayout layout = (LinearLayout)findViewById(R.id.howToPlay2);
        if(helpON == 0) {
            //layout.setTranslationY(-1000f);
            layout.setVisibility(View.VISIBLE);
            layout.animate().translationYBy(2000f).setDuration(1000);
            helpON = 1;
            gameIsActive = false;
        }

        /*while (i<7) {

            //Toast.makeText(getApplicationContext(), "Welcome to Enigma: Tic Tac Toe ©2015 ! This game is inspired from the classic game of TIC-TAC-TOE but is a little different. Each player is given 4 coins and must connect 3 of them diagonally, vertically or horizontally to win. When all 4 coins of each player are down on the mystical table, each player will lift one of their coins on their turn from any one of the position. On lifting one coin, each player has the choice to lift another or place the lifted coin(s) in the positions that have opened (or were open before). For further information go to Youtube and search for How to play Enigma (mobile game). - Suryaa Kumara Relan", Toast.LENGTH_LONG).show();
            i++;
        }*/
    }
    public  void closeMenu(View view){
        helpON = 0;
        LinearLayout layout = (LinearLayout)findViewById(R.id.howToPlay2);
        layout.animate().translationYBy(-2000f).setDuration(1000);
        //layout.setVisibility(View.INVISIBLE);
        gameIsActive = true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        LinearLayout helplayout = (LinearLayout)findViewById(R.id.howToPlay2);
        helplayout.setTranslationY(-2000f);

        ImageView bg1 = (ImageView) findViewById(R.id.bg1);
        bg1.animate().rotation(2000000f).setDuration(3000000);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
