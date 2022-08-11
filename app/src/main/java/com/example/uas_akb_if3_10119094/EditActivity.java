
//10119094 IF-3 Saeful Anwar Oktariansah

package com.example.uas_akb_if3_10119094;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    com.example.uas_akb_if3_10119094.DBHelper helper;
    EditText NotesTitle,NotesDesc;
    Notes notes;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        helper = new com.example.uas_akb_if3_10119094.DBHelper(this);

        id = getIntent().getLongExtra("id", 0);

        NotesTitle = (EditText)findViewById(R.id.NotesTitleEdit);
        NotesDesc = (EditText)findViewById(R.id.NotesDescEdit);

        getData();

        Button sveditbtn = findViewById(R.id.SaveEdit);
        sveditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = NotesTitle.getText().toString();
                String desc = NotesDesc.getText().toString();

                ContentValues values = new ContentValues();
                values.put(com.example.uas_akb_if3_10119094.DBHelper.row_title, title);
                values.put(com.example.uas_akb_if3_10119094.DBHelper.row_note, desc);

                if(title.equals("") && desc.equals("")){
                    Toast.makeText(EditActivity.this, "Catatan gagal diubah, kedua field tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else {
                    helper.updateData(values, id);
                    Toast.makeText(EditActivity.this, "Catatan berhasil diubah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        Button deleditbtn = findViewById(R.id.DeleteEdit);
        deleditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setMessage("Apakah kamu yakin ingin menghapus catatan ini?");
                builder.setCancelable(true);
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.deleteData(id);
                        Toast.makeText(EditActivity.this, "Catatan berhasil dihapus", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    private void getData(){
        Cursor cursor = helper.oneData(id);
        if (cursor.moveToFirst()){
            String title = cursor.getString(cursor.getColumnIndexOrThrow(com.example.uas_akb_if3_10119094.DBHelper.row_title));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(com.example.uas_akb_if3_10119094.DBHelper.row_note));

            NotesTitle.setText(title);
            NotesDesc.setText(desc);
        }
    }
}