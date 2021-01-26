import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Un objeto de esta clase permite registrar estudiantes de un
 * curso (leyendo la informaci�n de un fichero de texto) y 
 * emitir listados con las faltas de los estudiantes, justificar faltas, 
 * anular matr�cula dependiendo del n� de faltas, .....
 *
 */
public class GestorFaltas {
    private Estudiante[] curso;
    private int pos;

    public GestorFaltas(int n) {
        curso = new Estudiante[n];
        pos = 0;
    }

    /**
     * Devuelve true si el array de estudiantes est� completo,
     * false en otro caso
     */
    public boolean cursoCompleto() {
        return pos == curso.length;
    }

    /**
     *    A�ade un nuevo estudiante solo si el curso no est� completo y no existe ya otro
     *    estudiante igual (con los mismos apellidos). 
     *    Si no se puede a�adir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El estudiante se a�ade de tal forma que queda insertado en orden alfab�tico de apellidos
     *    (de menor a mayor)
     *    !!OJO!! No hay que ordenar ni utilizar ning�n algoritmo de ordenaci�n
     *    Hay que insertar en orden 
     *    
     */
    public void addEstudiante(Estudiante nuevo) {
        // if (pos == 0) {
        // curso[0] = nuevo;
        // pos++;
        // }
        if (!cursoCompleto()) {
            int aux = buscarEstudiante(nuevo.getApellidos());
            if (aux == -1) {
                int n = 0;
                while (n < pos && nuevo.getApellidos().compareToIgnoreCase(curso[n].getApellidos()) > 0) {
                    n++;
                }
                for (int i = pos; i > n; i--) {
                    curso[i] = curso[i - 1];
                }
                curso[n] = nuevo;
                pos++;
            }
            else {
                System.out.println("Ya est� registrado el estudiante " +
                    nuevo.getApellidos() + " " + nuevo.getNombre() + " en el curso.");
            }
        }
        else {
            System.out.println("El curso est� completo.");
        }
    }

    /**
     * buscar un estudiante por sus apellidos
     * Si est� se devuelve la posici�n, si no est� se devuelve -1
     * Es indiferente may�sculas / min�sculas
     * Puesto que el curso est� ordenado por apellido haremos la b�squeda m�s
     * eficiente
     *  
     */
    public int buscarEstudiante(String apellidos) {
        int izq = 0;
        int der = pos - 1;
        int n;
        while (izq <= der && izq >= 0) {
            n = (izq + der) / 2;
            String str = curso[n].getApellidos();
            if (apellidos.equalsIgnoreCase(str)) {
                return n;
            }
            else if (apellidos.compareToIgnoreCase(str) < 0) {
                der = n - 1;
            }
            else {
                izq = n +    1;
            }
        }
        return -1;
    }

    /**
     * Representaci�n textual del curso
     * Utiliza StringBuilder como clase de apoyo.
     *  
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Relaci�n de estudiantes (" + pos + ")\n");
        for (int i = 0; i < pos; i++) {
            sb.append(curso[i].toString());
            sb.append("------------------\n");
        }
        return sb.toString();

    }

    /**
     *  Se justifican las faltas del estudiante cuyos apellidos se proporcionan
     *  El m�todo muestra un mensaje indicando a qui�n se ha justificado las faltas
     *  y cu�ntas
     *  
     *  Se asume todo correcto (el estudiante existe y el n� de faltas a
     *  justificar tambi�n)
     */
    public void justificarFaltas(String apellidos, int faltas) {
        int aux = buscarEstudiante(apellidos);
        curso[aux].justificar(faltas);
        System.out.println("Se han justificado " + faltas +
            " faltas a " + curso[aux].getApellidos() + ", " +
            curso[aux].getNombre());
    }

    /**
     * ordenar los estudiantes de mayor a menor n� de faltas injustificadas
     * si coinciden se tiene en cuenta las justificadas
     * M�todo de selecci�n directa
     */
    public void ordenar() {
        for (int i = 0; i < curso.length; i++) {
            int max = i;
            for (int j = i + 1; j < pos; j++) {
                if (curso[j].getFaltasNoJustificadas() > curso[max].getFaltasNoJustificadas()) {
                    max = j;
                }
            }
            Estudiante aux = curso[max];
            curso[max] = curso[i];
            curso[i] = aux;
        }
    }

    /**
     * anular la matr�cula (dar de baja) a 
     * aquellos estudiantes con 30 o m�s faltas injustificadas
     */
    public void anularMatricula() {
        boolean encontrado = false;
        int izq = 0;
        int der = pos - 1;
        int n = 0;
        while (izq <= der && !encontrado) {
            n = (izq + der) / 2;
            String str = curso[n].getApellidos();
            if (curso[n].getFaltasNoJustificadas() == 30) {
                if (curso[n - 1].getFaltasNoJustificadas() < 30) {
                    encontrado = true;
                }
                else {
                    der = n - 1;
                }
            }
            else if (curso[n].getFaltasNoJustificadas() < 30) {
                der = n - 1;
            }
            else {
                izq = n + 1;
            }
        }
        for (int i = n; i < pos - 1; i++) {
            curso[i - n] = curso[i + 1];
        }
        pos -= n + 1;
    }

    /**
     * Lee de un fichero de texto los datos de los estudiantes
     *   con ayuda de un objeto de la  clase Scanner
     *   y los guarda en el array. 
     */
    public void leerDeFichero() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("estudiantes.txt"));
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                Estudiante estudiante = new Estudiante(linea);
                this.addEstudiante(estudiante);

            }

        }
        catch (IOException e) {
            System.out.println("Error al leer del fichero");
        }
        finally {
            if (sc != null) {
                sc.close();
            }
        }

    }

}
