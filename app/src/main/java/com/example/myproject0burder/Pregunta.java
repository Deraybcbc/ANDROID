package com.example.myproject0burder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pregunta {
    @SerializedName("ID")
    private int id;
    @SerializedName("pregunta")
    private String pregunta;
    @SerializedName("respostes")
    private List<String> respostes;
    @SerializedName("resposta_correcta")
    private int resposta_correcta;
    @SerializedName("respuestaSeleccionada")
    private String respuestaSeleccionada;
    @SerializedName("imatge")
    private String imatge;

    public int getId() {
        return id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public List<String> getRespostes() {
        return respostes;
    }

    public int getResposta_correcta() {
        return resposta_correcta;
    }

    public String getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public String getImatge() {
        return imatge;
    }
}