package cr.ac.ucr.ecci.examen2.DAO.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import cr.ac.ucr.ecci.examen2.DAO.Entities.User;
@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);
    @Update
    void updateUser(User user);
    @Delete
    void deleteUser(User user);
    @Query("Select * from user_table where id like :id ")
    List<User> getUserById(String id);
    @Query("Select * from user_table order by user_firstname")
    List<User> getAllUsers();
}
