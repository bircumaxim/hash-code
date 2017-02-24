package com.company;

import com.jakewharton.fliptables.FlipTableConverters;

public class Main {

    public static void main(String[] args) {
        Database database = DataHelper.read("data.in");

        CostComputing costComputing = new CostComputing(database);
        int[][] cost = costComputing.getCacheServerToEndPointCost();
        int[][] matrix = costComputing.getCacheServerToEndPointCostForRequests();
        int[] totalRequestsForVideos = costComputing.getTotalRequestsForVideos();


        System.out.println("Costs for cache - endpoint");
        PrettyPrint.print(cost);
        System.out.println("Costs for requests");
        PrettyPrint.print(matrix);

        System.out.println("Total videos requests");
        PrettyPrint.print(totalRequestsForVideos);

        DataHelper.write("data.out",database);
    }
}
