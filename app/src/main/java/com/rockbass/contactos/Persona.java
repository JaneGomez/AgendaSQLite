package com.rockbass.contactos;

public class Persona {
    public int id;
    public String nombre;
    public String apellidoPaterno, apellidoMaterno;
    public int edad;
    public String telefono;
    public String email;
    public int contacto;

    public Persona(){}

    public Persona(int id,String nombre,String apellidoPaterno,String apellidoMaterno,int edad,String telefono,String email,int contacto){
        setId(id);
        setNombre(nombre);
        setApellidoPaterno(apellidoPaterno);
        setApellidoMaterno(apellidoMaterno);
        setEdad(edad);
        setTelefono(telefono);
        setEmail(email);
        setContacto(contacto);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public int getContacto() {
        return contacto;
    }
}
