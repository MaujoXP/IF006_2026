package descifrador;

import javax.swing.JOptionPane;

/**
 *
 * @author Meowricio
 */
public class InterfazDescifrador {

    Descifrador d = new Descifrador();

    public int menu() {
        String menu = "=== DESCIFRADOR ===\n"
                + "1) Vaciar la tabla\n"
                + "2) Rellenar tabla con un carácter\n"
                + "3) Rellenar una fila\n"
                + "4) Rellenar una columna\n"
                + "5) Rellenar diagonal principal\n"
                + "6) Rellenar diagonal secundaria\n"
                + "7) Escribir palabra en fila\n"
                + "8) Escribir palabra en columna\n"
                + "9) Extraer contenido de fila\n"
                + "10) Extraer contenido de fila inversa\n"
                + "11) Extraer contenido de columna\n"
                + "12) Extraer contenido de columna inversa\n"
                + "13) Extraer diagonal principal\n"
                + "14) Extraer diagonal principal inversa\n"
                + "15) Extraer diagonal secundaria\n"
                + "16) Extraer diagonal secundaria inversa\n"
                + "17) Mostrar tabla en pantalla\n"
                + "0) Finalizar\n";

        String opcion = JOptionPane.showInputDialog(null, menu, "Panel de opciones", JOptionPane.PLAIN_MESSAGE);

        if (opcion == null) {
            return 0;
        }

        try {
            return Integer.parseInt(opcion.trim());
        } catch (Exception e) {
            return -1;
        }
    }

    public void ejecutar() {
        int opcion;

        do {
            opcion = menu();

            int fila, col;
            char c;
            String palabra;

            switch (opcion) {

                case 1:
                    d.limpiarTabla();
                    JOptionPane.showMessageDialog(null, "La tabla ha sido reiniciada.");
                    break;

                case 2:
                    c = JOptionPane.showInputDialog("Ingrese un carácter: ").charAt(0);
                    d.rellenarTabla(c);
                    JOptionPane.showMessageDialog(null, "Tabla rellenada con '" + c + "'");
                    break;

                case 3:
                    c = JOptionPane.showInputDialog("Carácter: ").charAt(0);
                    fila = Integer.parseInt(JOptionPane.showInputDialog("Número de fila (0-19): "));
                    d.rellenarFila(c, fila);
                    JOptionPane.showMessageDialog(null, "Fila " + fila + " completada.");
                    break;

                case 4:
                    c = JOptionPane.showInputDialog("Carácter: ").charAt(0);
                    col = Integer.parseInt(JOptionPane.showInputDialog("Número de columna (0-19): "));
                    d.rellenarColumna(c, col);
                    JOptionPane.showMessageDialog(null, "Columna " + col + " completada.");
                    break;

                case 5:
                    c = JOptionPane.showInputDialog("Carácter: ").charAt(0);
                    d.rellenarDP(c, 0, 19);
                    JOptionPane.showMessageDialog(null, "Diagonal principal rellenada.");
                    break;

                case 6:
                    c = JOptionPane.showInputDialog("Carácter: ").charAt(0);
                    d.rellenarDP(c, 0, 20);
                    JOptionPane.showMessageDialog(null, "Diagonal secundaria rellenada.");
                    break;

                case 7:
                    fila = Integer.parseInt(JOptionPane.showInputDialog("Fila: "));
                    palabra = JOptionPane.showInputDialog("Palabra: ");
                    d.rellenarFilaPalabra(fila, palabra);
                    JOptionPane.showMessageDialog(null, "Palabra insertada en fila " + fila);
                    break;

                case 8:
                    col = Integer.parseInt(JOptionPane.showInputDialog("Columna: "));
                    palabra = JOptionPane.showInputDialog("Palabra: ");
                    d.rellenarColumnaPalabra(col, palabra);
                    JOptionPane.showMessageDialog(null, "Palabra insertada en columna " + col);
                    break;

                case 9:
                    fila = Integer.parseInt(JOptionPane.showInputDialog("Fila: "));
                    String extraFila = d.extraerFila(fila, 0, 19);
                    JOptionPane.showMessageDialog(null, "Fila: " + extraFila);
                    break;

                case 10:
                    fila = Integer.parseInt(JOptionPane.showInputDialog("Fila: "));
                    String extraFilaInv = d.extraerFilaInversa(fila, 0, 19);
                    JOptionPane.showMessageDialog(null, "Fila inversa: " + extraFilaInv);
                    break;

                case 11:
                    col = Integer.parseInt(JOptionPane.showInputDialog("Columna: "));
                    String extraCol = d.extraerColumna(col, 0, 19);
                    JOptionPane.showMessageDialog(null, "Columna: " + extraCol);
                    break;

                case 12:
                    col = Integer.parseInt(JOptionPane.showInputDialog("Columna: "));
                    String extraColInv = d.extraerColumnaInversa(col, 0, 19);
                    JOptionPane.showMessageDialog(null, "Columna inversa: " + extraColInv);
                    break;

                case 13:
                    String diagP = d.extraerDiagonalDerecha(0, 19);
                    JOptionPane.showMessageDialog(null, "Diagonal principal: " + diagP);
                    break;

                case 14:
                    String diagPInv = d.extraerDiagonalDerechainversa(0, 19);
                    JOptionPane.showMessageDialog(null, "Diagonal principal inversa: " + diagPInv);
                    break;

                case 15:
                    String diagS = d.extraerDiagonalIzquierda(0, 19);
                    JOptionPane.showMessageDialog(null, "Diagonal secundaria: " + diagS);
                    break;

                case 16:
                    String diagSInv = d.extraerDiagonalIzquerdainversa(0, 19);
                    JOptionPane.showMessageDialog(null, "Diagonal secundaria inversa: " + diagSInv);
                    break;

                case 17:
                    JOptionPane.showMessageDialog(null, d.toString(), "Vista de la tabla", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }

        } while (opcion != 0);
    }
}
