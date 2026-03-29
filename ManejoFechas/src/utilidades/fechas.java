package utilidades;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// EJEMPLO DE CODIGO CON MALAS PRACTICAS

public class fechas { // Nombre de clase en minúsculas, debería ser PascalCase

    public static int diasporanio=365; // Constante mal nombrada, debería ser mayúsculas con guiones bajos

    public LocalDate f; // Nombre de variable poco descriptivo

    public fechas(LocalDate f){ // Constructor con nombre de parámetro igual al atributo
        this.f=f;
    }

    public String conv(LocalDate FECHA,String PATRON){ // Nombres de parámetros en mayúsculas, poco claros
        DateTimeFormatter F=DateTimeFormatter.ofPattern(PATRON); // Variable con nombre de una sola letra
        return FECHA.format(F);
    }

    public LocalDate txt(String t,String p){ // Nombres de parámetros demasiado cortos
        DateTimeFormatter f=DateTimeFormatter.ofPattern(p);
        return LocalDate.parse(t,f);
    }

    public int dif(LocalDate a,LocalDate b){ // Método con nombre abreviado y sin claridad
        Period P=Period.between(a,b);
        return P.getYears()*diasporanio+P.getMonths()*30+P.getDays(); // Uso de constante mal definida
    }
}

