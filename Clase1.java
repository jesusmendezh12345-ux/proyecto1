
package com.mycompany.clase1;


public class Clase1 {

    public static void main(String[] args) {
      int[] numeros = {10, 20, 30, 40, 50};
      numeros [0] = 202;
      
      
      for (int i  = 0 ; i < numeros.length; i++)
          System.out.println("posicion" + i + ":" + numeros [i]);
      //numeros.length
      
        System.out.println("recorrido con for -each");
      
        for (int num : numeros){
            System.out.println(numeros[num]);
        }
        
        
        System.out.println(numeros.length);
        System.out.println("El arreglo inicia en : "+numeros[0]);
        System.out.println(numeros[1]);
      int [][]matriz= {
          {1,2,3},
          {4,5,6},
          {7,8,9}
      };
      
       for(int fila = 0 ; fila< matriz.length; fila++ ){
           int sumafila =0;
            for(int columna = 0 ; columna< matriz[fila].length; columna++ ){
                sumafila+= matriz[fila][columna];
            }
            System.out.println("SUMA FILA"+ fila + sumafila);
       }
       for(int columnas = 0 ; columnas< matriz[0].length; columnas++ ){
           int sumacolumnas =0;
            for(int fila = 0 ; fila< matriz.length; fila++ ){
                sumacolumnas+= matriz[fila][columnas];
            }
            System.out.println("SUMACOLUMNAS"+ columnas + sumacolumnas);
       }
        System.out.println(matriz[1][1]);
        
        for(int i = 0 ; i< matriz.length; i++){
            for(int j = 0 ; j< matriz [i].length; j++ ){
            System.out.println(matriz[i][j]);
            }
       
        }
        int sumadiagonal = 0; 
        
        for(int i = 0 ; i< matriz.length;){
            sumadiagonal += matriz[i][i];
            
        
        }
        System.out.println("suma diagonal"+ sumadiagonal);
    }
}
