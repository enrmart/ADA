package Practica1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static Practica1.Algoritmos.*;

public class MainParte1 {
    public static void main(String []args) throws IOException {

        int [] A= new int[100000];
        FileWriter fich=new FileWriter("./Practica1Parte1.csv");
        PrintWriter pw=new PrintWriter(fich);
        pw.println("PARTE 1: QUICKSORT MODIFICADO");
        pw.println("TAMANO: "+A.length);
        pw.print("UMBRAL  ");
        pw.print("COMPARACIONES  ");
        pw.println("ASIGNACIONES  ");

        for(int K=3;K<=30;K++){

        int proc = 0;
        int proa = 0;

                for (int v = 1; v <= 20; v++) {//cada una de las repeticiones por valor del umbral
                AtomicInteger comp = new AtomicInteger();
                AtomicInteger asig = new AtomicInteger();
                for (int i = 1; i <= A.length; i++) {//rellena el vector
                    A[i - 1] = i;
                }

                for (int j = 0; j < A.length; j++) {//desordena el vector
                int x = (int) Math.floor(Math.random() * (A.length - 1));
                int y = (int) Math.floor(Math.random() * (A.length - 1));
                int temp = A[x];
                A[x] = A[y];
                A[y] = temp;
            }

            mediana(A,A[0], A[(A.length / 2)-1], A[A.length - 1],comp,asig);

            quicksortm(A, 0, A.length - 1, 3, comp, asig);


            proc = comp.get() + proc;
            proa = asig.get() + proa;
        }
        pw.print(K+"                    ");
        pw.print(proc / 20+"                   ");
        pw.println(proa / 20);
    }

    fich.close();
}
}
