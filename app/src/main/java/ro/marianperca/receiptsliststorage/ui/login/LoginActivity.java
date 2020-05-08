package ro.marianperca.receiptsliststorage.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ro.marianperca.receiptsliststorage.R;
import ro.marianperca.receiptsliststorage.ui.register.RegisterActivity;
import ro.marianperca.receiptsliststorage.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mPassword;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref = getSharedPreferences(getString(R.string.app_prefs_file), Context.MODE_PRIVATE);
        mPassword = findViewById(R.id.password);

        findViewById(R.id.btnLogin).setOnClickListener(this);

        boolean firstRun = sharedPref.getBoolean("first_run", true);

        if (firstRun) {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View view) {
        String storedPass = sharedPref.getString("password", "");

        if (storedPass.equals(mPassword.getText().toString())) {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainActivityIntent);
        } else {
            mPassword.setError("Invalid password");
        }
    }
}
