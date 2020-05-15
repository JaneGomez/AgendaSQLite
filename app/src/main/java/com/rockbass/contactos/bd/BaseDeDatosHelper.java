package com.rockbass.contactos.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDatosHelper extends SQLiteOpenHelper {
    public static final String NOMBRE_BASEDEDATOS = "basededatos.db";
    public static final int VERSION = 1;

    private static final String QUERY_CREACION_TABLA_CONTACTOS =
            "CREATE TABLE "+ ContactosContract.ContactoEntry.NOMBRE_TABLA+"("
                    + ContactosContract.ContactoEntry._ID + " INTEGER PRIMARY KEY,"
                    + ContactosContract.ContactoEntry.COLUMNA_NOMBRE + " TEXT,"
                    + ContactosContract.ContactoEntry.COLUMNA_APELLIDO_PATERNO + " TEXT,"
                    + ContactosContract.ContactoEntry.COLUMNA_APELLIDO_MATERNO + " TEXT,"
                    + ContactosContract.ContactoEntry.COLUMNA_EDAD + " INTEGER,"
                    + ContactosContract.ContactoEntry.COLUMNA_TELEFONO + " TEXT,"
                    + ContactosContract.ContactoEntry.COLUMNA_EMAIL + " TEXT,"
                    + ContactosContract.ContactoEntry.COLUMNA_CONTACTO + "INTERGER);";

    private static final String QUERY_BORRADO_TABLA_CONTACTOS =
            "DROP TABLE IF EXISTS "+ ContactosContract.ContactoEntry.NOMBRE_TABLA+";";
    public BaseDeDatosHelper(final Context context){
        super(context, NOMBRE_BASEDEDATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREACION_TABLA_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_BORRADO_TABLA_CONTACTOS);
        onCreate(db);
    }
}
