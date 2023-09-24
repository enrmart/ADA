package Practica2;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static Practica2.Apilador.EncuentraSolucion;
import static Practica2.Apilador.CreaTabla;


public class Main {
    public static void main(String args[]) throws IOException {
        int N1; //numero de piezas torre 1
        int N2; //numero de piezas torre 2
        int[] A1;//torre 1
        int[] A2;//torre 2
        int j = 1;

        String cad;
        FileReader fiche = new FileReader("./Entrada.txt");
        BufferedReader b = new BufferedReader(fiche);

        while ((cad = b.readLine()) != null) {
            String[] numeros = cad.split(" ");
            N1 = Integer.parseInt(numeros[0]);
            N2 = Integer.parseInt(numeros[1]);

            if (N1 >= 1 && N1 <= 100 && N2 <= 100 && N2 >= 1) {
                A1 =new int [N1];
                A2= new int[N2];

                creaTorres(A1,A2,b);

                int [][] tabla=new int[N1+1][N2+1];
                CreaTabla(A1, A2,N1,N2,tabla);
                ArrayList<Integer> resp= new ArrayList<>();

                EncuentraSolucion(A1,A2,resp,tabla);


                System.out.println("Caso de prueba " + j);
                System.out.println("Numero de piezas: " + resp.size());
                System.out.print("Solucion: ");

                if(resp.size()>0) {
                    Collections.reverse(resp);
                    for(int i=0;i<resp.size();i++){
                        System.out.print(resp.get(i)+" ");
                    }
                }
                System.out.println('\n');
                j++;
            } else if (N1 == 0 && N2 == 0) {
                break;
            }
        }

        fiche.close();
    }

    private static void creaTorres(int[] A1,int[]A2,BufferedReader b) throws IOException {
       String cad;
       String [] numeros;

       //Primera Torre
       cad = b.readLine();
       numeros= cad.split(" ");
        for (int i = 0; i < numeros.length; i++) {
            A1[i] = Integer.parseInt(numeros[i]);
        }

        //Segunda Torre
        cad = b.readLine();
        numeros = cad.split(" ");
        for (int i = 0; i < numeros.length; i++) {
            A2[i] = Integer.parseInt(numeros[i]);
        }
    }

}