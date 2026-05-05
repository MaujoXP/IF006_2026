/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class ListaIndexContacto{
    private  ListaContactos listaIndex ;
    private  int llavePrimaria[];
    private  int posRelativa[]; 
    //private int estado[]; 
     
 public ListaIndexContacto(){
    listaIndex   = new ListaContactos(25) ;
    llavePrimaria= new int[25];
    posRelativa  = new int[25]; 
    cargarLlaveInt (); 
 }
  public ListaIndexContacto(ListaContactos nueva){
    listaIndex   = nueva  ;
    llavePrimaria= new int[listaIndex.getTamMaximo()];
    posRelativa  = new int[listaIndex.getTamMaximo()]; 
    cargarLlaveInt (); 
 }
 public void cargarLlaveInt (){
    for(int i = 0 ;i<listaIndex.getAContacto() ;i++){
       llavePrimaria[i] = Integer.parseInt(listaIndex.getContacto(i).getId()); 
       posRelativa  [i] = i ; 
    }
     ordenarLlaveSLC ();
 }
  public void ordenarLlaveSLC (){
    int aux;
    for(int i = 0 ;i<listaIndex.getAContacto()-1 ;i++){
      for(int j = i+1 ;j<listaIndex.getAContacto() ;j++){ 
        if(llavePrimaria[i] > llavePrimaria[j]){
      // intercambio o swap
          aux              = llavePrimaria[i];
          llavePrimaria[i] = llavePrimaria[j];
          llavePrimaria[j] = aux;
      // swap a la posicion relativa 
          aux            = posRelativa[i];
          posRelativa[i] = posRelativa[j];
          posRelativa[j] = aux;        
        } 
   }
    }
 }
  // localizar registro
public int  localizarLlave(int llave){
      int resultado = -1;
      for(int i = 0 ;i<listaIndex.getAContacto() ;i++){
        if(llavePrimaria[i]== llave){
           resultado = i ;
            i = listaIndex.getAContacto();}
      }  
      return resultado; 
    }
// METODOS GET

 public ListaContactos getListaContactoX(){
      return listaIndex; }
 
 public int  posRelativaX (int pos){
      return posRelativa  [pos];
 }  
 // DESPLEGAR INDICES 
 public String desplegarIndices(){
      String sal = "LISTA de indices \n ";
      for(int i = 0 ;i<listaIndex.getAContacto() ;i++){
          sal += i+ "["+llavePrimaria[i]+"]  ";
          sal +=    "["+posRelativa  [i]+"]";
          sal +=    "Detalle : " + 
          listaIndex.getContacto(posRelativa[i]).toDetalle2()+"\n";
          }
      return sal; 
    }     
 }
