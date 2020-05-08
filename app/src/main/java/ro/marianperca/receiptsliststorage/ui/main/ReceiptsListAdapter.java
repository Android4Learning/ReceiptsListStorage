package ro.marianperca.receiptsliststorage.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import ro.marianperca.receiptsliststorage.R;
import ro.marianperca.receiptsliststorage.models.Receipt;

public class ReceiptsListAdapter extends RecyclerView.Adapter<ReceiptsListAdapter.ViewHolder> {
    private SimpleDateFormat formatDate = new SimpleDateFormat("EEEE, MMM d", Locale.ENGLISH);
    private List<Receipt> mDataset = new LinkedList<>();
    private OnReceiptClickListener mClickListener;

    void setClickListener(OnReceiptClickListener callback) {
        mClickListener = callback;
    }

    // apelata cand se creaza un rand in lista
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_list_item, parent, false);
        return new ViewHolder(cv);
    }

    // apelata de fiecare data cand este populat un rand
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Receipt receipt = mDataset.get(position);
        holder.bind(receipt, mClickListener);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setReceipts(List<Receipt> receipts) {
        mDataset = receipts;
        notifyDataSetChanged();
    }

    public interface OnReceiptClickListener {
        void onClick(Receipt receipt);
    }

    // aceasta clasa este un wrapper pentru layoutul fiecarui rand
    class ViewHolder extends RecyclerView.ViewHolder {
        View rootView; //card view
        TextView mValue;
        TextView mDate;
        TextView mStore;

        ViewHolder(View v) {
            super(v);
            rootView = v;

            mValue = v.findViewById(R.id.value);
            mDate = v.findViewById(R.id.date);
            mStore = v.findViewById(R.id.store);
        }

        void bind(final Receipt receipt, final OnReceiptClickListener listener) {
            mValue.setText(receipt.value + "");
            mDate.setText(formatDate.format(receipt.date));
            mStore.setText(receipt.store);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(receipt);
                }
            });
        }
    }
}
