package user.mobileappuni;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends Activity {

    private static String token;

    private static String username;

    private static synchronized void setToken(String t){
        token = t;
    }

    public static synchronized String getToken(){
        return token;
    }

    public static synchronized String getUsername() {
        return username;
    }

    private static synchronized void setUsername(String username) {
        LoginActivity.username = username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        final EditText username = (EditText) findViewById(R.id.login_user_textEdit);
        final EditText password = (EditText) findViewById(R.id.login_password_textEdit);
        Button login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    final YaasRestClient client = new YaasRestClient();
                    client.getToken(getApplicationContext(), new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {

                                DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
                                SQLiteDatabase database = databaseHelper.getReadableDatabase();
                                Cursor c = database.rawQuery("SELECT USERNAME FROM USER WHERE PASSWORD='"
                                        +password.getText().toString()+"' AND USERNAME='"+ username.getText().toString() +"'",null);
                                c.moveToFirst();
                                String u = c.getString(0);
                                c.close();
                                database.close();
                                databaseHelper.close();
                                if(u.equals(username.getText().toString())) {
                                    setUsername(username.getText().toString());
                                    setToken(response.getString("access_token"));
                                    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                                    startActivity(intent);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}
