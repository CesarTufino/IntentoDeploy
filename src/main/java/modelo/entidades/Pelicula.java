package modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {

    private String codigo;
    private String titulo;

    private String sinopsis;
    private int anio;
    private List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

    private static List<Pelicula> peliculas = null;

    public Pelicula() {
    }

    public Pelicula(String codigo, String titulo, int anio, String sinopsis) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anio = anio;
        this.sinopsis = sinopsis;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    // Reglas del negocio
    public void create(Pelicula p) {
        this.getPeliculas().add(p);
    }

    public List<Pelicula> getPeliculas() {
        if (peliculas == null) {
            peliculas = new ArrayList<Pelicula>();
            peliculas.add(new Pelicula("P001", "El Padrino", 1972, "América, años 40. Don Vito Corleone (Marlon Brando) es el respetado y temido jefe de una de las cinco familias de la mafia de Nueva York. Tiene cuatro hijos: Connie (Talia Shire), el impulsivo Sonny (James Caan), el pusilánime Fredo (John Cazale) y Michael (Al Pacino), que no quiere saber nada de los negocios de su padre. Cuando Corleone, en contra de los consejos de 'Il consigliere' Tom Hagen (Robert Duvall), se niega a participar en el negocio de las drogas, el jefe de otra banda ordena su asesinato. Empieza entonces una violenta y cruenta guerra entre las familias mafiosas."));
            peliculas.add(new Pelicula("P002", "Pulp Fiction", 1994,"Jules y Vincent, dos asesinos a sueldo con no demasiadas luces, trabajan para el gángster Marsellus Wallace. Vincent le confiesa a Jules que Marsellus le ha pedido que cuide de Mia, su atractiva mujer. Jules le recomienda prudencia porque es muy peligroso sobrepasarse con la novia del jefe. Cuando llega la hora de trabajar, ambos deben ponerse \"manos a la obra\". Su misión: recuperar un misterioso maletín."));
            peliculas.add(new Pelicula("P003", "Cadena perpetua", 1994, "Acusado del asesinato de su mujer, Andrew Dufresne (Tim Robbins), tras ser condenado a cadena perpetua, es enviado a la cárcel de Shawshank. Con el paso de los años conseguirá ganarse la confianza del director del centro y el respeto de sus compañeros de prisión, especialmente de Red (Morgan Freeman), el jefe de la mafia de los sobornos."));
            peliculas.add(new Pelicula("P004", "Titanic", 1997, "Un joven artista se enamora de una joven aristócrata a bordo del Titanic, en su viaje inaugural, el viaje se ve interrumpido por el trágico hundimiento del barco."));
            peliculas.add(new Pelicula("P005", "El Señor de los Anillos: La Comunidad del Anillo", 2001, "Un hobbit llamado Frodo recibe un anillo mágico que perteneció al oscuro Señor Sauron. Con la ayuda de un grupo de compañeros, Frodo debe emprender un peligroso viaje para destruir el anillo y evitar que Sauron recupere su poder."));
            peliculas.add(new Pelicula("P006","El Rey León", 1994, "Un joven león llamado Simba debe enfrentar su destino como rey después de la muerte de su padre y superar las adversidades que le presenta su tío Scar."));
            peliculas.add(new Pelicula("P007", "Coco", 2017, "Un niño llamado Miguel se embarca en un viaje en el Día de los Muertos para descubrir su verdadera herencia familiar y desentrañar los secretos detrás de la aversión de su familia a la música." ));
        }
        return peliculas;
    }

    public Pelicula getByCodigo(String codigo) {
        Pelicula p = null;
        List<Pelicula> listPeliculas = this.getPeliculas();

        for (Pelicula pelicula : listPeliculas) {
            if (pelicula.getCodigo().equals(codigo)) {
                p = pelicula;
                break;
            }
        }
        return p;
    }
}
