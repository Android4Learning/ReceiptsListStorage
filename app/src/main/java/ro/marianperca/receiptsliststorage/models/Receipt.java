package ro.marianperca.receiptsliststorage.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Entity
public class Receipt implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public Date date;
    public double value;
    public String store;

    public Receipt() {
    }

    public Receipt(Date date, double value, String store) {
        this.date = date;
        this.value = value;
        this.store = store;
    }

    public static Receipt generateFakeReceipt() {
        ArrayList<String> stores = new ArrayList<String>() {
            {
                add("Carrefour");
                add("Kaufland");
                add("Penny");
                add("La 2 pasi");
            }
        };

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);

        double receiptValue = ThreadLocalRandom.current().nextDouble(5, 101);
        int storeIndex = ThreadLocalRandom.current().nextInt(stores.size());

        return new Receipt(c.getTime(), Math.round(receiptValue * 100) / 100d, stores.get(storeIndex));
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "date=" + date +
                ", value=" + value +
                ", store='" + store + '\'' +
                '}';
    }
}
