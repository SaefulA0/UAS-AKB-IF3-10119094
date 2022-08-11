
//10119094 IF-3 Saeful Anwar Oktariansah

package com.example.uas_akb_if3_10119094;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    com.example.uas_akb_if3_10119094.DBHelper helper;
    EditText NotesTitle,NotesDesc;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        helper = new com.example.uas_akb_if3_10119094.DBHelper(this);

        NotesTitle = (EditText)findViewById(R.id.NotesTitleAdd);
        NotesDesc = (EditText)findViewById(R.id.NotesDescAdd);

        Button addbtn = findViewById(R.id.SaveAdd);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = NotesTitle.getText().toString();
                String desc = NotesDesc.getText().toString();

                //get calender
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
                String created = simpleDateFormat.format(calendar.getTime());

                ContentValues values = new ContentValues();
                values.put(com.example.uas_akb_if3_10119094.DBHelper.row_title, title);
                values.put(com.example.uas_akb_if3_10119094.DBHelper.row_note, desc);
                values.put(com.example.uas_akb_if3_10119094.DBHelper.row_created, created);

                //Create Condition
                if(title.equals("") && desc.equals("")){
                    Toast.makeText(AddActivity.this, "Catatan gagal ditambah, kedua field tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else {
                    helper.insertData(values);
                    Toast.makeText(AddActivity.this, "Catatan berhasil ditambah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}