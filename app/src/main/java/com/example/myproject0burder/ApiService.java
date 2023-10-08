package com.example.myproject0burder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface  ApiService {

    @GET("getPreguntes")
    Call<List<Pregunta>> ObtenerPreguntas();
    @GET("getPreguntes")
    Call<List<Pregunta>> ObtenerImagenes();
    @GET("getPreguntes")
    Call<List<Pregunta>> ObtenerRespuestas();

    @POST("PreguntasAndroid")
    Call<Void> DevolverRespuestas(@Body List<Respuestas> respuestas);
}