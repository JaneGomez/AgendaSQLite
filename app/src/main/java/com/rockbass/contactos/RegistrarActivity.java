package com.rockbass.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.rockbass.contactos.bd.BaseDeDatosHelper;
import com.rockbass.contactos.bd.ContactosContract;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RegistrarActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApellidoPaterno, editTextApellidoMaterno,
            editTextEdad, editTextTelefono, editTextEmail;
    private Spinner spinnerContacto;
    private BaseDeDatosHelper baseDeDatosHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        baseDeDatosHelper = new BaseDeDatosHelper(this);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellidoPaterno = findViewById(R.id.editTextApellidoPaterno);
        editTextApellidoMaterno = findViewById(R.id.editTextApellidoMaterno);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextEmail = findViewById(R.id.editTextEmail);
        spinnerContacto = findViewById(R.id.spinnerContacto);


        FloatingActionButton fabRegistrar = findViewById(R.id.fab_registrar);
        fabRegistrar.setOnClickListener(
                view->{
                    SQLiteDatabase db = baseDeDatosHelper.getWritableDatabase();

                    String nombre = editTextNombre.getText().toString();
                    String apellidoPaterno = editTextApellidoPaterno.getText().toString();
                    String apellidoMaterno = editTextApellidoMaterno.getText().toString();
                    String telefono = editTextTelefono.getText().toString();
                    int edad = Integer.parseInt(editTextEdad.getText().toString());
                    String email = editTextEmail.getText().toString();

                    ContentValues values = new ContentValues();
                    values.put(ContactosContract.ContactoEntry.COLUMNA_NOMBRE, nombre);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_APELLIDO_PATERNO, apellidoPaterno);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_APELLIDO_MATERNO, apellidoMaterno);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_TELEFONO, telefono);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_EDAD, edad);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_EMAIL, email);


                    long id = db.insert(ContactosContract.ContactoEntry.NOMBRE_TABLA,null, values);
                    Toast.makeText(getApplicationContext(),"Id registro: "+ id,Toast.LENGTH_SHORT).show();
                    db.close();

                    finish();
                }
        );
    }
    private void cargarSpinner(){
        List<String> persona= new ArrayList<String>();
        for (int i = 0; i < persona.size(); i++) {
            persona.add(persona.get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,persona);
        spinnerContacto.setAdapter(adapter);
    }
    @Override
    protected void onStop() {
        super.onStop();

        baseDeDatosHelper.close();
    }
}
