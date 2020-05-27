package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerseekbar;
    TextView timerTextView;
    Button button;
    Boolean counterIsActive= false;
     CountDownTimer countDownTimer;
     public void resetTimer(){
         timerTextView.setText("0:30");
         timerseekbar.setProgress(30);
         countDownTimer.cancel();
         button.setText("GO");
         timerseekbar.setEnabled(true);
         counterIsActive=false;
     }

    public void updateTimer(int secLeft){
        int minutes= (int)secLeft/60;
        int secs= secLeft - minutes*60;

        String secString= Integer.toString(secs);
        if(secs<=9){
            secString= "0"+secString;

        }
        timerTextView.setText(Integer.toString(minutes)+":"+secString);

    }
    public void controlTimer(View view){
        if(counterIsActive==false) {
            counterIsActive = true;
            timerseekbar.setEnabled(false);
            button.setText("Stop");



            countDownTimer= new CountDownTimer(timerseekbar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                   resetTimer();
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    player.start();


                }
            }.start();
        }
        else{

            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= findViewById(R.id.button);
       timerseekbar= findViewById(R.id.seekBar);
        timerTextView= findViewById(R.id.timerTextView);
        timerseekbar.setMax(600);
        timerseekbar.setProgress(30);
        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

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
