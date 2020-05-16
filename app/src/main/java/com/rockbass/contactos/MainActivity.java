package com.rockbass.contactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Person;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rockbass.contactos.bd.BaseDeDatosHelper;
import com.rockbass.contactos.bd.ContactosContract;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BaseDeDatosHelper baseDeDatosHelper;
    private AdapterPersona adapterPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAgregar = findViewById(R.id.fab_agregar);
        buttonAgregar.setOnClickListener(
                (view)->{
                    Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
                    startActivity(intent);
                });

        baseDeDatosHelper = new BaseDeDatosHelper(MainActivity.this);
        adapterPersona = new AdapterPersona();
        consultarContactos();
        RecyclerView recyclerView = findViewById(R.id.recyclerview_contactos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterPersona);

    }

    private void consultarContactos() {

        SQLiteDatabase db = baseDeDatosHelper.getReadableDatabase();
        String[] columnas = {
                ContactosContract.ContactoEntry._ID,
                ContactosContract.ContactoEntry.COLUMNA_NOMBRE,
                ContactosContract.ContactoEntry.COLUMNA_APELLIDO_PATERNO,
                ContactosContract.ContactoEntry.COLUMNA_APELLIDO_MATERNO,
                ContactosContract.ContactoEntry.COLUMNA_TELEFONO,
                ContactosContract.ContactoEntry.COLUMNA_EDAD,
                ContactosContract.ContactoEntry.COLUMNA_EMAIL,
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
        List<Persona> personas = new ArrayList<>();
        do{
            Persona persona = new Persona();

            persona.id = cursor.getInt(cursor.getColumnIndex(ContactosContract.ContactoEntry._ID));
            persona.nombre = cursor.getString(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_NOMBRE));
            persona.apellidoPaterno = cursor.getString(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_APELLIDO_PATERNO));
            persona.apellidoMaterno = cursor.getString(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_APELLIDO_MATERNO));
            persona.telefono = cursor.getString(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_TELEFONO));
            persona.edad = cursor.getInt(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_EDAD));
            persona.email = cursor.getString(cursor.getColumnIndex(ContactosContract.ContactoEntry.COLUMNA_EMAIL));

            personas.add(persona);
        }while(cursor.moveToNext());
        cursor.close();

        adapterPersona.setData(personas);
        adapterPersona.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        consultarContactos();

    }
    @Override
    protected void onStop() {
        super.onStop();

        baseDeDatosHelper.close();
    }

    class AdapterPersona extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private final List<Persona> personas;

        public AdapterPersona(){
            personas = new ArrayList<>();
        }

        public void setData(final List<Persona> personas){
            this.personas.clear();
            this.personas.addAll(personas);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.persona_card, parent, false);

            RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {};

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            View view = holder.itemView;

            Persona persona = personas.get(position);

            TextView nombreCompleto, edad, telefono, email;
            nombreCompleto = view.findViewById(R.id.textViewNombreCompleto);
            edad = view.findViewById(R.id.textViewEdad);
            telefono = view.findViewById(R.id.textViewTelefono);
            email = view.findViewById(R.id.textViewEmail);

            nombreCompleto.setText(
                    String.format("%s %s %s",
                            persona.nombre,
                            persona.apellidoPaterno,
                            persona.apellidoMaterno)
            );

            edad.setText(Integer.toString(persona.edad));

            telefono.setText(persona.telefono);

            email.setText(persona.email);

            view.setOnClickListener(
                    (v)->{
                        Intent intent = new Intent(MainActivity.this, ModificarActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Posicion",position);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
            );
        }

        @Override
        public int getItemCount() {
            return personas.size();
        }
    }
}
