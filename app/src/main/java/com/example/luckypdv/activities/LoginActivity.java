package com.example.luckypdv.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.luckypdv.R;
import com.example.luckypdv.persistence.DatabaseHelper;
import com.example.luckypdv.utilities.InputValidation;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilUsername, tilPassword;
    private TextInputEditText tietUsername, tietPassword;
    private MaterialButton btSignIn;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        tilUsername = findViewById(R.id.til_username);
        tilPassword = findViewById(R.id.til_password);
        tietUsername = findViewById(R.id.tiet_username);
        tietPassword = findViewById(R.id.tiet_password);
        btSignIn = findViewById(R.id.bt_sign_in);
    }

    private void initListeners() {
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyFromSQLite();
            }
        });
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);

    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(tietUsername, tilUsername, getString(R.string.error_empty))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(tietPassword, tilPassword, getString(R.string.error_empty))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(tietUsername, tilUsername, getString(R.string.error_email))) {
            return;
        }

        if (databaseHelper.checkUser(tietUsername.getText().toString().trim()
                , tietPassword.getText().toString().trim())) {
            Toast.makeText(this, "Credenciales correctas", Toast.LENGTH_SHORT).show();
            emptyInputEditText();

        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyInputEditText() {
        tietUsername.setText(null);
        tietPassword.setText(null);
    }
}
