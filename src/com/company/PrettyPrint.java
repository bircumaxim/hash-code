package com.company;

import com.jakewharton.fliptables.FlipTableConverters;

/**
 * Created by nicu on 2/24/17.
 */
public class PrettyPrint {

    public static void print(int[][] data){
        System.out.println(FlipTableConverters.fromObjects(PrettyPrint.getRowColumn(data),PrettyPrint.convert(data)));
    }

    public static String[][] convert(int[][] data){
        int columns = data.length;
        int rows = columns == 0 ? 0 : data[0].length;
        String[][] strings = new String[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                strings[i][j] = String.format("%d",data[i][j]);
            }
        }

        return strings;
    }

    public static String[] getRowColumn(int[][] matrix){
        int size = matrix.length > 0 ? matrix[0].length : 0;
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = String.format("%d",i);
        }

        return strings;
    }
}
