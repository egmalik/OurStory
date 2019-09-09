package org.tsofen.ourstory.StoryTeam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.tsofen.ourstory.EditCreateMemory.CreateEditMemoryActivity;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.io.Serializable;

public class ViewStory extends AppCompatActivity implements Serializable {

    ImageButton ib;
    ImageButton share;
    Story target_story ;
    private OurStoryService story_api;
    //Intent intent = getIntent() ;
    long id =2;
    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_story);
        Boolean flag = Boolean.TRUE;

        if (flag){
              LinearLayout linearLayout = findViewById(R.id.linearLayout3);
              linearLayout.setVisibility(View.INVISIBLE);

              ConstraintLayout constraintLayout = findViewById(R.id.constrainlayout2);
              constraintLayout.setVisibility(View.VISIBLE);

                Intent intent = getIntent() ;
                String Name = intent.getStringExtra("name");
                String date1 = intent.getStringExtra("date1");
                String date2 = intent.getStringExtra("date2");
                String tag1 = intent.getStringExtra("ttag3");
                String tag2 = intent.getStringExtra("ttag2");
                String tag3 = intent.getStringExtra("ttag1");

                int iv = intent.getIntExtra("image", 0);
                int ic1 = intent.getIntExtra("tag1", 0);
                int ic2 = intent.getIntExtra("tag2", 0);
                int ic3 = intent.getIntExtra("tag3", 0);

                TextView textView = (findViewById(R.id.textView));
                textView.setText(Name);
                
                 String date = date1 + "-" + date2;
                 TextView textView2 = (findViewById(R.id.textView2));
                 textView2.setText(date);
                
                ImageView image4 = findViewById(R.id.imageView3);
                image4.setImageResource(R.drawable.nopicyet);


        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout3);
            linearLayout.setVisibility(View.VISIBLE);

            ConstraintLayout constraintLayout = findViewById(R.id.constrainlayout2);
            constraintLayout.setVisibility(View.INVISIBLE);


            story_api = WebFactory.getService();
            story_api.GetStoryById(new Long(134)).enqueue(new Callback<Story>() {
                @Override
                public void onResponse(Call<Story> call, Response<Story> response) {
                    Log.d("Response", "Response");
                    story = response.body();
                    String name_p = story.getNameOfPerson();
                    Toast.makeText(getApplicationContext(), name_p, Toast.LENGTH_LONG).show();
                    TextView textView1 = (findViewById(R.id.textView));
                    textView1.setText(name_p);

                    String date1 = story.getDateOfBirth();
                    String date2 = story.getDateOfDeath();

//                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                String date11= formatter.format(story.getDateOfBirth()) ;
//                String date22= formatter.format(story.getDateOfDeath()) ;
//
                    String date = date1 + "-" + date2;
                    TextView textView2 = (findViewById(R.id.textView2));
                    textView2.setText(date);

                    ImageView image4 = findViewById(R.id.imageView3);
                    image4.setImageResource(R.drawable.profilepic_1);

                }


                @Override
                public void onFailure(Call<Story> call, Throwable t) {
                    Log.d("Failure", t.toString());

                }
            });
        }

       // Story story = new Story(fName, lName, date1, date2, iv, tag1, tag2, tag3, ic1, ic2, ic3);

//        String date;
//        date = date1 + "-" + date2;
//        TextView textView2 = (findViewById(R.id.textView2));
//        textView2.setText(date);

//        TextView textView4 = (findViewById(R.id.textView4));
//        textView4.setText(story.getTag1());
//
//        TextView textView5 = (findViewById(R.id.textView5));
//        textView5.setText(story.getTag2());
//
//        TextView textView6 = (findViewById(R.id.textView6));
//        textView6.setText(story.getTag3());
//
//        ImageView image1 = findViewById(R.id.imageView5);
//        image1.setImageResource(story.getTag_icon1());
//
//        ImageView image2 = findViewById(R.id.imageView7);
//        image2.setImageResource(story.getTag_icon2());
//
//        ImageView image3 = findViewById(R.id.imageView6);
//        image3.setImageResource(story.getTag_icon3());

    }

    public void launchSearchActivity(View view) {
        Intent i = new Intent(ViewStory.this, SearchStory.class);
        startActivity(i);
    }

    public void launchShare(View view) {
//        share = findViewById(R.id.sharebtn);

//        Intent myIntent = new Intent(Intent.ACTION_SEND);
//        myIntent.setType("text/plain");
//        String shareBody = "The Story of Pini Cohen";
//        String sharesub = "Your subject here";
//        myIntent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
//        myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//        startActivity(Intent.createChooser(myIntent, "Share using: "));
    }
}
