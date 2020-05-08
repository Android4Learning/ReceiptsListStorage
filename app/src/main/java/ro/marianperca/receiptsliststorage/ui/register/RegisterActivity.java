package ro.marianperca.receiptsliststorage.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ro.marianperca.receiptsliststorage.R;
import ro.marianperca.receiptsliststorage.ui.main.MainActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mPassword;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences(getString(R.string.app_prefs_file), Context.MODE_PRIVATE);
        mPassword = findViewById(R.id.password);

        findViewById(R.id.btnSave).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", mPassword.getText().toString());
        editor.putBoolean("first_run", false);
        editor.apply();

        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivityIntent);
    }
}
