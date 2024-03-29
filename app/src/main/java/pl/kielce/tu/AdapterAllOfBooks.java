package pl.kielce.tu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pl.kielce.tu.library.Book;

import java.util.ArrayList;
import java.util.List;

public class AdapterAllOfBooks extends RecyclerView.Adapter<AdapterAllOfBooks.MyViewHolder> {

    Context context;
    private List<Book> listOfBooks;
    private final  OnBookListener onBookListener;

    public AdapterAllOfBooks(Context context, ArrayList<Book> listOfBooks, OnBookListener onBookListener) {
        this.context = context;
        this.listOfBooks = listOfBooks;
        this.onBookListener = onBookListener;
    }

    public void setFilteredList(List<Book> filteredList){
        this.listOfBooks = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterAllOfBooks.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_book,parent,false);
        return new MyViewHolder(v,onBookListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAllOfBooks.MyViewHolder holder, int position) {
        Book book = listOfBooks.get(position);
        holder.titleBookTv.setText(book.getTitle());
        holder.authorBookTv.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        return listOfBooks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView titleBookTv,authorBookTv;
        OnBookListener onBookListener;
        public MyViewHolder(@NonNull View itemView,OnBookListener onBookListener) {
            super(itemView);

            titleBookTv = itemView.findViewById(R.id.titleBookTv);
            authorBookTv = itemView.findViewById(R.id.authorBookTv);

            this.onBookListener = onBookListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            onBookListener.onLongBookClick(getAdapterPosition());
            return true;
        }

        @Override
        public void onClick(View v) {
            onBookListener.onBookCLick(getAdapterPosition());
        }
    }

    public interface OnBookListener {
        void onBookCLick(int position);
        void onLongBookClick(int position);
    }
}
