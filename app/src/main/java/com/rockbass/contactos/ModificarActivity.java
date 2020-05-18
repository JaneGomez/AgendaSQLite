package com.rockbass.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rockbass.contactos.bd.BaseDeDatosHelper;
import com.rockbass.contactos.bd.ContactosContract;

import java.util.ArrayList;
import java.util.List;

public class ModificarActivity extends AppCompatActivity {

    EditText editTextNombre, editTextApellidoPaterno, editTextApellidoMaterno,
            editTextEdad, editTextTelefono, editTextEmail;
    Spinner spinnerContacto;
    int position, idModificar;
    private BaseDeDatosHelper baseDeDatosHelper;
    ArrayList<String> listaSpinner;
    ArrayList<Persona> personaList;
    Persona p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        baseDeDatosHelper = new BaseDeDatosHelper(this);

        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextApellidoPaterno = (EditText) findViewById(R.id.editTextApellidoPaterno);
        editTextApellidoMaterno = (EditText) findViewById(R.id.editTextApellidoMaterno);
        editTextEdad = (EditText) findViewById(R.id.editTextEdad);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        spinnerContacto = (Spinner) findViewById(R.id.spinnerContacto);
        consultarContactos();

        FloatingActionButton fabModificar = findViewById(R.id.fab_modificar);
        fabModificar.setOnClickListener(
                view -> {
                    SQLiteDatabase db = baseDeDatosHelper.getWritableDatabase();

                    String nombre = editTextNombre.getText().toString();
                    String apellidoPaterno = editTextApellidoPaterno.getText().toString();
                    String apellidoMaterno = editTextApellidoMaterno.getText().toString();
                    String telefono = editTextTelefono.getText().toString();
                    int edad = Integer.parseInt(editTextEdad.getText().toString());
                    String email = editTextEmail.getText().toString();
                    int contacto = spinnerContacto.getSelectedItemPosition();
                    if(contacto == 0){
                        contacto = p.contacto;}

                    ContentValues values = new ContentValues();
                    values.put(ContactosContract.ContactoEntry.COLUMNA_NOMBRE, nombre);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_APELLIDO_PATERNO, apellidoPaterno);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_APELLIDO_MATERNO, apellidoMaterno);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_TELEFONO, telefono);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_EDAD, edad);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_EMAIL, email);
                    values.put(ContactosContract.ContactoEntry.COLUMNA_CONTACTO, contacto);

                    db.update(ContactosContract.ContactoEntry.NOMBRE_TABLA,values,"_id="+idModificar,null);
                    Toast.makeText(getApplicationContext(), "Contacto modificado ", Toast.LENGTH_SHORT).show();

                    db.close();


                    finish();
                }
        );
    }

    private void consultarContactos() {
        SQLiteDatabase db = baseDeDatosHelper.getReadableDatabase();
        Persona persona = null;
        String[] columnas = {
                ContactosContract.ContactoEntry._ID,
                ContactosContract.ContactoEntry.COLUMNA_NOMBRE,
                ContactosContract.ContactoEntry.COLUMNA_APELLIDO_PATERNO,
                ContactosContract.ContactoEntry.COLUMNA_APELLIDO_MATERNO,
                ContactosContract.ContactoEntry.COLUMNA_TELEFONO,
                ContactosContract.ContactoEntry.COLUMNA_EDAD,
                ContactosContract.ContactoEntry.COLUMNA_EMAIL,
                ContactosContract.ContactoEntry.COLUMNA_CONTACTO,
        };
        Cursor cursor = db.query(ContactosContract.ContactoEntry.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null);

        if (!cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return;
        }
        personaList = new ArrayList<Persona>();
        do {
            persona = new Persona();

            persona.id = cursor.getInt(cursor.getColumnIndex(ContactosContract.ContactoEntry._ID));
            persona.nombre = cursor.getString(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_NOMBRE));
            persona.apellidoPaterno = cursor.getString(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_APELLIDO_PATERNO));
            persona.apellidoMaterno = cursor.getString(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_APELLIDO_MATERNO));
            persona.telefono = cursor.getString(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_TELEFONO));
            persona.edad = cursor.getInt(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_EDAD));
            persona.email = cursor.getString(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_EMAIL));
            persona.contacto = cursor.getInt(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_CONTACTO));

            personaList.add(persona);
        } while (cursor.moveToNext());

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            position = bundle.getInt("Posicion");
            p = personaList.get(position);
            idModificar= p.id;
            editTextNombre.setText(p.nombre);
            editTextApellidoPaterno.setText(p.apellidoPaterno);
            editTextApellidoMaterno.setText(p.apellidoMaterno);
            editTextEdad.setText(Integer.toString(p.edad));
            editTextTelefono.setText(p.telefono);
            editTextEmail.setText(p.email);
            cargarSpinner(p.contacto);

            cursor.close();
        }
    }
    private void cargarSpinner(int pos){
        listaSpinner = new ArrayList<String>();
        if(pos == 0){
            listaSpinner.add("Seleccione");
        }else{
            for (int i = 0; i < personaList.size(); i++){
                if(pos == personaList.get(i).getId()){
                    listaSpinner.add(personaList.get(i).getId()+"-"+personaList.get(i).getNombre()+" "+personaList.get(i).getApellidoPaterno());
                }
            }
        }

        for (int i = 0; i < personaList.size(); i++) {
            listaSpinner.add(personaList.get(i).getId()+"-"+personaList.get(i).getNombre()+" "+personaList.get(i).getApellidoPaterno());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaSpinner);
        spinnerContacto.setAdapter(adapter);
    }
}
