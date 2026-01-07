Queue using Linked List
Difficulty: BasicAccuracy: 45.6%Submissions: 155K+Points: 1Average Time: 20m
Implement a Queue using Linked List. 
A Query Q is of 2 Types
(i) 1 x   (a query of this type means  pushing 'x' into the queue)
(ii) 2     (a query of this type means to pop an element from the queue and print the poped element)

Examples:

Input: Q = 5, Queries = 1 2 1 3 2 1 4 2
Output: 2 3 
Explanation: 
[1,2] queue will be 2
[1,3] queue will be 2,3
[2] poped element will be 2 the queue will be 3
[1, 4] queue will be 3, 4
[2] poped element will be 3 
Input: Q = 4, Queries = 1 2 2 2 1 3
Output: 2 -1
Explanation:
[1, 2] queue will be 2
[2]  poped element will be 2 then
    the queue will be empty. 
[2]  the queue is empty and hence -1
[1, 3] the queue will be 3
Constraints:
1 <= Q <= 100
1 <= x <= 100

/*The structure of the node of the queue is
class QueueNode
{
    int data;
    QueueNode next;
    QueueNode(int a)
    {
        data = a;
        next = null;
    }
}*/

class MyQueue {
    QueueNode front, rear;

    public MyQueue(){
        this.front=null;
        this.rear=null;
    }

    void push(int a) {
                if(front==null){
                    front = new QueueNode(a);
                    rear = front;
                }
                else{
                    rear.next = new QueueNode(a);
                    rear = rear.next;
                }
    }

    // Function to pop front element from the queue.
    int pop() {

        if(front==null){
        rear=null;
        return -1;
        }
        
        int popped = front.data;
        front = front.next;
        return popped;
        
        

        
    }
}


Stack using Linked List
Difficulty: EasyAccuracy: 53.98%Submissions: 182K+Points: 2Average Time: 40m
Let's give it a try! You have a linked list and must implement the functionalities push and pop of stack using this given linked list. Your task is to use the class as shown in the comments in the code editor and complete the functions push() and pop() to implement a stack. 
The push() method takes one argument, an integer 'x' to be pushed into the stack and pop() which returns an integer present at the top and popped out from the stack. If the stack is empty then return -1 from the pop() method.
Note: The input is given in the form of queries. Since there are two operations push() and pop(), there is two types of queries as described below:
(i) 1   (a query of this type takes x as another parameter and pushes it into the stack)
(ii) 2  (a query of this type means to pop an element from the stack and return the popped element)
Input is separated by space and as described above. 

Examples :

Input: [[1,2], [1,3], [2], [1,4], [2]]
Output: [3, 4]
Explanation: 
push(2)  : the stack will be {2}
push(3)  : the stack will be {2 3}
pop()    : poped element will be 3,the stack will be {2}
push(4)  : the stack will be {2 4}
pop()    : poped element will be 4
Input: [[2], [1,4], [1,5], [2]]
Output: [-1, 4]
Explanation: 
pop()    : the stack is empty so its -1.
push(4)  : the stack will be {4}
push(5)  : the stack will be {4 5}
pop()    : poped element will be 5, the stack will be {4}
Expected Time Complexity: O(1)
Expected Auxillary Space: O(1)

Constraints:
1 <= numbers of calls made to push, pop <= 100
1 <= x <= 100

class MyStack {
    // class StackNode {
    //     int data;
    //     StackNode next;
    //     StackNode(int a) {
    //         data = a;
    //         next = null;
    //     }
    // }
    StackNode top;

    // Function to push an integer into the stack.
    void push(int a) {
        StackNode newNode = new StackNode(a);
        newNode.next = top;
        top = newNode;
    }

    // Function to remove an item from top of the stack.
    int pop() {

        if(top==null)return -1;

        int popped = top.data;
        top = top.next;
        return popped;
        // Add your code here
    }
}


155. Min Stack
Solved
Medium
Topics
conpanies icon
Companies
Hint
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

 

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
 

Constraints:

-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.

class Pair{
    int a, b;
    public Pair(int a, int b){
        this.a=a;
        this.b=b;
    }
}

class MinStack {
    Stack<Pair> st;
    Stack ms;
    public MinStack() {
        st = new Stack<Pair>();
    }
    
    public void push(int val) {

        if(st.isEmpty())st.push(new Pair(val,val));
        else{
            st.push(new Pair(val,Math.min(st.peek().b,val)));
        }
    }
    
    public void pop() {
       st.pop();
    }
    
    public int top() {
       return st.peek().a;
    }
    
    public int getMin() {
        return st.peek().b;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


 503. Next Greater Element II
Medium
Topics
conpanies icon
Companies
Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

 

Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number. 
The second 1's next greater number needs to search circularly, which is also 2.
Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
 

Constraints:

1 <= nums.length <= 104
-109 <= nums[i] <= 109

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> minstack = new Stack<>();

        for(int i = nums.length-2; i >= 0; i--)minstack.push(nums[i]);
        int[] res = new int[nums.length];
        for(int j = nums.length-1; j >= 0; j--){

            
            while(!minstack.isEmpty() && minstack.peek()<=nums[j]){
                minstack.pop();
            }

            if(minstack.isEmpty()){
                res[j]=-1;
            }
            else res[j]=minstack.peek();
            minstack.push(nums[j]);
        }
        return res;

    }
}


