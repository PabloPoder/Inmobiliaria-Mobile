package com.grupo4.inmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.StringDef;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grupo4.inmobiliaria.modelo.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public class ApiClient {
    private ArrayList<Propietario> propietarios=new ArrayList<>();
    private ArrayList<Inquilino> inquilinos=new ArrayList<>();
    private ArrayList<Inmueble> inmuebles=new ArrayList<>();
    private ArrayList<Contrato> contratos=new ArrayList<>();
    private ArrayList<Pago> pagos=new ArrayList<>();
    private static Propietario usuarioActual = null;
    private static ApiClient api=null;

    public static String getToken(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("token.dat", 0);
        String token = sharedPreferences.getString("token", "Token incorrecto");

        return token;
    }

    private static final String PATH = "http://192.168.1.105:45455/api/";
    private static MyApiInterface myApiInterface;

    public static MyApiInterface getMyApiClient(){

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myApiInterface = retrofit.create(MyApiInterface.class);
        Log.d("Salida", retrofit.baseUrl().toString());

        return myApiInterface;
    }

    public interface MyApiInterface {

        @FormUrlEncoded
        @POST("Propietarios/Login")
        Call<String> Login (@Field("usuario")String usuario, @Field("clave")String clave);

        // @FormUrlEncoded
        @GET("Propietarios/PropietarioActual")
        Call<Propietario> PropietarioActual(@Header("Authorization") String token);

        // @FormUrlEnconded
        @GET("Inmuebles/")
        Call<List<Inmueble>> Inmuebles (@Header("Authorization") String token);

        // @FormUrlEnconded
        @GET("Contratos/{id}")
        Call<Contrato> Contrato (@Path("id") int id, @Header("Authorization") String token);

        @GET("Contratos/ContratosVigentes")
        Call<List<Contrato>> ContratosVigentes (@Header("Authorization") String token);

        @PUT("Propietarios/EditarUsuario/{id}")
        Call<Propietario> EditarPerfil (@Path("id") int id, @Body Propietario propietario, @Header("Authorization") String token);

        @GET("Pagos/{id}")
        Call<List<Pago>> Pagos(@Path("id") int id, @Header("Authorization") String token);

        @GET("Contratos/ContratoVigentePorInmueble/{id}")
        Call<Contrato> ContratoVigentePorInmueble (@Path("id") int id, @Header("Authorization") String token);
    }




}


