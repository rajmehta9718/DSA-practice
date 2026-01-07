import java.util.HashMap;
import java.util.HashSet;

class SnakeGame {

    static class ListNode{
        int x;
        int y;
        boolean isFood;
        ListNode prev;
        ListNode next;

        public ListNode(int x, int y){
            this.x = x;
            this.y = y;
            isFood = false;
        }
    }

    int height = 0;
    int width = 0;

    ListNode head;
    ListNode tail;

    HashMap<Integer,ListNode> body;
    HashSet<String> foodSet;
    List<Integer> foodList;

    int foodIdx = 0;

    int score = 0;

    public SnakeGame(int w, int h, int[][] food) {
        height = h;
        width = w;
        head = new ListNode(0,0);
        foodList = new ArrayList();
        body = new HashMap<>();
        foodSet = new HashSet<>();
        tail = head;
        body.put(0,head);
        for (int[] ints : food) {
            foodList.add(ints[0]*width+ints[1]);
        }
    }

    public int move(String direction) {
        int dx = 0,dy = 0;
        switch (direction){
            case "R":
                dy = 1;
                break;
            case "D":
                dx = 1;
                break;
            case "U":
                dx = -1;
                break;
            case "L":
                dy = -1;
                break;
            default:
                return -1;
        }

        int currHeadRow = head.x;
        int currHeadCol = head.y;
        int newRowHead = currHeadRow+dx;
        int newColHead = currHeadCol+dy;
        int currId = newRowHead * width + newColHead;

        if (newRowHead < 0 || newRowHead >= height || newColHead < 0 || newColHead >= width) return -1;
        boolean willEat = (foodIdx < foodList.size() && Objects.equals(currId,foodList.get(foodIdx)));

        if (!willEat){
            int tailId = tail.x * width + tail.y;
            body.remove(tailId);
        }

        if (body.containsKey(currId)) return -1;

        ListNode newHead = new ListNode(newRowHead,newColHead);
        newHead.prev = head;
        head.next = newHead;
        head = newHead;
        body.put(currId,newHead);

        if (willEat){
            score++;
            foodIdx++;
        }else{
            ListNode oldTail = tail;
            tail = tail.next;
            if (tail!=null) tail.prev = null;
            oldTail.next = null;
        }
        return score;
    }

    // public static void main(String[] args) {
    //     int[][] arr = {{1,2},{0,1}};
    //     SnakeGame snakeGame = new SnakeGame(3,2,arr);

    //     System.out.println(snakeGame.move("R"));
    //     System.out.println(snakeGame.move("D"));
    //     System.out.println(snakeGame.move("R"));
    //     System.out.println(snakeGame.move("U"));
    //     System.out.println(snakeGame.move("L"));
    //     System.out.println(snakeGame.move("U"));

    // }
}


2034. Stock Price Fluctuation 

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Hint
You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.

Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.

Design an algorithm that:

Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
Finds the maximum price the stock has been based on the current records.
Finds the minimum price the stock has been based on the current records.
Implement the StockPrice class:

StockPrice() Initializes the object with no price records.
void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
int current() Returns the latest price of the stock.
int maximum() Returns the maximum price of the stock.
int minimum() Returns the minimum price of the stock.
 

Example 1:

