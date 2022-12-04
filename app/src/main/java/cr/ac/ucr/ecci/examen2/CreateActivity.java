package cr.ac.ucr.ecci.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cr.ac.ucr.ecci.examen2.Controller.CreateController;
import cr.ac.ucr.ecci.examen2.DAO.Entities.User;

public class CreateActivity extends AppCompatActivity {

    CreateController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        this.controller = new CreateController(this);

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitUser();
            }
        });
    }

    private void submitUser(){
        User newUser = new User();
        //Retrieve UI elements that contain user input.
        EditText name = findViewById(R.id.name);
        EditText lastName = findViewById(R.id.last_name);
        EditText id = findViewById(R.id.id);
        EditText age = findViewById(R.id.age);
        EditText phone = findViewById(R.id.phone);
        EditText email = findViewById(R.id.email);
        EditText url = findViewById(R.id.url);

        //Extract data from UI elements and populate newUser's attributes with it
        newUser.firstName = name.getText().toString();
        newUser.lasttName = lastName.getText().toString();
        newUser.id = Integer.parseInt(id.getText().toString());
        newUser.age = Integer.parseInt(age.getText().toString());
        newUser.phone = phone.getText().toString();
        newUser.email = email.getText().toString();
        newUser.image = url.getText().toString();

        //Send data to controller
        this.controller.onDataSubmitted(newUser);
    }

    @Override
    public void onBackPressed(){
        this.setResult(MainActivity.OPERATION_CANCELED);
        finish();
    }
}