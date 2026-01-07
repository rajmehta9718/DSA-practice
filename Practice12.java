import java.util.*;

public class Solution {
    public static boolean subsetSumToK(int n, int k, int[] arr) {
        boolean[][] dp = new boolean[n][k + 1]; // k+1 to handle target = k

        for(int x = 0; x < n; x++){
            dp[x][0]=true;
        }

        if(arr[0]<=target) dp[0][arr[0]]=true;
    
        
        for(int i = 1; i < n; i++){
            for(int target = 0; target <= k; target++){

                boolean notTake = dp[i - 1][target];
                boolean take = false;
                if (target >= arr[i]) {
                take = dp[i - 1][target - arr[i]];
                dp[i][target] = notTake || take
            }

        }    
        
        return dp[n-1][k];
    }
}


class Solution {
    public boolean canPartition(int[] nums) {
       int total = 0;
       for(int i : nums)total+=i;
        if(total%2==1)return false;
       total/=2;
       return solve(nums,nums.length-1,total); 
    }

    public boolean solve(int[] nums, int j, int target){
        if(sum==half)return true;
        if(j==0){
            return nums[i]==sum
        }


        boolean notTake = solve(nums,j-1,target);
        boolean take = solve(nums,j-1,target-nums[j]);

        return notTake || take;
    }
}





2035. Partition Array Into Two Arrays to Minimize Sum Difference

avatar
Discuss Approach
arrow-up
Hard
Topics
conpanies icon
Companies
Hint
You are given an integer array nums of 2 * n integers. You need to partition nums into two arrays of length n to minimize the absolute difference of the sums of the arrays. To partition nums, put each element of nums into one of the two arrays.

Return the minimum possible absolute difference.

 

Example 1:

example-1
Input: nums = [3,9,7,3]
Output: 2
Explanation: One optimal partition is: [3,9] and [7,3].
The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.
Example 2:

Input: nums = [-36,36]
Output: 72
Explanation: One optimal partition is: [-36] and [36].
The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.
Example 3:

example-3
Input: nums = [2,-1,0,4,-2,-9]
Output: 0
Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.
 

class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        int target = 0;
        for(int i : nums)target+=i;
        boolean[] dp = new boolean[target+1];

        dp[i][0] = true;

        if(arr[0]<=target)dp[arr[0]]=true;

        for(int i = 1; i < n; i++){
            boolean[] temp = new boolean[target+1];
            temp[0] = true;
            for(int j = 1; j < target; j++){
                boolean notTake = dp[j];
                boolean take = false;
                if(j>=nums[i]) take = dp[j]-nums[i]];
                temp[i]= take || notTake;
            }
            dp = temp;
        }

        int minDiff = Integer.MAX_VALUE;

        for(int i = 0; i < total/2; i++){
            if(dp[i]){
                int s1 = i;
                int s2 = total-i;
            }   

            minDiff = Math.min(minDiff,Math.abs(s1-s2));
            
        }

        return minDiff;
    }
}

322. Coin Change
Solved

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

 

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0
 

Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104

class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount+1];
        
        for(int i = 0; i <= amount; i++){
            if(i%coins[0]==0)dp[i] = i/coins[0];
            else dp[i] = amount+1;
        }
        for(int i = 1; i < n; i++){
            int[] temp = new int[amount+1];
            for(int x = 0; x <= amount; x++){
            if(x%coins[i]==0)temp[x] = x/coins[i];
            else temp[i] = amount+1;
             }
            for(int target = 0; target <= amount; target++){
                int notTake = 0 + dp[target];
                int take = amount+1;
                if (target >= coins[i])take = 1 + temp[target - coins[i]];
                dp[target] = Math.min(take,notTake);
            }
            dp = temp;
        }
        
        return dp[amount] >= amount+1 ? -1 : dp[amount];
    }
}


494. Target Sum
Solved

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

 

Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1
 

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000


class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int total = 0;
        for(int i : nums)total+=i;

        if((total-target)<0 || (total-target)%2!=0)return 0;
        
        int sum = (total-target)/2;
        int[] dp = new int[sum+1];
        
        if(arr[0]==0)dp[0]=2;
        if(arr[0]<=sum)dp[arr[0]]=1;
        
        for(int i = 1; i < n; i++){
        int[] temp = new int[sum+1];
            for(int j = 0; j <= sum; j++){
                int notTake = dp[j];
                int take = 0;
                if(j>=nums[i]) take = temp[j-nums[i]];
                temp[j] = notTake+take;
            }
            dp = temp;
        }
        
        return dp[target];
    }
}

s1-s2=target
total-s2-s2 = target
total-2s2=target
s2 = total-target


518. Coin Change II

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

 

Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1
 

Constraints:

1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000