Input
["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
[[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
Output
[null, null, null, 5, 10, null, 5, null, 2]

Explanation
StockPrice stockPrice = new StockPrice();
stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
                          // Timestamps are [1,2] with corresponding prices [3,5].
stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.
 

Constraints:

1 <= timestamp, price <= 109
At most 105 calls will be made in total to update, current, maximum, and minimum.
current, maximum, and minimum will be called only after update has been called at least once.


class StockPrice {

    public StockPrice() {
        
    }
    
    public void update(int timestamp, int price) {
        
    }
    
    public int current() {
        
    }
    
    public int maximum() {
        
    }
    
    public int minimum() {
        
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */


 Postfix to prefix

 AB-DE+F*/

 A       A
 B       B
 -       -AB,
 D       -AB,D
 E       -AB,D,E
 +       -AB,+DE
 F       -AB,+DE,F
 *       -AB,*+DEF
 /       /-AB*+DEF

 Prefix to  postfix

 /-AB*+DEF 
 
 F       F
 E       FE
 D       FED
 +       F,DE+
 *       DE+F*
 B       DE+F*,B
 A       DE+F*,B,A
 -       DE+F*,AB-
 /       AB-DE+F*/

 Postfix to infix

 AB-DE+F*/
 
 A      A
 B      B
 -      (A-B)
 D      (A-B),D
 E      (A-B),D,E
 +      (A-B),(D+E)
 F      (A-B),(D+E),F
 *      (A-B),((D+E)*F),
 /      (A-B)/((D+E)*F)

 Infix to postFix

 a+b*(c^d-e)

 a          a
 b          ab
 +    +     ab
 *    +*    ab
 (    +*(
 c    +*(   abc
 ^    +*(^  abc
 d   +*(^   abcd
 -   +*(-   abcd^
 e          abcd^e
 )          abcd^e-*+

 //A+(B*((C^D)-E))
 
 Prefix to Infix

 *+PQ-MN
 
 N         N
 M         M
 -         (M-N)
 Q         (M-N),Q
 P         (M-N),Q,P
 +         (M-N),(P+Q)
 *         (P+Q)*(M-N)  

Infix to prefix

 (A+B)*C - D + F
 
 F+D-C*(B+A)

F           F
+     +     F
D     +     FD
-     +-    FD
C     +-    FDC 
*     +-*   FDC
(     +-*(  FDC
B     +-*(  FDCB
+     +-*(+ FDCB
A     +-*(+ FDCBA
)     +-*   FDCBA+*-+

+-*+ABCDF

((A+B)*C)-D+F


class LazySeg
{
    int[] seg;
    boolean[] lazy;
    public LazySeg(n){
        seg = new int[4*n+1];
        lazy = new int[4*n+1];
    }

    public void build(int idx, int[] seg, int low, int mid, int[] arr){
        if(low == high){
            if(arr[low]==1){
                seg[idx] = 1;
            }
            return;
        }

        int mid = (low+high)/2;
        build(2*idx+1, seg, low, mid, arr);
        build(2*idx+2, seg, mid+1, high, arr);

        seg[idx] = seg[2*idx+1] + seg[2*idx+2];
    }

    public int query(int idx, int l, int r, int low, int high, int[] seg, int[] arr, boolean[] lazy){

        if(lazy[idx]){
            seg[idx] = (high-low+1) - seg[idx];

            lazy[2*idx+1]=true;
            lazy[2*idx+2]=true;
            lazy[idx] = false;
        }

        if(r<low || l>high){
            return 0;
        }

        else if(l<=low && high<=r){
            return seg[idx];
        } else{
            int mid = (low+high)/2;
            int left = query(2*idx+1,l,r,low,mid,seg,arr,lazy);
            int right = query(2*idx+2,l,r,mid+1,high,seg,arr,lazy);
            return left+right;
        }

    }

    public void update(int idx, int l, int r, int low, int high, int[] seg, int[] arr, boolean[] lazy){

        if(lazy[idx]){
            seg[idx] = (high-low+1) - seg[idx];

            lazy[2*idx+1]=true;
            lazy[2*idx+2]=true;
            lazy[idx]=false;
        }

        if(r<low || l>high){
            return;
        }

        else if(l<=low && high<=r){
            seg[idx] = (high-low+1) - seg[idx];

            if(low!=high){
            lazy[2*idx+1]=true;
            lazy[2*idx+2]=true;
            }

            return;
            
        } else{

            int mid = (low+high)/2;
            update(2*idx+1,l,r,low,mid,seg,arr,lazy);
            update(2*idx+2,l,r,mid+1,high,seg,arr,lazy);
            seg[idx] = seg[2*idx+1]+seg[2*idx+2];

        }

    }
}

304. Range Sum Query 2D - Immutable

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
You must design an algorithm where sumRegion works on O(1) time complexity.

 

Example 1:

Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]

class NumMatrix {
    
    int[][] prefixSum;
    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        prefixSum = new int[m][n];
        for(int i = 0; i < prefixSum.length; i++){
            int sum = 0;
            for(int j = 0; j < prefix[i].length; j++){
                sum+=matrix[i][j];
                prefixSum[i][j]=sum;
            }
        }        
    }
    

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for(int i = row1; i <= row2; i++){
            for(int j = col1; j <= col2; j++){
                if(col1 == 0){
                    res+=prefixSum[i][j]
                }
                else{
                    res+=prefixSum[i][j]-prefixSum[i][j-1];
                }
            }
        }
        return res;       
    }
}


308. Range Sum Query 2D - Mutable
Solved

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Given a 2D matrix matrix, handle multiple queries of the following types:

Update the value of a cell in matrix.
Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
void update(int row, int col, int val) Updates the value of matrix[row][col] to be val.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 

Example 1:


Input
["NumMatrix", "sumRegion", "update", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
Output
[null, 8, null, 10]

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e. sum of the left red rectangle)
numMatrix.update(3, 2, 2);       // matrix changes from left image to right image
numMatrix.sumRegion(2, 1, 4, 3); // return 10 (i.e. sum of the right red rectangle)
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-1000 <= matrix[i][j] <= 1000
0 <= row < m
0 <= col < n
-1000 <= val <= 1000
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
At most 5000 calls will be made to sumRegion and update.


class NumMatrix {
    

    int[][] seg;
    int m,n;
    int[][] matrix;
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        m = matrix.length, n = matrix[0].length;
        seg = new int[m][n];
    }
    
    private void updateSeg(int row, int i, int idx, int low, int high, int val){
        if(low==high){
            seg[row][idx]=val;
        }

        int mid = (low+high)/2;
        if(idx <= mid){
            updateSeg(row,i,2*idx+1,low,mid,val);
        }else{
            updateSeg(row,i,2*idx+2,mid+1,high,val);
        }

        seg[row][idx]=seg[row][2*idx+1]+seg[row][2*idx+2];
    }

    public void update(int row, int col, int val) {
        updateSeg(row,col,0,0,n-1,val);
    }
    
    private int query(int row, int idx,  int low, int high, int l, int r){
        if(r<low || l>high){
            return 0;
        }

        else if(l<=low && high<=r){
            return seg[idx];
        } else{
            int mid = (low+high)/2;
            int left = query(2*idx+1,l,r,low,mid,seg,arr,lazy);
            int right = query(2*idx+2,l,r,mid+1,high,seg,arr,lazy);
            return left+right;
        }

    }


    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for(int i = row1; i <= row2; i++){
            sum+=query(i, 0, 0, n-1, col1,col2);
        }
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */


 3713. Longest Balanced Substring I

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Hint
You are given a string s consisting of lowercase English letters.

A substring of s is called balanced if all distinct characters in the substring appear the same number of times.

Return the length of the longest balanced substring of s.

 

Example 1:

Input: s = "abbac"

Output: 4

Explanation:

The longest balanced substring is "abba" because both distinct characters 'a' and 'b' each appear exactly 2 times.

Example 2:

Input: s = "zzabccy"

Output: 4

Explanation:

The longest balanced substring is "zabc" because the distinct characters 'z', 'a', 'b', and 'c' each appear exactly 1 time.​​​​​​​

Example 3:

Input: s = "aba"

Output: 2

Explanation:

One of the longest balanced substrings is "ab" because both distinct characters 'a' and 'b' each appear exactly 1 time. Another longest balanced substring is "ba".

 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.


abbac:

class Solution {
    public int longestBalanced(String s) {
        int i = 0, j = 0;
        Map<Character,Integer> map = new HashMap<>();
        int max = 0;
        while(j<s.length()){
            map.put(s.charAt(j),map.getOrDefault(s.charAt(j),0)+1);
            int myval = map.get(s.charAt(j));
            boolean same = true;
            for(int val : map.getValues()){
                if(val!=myval){
                    val==myval;
                    same = false;
                    break;
                }
            }

            if(same){
                max = Math.max(j-i+1,max);
            }


            j++;

            if(j==s.length()){

            }
        }
    }
}


3349. Adjacent Increasing Subarrays Detection I

avatar
Discuss Approach
arrow-up
Easy
Topics
conpanies icon
Companies
Hint
Given an array nums of n integers and an integer k, determine whether there exist two adjacent subarrays of length k such that both subarrays are strictly increasing. Specifically, check if there are two subarrays starting at indices a and b (a < b), where:

Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
The subarrays must be adjacent, meaning b = a + k.
Return true if it is possible to find two such subarrays, and false otherwise.

 

Example 1:

Input: nums = [2,5,7,8,9,2,3,4,3,1], k = 3

Output: true

Explanation:

The subarray starting at index 2 is [7, 8, 9], which is strictly increasing.
The subarray starting at index 5 is [2, 3, 4], which is also strictly increasing.
These two subarrays are adjacent, so the result is true.

Example 2:

Input: nums = [1,2,3,4,4,4,4,5,6,7], k = 5

Output: false

Constraints:

2 <= nums.length <= 100
1 < 2 * k <= nums.length
-1000 <= nums[i] <= 1000



[19,4,19,6,18]


class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        if(k == 1)
            return true;
        
        int n = nums.size();
        int count = 1;
        boolean prevInc = false;

        for(int i=0;i<n-1;i++){
            if(nums.get(i+1) > nums.get(i))
                count++;
            else{
                if(count >= k*2)
                    return true;
                
                if(count>=k){
                    if(prevInc)
                        return true;
                    prevInc = true;
                }
                else
                    prevInc = false;
                count = 1;
            }
        }

        if(count >= k && prevInc)
            return true;
        
        if(count >= k*2)
            return true;

        return false;
    }
}


2048. Next Greater Numerically Balanced Number

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Hint
An integer x is numerically balanced if for every digit d in the number x, there are exactly d occurrences of that digit in x.

Given an integer n, return the smallest numerically balanced number strictly greater than n.

 

Example 1:

Input: n = 1
Output: 22
Explanation: 
22 is numerically balanced since:
- The digit 2 occurs 2 times. 
It is also the smallest numerically balanced number strictly greater than 1.
Example 2:

Input: n = 1000
Output: 1333
Explanation: 
1333 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times. 
It is also the smallest numerically balanced number strictly greater than 1000.
Note that 1022 cannot be the answer because 0 appeared more than 0 times.
Example 3:

Input: n = 3000
Output: 3133
Explanation: 
3133 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times.
It is also the smallest numerically balanced number strictly greater than 3000.


class Solution {
    public int nextBeautifulNumber(int n) {

        int num = n+1;
        while(true){

            Map<Integer,Integer> map = new HashMap<>();
            int x = num;
            while(x>0){
                int key = x%10;
                map.put(key,map.getOrDefault(key,0)+1);
                x/=10;
            }
            boolean beautiful = false;
            for(Map.Entry<Integer,Integer> entry : map.entrySet()){
                if(entry.getValue()!=entry.getKey()){
                    beautiful = false;
                    break;
                }
            }
            if(beautiful){
                return num;
            }
            num++;
        }
        return false;
    }
}


41. First Missing Positive

avatar
Discuss Approach
arrow-up
Hard
Topics
conpanies icon
Companies
Hint
Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

 

Example 1:

Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.
 

Constraints:

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1


public class Solution {
    public int firstMissingPositive(int[] A) {
        int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }
    
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}



2043. Simple Bank System
Medium
Topics
premium lock icon
Companies
Hint
You have been tasked with writing a program for a popular bank that will automate all its incoming transactions (transfer, deposit, and withdraw). The bank has n accounts numbered from 1 to n. The initial balance of each account is stored in a 0-indexed integer array balance, with the (i + 1)th account having an initial balance of balance[i].

Execute all the valid transactions. A transaction is valid if:

The given account number(s) are between 1 and n, and
The amount of money withdrawn or transferred from is less than or equal to the balance of the account.
Implement the Bank class:

Bank(long[] balance) Initializes the object with the 0-indexed integer array balance.
boolean transfer(int account1, int account2, long money) Transfers money dollars from the account numbered account1 to the account numbered account2. Return true if the transaction was successful, false otherwise.
boolean deposit(int account, long money) Deposit money dollars into the account numbered account. Return true if the transaction was successful, false otherwise.
boolean withdraw(int account, long money) Withdraw money dollars from the account numbered account. Return true if the transaction was successful, false otherwise.
 

Example 1:

Input
["Bank", "withdraw", "transfer", "deposit", "transfer", "withdraw"]
[[[10, 100, 20, 50, 30]], [3, 10], [5, 1, 20], [5, 20], [3, 4, 15], [10, 50]]
Output
[null, true, true, true, false, false]

Explanation
Bank bank = new Bank([10, 100, 20, 50, 30]);
bank.withdraw(3, 10);    // return true, account 3 has a balance of $20, so it is valid to withdraw $10.
                         // Account 3 has $20 - $10 = $10.
bank.transfer(5, 1, 20); // return true, account 5 has a balance of $30, so it is valid to transfer $20.
                         // Account 5 has $30 - $20 = $10, and account 1 has $10 + $20 = $30.
bank.deposit(5, 20);     // return true, it is valid to deposit $20 to account 5.
                         // Account 5 has $10 + $20 = $30.
bank.transfer(3, 4, 15); // return false, the current balance of account 3 is $10,
                         // so it is invalid to transfer $15 from it.
bank.withdraw(10, 50);   // return false, it is invalid because account 10 does not exist.
 

Constraints:

n == balance.length
1 <= n, account, account1, account2 <= 105
0 <= balance[i], money <= 1012
At most 104 calls will be made to each function transfer, deposit, withdraw.

class Bank {
    
    long[] balance;
    public Bank(long[] balance) {
        this.balance = balance;
    }
    
    public boolean transfer(int account1, int account2, long money) {
        if(balance[account1] >= money){
            balance[account1]-=money;
            balance[account2]+=money;
            return true;
        }
        return false;
    }
    
    public boolean deposit(int account, long money) {
        balance[account]+=money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        balance[account]-=money;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */



 752. Open the Lock
Medium
Topics
conpanies icon
Companies
Hint
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

 

Example 1:

Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation: 
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:

Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:

Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation: We cannot reach the target without getting stuck.
 

Constraints:

1 <= deadends.length <= 500
deadends[i].length == 4
target.length == 4
target will not be in the list deadends.
target and deadends[i] consist of digits only.

class Solution {
    public int openLock(String[] deadends, String target) {

        Set<Integer> dead = new HashSet();
        for(String s : deadends){
            dead.add(s);
        }

        if(dead.contains("0000")){
            return -1;
        }
        Queue<String> visited = new LinkedList<>();
        Queue>String> bfs = new LinkedList<>();

        bfs.add("0000");
        visited.add("0000");

        int steps = 0;
        while(!queue.isEmpty()){
            String pattern = bfs.poll();

            if(pattern.equals(target)){
            return steps;
            }

            for(String neighbor : neighbors(pattern)){
                if(!dead.contains(neighbor) && !visited.contains(neighbor)){
                    bfs.add(neighbor);
                    visited.add(neighbor);
                }
            }

            steps++;
        }

        return -1;
    }

    private List<String> neighbors(String s){

        List<String> res = new ArrayList<>();

        char[] arr = s.toCharArray(); 
        for(int i = 0; i < 4; i++){
            char ch : arr[i];

            arr[i] = ch == '9' ? '0' : char(ch + 1);

            res.add(new String(arr));

            arr[i] = ch == '0' ? '9' : char(ch - 1);

            res.add(new String(arr)); 
        }
    }
}



3318. Find X-Sum of All K-Long Subarrays I
Easy
Topics
conpanies icon
Companies
Hint
You are given an array nums of n integers and two integers k and x.

The x-sum of an array is calculated by the following procedure:

Count the occurrences of all elements in the array.
Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences, the element with the bigger value is considered more frequent.
Calculate the sum of the resulting array.
Note that if an array has less than x distinct elements, its x-sum is the sum of the array.

Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].

 

Example 1:

Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2

Output: [6,10,12]

Explanation:

For subarray [1, 1, 2, 2, 3, 4], only elements 1 and 2 will be kept in the resulting array. Hence, answer[0] = 1 + 1 + 2 + 2.
For subarray [1, 2, 2, 3, 4, 2], only elements 2 and 4 will be kept in the resulting array. Hence, answer[1] = 2 + 2 + 2 + 4. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.
For subarray [2, 2, 3, 4, 2, 3], only elements 2 and 3 are kept in the resulting array. Hence, answer[2] = 2 + 2 + 2 + 3 + 3.
Example 2:

Input: nums = [3,8,7,8,7,5], k = 2, x = 2

Output: [11,15,15,15,12]

Explanation:

Since k == x, answer[i] is equal to the sum of the subarray nums[i..i + k - 1].

 

Constraints:

1 <= n == nums.length <= 50
1 <= nums[i] <= 50
1 <= x <= k <= nums.length


class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        
    }
}


44. Wildcard Matching
Hard
Topics
conpanies icon
Companies
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 

Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.

class Solution {
    public boolean isMatch(String s, String p) {
        Boolean[][] dp = new Boolean[s.length()][p.length()];
        return solve(0,0,s,p,dp);
    }

    public boolean solve(int idx1, int idx2,String s, String p, Boolean[][] dp){
        if(idx1 == p.length() && idx2 == s.length()){
            return true;
        }

        if(idx1 == p.length() && idx2!=s.length()){
            return false;
        }

        if(idx2 == s.length() && idx1!=p.length()){
            while(idx1<p.length()){
                if(p.charAt(idx).isLetter()){
                    return false;
                }
            }
            return true;
        }

        if(dp[idx1][idx2]!=null){
            return dp[idx1][idx2];
        }

        boolean questionMark = false;
        boolean starRemain = false;
        boolean starMove = false;
        boolean charMatch = false;
        if(p.charAt(idx1) == '?'){
            questionMark = solve(idx1+1,idx2+1,s,p);
        } else if(p.charAt(idx1) == '*'){
            starMove = solve(idx1+1,idx2+1,s,p);
            starRemain = solve(idx1,idx2+1,s,p);
        } else{
            if(p.charAt(idx1) == s.charAt(idx2)){
                charMatch = solve(idx1+1,idx2+1,s,p);
            }
        }

        return dp[idx1][idx2] = questionMark || starRemain || starMove || charMatch;
    }
}


1789. Primary Department for Each Employee
Easy
Topics
conpanies icon
Companies
SQL Schema
Pandas Schema
Table: Employee

+---------------+---------+
| Column Name   |  Type   |
+---------------+---------+
| employee_id   | int     |
| department_id | int     |
| primary_flag  | varchar |
+---------------+---------+
(employee_id, department_id) is the primary key (combination of columns with unique values) for this table.
employee_id is the id of the employee.
department_id is the id of the department to which the employee belongs.
primary_flag is an ENUM (category) of type ('Y', 'N'). If the flag is 'Y', the department is the primary department for the employee. If the flag is 'N', the department is not the primary.
 

Employees can belong to multiple departments. When the employee joins other departments, they need to decide which department is their primary department. Note that when an employee belongs to only one department, their primary column is 'N'.

Write a solution to report all the employees with their primary department. For employees who belong to one department, report their only department.

Return the result table in any order.

The result format is in the following example.

 

Example 1:

Input: 
Employee table:
+-------------+---------------+--------------+
| employee_id | department_id | primary_flag |
+-------------+---------------+--------------+
| 1           | 1             | N            |
| 2           | 1             | Y            |
| 2           | 2             | N            |
| 3           | 3             | N            |
| 4           | 2             | N            |
| 4           | 3             | Y            |
| 4           | 4             | N            |
+-------------+---------------+--------------+
Output: 
+-------------+---------------+
| employee_id | department_id |
+-------------+---------------+
| 1           | 1             |
| 2           | 1             |
| 3           | 3             |
| 4           | 3             |
+-------------+---------------+
Explanation: 
- The Primary department for employee 1 is 1.
- The Primary department for employee 2 is 1.
- The Primary department for employee 3 is 3.
- The Primary department for employee 4 is 3.


/* Write your PL/SQL query statement below */
select employee_id,department_id from employee e where
primary_flag = 'Y'
UNION
select e.employee_id,e.department_id from employee e where e.employee_id IN (select count1.employee_id from 
(select e1.employee_id,count(*) as cnt from employee e1 group by e1.employee_id) count1 
where count1.cnt = 1;

220. Contains Duplicate III
Hard
Topics
conpanies icon
Companies
Hint
You are given an integer array nums and two integers indexDiff and valueDiff.

Find a pair of indices (i, j) such that:

i != j,
abs(i - j) <= indexDiff.
abs(nums[i] - nums[j]) <= valueDiff, and
Return true if such pair exists or false otherwise.

 

Example 1:

Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
Output: true
Explanation: We can choose (i, j) = (0, 3).
We satisfy the three conditions:
i != j --> 0 != 3
abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
Example 2:

Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
Output: false
Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.
 

Constraints:

2 <= nums.length <= 105
-109 <= nums[i] <= 109
1 <= indexDiff <= nums.length
0 <= valueDiff <= 109

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                if(Math.abs(j-i)<= indexDiff){
                    if(Math.abs(nums[i]-nums[j]) <= valueDiff){
                        return true;
                    }
                }else{
                    break;
                }
            }
        }
        return false;    
    }
}


Subsets
Solved 
Given an array nums of unique integers, return all possible subsets of nums.

The solution set must not contain duplicate subsets. You may return the solution in any order.

Example 1:

Input: nums = [1,2,3]

Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [7]

Output: [[],[7]]
Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10


class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        solve(0,nums,res,new ArrayList<>());
        return res;
    }

    private void solve(int idx, int[] nums, List<List<Integer>> res, List<Integer> subres){
        if(idx == nums.length){
            res.add(new ArrayList<>(subres));
            return;
        }

        subres.add(nums[idx]);
        solve(idx+1,nums,res,subres);
        subres.remove(subres.size()-1);
        solve(idx+1,nums,res,subres);
    }
}


Combination Sum
Solved 
You are given an array of distinct integers nums and a target integer target. Your task is to return a list of all unique combinations of nums where the chosen numbers sum to target.

The same number may be chosen from nums an unlimited number of times. Two combinations are the same if the frequency of each of the chosen numbers is the same, otherwise they are different.

You may return the combinations in any order and the order of the numbers in each combination can be in any order.

Example 1:

Input: 
nums = [2,5,6,9] 
target = 9

Output: [[2,2,5],[9]]
Explanation:
2 + 2 + 5 = 9. We use 2 twice, and 5 once.
9 = 9. We use 9 once.

Example 2:

Input: 
nums = [3,4,5]
target = 16

Output: [[3,3,3,3,4],[3,3,5,5],[4,4,4,4],[3,4,4,5]]
Example 3:

Input: 
nums = [3]
target = 5

Output: []
Constraints:

All elements of nums are distinct.
1 <= nums.length <= 20
2 <= nums[i] <= 30
2 <= target <= 30

class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        solve(0, target, nums, res, new ArrayList<>());
        return res;
    }

    public void solve(int idx, int target, int[] nums, List<List<Integer>> res, List<Integer> subres){
        if(target == 0){
            res.add(new ArrayList<>(subres));
            return;
        }

        for(int i = idx, i < nums.length && target - nums[idx] >= 0; i++){
            subres.add(nums[idx]);
            solve(idx,target-nums[idx],nums,res,subres);
            subres.remove(subres.size()-1);
        }
    }
}


Combination Sum II
Solved 
You are given an array of integers candidates, which may contain duplicates, and a target integer target. Your task is to return a list of all unique combinations of candidates where the chosen numbers sum to target.

Each element from candidates may be chosen at most once within a combination. The solution set must not contain duplicate combinations.

You may return the combinations in any order and the order of the numbers in each combination can be in any order.

Example 1:

Input: candidates = [9,2,2,4,6,1,5], target = 8

Output: [
  [1,2,5],
  [2,2,4],
  [2,6]
]
Example 2:

Input: candidates = [1,2,3,4,5], target = 7

Output: [
  [1,2,4],
  [2,5],
  [3,4]
]
Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        solve(0,candidates,target,res,new ArrayList<>());
        return res;
    }

    public void solve(in idx, int target, List<List<Integer>> res, List<Integer> subres){
        if(target==0){
            res.add(new ArrayList<>(subres));
            return;
        }

        for(int i = idx; i < candidates.length && candidates[i]<=target; i++){
            if(i>idx && candidates[i]==candidates[i-1]){
                continue;
            }
            subres.add(candidates[i]);
            solve(i+1,candidates,target-candidates[i],res,subres);
            subres.remove(subres.size()-1);
        }
    }
}


