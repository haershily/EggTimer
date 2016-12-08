package com.example.harshil.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView timer;
    Button button;
    Boolean counterisActive=false;
    CountDownTimer countDownTimer;
    public void controlTimer(View view){
        if(counterisActive==false) {
            counterisActive = true;
            button.setText("Stop");
            seekBar.setEnabled(false);
            countDownTimer=new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    int sec = (int) l / 1000;
                    int minutes = (int) sec / 60;
                    int seconds = sec - minutes * 60;
                    String secondstring = "";
                    if (seconds < 10) {
                        secondstring = "0" + Integer.toString(seconds);
                    } else {
                        secondstring = Integer.toString(seconds);
                    }
                    timer.setText(Integer.toString(minutes) + ":" + secondstring);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mediaPlayer.start();
                    timer.setText("0:30");
                   seekBar.setProgress(30);
                    countDownTimer.cancel();
                    button.setText("Go!");
                    seekBar.setEnabled(true);
                    counterisActive=false;
                }
            }.start();
        }
        else{
          timer.setText("0:30");
            seekBar.setProgress(30);
            countDownTimer.cancel();
            button.setText("Go!");
            seekBar.setEnabled(true);
            counterisActive=false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      timer=(TextView)findViewById(R.id.textview);
        seekBar=(SeekBar)findViewById(R.id.seekbartimer);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        button=(Button)findViewById(R.id.button);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int minutes=(int)i/60;
                int seconds=i-minutes*60;
                String secondstring="";
                if(seconds<10){secondstring="0"+Integer.toString(seconds);}
                else{secondstring=Integer.toString(seconds);}
                timer.setText(Integer.toString(minutes)+":"+secondstring);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
