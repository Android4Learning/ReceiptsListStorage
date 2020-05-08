package ro.marianperca.receiptsliststorage.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ro.marianperca.receiptsliststorage.R;
import ro.marianperca.receiptsliststorage.ui.addreceipt.AddReceiptActivity;
import ro.marianperca.receiptsliststorage.ui.details.ReceiptDetailsActivity;
import ro.marianperca.receiptsliststorage.models.Receipt;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_RECEIPT = 1;

    ReceiptsListAdapter mAdapter;
    ReceiptViewModel mReceiptViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceiptViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.receipts_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ReceiptsListAdapter();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(new ReceiptsListAdapter.OnReceiptClickListener() {
            @Override
            public void onClick(Receipt receipt) {
                Log.d("###", receipt.toString());
                Intent i = new Intent(MainActivity.this, ReceiptDetailsActivity.class);
                i.putExtra("receipt", receipt);
                startActivity(i);
            }
        });

        FloatingActionButton fab = findViewById(R.id.add_receipt);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Intent i = new Intent(MainActivity.this, AddReceiptActivity.class);
                startActivityForResult(i, ADD_RECEIPT);
            }
        });

        mReceiptViewModel.getAllReceipts().observe(this, new Observer<List<Receipt>>() {
            @Override
            public void onChanged(List<Receipt> receipts) {
                mAdapter.setReceipts(receipts);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_RECEIPT && resultCode == RESULT_OK) {
            Receipt student = new Receipt(
                    new Date(data.getLongExtra("date", 0)),
                    data.getIntExtra("value", 0),
                    data.getStringExtra("store")
            );

            mReceiptViewModel.insert(student);
        }
    }
}
