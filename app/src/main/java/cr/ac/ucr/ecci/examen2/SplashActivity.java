package cr.ac.ucr.ecci.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import cr.ac.ucr.ecci.examen2.Controller.SplashController;

public class SplashActivity extends AppCompatActivity {
    SplashController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.controller = new SplashController(this);
    }

    public void boot(){
        System.out.println("Exito!");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }

    public void onFailure(){
        Toast.makeText(this,"Algo salió mal", Toast.LENGTH_SHORT).show();
    }
}