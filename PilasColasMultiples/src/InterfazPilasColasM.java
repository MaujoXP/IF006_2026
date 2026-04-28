
import Cola.ColasM;
import Pila.PilasM;
import javax.swing.JOptionPane;

public class InterfazPilasColasM {

    PilasM pilas = new PilasM();
    ColasM colas = new ColasM();

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

    public void ejecutar() {

        int opcion;

        do {
            opcion = menu();

            switch (opcion) {
                case 1: {
                    int pos = pilas.buscarLibre();
                    if (pos == -1) {
                        JOptionPane.showMessageDialog(null, "NO HAY ESPACIO PARA MÁS PILAS");
                        break;
                    }

                    int tam = pedirEntero("Tamaño:");
                    if (tam <= 0) {
                        JOptionPane.showMessageDialog(null, "Tamaño inválido");
                        break;
                    }

                    pilas.crearPila(pos, tam);
                    JOptionPane.showMessageDialog(null, "PILA CREADA EN POSICIÓN: " + pos);
                    break;
                }
                case 2: {
                    int pos = colas.buscarLibre();
                    if (pos == -1) {
                        JOptionPane.showMessageDialog(null, "NO HAY ESPACIO PARA MÁS COLAS");
                        break;
                    }

                    int tam = pedirEntero("Tamaño:");
                    if (tam <= 0) {
                        JOptionPane.showMessageDialog(null, "Tamaño inválido");
                        break;
                    }

                    colas.crearCola(pos, tam);
                    JOptionPane.showMessageDialog(null, "COLA CREADA EN POSICIÓN: " + pos);
                    break;
                }
                case 3: {
                    String disponibles = pilas.listar();
                    int pos = pedirEntero("Pilas disponibles: " + disponibles + "\nSeleccione:");

                    if (!pilas.existe(pos)) {
                        JOptionPane.showMessageDialog(null, "PILA NO EXISTE");
                        break;
                    }

                    if (pilas.estaLlena(pos)) {
                        JOptionPane.showMessageDialog(null, "PILA LLENA");
                        break;
                    }

                    int elem = pedirEntero("Elemento:");
                    pilas.agregarPilaM(pos, elem);

                    JOptionPane.showMessageDialog(null, "ELEMENTO INGRESADO");
                    break;
                }
                case 4: {
                    String disponibles = colas.listar();
                    int pos = pedirEntero("Colas disponibles: " + disponibles + "\nSeleccione:");

                    if (!colas.existe(pos)) {
                        JOptionPane.showMessageDialog(null, "COLA NO EXISTE");
                        break;
                    }

                    if (colas.estaLlena(pos)) {
                        JOptionPane.showMessageDialog(null, "COLA LLENA");
                        break;
                    }

                    int elem = pedirEntero("Elemento:");
                    colas.agregarColaM(pos, elem);

                    JOptionPane.showMessageDialog(null, "ELEMENTO INGRESADO");
                    break;
                }
                case 5: {
                    
                    String disponibles = pilas.listar();
                    int pos = pedirEntero("Pilas disponibles: " + disponibles + "\nSeleccione:");

                    if (!pilas.existe(pos)) {
                        JOptionPane.showMessageDialog(null, "PILA NO EXISTE");
                        break;
                    }

                    JOptionPane.showMessageDialog(null, pilas.toString(pos));
                    break;
                }
                case 6: {
                    String disponibles = colas.listar();
                    int pos = pedirEntero("Colas disponibles: " + disponibles + "\nSeleccione:");

                    if (!colas.existe(pos)) {
                        JOptionPane.showMessageDialog(null, "COLA NO EXISTE");
                        break;
                    }

                    JOptionPane.showMessageDialog(null, colas.toString(pos));
                    break;
                }
                case 7: {
                    String disponibles = pilas.listar();
                    int pos = pedirEntero("Pilas disponibles: " + disponibles + "\nSeleccione:");

                    if (!pilas.existe(pos)) {
                        JOptionPane.showMessageDialog(null, "PILA NO EXISTE");
                        break;
                    }

                    if (pilas.estaVacia(pos)) {
                        JOptionPane.showMessageDialog(null, "NO HAY MÁS ELEMENTOS");
                        break;
                    }

                    int eliminado = pilas.eliminarPilaM(pos);
                    JOptionPane.showMessageDialog(null, "ELIMINADO: " + eliminado);
                    break;
                }
                case 8: {
                    String disponibles = colas.listar();
                    int pos = pedirEntero("Colas disponibles: " + disponibles + "\nSeleccione:");

                    if (!colas.existe(pos)) {
                        JOptionPane.showMessageDialog(null, "COLA NO EXISTE");
                        break;
                    }

                    if (colas.estaVacia(pos)) {
                        JOptionPane.showMessageDialog(null, "NO HAY MÁS ELEMENTOS");
                        break;
                    }

                    int eliminado = colas.eliminarColaM(pos);
                    JOptionPane.showMessageDialog(null, "ELIMINADO: " + eliminado);
                    break;
                }
            }

        } while (opcion != 0);
    }
}
