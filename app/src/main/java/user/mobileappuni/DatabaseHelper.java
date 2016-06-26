package user.mobileappuni;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "OurSuperCoolDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS USER (USERNAME TEXT , PASSWORD TEXT , TOKEN TEXT , LAST TEXT)");
        db.execSQL("INSERT INTO USER(USERNAME , PASSWORD) VALUES('a', 'a')");
        db.execSQL("INSERT INTO USER(USERNAME , PASSWORD) VALUES('user@test.com', 'password')");
        db.execSQL("CREATE TABLE IF NOT EXISTS PLACES (NAME TEXT , PIC TEXT , DESCRIPTION TEXT, SPORT TEXT)");
        db.execSQL("INSERT INTO PLACES(NAME,PIC,DESCRIPTION,SPORT) VALUES('Pulse','boxing_gym1','Tuk ste moderni','Boxing'),('Boxi Boxi','boxing_gym2','Opravqme zubi','Boxing'),('Pluv Pluv','swimming1','Suprotivlenie pod nashata voda = 0','Swimming'),('Delfin4eto','swimming2','Pluvane v salata','Swimming')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
