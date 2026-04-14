/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejrecursividad;

/**
 *
 * @author Gaby
 */
public class Recursividad {

    // |============================================|
// |Universidad de Costa Rica, Sede del Caribe  |
// |Carrera Informatica Empresarial             |  
// |curso Algoritmos y estructura de Datos      | 
// |Profesor: Luis Serrano Franceschi           |
// |tema : R E C U R S I V I D A D              |
// | |
// |============================================|
    private int n;
// contructor 

    public Recursividad() {
        this.n = 10;
    }
// metodos recursivos 

    public static int sumaR(int x) {
        //  ===========================================|EJEMPLO X = 10    
        // Suma numeros de manera recursiva             |salida=10+9+8+   
        //  =============================================|7+6+5+4+3+2+1 = 55              
        int salida;
        //  ===========================================|PARTE INDISPENSABLE   
        // Condicion de salida de metodo recursivo.     |DE UN METODO   
        //  =============================================|RECURSIVO chesky      
        if (x == 1) {
            return x;
        } else {
            salida = x + sumaR(x - 1); //Se Llama a si mismo
        }

        return salida;//Retorna salida
    }

    public static int factorialR(int x) {
        //  ============================\\    |EJEMPLO X = 5    
        // Factorial de  manera RECURSIVA \\   |salida=5*4*3*2*1 = 120    
        //  ================================\\  |         
        int salida;
        if (x == 1) {
            return x; // este valor será de 1 y es el ultimo que retornará
        } else {
            salida = x * factorialR(x - 1); // se vuelve a llamar a si mismo
        }
        return salida; // retorna salida 
    }

    public static String invertirTexto(String texto, int tamaño) {
        //  =============================\\    |EJEMPLO TEXTO = "ALGORITMOS"    
        // TEXTO O FRASES INVERTIDAS       \\   |salida="SOMTIROGLA"    
        //  =================================\\  |       
        if (tamaño == 0) {
            return texto.charAt(tamaño) + "";
        } else {
            return texto.charAt(tamaño) + (invertirTexto(texto, tamaño - 1));
        }

    }

    public int fibonacci(int u, int p, int salida) {
        //  ===========================================|EJEMPLO hasta 55 (valor)    
        // producir la serie de fibonacci  1, 1         |salida=1,1,2,3,5,8,13,21,34,55...    
        //  =============================================|         
//   int salida = 0;
        if (salida >= 89) {
            return 1;
        } else {
            salida = u + p;
            p = u;
            u = salida;
            System.out.print(salida + ",");
            fibonacci(u, p, salida);
        }
        return salida;// returna salida 
    }

// metodos iteractivos , con ciclos 
    public int factorialI(int n) {
        int fac = 1;
        if (n <= 1) {
            return fac;
        }
        for (int i = 1; i <= n; i++) {
            fac *= i;
        }
        return fac;
    }

    public int sumaI(int n) {
        int suma = 0;
        // if (n <= 1) return fac;
        for (int i = 1; i <= n; i++) {
            suma += i;
        }
        return suma;
    }

    public String fibonacciI(int valor) {
        //  ==================================\\  |EJEMPLO hasta 55 (valor)    
        // producir la serie de fibonacci  1, 1 \\ |salida=1,1,2,3,5,8,13,21,34,55...    
        //  ======================================\\|         
        String salida = "{";
        int u = 1;
        int p = 1;
        int a = 1;
        while (a <= valor) {
            salida += a + ",";
            a = u + p;
            p = u;
            u = a;
        }
        return salida + "}";
    }

    public static String invertirTextoI(String texto) {
        //==============================\\     |EJEMPLO TEXTO = "ALGORITMOS"    
        // TEXTO O FRASES INVERTIDAS      \\    |salida="SOMTIROGLA"    
        //==================================\\   |       
        String textoInvertido = "";
        int tamTexto = texto.length() - 1;
        for (int i = tamTexto; i >= 0; i--) {
            textoInvertido += texto.charAt(i);
        }
        return textoInvertido;
    }
}
