package user.mobileappuni;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "OurSuperCoolDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS USER (USERNAME TEXT , PASSWORD TEXT , TOKEN TEXT , LAST TEXT)");
        db.execSQL("INSERT INTO USER(USERNAME , PASSWORD) VALUE('a', 'a')");
        db.execSQL("INSERT INTO USER(USERNAME , PASSWORD) VALUE('user@test.com', 'password')");
        db.execSQL("CREATE TABLE IF NOT EXISTS PLACES (NAME TEXT , PIC TEXT , DESCRIPTION TEXT, SPORT TEXT)");
        db.execSQL("INSERT INTO PLACES(NAME,PIC,DESCRIPTION,SPORT) VALUES('Pulse','','Tuk ste moderni','Boxing'),('Boxi Boxi','','Opravqme zubi','Boxing'),('Pluv Pluv','','Suprotivlenie pod nashata voda = 0','Swimming'),('Delfin4eto','','Pluvane v salata','Swimming')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
