class Solution {

    public boolean findOut(int dist, int[] position, int m) {
        int currPos = position[0];

        int balls = 0;
        for(int i = 1; i < position.length; i++){
        if(position[i]-curr[pos]>=dist){
            if(balls>=m)return true;
            balls++;
            currPos = position[i];
        }

        }
        return balls>=m;
    }

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);

        int left = position[0], right = position[position.length0-1];

        while (left <= right) {

            int mid = left + (right - left) / 2;
            boolean canPlace = findOut(mid, positions, m);

            if (canPlace) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

}


45. Jump Game II

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].

class Solution {
    public int jump(int[] nums) {
        
        int n = nums.length;
        return solve(0, nums);
    }

    public int solve(int idx, int[] nums){
        if(idx==nums.length-1){
            return 0;
        }
        if(idx>=nums.length){
            return Integer.MAX_VALUE;
        }
        
        int minJumps = Integer.MAX_VALUE;
        for(int i = 0; i <= nums[idx]; i++){
            minJumps = Math.min(minJumps,i + solve(idx+i,nums));
        }
        return minJumps;
    }
}


1010
1001

1100
1011


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return solve(0,s,wordDict);
    }

    public boolean solve(int idx, String s, List<String> wordDict){
        if(idx==s.length()-1){
            if(wordDict.contains(sb))return true;
            else return false;
        }

        for(int i = idx; i<s.length(); i++){

            if(wordDict.contains(idx,i+1)){
                return solve(i+1,s,wordDict);
            }

        }
    }
}


Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

 

                    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false



                                 catsandog





                                          c


                                    ca


                                cat


                          cats


                      a



                 an



            and
           



        o



    og     



904. Fruit Into Baskets

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

 

Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
 

Constraints:

1 <= fruits.length <= 105
0 <= fruits[i] < fruits.length


class Solution {
    public int totalFruit(int[] fruits) {
        int i = 0, j = 0;
        int n = fruits.length;
        int count1 = 0, count2 = 0;
        int basket1 = -1, basket2 = -1;
        int count = 0;
        while(j<n){
            if(fruits[j]==basket1){
                count1++;
            } else if(fruits[j]==basket2){
                count2++;
            } else{
                if(basket1==-1){
                   basket1 = fruits[j];
                   count1 = 1;
                } else if(basket2==-1){
                   basket2 = fruits[j];
                   count2 = 1;
                } else{
                   while(count1>0 && count2>0){
                    if(fruits[i]==basket1){
                        count1--;
                    }
                    else{
                        count2--;
                    }
                    i++;
                   }

                   if(count1==0){
                     basket1=fruits[j];
                     count1 = 1;
                   } else{
                     basket2=fruits[j];
                     count2 = 1;
                   }
                }
            }

            count = Math.max(count,count1+count2);
            j++;
        }

        return count;
    }
}

[1,0,3,4,3]

b1 = 3  c1 = 1
b2 = 0  c2 = 1

[1,0,1,4,1,4,1,2,3]

b1 =  1  c1 = 2
b2 =  0  c2 = 1



1011. Capacity To Ship Packages Within D Days

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
Hint
A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

 

Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
Example 2:

Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4
Example 3:

Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1
 

Constraints:

1 <= days <= weights.length <= 5 * 104
1 <= weights[i] <= 500

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        
    }
}


328. Odd Even Linked List

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.

 

Example 1:


Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
Example 2:


Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]
 

Constraints:

The number of nodes in the linked list is in the range [0, 104].
-106 <= Node.val <= 106




/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode res = new ListNode(0);
        res.next = head;

        ListNode curr = head;

        while(curr!=null && curr.next!=null){

            ListNode nextNode = curr.next;
            curr.next = curr.next.next;
            curr = curr.next;
            curr.next = oddEvenList(nextNode);
        }

        return head;

    }
}


[1,2,3,4,5]

1->3->2->3->4->5

Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

 

Example 1:

Input: num = "1432219", k = 3
Output: "1219"


Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

10200
  

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2

Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

class Solution {
    public String removeKdigits(String num, int k) {
        
    }
}


1432219
1


10200



424. Longest Repeating Character Replacement

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

 

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.
 

Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length

class Solution {
    public int characterReplacement(String s, int k) {
        int i = 0, j = 0;

        Map<Character,Integer> map = new HashMap<>();
        int maxChar = '';
        int maxCount = 0;
        int maxWindow = 0;
        while(j<s.length()){
            char ch = s.charAt(j);
            map.put(ch,map.getOrDefault(ch,0)+1);

            if(map.get(ch)>maxCount){
                maxCount = map.get(ch);
                maxChar = ch;
            }

            while((j-i+1)-maxCount > k){
                i++;
            }
            maxWindow = Math.max(maxWindow,j-i+1);
        }
        return maxWindow;
    }
}

A->2
B->1

A B A B



