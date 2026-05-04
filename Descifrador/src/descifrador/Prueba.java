package descifrador;

public class Prueba {

    public static void main(String[] args) {
        Descifrador d = new Descifrador();

        // Mostrar tabla inicial
        System.out.println("Tabla inicial:");
        System.out.println(d);

        // Rellenar toda la tabla
        d.rellenarTabla('X');
        System.out.println("Tabla rellena con X:");
        System.out.println(d);

        // Rellenar fila completa
        d.rellenarFila('A', 0);
        System.out.println("Fila 0 con A:");
        System.out.println(d);

        // Rellenar fila en rango
        d.rellenarFila('B', 1, 5);
        System.out.println("Fila 1 de 0 a 5 con B:");
        System.out.println(d);

        // Rellenar fila con inicio, fin y paso
        d.rellenarFila('C', 2, 0, 19, 2);
        System.out.println("Fila 2 con C cada 2 posiciones:");
        System.out.println(d);

        // Rellenar fila con palabra
        d.rellenarFilaPalabra(3, "Hola Mundo");
        System.out.println("Fila 3 con palabra:");
        System.out.println(d);

        // Rellenar fila con palabra y paso
        d.rellenarFilaPalabra(4, "Java Rocks", 2);
        System.out.println("Fila 4 con palabra cada 2 posiciones:");
        System.out.println(d);

        // Rellenar columna completa
        d.rellenarColumna('D', 0);
        System.out.println("Columna 0 con D:");
        System.out.println(d);

        // Rellenar columna en rango
        d.rellenarColumna('E', 1, 10, 0);
        System.out.println("Columna 0 de 1 a 10 con E:");
        System.out.println(d);

        // Rellenar columna con palabra
        d.rellenarColumnaPalabra(1, "Vertical");
        System.out.println("Columna 1 con palabra:");
        System.out.println(d);

        // Rellenar columna con palabra y paso
        d.rellenarColumnaPalabra(2, "Paso", 2);
        System.out.println("Columna 2 con palabra cada 2 posiciones:");
        System.out.println(d);

        // Rellenar diagonal principal
        d.rellenarDP('P', 0, 19);
        System.out.println("Diagonal principal con P:");
        System.out.println(d);

        // Rellenar diagonal secundaria
        d.rellenarDS('S');
        System.out.println("Diagonal secundaria con S:");
        System.out.println(d);

        // ============================
        // PRUEBAS DE EXTRACCIÓN
        // ============================

        System.out.println("Extraer fila 0 completa:");
        System.out.println(d.extraerFila(0, 0, 19, 1));

        System.out.println("Extraer fila 1 inversa:");
        System.out.println(d.extraerFila(1, 19, 0, -1));
    }
}
