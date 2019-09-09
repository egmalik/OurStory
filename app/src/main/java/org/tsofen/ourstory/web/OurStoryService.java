package org.tsofen.ourstory.web;


import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.CommentA;
import org.tsofen.ourstory.model.api.ListOfStory;
import org.tsofen.ourstory.model.api.MemoryA;
import org.tsofen.ourstory.model.api.Owner;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OurStoryService {
    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("api/comments")
    Call<CommentA> newComment(@Body CommentA comment);
    @GET("memories/getUserMemories/{id}")
    Call<ArrayList<MemoryA>> GetMemoriesByUser(@Path("id") long id);
    @GET("comments/findById/{id}")
    Call<ArrayList<CommentA>> GetCommentbyId(@Path("id") long id);
    @Headers({"Content-Type: application/json"})
    @POST("stories/create")
    Call<Story> CreateStory(@Body Story story);

    @GET("users/findById/{id}")
    Call<Owner> GetUserById(@Path("id") long id);

    @GET("stories/findStoriesByKeyword/")
    Call<ArrayList<ListOfStory>> GetStoriesByName(@Query("name") String name);
    @POST("memories/create")
    Call<Memory> CreateMemory(@Body Memory memory);
    // TODO: Maybe need to change that path.
    @PUT("memories/create")
    Call<Memory> EditMemory(@Body Memory memory);
    @GET("users/findByEmail/{email}")
    Call<User> GetUserByEmail( @Path("email") String email);


    Call<ArrayList<ListOfStory>> GetStoriesByName(@Query("name") String n);

    @GET("stories/findById/{id}")
    Call<Story> GetStoryById(@Path("id") long id);

}
