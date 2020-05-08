package ro.marianperca.receiptsliststorage.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ro.marianperca.receiptsliststorage.models.Receipt;

@Dao
public interface ReceiptDao {
    @Insert
    void insert(Receipt receipt);

    @Query("select * from receipt")
    LiveData<List<Receipt>> fetch();
}
