package com.example.alex.basesqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Alex on 2/8/2016.
 */

public class Login extends AppCompatActivity {
    Button ingresar;
    EditText etc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ingresar = (Button)findViewById(R.id.BTIngresar);
        etc=(EditText)findViewById(R.id.ETCedula);

    }
    public void Ingresar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        String ced = etc.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select nombre,apellido,telefono,correo,sexo from persona where cedula=" + ced, null);
        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)

            Toast.makeText(this, "nombre : "+fila.getString(0)+" apellido: "+fila.getString(1)+" telefono: "+fila.getString(2)+
                            " correo: "+fila.getString(3),
                    Toast.LENGTH_SHORT).show();


        } else
            Toast.makeText(this, "No existe una persona con dicha cedula" ,
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void Registrarse(View v){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