class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount+1];
        dp[0]=1;
        for(int i = 1; i <= amount; i++){
            if(i%coins[0]==0)dp[i] = 1;
        }
        
        for(int i = 1; i < n; i++){
            int[] temp = new int[amount+1];
            for(int j = 0; j <= amount; j++){
                int notTake = dp[j];
                int take = 0;
                if(j>=coins[i])take = dp[j-coins[i]];

                temp[j] = take+notTake;
            }
            dp = temp;
        }

        return dp[n-1][amount];
        
        
    }
}



122. Best Time to Buy and Sell Stock II
Solved

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 

Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104


class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        
        
        int profit = 0;
        dp[n][0] = dp[n][1] = 0;
        for(int i = n-1; i >= 0; i--){
            for(int buy = 0; buy <= 1; buy++){
                if (buy == 1) {
                profit = Math.max(-prices[i] + dp[i + 1][0], 0 + dp[i + 1][1];
                } else {
                profit = Math.max(prices[i] + dp[i + 1][1], 0 + dp[i + 1][0];
                }        
            }
        }
        
        return dp[i][buy] = profit;
    }
}


123. Best Time to Buy and Sell Stock III

avatar
Discuss Approach
arrow-up
Hard
Topics
conpanies icon
Companies
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.


buy = 1
{
    
}


Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105


class Solution {
    public int maxProfit(int[] prices) {
       
       int[][][] dp = new int[n+1][2][3];

        for(int i = n-1; i >= 0; i--){
            for(int buy = 0; buy <= 1; buy++){
                for(int cap = 2; cap>0; cap--){
                if (buy == 1) {
                profit = Math.max(-prices[i] + dp[i + 1][0][cap], 0 + dp[i + 1][1][cap];
                } else {
                profit = Math.max(prices[i] + dp[i + 1][1][cap-1], 0 + dp[i + 1][0][cap];
                }        
            }
        }
        }
       return dp[0][1][0];
    }
}

buy and sell stock IV

class Solution {
    public int maxProfit(int k, int[] prices) {
        
       int[][] dp = new int[2][k];

        for(int i = n-1; i >= 0; i--){
            int[][] temp = new int[2][k];
            for(int buy = 0; buy <= 1; buy++){
                for(int cap = k; cap>0; cap--){
                if (buy == 1) {
                temp[i][buy] = Math.max(-prices[i] + dp[0][cap], 0 + dp[1][cap];
                } else {
                temp[i][buy] = Math.max(prices[i] + dp[1][cap-1], 0 + dp[0][cap];
                }        
            }
        }
        }
       return dp[1][k];
    }
}


309. Best Time to Buy and Sell Stock with Cooldown

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0
 

Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        
        
        for(int i = n-1; i>= 0; i--){
            for(int buy = 0; buy<=1; buy++){
               if (buy == 1) {
                return dp[i][buy] = Math.max(-prices[i] + dp[i + 1][0], 0 + dp[i + 1][1]);
                } else {
                return dp[i][buy] = Math.max(prices[i] + dp[i + 2][1], 0 + dp[i + 1][0]);
        } 
            }
        }
        
    }
}


714. Best Time to Buy and Sell Stock with Transaction Fee

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Hint
You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.
 

Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6
 

Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] dp = new int[2];
        
        
        for(int i = n-1; i>= 0; i--){
            int[] temp = new int[2];
            for(int buy = 0; buy<=1; buy++){
                 temp[1] = Math.max(-prices[i] + dp[0], 0 + dp[1]);
                 temp[0] = Math.max(prices[i] - fee + dp[1], 0 + dp[0]);
        } 
            dp = temp;
        }
        return dp[1];
    }
}

class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int position = find(nums[i], list);
            if (position == list.size())
                list.add(nums[i]);
            else {
                list.set(position, nums[i]);
            }
        }
    }
    
    public int find(int value, List<Integer> list) {
        int left = 0, right = list.size() - 1;
        int res = list.size();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) >= value) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}



class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        int last = -1;
        int max = 0;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],1+dp[j]);
                }
            }
            if(dp[i]>max){
            max = dp[i];
            last= i;
            }
        }

        int[] lis = new int[max];
        int ind = 0;
        while(hash[last]!=last){
            last = hash[last];
            lis[ind++] = nums[last];
        }
        

    }
}



class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        int[] hash = new int[n];
        for(int i = 0; i < n; i++){
            hash[i]=i;
        }
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[i]>nums[j]){
                    if(1+dp[j]>dp[i]){
                        dp[i]=1+dp[j];
                        hash[i] = j;
                    }
                }
            }
        }

        for(int i = 0; i < n; i++)

        return max;
    }
}


Longest Bitonic subsequence

avatar
Discuss Approach
arrow-up
Difficulty: MediumAccuracy: 47.34%Submissions: 133K+Points: 4
Given an array of positive integers. Find the maximum length of Bitonic subsequence. 
A subsequence of array is called Bitonic if it is first strictly increasing, then strictly decreasing. Return the maximum length of bitonic subsequence.
 
Note : A strictly increasing or a strictly decreasing sequence should not be considered as a bitonic sequence

Examples :

