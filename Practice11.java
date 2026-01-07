Given a weighted, undirected, and connected graph with V vertices and E edges, the task is to find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of the graph using Kruskal's Algorithm. The graph is represented as an edge list edges[][], where edges[i] = [u, v, w] denotes an undirected edge between u and v with weight w.

Input: V = 3, E = 3, edges[][] = [[0, 1, 5], [1, 2, 3], [0, 2, 1]]

Output: 4
Explanation:

The Spanning Tree resulting in a weight of 4 is shown above.


class Solution {

	public int findParent(int element, int[] parent){

		if(parent[element]==element)return element;

		return parent[element] = findParent(parent[element],parent);
	}


	public void find(){
		
	}

    static int kruskalsMST(int V, int[][] edges) {
        
    }
}


// User function Template for Java
class Solution {
    int minCost(int[] height) {
        int n = height.length;
        int[] dp = new int[n];

		if(n==0){
			return dp[0] = 0;
		}        
		if(n==1){
			return dp[1] = height[1]-height[0];
		}
		for(int i = 2; i < n; i++){
			dp[i] = Math.min(dp[i-1]+Math.abs(height[i]-height[i-1]),dp(i-2)+Math.abs(height[i]-height[i-2]);
    }
		}
		return dp[n-1];
}


3165. Maximum Sum of Subsequence With Non-adjacent Elements

avatar
Discuss Approach
arrow-up
Hard
Topics
premium lock icon
Companies
Hint
You are given an array nums consisting of integers. You are also given a 2D array queries, where queries[i] = [posi, xi].

For query i, we first set nums[posi] equal to xi, then we calculate the answer to query i which is the maximum sum of a subsequence of nums where no two adjacent elements are selected.

Return the sum of the answers to all queries.

Since the final answer may be very large, return it modulo 109 + 7.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Example 1:

Input: nums = [3,5,9], queries = [[1,-2],[0,-3]]

Output: 21
class Solution {
    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        
    	int res = 0;
        for(int[] query : queries){

        	int temp = nums[query[0]];
        	nums[query[0]] = query[1];

        	int prev = nums[0] <= 0 ? 0 : nums[0];
        	int prev2 = 0;
        	int curri = 0;
        	for(int i = 1; i < nums.length; i++){
        		int pick = prev2;
        		if(nums[i]>=0)pick+=nums[i];
        		int notPick = prev;

        		curri = Math.max(pick,notPick);
        		prev2 = prev;
        		prev = curri;
        	}
        	res+=prev;
        	nums[query[0]]=temp;
        }

        return res;
    }
}


class Solution {
    public int rob(int[] nums) {
        int prev = nums[0];
        int prev2 = 0;
        int curri = 0;
        for(int = 1; i < nums.length; i++){

        	int pick = nums[i]+prev2;
        	int notPick = 0 + nums[i-2];

        	curri = Math.max(pick,notPick);
        	prev2 = prev;
        	prev = curri;
        }

        return curri;


    }
}


Problem statement
Ninja has a 'GRID' of size 'R' X 'C'. Each cell of the grid contains some chocolates. Ninja has two friends Alice and Bob, and he wants to collect as many chocolates as possible with the help of his friends.

Initially, Alice is in the top-left position i.e. (0, 0), and Bob is in the top-right place i.e. (0, ‘C’ - 1) in the grid. Each of them can move from their current cell to the cells just below them. When anyone passes from any cell, he will pick all chocolates in it, and then the number of chocolates in that cell will become zero. If both stay in the same cell, only one of them will pick the chocolates in it.

If Alice or Bob is at (i, j) then they can move to (i + 1, j), (i + 1, j - 1) or (i + 1, j + 1). They will always stay inside the ‘GRID’.

Your task is to find the maximum number of chocolates Ninja can collect with the help of his friends by following the above rules.


import java.util.* ;
import java.io.*; 
public class Solution {
	public static int maximumChocolates(int r, int c, int[][] grid) {
		
	}
}


Problem statement
You are given an array/list ‘ARR’ of ‘N’ positive integers and an integer ‘K’. Your task is to check if there exists a subset in ‘ARR’ with a sum equal to ‘K’.

Note: Return true if there exists a subset with sum equal to ‘K’. Otherwise, return false.

For Example :
If ‘ARR’ is {1,2,3,4} and ‘K’ = 4, then there exists 2 subsets with sum = 4. These are {1,3} and {4}. Hence, return true.

import java.util.* ;
import java.io.*; 
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        return solve(n-1,k,arr);
    }

    public static boolean solve(int i, int target, int[] arr){
    	if(i==0){
    		return arr[i]==target;
    	}

    	if(target==0)return true;

    	boolean notTake = solve(i-1,target,arr);
    	boolean take = false;
    	take = solve(i-1, target-arr[i], arr);

    	return notTake || take;
    }
}


import java.util.* ;
import java.io.*; 
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
		int[][] dp = new int[n][target];
		for(int[] arr : dp)Arrays.fill(arr,-1);
        return solve(n-1,k,arr,dp);
    }

    public static boolean solve(int i, int target, int[] arr, int[] dp){
		if(target==0)return true;
    	if(i==0){
    		return arr[i]==target;
    	}

    	
		if(dp[i][target]!=-1)return false;
    	boolean notTake = solve(i-1,target,arr);
    	boolean take = false;
    	if(target>=arr[i]) take = solve(i-1, target-arr[i], arr);
		dp[i][target] = 1;
    	return notTake || take;
    }
}



