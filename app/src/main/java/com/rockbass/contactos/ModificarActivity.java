package com.rockbass.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ModificarActivity extends AppCompatActivity {

    EditText editTextNombre, editTextApellidoPaterno, editTextApellidoMaterno,
            editTextEdad, editTextTelefono, editTextEmail;
    Spinner spinnerContacto;
    Integer contacto,position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextApellidoPaterno = (EditText) findViewById(R.id.editTextApellidoPaterno);
        editTextApellidoMaterno = (EditText) findViewById(R.id.editTextApellidoMaterno);
        editTextEdad = (EditText) findViewById(R.id.editTextEdad);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        spinnerContacto = (Spinner) findViewById(R.id.spinnerContacto);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
            position = bundle.getInt("Posicion");

            Persona persona = Memory.PERSONAS.get(position);

            editTextNombre.setText(persona.nombre);
            editTextApellidoPaterno.setText(persona.apellidoPaterno);
            editTextApellidoMaterno.setText(persona.apellidoMaterno);
            editTextEdad.setText(Integer.toString(persona.edad));
            editTextTelefono.setText(persona.telefono);
            editTextEmail.setText(persona.email);
            contacto = persona.contacto;
            cargarSpinner(contacto);
        }

        FloatingActionButton fabModificar = findViewById(R.id.fab_modificar);
        fabModificar.setOnClickListener(
                view->{
                    Persona persona = Memory.PERSONAS.get(position);
                    persona.setNombre(editTextNombre.getText().toString());
                    persona.setApellidoPaterno(editTextApellidoPaterno.getText().toString());
                    persona.setApellidoMaterno(editTextApellidoMaterno.getText().toString());
                    persona.setEdad(Integer.parseInt(editTextEdad.getText().toString()));
                    persona.setTelefono(editTextTelefono.getText().toString());
                    persona.setEmail(editTextEmail.getText().toString());
                    persona.setContacto(spinnerContacto.getSelectedItemPosition());

                    finish();
                }
        );
    }
    private void cargarSpinner(int pos){
        List<String> persona= new ArrayList<String>();
        Persona per = Memory.PERSONAS.get(pos);
        persona.add(per.getNombre());
        for(Persona p: Memory.PERSONAS){
            persona.add(p.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,persona);
        spinnerContacto.setAdapter(adapter);
    }
}
