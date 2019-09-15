package org.tsofen.ourstory.StoryTeam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.esafirm.imagepicker.model.Image;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.tsofen.ourstory.EditCreateMemory.CreateEditMemoryActivity;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.UserModel.UsersList;
import org.tsofen.ourstory.model.api.FullViewStory;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.Tags;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

import static android.app.PendingIntent.getActivity;

public class ViewStory extends AppCompatActivity implements Serializable {

    ImageButton ib;
    ImageButton share;
    Story target_story ;
    Object imageView_profile;
    Tags tag;
    private final LinkedList<Story> mStoryList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private ViewStoryAdapter mAdapter;

    private OurStoryService story_api;
    private OurStoryService story_api2;
    //Intent intent = getIntent() ;
    long id =2;
    FullViewStory story_full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_story);
        Boolean flag = Boolean.FALSE;


        Story story = new Story("pini Cohen", "12/8/1930", "5/4/2002", "mm");
//        String Name=story.getNameOfPerson();
//        String date1= story.getDateOfBirth();
//        String date2=story.getDateOfDeath();
//
        for (int i = 0; i < 20; i++) {
            mStoryList.addLast(new Story("pini Cohen" + i, "12/8/1930" + i, "5/4/2002", "m"));

        }
        Toast.makeText(this, mStoryList.get(7).getNameOfPerson(), Toast.LENGTH_SHORT).show();


        Activity aa = this;
        if (flag) {
            LinearLayout linearLayout = findViewById(R.id.linearLayout3);
            linearLayout.setVisibility(View.INVISIBLE);

            ConstraintLayout constraintLayout = findViewById(R.id.constrainlayout2);
            constraintLayout.setVisibility(View.VISIBLE);

            Intent intent = getIntent();
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


        } else {
        LinearLayout linearLayout = findViewById(R.id.linearLayout3);
        linearLayout.setVisibility(View.VISIBLE);

        ConstraintLayout constraintLayout = findViewById(R.id.constrainlayout2);
        constraintLayout.setVisibility(View.INVISIBLE);

        story_api = WebFactory.getService();
        story_api.GetFullViewStoryById(new Long(26)).enqueue(new Callback<FullViewStory>() {
            @Override
            public void onResponse(Call<FullViewStory> call, Response<FullViewStory> response) {
                story_full = response.body();
                if (story_full == null)
                    Toast.makeText(getApplicationContext(), "Null", Toast.LENGTH_LONG).show();
                else {

                    String personName = story_full.getStory().getNameOfPerson();
                    TextView textView1 = (findViewById(R.id.textView));
                    textView1.setText(personName);
                    String date1 = story_full.getStory().getDateOfBirth();
                    String date2 = story_full.getStory().getDateOfDeath();
                    String[] parts = date1.split("-");
                    String year = parts[0];
                    String month = parts[1];
                    String day = parts[2];
                    day = day.substring(0, 2);

                    date1 = day + "/" + month + "/" + year;

                    parts = date2.split("-");
                    year = parts[0];
                    month = parts[1];
                    day = parts[2];
                    day = day.substring(0, 2);

                    date2 = day + "/" + month + "/" + year;


                    String date = date1 + "-" + date2;
                    TextView textView2 = (findViewById(R.id.textView2));
                    textView2.setText(date);

                    ImageView pic = findViewById(R.id.imageView3);
                    imageView_profile = story_full.getStory().getPicture();
                    String st = imageView_profile.toString();
                    Uri uri = Uri.parse(st);
                    RequestOptions options = new RequestOptions()
                            .override(375, 192)
                            .centerCrop()
                            .placeholder(R.drawable.nopicyet)
                            .error(R.drawable.nopicyet);

                    Glide.with(aa).load(uri).apply(options).into(pic);
                    TextView textView = findViewById(R.id.textView4);
                    textView.setText(story_full.getTop3tags().get(0));
                    Log.d("sss",story_full.getTop3tags().get(0) );

                    textView = findViewById(R.id.textView5);
                    textView.setText(story_full.getTop3tags().get(1));
                    textView = findViewById(R.id.textView6);
                    textView.setText(story_full.getTop3tags().get(2));




                    // Get a handle to the RecyclerView.
                    mRecyclerView = findViewById(R.id.recyclerview);
// Create an adapter and supply the data to be displayed.
                    mAdapter = new ViewStoryAdapter(aa, story_full.getMemories());
               //     Toast.makeText(aa, mAdapter.mStoryList.get(7).(), Toast.LENGTH_SHORT).show();


// Connect the adapter with the RecyclerView.
                    mRecyclerView.setAdapter(mAdapter);
// Give the RecyclerView a default layout manager.
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(aa));





                }
            }


            @Override
            public void onFailure(Call<FullViewStory> call, Throwable t) {

            }
        });



    }


