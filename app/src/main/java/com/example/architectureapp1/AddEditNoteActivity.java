package com.example.architectureapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.architectureapp1.extra_id";
    public static final String EXTRA_TITLE = "com.example.architectureapp1.extra_title";
    public static final String EXTRA_DESCRIPTION = "com.example.architectureapp1.extra_description";
    public static final String EXTRA_PRIORITY = "com.example.architectureapp1.extra_priority";
    public static final String EXTRA_IS_SAME = "com.example.architectureapp1.extra_is_same";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPicker;

    private String title;
    private String description;
    private int priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPicker = findViewById(R.id.number_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID) && intent.hasExtra(EXTRA_TITLE) &&
                intent.hasExtra(EXTRA_PRIORITY) && intent.hasExtra(EXTRA_DESCRIPTION)) {
            setTitle("Edit Note");
            title = intent.getStringExtra(EXTRA_TITLE);
            editTextTitle.setText(title);

            description = intent.getStringExtra(EXTRA_DESCRIPTION);
            editTextDescription.setText(description);

            priority = intent.getIntExtra(EXTRA_PRIORITY, 1);
            numberPicker.setValue(priority);
        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save_button) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String newTitle = editTextTitle.getText().toString();
        String newDescription = editTextDescription.getText().toString();
        int newPriority = numberPicker.getValue();

        if(newTitle.trim().isEmpty() || newDescription.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent dataIntent = new Intent();
        dataIntent.putExtra(EXTRA_TITLE, newTitle);
        dataIntent.putExtra(EXTRA_DESCRIPTION, newDescription);
        dataIntent.putExtra(EXTRA_PRIORITY, newPriority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1) {
            dataIntent.putExtra(EXTRA_ID, id);
        }

        if(title != null && title.equals(newTitle)  &&
                description != null && description.equals(newDescription) && priority == newPriority) {
            dataIntent.putExtra(EXTRA_IS_SAME, true);
        } else {
            dataIntent.putExtra(EXTRA_IS_SAME, false);
        }

        setResult(RESULT_OK, dataIntent);
        finish();
    }
}