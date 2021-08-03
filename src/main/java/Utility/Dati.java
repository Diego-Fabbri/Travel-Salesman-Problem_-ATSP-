/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author diego
 */
public class Dati {
//If you want to re-use this code, you have just to modify input data in this class
    public static int Problem_Size() {
        return 6;
    }

    public static double[][] Distances() {
        //WARNING: elements in distances[][0] concern the node j=0, which is the Depot
        double[][] distances = {
            {Double.MAX_VALUE, 33.6, 14, 40.9, 14.5, 11.5},
            {34.7, Double.MAX_VALUE, 21.7, 13, 20.2, 23.4},
            {14.8, 21.5, Double.MAX_VALUE, 29.3, 2, 3.9},
            {41.7, 13.1, 29.4, Double.MAX_VALUE, 27.6, 30.3},
            {15, 20.2, 2, 27.5, Double.MAX_VALUE, 3.9},
            {12, 22.8, 2, 30.1, 4, Double.MAX_VALUE},};

        return distances;
    }
}

