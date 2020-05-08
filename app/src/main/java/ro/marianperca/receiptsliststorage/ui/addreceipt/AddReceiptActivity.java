package ro.marianperca.receiptsliststorage.ui.addreceipt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ro.marianperca.receiptsliststorage.R;

public class AddReceiptActivity extends AppCompatActivity implements View.OnClickListener, DatePickerFragment.DatePickerFragmentListener {

    private SimpleDateFormat formatDate = new SimpleDateFormat("EEEE, MMM d", Locale.ENGLISH);

    EditText mDate;
    EditText mValue;
    EditText mStore;
    Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipt);

        mDate = findViewById(R.id.date);
        mValue = findViewById(R.id.value);
        mStore = findViewById(R.id.store);

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment dialogFragment = DatePickerFragment.newInstance(AddReceiptActivity.this);
                dialogFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        findViewById(R.id.btnAdd).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("date", selectedDate.getTime());
        replyIntent.putExtra("value", Integer.valueOf(mValue.getText().toString()));
        replyIntent.putExtra("store", mStore.getText().toString());
        setResult(RESULT_OK, replyIntent);

        finish();
    }

    @Override
    public void onDateSet(Date date) {
        selectedDate = date;
        mDate.setText(formatDate.format(selectedDate));
    }
}
