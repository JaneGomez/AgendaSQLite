package com.rockbass.contactos;

import android.provider.BaseColumns;

public class ContactosContract {

    public ContactosContract(){}

    public static class ContactoEntry implements BaseColumns{
        public static final String NOMBRE_TABLA = "Contactos";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_APELLIDO_PATERNO = "apellidoPaterno";
        public static final String COLUMNA_APELLIDO_MATERNO = "apellidoMaterno";
        public static final String COLUMNA_TELEFONO = "telefono";
        public static final String COLUMNA_EDAD = "edad";
        public static final String COLUMNA_EMAIL = "email";
        public static final String COLUMNA_CONTACTO = "contacto";
    }
}