//
//            story_api = WebFactory.getService();
//            story_api.GetStoryById(new Long(23)).enqueue(new Callback<Story>() {
//                @Override
//                public void onResponse(Call<Story> call, Response<Story> response) {
//                    Log.d("Response", "Response");
//                    story = response.body();
//                    String name_p = story.getNameOfPerson();
//                    Toast.makeText(getApplicationContext(), name_p, Toast.LENGTH_LONG).show();
//                    TextView textView1 = (findViewById(R.id.textView));
//                    textView1.setText(name_p);
//
//                    String date1 = story.getDateOfBirth();
//                    String date2 = story.getDateOfDeath();
//                    String[] parts = date1.split("-");
//                    String year = parts[0];
//                    String month = parts[1];
//                    String day = parts[2];
//                    day=day.substring(0,2);
//
//                    date1=day+"/"+month+"/"+year;
//
//                    parts = date2.split("-");
//                    year = parts[0];
//                    month = parts[1];
//                    day = parts[2];
//                    day=day.substring(0,2);
//
//                    date2= day+"/"+month+"/"+year;
//
//
//                    String date = date1 + "-" + date2;
//                    TextView textView2 = (findViewById(R.id.textView2));
//                    textView2.setText(date);
//
//
//
//
//
//
//
//                    ImageView pic = findViewById(R.id.imageView3);
//                    imageView_profile = story.getPicture();
//                    String st=imageView_profile.toString();
//                    Uri uri=Uri.parse(st);
//                    RequestOptions options = new RequestOptions()
//                            .override(375,192)
//                            .centerCrop()
//                            .placeholder(R.drawable.nopicyet)
//                            .error(R.drawable.nopicyet);
//
//                    Glide.with(aa).load(uri).apply(options).into(pic);
//
//
//
//                    story_api2 = WebFactory.getService();
//                    story_api2.GetListPicById(23).enqueue(call<);
//
//
//
////                    story_api = WebFactory.getService();
////                    story_api.GetTop3TagsByStoryId(new Long(23)).enqueue(new Callback<Tags>() {
////                        @Override
////                        public void onResponse(Call<Tags> call, Response<Tags> response) {
////                            Log.d("Response", "Response");
////                            tag = response.body();
////                            Toast.makeText(getApplicationContext(),tag.getHref(),Toast.LENGTH_LONG).show();
////                            String texttag = tag.getHref();
////                        }
////
////
////
////                    @Override
////                    public void onFailure(Call<Tags> call, Throwable t) {
////                        Log.d("Failure", t.toString());
////                    }
////
////                });
//
//
//
//
//                }
//
//
//                @Override
//                public void onFailure(Call<Story> call, Throwable t) {
//                    Log.d("Failure", t.toString());
//
//                }
//
//            });
//        }

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

    public void EditProfile(View view) {
        Intent intent= new Intent(this,EditStoryProfile.class);
        startActivity(intent);

    }

    public void back(View view) {
        finish();
    }

    public void AddMemory(View view) {
        Intent intent=new Intent(this,CreateEditMemoryActivity.class);
        startActivity(intent);
    }
}
