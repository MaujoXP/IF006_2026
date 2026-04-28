
import Cola.Cola;
import Pila.Pila;
import javax.swing.JOptionPane;

public class InterfazPilasColas {

    Pila[] pilas = new Pila[10];
    Cola[] colas = new Cola[10];

    public int buscarLibre(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public String listar(Object[] arr) {
        String s = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                s += i + " | ";
            }
        }
        return s.equals("") ? "(vacío)" : s;
    }

    public int pedirEntero(String msg) {
        while (true) {
            try {
                String in = JOptionPane.showInputDialog(msg);
                if (in == null) {
                    return -1;
                }
                return Integer.parseInt(in.trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Dato inválido");
            }
        }
    }

    public int menu() {
        String m = "PILAS Y COLAS\n"
                + "1. Crear pila\n"
                + "2. Crear cola\n"
                + "3. Insertar en pila\n"
                + "4. Insertar en cola\n"
                + "5. Mostrar pila\n"
                + "6. Mostrar cola\n"
                + "7. Eliminar de pila\n"
                + "8. Eliminar de cola\n"
                + "0. Salir";

        try {
            String op = JOptionPane.showInputDialog(m);
            if (op == null) {
                return 0;
            }
            return Integer.parseInt(op);
        } catch (Exception e) {
            return -1;
        }
    }

    public void crearPilaCola() {

        int opcion;
        do {
            opcion = menu();

            switch (opcion) {

                case 1: { // Crear PILA
                    int pos = buscarLibre(pilas);
                    if (pos == -1) {
                        JOptionPane.showMessageDialog(null, "No hay más espacio para pilas");
                        break;
                    }

                    int tam = pedirEntero("Tamaño de la pila:");
                    if (tam <= 0) {
                        JOptionPane.showMessageDialog(null, "Tamaño inválido");
                        break;
                    }

                    pilas[pos] = new Pila(tam);
                    JOptionPane.showMessageDialog(null, "Pila creada en posición automática: " + pos);
                    break;
                }

                case 2: { // Crear COLA
                    int pos = buscarLibre(colas);
                    if (pos == -1) {
                        JOptionPane.showMessageDialog(null, "No hay más espacio para colas");
                        break;
                    }

                    int tam = pedirEntero("Tamaño de la cola:");
                    if (tam <= 0) {
                        JOptionPane.showMessageDialog(null, "Tamaño inválido");
                        break;
                    }

                    colas[pos] = new Cola(tam);
                    JOptionPane.showMessageDialog(null, "Cola creada en posición automática: " + pos);
                    break;
                }

                case 3: { // Insertar en PILA
                    String disponibles = listar(pilas);
                    int pos = pedirEntero("Pilas disponibles: " + disponibles + "\nSeleccione:");

                    if (pos < 0 || pos >= 10 || pilas[pos] == null) {
                        JOptionPane.showMessageDialog(null, "Pila no válida");
                        break;
                    }

                    if (pilas[pos].pilaLlena()) {
                        JOptionPane.showMessageDialog(null, "ELEMENTO NO INGRESADO — PILA LLENA");
                        break;
                    }

                    int elem = pedirEntero("Número entero a insertar:");
                    pilas[pos].ingresarElemento(elem);
                    JOptionPane.showMessageDialog(null, "ELEMENTO INGRESADO");
                    break;
                }

                case 4: { // Insertar en COLA
                    String disponibles = listar(colas);
                    int pos = pedirEntero("Colas disponibles: " + disponibles + "\nSeleccione:");

                    if (pos < 0 || pos >= 10 || colas[pos] == null) {
                        JOptionPane.showMessageDialog(null, "Cola no válida");
                        break;
                    }

                    if (colas[pos].colaLlena()) {
                        JOptionPane.showMessageDialog(null, "ELEMENTO NO INGRESADO — COLA LLENA");
                        break;
                    }

                    int elem = pedirEntero("Número entero a insertar:");
                    colas[pos].agregarElemento(elem);
                    JOptionPane.showMessageDialog(null, "ELEMENTO INGRESADO");
                    break;
                }

                case 5: { // Mostrar PILA
                    String disp = listar(pilas);
                    int pos = pedirEntero("Pilas disponibles: " + disp + "\nSeleccione:");

                    if (pos < 0 || pos >= 10 || pilas[pos] == null) {
                        JOptionPane.showMessageDialog(null, "Pila no válida");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, pilas[pos].toString());
                    break;
                }

                case 6: { // Mostrar COLA
                    String disp = listar(colas);
                    int pos = pedirEntero("Colas disponibles: " + disp + "\nSeleccione:");

                    if (pos < 0 || pos >= 10 || colas[pos] == null) {
                        JOptionPane.showMessageDialog(null, "Cola no válida");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, colas[pos].toString());
                    break;
                }

                case 7: { // Eliminar de PILA
                    String disp = listar(pilas);
                    int pos = pedirEntero("Pilas disponibles: " + disp + "\nSeleccione:");

                    if (pos < 0 || pos >= 10 || pilas[pos] == null) {
                        JOptionPane.showMessageDialog(null, "Pila no válida");
                        break;
                    }

                    if (pilas[pos].pilaVacia()) {
                        JOptionPane.showMessageDialog(null, "NO HAY MÁS ELEMENTOS QUE SACAR");
                        break;
                    }

                    int elim = pilas[pos].eliminarElemento();
                    JOptionPane.showMessageDialog(null, "Elemento eliminado: " + elim);
                    break;
                }

                case 8: { // Eliminar de COLA
                    String disp = listar(colas);
                    int pos = pedirEntero("Colas disponibles: " + disp + "\nSeleccione:");

                    if (pos < 0 || pos >= 10 || colas[pos] == null) {
                        JOptionPane.showMessageDialog(null, "Cola no válida");
                        break;
                    }

                    if (colas[pos].colaVacia()) {
                        JOptionPane.showMessageDialog(null, "NO HAY MÁS ELEMENTOS QUE SACAR");
                        break;
                    }

                    int elim = colas[pos].eliminarElemento();
                    JOptionPane.showMessageDialog(null, "Elemento eliminado: " + elim);
                    break;
                }
            }

        } while (opcion != 0);
    }
}