Code
Testcase
Test Result
Test Result
808. Soup Servings

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Hint
You have two soups, A and B, each starting with n mL. On every turn, one of the following four serving operations is chosen at random, each with probability 0.25 independent of all previous turns:

pour 100 mL from type A and 0 mL from type B
pour 75 mL from type A and 25 mL from type B
pour 50 mL from type A and 50 mL from type B
pour 25 mL from type A and 75 mL from type B
Note:

There is no operation that pours 0 mL from A and 100 mL from B.
The amounts from A and B are poured simultaneously during the turn.
If an operation asks you to pour more than you have left of a soup, pour all that remains of that soup.
The process stops immediately after any turn in which one of the soups is used up.

Return the probability that A is used up before B, plus half the probability that both soups are used up in the same turn. Answers within 10-5 of the actual answer will be accepted.

 

Example 1:

Input: n = 50
Output: 0.62500
Explanation: 
If we perform either of the first two serving operations, soup A will become empty first.
If we perform the third operation, A and B will become empty at the same time.
If we perform the fourth operation, B will become empty first.
So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
Example 2:

Input: n = 100
Output: 0.71875
Explanation: 
If we perform the first serving operation, soup A will become empty first.
If we perform the second serving operations, A will become empty on performing operation [1, 2, 3], and both A and B become empty on performing operation 4.
If we perform the third operation, A will become empty on performing operation [1, 2], and both A and B become empty on performing operation 3.
If we perform the fourth operation, A will become empty on performing operation 1, and both A and B become empty on performing operation 2.
So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.71875.
 

Constraints:

0 <= n <= 109


class Solution {
    public double soupServings(int n) {
        return solve(n,n);
    }

    public double solve(int a, int b){
        if(a<=0 && b>0){
            return 1.0;
        }

        if(a<=0 && b<=0){
        return 0.5;
        }


        double s1 = solve(a-100,b);
        double s2 = solve(a-75,b-25);
        double s3 = solve(a-50,b-50);
        double s4 = solve(a-25,b-75);

        return 0.24*(s1+s2+s3+s4);
    }
}


1277. Count Square Submatrices with All Ones

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Hint
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 

Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 

Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1


class Solution {
    public int countSquares(int[][] matrix) {

        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < dp.length; i++){
            dp[i][0]=1;
        }
        for(int i = 0; i < dp[0].length; i++){
            dp[0][i]=1;
        }

        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]));
            }    
        }

        return dp[dp.length-1][dp[0].length-1];
    }
}

1504. Count Submatrices With All Ones

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Hint
Given an m x n binary matrix mat, return the number of submatrices that have all ones.

 

Example 1:


Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
Output: 13
Explanation: 
There are 6 rectangles of side 1x1.
There are 2 rectangles of side 1x2.
There are 3 rectangles of side 2x1.
There is 1 rectangle of side 2x2. 
There is 1 rectangle of side 3x1.
Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
Example 2:


Input: mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
Output: 24
Explanation: 
There are 8 rectangles of side 1x1.
There are 5 rectangles of side 1x2.
There are 2 rectangles of side 1x3. 
There are 4 rectangles of side 2x1.
There are 2 rectangles of side 2x2. 
There are 2 rectangles of side 3x1. 
There is 1 rectangle of side 3x2. 
Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.


[1,0,1],
[1,1,0],
[1,1,0]

class Solution {
    public int numSubmat(int[][] mat) {
                
    }
}

1 0 1

2 2 0

3 4 0



Code
Testcase
Test Result
Test Result
256. Paint House

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.

For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
Return the minimum cost to paint all houses.

 

Example 1:

Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
Minimum cost: 2 + 5 + 3 = 10.
Example 2:

Input: costs = [[7,6,2]]
Output: 2
 

Constraints:

costs.length == n
costs[i].length == 3
1 <= n <= 100
1 <= costs[i][j] <= 20


498. Diagonal Traverse

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

 

Example 1:


Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
Example 2:

Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
-105 <= mat[i][j] <= 105

[1,2,3]
[4,5,6]
[7,8,9]

class Solution {
    public int[] findDiagonalOrder(int[][] mat) {

        Map<Integer,List<Integer>> map = new HashMap<>();
        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[m*n];
        int sum = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                sum = i+j;
                if(map.containsKey(sum)){
                    List<Integer> list = map.get(sum);
                    list.add(mat[i][j]);
                }else{
                    List<Integer> list = new ArrayList<>();
                    list.add(mat[i][j]);
                    map.put(sum,list);
                }
            }
        }

        int index = 0;
        int k = 0;
        for(List<Integer> values : map.values()){
            if(index%2==0){
                for(int i = values.size()-1; i>=0; i--){
                    res[k++] = values.get(i);
                }
            }else{
                for(int i = 0; i < values.size(); i++){
                    res[k++] = values.get(i);
                }
            }
            index++;
        }

        return res;
    }
}

307. Range Sum Query - Mutable

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 

Example 1:

Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 

Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
0 <= index < nums.length
-100 <= val <= 100
0 <= left <= right < nums.length
At most 3 * 104 calls will be made to update and sumRange.


