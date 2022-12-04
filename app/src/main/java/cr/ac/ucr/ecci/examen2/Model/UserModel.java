package cr.ac.ucr.ecci.examen2.Model;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import cr.ac.ucr.ecci.examen2.AppDatabase;
import cr.ac.ucr.ecci.examen2.Controller.CreateController;
import cr.ac.ucr.ecci.examen2.Controller.MainController;
import cr.ac.ucr.ecci.examen2.DAO.Entities.User;
import cr.ac.ucr.ecci.examen2.DAO.Interfaces.UserDAO;
import cr.ac.ucr.ecci.examen2.Controller.SplashController;
import cr.ac.ucr.ecci.examen2.MainActivity;

public class UserModel {
    AppDatabase db;
    UserDAO userDAO;
    SplashController splashController;
    MainController mainController;
    CreateController createController;
    public UserModel (Context context, SplashController splashController) {
        this.splashController = splashController;
        this.setup(context);
    }

    public UserModel (Context context, MainController mainController) {
        this.mainController = mainController;
        setup(context);
    }

    public UserModel (Context context, CreateController createController) {
        this.createController = createController;
        setup(context);
    }

    private void setup(Context context){
        this.db = db.getInstance(context);
        this.userDAO = this.db.userDAO();
    }

    public void refreshList(boolean firstLoad){
        try {
            List<User> users = new AsyncRefreshList(userDAO).execute().get();
            if(firstLoad == true){
                mainController.onListLoaded(users);
            }else{
                mainController.onListUpdated(users);
            }
        } catch (Exception e) {
            Log.e("DATABASE READ:", e.getMessage());
        }
    }

    public void insertUser(User user){
        try {
            new AsyncInsert(userDAO).execute(user);
        } catch (Exception e) {
            Log.e("DATABASE READ:", e.getMessage());
        }
    }

    public void updateUser(User user){
        try {
            new AsyncUpdate(userDAO).execute(user);
        } catch (Exception e) {
            Log.e("DATABASE READ:", e.getMessage());
        }

    }

    public void deleteUser(User user){
        try {
            new AsyncDelete(userDAO).execute(user);
        } catch (Exception e) {
            Log.e("DATABASE READ:", e.getMessage());
        }
    }

    private class AsyncRefreshList extends AsyncTask<Void, Void,
            List<User>>
    {
        private UserDAO userDao;
        public AsyncRefreshList(UserDAO userdao)
        {
            userDao = userdao;
        }
        @Override
        protected List<User> doInBackground(Void... voids) {
            return userDao.getAllUsers();

        }
    }
    private class AsyncInsert extends AsyncTask<User, Void, Void>
    {
        private UserDAO userDao;
        public AsyncInsert(UserDAO userDao)
        {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            System.out.println(users[0].email);
            this.userDao.insertUser(users[0]);
            //splashController.onUserSubmitted();
            return null;
        }
    }

    private class AsyncDelete extends AsyncTask<User, Void, Void>
    {
        private UserDAO userDao;
        public AsyncDelete(UserDAO userDao)
        {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            this.userDao.deleteUser(users[0]);
            return null;
        }
    }

    private class AsyncUpdate extends AsyncTask<User, Void, Void>
    {
        private UserDAO userDao;
        public AsyncUpdate(UserDAO userDao)
        {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users[0]);
            return null;
        }
    }
}
