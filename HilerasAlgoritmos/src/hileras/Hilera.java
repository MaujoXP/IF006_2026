/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hileras;

/**
 *
 * @author Gaby
 */
public class Hilera {
    private String H;
    private String[] palabras;
    private int tamaño;
    
    public Hilera() {
        this.H = "BUENOS DIAS";
        tamaño = H.length();
    }
    
    public Hilera(Hilera hilera) {
        this.H = hilera.H;
        this.palabras = hilera.palabras;
    }
    
    public Hilera(String hileraNueva) {
        this.H = hileraNueva;
        this.tamaño = hileraNueva.length();
    }
    
    public Hilera(char[] hileraChar) {
        this.H = new String(hileraChar);
    }
    
    public String hImp1() {
        String salida = "";
        for(int i = 0; i <= H.length(); i++) {
            salida += H.substring(0, i) + "\n";
        }
        return salida;
    }
    
    public String hImp2() {
        String salida = "";
        for(int i = H.length(); i >= 0; i--) {
            salida += H.substring(i) + "\n";
        }
        return salida;
    }
    
    public String hImp3() {
        String salida = "";
        for(int i = H.length(); i >= 0; i--) {
            salida += H.substring(0, i) + "\n";
        }
        return salida;
    }
    
    public String hImp4() {
        String salida = "";
        for(int i = 0; i <= H.length(); i++) {
            salida += H.substring(i, H.length()) + "\n";
        }
        return salida;
    }
    
    public String hDiv1() {
        return H.substring(0, H.length() / 2);
    }
    
    public String hDiv2() {
        return H.substring(H.length() / 2, H.length());
    }
    
    public int hNumerosBlancos() {
        int nBlancos = 0;
        for(int i = 0; i < H.length(); i++) {
            if(H.charAt(i)  == ' ') nBlancos++;
        }
        return nBlancos;
    }

    public String getH() {
        return H;
    }

    public String[] getPalabras() {
        return palabras;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setH(String H) {
        this.H = H;
    }

    public void setPalabras(String[] palabras) {
        this.palabras = palabras;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    public String toString(){
  String salida="HILERA\n";
  salida+= "H=" +H+"\n" ;
  salida+= "Lista de palabras \n";
 
  //for(int i=0;i<=numeroPalabras()-1;i++){
      //salida+="Palabra["+i+"]"+palabras[i]+"\n"; 
    //}//fin del for
   return salida;
    }
}
