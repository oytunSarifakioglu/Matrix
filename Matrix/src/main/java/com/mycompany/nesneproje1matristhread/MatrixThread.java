/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nesneproje1matristhread;

import java.util.ArrayList;

/**
 *
 * @author Oytun
 */
public class MatrixThread implements Runnable {

    ArrayList<Integer> m1;
    ArrayList<Integer> m2;
    int[][] f1;
    int totalRowNumber;
    int totalColomnNumber;
    int threadRow;

    @Override
    public void run() {
        int size1 = m1.size();
        int size2 = m2.size();
        size1 = size1 / totalRowNumber;
        int total = 0;
        for (int i = 0; i < totalColomnNumber; i++) {
            total = 0;
            for (int j = 0; j < size1; j++) {

                total += (m1.get(threadRow * size1 + j) * m2.get((j * totalColomnNumber) + i)); //istenen satırın elemanlarıyla 2. matris sütunları çarpılıp toplanır
                System.out.println("threadNo:" + threadRow + " row:" + j + " colomn:" + i);
            }
            f1[threadRow][i] = total;
            System.out.println(f1[threadRow][i]);
            //f1.add(threadRow + i, total);
            //System.out.println("eklendi  " + (totalColomnNumber * threadRow + i));
        }
    }

    public MatrixThread(ArrayList<Integer> matris1, ArrayList<Integer> matris2, int[][] finalMatrix, int totalRowNumber, int totalColomnNumber, int threadRow) {
        this.m1 = matris1; //Matris1.txt
        this.m2 = matris2; //Matris2.txt
        this.f1 = finalMatrix; //Sonuç
        this.totalRowNumber = totalRowNumber; //ilk matrisin toplan satır sayısı
        this.totalColomnNumber = totalColomnNumber; //ikinci matrisin toplam colon sayısı
        this.threadRow = threadRow; //threadin sorumlu olduğu satır numarası
    }

}
