package com.example.myproject0burder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static ApiService apiService;
    private ArrayList<TextView> textos;
    private ArrayList<ArrayList<RadioButton>> respuestas;

    private ArrayList<ImageView> Imagenes;
    //URL DE LA PETICION DEL SERVIDOR;
    private static final String baseUrl= "http://192.168.1.35:3000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textos = ArrayPreguntas();

        Imagenes = ArrayFotos();

        respuestas = ArrayRespuestas();

        ObtenerPreguntas();

        ObtenerImagenes();

        ObtenerRespuestas();

        //HACER COMO UN AVISO DE TEST EMPEZAR
        CharSequence texto = "EL TEST HA COMENZADO";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, texto, duration);
        toast.show();

        Button boton = (Button) findViewById(R.id.Boton);

        boton.setOnClickListener(this);
    }

    public ArrayList<TextView> ArrayPreguntas(){
        ArrayList<TextView> texto = new ArrayList<>();

        texto.add(findViewById(R.id.Pregunta01));
        texto.add(findViewById(R.id.Pregunta02));
        texto.add(findViewById(R.id.Pregunta03));
        texto.add(findViewById(R.id.Pregunta04));
        texto.add(findViewById(R.id.Pregunta05));
        texto.add(findViewById(R.id.Pregunta06));
        texto.add(findViewById(R.id.Pregunta07));
        texto.add(findViewById(R.id.Pregunta08));
        texto.add(findViewById(R.id.Pregunta09));
        texto.add(findViewById(R.id.Pregunta010));

        return texto;
    }

    public ArrayList<ImageView> ArrayFotos(){
        ArrayList<ImageView> Imagenes = new ArrayList<>();

        Imagenes.add(findViewById(R.id.Imagen1));
        Imagenes.add(findViewById(R.id.Imagen2));
        Imagenes.add(findViewById(R.id.Imagen3));
        Imagenes.add(findViewById(R.id.Imagen4));
        Imagenes.add(findViewById(R.id.Imagen5));
        Imagenes.add(findViewById(R.id.Imagen6));
        Imagenes.add(findViewById(R.id.Imagen7));
        Imagenes.add(findViewById(R.id.Imagen8));
        Imagenes.add(findViewById(R.id.Imagen9));
        Imagenes.add(findViewById(R.id.Imagen10));


        return Imagenes;
    }

    public ArrayList<ArrayList<RadioButton>> ArrayRespuestas(){
        ArrayList<ArrayList<RadioButton>> respuestas = new ArrayList<>();

        // recoger las 4 preguntas que estan en el xml
        ArrayList<RadioButton> p1 = new ArrayList<>();
        p1.add(findViewById(R.id.Opcion1));
        p1.add(findViewById(R.id.Opcion2));
        p1.add(findViewById(R.id.Opcion3));
        p1.add(findViewById(R.id.Opcion4));
        respuestas.add(p1);

        ArrayList<RadioButton> p2 = new ArrayList<>();
        p2.add(findViewById(R.id.Opcion1_2));
        p2.add(findViewById(R.id.Opcion2_2));
        p2.add(findViewById(R.id.Opcion3_2));
        p2.add(findViewById(R.id.Opcion4_2));
        respuestas.add(p2);

        ArrayList<RadioButton> p3 = new ArrayList<>();
        p3.add(findViewById(R.id.Opcion1_3));
        p3.add(findViewById(R.id.Opcion2_3));
        p3.add(findViewById(R.id.Opcion3_3));
        p3.add(findViewById(R.id.Opcion4_3));
        respuestas.add(p3);

        ArrayList<RadioButton> p4 = new ArrayList<>();
        p4.add(findViewById(R.id.Opcion1_4));
        p4.add(findViewById(R.id.Opcion2_4));
        p4.add(findViewById(R.id.Opcion3_4));
        p4.add(findViewById(R.id.Opcion4_4));
        respuestas.add(p4);

        ArrayList<RadioButton> p5 = new ArrayList<>();
        p5.add(findViewById(R.id.Opcion1_5));
        p5.add(findViewById(R.id.Opcion2_5));
        p5.add(findViewById(R.id.Opcion3_5));
        p5.add(findViewById(R.id.Opcion4_5));
        respuestas.add(p5);

        ArrayList<RadioButton> p6 = new ArrayList<>();
        p6.add(findViewById(R.id.Opcion1_6));
        p6.add(findViewById(R.id.Opcion2_6));
        p6.add(findViewById(R.id.Opcion3_6));
        p6.add(findViewById(R.id.Opcion4_6));
        respuestas.add(p6);

        ArrayList<RadioButton> p7 = new ArrayList<>();
        p7.add(findViewById(R.id.Opcion1_7));
        p7.add(findViewById(R.id.Opcion2_7));
        p7.add(findViewById(R.id.Opcion3_7));
        p7.add(findViewById(R.id.Opcion4_7));
        respuestas.add(p7);

        ArrayList<RadioButton> p8 = new ArrayList<>();
        p8.add(findViewById(R.id.Opcion1_8));
        p8.add(findViewById(R.id.Opcion2_8));
        p8.add(findViewById(R.id.Opcion3_8));
        p8.add(findViewById(R.id.Opcion4_8));
        respuestas.add(p8);

        ArrayList<RadioButton> p9 = new ArrayList<>();
        p9.add(findViewById(R.id.Opcion1_9));
        p9.add(findViewById(R.id.Opcion2_9));
        p9.add(findViewById(R.id.Opcion3_9));
        p9.add(findViewById(R.id.Opcion4_9));
        respuestas.add(p9);

        ArrayList<RadioButton> p10 = new ArrayList<>();
        p10.add(findViewById(R.id.Opcion1_10));
        p10.add(findViewById(R.id.Opcion2_10));
        p10.add(findViewById(R.id.Opcion3_10));
        p10.add(findViewById(R.id.Opcion4_10));
        respuestas.add(p10);


        return respuestas;
    }

    private void ObtenerPreguntas(){

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de ApiService
        apiService = retrofit.create(ApiService.class);

        Call<List<Pregunta>> call = apiService.ObtenerPreguntas();
        call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse (Call<List<Pregunta>> call, Response<List<Pregunta>> response){
                if (response.isSuccessful()){
                    Log.d("CONEXION","SERVIDOR CONECTADO");
                    List<Pregunta> pregun = response.body();

                    if(pregun != null ){

                        //RECORRER LA ARRAY DE LOS TEXTOS
                        for(int i =0; i < textos.size();i++){
                            //PARA CAMBIAR LOS CAMPOS TEXTVIEWS POR LOS DEL JSON
                            textos.get(i).setText(pregun.get(i).getPregunta());
                        }
                    }else {
                        Log.e("Error", "La lista de preguntas está vacía o es nula.");
                    }
                }
                else{
                    int ErrorCode = response.code();
                    try{
                        String error = response.errorBody().string();
                        Log.e("ERROR:","CODIGO ERROR:" +ErrorCode+", Mensaje de error: "+error);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            };

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
                Log.e("ERROR","ERROR EN LA CONEXION");
            };
        });
    }

    private void ObtenerImagenes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        Call<List<Pregunta>> call = apiService.ObtenerImagenes();

        call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                if (response.isSuccessful()){
                    Log.d("CONEXION","SERVIDOR CONECTADO");
                    List<Pregunta> pregun = response.body();
                    if (pregun != null){
                        //RECORRER LA ARRAY DE LAS IMAGENES
                        for (int i = 0; i < Imagenes.size(); i++){
                            String URLImagen = pregun.get(i).getImatge();

                            ImageView imageView = Imagenes.get(i);

                            Picasso.get().load(URLImagen).into(imageView);
                        }
                    }else{
                        Log.e("Error", "La lista de imagenes está vacía o es nula.");
                    }
                }
                else{
                    int ErrorCode = response.code();
                    try{
                        String error = response.errorBody().string();
                        Log.e("ERROR:","CODIGO ERROR:" +ErrorCode+", Mensaje de error: "+error);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
                Log.e("ERROR","NO HAY CONEXION CON EL SERVIDOR");
            }
        });
    }

    private  void ObtenerRespuestas(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        Call<List<Pregunta>> call = apiService.ObtenerRespuestas();

        call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                if (response.isSuccessful()){
                    Log.d("CONEXION", "SERVIDOR CONECTADO");
                    //PARA PODER CAPTURAR LOS DATOS DEL JSON
                    List<Pregunta> pregun = response.body();
                    if (pregun != null){

                        for (int i=0; i< pregun.size();i++){
                            //OBTENEMOS UNA LISTA CON ELEMENTE ESPECIFICO
                            Pregunta pregunta = pregun.get(i);
                            //ES PARA DEVOLVER UNA LISTA DE CADENA
                            List<String> textoRes = pregunta.getRespostes();

                            for (int j=0; j < pregunta.getRespostes().size();j++){
                                //CONTENIDO DEL JSON QUE SON LAS RESPUESTAS
                                String respuestaTexto = textoRes.get(j);

                                //TRASPASAR LOS DATOS DEL JSON A LOS RADIOBUTTON
                                RadioButton radioButton = respuestas.get(i).get(j);
                                radioButton.setText(respuestaTexto);
                                radioButton.setTag(pregunta.getId());
                            }
                        }
                    }else {
                        Log.e("Error", "La lista de respuestas está vacía o es nula.");
                    }
                }
                else{
                    int ErrorCode = response.code();
                    try{
                        String error = response.errorBody().string();
                        Log.e("ERROR:","CODIGO ERROR:" +ErrorCode+", MENSAJE DE ERRROR: "+error);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
                Log.e("ERROR","NO HAY CONEXION CON EL SERVIDOR");
            }
        });
    }

    private void DevolverPreguntas(List<Respuestas> respuestasList){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        Call<Void> call = apiService.DevolverRespuestas(respuestasList);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("CONEXION", "SERVIDOR CONECTADO Y RESPUETAS ENVIADAS");
                }
                else{
                    int ErrorCode = response.code();
                    try{
                        String error = response.errorBody().string();
                        Log.e("ERROR:","CODIGO ERROR:" +ErrorCode+", MENSAJE DE ERRROR: "+error);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("ERROR","NO HAY CONEXION CON EL SERVIDOR");
            }
        });
    }

    public boolean Verificar (RadioGroup...grup){
        boolean result = true;
        for (RadioGroup TotalBotones : grup){
            if (TotalBotones.getCheckedRadioButtonId() == -1) {
                result = false;
            }
        }

        return result;
    }

    public String OpcionSeleccionada(RadioGroup...grups){
        String imp ="";
        int cont = 1;
        for (RadioGroup respuestas : grups){
            int idRes = respuestas.getCheckedRadioButtonId();
            RadioButton boto = (RadioButton) findViewById(idRes);
            imp = imp + "Respuestas " + cont+": "+boto.getText().toString()+"\n";
            cont++;
        }
        return imp;
    }

    @Override
    public void onClick(View v) {
        RadioGroup pre1 = (RadioGroup) findViewById(R.id.Pregunta1);
        RadioGroup pre2 = (RadioGroup) findViewById(R.id.Pregunta2);
        RadioGroup pre3 = (RadioGroup) findViewById(R.id.Pregunta3);
        RadioGroup pre4 = (RadioGroup) findViewById(R.id.Pregunta4);
        RadioGroup pre5 = (RadioGroup) findViewById(R.id.Pregunta5);
        RadioGroup pre6 = (RadioGroup) findViewById(R.id.Pregunta6);
        RadioGroup pre7 = (RadioGroup) findViewById(R.id.Pregunta7);
        RadioGroup pre8 = (RadioGroup) findViewById(R.id.Pregunta8);
        RadioGroup pre9 = (RadioGroup) findViewById(R.id.Pregunta9);
        RadioGroup pre10 = (RadioGroup) findViewById(R.id.Pregunta10);

        if (Verificar(pre1,pre2,pre3,pre4,pre5,pre6,pre7,pre8,pre9,pre10) == false){
            CharSequence text = "Debe responderlas todas";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }
        else {
            CharSequence text = "ENVIADO";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

            Log.d("RespuestasLOG",OpcionSeleccionada(pre1,pre2,pre3,pre4,pre5,pre6,pre7,pre8,pre9,pre10));

            List<Respuestas> respuestasList  = new ArrayList<>();

            for (int i = 1; i <= 10; i++) {

                //PARA EL NOMBRE DEL RADIGROUP QUE VIENE SIENDO EL ID
                String IDGROUP = "Pregunta" + i;

                //OBTENEMOS EL ID NUMERICO ASOCIADO A UN RECURSO DE LA CARPETA
                int radioGroupId = getResources().getIdentifier(IDGROUP, "id", getPackageName());

                //POR SINO COINCIDE EL NOMBRE O EL ID BUSCADO SALTAR UN ERRROR
                if (radioGroupId == 0) {
                    Log.e("ERROR", "No se encontró el RadioGroup con el nombre " + IDGROUP);
                    return;  // Salir si no se encontró el RadioGroup
                }

                //BUSCAMOS EL RADIOGROUP POR EL ID QUE HEMOS OBTENIDO
                RadioGroup radioGroup = findViewById(radioGroupId);

                //NOS DEVUELVE EL ID DEL RADIOBUTTON SELECIONADO
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                //CONDICION EN LA QUE SI SELLECIONAMOS TODOS VAMOS A ESTE IF SINO AL ELSE
                if (selectedRadioButtonId != -1) {

                    //BUSCAMOS EL RADIOGROUP POR EL ID QUE HEMOS OBTENIDO
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                    //NOS DEVUELVE UN CHAR PERO CON EL TOSTRING LO VOLVEMOS UN STRING
                    String respuesta = selectedRadioButton.getText().toString();

                    //PARA OBTERNER EL RESULTADO DE LA FUNCION PARA VER QUE ID TIENE LA RESPUESTA.
                    int respuestaNumero = obtenerRespuestaNumero(selectedRadioButtonId, radioGroup);


                    //PASAMOS LOS DATOS QUE HEMOS IDO CAPTURANDO AL CONSTRUCTOR
                    Respuestas respuestaObj = new Respuestas(i, respuestaNumero,respuesta);

                    //LUEGO AÑADIMOS LA INFORMACION A LA ARRAYLIST
                    respuestasList.add(respuestaObj);
                } else {
                    Log.e("ERROR", "No se ha seleccionado una respuesta para la pregunta " + i);
                }
            }
            //SE LO PASAMOS A LA FUNCION DE ENVIAR PREGUNTAS AL SERCIDOR
            DevolverPreguntas(respuestasList);
        }
    }

    private int obtenerRespuestaNumero(int selectedRadioButtonId, RadioGroup radioGroup) {

        //OBTENEMOS EL NUMERO DE RADIOBUTTONS QUE HAY DENTRO DEL RADIOGROUP
        int radioButtonCount = radioGroup.getChildCount();

        //RECOREMOS LOS RADIOSBUTTONS QUE TENEMOS DENTRO DE RADIOGROUP
        for (int i = 0; i < radioButtonCount; i++) {

            //OBTENEMOS LA POSICION I DENTRO DEL RADIOGROUP QUE EN ESTE CASO SERIA DEL 0 AL 3
            View radioButton = radioGroup.getChildAt(i);

            //CON EL INSTANCEOF LO QUE HACEMOS ES VER SI EN VERDAD ES UN RADIOBUTTTON, LUEGO COMPARAMOS LOS ID QUE HEMOS SELECIONADO
            //LOS OTROS RADIOBUTTON
            if (radioButton instanceof RadioButton && radioButton.getId() == selectedRadioButtonId) {
                // Si encontramos el RadioButton seleccionado, su índice será el número de respuesta (0, 1, 2, 3)
                return i;
            }
        }
        return -1;  // En caso de que el ID no se encuentre en la lista de respuestas
    }


}

