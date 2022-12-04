package cr.ac.ucr.ecci.examen2.DAO.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "user_table")

public class User {
    @PrimaryKey(autoGenerate = false)
    public int id;
    @ColumnInfo(name= "user_firstname")
    public String firstName;
    @ColumnInfo(name= "user_lastname")
    public String lasttName;
    @ColumnInfo(name= "age")
    public int age;
    @ColumnInfo(name= "phone")
    public String phone;
    @ColumnInfo(name= "email")
    public String email;
    @ColumnInfo(name= "image")
    public String image;
}