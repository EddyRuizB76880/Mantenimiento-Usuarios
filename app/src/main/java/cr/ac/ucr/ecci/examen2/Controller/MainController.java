package cr.ac.ucr.ecci.examen2.Controller;

import androidx.recyclerview.widget.ItemTouchHelper;

import java.util.List;

import cr.ac.ucr.ecci.examen2.DAO.Entities.User;
import cr.ac.ucr.ecci.examen2.MainActivity;
import cr.ac.ucr.ecci.examen2.Model.UserModel;

public class MainController {
    MainActivity activity;
    UserModel model;
    public MainController(MainActivity activity){
        this.activity = activity;
        this.model = new UserModel(activity.getApplicationContext(), this);
        this.model.refreshList(true);
    }

    public void onListLoaded(List<User> users){
        activity.onDataLoaded(users);
    }

    public void onListUpdated(List<User> users) { activity.onDatasetUpdated(users); }

    public void onUserInserted() { this.model.refreshList(false); }

    public void onEntrySwap(User user, int direction){
        if(direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT){
            this.model.deleteUser(user);
        }
    }
}
