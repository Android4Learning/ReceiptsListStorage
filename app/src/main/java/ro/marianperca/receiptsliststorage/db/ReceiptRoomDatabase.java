package ro.marianperca.receiptsliststorage.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ro.marianperca.receiptsliststorage.models.Receipt;

@Database(
        entities = {Receipt.class},
        version = 1
)
@TypeConverters({Converters.class})
public abstract class ReceiptRoomDatabase extends RoomDatabase {
    private static ReceiptRoomDatabase INSTANCE;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static ReceiptRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ReceiptRoomDatabase.class,
                    "receipts.db").build();
        }

        return INSTANCE;
    }

    public abstract ReceiptDao receiptDao();

}