Permutations
Solved 
Given an array nums of unique integers, return all the possible permutations. You may return the answer in any order.

Example 1:

Input: nums = [1,2,3]

Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [7]

Output: [[7]]
Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        solve(nums,res,newArrayList<>());
        return res;
    }

    private void solve(int[] nums, List<List<Integer>> res, List<Integer> list){
        if(list.size()==nums.length){
            res.add(new ArrayList<>(list));
        }

        for(int i = 0; i < nums.length; i++){
            if(list.contains(nums[i])){
                continue;
            }
            list.add(nums[i]);
            solve(nums,res,list);
            list.remove(list.size()-1);
        }
    }
}


Subsets II
Solved 
You are given an array nums of integers, which may contain duplicates. Return all possible subsets.

The solution must not contain duplicate subsets. You may return the solution in any order.

Example 1:

Input: nums = [1,2,1]

Output: [[],[1],[1,2],[1,1],[1,2,1],[2]]
Example 2:

Input: nums = [7,7]

Output: [[],[7], [7,7]]
Constraints:

1 <= nums.length <= 11
-20 <= nums[i] <= 20

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
          List<List<Integer>> res = new ArrayList<>();
          Arrays.sort(nums);
          solve(0,nums,res,new ArrayList<>());
          return res;      
    }

    private void solve(int idx, int[] nums, List<List<Integer>> res, List<Integer> list){
        res.add(new ArrayList<>(list));
        for(int i = idx; i < nums.length; i++){
            if(i>idx && nums[i]==nums[i-1]){
            continue;
            }
            list.add(nums[i]);
            solve(i+1,nums,res,list);
            list.remove(list.size()-1);
        }
    }
}

Generate Parentheses
Solved 
You are given an integer n. Return all well-formed parentheses strings that you can generate with n pairs of parentheses.

Example 1:

Input: n = 1

Output: ["()"]
Example 2:

Input: n = 3

Output: ["((()))","(()())","(())()","()(())","()()()"]
You may return the answer in any order.

Constraints:

1 <= n <= 7


class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        solve(n, 0, 0, res, new StringBuilder());
        return res;
    }

    private void solve(int n, int open, int close, List<String> res, StringBuilder sb){
        if(open > n || close > open){
            return;
        }

        if(sb.length() == 2*n){
            res.add(new String(sb));
        }

        sb.append('(');
        solve(n, open+1, close, res, sb);
        sb.deleteCharAt(sb.length()-1);
        sb.append(')');
        solve(n, open, close+1, res, sb);
        sb.deleteCharAt(sb.length()-1);

    }
}


Number of Islands
Solved 
Given a 2D grid grid where '1' represents land and '0' represents water, count and return the number of islands.

An island is formed by connecting adjacent lands horizontally or vertically and is surrounded by water. You may assume water is surrounding the grid (i.e., all the edges are water).

