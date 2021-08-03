package com.mycompany.atsp_excercise;

import ilog.concert.*;
import ilog.cplex.*;

import java.awt.Color;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

public class ATSP_Model {

    public static void solveATSP(int n, double[][] dist) {

        double[][] c = dist;

        //Modeling
        try {
            IloCplex cplex = new IloCplex();
            // Variables
            IloNumVar[][] x = new IloNumVar[n][];
            for (int i = 0; i < n; i++) {
                x[i] = cplex.boolVarArray(n);
            }
            IloNumVar[] u = new IloNumVar[n];
            for (int i = 0; i < n; i++) {

                u[i] = cplex.numVar(0, Double.MAX_VALUE);

            }

            //Objective
            IloLinearNumExpr objective = cplex.linearNumExpr();
            for (int i = 0; i < n; i++) {
                int pos_i = i + 1;
                for (int j = 0; j < n; j++) {

                    int pos_j = j + 1;
                    x[i][j] = cplex.boolVar("x[" + pos_i + "][" + pos_j + "]");
                    objective.addTerm(c[i][j], x[i][j]);

                }
            }
            cplex.addMinimize(objective);

            //Constraints
            for (int j = 0; j < n; j++) {//degree constraints.
                IloLinearNumExpr exp = cplex.linearNumExpr();
                for (int i = 0; i < n; i++) {
                    if (i != j) {
                        exp.addTerm(1, x[i][j]);
                    }
                }
                cplex.addEq(exp, 1);
            }

            for (int i = 0; i < n; i++) {//degree constraints.
                IloLinearNumExpr exp = cplex.linearNumExpr();
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        exp.addTerm(1, x[i][j]);
                    }
                }
                cplex.addEq(exp, 1);
            }

            //WARNING:if i,j=0, this is the Depot.
            for (int i = 1; i < n; i++) {//connectivity constraints).
                for (int j = 1; j < n; j++) {
                    if (i != j) {
                        IloLinearNumExpr exp = cplex.linearNumExpr();
                        exp.addTerm(1, u[i]);
                        exp.addTerm(-1, u[j]);
                        exp.addTerm((n - 1), x[i][j]);
                        cplex.addLe(exp, (n - 2));
                    }
                }
            }

            //Solving
            cplex.solve();
            System.out.println();
            System.out.println("Solution status = " + cplex.getStatus());
            System.out.println();
            cplex.exportModel("ATSP_Exercise.lp");
            System.out.println("Optimal value = " + cplex.getObjValue());

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    if (cplex.getValue(x[i][j]) != 0) {
                        System.out.println("x[" + i + "][" + j + "] = " + cplex.getValue(x[i][j]));
                    }

                }
                //   System.out.println("u[" + i + "] = "+ cplex.getValue(u[i]));

            }

        } catch (IloException exc) {
            exc.printStackTrace();
        }
    }

}
