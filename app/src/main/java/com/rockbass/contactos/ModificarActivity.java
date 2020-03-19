package com.rockbass.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class ModificarActivity extends AppCompatActivity {

    EditText editTextNombre, editTextApellidoPaterno, editTextApellidoMaterno,
            editTextEdad, editTextTelefono, editTextEmail;
    Spinner spinnerContacto;

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
            int position = bundle.getInt("posicion");
            Persona persona = Memory.PERSONAS.get(position);

            editTextNombre.setText();
        }
    }
}
