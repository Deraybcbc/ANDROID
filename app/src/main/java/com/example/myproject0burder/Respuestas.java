package com.example.myproject0burder;

import com.google.gson.annotations.SerializedName;

public class Respuestas {



    @SerializedName("id")
    private  int id;

    @SerializedName("idRespuesta")
    private int idRespuesta;
    @SerializedName("respuestas")
    private String respuestas;

    public Respuestas(int preguntaId,int idRespuesta, String respuestaTexto) {
        this.id = preguntaId;
        this.idRespuesta=idRespuesta;
        this.respuestas = respuestaTexto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }
}
