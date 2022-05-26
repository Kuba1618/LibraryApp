package pl.kielce.tu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pl.kielce.tu.library.Student;

import java.util.ArrayList;

public class AdapterAllOfStudents extends RecyclerView.Adapter<AdapterAllOfStudents.MyViewHolder> {

    Context context;
    private final ArrayList<Student> listOfStudents;
    private final OnStudentListener onStudentListener;

    public AdapterAllOfStudents(Context context, ArrayList<Student> listOfStudents, OnStudentListener onStudentListener) {
        this.context = context;
        this.listOfStudents = listOfStudents;
        this.onStudentListener = onStudentListener;
    }

    @NonNull
    @Override
    public AdapterAllOfStudents.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_student,parent,false);
        return new MyViewHolder(v, onStudentListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAllOfStudents.MyViewHolder holder, int position) {
        Student student = listOfStudents.get(position);
        holder.nameStudentTV.setText(student.getName());
        holder.surnameStudentTv.setText(student.getSurname());

    }

    @Override
    public int getItemCount() {
        return listOfStudents.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        TextView nameStudentTV,surnameStudentTv;
        OnStudentListener onStudentListener;
        public MyViewHolder(@NonNull View itemView, OnStudentListener onStudentListener) {
            super(itemView);

            nameStudentTV = itemView.findViewById(R.id.nameStudentTV);
            surnameStudentTv = itemView.findViewById(R.id.surnameStudentTv);

            this.onStudentListener = onStudentListener;

            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }



        @Override
        public boolean onLongClick(View v) {
            onStudentListener.onLongStudentClick(getAdapterPosition());
            return true;
        }

        @Override
        public void onClick(View v) {
            onStudentListener.onStudentClick(getAdapterPosition());
        }
    }

    public interface OnStudentListener {
        void onStudentClick(int position);
        void onLongStudentClick(int position);
    }
}
