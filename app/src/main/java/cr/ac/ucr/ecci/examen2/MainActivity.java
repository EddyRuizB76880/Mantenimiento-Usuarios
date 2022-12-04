package cr.ac.ucr.ecci.examen2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cr.ac.ucr.ecci.examen2.Adapter.UserAdapter;
import cr.ac.ucr.ecci.examen2.Controller.MainController;
import cr.ac.ucr.ecci.examen2.DAO.Entities.User;

public class MainActivity extends AppCompatActivity {
    MainController controller;
    FloatingActionButton mainFab, addUserFab;
    TextView addUserTV;
    RecyclerView rView;
    Boolean enableFabs;
    public final static int ADD_USER = 1;
    public final static int USER_ADDED = 2;
    public final static int OPERATION_CANCELED = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new MainController(this);

    }

    private void getViews(){
        mainFab = findViewById(R.id.main_fab);
        addUserFab = findViewById(R.id.add_user_fab);
        addUserTV = findViewById(R.id.add_user_tv);
        addUserFab.setVisibility(View.GONE);
        addUserTV.setVisibility(View.GONE);
        rView = findViewById(R.id.user_list);
        enableFabs = false;


        mainFab.setOnClickListener(view ->
        {
            if (!enableFabs)
            {
                addUserFab.show();
                addUserTV.setVisibility(View.VISIBLE);
            }else{
                addUserFab.hide();
                addUserTV.setVisibility(View.GONE);
            }
            enableFabs = !enableFabs;
        });

        addUserFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCreateUser();
            }
        });
    }

    private void launchCreateUser(){
        Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
        startActivityForResult(intent, ADD_USER);
    }

    @Override
    public void onBackPressed(){
        this.finishAffinity();
    }

    public void onDataLoaded(List<User> users){
        getViews();
        UserAdapter adapter = new UserAdapter(this.getLayoutInflater(), users);
        LinearLayoutManager lManager = new LinearLayoutManager(this);
        DividerItemDecoration divider = new DividerItemDecoration(this, lManager.getOrientation());
        rView.setLayoutManager(lManager);
        rView.addItemDecoration(divider);
        rView.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull
                    RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int
                    direction) {
                controller.onEntrySwap(adapter.getUser(viewHolder.getAdapterPosition()), direction);
                adapter.removeUserFromView(viewHolder.getAdapterPosition());
            }
            }).attachToRecyclerView(rView);
    }

    public void onDatasetUpdated(List<User> users){
        UserAdapter adapter = (UserAdapter)rView.getAdapter();
        adapter.setUsers(users);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_USER){
            if(resultCode == USER_ADDED){
                this.controller.onUserInserted();
                displayMessage("Usuario añadido exitosamente");
            }else if(resultCode == OPERATION_CANCELED){
                displayMessage("Operación cancelada");
            }
        }
    }

    private void displayMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}