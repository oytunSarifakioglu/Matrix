/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nesneproje1matristhread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Oytun
 */
public class MatrixReader {

    public static void main(String[] args) {
        File matris1File = new File("Matris1.txt");
        File matris2File = new File("Matris2.txt");
        int count1 = 0;
        ArrayList<Integer> matris1ArrayList = new ArrayList<>();
        ArrayList<Integer> matris2ArrayList = new ArrayList<>();
        int count2 = 0;
        try {
            Scanner scanner1 = new Scanner(matris1File); //matris 1 okunması

            while (scanner1.hasNextLine()) { //satır sayısı
                count1++;
                scanner1.nextLine();
            }
            scanner1.reset();
            scanner1 = new Scanner(matris1File);

            while (scanner1.hasNext()) {
                matris1ArrayList.add(scanner1.nextInt());
            }
            System.out.print(matris1ArrayList.toString());
            System.out.println("Number of rows M1=" + count1);
        } catch (FileNotFoundException e) {
        }
        try {
            Scanner scanner2 = new Scanner(matris2File);//matris 2 okunması

            while (scanner2.hasNextLine()) {
                count2++;
                scanner2.nextLine();
            }
            scanner2.reset();
            scanner2 = new Scanner(matris2File);

            while (scanner2.hasNext()) {
                matris2ArrayList.add(scanner2.nextInt());
            }
            System.out.print(matris2ArrayList.toString());
            System.out.println("Number of rows M2=" + count2);
        } catch (FileNotFoundException e) {
        }

        try{
            count2 = matris2ArrayList.size() / count2;//sütun sayısı bulunuyor
        }catch(ArithmeticException ae){

        }
        int[][] finalMatrisArray = new int[count1][count2];

        System.out.println("Final matrix rows=" + count1);
        Thread[] matrixThreadList = new Thread[count1]; //threadlerin tutulacağı erişileceği array

        System.out.println("Final matrix colomn=" + count2);

        for (int i = 0; i < count1; i++) {  //threadler burada oluşturuluyor
            matrixThreadList[i] = new Thread(new MatrixThread(matris1ArrayList, matris2ArrayList, finalMatrisArray, count1, count2, i));
            System.out.println("Threads created:" + i);
        }
        for (int i = 0; i < count1; i++) { //
            matrixThreadList[i].start();
        }
        for (int i = 0; i < count1; i++) {
            try {
                matrixThreadList[i].join();
            } catch (Exception e) {
            }

        }
        for (int i = 0; i < count1; i++) {
            for (int j = 0; j < count2; j++) {
                System.out.printf("%5d ", finalMatrisArray[i][j]);
            }
            System.out.println();
        }

    }
}