Example 1:

Input: grid = [
    ["0","1","1","1","0"],
    ["0","1","0","1","0"],
    ["1","1","0","0","0"],
    ["0","0","0","0","0"]
  ]
Output: 1
Example 2:

Input: grid = [
    ["1","1","0","0","1"],
    ["1","1","0","0","1"],
    ["0","0","1","0","0"],
    ["0","0","0","1","1"]
  ]
Output: 4
Constraints:

1 <= grid.length, grid[i].length <= 100
grid[i][j] is '0' or '1'.


class Solution {
    public int numIslands(char[][] grid) {
        int numIslands = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]=='1' && !visited[i][j]){
                    numIslands++;
                    solve(i,j,grid,visited);
                }
            }
        }
        return numIslands;        
    }

    private void solve(int i, int j, char[][] grid, boolean[][] visited){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || visited[i][j] || grid[i][j]=='0'){
            return;
        }

        visited[i][j]=true;
        solve(i+1,j,grid);
        solve(i-1,j,grid);
        solve(i,j-1,grid);
        solve(i,j+1,grid);
    }
}


Max Area of Island
Solved 
You are given a matrix grid where grid[i] is either a 0 (representing water) or 1 (representing land).

An island is defined as a group of 1's connected horizontally or vertically. You may assume all four edges of the grid are surrounded by water.

The area of an island is defined as the number of cells within the island.

Return the maximum area of an island in grid. If no island exists, return 0.

Example 1:



Input: grid = [
  [0,1,1,0,1],
  [1,0,1,0,1],
  [0,1,1,0,1],
  [0,1,0,0,1]
]

Output: 6
Explanation: 1's cannot be connected diagonally, so the maximum area of the island is 6.

Constraints:

1 <= grid.length, grid[i].length <= 50

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]==1 && !visited[i][j]){
                    int count = solve(i,j,grid,visited);
                    maxArea = Math.max(count,maxArea);
                }
            }
        }
        return maxArea;       
    }

    private int solve(int i, int j, int[][] grid, boolean[][] visited){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || visited[i][j] || grid[i][j]==0){
            return 0;
        }
        visited[i][j]=true;
        int res = 1;
        res+=solve(i+1,j,grid,visited);
        res+=solve(i-1,j,grid,visited);
        res+=solve(i,j-1,grid,visited);
        res+=solve(i,j+1,grid,visited);
        return res;
    }
}


Clone Graph
Given a node in a connected undirected graph, return a deep copy of the graph.

Each node in the graph contains an integer value and a list of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
The graph is shown in the test cases as an adjacency list. An adjacency list is a mapping of nodes to lists, used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

For simplicity, nodes values are numbered from 1 to n, where n is the total number of nodes in the graph. The index of each node within the adjacency list is the same as the node's value (1-indexed).

The input node will always be the first node in the graph and have 1 as the value.

Example 1:



Input: adjList = [[2],[1,3],[2]]

Output: [[2],[1,3],[2]]
Explanation: There are 3 nodes in the graph.
Node 1: val = 1 and neighbors = [2].
Node 2: val = 2 and neighbors = [1, 3].
Node 3: val = 3 and neighbors = [2].

Example 2:



Input: adjList = [[]]

Output: [[]]
Explanation: The graph has one node with no neighbors.

Example 3:

Input: adjList = []

Output: []
Explanation: The graph is empty.

Constraints:

0 <= The number of nodes in the graph <= 100.
1 <= Node.val <= 100
There are no duplicate edges and no self-loops in the graph.


class Solution {
    public Node cloneGraph(Node node) {
        
        if (node == null) return null;
        Queue<Node> queue = new LinkedList<>();

        queue.add(node);
        Map<Node,Node> map = new Hashmap<>();

        Node newNode = new Node(node.val);

        map.put(node,newNode);
        while(!queue.isEmpty()){
            Node popped = queue.poll();
            Node cloneNode = map.get(popped);
            List<Node> cloneNeighbors = new ArrayList<>();
            for(Node neighbor : popped.neighbors){
                Node cloneNeighbor = new Node(neighbor.val);
                cloneNeighbors.add(cloneNeighbor);
                map.put(neighbor,cloneNeighbor);
                queue.add(neighbor);
            }

            cloneNode.neighbors = cloneNeighbors;
        }

        return map.get(node);
    }
}


Islands and Treasure
Solved 
You are given a 
m
×
n
m×n 2D grid initialized with these three possible values:

-1 - A water cell that can not be traversed.
0 - A treasure chest.
INF - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.
Fill each land cell with the distance to its nearest treasure chest. If a land cell cannot reach a treasure chest then the value should remain INF.

Assume the grid can only be traversed up, down, left, or right.

Modify the grid in-place.

Example 1:

Input: [
  [2147483647,-1,0,2147483647],
  [2147483647,2147483647,2147483647,-1],
  [2147483647,-1,2147483647,-1],
  [0,-1,2147483647,2147483647]
]

Output: [
  [3,-1,0,1],
  [2,2,1,-1],
  [1,-1,2,-1],
  [0,-1,3,4]
]
Example 2:

Input: [
  [0,-1],
  [2147483647,2147483647]
]

Output: [
  [0,-1],
  [1,2]
]
Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
grid[i][j] is one of {-1, 0, 2147483647}


public class Solution {
    public void islandsAndTreasure(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    q.add(new int[] { i, j });
                }
            }
        }
        if (q.size() == 0) return;

        int[][] dirs = { { -1, 0 }, { 0, -1 },
                         { 1, 0 }, { 0, 1 } };
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int row = node[0];
            int col = node[1];
            for (int[] dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];
                if (r >= m || c >= n || r < 0 ||
                    c < 0 || grid[r][c] != Integer.MAX_VALUE) {
                    continue;
                }
                q.add(new int[] { r, c });

                grid[r][c] = grid[row][col] + 1;
            }
        }
    }
}


Rotting Fruit
You are given a 2-D matrix grid. Each cell can have one of three possible values:

0 representing an empty cell
1 representing a fresh fruit
2 representing a rotten fruit
Every minute, if a fresh fruit is horizontally or vertically adjacent to a rotten fruit, then the fresh fruit also becomes rotten.

Return the minimum number of minutes that must elapse until there are zero fresh fruits remaining. If this state is impossible within the grid, return -1.

Example 1:



Input: grid = [[1,1,0],[0,1,1],[0,1,2]]

Output: 4
Example 2:

Input: grid = [[1,0,1],[0,2,0],[1,0,1]]

Output: -1
Constraints:

1 <= grid.length, grid[i].length <= 10

class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]==2){
                    queue.add(new int[]{i,j,0});
                }
            }
        }

        int maxDist = 0;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while(!queue.isEmpty()){
            int[] popped = queue.poll();

            maxDist = Math.max(popped[2],maxDist);
            for(int[] dir : dirs){
                int newrow = popped[0] + dir[0];
                int newcol = popped[1] + dir[1];

                if(newrow < 0 || newcol < 0 || newrow >= grid.length || newcol >= grid[0].length || grid[newrow][newcol]=2 || grid[newrow][newcol]==0){
                    continue;
                }

                queue.add(new int[]{newrow,newcol,popped[2]+1});
                grid[newrow][newcol]=2;
            }

        }

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]==1){
                    return -1;
                }
            }
        }

        return maxDist;
    }
}



Pacific Atlantic Water Flow
Solved 
You are given a rectangular island heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The islands borders the Pacific Ocean from the top and left sides, and borders the Atlantic Ocean from the bottom and right sides.

Water can flow in four directions (up, down, left, or right) from a cell to a neighboring cell with height equal or lower. Water can also flow into the ocean from cells adjacent to the ocean.

Find all cells where water can flow from that cell to both the Pacific and Atlantic oceans. Return it as a 2D list where each element is a list [r, c] representing the row and column of the cell. You may return the answer in any order.

Example 1:



Input: heights = [
  [4,2,7,3,4],
  [7,4,6,4,7],
  [6,3,5,3,6]
]

Output: [[0,2],[0,4],[1,0],[1,1],[1,2],[1,3],[1,4],[2,0]]
Example 2:

Input: heights = [[1],[1]]

Output: [[0,0],[0,1]]
Constraints:

1 <= heights.length, heights[r].length <= 100
0 <= heights[r][c] <= 1000


class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();

        int[][] pacific = new int[heights.length][heights[0].length];
        int[][] atlantic = new int[heights.length][heights[0].length];

        for(int i = 0; i < heights.length; i++){
            dfs(i,0,Integer.MIN_VALUE,heights,pacific);
        }

        for(int j = 0; j < heights[0].length; j++){
            dfs(0,j,Integer.MIN_VALUE,heights,pacific);
        }

        for(int i = 0; i < heights.length; i++){
            dfs(i,heights[0].length-1,Integer.MIN_VALUE,heights,atlantic);
        }

        for(int j = 0; j < heights[0].length; j++){
            dfs(heights.length-1,j,Integer.MIN_VALUE,heights,atlantic);
        }

        for(int i = 0; i < heights.length; i++){
            for(int j = 0; j < heights[0].length; j++){
                if(pacific[i][j] == -1 && atlantic[i][j]==-1){
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }

        return res;


    }

    public void dfs(int i, int j, int prev, int[][] heights, int[][] waterbody){
        if(i<0 || j<0 || i>=heights.length || j>=heights[0].length || waterbody[i][j] = -1 || heights[i][j]<prev){
            return;
        }

        prev = heights[i][j];
        waterbody[i][j] == -1;
        dfs(i+1,j,prev,heights,waterbody);
        dfs(i-1,j,prev,heights,waterbody);
        dfs(i+1,j,prev,heights,waterbody);
        dfs(i+1,j,prev,heights,waterbody);

    }
}


Surrounded Regions
Solved 
You are given a 2-D matrix board containing 'X' and 'O' characters.

If a continous, four-directionally connected group of 'O's is surrounded by 'X's, it is considered to be surrounded.

Change all surrounded regions of 'O's to 'X's and do so in-place by modifying the input board.

Example 1:



Input: board = [
  ["X","X","X","X"],
  ["X","O","O","X"],
  ["X","O","O","X"],
  ["X","X","X","O"]
]

Output: [
  ["X","X","X","X"],
  ["X","X","X","X"],
  ["X","X","X","X"],
  ["X","X","X","O"]
]
Explanation: Note that regions that are on the border are not considered surrounded regions.

Constraints:

1 <= board.length, board[i].length <= 200
board[i][j] is 'X' or 'O'.


