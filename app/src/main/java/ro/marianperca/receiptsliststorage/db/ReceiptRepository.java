package ro.marianperca.receiptsliststorage.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ro.marianperca.receiptsliststorage.models.Receipt;

public class ReceiptRepository {
    private ReceiptDao mReceiptDao;

    public ReceiptRepository(Application application) {
        ReceiptRoomDatabase db = ReceiptRoomDatabase.getDatabase(application);
        mReceiptDao = db.receiptDao();
    }

    public LiveData<List<Receipt>> fetch() {
        return mReceiptDao.fetch();
    }

    public void insert(final Receipt receipt) {
        ReceiptRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mReceiptDao.insert(receipt);
            }
        });
    }
}
