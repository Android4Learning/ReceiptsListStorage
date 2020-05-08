package ro.marianperca.receiptsliststorage.ui.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import ro.marianperca.receiptsliststorage.R;
import ro.marianperca.receiptsliststorage.models.Receipt;

public class ReceiptDetailsActivity extends AppCompatActivity {
    private SimpleDateFormat formatDate = new SimpleDateFormat("EEEE, MMM d", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Receipt receipt = (Receipt) getIntent().getExtras().getSerializable("receipt");

        if (receipt != null) {
            Log.d("###", "received receipt");
            Log.d("###", receipt.toString());

            ((TextView) findViewById(R.id.price)).setText("" + receipt.value);
            ((TextView) findViewById(R.id.date)).setText(formatDate.format(receipt.date));
            ((TextView) findViewById(R.id.store)).setText(receipt.store);
        }
    }
}
