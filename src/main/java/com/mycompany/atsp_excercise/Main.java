
package com.mycompany.atsp_excercise;

import Utility.Dati;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {

//WARNING:node j=0, which is the Depot
    public static void main(String[] args) throws FileNotFoundException {
        System.setOut(new PrintStream("ATSP_Exercise.log"));
        int size = Dati.Problem_Size();
        double[][] distances = Dati.Distances();
        ATSP_Model.solveATSP(size, distances);
    }

}
