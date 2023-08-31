package modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private String contraseña;
    private Boolean esGerente;
    private static List<Usuario> usuarios;

    public Usuario(){

    }

    public Usuario(int id, String nombre, String contraseña, Boolean esGerente) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.esGerente = esGerente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Boolean getEsGerente() {
        return esGerente;
    }

    public void setEsGerente(Boolean esGerente) {
        this.esGerente = esGerente;
    }

    // Reglas del negocio
    public void create(Usuario u) {
        this.getUsuarios().add(u);
    }
    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
            Usuario usuario = new Usuario(1, "Juan", "juan123", true);
            Usuario usuario2 = new Usuario(2, "Carlos", "carlos123", false);

            usuarios.add(usuario);
            usuarios.add(usuario2);

        }
        return usuarios;
    }

    public Usuario autorizar(String nombre, String contraseña){
        Usuario u = null;
        List<Usuario> listUsuarios = this.getUsuarios();
        for (Usuario usuario: listUsuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contraseña) ) {
                u = usuario;
                break;
            }
        }
        return u;
    }
}