class NumArray {
        int n;
        int[] nums;
        int[] seg;

    public void build(int idx, int low, int high, int[] nums, int[] seg){
        if(low==high){
            seg[idx] = arr[low];
            return;
        }

        int mid = low + (high-low)/2;

        build(2*idx+1, low, mid, nums, seg);
        build(2*idx+2, mid+1, high, nums, seg);

        seg[idx] = seg[2*idx+1] + seg[2*idx+2];
    }

    public void updateVal(int idx, int low, int high, int i, int val){
        if(low==high){
            seg[idx] = val;
            return;
        }

        int mid = low + (high-low)/2;
        if(i<=mid){
            updateVal(2*idx+1, low, mid, i, val);
        }else{
            updateVal(2*idx+2, mid+1, high, i, val);
        }

        seg[idx] = seg[2*idx+1] + seg[2*idx+2];
    }

    public int query(int idx, int low, int high, int left, int right){
        if(left<=low && high<=right){
            return seg[idx];
        }else if(high<left || low>right){
            return 0;
        }else{
            int mid = low + (high-low)/2;
            if(right<=mid){
                query(2*idx+1,low,mid,left,right);
            }else{
                query(2*idx+2,mid+1,right,left,right);
            }
        }
    }

    public NumArray(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.seg = new int[n*4];

        build(0, 0, n-1, nums, seg);

    }
    
    

    public void update(int index, int val) {
        updateValue(0, 0, n-1, index, val);
        nums[index] = val;
    }
    


    public int sumRange(int left, int right) {
        return query(0, 0, n-1, left, right);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */



852. Peak Index in a Mountain Array

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
You are given an integer mountain array arr of length n where the values increase to a peak element and then decrease.

Return the index of the peak element.

Your task is to solve it in O(log(n)) time complexity.

 

Example 1:

Input: arr = [0,1,0]

Output: 1

Example 2:

Input: arr = [0,2,1,0]

Output: 1

Example 3:

Input: arr = [0,10,5,2]

Output: 1

 

Constraints:

3 <= arr.length <= 105
0 <= arr[i] <= 106
arr is guaranteed to be a mountain array.


class Solution {
    public int peakIndexInMountainArray(int[] arr) {
    int n = arr.length;
        if(arr[0]>arr[1]){
            return 0;
        }
        if(arr[n-1]>arr[n-2]){
            return n-1;
        }

        int i = 1, j = n-2;

        while(i<=j){
            int mid = (low + high) >> 2;

            if(arr[mid]>arr[mid-1] && arr[mid]>arr[mid+1]){
            return mid;
            }

            if(arr[mid]>arr[mid-1]){
                i = mid+1;
            }else{
                j = mid-1;
            }
        }
        return -1;
    }
}


3659. Partition Array Into K-Distinct Groups

avatar
Discuss Approach
arrow-up
Medium
conpanies icon
Companies
Hint
You are given an integer array nums and an integer k.

Your task is to determine whether it is possible to partition all elements of nums into one or more groups such that:

Each group contains exactly k elements.
All elements in each group are distinct.
Each element in nums must be assigned to exactly one group.
Return true if such a partition is possible, otherwise return false.

 

Example 1:

Input: nums = [1,2,3,4], k = 2

Output: true

Explanation:

One possible partition is to have 2 groups:

Group 1: [1, 2]
Group 2: [3, 4]
Each group contains k = 2 distinct elements, and all elements are used exactly once.

Example 2:

Input: nums = [3,5,2,2], k = 2

Output: true

Explanation:

One possible partition is to have 2 groups:

Group 1: [2, 3]
Group 2: [2, 5]
Each group contains k = 2 distinct elements, and all elements are used exactly once.

Example 3:

Input: nums = [1,5,2,3], k = 3

Output: false

Explanation:

We cannot form groups of k = 3 distinct elements using all values exactly once.

 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= k <= nums.length


class Solution {
    public boolean partitionArray(int[] nums, int k) {
        
        int n = nums.length;
        if(n%k!=0){
        return false;
        }

        int groups = n/k;
        Map<Integer,Integer> map = new HashMap<>();

        for(int i : nums){
            map.put(i, map.getOrDefault(i,0)+1);            
        }

        for(int val : map.values()){
            if(val>groups){
                return false;
            }
        }

        return true;
     }
}


1268. Search Suggestions System

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Hint
You are given an array of strings products and a string searchWord.

Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return a list of lists of the suggested products after each character of searchWord is typed.

 

Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Explanation: The only word "havana" will be always suggested while typing the search word.
 

Constraints:

1 <= products.length <= 1000
1 <= products[i].length <= 3000
1 <= sum(products[i].length) <= 2 * 104
All the strings of products are unique.
products[i] consists of lowercase English letters.
1 <= searchWord.length <= 1000
searchWord consists of lowercase English letters.


                                






