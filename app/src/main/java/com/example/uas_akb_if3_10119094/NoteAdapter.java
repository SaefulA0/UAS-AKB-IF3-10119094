
//10119094 IF-3 Saeful Anwar Oktariansah

package com.example.uas_akb_if3_10119094;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Notes> notes;
    long id;

    public NoteAdapter(Context context, List<Notes> notes)
    {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ctnnotes, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = notes.get(position).getTitle();
        String desc = notes.get(position).getDesc();
        String created = notes.get(position).getCreated();

        holder.title.setText(title);
        holder.desc.setText(desc);
        holder.created.setText(created);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, created, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.ListTitle);
            desc = itemView.findViewById(R.id.ListDescription);
            created = itemView.findViewById(R.id.ListCreate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), com.example.uas_akb_if3_10119094.EditActivity.class);
                    intent.putExtra("id", notes.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
