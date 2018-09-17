package org.example.tictactoe;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

// we implement the onClickListener - so this means there
//will be an onClick method defined for ALL the views later
//in the onClick method
public class MainActivity extends AppCompatActivity implements OnClickListener {

    int turnCounter = 0;
    int[] states = new int[9];
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View table = findViewById(R.id.table); //table=gridlayout
        table.setOnClickListener(this);

        //we add clicklisteners, this, to all our fields
        //MainActivity implements OnClickListener
        View field = findViewById(R.id.field1);
        field.setOnClickListener(this);

        field = findViewById(R.id.field2);
        field.setOnClickListener(this);

        findViewById(R.id.field3).setOnClickListener(this);
        findViewById(R.id.field4).setOnClickListener(this);
        findViewById(R.id.field5).setOnClickListener(this);
        findViewById(R.id.field6).setOnClickListener(this);
        findViewById(R.id.field7).setOnClickListener(this);
        findViewById(R.id.field8).setOnClickListener(this);
        findViewById(R.id.field9).setOnClickListener(this);

        //TODO add click listeners like this for the rest of the imageviews

        //States: 0=blank, 1=X, 2=O
        resetStates();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Method from interface OnClickListener MainActivity implements
    //States: 0=blank, 1=X, 2=O
    @Override
    public void onClick(View view) {
        if (turnCounter >= 6) {
            String msg = "Number of turns is over 6.\nGame is over";
            if(toast != null){
                toast.cancel();
            }
            toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            toast.show();
        } else {
            ImageView fieldImageView = (ImageView) view;
            // TODO Here you need to get the ID of the view
            // being pressed and then if the view is pressed
            // you need to first put a "X", then next time
            // put a "O" and also make sure that you cannot
            // put a "O" or a "X" if there is already something.
            String idFull = getResources().getResourceName(view.getId());
            int pos = Integer.parseInt(idFull.substring(idFull.length()-1))-1; //Array is zero indexed
            if(states[pos]!=0){
                String msg = "This field is already taken, choose another";
                if(toast!=null){toast.cancel();}
                toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                toast.show();
            }else{
                boolean possibleWinner = turnCounter>4;
                if(turnCounter==0 || turnCounter%2==0){  //X's turn
                    fieldImageView.setImageResource(R.drawable.kryds);
                    states[pos]=1;
                    if(possibleWinner){
                        boolean won = didThisPlayerWin(1);
                        if(won){
                            congratulationsToast("Player X");
                            gameOver();
                        }
                    }
                }else{ //O's turn
                    fieldImageView.setImageResource(R.drawable.bolle);
                    states[pos]=2;
                    if(possibleWinner){
                        boolean won = didThisPlayerWin(2);
                        if(won){
                            congratulationsToast("Player O");
                            gameOver();
                        }
                    }
                }
                turnCounter++;
            }
        }
    }

    public boolean didThisPlayerWin(int player){
        if(states[0]==player&&states[1]==player&&states[2]==player){
            return true;
        }else if(states[3]==player && states[4]==player&&states[5]==player){
            return true;
        }else if(states[6]==player && states[7]==player&&states[8]==player){
            return true;
        }else if(states[0]==player && states[3]==player&&states[6]==player){
            return true;
        }else if(states[1]==player && states[4]==player&&states[7]==player){
            return true;
        }else if(states[2]==player && states[5]==player&&states[8]==player){
            return true;
        }else if(states[0]==player && states[4]==player&&states[8]==player){
            return  true;
        }else if(states[2]==player && states[4]==player&&states[6]==player){
            return true;
        }
        return false;
    }

    private void resetFields(){
        ImageView field = findViewById(R.id.field1);
        field.setImageResource(R.drawable.blank);
        field = findViewById(R.id.field2);
        field.setImageResource(R.drawable.blank);
        field = findViewById(R.id.field2);
        field.setImageResource(R.drawable.blank);
        field = findViewById(R.id.field3);
        field.setImageResource(R.drawable.blank);
        field = findViewById(R.id.field4);
        field.setImageResource(R.drawable.blank);
        field = findViewById(R.id.field5);
        field.setImageResource(R.drawable.blank);
        field = findViewById(R.id.field6);
        field.setImageResource(R.drawable.blank);
        field = findViewById(R.id.field7);
        field.setImageResource(R.drawable.blank);
        field = findViewById(R.id.field8);
        field.setImageResource(R.drawable.blank);
        field = findViewById(R.id.field9);
        field.setImageResource(R.drawable.blank);
    }

    private void resetStates(){
        for(int i=0;i<states.length;i++){
            states[i]=0;
        }
    }

    public void congratulationsToast(String player){
        if(toast!=null){
            toast.cancel();
        }
        String msg = "Congratulations " + player + " you won!!";
        toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast.show();
    }

    private void gameOver(){

    }

    public void onClickNewGame(View view) {
        turnCounter=0;
        resetStates();
        resetFields();
    }
}