class Solution {
    public void solve(char[][] board) {
        for(int i = 0; i < board.length; i++){
            if(board[i][0]=='O'){
                dfs(i,0,board);
            }
            
        }

        for(int j = 0; j < board[0].length; j++){
            if(board[0][j]=='O'){
                dfs(0,j,board);
            }
        }

        for(int i = 0; i < board.length; i++){
            if(board[i][board[0].length-1]=='O'){
                dfs(i,board[0].length-1,board);
            }
        }

        for(int j = 0; j < board[0].length; j++){
        if(board[board.length-1][j]=='O'){
                dfs(board.length-1,j,board);
            }
        }

         for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }
                if(board[i][j]=='#'){
                    board[i][j]='O';
                }
                
            }
        }



    }


    public void dfs(int i, int j, char[][] board){
        if(i<0 || j<0 || i>=heights.length || j>=heights[0].length || board[i][j]=='#' || board[i][j]=='X'){
            return;
        }


        board[i][j] = '#';
        dfs(i+1,j,board);
        dfs(i-1,j,board);
        dfs(i,j+1,board);
        dfs(i,j-1,board);

    }
}


Course Schedule
You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.

The pair [0, 1], indicates that must take course 1 before taking course 0.

There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

Return true if it is possible to finish all courses, otherwise return false.

Example 1:

Input: numCourses = 2, prerequisites = [[0,1]]

Output: true
Explanation: First take course 1 (no prerequisites) and then take course 0.

Example 2:

Input: numCourses = 2, prerequisites = [[0,1],[1,0]]

Output: false
Explanation: In order to take course 1 you must take course 0, and to take course 0 you must take course 1. So it is impossible.

Constraints:

1 <= numCourses <= 1000
0 <= prerequisites.length <= 1000
prerequisites[i].length == 2
0 <= a[i], b[i] < numCourses
All prerequisite pairs are unique.


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] prereq : prerequisites){
            adj.get(prereq[1].add(prereq[0]));
        }   

        int[] inline = new int[2];

        for(List<Integer>  list : adj){
            for(int i : list){
                inline[i]++;
            }
        }

        if(inline[0]>0){
        return false;
        }

        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        queue.add(0);
        while(!queue.isEmpty()){
            int popped = queue.popped();
            count++;
            for(int v : adj.get(popped)){
                inline[v]--;

                if(inline[v]==0){
                    queue.add(v);
                }
            }
        }

        return count==n;
    }
}


Course Schedule II
You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

Return a valid ordering of courses you can take to finish all courses. If there are many valid answers, return any of them. If it's not possible to finish all courses, return an empty array.

Example 1:

Input: numCourses = 3, prerequisites = [[1,0]]

Output: [0,1,2]
Explanation: We must ensure that course 0 is taken before course 1.

Example 2:

Input: numCourses = 3, prerequisites = [[0,1],[1,2],[2,0]]

Output: []
Explanation: It's impossible to finish all courses.

Constraints:

1 <= numCourses <= 1000
0 <= prerequisites.length <= 1000
All prerequisite pairs are unique.

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[2];
        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] prereq : prerequisites){
            indegree[prereq[0]]++;
            adj.get(prereq[1].add(prereq[0]));
        }   

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i]==0){
            queue.add(i);
            }
        }
              
        int count = 0;
        int[] topsort = new int[n];

        while(!queue.isEmpty()){
            int popped = queue.popped();
            topsort[count] = popped;
            count++;
            for(int v : adj.get(popped)){
                inline[v]--;

                if(inline[v]==0){
                    queue.add(v);
                }
            }
        }

        return topsort.length==n ? topsort : new int[]{};
    }
}


Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not.
The graph is represented as a 2D vector edges[][], where each entry edges[i] = [u, v] denotes an edge from verticex u to v.

Examples:

Input: V = 4, edges[][] = [[0, 1], [1, 2], [2, 0], [2, 3]]



Output: true
Explanation: The diagram clearly shows a cycle 0 → 1 → 2 → 0
Input: V = 4, edges[][] = [[0, 1], [0, 2], [1, 2], [2, 3]]


Output: false
Explanation: no cycle in the graph
Constraints:
1 ≤ V ≤ 105
0 ≤ E ≤ 105
0 ≤ edges[i][0], edges[i][1] < V

class Solution {
    public boolean isCyclic(int V, int[][] edges) {
        
        List<<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
        }

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for(int u = 0; u < V; u++){
            if(!visited[u]){
                if(dfs(u,visited,stack,adj)){
                    return true;
                }
            }
        }

        return false;
    }


    private boolean dfs(int u, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj){
        visited[u]=true;

        for(int v : adj.get(u)){
            if(!visited[v]){
                dfs(v,visited,inrec,adj)){
                    return true;
                }
            }
            else{
                if(inrec[v]){
                    return true;
                }
            }
        }

        inrec[u] = false;

        return false;
    }
}




Graph Valid Tree
Solved 
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input:
n = 5
edges = [[0, 1], [0, 2], [0, 3], [1, 4]]

Output:
true
Example 2:

Input:
n = 5
edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]

Output:
false
Note:

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
Constraints:

1 <= n <= 100
0 <= edges.length <= n * (n - 1) / 2

class Solution {
    public boolean validTree(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[V];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[0,-1]);

        while(!queue.isEmpty()){

            int[] popped = queue.poll();

            int u = popped[0];
            int parent = popped[1];
            visited[u]=true;

            for(int v : adj.get(u)){

                if(!visited[v]){
                    queue.add(new int[]{v,u});
                }
                else{
                    if(parent!=v){
                    return false;
                    }
                }
            }
        }


        for(int i = 0; i < V; i++){
            if(!visited[i]){
                return false;
            }
        }

        return true;
    }
}


    


Number of Connected Components in an Undirected Graph
Solved 
There is an undirected graph with n nodes. There is also an edges array, where edges[i] = [a, b] means that there is an edge between node a and node b in the graph.

The nodes are numbered from 0 to n - 1.

Return the total number of connected components in that graph.

Example 1:

Input:
n=3
edges=[[0,1], [0,2]]

Output:
1
Example 2:

Input:
n=6
edges=[[0,1], [1,2], [2,3], [4,5]]

Output:
2
Constraints:

1 <= n <= 100
0 <= edges.length <= n * (n - 1) / 2

class Solution {
    public int countComponents(int n, int[][] edges) {
        List<<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[V];
        int comps = 0;
        for(int u = 0; u < V; u++){
            if(!visited[u]){
               comps++;
               bfs(u,visited,adj);
            }
        }

        return comps;
    }

    public void bfs(int u, boolean[] visited, List<List<Integer>>){
        Queue<Integer> queue = new LinkedList<>();

        queue.add(u);

        while(!queue.isEmpty()){

            int u = queue.poll();
            visited[u]=true;

            for(int v : adj.get(u)){
                if(!visited[v]){
                    queue.add(v);
                }
            }
        }
    }

}


Redundant Connection
Solved 
You are given a connected undirected graph with n nodes labeled from 1 to n. Initially, it contained no cycles and consisted of n-1 edges.

We have now added one additional edge to the graph. The edge has two different vertices chosen from 1 to n, and was not an edge that previously existed in the graph.

The graph is represented as an array edges of length n where edges[i] = [ai, bi] represents an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the graph is still a connected non-cyclical graph. If there are multiple answers, return the edge that appears last in the input edges.

Example 1:



Input: edges = [[1,2],[1,3],[3,4],[2,4]]

Output: [2,4]
Example 2:



Input: edges = [[1,2],[1,3],[1,4],[3,4],[4,5]]

Output: [3,4]
Constraints:

n == edges.length
3 <= n <= 100
1 <= edges[i][0] < edges[i][1] <= edges.length
There are no repeated edges and no self-loops in the input.

class Solution {
    
    public int[] findRedundantConnection(int[][] edges) {

        List<<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[V];
        boolean[] inrec = new boolean[V];
        List<int[]> toRemove = new ArrayList<>();
        dfs(u,visited,inrec,toRemove,adj);
                 
        int[] res = new int[2];
        for(int[] edge : edges){
            if(toRemove.contains(edge) ){
                res = edge;
            }
        }

        return res;
    }


    private boolean dfs(int u, boolean[] visited, int[] inrec, List<int[]> toRemove, List<List<Integer>> adj){
        visited[u]=true;

        for(int v : adj.get(u)){
            if(!visited[v]){
                dfs(v,visited,inrec,adj);
            }
            else{
                if(inrec[v]){
                    int[] newRemove = new int[2];
                    newRemove[0] = u;
                    newRemove[1] = v;
                    toRemove.add(newRemove);
                }
            }
        }

        inrec[u] = false;
    }
}



Word Ladder
Solved 
You are given two words, beginWord and endWord, and also a list of words wordList. All of the given words are of the same length, consisting of lowercase English letters, and are all distinct.

Your goal is to transform beginWord into endWord by following the rules:

You may transform beginWord to any word within wordList, provided that at exactly one position the words have a different character, and the rest of the positions have the same characters.
You may repeat the previous step with the new word that you obtain, and you may do this as many times as needed.
Return the minimum number of words within the transformation sequence needed to obtain the endWord, or 0 if no such sequence exists.

Example 1:

Input: beginWord = "cat", endWord = "sag", wordList = ["bat","bag","sag","dag","dot"]

Output: 4
Explanation: The transformation sequence is "cat" -> "bat" -> "bag" -> "sag".

Example 2:

Input: beginWord = "cat", endWord = "sag", wordList = ["bat","bag","sat","dag","dot"]

Output: 0
Explanation: There is no possible transformation sequence from "cat" to "sag" since the word "sag" is not in the wordList.

Constraints:

1 <= beginWord.length <= 10
1 <= wordList.length <= 100

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
    }
}



277. Find the Celebrity

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
conpanies icon
Companies
Hint
Suppose you are at a party with n people labeled from 0 to n - 1 and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know the celebrity, but the celebrity does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. You are only allowed to ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given an integer n and a helper function bool knows(a, b) that tells you whether a knows b. Implement a function int findCelebrity(n). There will be exactly one celebrity if they are at the party.

Return the celebrity's label if there is a celebrity at the party. If there is no celebrity, return -1.

Note that the n x n 2D array graph given as input is not directly available to you, and instead only accessible through the helper function knows. graph[i][j] == 1 represents person i knows person j, wherease graph[i][j] == 0 represents person j does not know person i.

 

Example 1:


Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
Example 2:


Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
Output: -1
Explanation: There is no celebrity.
 

Constraints:

n == graph.length == graph[i].length
2 <= n <= 100
graph[i][j] is 0 or 1.
graph[i][i] == 1
 

