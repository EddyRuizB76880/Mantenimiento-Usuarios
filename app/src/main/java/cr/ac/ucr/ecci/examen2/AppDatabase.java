package cr.ac.ucr.ecci.examen2;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import cr.ac.ucr.ecci.examen2.DAO.Entities.User;
import cr.ac.ucr.ecci.examen2.DAO.Interfaces.UserDAO;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract UserDAO userDAO();
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
//crea la instancia de la creación de la base de datos
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new
            RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                }
            };
    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void,
            Void> {
        PopulateDBAsyncTask(AppDatabase instance)
        {
            UserDAO userDAO = instance.userDAO();
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            return null;
        }
    }
}