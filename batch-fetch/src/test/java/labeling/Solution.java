package labeling;

import java.util.Arrays;


public class Solution {

  public int minCostII(int[][] costs) {
    int housesLength = costs.length;
    int colorsLength = costs[0].length;

    for (int currentHouse = 1; currentHouse < housesLength; currentHouse++) {
      for (int currentColor = 0; currentColor < colorsLength; currentColor++) {
        // Find the minimum cost of painting the previous house with a different color
        int minPrevCost = Integer.MAX_VALUE;
        for (int m = 0; m < colorsLength; m++) {
          if (m != currentColor) {
            minPrevCost = Math.min(minPrevCost, costs[currentHouse - 1][m]);
          }
        }
        // Update the current cost with the minimal cost found
        costs[currentHouse][currentColor] += minPrevCost;
      }
    }
    // Find the minimum cost from the last row (last house)
    return Arrays.stream(costs[housesLength - 1])
            .min()
            .orElse(0);
  }

  public static void main(String[] args) {
    final var solution = new Solution();
    System.out.println(solution.minCostII(new int[][]{{1, 2}, {2, 3}})); // 4
    System.out.println(solution.minCostII(new int[][]{{1, 2, 0}, {2, 3, 5}})); // 2
    System.out.println(solution.minCostII(new int[][]{{1, 5, 3}, {2, 9, 4}})); // 5
    System.out.println(solution.minCostII(new int[][]{{1, 5, 1}, {2, 9, 1}})); // 2
    System.out.println(solution.minCostII(new int[][]{{1, 5, 1, 0}, {2, 9, 1, 0}})); // 1
    System.out.println(solution.minCostII(new int[][]{{1, 5, 1, 0}, {2, 9, 1, 0}, {2, 9, 0, 1}})); // 1
  }
}
