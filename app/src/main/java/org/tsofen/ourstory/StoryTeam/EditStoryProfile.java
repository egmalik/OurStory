package org.tsofen.ourstory.StoryTeam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import org.tsofen.ourstory.R;

public class EditStoryProfile extends AppCompatActivity {
    int flag = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_story_profile);
        //Intent intent = getIntent();
        Story story=new Story("pini", "Cohen","12/8/1930","5/4/2002");
        TextView textView=findViewById(R.id.firstNameEditText_edit);
        textView.setText(story.FirstName);
        textView=findViewById(R.id.lastNameEditText_edit);
        textView.setText(story.LastName);
        String date =story.date1;
        String[] parts = date.split("/");
        String day = parts[0]; // 004
        String month = parts[1]; // 034556
        String year = parts[2]; // 034556
        textView=findViewById(R.id.day_1_edit);
        textView.setText(day);
        textView=findViewById(R.id.month_1_edit);
        textView.setText(month);
        textView=findViewById(R.id.year_1_edit);
        textView.setText(year);

         date =story.date2;
         parts = date.split("/");
         day = parts[0]; // 004
         month = parts[1]; // 034556
         year = parts[2]; // 034556

        textView=findViewById(R.id.day_2_edit);
        textView.setText(day);
        textView=findViewById(R.id.month_2_edit);
        textView.setText(month);
        textView=findViewById(R.id.year_2_edit);
        textView.setText(year);







    }

    public void closeActivity(View view) {
    }

    public void showDatePicker1(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        flag = 1;
    }

    public void showDatePicker2(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        flag = 2;

    }

    public void pickPic(View view) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Pick an image"), 1);

    }

    public void cancel(View view) {
        finish();
    }

    public void updateProfile(View view) {
    }
}
