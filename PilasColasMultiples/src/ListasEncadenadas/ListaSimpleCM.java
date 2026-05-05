/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListasEncadenadas;

import Pila.Pila;

/**
 *
 * @author Gaby
 */
public class ListaSimpleCM {
  // +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
  // LISTA SIMPLE CIRCULAR  
  // Listas encadenadas , 
  // de manera cíclica  
  // +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   private int TOP; // Máximo 
   private int Apunt[ ]; 
   private int INFO [ ]; 
   private int vPTR[]; // poner varios apuntadores de lista PTR
   private Pila DISP;
   
   public ListaSimpleCM(){
     TOP = 20;
     INFO = new int[TOP];
     Apunt = new int[TOP];
     vPTR = new int[5];
     DISP = new Pila(TOP-1);
     inicializarPila();
   }
   
   public ListaSimpleCM(int max){
     TOP = max+1;
     INFO = new int[TOP];
     Apunt = new int[TOP];
     vPTR = new int[5]; 
     
     DISP = new Pila(TOP-1);
     inicializarPila();
   }
   public ListaSimpleCM(int max, int nListas){
     TOP = max+1;
     INFO = new int[TOP];
     Apunt = new int[TOP];
     vPTR = new int[nListas]; 
     
     DISP = new Pila(TOP-1);
     inicializarPila();
   }
   public void inicializarPila(){
     //DISP.getTop();
     for(int i=1; i<TOP; i++){
       DISP.ingresarElemento(i);
     }
   }
   
   public String desplegarPila(){
     return DISP.toString();
   }
   
   public void agregarElemento(int elemento, int nPTR){
     //saca un elemento de la pila y disminuye el apunt en 1
     int temp = DISP.eliminarElemento();
     int PTR = vPTR[nPTR]; 
     if(PTR==0){
       PTR = temp;
     }
     INFO[temp]=elemento;
     Apunt[temp]=Apunt[PTR];
     Apunt[PTR]=temp;
     PTR=temp;
   }
   
   public int eliminarElemento(int nPTR){
     int PTR = vPTR[nPTR]; 
     int temp = PTR; 
     int elemento = INFO[temp];
     INFO[temp] = 0;
     PTR = Apunt[temp];
     Apunt[temp] = 0;
     //devuelve un elemento a la pila y aumenta el apunt en 1
     DISP.ingresarElemento(temp);
     return elemento;
   }
   
   public int localizarElemento(int elemento, int nPTR){
     int PTR = vPTR[nPTR]; 
     int R = PTR;
     int Enodo = 0;
     int nInicio = 0 ; 
    while (R!=PTR || nInicio == 0){
       nInicio = 1; 
       if(INFO[R]==elemento){
         Enodo=R;
         R=Apunt[R]; /// busca encadenado
       }
     }
     return Enodo;
   }
   public int cantidadElementos(int nPTR){
     int PTR = vPTR[nPTR]; 
     int R = PTR;
     int cantidad = 0;
     int nInicio = 0 ; 
    while (R!=PTR || nInicio == 0){
       nInicio = 1; 
       cantidad++;
       R=Apunt[R]; /// busca encadenado
       }
     
     return cantidad;
   }
   
   public void  unirListas( int PTR1 , int PTR2){
   // unir lista con la lista de la clase 
     int R = Apunt[PTR1]; 
     Apunt[PTR1] = Apunt[PTR2] ; 
     Apunt[PTR2] = R; 
     PTR2 = 0; 
   }
   public void invertirListas ( int nPTR, int iPTR){
     int PTR    = vPTR[nPTR];
     int PTRinv = vPTR[iPTR];
     int R = PTR;
     int nInicio = 0 ; 
     int elemento =0;
    while (R!=PTR || nInicio == 0){
       nInicio = 1; 
       // INVERTIRLO
       elemento  = INFO[R]; 
       agregarElemento(elemento, PTRinv); 
       
       R=Apunt[R]; // va para el siguiente nodo
       }
     
     
   }
    public void  dividirListas(int nPTR, int K, int nPTRaK, int nPTRdK ){
     int PTR = vPTR[nPTR]; // la lista original
     int R = PTR;
     int PTRaK = vPTR[nPTRaK];
     int PTRdK = vPTR[nPTRdK];
     int nInicio = 0 ; 
    while (R!=K || nInicio == 0){
       nInicio = 1; 
       agregarElemento(INFO[R],PTRaK);
       R=Apunt[R]; /// sigue con el encadenado
       }
     agregarElemento(INFO[R],PTRaK);// Agrega K a la lista primer
     
     while (R!=PTR ){ // crea la segunda lista 
       R=Apunt[R]; /// sigue con el encadenado
       agregarElemento(INFO[R],PTRdK);
     }
       }

   
   public String toString(){
     String salida = "";
     for(int i=1; i<TOP; i++){
       salida+=i+" ";
       salida+="["+INFO[i]+"]";
       salida+="["+Apunt[i]+"]\n";
     }
     salida+="Estado de la pila\n";
     salida+=DISP.toString();
     return salida;
   }
   
   public String toStringI(){
     String salida = "";
     for(int i=TOP-1; i>0; i--){//invertido
       salida+=i+" ";
       salida+="["+INFO[i]+"]";
       salida+="["+Apunt[i]+"]\n";
     }
     salida+="Estado de la pila\n";
     salida+=DISP.toString();
     return salida;
   }
   
}
//Primeros 8, extraer id, asignar a una lista multiple con encadenamiento de metodos, en una sola linea
