package com.rockbass.contactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAgregar = findViewById(R.id.fab_agregar);
        buttonAgregar.setOnClickListener(
                (view)->{
                    Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
                    startActivity(intent);
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_contactos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdapterPersona adapterPersona = new AdapterPersona();
        recyclerView.setAdapter(adapterPersona);
    }

    class AdapterPersona extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.persona_card, parent, false);

            return new RecyclerView.ViewHolder(view) {};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            View view = holder.itemView;

            Persona persona = Memory.PERSONAS.get(position);

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
            return Memory.PERSONAS.size();
        }
    }
}
