253. Meeting Rooms II

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
Hint
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

 

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
 

Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106


class Solution {
    public int minMeetingRooms(int[][] intervals) {

    	int[] starts = new int[intervals.length];
    	int[] ends = new int[intervals.length];

    	int i=0;
    	for(int[] interval : intervals){
    		starts[i]=interval[0];
    		ends[i]=interval[1];
    		i++;
    	}

    	Arrays.sort(starts);
    	Arrays.sort(ends);

    	int s = 0, e = 0;
    	int res = 0;
    	while(s<intervals.length){
    		if(starts[s]<ends[e]){
    			res++;
    		}
    		else{
    			e++;
    		}
    		s++;
    	}

    	return res;

    }
}


Code
Testcase
Test Result
Test Result
122. Best Time to Buy and Sell Stock II

avatar
Discuss Approach
arrow-up
Solved
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

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

 

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4




1 3  2 4  5 9  0 9

1 3  2 4  0 9  5 9

0 9  1 3  2 4  5 9


1 4  0 4

0 4  1 4



540. Single Element in a Sorted Array

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

 

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10

class Solution {
    public int singleNonDuplicate(int[] nums) {
        
    }
}

131. Palindrome Partitioning

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.

class Solution {
    public List<List<String>> partition(String s) {
        
    }
}



                       {aab}


                a|{ab}


         a|a|{b}      


    a|a|b



a|a|b



416. Partition Equal Subset Sum

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100


class Solution {
    public boolean canPartition(int[] nums) {
         int sum = 0;
         for(int x : nums)sum+=x;
         if(sum%2!=0)return false;
         sum/=2;
         return solve(0,sum,nums);
    }

    public boolean solve(int i, int sum, int[] nums){
        if(sum==0)return true;
        if(i==nums.length)return false;

        boolean notTake = solve(i+1,sum,nums);
        boolean take = false;
        if(nums[i]<=sum){
            take = solve(i+1,sum-nums[i],nums);
        }
        return take || notTake;
    }
}907. Sum of Subarray Minimums

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

 

Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444
 

Constraints:

1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104


class Solution {
    public int sumSubarrayMins(int[] arr) {
        int[] leftMin = findLeftMins(arr);
        int[] rightMin = findRightMins(arr);

        long minSum = 0;
        int mod = (int) 10000000;
        for(int i = 0; i < arr.length; i++){
            minSum += (((rightMin[i]-i)*(i-leftMin[i])) * arr[i]) % mod;
        }
        return (int)minsum;
    }

    public int[] findLeftMin(int[] arr){

        Stack<Integer> st = new Stack<>();

        int[] res = new int[];

        for(int i = 0; i < arr.length; i++){

            while(!st.isEmpty() && arr[st.peek()]>=arr[i]){
                st.pop();
            }

            if(st.isEmpty()){
                res[i] = -1;
            } else{
                res[i] = st.peek();
            }

            st.push(i);
        }

        return res;
    }

    public int[] findRightMin(int[] arr){

        Stack<Integer> st = new Stack<>();

        int[] res = new int[];

        for(int i = arr.length-1; i >= 0; i--){

            while(!st.isEmpty() && arr[st.peek()]>=arr[i]){
                st.pop();
            }

            if(st.isEmpty()){
                res[i] = arr.length;
            } else{
                res[i] = st.peek();
            }

            st.push(i);
        }

        return res;
    }
}


   3  1  2  4

   3  4  6  10

   17 14 10 4


3+4+6+4


11 81 94  43  3

11 92 186 229 232

243 232  140  46  3 


11


79. Word Search

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 

Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
 

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 

Follow up: Could you use search pruning to make your solution faster with a larger board?

  word = "ABCCED"

["A","B","C","E"]
["S","F","C","S"]
["A","D","E","E"]

Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.


49. Group Anagrams

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

 

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
Example 2:

Input: strs = [""]

Output: [[""]]

Example 3:

Input: strs = ["a"]

Output: [["a"]]

 

Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 
Seen this question in a real interview before?
1/5
Yes
No
Accepted
4,033,334/5.7M
Acceptance Rate
71.3%
