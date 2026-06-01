package Ordenamiento;

public class VectorX{ 
   
  //Atributos 
  private int vector []; 
  private int tamaño; 
  private int nIntercambios; 
  private int nComparaciones; 
  private int nPasadas; 
 
  //Constructores 
  public VectorX (){ 
    vector = new int [10]; 
    this.tamaño= 10; 
    this.nIntercambios= 0; 
    this.nComparaciones= 0; 
    this.nPasadas= 0; 
  } 
   
  public VectorX (int t){ 
    vector = new int [t]; 
    this.tamaño= t; 
    this.nIntercambios= 0; 
    this.nComparaciones= 0; 
    this.nPasadas= 0; 
    for (int i=0; i<vector.length; i++){ 
      //this.vector [i]= (int)(Math.random ()*t); 
      this.vector [i]= i*2; 
    } 
    this.nIntercambios= 0; 
    this.nComparaciones= 0; 
    this.nPasadas= 0; 
  } 
   
  public VectorX (int t, int valor){ 
    vector = new int [t]; 
    this.tamaño= t; 
    this.nIntercambios= 0; 
    this.nComparaciones= 0; 
    this.nPasadas= 0; 
    for (int i=0; i<tamaño; i++){ 
      this.vector [i]=valor; 
    } 
  } 
   
  public VectorX (int vec []){ 
    this.tamaño= vec.length; 
    this.nIntercambios= 0; 
    this.nComparaciones= 0; 
    this.nPasadas= 0; 
    vector = new int [tamaño]; 
    for (int i = 0; i < tamaño; i++){ 
      this.vector [i] = vec [i]; 
    }  
  } 
   
  public VectorX (VectorX nuevo){ 
    this.tamaño = nuevo.tamaño; 
    this.nIntercambios = nuevo.nIntercambios; 
    this.nComparaciones = nuevo.nComparaciones; 
    this.nPasadas = nuevo.nPasadas; 
    vector = new int [nuevo.tamaño]; 
  } 
   
  //Métodos set y get 
  public void setValor (int pos, int valor){ 
    this.vector [pos]= valor; 
  } 
   
  public int[] getVector(){ 
    return this.vector; 
  } 
   
  /* Métodos asociados a vectores */ 
   
  public  void intercambio (int i, int j){ // vector de la clase  
  int aux = vector[i]; 
      vector[i]= vector[j]; 
      vector[j]= aux; 
  } 
   
 
   
  public void ordenarSLI() {  
     int n = vector.length; 
     for (int i = 0; i < n-1;i++){ 
       for (int j = i+1;j< n ; j++){
           nComparaciones++;
         if(vector[i] > vector[j]){ 
            intercambio(i,j); 
            nIntercambios++;
         } 
       } 
       nPasadas++;
     } 
  } 
   
      
  public int [] ordenarSLI(int vec[]) {  
     int n = vec.length; 
     int aux =0; 
     for (int i = 0; i < n-1;i++){ 
       for (int j = i+1;j< n ; j++){ 
         if(vec[i] > vec[j]){ 
           // intercambio(i,j); 
           aux   = vec[i]; 
           vec[i]= vec[j]; 
           vec[j]= aux; 
         } 
       } 
     } 
     return vec; 
  } 
  
  public int[] generarVector(int cantidad) {
      int[] temp = new int[cantidad];
      for(int i = 0; i < temp.length; i++) {
         temp[i] = (int)(Math.random ()*cantidad); 
      }
      return temp;
  }
   
  public String toString (){ 
    String sal= "CONTENIDO DEL VECTOR\n"; 
    sal+= "\nNúmero de elementos     = " + this.tamaño; 
    sal+= "\nNúmero de pasadas       = " + this.nPasadas; 
    sal+= "\nNúmero de intercambios  = " + this.nIntercambios; 
    sal+= "\nNúmero de comparaciones = " + this.nComparaciones + "\n"; 
     
    for (int i=0; i<tamaño; i++){ 
      sal += "Vector [ " + i + " ]= "; 
      sal +=  this.vector [i] + "\n"; 
    } 
     
    return sal;   
  } 
  public String toString (int vec[]){ //recibe un vector foraneo 
    String sal= "CONTENIDO DEL VECTOR\n"; 
     int  n = vec.length;  
    for (int i=0; i<n; i++){ 
      sal += "Vector [ " + i + " ]= "; 
      sal +=  vec [i] + "\n"; 
    } 
     
    return sal;   
  } 
  public String toString3 (){ 
    String sal= "CONTENIDO DEL VECTOR\n"; 
    sal+= "\nNúmero de elementos     = " + this.tamaño; 
    sal+= "\nNúmero de pasadas       = " + this.nPasadas; 
    sal+= "\nNúmero de intercambios  = " + this.nIntercambios; 
    sal+= "\nNúmero de comparaciones = " + this.nComparaciones + "\n"; 
     
    return sal;   
  } 
     
}