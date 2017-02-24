package com.company;

public class Main {

    public static void main(String[] args) {
        Database database = DataHelper.read("data.in");

        CostComputing costComputing = new CostComputing(database);
        int[][] cost = costComputing.getCacheServerToEndPointCost();
        int[][] matrix = costComputing.getCacheServerToEndPointCostForRequests();


        System.out.println("Costs for cache - endpoint");
        PrettyPrint.print(cost);
        System.out.println("Costs for requests");
        PrettyPrint.print(matrix);


        DataHelper.write("data.out",database);
    }
}
