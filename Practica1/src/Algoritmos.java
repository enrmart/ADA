package Practica1;

import java.util.concurrent.atomic.AtomicInteger;

public class Algoritmos {
    /**
     * Calcula el pivote entre el valor de
     * la primera posicion del vector,la del medio y la del final.
     *
     * @param inicio El valor de la primera posicion del vector
     * @param medio  El valor de la posicion del medio del vector
     * @param fin    El valor de la ultima posicion del vector
     */

    public static void mediana(int[] A, int inicio, int medio, int fin, AtomicInteger comp, AtomicInteger asig) {
        int res;
        int temp;
        int pivote;
        comp.incrementAndGet();
        if (inicio > medio) {
            res = inicio;
            temp = medio;
        } else {
            res = medio;
            temp = inicio;
        }
        if (res > fin) {
            res = fin;
        }
        if (res < temp) {
            res = temp;
        }

        comp.incrementAndGet();
        if (res == A[0]/*+1 comp*/) {//identifica la posicion del pivote
            pivote = 0;
            cambia(A, pivote, A.length - 1, asig);
        } else if (res == A[A.length - 1]/*+1 comp*/) {
            pivote = A.length - 1;
            comp.incrementAndGet();
        } else {
            comp.incrementAndGet();
            pivote = A.length / 2;
            cambia(A, pivote, A.length - 1, asig);
        }

    }


    /**
     * Algoritmo Quicksort modificado que ordena un vector
     *
     * @param A      vector a ordenar
     * @param inicio primera posicion a ordenar
     * @param fin    ultima posicion a ordenar
     * @param K      umbral para ver a que algortimo se llama
     * @param comp   El numero de comparaciones totales que lleva el programa
     * @param asig   El numero de asignaciones totales que lleva el programa
     */
    public static void quicksortm(int[] A, int inicio, int fin, int K, AtomicInteger comp, AtomicInteger asig) {

        // condición base
        if (inicio >= fin) {
            return;
        }

        int pivote = particion(A, inicio, fin, comp, asig);


        if (pivote - inicio <= K) {
            ordenacionInsercion(A, (pivote-inicio) , inicio, comp, asig);
        } else {
            quicksortm(A, inicio, pivote - 1, K, comp, asig);
        }

        if ((fin-pivote) <= K) {

            ordenacionInsercion(A, (fin - pivote), pivote + 1, comp, asig);
        } else {
            quicksortm(A, pivote + 1, fin, K, comp, asig);
        }
    }

    /**
     * El algortimo quicksort sin modificaciones
     *
     * @param a      vector a ordenar
     * @param inicio primera posicion a ordenar
     * @param fin    ultima posicion a ordenar
     * @param comp   El numero de asignaciones totales que lleva el programa
     * @param asig   El numero de asignaciones totales que lleva el programa
     */
    public static void quicksort(int[] a, int inicio, int fin, AtomicInteger comp, AtomicInteger asig) {
        // condición base
        if (inicio >= fin) {
            return;
        }

        int pivote = particion(a, inicio, fin, comp, asig);

        quicksort(a, inicio, pivote - 1, comp, asig);

        quicksort(a, pivote + 1, fin, comp, asig);
    }

    /**
     * Ordena los valores de la particion menor que el umbral
     *
     * @param a    El vector a ordenar
     * @param tam  El numero de posiciones uqe va a ordenar
     * @param pos  La posicion en la que empieza a ordenar
     * @param comp El numero de comparaciones totales que lleva el programa
     * @param asig El numero de asignaciones totales que lleva el programa
     */

    public static void ordenacionInsercion(int a[],int tam,int pos, AtomicInteger comp,AtomicInteger asig){
        int j;
        int aux;
        for (int p = pos+1; p<tam+pos; p++){
            aux = a[p];
            asig.incrementAndGet();
            j = p - 1;            // empezamos a comprobar con el anterior
            while ((j >= pos) && (aux < a[j])){ // mientras queden posiciones y el valor de aux sea menor que los
                comp.incrementAndGet();
                a[j + 1] = a[j];   // de la izquierda, se desplaza a
                asig.incrementAndGet();
                j--;               // la derecha
            }
            a[j + 1] = aux;       // colocamos aux en su sitio
            asig.incrementAndGet();
        }
    }

    /**
     * Calcula las particiones del vector en función del pivote
     *
     * @param a      vector que se quiere ordenar
     * @param inicio primera posicion que se ordena
     * @param fin    ultima posicion que se ordena
     * @param comp   El numero de comparaciones totales que lleva el programa
     * @param asig   El numero de asignaciones totales que lleva el programa
     * @return el indice del pivote
     */
    public static int particion(int[] a, int inicio, int fin, AtomicInteger comp, AtomicInteger asig) {
        // Obtiene el valor del pivote en funcion de lo calculado previamente
        int pivote = a[fin];
        asig.incrementAndGet();

        int pIndex = inicio;

        for (int i = inicio; i < fin; i++) {
            comp.incrementAndGet();
            if (a[i] <= pivote/*+1comp*/) {
                cambia(a, i, pIndex, asig);
                pIndex++;
            }
        }
        // intercambia `pIndex` con pivote
        cambia(a, fin, pIndex, asig);
        return pIndex;
    }

    /**
     * Intercambia las posiciones del vector
     *
     * @param a    El vector
     * @param i    Una posicion a intercambiar
     * @param j    La otra posicion a intercambiar
     * @param asig El numero de asignaciones totales que lleva el programa
     */

    public static void cambia(int[] a, int i, int j, AtomicInteger asig) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        asig.set(asig.get() + 3);
    }
}