Number of greater elements to the right
Difficulty: MediumAccuracy: 56.74%Submissions: 38K+Points: 4Average Time: 10m
Given an array of N integers and Q queries of indices. For each query indices[i], determine the count of elements in arr that are strictly greater than arr[indices[i]] to its right (after the position indices[i]).

Examples :

Input: arr[] = [3, 4, 2, 7, 5, 8, 10, 6], queries = 2, indices[] = [0, 5]
Output:  [6, 1]
Explanation: The next greater elements to the right of 3(index 0) are 4,7,5,8,10,6. The next greater elements to the right of 8(index 5) is only 10.
Input: arr[] = [1, 2, 3, 4, 1], queries = 2, indices[] = [0, 3]
Output:  [3, 0]
Explanation: The count of numbers to the right of index 0 which are greater than arr[0] is 3 i.e. (2, 3, 4). Similarly, the count of numbers to the right of index 3 which are greater than arr[3] is 0, since there are no greater elements than 4 to the right of the array.
Constraints:
1 <= N <= 104
1 <= arr[i] <= 105
1 <= queries <= 100
0 <= indices[i] <= N - 1

// User function Template for Java

class Solution {
    public static int[] count_NGEs(int N, int arr[], int queries, int indices[]) {
        
    }
}

402. Remove K Digits
Solved
Medium
Topics
conpanies icon
Companies
Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

 

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 

Constraints:

1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.


class Solution {
    public String removeKdigits(String num, int k) {
        if(k==num.length())return "0";
        Stack<Character> stack = new Stack<>();

        for(char ch : num.toCharArray()){

            if(k==0){
            stack.push(ch);
            continue;
            }
            if(stack.isEmpty())stack.push(ch);
            else{
                if(ch-'a' < stack.peek()-'a'){
                    k--;
                    stack.pop();
                    stack.push(ch);
                }
            }
        }

        StringBuilder result = new StringBuilder();

        while(!stack.isEmpty()){
            result.append(stack.pop());
        }

        while (result.length() > 0 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.length() == 0 ? "0" : result.toString();
    }
}



84. Largest Rectangle in Histogram
Solved
Hard
Topics
conpanies icon
Companies
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

 

Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4
 

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104


class Solution {

    public int[] findPrevSmaller(int[] heights){
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for(int i = 0; i < heights.length; i++){
            while(!stack.isEmpty() && heights[stack.peek()]>heights[i]){
                int element = heights[stack.pop()];
                int nse = i;
                int pse = stack.isEmpty() ? -1 : stack.peek();

                maxArea = Math.max(maxArea,(nse-pse-1)*element);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
                int nse = heights.length;
                int element = heights[stack.pop()];
                int pse = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea,(nse-pse-1)*element);

        }
        return maxArea;
    }
}

239. Sliding Window Maximum
Solved
Hard
Topics
conpanies icon
Companies
Hint
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
    }
}


901. Online Stock Span
Solved
Medium
Topics
conpanies icon
Companies
Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.

The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.

For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
Implement the StockSpanner class:

StockSpanner() Initializes the object of the class.
int next(int price) Returns the span of the stock's price given that today's price is price.
 

Example 1:

Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]

Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6
 

Constraints:

1 <= price <= 105
At most 104 calls will be made to next.

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;

        for(int i = 1; i < n; i++){
            if(knows(candidate,i))candidate = i;
        }

        for(int i = 0; i < n; i++){
            if(i!=candidate && (knows(candidate,i) || !knows(i,candidate)))return -1;
        }
        return candidate;
    }
}

904. Fruit Into Baskets
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
        int maxFruits = 0;
        int first = -1, second = -1;
        int count1 = 0, count2 = 0;
        while (j < fruits.length) {

            if (first == -1) {
                first = fruits[j];
                count1 = 1;
            } else if (second == -1 && fruits[j] != first) {
                second = fruits[j];
                count2 = 1;
            } else if (fruits[j] == first)
                count1++;
            else if (fruits[j] == second)
                count2++;
            else {
                maxFruits = Math.max(maxFruits, count1 + count2);
                while (count1 > 0 && count2 > 0) {
                    if (fruits[i] == first)
                        count1--;
                    else
                        count2--;
                    i++;
                }
                if (count1 == 0) {
                    first = fruits[j];
                    count1 = 1;
                } else {
                    second = fruits[j];
                    count2 = 1;
                }
            }
            j++;
        }
        return Math.max(count1 + count2, maxFruits);
    }
}


424. Longest Repeating Character Replacement
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
        
    }
}

930. Binary Subarrays With Sum
Medium
Topics
conpanies icon
Companies
Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.

 

Example 1:

Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Example 2:

Input: nums = [0,0,0,0,0], goal = 0
Output: 15
 

Constraints:

1 <= nums.length <= 3 * 104
nums[i] is either 0 or 1.
0 <= goal <= nums.length

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        
    }
}