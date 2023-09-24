package Practica2;

import java.util.ArrayList;

class Apilador {

    /**
     * Metodo que rellena la tabla de Programacion Dinamica que muestra
     * las posibles torres
     *
     * @param A1 La primera torre como vector de Enteros
     * @param A2 La segunda torre como vector de Enteros
     * @param m  Tamaño de la primera torre
     * @param n  Tamaño de la segunda torre
     * @param tabla La tabla de Programacion Dinamica que se va a rellenar
     */
    static void CreaTabla(int[] A1, int[] A2, int m, int n,int [] [] tabla) {
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    tabla[i][j] = 0;
                else if (A1[i - 1] == A2[j - 1])
                    tabla[i][j] = tabla[i - 1][j - 1] + 1;
                else
                    tabla[i][j] = Math.max(tabla[i - 1][j], tabla[i][j - 1]);
            }
        }
    }

    /**
     * Metodo que deshace el camino para obtener la torre mas larga
     *
     * @param A1 La primera torre como vector de Enteros
     * @param A2 La segunda torre como vector de Enteros
     * @param resp Un {@link ArrayList} que contendra la torre mas larga
     * @param tabla La tabla de programacion dinamica en la que nos basaremos para obtener la respuesta
     */
    static void EncuentraSolucion(int[]A1, int []A2, ArrayList<Integer>resp, int [] [] tabla){
        int i = tabla.length-1, j = tabla[0].length-1;
        while (i > 0 && j > 0) {
            if(A1[i-1]== A2[j - 1]) {
                resp.add(A1[i-1]);
                i--;
                j--;
            }
            else if (tabla[i - 1][j] >tabla[i][j - 1])
                i--;
            else
                j--;
        }
    }
 }
