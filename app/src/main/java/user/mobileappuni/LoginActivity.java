package user.mobileappuni;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
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
                                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put("USERNAME",username.getText().toString());
                                values.put("PASSWORD",password.getText().toString());
                                values.put("TOKEN",response.getString("access_token"));
                                values.put("LAST",new Date().toString());
                                database.insert("USER", null,values);
                                database.close();
                                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                                startActivity(intent);

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
