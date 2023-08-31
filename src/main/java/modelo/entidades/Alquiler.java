    package modelo.entidades;

    import java.text.DateFormat;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.List;

    public class Alquiler {
        private Long numeroAlquiler;
        private int diasDeAlquiler;
        private double precioTotal;
        private Cliente cliente;
        private List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
        private static long contador = 0;
        private Calendar fechaDevolucion;

        private String strFechaDevolucion;

        private boolean estadoFinalizacion;

        private static List<Alquiler> alquileres = null;

        public Alquiler() {
        }

        public Alquiler(long numero, int diasDeAlquiler, double precioTotal, Cliente cliente, List<Ejemplar> ejemplares) {
            this.numeroAlquiler = numero;
            this.diasDeAlquiler = diasDeAlquiler;
            this.precioTotal = precioTotal;
            this.cliente = cliente;
            this.ejemplares = ejemplares;
            this.fechaDevolucion = Calendar.getInstance();
            fechaDevolucion.add(Calendar.DAY_OF_YEAR, diasDeAlquiler);
            DateFormat formateador = new SimpleDateFormat("dd/M/yy");
            this.strFechaDevolucion = formateador.format(fechaDevolucion.getTime());
            this.estadoFinalizacion = false;
        }

        public Long getNumeroAlquiler() {
            return numeroAlquiler;
        }

        public void setNumeroAlquiler(Long numeroAlquiler) {
            this.numeroAlquiler = numeroAlquiler;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

        public List<Ejemplar> getEjemplares() {
            return ejemplares;
        }

        public void setEjemplares(List<Ejemplar> ejemplares) {
            this.ejemplares = ejemplares;
        }

        public int getDiasDeAlquiler() {
            return diasDeAlquiler;
        }

        public void setDiasDeAlquiler(int diasDeAlquiler) {
            this.diasDeAlquiler = diasDeAlquiler;
        }

        public double getPrecioTotal() {
            return precioTotal;
        }

        public void setPrecioTotal(double precioTotal) {
            this.precioTotal = precioTotal;
        }

        public String getStrFechaDevolucion() {
            return strFechaDevolucion;
        }

        public void setStrFechaDevolucion(String strFechaDevolucion) {
            this.strFechaDevolucion = strFechaDevolucion;
        }

        public Calendar getFechaDevolucion() {
            return fechaDevolucion;
        }

        public void setFechaDevolucion(Calendar fechaDevolucion) {
            this.fechaDevolucion = fechaDevolucion;
        }

        public boolean isEstadoFinalizacion() {
            return estadoFinalizacion;
        }

        public void setEstadoFinalizacion(boolean estadoFinalizacion) {
            this.estadoFinalizacion = estadoFinalizacion;
        }

        // Reglas del negocio

        public void create(Alquiler a) {
            this.getAlquileres().add(a);
        }

        public List<Alquiler> getAlquileres () {
            if(alquileres == null){
                alquileres = new ArrayList<Alquiler>();
            }
            return alquileres;
        }

        public Alquiler getByNumeroALquiler(Long numeroAlquiler){
            Alquiler a = null;
            List<Alquiler> listAlquileres = this.getAlquileres();

            for(Alquiler alquiler: listAlquileres){
                if(alquiler.getNumeroAlquiler() == numeroAlquiler){
                    a = alquiler;
                    break;
                }
            }
            return a;
        }


        public Alquiler alquilar(int diasAlquiler, List<Ejemplar> ejemplares, Cliente cliente) {
            double precioTotal = calcularPrecioTotal(ejemplares, diasAlquiler);
            cambiarDisponibilidadEjemplares(ejemplares);
            aumentarPuntosFidelidad(ejemplares, cliente);
            precioTotal = redondearPrecioDosDecimales(precioTotal);
            return new Alquiler(++contador, diasAlquiler, precioTotal, cliente, ejemplares);
        }

        public Alquiler alquilarFidelidad(int diasAlquiler, List<Ejemplar> ejemplares, Cliente cliente) {
            double precioTotal = calcularPrecioTotal(ejemplares, diasAlquiler);
            if (verificarPuntosFidelidadSuficientes(cliente.getPuntosPorFidelidad())) {
                precioTotal = aplicarDescuento(precioTotal, cliente);
            }
            cambiarDisponibilidadEjemplares(ejemplares);
            aumentarPuntosFidelidad(ejemplares, cliente);
            precioTotal = redondearPrecioDosDecimales(precioTotal);
            return new Alquiler(++contador, diasAlquiler, precioTotal, cliente, ejemplares);
        }

        private double calcularPrecioTotal(List<Ejemplar> ejemplares, int diasAlquiler) {
            double precioPorDia = 0;
            for (Ejemplar ejemplar : ejemplares) {
                precioPorDia += ejemplar.getCostoPorDia();
            }
            return precioPorDia * diasAlquiler;
        }

        private void cambiarDisponibilidadEjemplares(List<Ejemplar> ejemplares) {
            for (Ejemplar ejemplar : ejemplares) {
                ejemplar.setEstadoDisponibilidad(false);
            }
        }

        private static void aumentarPuntosFidelidad(List<Ejemplar> ejemplares, Cliente cliente) {
            for (Ejemplar ignored : ejemplares){
                if (cliente.getPuntosPorFidelidad() < 100) {
                    sumarPuntajeAlquiler(cliente);
                }
            }
        }

        public static void sumarPuntajeAlquiler(Cliente cliente) {
            int puntajeActual = cliente.getPuntosPorFidelidad();
            int puntajeSumado = 5;
            int nuevoPuntaje = puntajeActual + puntajeSumado;
            cliente.setPuntosPorFidelidad(nuevoPuntaje);
        }

        private static double redondearPrecioDosDecimales(double precioTotal) {
            precioTotal = precioTotal *100;
            precioTotal = Math.round(precioTotal);
            precioTotal = precioTotal /100;
            return precioTotal;
        }

        private boolean verificarPuntosFidelidadSuficientes(int puntosPorFidelidad) {
            return puntosPorFidelidad >= 50;
        }

        public static double aplicarDescuento(double precioActual, Cliente cliente) {
            double precioNuevo = precioActual - (precioActual * 0.20);
            cliente.setPuntosPorFidelidad(cliente.getPuntosPorFidelidad() - 50);

            return precioNuevo;
        }

        public void devolver(Alquiler alquiler, List<Ejemplar> ejemplares, Cliente cliente, boolean tienePercanse) {
            if (tienePercanse){
                cliente.setPuntosPorFidelidad(0);
            }
            for (Ejemplar ejemplar : ejemplares) {
                ejemplar.setEstadoDisponibilidad(true);
            }
            for (Ejemplar ejemplar : alquiler.getEjemplares()){
                if (!ejemplar.getEstadoDisponibilidad()){
                    return;
                }
            }
            alquiler.setEstadoFinalizacion(true);
        }

        public List<Alquiler> getAlquileresNoDevueltos(){
            List<Alquiler> alquileresNoDevueltos = new ArrayList<>();
            for(Alquiler a: this.getAlquileres()){
                if (a.estadoFinalizacion){
                    continue;
                }
                for(Ejemplar e: a.getEjemplares()){
                    if(!e.getEstadoDisponibilidad() && !alquileresNoDevueltos.contains(a)){
                        alquileresNoDevueltos.add(a);
                    }
                }
            }
            return alquileresNoDevueltos;
        }

        public List<Cliente> getClientesByAlquileresNoDevueltos(){
            List<Cliente> clientesAlquileresNoDevueltos = new ArrayList<>();
            for(Alquiler a: this.getAlquileres()){
                for(Ejemplar e: a.getEjemplares()){
                    if(!e.getEstadoDisponibilidad() && !clientesAlquileresNoDevueltos.contains(a.getCliente())){
                        clientesAlquileresNoDevueltos.add(a.getCliente());
                    }
                }
            }
            return clientesAlquileresNoDevueltos;
        }

        public boolean esMenorIgualASiete(int diasDeAlquiler){
            return (diasDeAlquiler <= 7 && diasDeAlquiler > 0)? true : false;
        }
    }