Follow up: If the maximum number of allowed calls to the API knows is 3 * n, could you find a solution without exceeding the maximum number of calls?

Decode Ways
A string consisting of uppercase english characters can be encoded to a number using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode a message, digits must be grouped and then mapped back into letters using the reverse of the mapping above. There may be multiple ways to decode a message. For example, "1012" can be mapped into:

"JAB" with the grouping (10 1 2)
"JL" with the grouping (10 12)
The grouping (1 01 2) is invalid because 01 cannot be mapped into a letter since it contains a leading zero.

Given a string s containing only digits, return the number of ways to decode it. You can assume that the answer fits in a 32-bit integer.

Example 1:

Input: s = "12"

Output: 2

Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: s = "01"

Output: 0
Explanation: "01" cannot be decoded because "01" cannot be mapped into a letter.

Constraints:

1 <= s.length <= 100
s consists of digits

class Solution {
    public int numDecodings(String s) {
       int count = 0;
       int[] map = new int[27];
       Arrays.fill(map,1);
       map[0]=0;
       int[] dp = new int[s.length()];
       Arrays.fill(dp,-1);
       return solve(0, map, s, dp); 
    }

    public int solve(int idx, int[] map, String s, int[] dp){
        if(idx==s.length()){
            return 1;
        }

        if(dp[idx]!=-1){
            return dp[idx];
        }
        int count = 0;

        for(int i = idx; i < s.length(); i++){
            String sub = s.substring(idx,i+1);
            int num = Integer.parseInt(sub);
            if(num==0 || num>26){
                break;
            }
            if(map[Integer.parseInt(sb)]==1){
               count+= solve(i+1,map,s,dp);
            }

        }
        return dp[idx] = count;
    }

}



Coin Change
Solved 
You are given an integer array coins representing coins of different denominations (e.g. 1 dollar, 5 dollars, etc) and an integer amount representing a target amount of money.

Return the fewest number of coins that you need to make up the exact target amount. If it is impossible to make up the amount, return -1.

You may assume that you have an unlimited number of each coin.

Example 1:

Input: coins = [1,5,10], amount = 12

Output: 3
Explanation: 12 = 10 + 1 + 1. Note that we do not have to use every kind coin available.

Example 2:

Input: coins = [2], amount = 3

Output: -1
Explanation: The amount of 3 cannot be made up with coins of 2.

Example 3:

Input: coins = [1], amount = 0

Output: 0
Explanation: Choosing 0 coins is a valid way to make up 0.

Constraints:

1 <= coins.length <= 10
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10000

1,2,5
class Solution {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        long[][] dp = new long[coins.length][amount];
        for(long[] arr : dp) Arrays.fill(arr,-1);
        int res = (int)solve(coins.length-1,coins,amount,dp);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private long solve(int idx, int[] coins, long amount, long[][] dp){
        if(idx < 0){
            if(amount==0){
                return 0;
            }
            else return Integer.MAX_VALUE;
        }

        if(amount == 0){
            return 0;
        }
        if(amount<0){
            return Integer.MAX_VALUE;
        }

        if(dp[idx][amount]!=-1){
        return dp[idx][amount];
        }
        long take = Integer.MAX_VALUE;
        long notTake = Integer.MAX_VALUE;
        
        take = 1 + solve(idx,coins,amount-coins[idx],dp);
        notTake = 0 + solve(idx-1,coins,amount,dp);
            return dp[idx][amount] = Math.min(take,notTake);
        }
}


class Solution {
    public int maxProduct(int[] nums) {
    int[] leftProd = new int[nums.length];
    int[] rightProd = new int[nums.length];

    int left = 1;
    int right = 1;

    int res = Integer.MIN_VALUE;
    for(int i = 0; i < nums.length; i++){
        left*=nums[i];
        right*=nums[nums.length-i-1];
        if(left==0)left=1;
        if(right==0)right=1;
        res = Math.max(res, Math.max(leftProd,rightProd));
    }

    return res;        
    }
}


Word Break
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of dictionary words.

You are allowed to reuse words in the dictionary an unlimited number of times. You may assume all dictionary words are unique.

Example 1:

Input: s = "neetcode", wordDict = ["neet","code"]

Output: true
Explanation: Return true because "neetcode" can be split into "neet" and "code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen","ape"]

Output: true
Explanation: Return true because "applepenapple" can be split into "apple", "pen" and "apple". Notice that we can reuse words and also not use all the words.

Example 3:

Input: s = "catsincars", wordDict = ["cats","cat","sin","in","car"]

Output: false
Constraints:

1 <= s.length <= 200
1 <= wordDict.length <= 100
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
    }
}


Longest Common Subsequence
Given two strings text1 and text2, return the length of the longest common subsequence between the two strings if one exists, otherwise return 0.

A subsequence is a sequence that can be derived from the given sequence by deleting some or no elements without changing the relative order of the remaining characters.

For example, "cat" is a subsequence of "crabt".
A common subsequence of two strings is a subsequence that exists in both strings.

Example 1:

Input: text1 = "cat", text2 = "crabt" 

Output: 3 
Explanation: The longest common subsequence is "cat" which has a length of 3.

Example 2:

Input: text1 = "abcd", text2 = "abcd"

Output: 4
Example 3:

Input: text1 = "abcd", text2 = "efgh"

Output: 0
Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for(int[] arr : dp)Arrays.fill(arr,-1);
        return solve(0,0,text1,text2,dp);        
    }

    private int solve(int idx1, int idx2, String text1, String text2, int[][] dp){

        if(idx1==text1.length() || idx2==text2.length()){
            return 0;
        }

        if(dp[idx1][idx2]!=-1){
            return dp[idx1][idx2];
        }
        int take1 = 0, take2 = 0, take3 = 0;

        if(text1.charAt(idx1)==text2.charAt(idx2)){
            take1 = 1 + solve(idx1+1,idx2+1,text1,text2,dp);
        } else{
            take2 = 0 + solve(idx1+1,idx2,text1,text2,dp);
            take3 = 0 + solve(idx1,idx2+1,text1,text2,dp);
        }

        return dp[idx1][idx2] = Math.max(take1, Math.max(take2,take3));
    }
}


Best Time to Buy and Sell Stock with Cooldown
You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.

You may buy and sell one NeetCoin multiple times with the following restrictions:

After you sell your NeetCoin, you cannot buy another one on the next day (i.e., there is a cooldown period of one day).
You may only own at most one NeetCoin at a time.
You may complete as many transactions as you like.

Return the maximum profit you can achieve.

Example 1:

Input: prices = [1,3,4,0,4]

Output: 6
Explanation: Buy on day 0 (price = 1) and sell on day 1 (price = 3), profit = 3-1 = 2. Then buy on day 3 (price = 0) and sell on day 4 (price = 4), profit = 4-0 = 4. Total profit is 2 + 4 = 6.

Example 2:

Input: prices = [1]

Output: 0
Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000


class Solution {
    public int maxProfit(int[] prices) {
        return solve(0, 0, 0, false, prices);
    }

    private int solve(int idx, int buysell, int buyprice, boolean coolDown, int[] prices){
        if(idx==prices.length){

            return 0;
        }

        int buythis = 0;
        int skipbuy = 0;
        int maxbuy = 0;

        if(buysell == 0){

            buythis = (-1 * prices[idx]) + solve(idx+1, 1, prices[idx], false, prices);
            notbuy = solve(idx+1, 0, 0, false, prices);

            maxbuy = Math.max(buythis,notbuy);
        } else{

            if(coolDown){
                return solve(idx+1, 0, 0, false, prices);
            }
            else{

            if(prices[idx] > buyPrice){

            return Math.max(prices[idx] + solve(idx+1, 1, 0, true, prices),solve(idx+1, 1, buyprice, false, prices));
            }
            else return solve(idx+1, 1, buyPrice, false, prices);
            }

            
        }

        return maxbuy;
    }
}


Coin Change II
You are given an integer array coins representing coins of different denominations (e.g. 1 dollar, 5 dollars, etc) and an integer amount representing a target amount of money.

Return the number of distinct combinations that total up to amount. If it's impossible to make up the amount, return 0.

You may assume that you have an unlimited number of each coin and that each value in coins is unique.

Example 1:

Input: amount = 4, coins = [1,2,3]

Output: 4
Explanation:

1+1+1+1 = 4
1+1+2 = 4
2+2 = 4
1+3 = 4
Example 2:

Input: amount = 7, coins = [2,4]

Output: 0
Constraints:

1 <= coins.length <= 100
1 <= coins[i] <= 5000
0 <= amount <= 5000


class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount+1];
        for(int[] arr : dp)Arrays.fill(arr,-1);
        return solve(0, amount, coins, dp);
    }

    private int solve(int idx, int amount, int[] coins, int[][] dp){

        if(idx == coins.length){
            if(amount==0){
                return 1;
            }
            else return 0;
        }

        if(amount == 0){
            return 1;
        }

        if(amount < 0){
            return 0;
        }

        if(dp[idx][amount]!=-1){
        return dp[idx][amount];
        }
        take = solve(idx,amount-coins[idx],coins);
        notTake = solve(idx+1, amount, coins);
        return dp[idx][amount] = take + notTake;
    }
}


Target Sum
You are given an array of integers nums and an integer target.

For each number in the array, you can choose to either add or subtract it to a total sum.

For example, if nums = [1, 2], one possible sum would be "+1-2=-1".
If nums=[1,1], there are two different ways to sum the input numbers to get a sum of 0: "+1-1" and "-1+1".

Return the number of different ways that you can build the expression such that the total sum equals target.

Example 1:

Input: nums = [2,2,2], target = 2

Output: 3
Explanation: There are 3 different ways to sum the input numbers to get a sum of 2.
+2 +2 -2 = 2
+2 -2 +2 = 2
-2 +2 +2 = 2

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
-1000 <= target <= 1000


class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        
    }
}


Interleaving String
You are given three strings s1, s2, and s3. Return true if s3 is formed by interleaving s1 and s2 together or false otherwise.

Interleaving two strings s and t is done by dividing s and t into n and m substrings respectively, where the following conditions are met

|n - m| <= 1, i.e. the difference between the number of substrings of s and t is at most 1.
s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
Interleaving s and t is s1 + t1 + s2 + t2 + ... or t1 + s1 + t2 + s2 + ...
You may assume that s1, s2 and s3 consist of lowercase English letters.

Example 1:



Input: s1 = "aaaa", s2 = "abbbb", s3 = "aabbbbaa"

