package cr.ac.ucr.ecci.examen2.Controller;

import cr.ac.ucr.ecci.examen2.AppDatabase;
import cr.ac.ucr.ecci.examen2.CreateActivity;
import cr.ac.ucr.ecci.examen2.DAO.Entities.User;
import cr.ac.ucr.ecci.examen2.MainActivity;
import cr.ac.ucr.ecci.examen2.Model.UserModel;

public class CreateController {
    UserModel model;
    CreateActivity activity;
    public CreateController(CreateActivity activity){
        this.activity = activity;
        this.model = new UserModel(activity.getApplicationContext(), this);
    }

    public void onDataSubmitted(User user){
        this.model.insertUser(user);
        this.activity.setResult(MainActivity.USER_ADDED);
        this.activity.finish();
    }

}
