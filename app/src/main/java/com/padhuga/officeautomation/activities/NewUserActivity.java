package com.padhuga.officeautomation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.padhuga.officeautomation.R;
import com.padhuga.officeautomation.tables.UserTable;

import org.parceler.Parcels;

/**
 * Activity for entering a word.
 */

public class NewUserActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText editUserFirstNameView;
    private EditText editUserLastNameView;
    private EditText editUserLoginNameView;
    private EditText editUserPasswordView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        editUserFirstNameView = findViewById(R.id.first_name);
        editUserLastNameView = findViewById(R.id.last_name);
        editUserLoginNameView = findViewById(R.id.login_name);
        editUserPasswordView = findViewById(R.id.password);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editUserFirstNameView.getText()) || TextUtils.isEmpty(editUserLastNameView.getText())
                        || TextUtils.isEmpty(editUserLoginNameView.getText()) || TextUtils.isEmpty(editUserPasswordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String firstName = editUserFirstNameView.getText().toString();
                    String lastName = editUserLastNameView.getText().toString();
                    String loginName = editUserLoginNameView.getText().toString();
                    String password = editUserPasswordView.getText().toString();
                    UserTable userTable = new UserTable(firstName, lastName, loginName, password);
                    replyIntent.putExtra(EXTRA_REPLY, Parcels.wrap(userTable));
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}

