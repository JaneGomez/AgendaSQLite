package com.rockbass.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RegistrarActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApellidoPaterno, editTextApellidoMaterno,
            editTextEdad, editTextTelefono, editTextEmail;
    private Spinner spinnerContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellidoPaterno = findViewById(R.id.editTextApellidoPaterno);
        editTextApellidoMaterno = findViewById(R.id.editTextApellidoMaterno);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextEmail = findViewById(R.id.editTextEmail);
        spinnerContacto = findViewById(R.id.spinnerContacto);
        cargarSpinner();


        FloatingActionButton fabRegistrar = findViewById(R.id.fab_registrar);
        fabRegistrar.setOnClickListener(
                view->{
                    Persona persona = new Persona();
                    persona.nombre = editTextNombre.getText().toString();
                    persona.apellidoPaterno = editTextApellidoPaterno.getText().toString();
                    persona.apellidoMaterno = editTextApellidoMaterno.getText().toString();
                    persona.edad = Integer.parseInt(editTextEdad.getText().toString());
                    persona.telefono = editTextTelefono.getText().toString();
                    persona.email = editTextEmail.getText().toString();
                    persona.contacto = spinnerContacto.getSelectedItemPosition();

                    Memory.PERSONAS.add(persona);

                    finish();
                }
        );
    }
    private void cargarSpinner(){
        List<String> persona= new ArrayList<String>();
        for(Persona p: Memory.PERSONAS){
            persona.add(p.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,persona);
        spinnerContacto.setAdapter(adapter);
    }
}
