package ro.marianperca.receiptsliststorage.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ro.marianperca.receiptsliststorage.db.ReceiptRepository;
import ro.marianperca.receiptsliststorage.models.Receipt;

public class ReceiptViewModel extends AndroidViewModel {
    private ReceiptRepository receiptRepository;
    private LiveData<List<Receipt>> mReceiptsList;

    public ReceiptViewModel(@NonNull Application application) {
        super(application);

        receiptRepository = new ReceiptRepository(application);
        mReceiptsList = receiptRepository.fetch();
    }

    public LiveData<List<Receipt>> getAllReceipts() {
        return mReceiptsList;
    }

    public void insert(Receipt receipt) {
        receiptRepository.insert(receipt);
    }
}
