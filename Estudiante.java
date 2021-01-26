/**
 * Un objeto de esta clase guarda la información de un estudiante
 *
 */
public class Estudiante {
    private final static String SEPARADOR = ",";
    private String nombre;
    private String apellidos;
    private int faltasNoJustificadas;
    private int faltasJustificadas;

    /**
     *  
     *  Inicializa los atributos a partir de la información recibida
     *  Esta información se encuentra en lineaDatos
     *  (ver enunciado) 
     *  
     */
    public Estudiante(String lineaDatos) {
        String[] aux = lineaDatos.split(SEPARADOR);
        String auxn = aux[0];
        this.apellidos = aux[1].toUpperCase().trim();
        this.faltasNoJustificadas = Integer.parseInt(aux[2].trim());
        this.faltasJustificadas = Integer.parseInt(aux[3].trim());
        aux = auxn.trim().split("\\s+");
        auxn = "";
        for (int i = 0; i < aux.length - 1; i++) {
            auxn += aux[i].toUpperCase().charAt(0) + ". ";
        }
        auxn += aux[aux.length - 1].toUpperCase().charAt(0) + aux[aux.length - 1].substring(1);
        this.nombre = auxn;
    }

    /**
     * accesor para el nombre completo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * mutador para el nombre
     *  
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * accesor para los apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *  mutador para los apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * accesor para el nº de faltas no justificadas
     */
    public int getFaltasNoJustificadas() {
        return faltasNoJustificadas;
    }

    /**
     * mutador para el nº de faltas no justificadas
     */
    public void setFaltasNoJustificadas(int faltasNoJustificadas) {
        this.faltasNoJustificadas = faltasNoJustificadas;
    }

    /**
     * accesor para el nº de faltas justificadas
     */
    public int getFaltasJustificadas() {
        return faltasJustificadas;
    }

    /**
     *  mutador para el nº de faltas justificadas
     */
    public void setFaltasJustificadas(int faltasJustificadas) {
        this.faltasJustificadas = faltasJustificadas;
    }

    /**
     *  se justifican n faltas que hasta el momento eran injustificadas
     *  Se asume n correcto
     */
    public void justificar(int n) {
        this.faltasNoJustificadas -= n;
        this.faltasJustificadas += n;
    }

    /**
     * Representación textual del estudiante
     * (ver enunciado)
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-25s","Apellidos y Nombre:"));
        sb.append(apellidos + ", " + nombre + "\n");
        sb.append(String.format("%-25s","Faltas No Justificadas:"));
        sb.append(faltasNoJustificadas + "\n");
        sb.append(String.format("%-25s","Faltas Justificadas:"));
        sb.append(faltasJustificadas + "\n");
        sb.append(String.format("%-25s","Apercibimientos:"));
        if (faltasNoJustificadas > 10) {
            sb.append("DIEZ ");
        }
        if (faltasNoJustificadas > 20) {
            sb.append("VEINTE ");
        }
        if (faltasNoJustificadas > 30) {
            sb.append("TREINTA ");
        }
        if (faltasNoJustificadas < 10) {
            sb.append("Sin apercibimientos");
        }
        sb.append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        Estudiante e1 = new Estudiante("  ander ibai  ,  Ruiz Sena , 12, 23");
        System.out.println(e1);
        System.out.println();
        Estudiante e2 = new Estudiante(
                " pedro josé   andrés  ,  Troya Baztarrica , 42, 6 ");
        System.out.println(e2);
        System.out.println();
        Estudiante e3 = new Estudiante("  Javier  ,  Suescun  Andreu , 39, 9 ");
        System.out.println(e3);
        System.out.println();
        Estudiante e4 = new Estudiante("julen, Duque Puyal, 5, 55");
        System.out.println(e4);
        System.out.println();

        System.out.println("---------------------------------");
        e1.justificar(3);
        System.out.println(e1);
        System.out.println();

        System.out.println("---------------------------------");

        e3.justificar(12);
        System.out.println(e3);
        System.out.println();

    }

}