Output: true
Explanation: We can split s1 into ["aa", "aa"], s2 can remain as "bbbb" and s3 is formed by interleaving ["aa", "aa"] and "bbbb".

Example 2:

Input: s1 = "", s2 = "", s3 = ""

Output: true
Example 3:

Input: s1 = "abc", s2 = "xyz", s3 = "abxzcy"

Output: false


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        Boolean[][][] dp = new Boolean[s1.length()][s2.length()][s3.length()];
        return solve(0,0,0,s1,s2,s3,dp);
    }

    private boolean solve(int idx1, int idx2, int idx3, String s1, String s2, String s3, Boolean[][][] dp){

        if(idx3 == s3.length()){
            return (idx1 == s1.length() && idx2 == s2.length())
        }

        if(idx1 == s1.length() && idx2 == s2.length()){
            return false;
        }

        if(dp[idx1][idx2][idx3]!=null){
            dp[idx1][idx2][idx3]
        }
        boolean takes1 = false;
        boolean takes2 = false;
        if(idx1<s1.length() && s1.charAt(idx1) == s3.charAt(idx3)){
            takes1 = solve(idx1+1,idx2,idx3+1,s1,s2,s3);
        }
        if(idx2<s2.length() && s2.charAt(idx2) == s3.charAt(idx3)){
            takes2 = solve(idx1,idx2+1,idx3+1,s1,s2,s3);
        }
        return dp[idx1][idx2][idx3] = takes1 || takes2;
    }
}


Longest Increasing Path in Matrix
You are given a 2-D grid of integers matrix, where each integer is greater than or equal to 0.

Return the length of the longest strictly increasing path within matrix.

From each cell within the path, you can move either horizontally or vertically. You may not move diagonally.

Example 1:

5 5 3
2 3 6
1 1 1

Input: matrix = [[5,5,3],[2,3,6],[1,1,1]]

Output: 4
Explanation: The longest increasing path is [1, 2, 3, 6] or [1, 2, 3, 5].

Example 2:



Input: matrix = [[1,2,3],[2,1,4],[7,6,5]]

Output: 7
Explanation: The longest increasing path is [1, 2, 3, 4, 5, 6, 7].

Constraints:

1 <= matrix.length, matrix[i].length <= 100

class Solution {
    public int longestIncreasingPath(int[][] matrix) {

        int lip = 0;

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                lip = Math.max(lip,solve(matrix.length-1,matrix[0].length-1,Integer.MAX_VALUE,matrix));
            }
        }
        return lip;
    }

    private int solve(int i, int j, int prev, int[][] matrix){
        if(i<0 || j<0 || i>=matrix.length || j>=matrix[0].length){
            return 0;
        }

        int left = 0, right = 0, top = 0, down = 0;
        if(matrix[i][j] >= prev){
            left = -1 + solve(i,j-1,matrix[i][j],matrix);
            top  = -1 + solve(i-1,j,matrix[i][j],matrix);
            right= -1 + solve(i,j+1,matrix[i][j],matrix);
            down = -1 + solve(i+1,j,matrix[i][j],matrix);

        } else{
            left = 1 + solve(i,j-1,matrix[i][j],matrix);
            top  = 1 + solve(i-1,j,matrix[i][j],matrix);
            right= 1 + solve(i,j+1,matrix[i][j],matrix);
            down = 1 + solve(i+1,j,matrix[i][j],matrix);
  
        }
        
        return Math.max(Math.max(left,right),Math.max(top,down));
    }
}



Distinct Subsequences
You are given two strings s and t, both consisting of english letters.

Return the number of distinct subsequences of s which are equal to t.

Example 1:

Input: s = "caaat", t = "cat"

Output: 3
Explanation: There are 3 ways you can generate "cat" from s.

(c)aa(at)
(c)a(a)a(t)
(ca)aa(t)
Example 2:

Input: s = "xxyxy", t = "xy"

Output: 5
Explanation: There are 5 ways you can generate "xy" from s.

(x)x(y)xy
(x)xyx(y)
x(x)(y)xy
x(x)yx(y)
xxy(x)(y)
Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.

class Solution {
    public int numDistinct(String s, String t) {
        
    }
}


Distinct Subsequences
You are given two strings s and t, both consisting of english letters.

Return the number of distinct subsequences of s which are equal to t.

Example 1:

Input: s = "caaat", t = "cat"

Output: 3
Explanation: There are 3 ways you can generate "cat" from s.

(c)aa(at)
(c)a(a)a(t)
(ca)aa(t)
Example 2:

Input: s = "xxyxy", t = "xy"

Output: 5
Explanation: There are 5 ways you can generate "xy" from s.

(x)x(y)xy
(x)xyx(y)
x(x)(y)xy
x(x)yx(y)
xxy(x)(y)
Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.


class Solution {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        for(int[] arr : dp){
            Arrays.fill(arr,-1);
        }
        return solve(0,0,s,t,dp);        
    }

    private int solve(int sidx, int tidx, String s, String t, int[][] dp){
        if(sidx==s.length()){
            return tidx == t.length() ? 1 : 0;
        }

        if(tidx == t.length()){
            return 1;
        }

        if(dp[sidx][tidx]!=-1){
            return dp[sidx][tidx];
        }
        int take = 0, notTake = 0;

        if(s.charAt(sidx)==t.charAt(tidx)){
            take = solve(sidx+1,tidx+1,s,t,dp);
        }

        notTake = solve(sidx+1,tidx,s,t,dp);

        return dp[sidx][tidx] = take + notTake;
    }
}


Edit Distance
You are given two strings word1 and word2, each consisting of lowercase English letters.

You are allowed to perform three operations on word1 an unlimited number of times:

Insert a character at any position
Delete a character at any position
Replace a character at any position
Return the minimum number of operations to make word1 equal word2.

Example 1:

Input: word1 = "monkeys", word2 = "money"

Output: 2
Explanation:
monkeys -> monkey (remove s)
monkey -> money (remove k)

Example 2:

Input: word1 = "neatcdee", word2 = "neetcode"

Output: 3
Explanation:
neatcdee -> neetcdee (replace a with e)
neetcdee -> neetcde (remove last e)
neetcde -> neetcode (insert o)

Constraints:

0 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.

class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for(int[] arr : dp){
            Arrays.fill(arr,-1);
        }
        return (int)solve(0,0,word1,word2,dp);
    }

    private long solve(int idx1, int idx2, String word1, String word2, int[][] dp){
        if(idx2==word2.length()){
            return word1.length() - idx1;
        }

        if(idx1==word1.length()){
            return word2.length() - idx2;
        }

        if(dp[idx1][idx2]!=-1){
            return dp[idx1][idx2];
        }

        long noAction = Integer.MAX_VALUE, insert = Integer.MAX_VALUE, delete = Integer.MAX_VALUE, replace = Integer.MAX_VALUE;
        if(word1.charAt(idx1)==word2.charAt(idx2)){
            noAction = 0 + solve(idx1+1,idx2+1,word1,word2,dp);
        } else{
            insert = 1 + solve(idx1,idx2+1,word1,word2,dp);
            delete = 1 + solve(idx1+1,idx2,word1,word2,dp);
            replace = 1 + solve(idx1+1,idx2+1,word1,word2,dp);
        }

        return dp[idx1][idx2] = Math.min(Math.min(noAction,insert),Math.min(delete,replace));
    }
}


Burst Balloons
You are given an array of integers nums of size n. The ith element represents a balloon with an integer value of nums[i]. You must burst all of the balloons.

If you burst the ith balloon, you will receive nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then assume the out of bounds value is 1.

Return the maximum number of coins you can receive by bursting all of the balloons.

Example 1:

Input: nums = [4,2,3,7]

Output: 143

Explanation:
nums = [4,2,3,7] --> [4,3,7] --> [4,7] --> [7] --> []
coins =  4*2*3    +   4*3*7   +  1*4*7  + 1*7*1 = 143
Constraints:

n == nums.length
1 <= n <= 300
0 <= nums[i] <= 100


class Solution {
    public int maxCoins(int[] nums) {
        return solve(0, 0, nums);
    }

    private int solve(int idx, int prevIdx, int[] nums){
        if(idx == nums.length){
            return 0;
        }

        int take = 0, notTake = 0;

        int thiscoins = nums[idx];

        if(prevIdx < idx){
            thiscoins*= nums[prevIdx];
        }

        if(idx+1 < nums.length){
            thiscoins*= nums[idx+1];
        }

        take = thiscoins + solve(idx+1,idx+1,nums);
        notTake = solve(idx+1,prevIdx,nums);

        return Math.max(take,notTake);
    }
}


Regular Expression Matching
You are given an input string s consisting of lowercase english letters, and a pattern p consisting of lowercase english letters, as well as '.', and '*' characters.

Return true if the pattern matches the entire input string, otherwise return false.

'.' Matches any single character
'*' Matches zero or more of the preceding element.
Example 1:

Input: s = "aa", p = ".b"

Output: false
Explanation: Regardless of which character we choose for the '.' in the pattern, we cannot match the second character in the input string.

Example 2:

Input: s = "nnn", p = "n*"

Output: true
Explanation: '*' means zero or more of the preceding element, 'n'. We choose 'n' to repeat three times.

Example 3:

Input: s = "xyz", p = ".*z"

Output: true
Explanation: The pattern ".*" means zero or more of any character, so we choose ".." to match "xy" and "z" to match "z".

Constraints:

1 <= s.length <= 20
1 <= p.length <= 20
Each appearance of '*', will be preceded by a valid character or '.'.


class Solution {
    public boolean isMatch(String s, String p) {
        return solve(0,0,s,p);
    }

    private boolean solve(int sidx, int pidx, String s, String p){
        if(sidx == s.length()){
            if(pidx==p.length()){
                return true;
            }
            else{
                while(pidx<p.length()){
                    if(p.charAt(pidx) == '.' || p.charAt(pidx)=='*'){
                        pidx++;
                    }else{
                        return false;
                    }
                   
                }
                return true;
            }
        }
        if(pidx == p.length()){
            return false;
        }
        boolean charmatch = false;
        boolean dotmatch = false;
        boolean star0match = false;
        boolean star1match = false;
        boolean starmatch = false;
        if(s.charAt(sidx) == p.charAt(pidx)){
            charmatch = solve(sidx+1,pidx+1,s,p);
        }
        
        if(p.charAt(pidx)=='.' || p.charAt(pidx)=='*'){
            if(p.charAt(pidx)=='.'){
                dotmatch = solve(sidx+1,pidx+1,s,p);
            }
            else{
                star0match = solve(sidx,pidx+1,s,p);
                star1match = solve(sidx+1,pidx+1,s,p);
                starmatch = solve(sidx+1,pidx,s,p);
            }
        }
        else return false;

        return charmatch || dotmatch || star0match || star1match || starmatch;
    }
}


