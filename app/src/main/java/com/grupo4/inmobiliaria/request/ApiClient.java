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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public class ApiClient {
    private ArrayList<Propietario> propietarios=new ArrayList<>();
    private ArrayList<Inquilino> inquilinos=new ArrayList<>();
    private ArrayList<Inmueble> inmuebles=new ArrayList<>();
    private ArrayList<Contrato> contratos=new ArrayList<>();
    private ArrayList<Pago> pagos=new ArrayList<>();
    private static Propietario usuarioActual = null;
    private static ApiClient api=null;

//    private ApiClient(){
//        //Nos conectamos a nuestra "Base de Datos"
//        cargaDatos();
//    }
    //Método para crear una instancia de ApiClient
//    public static ApiClient getApi(){
//        if (api==null){
//            api=new ApiClient();
//        }
//        return api;
//
//    }
//
//
//
////Dado un inmueble retorna el contrato activo de dicho inmueble
//
//    public Contrato obtenerContratoVigente(Inmueble inmueble){
//
//        for(Contrato contrato:contratos){
//            if(contrato.getInmueble().equals(inmueble)){
//                return contrato;
//            }
//        }
//        return null;
//    }
//
//    //Dado un inmueble, retorna el inquilino del ultimo contrato activo de ese inmueble.
//    public Inquilino obtenerInquilino(Inmueble inmueble){
//        for(Contrato contrato:contratos){
//            if(contrato.getInmueble().equals(inmueble)){
//                return contrato.getInquilino();
//            }
//        }
//        return null;
//    }
//    //Dado un Contrato, retorna los pagos de dicho contrato
//    public ArrayList<Pago> obtenerPagos(Contrato contratoVer){
//        ArrayList<Pago> temp=new ArrayList<>();
//        for(Contrato contrato:contratos){
//            if(contrato.equals(contratoVer)){
//                for(Pago pago:pagos){
//                    if(pago.getContrato().equals(contrato)){
//                        temp.add(pago);
//                    }
//                }
//            }
//            break;
//        }
//        return temp;
//    }
//    //Actualizar Perfil
//    public void actualizarPerfil(Propietario propietario){
//        int posición=propietarios.indexOf(propietario);
//        if(posición!=-1){
//            propietarios.set(posición,propietario);
//        }
//    }
//
//    //ActualizarInmueble
//    public void actualizarInmueble(Inmueble inmueble){
//        int posicion=inmuebles.indexOf(inmueble);
//        if(posicion!=-1){
//            inmuebles.set(posicion,inmueble);
//        }
//    }

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
        @GET("Inmuebles")
        Call<ArrayList<Inmueble>> Inmuebles (@Header("Authorization") String token);

        // @FormUrlEnconded
        @GET("Contratos/{id}")
        Call<Contrato> Contrato (@Path("id") int id, @Header("Authorization") String token);

        @GET("Contratos/ContratosVigentes")
        Call<List<Contrato>> ContratosVigentes (@Header("Authorization") String token);
    }




}


