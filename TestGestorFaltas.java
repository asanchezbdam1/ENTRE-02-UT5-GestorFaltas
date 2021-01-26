/**
 * Punto de entrada a la aplicación
 */
public class TestGestorFaltas {
    /**
     * Se acepta como argumento del main() el nº máximo de estudiantes
     * y una vez creado el gestor de faltas se muestra la información solicitada
     * (ver enunciado)
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error en argumentos\nSintaxis: java TestGestorFaltas <max_estudiantes>");
        }
        else {
            GestorFaltas gestor = new GestorFaltas(Integer.parseInt(args[0]));
            gestor.leerDeFichero();
            System.out.println(gestor.toString());
            gestor.justificarFaltas("IRISO FLAMARIQUE", 6);
            gestor.ordenar();
            System.out.println(gestor.toString());
            System.out.println("Tras anular: ");
            gestor.anularMatricula();
            System.out.println(gestor.toString());
        }
    }

}