Maximum Subarray
Given an array of integers nums, find the subarray with the largest sum and return the sum.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [2,-3,4,-2,2,1,-1,4]

Output: 8
Explanation: The subarray [4,-2,2,1,-1,4] has the largest sum 8.

Example 2:

Input: nums = [-1]

Output: -1
Constraints:

1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000


class Solution {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for(int i : nums){
            sum+=i;

            res = Math.max(sum,res);
            if(sum<0){
            sum = 0;
            }
        }

        return res;
    }
}


Jump Game
Solved 
You are given an integer array nums where each element nums[i] indicates your maximum jump length at that position.

Return true if you can reach the last index starting from index 0, or false otherwise.

Example 1:

Input: nums = [1,2,0,1,0]

Output: true
Explanation: First jump from index 0 to 1, then from index 1 to 3, and lastly from index 3 to 4.

Example 2:

Input: nums = [1,2,1,0,1]

Output: false
Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 1000

class Solution {
    public boolean canJump(int[] nums) {
        if(nums.length == 1)return true;

        int j = nums.length-1;
        int i = nums.length-2;

        while(j>0){

            while(i>=0 && i+nums[i]<j){
                i--;
            }

            if(i<0){
            return false;
            }

            j = i;
            i = j-1;
        }

        return true;
    }
}


Jump Game II
You are given an array of integers nums, where nums[i] represents the maximum length of a jump towards the right from index i. For example, if you are at nums[i], you can jump to any index i + j where:

j <= nums[i]
i + j < nums.length
You are initially positioned at nums[0].

Return the minimum number of jumps to reach the last position in the array (index nums.length - 1). You may assume there is always a valid answer.

Example 1:

Input: nums = [2,4,1,1,1,1]

Output: 2
Explanation: Jump from index 0 to index 1, then jump from index 1 to the last index.

Example 2:

Input: nums = [2,1,2,1,0]

Output: 2
Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 100


class Solution {
    public int jump(int[] nums) {
    int jumps = 0;
      if(nums.length == 1)return 0;

        int j = nums.length-1;
        int i = nums.length-2;

        while(j>0){

            int nextJ = j;
            while(i>=0){
                if(i+nums[i]>=j){
                    nextJ = i;
                }
                i--;
            }

            jumps++;
            j = nextJ;
            i = j-1;
        }

        return jumps;   
    }
}



Gas Station
There are n gas stations along a circular route. You are given two integer arrays gas and cost where:

gas[i] is the amount of gas at the ith station.
cost[i] is the amount of gas needed to travel from the ith station to the (i + 1)th station. (The last station is connected to the first station)
You have a car that can store an unlimited amount of gas, but you begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index such that you can travel around the circuit once in the clockwise direction. If it's impossible, then return -1.

It's guaranteed that at most one solution exists.

Example 1:

Input: gas = [1,2,3,4], cost = [2,2,4,1]

Output: 3
Explanation: Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 1 + 1 = 4
Travel to station 1. Your tank = 4 - 2 + 2 = 4
Travel to station 2. Your tank = 4 - 2 + 3 = 5
Travel to station 3. Your tank = 5 - 4 + 4 = 5

Example 2:

Input: gas = [1,2,3], cost = [2,3,2]

Output: -1
Explanation:
You can't start at station 0 or 1, since there isn't enough gas to travel to the next station.
If you start at station 2, you can move to station 0, and then station 1.
At station 1 your tank = 0 + 3 - 2 + 1 - 2 = 0.
You're stuck at station 1, so you can't travel around the circuit.

Constraints:

1 <= gas.length == cost.length <= 1000
0 <= gas[i], cost[i] <= 1000


class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
       if(gas.length==1)return 0;
       for(int i = 0; i < gas.length; i++){
        if(can(i,gas,cost)){
            return i;
        }
       }
       return -1; 
    }

    private boolean can(int i, int[] gas, int[] cost){

        int j = i;
        int remainingGas = gas[j];

        if(cost[j]<=remainingGas){
            remainingGas-=cost[j];
            j++;
        }else return false;

        j = j%gas.length;
        remainingGas+=gas[j];
        while(j!=i){

            if(cost[j]<=remainingGas){
            remainingGas-=cost[j];
            j++;
            }{
            else return false;
            }

            j = j%gas.length;
            remainingGas+=gas[j];
        }

        return true;
    }
}


Valid Parenthesis String
You are given a string s which contains only three types of characters: '(', ')' and '*'.

Return true if s is valid, otherwise return false.

A string is valid if it follows all of the following rules:

Every left parenthesis '(' must have a corresponding right parenthesis ')'.
Every right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
A '*' could be treated as a right parenthesis ')' character or a left parenthesis '(' character, or as an empty string "".
Example 1:

Input: s = "((**)"

Output: true
Explanation: One of the '*' could be a ')' and the other could be an empty string.

Example 2:

Input: s = "(((*)"

Output: false
Explanation: The string is not valid because there is an extra '(' at the beginning, regardless of the extra '*'.

Constraints:

1 <= s.length <= 100

class Solution {
    public boolean checkValidString(String s) {
        
    int minopen = 0, maxopen = 0;

    for(char ch : s.toCharArray()){
        if(ch=='('){
            minopen++;
            maxopen++;
        }else if(ch==')'){
            minopen--;
            maxopen--;
        }else{
            minopen--;
            maxopen++;
        }

        if(maxopen<0)return false;

        if(minopen<0) minopen = 0;
    }

    return minopen==0;
}

}


Insert Interval
You are given an array of non-overlapping intervals intervals where intervals[i] = [start_i, end_i] represents the start and the end time of the ith interval. intervals is initially sorted in ascending order by start_i.

You are given another interval newInterval = [start, end].

Insert newInterval into intervals such that intervals is still sorted in ascending order by start_i and also intervals still does not have any overlapping intervals. You may merge the overlapping intervals if needed.

Return intervals after adding newInterval.

Note: Intervals are non-overlapping if they have no common point. For example, [1,2] and [3,4] are non-overlapping, but [1,2] and [2,3] are overlapping.

Example 1:

Input: intervals = [[1,3],[4,6]], newInterval = [2,5]

Output: [[1,6]]
Example 2:

Input: intervals = [[1,2],[3,5],[9,10]], newInterval = [6,7]

Output: [[1,2],[3,5],[6,7],[9,10]]
Constraints:

0 <= intervals.length <= 1000
newInterval.length == intervals[i].length == 2
0 <= start <= end <= 1000

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();

        for(int[] interval : intervals){

            if(newInterval[1] < interval[0]){
                res.add(newInterval);
                newInterval = interval;
            } else if(newInterval[0]<=interval[1]){
                newInterval[0] = Math.min(newInterval[0],interval[0]);
                newInterval[1] = Math.max(newInterval[1],interval[1]);
            } else res.add(interval);
        }

        res.add(newInterval);

        return res.
    }
}


Merge Intervals
Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

You may return the answer in any order.

Note: Intervals are non-overlapping if they have no common point. For example, [1, 2] and [3, 4] are non-overlapping, but [1, 2] and [2, 3] are overlapping.

Example 1:

Input: intervals = [[1,3],[1,5],[6,7]]

Output: [[1,5],[6,7]]
Example 2:

Input: intervals = [[1,2],[2,3]]

Output: [[1,3]]
Constraints:

1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= start <= end <= 1000

class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        for(int[] interval : intervals){

            if(res.isEmpty()){
                res.add(interval);
            }
            else if(interval[0]<=res.get(res.size()-1)[1]){
                res.get(res.size()-1)[1] = Math.max(res.get(res.size()-1)[1],interval[1]);
            } else res.add(interval);
        }

        return res.toArray(new int[res.size()][]);
    }
}

Non-overlapping Intervals
Given an array of intervals intervals where intervals[i] = [start_i, end_i], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note: Intervals are non-overlapping even if they have a common point. For example, [1, 3] and [2, 4] are overlapping, but [1, 2] and [2, 3] are non-overlapping.

Example 1:

Input: intervals = [[1,2],[2,4],[1,4]]

Output: 1
Explanation: After [1,4] is removed, the rest of the intervals are non-overlapping.

Example 2:

Input: intervals = [[1,2],[2,4]]

Output: 0
Constraints:

1 <= intervals.length <= 1000
intervals[i].length == 2
-50000 <= starti < endi <= 50000

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->a[1]-b[1]);
        int res = 0;
        int prevEnd = Integer.MIN_VALUE;
        for(int[] interval : intervals){
            if(interval[0]<prevEnd){
                res++;
            }
            else prevEnd = interval[1];  
        }
        return res;
    }
} 

1,5 1,8 1,10


Meeting Rooms
Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), determine if a person could add all meetings to their schedule without any conflicts.

Note: (0,8),(8,10) is not considered a conflict at 8

Example 1:

Input: intervals = [(0,30),(5,10),(15,20)]

Output: false
Explanation:

(0,30) and (5,10) will conflict
(0,30) and (15,20) will conflict
Example 2:

Input: intervals = [(5,8),(9,15)]

Output: true
Constraints:

0 <= intervals.length <= 500
0 <= intervals[i].start < intervals[i].end <= 1,000,000


/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);

        int prevEnd = Integer.MIN_VALUE;
        for(int[] interval : intervals){

            if(interval[0]<prevEnd){
            return false;
            }
            else prevEnd = interval[1];
        }

        return true;


    }
}


Multiply Strings
Solved 
You are given two strings num1 and num2 that represent non-negative integers.

Return the product of num1 and num2 in the form of a string.

Assume that neither num1 nor num2 contain any leading zero, unless they are the number 0 itself.

Note: You can not use any built-in library to convert the inputs directly into integers.

Example 1:

Input: num1 = "3", num2 = "4"

Output: "12"
Example 2:

Input: num1 = "111", num2 = "222"

Output: "24642"
Constraints:

1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.

public class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }

        int[] res = new int[num1.length() + num2.length()];
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        for(int i = 0; i < num1.length(); i++){
            for(int j = 0; j < num2.length(); j++){
                int digit = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                res[i+j]+=digit;
                res[i+j+1] = res[i+j]/10;
                res[i+j]%=10
            }
        }

        int i = res.length-1;
        StringBuilder sb = new StringBuilder();
        while(i>=0){
            sb.append(res[i--]);
        }

        return sb.toString();
    }
}