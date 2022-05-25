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

public class AdapterAllOfBooks extends RecyclerView.Adapter<AdapterAllOfBooks.MyViewHolder>  {

    Context context;
    ArrayList<Book> listOfBooks;


    public AdapterAllOfBooks(Context context, ArrayList<Book> listOfBooks) {
        this.context = context;
        this.listOfBooks = listOfBooks;
    }

    @NonNull
    @Override
    public AdapterAllOfBooks.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_book,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAllOfBooks.MyViewHolder holder, int position) {
        Book book = listOfBooks.get(position);
        holder.titleBookTv.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        Toast.makeText(context, "Size: " + listOfBooks.size(), Toast.LENGTH_LONG).show();
        return listOfBooks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleBookTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleBookTv = itemView.findViewById(R.id.titleBookTv);
        }
    }
}