Input: n = 5, nums[] = [1, 2, 5, 3, 2]
Output: 5
Explanation: The sequence {1, 2, 5} is increasing and the sequence {3, 2} is decreasing so merging both we will get length 5.
Input: n = 8, nums[] = [1, 11, 2, 10, 4, 5, 2, 1]
Output: 6
Explanation: The bitonic sequence {1, 2, 10, 4, 2, 1} has length 6.
Input: n = 3, nums[] = [10, 20, 30]
Output: 0
Explanation: The decreasing or increasing part cannot be empty
Input: n = 3, nums[] = [10, 10, 10]
Output: 0
Explanation: The subsequences must be strictly increasing or decreasing
Constraints:
1 ≤ length of array ≤ 103
1 ≤ arr[i] ≤ 104



class Solution {
    public static int LongestBitonicSequence(int n, int[] nums) {
        int n = nums.length;
        int[] dp1 = new int[n];
        Arrays.fill(dp1,1);

        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[i]>nums[j] && dp1[j]+1 > dp1[i]){
                    dp1[i]=dp1[j]+1;
                }
            }
        }

        int[] dp2 = new int[n];
        Arrays.fill(dp2,1);
        for(int i = n-1; i >= 0; i--){
            for(int j = n; j > i; j--){
                if(nums[i]>nums[j] && dp2[j]+1 > dp2[i]){
                    dp2[i]=dp2[j]+1;
                }
            }
        }

        int res = 0;

        for(int i = 0; i < n; i++){
            res = Math.max(dp1[i]+dp2[i]-1,res);
        }

        return res;
    }
}


673. Number of Longest Increasing Subsequence

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

 

Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
 

Constraints:

1 <= nums.length <= 2000
-106 <= nums[i] <= 106
The answer is guaranteed to fit inside a 32-bit integer.

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        Arrays.fill(dp,1);
        Arrays.fill(cnt,1);

        int max = 1;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[i]>nums[j]){
                
                    if(dp[j]+1 > dp[i]){
                        dp[i] = dp[j]+1;
                        cnt[i] = cnt[j];

                    }
                    else if(dp[j]+1==dp[i]){
                    cnt[i]++;
                    }
                

            }
        }
        max = Math.max(max,cnt[i]);
        }

        return max;


    }
}



class Solution {
    static int matrixMultiplication(int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        int min = Integer.MAX_VALUE;

        for(int i = n-1; i>=0; i--){
            for(int j = i+1; j < n; j++){
                int min = Integer.MAX_VALUE;
                for(int k = i; k <= j-1; k++){

                    int steps  = (arr[i-1]*arr[k]*arr[j])+dp[i][k]+dp[k+1][j];
                    min = Math.min(min,steps);
                }
                dp[i][j]=min;
            }

        }
        

        return dp[1][n-1];
    }
}


class Solution {
    public int minCost(int n, int[] cuts) {
        
        Arrays.sort(cuts);
        int[] newCuts = new int[cuts.length + 2];
        newCuts[0] = 0;
        System.arraycopy(cuts, 0, newCuts, 1, cuts.length);
        newCuts[newCuts.length - 1] = n;
        int[][] dp = new int[n + 1][n + 1];
        for (int[] arr : dp){
        Arrays.fill(arr, -1);
        }
        return solve(1, cuts.length-1, newCuts, dp);
    }

    public int solve(int i, int j, int[] cuts, int[][] dp) {
        if (i > j)
            return 0;

        int min = Integer.MAX_VALUE;

        if (dp[i][j] != -1)
            return dp[i][j];
        for (int p = i; p <= j; p++) {

                int cost = (cuts[j+1] - cuts[i-1]) + solve(i, p-1, cuts, dp) + solve(p+1, j, cuts, dp);
                min = Math.min(cost, min);
            }

        }
        return dp[i][j] = min;
    }
}


312. Burst Balloons

avatar
Discuss Approach
arrow-up
Hard
Topics
conpanies icon
Companies
You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.

 

Example 1:

Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = 

[3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []



coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
Example 2:

Input: nums = [1,5]
Output: 10
 

Constraints:

n == nums.length
1 <= n <= 300
0 <= nums[i] <= 100


import java.util.Arrays;

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] mynums = new int[n + 2];
        int[][] dp = new int[n + 1][n + 1];

        // Fill with -1 for memoization
        
        // Add 1 at both ends
        mynums[0] = 1;
        mynums[n + 1] = 1;
        System.arraycopy(nums, 0, mynums, 1, n);
        for(int i = n; i>=1; i--){
            for(int j = 1; j<=n; j++){
                int max = 0;
                for (int k = i; k <= j; k++) {
                int cost = mynums[i - 1] * mynums[k] * mynums[j + 1]
                     + dp[i][k - 1]
                     + dp[k + 1][j];
                dp[i][j] = Math.max(dp[i][j], cost);
                }
            }
        }


        

        return dp[1][n];
    }
}
