package com.example.fitsync;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ExerciseApi {
    private static final String base_url = "https://exercisedb.p.rapidapi.com/exercises/bodyPart/";

    public static void fetchExercisesBodyPart(String bodyPart, Callback callback){
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = HttpUrl.parse(base_url+bodyPart)
                .newBuilder()
                .addQueryParameter("limit","10")
                .addQueryParameter("offset","0")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-rapidapi-host","exercisedb.p.rapidapi.com")
                .addHeader("x-rapidapi-key","ae5465de09msh905d7980c7ef394p19f895jsnf23dcb44988d")
                .build();

        client.newCall(request).enqueue(callback);
    }
}
