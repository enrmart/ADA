package Practica1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static Practica1.Algoritmos.*;

public class MainParte2 {
    public static void main(String []args) throws IOException {

        FileWriter fich=new FileWriter("./Practica1Parte2.csv");
        PrintWriter pw=new PrintWriter(fich);
        pw.println("PRACTICA 1: PARTE 2");
        pw.print("TAMANO  ");
        pw.print("COMP.QUICKSORTM  ");
        pw.print("ASIG.QUICKSORTM  ");
        pw.print("TIEMPO.QUICKSORTM  ");
        pw.print("COMP.QUICKSORT  ");
        pw.print("ASIG.QUICKSORT  ");
        pw.println("TIEMPO.QUICKSORT  ");


        for(int tam=10000;tam<=100000;tam=tam+10000) {

            int[] A = new int[tam];
            int procm = 0;
            int proam = 0;
            int procq = 0;
            int proaq = 0;
            long protm = 0;//Tiempo promedio del quicksort modificado
            long protq = 0;//Tiempo promedio del quicksort

            for (int v = 1; v <= 20; v++) {//cada una de las repeticiones por valor del umbral

                AtomicInteger compm = new AtomicInteger();
                AtomicInteger asigm = new AtomicInteger();
                AtomicInteger compq = new AtomicInteger();
                AtomicInteger asigq= new AtomicInteger();

                for (int i = 1; i <= A.length; i++) {//rellena el vector
                    A[i - 1] = i;
                }

                for (int j = 0; j < A.length / 10; j++) {//desordena el vector
                    int x = (int) Math.floor(Math.random() * (A.length - 1));
                    int y = (int) Math.floor(Math.random() * (A.length - 1));
                    int temp = A[x];
                    A[x] = A[y];
                    A[y] = temp;
                }

                int[] A2 = Arrays.copyOf(A, A.length);

                mediana(A,A[0], A[(A.length / 2)-1], A[A.length - 1],compm,asigm);

                Instant start = Instant.now();
                quicksortm(A, 0, A.length - 1, 19, compm, asigm);
                Instant finish = Instant.now();
                long timeElapsed = Duration.between(start, finish).toNanos();
                protm = timeElapsed + protm;

                start = Instant.now();
                quicksort(A2, 0, A2.length - 1, compq, asigq);
                finish = Instant.now();
                timeElapsed = Duration.between(start, finish).toNanos();
                protq = timeElapsed + protq;

                procm = compm.get() + procm;
                proam = asigm.get() + proam;

                procq = compq.get() + procq;
                proaq = asigq.get() + proaq;
            }
            pw.print(A.length+"              ");
            pw.print(procm / 20+"                        ");
            pw.print(proam / 20+"                        ");
            pw.print(protm / 20+"                        ");


            pw.print(procq / 20+"                        ");
            pw.print(proaq / 20+"                        ");
            pw.println(protq / 20);
        }
        fich.close();
    }
}

