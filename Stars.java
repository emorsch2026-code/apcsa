/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stars;

/**
 *
 * @author EMorsch2026
 */
public class Stars {

   public static void main(String[] args) {

        final int MAX_ROWS = 10;

        
        // Pattern A
        System.out.println("a.");
        for (int row = MAX_ROWS; row >= 1; row--) {
            for (int star = 1; star <= row; star++) {
                System.out.print("*");
            }
            System.out.println();
        }


        // Pattern B
        System.out.println("\nb.");
        for (int row = 1; row <= MAX_ROWS; row++) {
            // spaces
            for (int space = 1; space <= MAX_ROWS - row; space++) {
                System.out.print(" ");
            }
            // stars
            for (int star = 1; star <= row; star++) {
                System.out.print("*");
            }
            System.out.println();
        }


        // Pattern C
        System.out.println("\nc.");
        for (int row = MAX_ROWS; row >= 1; row--) {
            // increasing spaces
            for (int space = 1; space <= MAX_ROWS - row; space++) {
                System.out.print(" ");
            }
            // decreasing stars
            for (int star = 1; star <= row; star++) {
                System.out.print("*");
            }
            System.out.println();
        }


        // Pattern D
        System.out.println("\nd.");

        final int HALF = 5;   // half-height for the diamond shape

        // top half
        for (int row = 1; row <= HALF; row++) {
            for (int space = 1; space <= HALF - row; space++) {
                System.out.print(" ");
            }
            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // bottom half
        for (int row = HALF; row >= 1; row--) {
            for (int space = 1; space <= HALF - row; space++) {
                System.out.print(" ");
            }
            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

