package com.example.dell.connect3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    boolean gameIsActive=true;
    int counter1=0;
    int[] activePlay = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public boolean winningFun(int tag)
    {
        for(int i=0;i<8;i++)
        {
            if(activePlay[winningPositions[i][0]]==activePlay[tag]&&
                    activePlay[winningPositions[i][1]]==activePlay[tag]&&
                    activePlay[winningPositions[i][2]]==activePlay[tag])
            {
                return true;
            }
        }
        return false;
    }
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;


        int tag = Integer.parseInt(counter.getTag().toString());
        if (activePlay[tag] == 2&& gameIsActive) {
            activePlay[tag]=counter1%2;
            counter.setTranslationX(0f);

            if (counter1 % 2 == 0) {
                counter.setImageResource(R.drawable.zero);
            }
            else if (counter1 % 2 == 1) {
                counter.setImageResource(R.drawable.cross);
            }
            counter.animate().rotation(360).alpha(1f).setDuration(200);
            if(counter1>=4){
                if(winningFun(tag)){
                    String str;
                    if(counter1%2==0)
                        str="zeros";
                                else
                                    str="crosses";
                    Toast.makeText(this,str+" won!\nGame Over!",Toast.LENGTH_LONG).show();
                    gameIsActive=false;
                }
                else if(counter1==8)
                    Toast.makeText(this,"Match Draw",Toast.LENGTH_LONG).show();
            }

            counter1++;
        }



    }
    public void wantToPlayAgain(View view )
    {
        Intent intent=new Intent(this,NextActivity.class);
        startActivity(intent);

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
