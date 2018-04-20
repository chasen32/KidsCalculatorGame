package com.example.sheld.calculator;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sheld.calculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
   private ActivityMainBinding binding;
   private final long HOW_LONG_WAIT = 500;//.5 seconds

   private double valueOne = Double.NaN;
   private double valueTwo;
   private int solution;
   private boolean didWin;

   private static final char ADDITION = '+';
   private static final char SUBTRACTION = '-';
   private static final char MULTIPLICATION = '*';
   private static final char DIVISION = '/';
   private char CURRENT_ACTION;
   DecimalFormat decimalFormat = new DecimalFormat("#.##########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        didWin = false;
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        problemToSolve();


        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ ".");
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ "0");
                playAnimalSound();
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ "1");
                playAnimalSound();
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ "2");
                playAnimalSound();
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ "3");
                playAnimalSound();
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ "4");
                playAnimalSound();
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ "5");
                playAnimalSound();
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ "6");
                playAnimalSound();
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ "7");
                playAnimalSound();
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ "8");
                playAnimalSound();
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText()+ "9");
                playAnimalSound();
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = ADDITION;
                binding.infoTextView.setText(decimalFormat.format(valueOne) + "+");
                binding.editText.setText(null);
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = SUBTRACTION;
                binding.infoTextView.setText(decimalFormat.format(valueOne) + "-");
                binding.editText.setText(null);
            }
        });

        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = MULTIPLICATION;
                binding.infoTextView.setText(decimalFormat.format(valueOne) + "*");
                binding.editText.setText(null);
            }
        });

        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = DIVISION;
                binding.infoTextView.setText(decimalFormat.format(valueOne) + "/");
                binding.editText.setText(null);
            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                binding.infoTextView.setText(binding.infoTextView.getText().toString() +
                        decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
               startWinActivity(view);
            }
        });
        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                binding.infoTextView.setText(null);
                binding.editText.setText(null);
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });


    }

    private void problemToSolve(){
        Random rand = new Random();
        solution = rand.nextInt(101);
        binding.solveProblem.setText("What two numbers with an operation that equals " + solution);
    }
    private void startWinActivity(View view){
        if(didWin) {
            //error is cause picture is too big, make one that is smaller
            Intent intent = new Intent(this, WinActivity.class);
            startActivity(intent);
        }
    }

    private void computeCalculation(){
        if(!Double.isNaN(valueOne)){
            valueTwo = Double.parseDouble((binding.editText.getText().toString()));
            binding.editText.setText(null);
            double testValue;
            String toastText = "Incorrect, please try again";

            if (CURRENT_ACTION == ADDITION){
                valueOne = this.valueOne + valueTwo;
                testValue = valueOne;
                if (testValue == solution){

                    Handler handlerWinAdd = new Handler();
                    handlerWinAdd.postDelayed(new Runnable() {
                        public void run() {
                            playWin();

                        }

                    }, HOW_LONG_WAIT);
                    //play victory sound
                    didWin = true;

                    //don't need these cause the page is refactored every time
                    //problemToSolve();
                }else{

                    Handler handlerlossAdd = new Handler();
                    handlerlossAdd.postDelayed(new Runnable() {
                        public void run() {
                            playLoss();
                        }
                    }, HOW_LONG_WAIT);
                    //play fail sound
                    Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
                }
            }
            else if (CURRENT_ACTION == SUBTRACTION){
                valueOne = this.valueOne - valueTwo;
                testValue = valueOne;
                if (testValue == solution){

                    Handler handlerWinSub = new Handler();
                    handlerWinSub.postDelayed(new Runnable() {
                        public void run() {
                            playWin();
                        }
                    }, HOW_LONG_WAIT);
                    //play victory sound
                    didWin = true;
                    //don't need these cause the page is refactored every time
                    //problemToSolve();
                }else{

                    Handler handlerlossSub = new Handler();
                    handlerlossSub.postDelayed(new Runnable() {
                        public void run() {
                            playLoss();
                        }
                    }, HOW_LONG_WAIT);
                    //play fail sound
                    Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
                }
            }
            else if (CURRENT_ACTION == MULTIPLICATION){
                valueOne = this.valueOne * valueTwo;
                testValue = valueOne;
                if (testValue == solution){

                    Handler handlerWinMul = new Handler();
                    handlerWinMul.postDelayed(new Runnable() {
                        public void run() {
                            playWin();
                        }
                    }, HOW_LONG_WAIT);
                    //play victory sound
                    didWin = true;

                    //don't need these cause the page is refactored every time
                   //problemToSolve();
                }else{
                    Handler handlerlossMul = new Handler();
                    handlerlossMul.postDelayed(new Runnable() {
                        public void run() {
                            playLoss();
                        }
                    }, HOW_LONG_WAIT);
                    //play fail sound
                    Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
                }
            }
            else if (CURRENT_ACTION == DIVISION){
                valueOne = this.valueOne / valueTwo;
                testValue = valueOne;
                if (testValue == solution){

                    Handler handlerWinDiv = new Handler();
                    handlerWinDiv.postDelayed(new Runnable() {
                        public void run() {
                            playWin();
                        }
                    }, HOW_LONG_WAIT);
                    //play victory sound
                    didWin = true;

                    //don't need these cause the page is refactored every time
                    //problemToSolve();
                }else{

                    Handler handlerlossDiv = new Handler();
                    handlerlossDiv.postDelayed(new Runnable() {
                        public void run() {
                            playLoss();
                        }
                    }, HOW_LONG_WAIT);
                    //play fail sound
                    Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            try{
                valueOne = Double.parseDouble(binding.editText.getText().toString());
            }catch (Exception e){}

        }

    }
    private void playWin(){
        MediaPlayer win = MediaPlayer.create(MainActivity.this, R.raw.winsound);
        win.start();


    }
    private void playLoss(){
        MediaPlayer loss = MediaPlayer.create(MainActivity.this, R.raw.incorectsound);
        loss.start();
    }

    private void playCaw(){
        MediaPlayer caw = MediaPlayer.create(MainActivity.this,R.raw.caw);
        caw.start();
    }
    private void playCow(){
        MediaPlayer cow = MediaPlayer.create(MainActivity.this,R.raw.cow);
        cow.start();
    }
    private void playDuck(){
        MediaPlayer duck = MediaPlayer.create(MainActivity.this,R.raw.duck);
        duck.start();
    }
    private void playPig(){
        MediaPlayer pig = MediaPlayer.create(MainActivity.this,R.raw.pig);
        pig.start();
    }
    private void playSheep(){
        MediaPlayer sheep = MediaPlayer.create(MainActivity.this,R.raw.sheep);
        sheep.start();
    }

    private void playAnimalSound(){
        Random rand = new Random();
        int randAnSound = rand.nextInt(11);

        switch (randAnSound){
            case 1:
                break;
            case 2:

                Handler handlerCAW = new Handler();
                handlerCAW.postDelayed(new Runnable() {
                    public void run() {
                        playCaw();
                    }
                }, HOW_LONG_WAIT);

                break;
            case 3:
                break;
            case 4:

                Handler handlerCow = new Handler();
                handlerCow.postDelayed(new Runnable() {
                    public void run() {
                        playCow();
                        // yourMethod();
                    }
                }, HOW_LONG_WAIT);

                break;
            case 5:
                break;
            case 6:

                Handler handlerDuck = new Handler();
                handlerDuck.postDelayed(new Runnable() {
                    public void run() {
                        playDuck();
                        // yourMethod();
                    }
                }, HOW_LONG_WAIT);
                break;
            case 7:
                break;
            case 8:

                Handler handlerPig = new Handler();
                handlerPig.postDelayed(new Runnable() {
                    public void run() {
                        playPig();
                        // yourMethod();
                    }
                }, HOW_LONG_WAIT);

                break;
            case 9:
                break;
            case 10:

                Handler handlerShe = new Handler();
                handlerShe.postDelayed(new Runnable() {
                    public void run() {
                        playSheep();
                        // yourMethod();
                    }
                }, HOW_LONG_WAIT);
                break;
        }

    }

}
