class Solution {
    public TreeNode str2tree(String s) {
        if(s==null || s.length()==0)return null;

        return helper(s,0);
    }

    public TreeNode helper(String s, int index[0]){

        int sign = 1;

        while(index[0]<s.length() && s.charAt(index[0])=='-'){
            sign=-1;
            index[0]++;
        }

        int rootNum = 0;
        while(index[0]<s.length() && Character.isDigit(s.charAt(index[0]))){
            rootNum = (rootNum*10) + s.charAt(index[0])-'0';
            index[0]++;
        }

        TreeNode root = new TreeNode(rootNum);


        if(index[0]<s.length() && s.charAt(index[0])=='('){
            index[0]++;
            root.left = helper(s,index[0]);
            index[0]++;
        }

        if(index[0]<s.length() && s.charAt(index[0])=='('){
            index[0]++;
            root.right = helper(s,index[0]);
            index[0]++;
        }

        return root;
    }
}


1229. Meeting Scheduler
Medium
Topics
Companies
Hint
Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

 

Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
 

Constraints:

1 <= slots1.length, slots2.length <= 104
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 109
1 <= duration <= 106

class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
    List<Integer> res = new ArrayList<>();
            Arrays.sort(slots1, (s1,s2)->Integer.compare(s1[0],s2[0]));
            Arrays.sort(slots2, (s1,s2)->Integer.compare(s1[0],s2[0]));


            int i = 0, j = 0;

            int myduration = 0;
            while(i<slots1.length && j<slots2.length){

                myduration = Math.min(slots1[i][1],slots2[j][1])-Math.max(slots1[i][0],slots2[j][0]);
                if(myduration<0 || myduration<duration){
                    if(slots1[i][1]<slots2[j][1]){
                        i++;
                    }
                    else j++;
                }
                else if(myduration>=duration){
                    res.add(Math.max(slots1[i][0],slots2[j][0]));
                    res.add(Math.max(slots1[i][0],slots2[j][0])+duration);
                    return res;
                }
            }

            return res;
    }
}



2563. Count the Number of Fair Pairs
Medium
Topics
Companies
Hint
Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.

A pair (i, j) is fair if:

0 <= i < j < n, and
lower <= nums[i] + nums[j] <= upper
 

Example 1:

Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
Example 2:

Input: nums = [1,7,9,2,5], lower = 11, upper = 11
Output: 1
Explanation: There is a single fair pair: (2,3).


class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        long res = 0;
        long sum = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                sum = nums[i]+nums[j];
                if(sum>=lower && sum<=upper)res++;
            }
        }
        return res;
    }                                                                                                                                                                                                                                                                                                                                   
}


394. Decode String
Medium
Topics
Companies
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.

 

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"


class Solution {
    int i = 0;
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for(; i < s.length(); i++){
            int num = 0;
            while(Character.isDigit(s.charAt(i))){
                num = s.charAt(i)-'0' + num*10;
                i++;
            }

            i++;
            String toAppend = "";
            while(s.charAt(i)!=']'){

                if(Character.isDigit(s.charAt(i))){
                    toAppend = decodeString(s.substring(i,s.length()));
                }
                else sb.append(s.charAt(i));
                i++;
            }
            String finalString = sb.append(toAppend);

            while(num>0){
                res.append(finalString);
                num--;
            }

            return res.toString();


        }
        
    }
}


510. Inorder Successor in BST II
Medium
Topics
Companies
Given a node in a binary search tree, return the in-order successor of that node in the BST. If that node has no in-order successor, return null.

The successor of a node is the node with the smallest key greater than node.val.

You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node. Below is the definition for Node:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
 

Example 1:


Input: tree = [2,1,3], node = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both the node and the return value is of Node type.
Example 2:


Input: tree = [5,3,6,2,4,null,null,1], node = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
All Nodes will have unique values.
 

Follow up: Could you solve it without looking up any of the node's values?

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node inorderSuccessor(Node node) {
     Node successor = new Node(-1);
     if(node.right!=null){
        node = node.right;
        while(node!=null){
         successor = node;
         node = node.left;
        }
        return successor;
     }
     
     Node prevNode = node;

     while(node.parent!=null){
        node = node.parent;
        if(node.left==prevNode)return node;
        else prevNode = node;
     }

     return null;
    }
}



1644. Lowest Common Ancestor of a Binary Tree II
Medium
Topics
Companies
Hint
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.
 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:



Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
Example 3:



Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
Output: null
Explanation: Node 10 does not exist in the tree, so return null.
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
 

Follow up: Can you find the LCA traversing the tree, without checking nodes existence?

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val == p.vall || root.val==q.val)return root;

        TreeNode leftLook = lowestCommonAncestor(root.left,p,q);
        TreeNode rightLook = lowestCommonAncestor(root.right,p,q);

        if(leftLook!=null && rightLook!=null)return root;

        return leftLook!=null? leftLook : rightLook;
    }
}


3396. Minimum Number of Operations to Make Elements in Array Distinct
Easy
Topics
Companies
Hint
You are given an integer array nums. You need to ensure that the elements in the array are distinct. To achieve this, you can perform the following operation any number of times:

Remove 3 elements from the beginning of the array. If the array has fewer than 3 elements, remove all remaining elements.
Note that an empty array is considered to have distinct elements. Return the minimum number of operations needed to make the elements in the array distinct.

 

Example 1:

Input: nums = [1,2,3,4,2,3,3,5,7]

Output: 2

Explanation:

In the first operation, the first 3 elements are removed, resulting in the array [4, 2, 3, 3, 5, 7].
In the second operation, the next 3 elements are removed, resulting in the array [3, 5, 7], which has distinct elements.
Therefore, the answer is 2.

Example 2:

Input: nums = [4,5,6,4,4]

Output: 2

Explanation:

In the first operation, the first 3 elements are removed, resulting in the array [4, 4].
In the second operation, all remaining elements are removed, resulting in an empty array.
Therefore, the answer is 2.

Example 3:

Input: nums = [6,7,8,9]

Output: 0

Explanation:

The array already contains distinct elements. Therefore, the answer is 0.


class Solution {
    public int minimumOperations(int[] nums) {

        int[] buffer = new int[101];
        int index = 0;
        for(int i = nums.length-1; i>=0; i--){
            buffer[nums[i]]++;
            if(buffer[nums[i]]>1){
            index = i+1;
            break;
            }
        }

        return (int)Math.ceil(index/3.0);



    }
}


2104. Sum of Subarray Ranges
Medium
Topics
Companies
Hint
You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.

Return the sum of all subarray ranges of nums.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,2,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0 
[2], range = 2 - 2 = 0
[3], range = 3 - 3 = 0
[1,2], range = 2 - 1 = 1
[2,3], range = 3 - 2 = 1
[1,2,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
Example 2:

Input: nums = [1,3,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[3], range = 3 - 3 = 0
[3], range = 3 - 3 = 0
[1,3], range = 3 - 1 = 2
[3,3], range = 3 - 3 = 0
[1,3,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
Example 3:

Input: nums = [4,-2,-3,4,1]
Output: 59
Explanation: The sum of all subarray ranges of nums is 59.
 
class Solution {
    public long subArrayRanges(int[] nums) {
        long sum = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int min = nums[i], max = nums[i];
            for(int j = i+1; j < n; j++){
                min = min(min,nums[j]);
                max = max(max,nums[j]);
                sum+=(max-min);
            }
        }
        return sum;
    }
}

2864. Maximum Odd Binary Number
Easy
Topics
Companies
Hint
You are given a binary string s that contains at least one '1'.

You have to rearrange the bits in such a way that the resulting binary number is the maximum odd binary number that can be created from this combination.

Return a string representing the maximum odd binary number that can be created from the given combination.

Note that the resulting string can have leading zeros.

 

Example 1:

Input: s = "010"
Output: "001"
Explanation: Because there is just one '1', it must be in the last position. So the answer is "001".
Example 2:

Input: s = "0101"
Output: "1001"
Explanation: One of the '1's must be in the last position. The maximum number that can be made with the remaining digits is "100". So the answer is "1001".

Constraints:

1 <= s.length <= 100
s consists only of '0' and '1'.
s contains at least one '1'.

class Solution {
    public String maximumOddBinaryNumber(String s) {
        int count = 0;
        for(char ch : s.toCharArray()){
            if(ch=='1')count++;
        }

        StringBuilder sb = new StringBuilder();

        int i = 0;
        while(i<s.length()){
            sb.append('0');
            i++''
        }
        i = 0;
        while(count>1){
            sb.setCharAt(i,'1');
            count--;
            i++;
        }

        sb.setCharAt(sb.length()-1,'1');
        return sb.toString;

    }
}


3527. Find the Most Common Response
Medium
Topics
Companies
Hint
You are given a 2D string array responses where each responses[i] is an array of strings representing survey responses from the ith day.

Return the most common response across all days after removing duplicate responses within each responses[i]. If there is a tie, return the lexicographically smallest response.

 

Example 1:

Input: responses = [["good","ok","good","ok"],["ok","bad","good","ok","ok"],["good"],["bad"]]

Output: "good"

Explanation:

After removing duplicates within each list, responses = [["good", "ok"], ["ok", "bad", "good"], ["good"], ["bad"]].
"good" appears 3 times, "ok" appears 2 times, and "bad" appears 2 times.
Return "good" because it has the highest frequency.
Example 2:

Input: responses = [["good","ok","good"],["ok","bad"],["bad","notsure"],["great","good"]]

Output: "bad"

Explanation:

After removing duplicates within each list we have responses = [["good", "ok"], ["ok", "bad"], ["bad", "notsure"], ["great", "good"]].
"bad", "good", and "ok" each occur 2 times.
The output is "bad" because it is the lexicographically smallest amongst the words with the highest frequency.
 

Constraints:

1 <= responses.length <= 1000
1 <= responses[i].length <= 1000
1 <= responses[i][j].length <= 10
responses[i][j] consists of only lowercase English letters

good
ok

ok


class Solution {
    public String findCommonResponse(List<List<String>> responses) {
        Map<String,Integer> map = new HashMap<>();

        for(List<String> day : responses){
            Set<String> set = new HashSet<>();
            for(String response : day){
                set.add(response);
            }

            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
            map.put(iterator.next(),map.getOrDefault(iterator.next(),0)+1);
            }

        }
        String common = "";
        int maxVal = Integer.MIN_VALUE;
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getValue()>maxVal){
                maxVal = entry.getValue();
                common = entry.getKey();
            }
        }
        return common;
    }
}


1152. Analyze User Website Visit Pattern
Medium
Topics
Companies
Hint
You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].

A pattern is a list of three websites (not necessarily distinct).

For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.

For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.
Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.

Note that the websites in a pattern do not need to be visited contiguously, they only need to be visited in the order they appeared in the pattern.

 

Example 1:

Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation: The tuples in this example are:
["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],["james","cart",5],["james","maps",6],["james","home",7],["mary","home",8],["mary","about",9], and ["mary","career",10].
The pattern ("home", "about", "career") has score 2 (joe and mary).
The pattern ("home", "cart", "maps") has score 1 (james).
The pattern ("home", "cart", "home") has score 1 (james).
The pattern ("home", "maps", "home") has score 1 (james).
The pattern ("cart", "maps", "home") has score 1 (james).
The pattern ("home", "home", "home") has score 0 (no user visited home 3 times).
Example 2:

Input: username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
Output: ["a","b","a"]

class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        
        

    }
}


2170. Minimum Operations to Make the Array Alternating
Medium
Topics
Companies
Hint
You are given a 0-indexed array nums consisting of n positive integers.

The array nums is called alternating if:

nums[i - 2] == nums[i], where 2 <= i <= n - 1.
nums[i - 1] != nums[i], where 1 <= i <= n - 1.
In one operation, you can choose an index i and change nums[i] into any positive integer.

Return the minimum number of operations required to make the array alternating.

 

Example 1:

Input: nums = [3,1,3,2,4,3]
Output: 3
Explanation:
One way to make the array alternating is by converting it to [3,1,3,1,3,1].
The number of operations required in this case is 3.
It can be proven that it is not possible to make the array alternating in less than 3 operations. 
Example 2:

Input: nums = [1,2,2,2,2]
Output: 2
Explanation:
One way to make the array alternating is by converting it to [1,2,1,2,1].
The number of operations required in this case is 2.
Note that the array cannot be converted to [2,2,2,2,2] because in this case nums[0] == nums[1] which violates the conditions of an alternating array.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105


class Solution {
    public int minimumOperations(int[] nums) {
        int count = 0;

        if(nums.length==1)return 0;
        if(nums.length==2){
            if(nums[0]==nums[1])return 1;
            else return 0;
        }
        for(int i = 1; i < nums.length; i++){
            if(i==1){
                if(nums[i]==nums[i-1]){
                    nums[i]=nums[i-1]+1;
                    if(nums[i+1]==nums[i]){
                    nums[i]=nums[i+1]+1;
                    }
                    count++;
                }
            }

            if(nums[i]!=nums[i-2]){
                nums[i]=nums[i-2];
                count++;
                if(nums[i]==nums[i-1]){
                    nums[i] = nums[i-1]+1;
                    count+=2;
                }
            }
        }
        return count;
    }
}


277. Find the Celebrity
Medium
Topics
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

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */



1209. Remove All Adjacent Duplicates in String II
Medium
Topics
Companies
Hint
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

 

Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"


Constraints:

1 <= s.length <= 105
2 <= k <= 104
s only contains lowercase English letters.

class Solution {
    public String removeDuplicates(String s, int k) {
        int count = 1;
        for(int i = 0; i < s.length()-1; i++){
            if(s.charAt(i)==s.charAt(i+1)){
                count++;
                if(count==k){
                  String x = s.substring(0,(i+1)-(k-1))+ s.substring(i+2);
                  s=x;
                  count = 1;
                  i=0;
                }
            }
            else{
                count=1;
            }
        }
        return s;

    }
}


2672. Number of Adjacent Elements With the Same Color
Medium
Topics
Companies
Hint
You are given an integer n representing an array colors of length n where all elements are set to 0's meaning uncolored. You are also given a 2D integer array queries where queries[i] = [indexi, colori]. For the ith query:

Set colors[indexi] to colori.
Count adjacent pairs in colors set to the same color (regardless of colori).
Return an array answer of the same length as queries where answer[i] is the answer to the ith query.

 

Example 1:

Input: n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]

Output: [0,1,1,0,2]

Explanation:

Initially array colors = [0,0,0,0], where 0 denotes uncolored elements of the array.
After the 1st query colors = [2,0,0,0]. The count of adjacent pairs with the same color is 0.
After the 2nd query colors = [2,2,0,0]. The count of adjacent pairs with the same color is 1.
After the 3rd query colors = [2,2,0,1]. The count of adjacent pairs with the same color is 1.
After the 4th query colors = [2,1,0,1]. The count of adjacent pairs with the same color is 0.
After the 5th query colors = [2,1,1,1]. The count of adjacent pairs with the same color is 2.
Example 2:

Input: n = 1, queries = [[0,100000]]

Output: [0]

Explanation:

After the 1st query colors = [100000]. The count of adjacent pairs with the same color is 0.

 

Constraints:

1 <= n <= 105
1 <= queries.length <= 105
queries[i].length == 2
0 <= indexi <= n - 1
1 <=  colori <= 105


2672. Number of Adjacent Elements With the Same Color
Medium
Topics
Companies
Hint
You are given an integer n representing an array colors of length n where all elements are set to 0's meaning uncolored. You are also given a 2D integer array queries where queries[i] = [indexi, colori]. For the ith query:

Set colors[indexi] to colori.
Count adjacent pairs in colors set to the same color (regardless of colori).
Return an array answer of the same length as queries where answer[i] is the answer to the ith query.

 

Example 1:

Input: n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]

Output: [0,1,1,0,2]

Explanation:

Initially array colors = [0,0,0,0], where 0 denotes uncolored elements of the array.
After the 1st query colors = [2,0,0,0]. The count of adjacent pairs with the same color is 0.
After the 2nd query colors = [2,2,0,0]. The count of adjacent pairs with the same color is 1.
After the 3rd query colors = [2,2,0,1]. The count of adjacent pairs with the same color is 1.
After the 4th query colors = [2,1,0,1]. The count of adjacent pairs with the same color is 0.
After the 5th query colors = [2,1,1,1]. The count of adjacent pairs with the same color is 2.
Example 2:

Input: n = 1, queries = [[0,100000]]

Output: [0]

Explanation:

After the 1st query colors = [100000]. The count of adjacent pairs with the same color is 0.

 

Constraints:

1 <= n <= 105
1 <= queries.length <= 105
queries[i].length == 2
0 <= indexi <= n - 1
1 <=  colori <= 105


3531. Count Covered Buildings
Medium
Topics
Companies
Hint
You are given a positive integer n, representing an n x n city. You are also given a 2D grid buildings, where buildings[i] = [x, y] denotes a unique building located at coordinates [x, y].

A building is covered if there is at least one building in all four directions: left, right, above, and below.

Return the number of covered buildings.

 

Example 1:



Input: n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]

Output: 1

Explanation:

Only building [2,2] is covered as it has at least one building:
above ([1,2])
below ([3,2])
left ([2,1])
right ([2,3])
Thus, the count of covered buildings is 1.
Example 2:



Input: n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]

Output: 0

Explanation:

No building has at least one building in all four directions.
Example 3:



Input: n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]

Output: 1

Explanation:

Only building [3,3] is covered as it has at least one building:
above ([1,3])
below ([5,3])
left ([3,2])
right ([3,5])
Thus, the count of covered buildings is 1.
 



class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        
        int res = 0;

        Map<Integer,List<Integer>> xmap = new HashMap<>();
        Map<Integer,List<Integer>> ymap = new HashMap<>();

        for(int[] building : buildings){
            int thisx = building[0];
            int thisy = building[1];

            List<Integer> xlist = xmap.get(thisx);
            List<Integer> ylist = ymap.get(thisy);

            if(xlist==null){
                xlist = new ArrayList<>();
            }

            if(ylist==null){
                ylist = new ArrayList<>();
            }

            xlist.add(thisy);
            ylist.add(thisx);
            
        }

        for(List<Integer> list : xmap.values()){
            Collections.sort(list);
        }

        for(List<Integer> list : ymap.values()){
            Collections.sort(list);
        }

        for(int[] building : buildings){

            int thisx = building[0];
            int thisy = building[1];

            List<Integer> xlist = map.get(thisx);
            List<Integer> ylist = map.get(thisy);

            if((xlist.size() > 0 && xlist.get(0)<thisy) && (xlist.size() > 0 && xlist.get(xlist.size()-1)>thisy) && 
                (ylist.size() > 0 && ylist.get(0)<thisx) && (ylist.size() > 0 && ylist.get(ylist.size()-1)>thisx)){
                    res++;
                }

        }

        return res;
    }
    }


xmap:

1-> {2}
2-> {1,2,3}
3-> {2}

ymap:

1-> {2}
2-> {1,2,3}
3-> {2}


1007. Minimum Domino Rotations For Equal Row
Medium
Topics
Companies
In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.

Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.

If it cannot be done, return -1.

 

Example 1:


Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
Output: 2
Explanation: 
The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
Example 2:

Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
Output: -1
Explanation: 
In this case, it is not possible to rotate the dominoes to make one row of values equal.
 

Constraints:

2 <= tops.length <= 2 * 104
bottoms.length == tops.length
1 <= tops[i], bottoms[i] <= 6


class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        
    }
}



Code
Testcase
Test Result
Test Result
1283. Find the Smallest Divisor Given a Threshold
Medium
Topics
Companies
Hint
Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.

Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

The test cases are generated so that there will be an answer.

 

Example 1:

Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 
Example 2:

Input: nums = [44,22,33,11,1], threshold = 5
Output: 44
 

Constraints:

1 <= nums.length <= 5 * 104
1 <= nums[i] <= 106
nums.length <= threshold <= 106

class Solution {

    public boolean thresholdTest(int mid, int[] nums, int threshold){
        int sum = 0;

        for(int i : nums){

            double div = (double)i/(double)mid;

            sum+=(int)Math.ceil(div);
            if(sum>threshold)return false;
        }
        return true;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;

        int high = Integer.MIN_VALUE;

        for(int i : nums){
            high = Math.max(high,i);
        }

        int smallest = -1;
        while(low<=high){
            int mid = (low+high)/2;

            boolean test = thresholdTest(mid, nums, threshold);
            if(test){
                smallest = mid;
                j = mid-1;
            }
            else i = mid+1;
        }
        return smallest;
    }
}


1011. Capacity To Ship Packages Within D Days
Solved
Medium
Topics
Companies
Hint
A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.



Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
Output: 15
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

    public boolean findOut(int capacity, int[] weights, int days){
        int mydays = 0;
        int cap = capacity;
        for(int i : weights){
            if(cap>=i){
                cap-=i;
            }
            else{
                mydays++;
                cap = capacity;
                cap-=i;
                if(mydays>days)return false;
            }
        }
        mydays++;
        return mydays<=days;
    }

    public int shipWithinDays(int[] weights, int days) {
        int low = Integer.MIN_VALUE, high = 0;

        for(int i : weights){
            low = Math.max(i,low);
            high += i;
        }

        int res = -1;
        while(low<=high){

            int mid = (low+high)/2;
            boolean canShip = findOut(mid,weights,days);

            if(canShip){
                res=mid;
                high = mid-1;
            }
            else low = mid+1;

        }
    }
}


1539. Kth Missing Positive Number
Solved
Easy
Topics
Companies
Hint
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Return the kth positive integer that is missing from this array.

 

Example 1:

Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
Example 2:

Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 

Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length

class Solution {


    public int findKthPositive(int[] arr, int k) {

        int[] missingCounter = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            missingCounter[i] = arr[i]-i-1;
        }
        
        int low = 0, high = missingCounter.length-1;

        while(low<=high){
            int mid = (low+high)/2;

            int missing = arr[mid]-mid-1;
            if(missing<k)low = mid+1;
            else high=mid-1;
        }

        return high+k+1;
                                   

                                                                                                                                                             
    }
}


Aggressive Cows
Difficulty: MediumAccuracy: 59.57%Submissions: 130K+Points: 4Average Time: 30m
You are given an array with unique elements of stalls[], which denote the position of a stall. You are also given an integer k which denotes the number of aggressive cows. Your task is to assign stalls to k cows such that the minimum distance between any two of them is the maximum possible.

Examples :

Input: stalls[] = [1, 2, 4, 8, 9], k = 3
Output: 3
Explanation: The first cow can be placed at stalls[0], 
the second cow can be placed at stalls[2] and 
the third cow can be placed at stalls[3]. 
The minimum distance between cows, in this case, is 3, which also is the largest among all possible ways.
Input: stalls[] = [10, 1, 2, 7, 5], k = 3
Output: 4
Explanation: The first cow can be placed at stalls[0],
the second cow can be placed at stalls[1] and
the third cow can be placed at stalls[4].
The minimum distance between cows, in this case, is 4, which also is the largest among all possible ways.
Input: stalls[] = [2, 12, 11, 3, 26, 7], k = 5
Output: 1
Explanation: Each cow can be placed in any of the stalls, as the no. of stalls are exactly equal to the number of cows.
The minimum distance between cows, in this case, is 1, which also is the largest among all possible ways.
Constraints:
2 <= stalls.size() <= 106
0 <= stalls[i] <= 108
2 <= k <= stalls.size()




class Solution {
    public static int aggressiveCows(int[] stalls, int k) {
        
        int low = 1;

        int high = Integer.MIN_VALUE;

        for(int i : stalls)high = Math.max(high,i);
        int len = high;
        int res = -1;
        while(low<=high){

            int mid = (low+high)/2;

            int numCows = Math.ceil((double)len/(double)mid);
            if(numCows>=k){
                res = mid;
                low = mid+1;
            }
            else high = mid-1;
        }

        return res;


        
    }
}


774. Minimize Max Distance to Gas Station
Solved
Hard
Topics
Companies
Hint
You are given an integer array stations that represents the positions of the gas stations on the x-axis. You are also given an integer k.

You should add k new gas stations. You can add the stations anywhere on the x-axis, and not necessarily on an integer position.

Let penalty() be the maximum distance between adjacent gas stations after adding the k new stations.

Return the smallest possible value of penalty(). Answers within 10-6 of the actual answer will be accepted.

 

Example 1:

Input: stations = [1,2,3,4,5,6,7,8,9,10], k = 9
Output: 0.50000
Example 2:

Input: stations = [23,24,36,39,46,56,57,65,84,98], k = 1
Output: 14.00000
 

Constraints:

10 <= stations.length <= 2000
0 <= stations[i] <= 108
stations is sorted in a strictly increasing order.
1 <= k <= 106

import java.util.PriorityQueue;

class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        int[] howMany = new int[stations.length - 1]; // Track extra stations added

        PriorityQueue<double[]> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        // Initialize maxHeap with distances between stations
        for (int i = 0; i < stations.length - 1; i++) {
            maxHeap.add(new double[]{(double) (stations[i + 1] - stations[i]), i});
        }

        for (int gasStations = 1; gasStations <= k; gasStations++) {
            double[] popped = maxHeap.poll(); // Get largest gap
            double max = popped[0];
            int maxIndex = (int)popped[1];

            howMany[maxIndex]++;
            double iniDiff = (double)stations[maxIndex+1]-(double)stations[maxIndex];
            double newDiff = max / (howMany[maxIndex] + 1);

            maxHeap.add(new double[]{newDiff, maxIndex});
        }

        return maxHeap.poll()[0]; // Return minimized max distance
    }
}

class Solution {

    public int countStations(int[] stations, int mid, int k){
        int count = 0;

        for(int i = 1; i < stations.length; i++){
            int numInBetween = (stations[i]-stations[i-1])/mid;
            if((stations[i]-stations[i-1])/mid == numInBetween * mid)numInBetween--;
            count+=(numInBetween);
        }
        return count; 
    }

    public double minmaxGasDist(int[] stations, int k) {
        double low = 0, high = Double.MIN_VALUE;

        for (int i = 0; i < stations.length - 1; i++) {
            high = Math.max(high, (double)stations[i+1]-(double)stations[i]);    
        }
        double ans = 0.0;
        double diff = 1e-6;
        while(high-low > diff){
            double mid = (low+high)/2;

            int count = countStations(stations,mid,k);
            if(count>k){
            low = mid;
            }
            else{
                ans = mid;
                high=mid;
            }

        }
    }
}


4. Median of Two Sorted Arrays
Solved
Hard
Topics
Companies
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106


class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0, j = 0, r = 0;
        int len = nums1.length+nums2.length;
        int first = 0, second = 0;
        while(i<nums1.length && j<nums2.length){
            if(nums1[i]<=nums2[j]){

            if(r==len/2)second=nums1[i];
            else if(r==(len/2)-1)first=nums1[i];

            i++;
            }
            else {

            if(r==len/2)second=nums2[j];
            else if(r==(len/2)-1)first=nums2[j];

            j++;
            }
            r++;
        }

        while(i<nums1.length){
        if(r==len/2)second=nums1[i];
        else if(r==(len/2)-1)first=nums1[i];
        r++;
        i++;
        }
        while(j<nums2.length){
        if(r==len/2)second=nums2[j];
        else if(r==(len/2)-1)first=nums2[j];
        r++;
        j++;
        }
        double median = 0.0;
        if(res.length%2==0){
            double sum = (double)first+second;
            median = (double)sum/(double)2;
        }
        else {
            median = (double)second;
        }
        return median;
    }
}


240. Search a 2D Matrix II
Medium
Topics
Companies
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int col = n-1, row = 0;

        while(col<=0 && row<m){

            if(matrix[row][col]==target)return true;

            if(matrix[row][col]<target)row++;
            else col--;
        }
        return -1;
    }
}

class Solution {
    public int findPeakElement(int[] nums) {
     int peak = 0;
     if(nums.length==1)
     {
         return 0;
     }
     if(nums[0]>nums[1])
     {
     return 0;
     }
     if(nums[nums.length-1]>nums[nums.length-2])
     {
     return nums.length-1;
     }

     int low = 1, high = nums.length-2;
     
     while(low<=high){

        int mid = (low+high)/2;
        if(nums[mid]>nums[mid-1] && nums[mid]>nums[mid+1])return mid;
        else if(nums[mid]<nums[mid+1])low = mid+1;
        else if(nums[mid]<nums[mid-1])high = mid-1;
        else low=mid+1;
     }

        return -1;
    }
}


1901. Find a Peak Element II
Medium
Topics
Companies
Hint
A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.

Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].

You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.

You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.

 

Example 1:



Input: mat = [[1,4],[3,2]]
Output: [0,1]
Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
Example 2:



Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
Output: [1,1]
Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 500
1 <= mat[i][j] <= 105
No two adjacent cells are equal.

class Solution {

    public int getMaxRow(int col, int[][] mat){
        int i = 0, j = 0;
        int maxRow = -1, max = Integer.MIN_VALUE;
        for(int row = 0; row < mat.length; row++){
            if(nums[row][col]>max){
                max = nums[row][col];
                maxRow = row;
            }
        }

        return maxRow;
    }

    public int[] findPeakGrid(int[][] mat) {
        int low = 0, high = matrix[0].length-1;

        while(low<=high){

            int mid = (low+high)/2;

            int row = getMaxRow(col,mat);

            if(nums[row][mid] > nums[row][mid-1] && nums[row][mid] > nums[row][mid+1])return new int[]{row,mid};
            if(nums[row][mid]<nums[row][mid-1]){
             high = mid-1;
            }
            else low = mid+1;
        }

        return new int[]{-1,-1};
    }
}


1021. Remove Outermost Parentheses
Solved
Easy
Topics
Companies
Hint
A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.

For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split it into s = A + B, with A and B nonempty valid parentheses strings.

Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk, where Pi are primitive valid parentheses strings.

Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.

 

Example 1:

Input: s = "(()())(())"
Output: "()()()"
Explanation: 
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
Example 2:

Input: s = "(()())(())(()(()))"
Output: "()()()()(())"
Explanation: 
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
Example 3:

Input: s = "()()"
Output: ""
Explanation: 
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".
 

Constraints:

1 <= s.length <= 105
s[i] is either '(' or ')'.
s is a valid parentheses string.

class Solution {

    

    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        for(int i = 0; i < s.length(); i++){
            if(sb.charAt(i))
        }
    }
}


151. Reverse Words in a String
Solved
Medium
Topics
Companies
Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

 

Example 1:

Input: s = "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 

Constraints:

1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.
 

Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?

class Solution {
    public String reverseWords(String s) {
        s = s.trim();

        StringBuilder sb = new StringBuilder();
        int i = s.length()-1, j = s.length()-1;
        while(j<=0){

            while(j>=0 && s.charAt(j)!=' '){
                j--;
                if(j==-1)break;
            }
            sb.append(s.substring(j+1,i+1));
            sb.append(' ');
            while(j>=0 && s.charAt(j)==' ')j--;

            i = j;
        }

        sb.append(s.substring(j+1,i+1));
        return sb.toString();
    }
}


1903. Largest Odd Number in String
Solved
Easy
Topics
Companies
Hint
You are given a string num, representing a large integer. Return the largest-valued odd integer (as a string) that is a non-empty substring of num, or an empty string "" if no odd integer exists.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: num = "52"
Output: "5"
Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.
Example 2:

Input: num = "4206"
Output: ""
Explanation: There are no odd numbers in "4206".
Example 3:

Input: num = "35427"
Output: "35427"
Explanation: "35427" is already an odd number.
 

Constraints:

1 <= num.length <= 105
num only consists of digits and does not contain any leading zeros.


class Solution {
    public String largestOddNumber(String num) {
     
        while(i>=0){
            if('13579'.indexOf(chArr[i])!=-1) return s.substring(0,i+1);
            i--;  
        }

        return "";
    }
}

205. Isomorphic Strings
Solved
Easy
Topics
Companies
Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

 

Example 1:

Input: s = "egg", t = "add"

Output: true

Explanation:

The strings s and t can be made identical by:

Mapping 'e' to 'a'.
Mapping 'g' to 'd'.
Example 2:

Input: s = "foo", t = "bar"

Output: false

Explanation:

The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.

Example 3:

Input: s = "paper", t = "title"

Output: true

 

Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.

class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] arr1 = new int[256];
        int[] arr2 = new int[256];

        Arrays.fill(arr1,-1);
        Arrays.fill(arr2,-1);

        for(int i = 0; i < s.length(); i++){
            char chs = s.charAt(i);
            char cht = t.charAt(i);

            if(arr1[chs-'a']==arr2[cht-'a']) 
        }

        return true;
    }
}


451. Sort Characters By Frequency
Solved
Medium
Topics
Companies
Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.

 

Example 1:

Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 

Constraints:

1 <= s.length <= 5 * 105
s consists of uppercase and lowercase English letters and digits.


class Solution {

    public Pair{

        Character character;
        int freq;

        public Pair(Character character, int freq){
            this.character=character;
            this.freq=freq;
        }
    }

    public String frequencySort(String s) {

        Map<Character,Integer> map = new HashMap<>();

        for(char ch : s.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        PriorityQueue<Pair> maxheap = new PriorityQueue<>((p1,p2)->Integer.compare(p2.freq,p1.freq));

        for(Map.Entry<Character,Integer> entry : map){
            pq.add(new Pair(entry.getKey(),entry.getValue()));
        }

        StringBuilder res = new StringBuilder();
        while(!pq.isEmpty()){
            Pair popped = pq.poll();

            res.append(popped.character);
        }

        return res.toString();

    }
}


K-th Bit is Set or Not
Difficulty: EasyAccuracy: 52.75%Submissions: 236K+Points: 2
Given a number n and a bit number k, check if the kth index bit of n is set or not. A bit is called set if it is 1. The position of set bit '1' should be indexed starting with 0 from the LSB side in the binary representation of the number.
Note: Index is starting from 0. You just need to return true or false.

Examples : 

Input: n = 4, k = 0
Output: false
Explanation: Binary representation of 4 is 100, in which 0th index bit from LSB is not set. So, return false.
Input: n = 4, k = 2
Output: true
Explanation: Binary representation of 4 is 100, in which 2nd index bit from LSB is set. So, return true.
Input: n = 500, k = 3
Output: false
Explanation: Binary representation of 500 is 111110100, in which 3rd index bit from LSB is not set. So, return false.
Constraints:
1 ≤ n ≤ 109
0 ≤ k ≤ 31


class CheckBit {
    static boolean checkKthBit(int n, int k) {
        // Your code here
        if(((n >> (k)) & 1) == 1)return true;
        return false;
    }
}


Swap two numbers
Difficulty: BasicAccuracy: 70.02%Submissions: 93K+Points: 1
You are given two numbers a and b. Your task is to swap the given two numbers.

Note: Try to do it without a temporary variable.

Examples:

Input: a = 13, b = 9
Output: 9 13
Explanation: After swapping it becomes 9 and 13.
Input: a = 15, b = 8
Output: 8 15
Explanation: after swapping it becomes 8 and 15.
Constraints:
1 ≤ a, b ≤ 106


class Solution {
    static List<Integer> get(int a, int b) {
        // code here
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        List<Integer> res = new ArrayList<>();
        res.add(a);
        res.add(b);
        return res;
    }
}

Set kth bit
Difficulty: BasicAccuracy: 72.71%Submissions: 49K+Points: 1
Given a number n and a value k. From the right, set the kth bit in the binary representation of n. The position of the Least Significant Bit(or last bit) is 0, the second last bit is 1 and so on. 

Examples:

Input: n = 10, k = 2
Output: 14
Explanation: Binary representation of the given number 10 is: 1 0 1 0, number of bits in the binary reprsentation is 4. Thus 2nd bit from right is 0. The number after changing this bit to 1 is: 14(1 1 1 0).
Input: n = 15, k = 3
Output: 15
Explanation: The binary representation of the given number 15 is: 1 1 1 1, number of bits in the binary representation is 4. Thus 3rd bit from the right is 1. The number after changing this bit to 1 is 15(1 1 1 1).
Constraints:
1 <= n <= 109
0 <= k < x, where x is the number of bits in the binary representation of n.


class Solution {
    static int setKthBit(int n, int k) {
        // code here
        
    }
}


Bit Manipulation
Difficulty: EasyAccuracy: 49.84%Submissions: 66K+Points: 2Average Time: 15m
Given a 32 bit unsigned integer num and an integer i. Perform following operations on the number - 

1. Get ith bit

2. Set ith bit

3. Clear ith bit

Note : For better understanding, we are starting bits from 1 instead 0. (1-based). You have to print space three space separated values ( as shown in output without a line break) and do not have to return anything.

Examples :

Input: 70 3
Output: 1 70 66
Explanation: Bit at the 3rd position from LSB is 1. (1 0 0 0 1 1 0) .The value of the given number after setting the 3rd bit is 70. The value of the given number after clearing 3rd bit is 66. (1 0 0 0 0 1 0)
Input: 8 1
Output: 0 9 8
Explanation: Bit at the first position from LSB is 0. (1 0 0 0)  .The value of the given number after setting the 1st bit is 9. (1 0 0 1).  The value of the given number after clearing 1st bit is 8. (1 0 0 0)
 

Constraints:

0<=num<=109

1<=i<=32

class Solution {
    static void bitManipulation(int num, int i) {

        int ithbit = num >> (i-1);
        int setted = 0, cleared = 0;
        if(ithbit==0)
        {
        setted = num ^ (1<<(i-1));
        cleared = num; 
        }
        else{
        setted = num;
        cleared = num ^ (1<<(i-1));
        }

        System.out.println(ithbit+" "+setted+" "+cleared);

    }
}


Set the rightmost unset bit
Difficulty: BasicAccuracy: 47.64%Submissions: 62K+Points: 1
Given a non-negative number n . The problem is to set the rightmost unset bit in the binary representation of n.

Examples :

Input: n = 6
Output: 7
Explanation: The binary representation of 6 is 110. After setting right most bit it becomes 111 which is 7.
Input: n = 15
Output: 31
Explanation: The binary representation of 15 is 01111. After setting right most bit it becomes 11111 which is 31.
Expected Time Complexity: O(Logn)
Expected Auxiliary Space: O(1)


Constraints:
1 <= n <= 109

// User function Template for Java
class Solution {
    static int setBit(int n) {
        // code here
        int i = 0;
        for(i; i <= 31; i++){
            if((n>>i & 1) == 0)break;
        }

        return n ^ (1<<i);
    }
}


231. Power of Two
Solved
Easy
Topics
conpanies icon
Companies
Given an integer n, return true if it is a power of two. Otherwise, return false.

An integer n is a power of two, if there exists an integer x such that n == 2x.

 

Example 1:

Input: n = 1
Output: true
Explanation: 20 = 1
Example 2:

Input: n = 16
Output: true
Explanation: 24 = 16
Example 3:

Input: n = 3
Output: false
 

Constraints:

-231 <= n <= 231 - 1
 

Follow up: Could you solve it without loops/recursion?

class Solution {
    public boolean isPowerOfTwo(int n) {

        int count = 0;

        while(n>0){
            n = n & (n-1);
            count++;
            if(count>1)return false;
        }
        return true;
    }
}


29. Divide Two Integers
Solved
Medium
Topics
conpanies icon
Companies
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

Return the quotient after dividing dividend by divisor.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.

 

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 

Constraints:

-231 <= dividend, divisor <= 231 - 1
divisor != 0


class Solution {
    public int divide(int dividend, int divisor) {

        if(dividend == Integer.MIN_VALUE && divisor==-1)return Integer.MAX_VALUE;
        int res = 0;
        boolean isNegative = (dividend < 0) ^ (divisor < 0);


        if((dividend < 0 && divisor > 0) || (dividend>=0 && divisor<0))isNegative = true;

        long mydividend = Math.abs((long) dividend);
        long mydivisor = Math.abs((long) divisor);
        while(mydividend >= divisor){
            int i = 0;
            int val = mydivisor;
            while(mydividend>=(val << 1){
                val <= 1;
                i++;
            }
            res+=(1<<i);
            mydividend-=val;
        }

        return isNegative? -res : res;
        
    }
}

2220. Minimum Bit Flips to Convert Number
Easy
Topics
conpanies icon
Companies
Hint
A bit flip of a number x is choosing a bit in the binary representation of x and flipping it from either 0 to 1 or 1 to 0.

For example, for x = 7, the binary representation is 111 and we may choose any bit (including any leading zeros not shown) and flip it. We can flip the first bit from the right to get 110, flip the second bit from the right to get 101, flip the fifth bit from the right (a leading zero) to get 10111, etc.
Given two integers start and goal, return the minimum number of bit flips to convert start to goal.

 

Example 1:

Input: start = 10, goal = 7
Output: 3
Explanation: The binary representation of 10 and 7 are 1010 and 0111 respectively. We can convert 10 to 7 in 3 steps:
- Flip the first bit from the right: 1010 -> 1011.
- Flip the third bit from the right: 1011 -> 1111.
- Flip the fourth bit from the right: 1111 -> 0111.
It can be shown we cannot convert 10 to 7 in less than 3 steps. Hence, we return 3.
Example 2:

Input: start = 3, goal = 4
Output: 3
Explanation: The binary representation of 3 and 4 are 011 and 100 respectively. We can convert 3 to 4 in 3 steps:
- Flip the first bit from the right: 011 -> 010.
- Flip the second bit from the right: 010 -> 000.
- Flip the third bit from the right: 000 -> 100.
It can be shown we cannot convert 3 to 4 in less than 3 steps. Hence, we return 3.
 

Constraints:

0 <= start, goal <= 109

class Solution {
    public int minBitFlips(int start, int goal) {
        
    }
}


78. Subsets
Solved
Medium
Topics
conpanies icon
Companies
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
2,484,526/3.1M
Acceptance Rate
80.7%

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        int n = nums.length;
        int count = 1 << n;
        int  i = 0;

        while(i<count){
            List<Integer> subset = new ArrayList<>();
            for(int x = 0; x < n; x++){
                if((i>>x) & 1 == 1)subset.add(nums[i]);
            }
            subsets.add(subset);
            i++;
        }

        return subsets;
    }
}


Largest prime factor
Difficulty: MediumAccuracy: 27.25%Submissions: 111K+Points: 4
Given a number n, your task is to find the largest prime factor of n.

Examples:

Input: n = 5
Output: 5
Explanation: The prime factorization of 5 is just 5. Therefore, the largest prime factor is 5.
Input: n = 24
Output: 3
Explanation: The prime factorization of 24 is 23×3. Among the prime factors (2, 3), the largest is 3.
Input: n = 13195
Output: 29
Explanation: The prime factorization of 13195 is 5×7×13×29. The largest prime factor is 29.
Constraints:
2 <= n <= 109


// User function Template for Java

class Solution {
    static int largestPrimeFactor(int n) {

     int largestPrime = 0;

     for(int i = 2; i <= Math.sqrt(n); i++){
        if(n%i==0){
            largestPrime = i;
            while(n%i==0){
                n=n/i;
            }
        }
     }

     if(n!=1)return n;

     return largestPrime;
    }
}

All divisors of a Number
Difficulty: EasyAccuracy: 46.73%Submissions: 54K+Points: 2Average Time: 10m
Given an integer N, print all the divisors of N in the ascending order.
 

Example 1:

Input : 20
Output: 1 2 4 5 10 20
Explanation: 20 is completely 
divisible by 1, 2, 4, 5, 10 and 20.

Example 2:

Input: 21191
Output: 1 21191
Explanation: As 21191 is a prime number,
it has only 2 factors(1 and the number itself).

Your Task:

Your task is to complete the function print_divisors() which takes N as input parameter and prints all the factors of N as space seperated integers in sorted order. You don't have to print any new line after printing the desired output. It will be handled in driver code.
 

Expected Time Complexity: O(sqrt(N))
Expected Space Complexity: O(sqrt(N))
 

Constraints:
1 <= N <= 105

class Solution {
    public static void print_divisors(int n) {
        List<Integer> factors = new ArrayList<>();
        for(int i = 1; i <= Math.sqrt(n); i++){

           if(n%i==0){
                factors.add(i);

                if((n/i)!=i){
                    factors.add(n/i);
                }
           }
        }

        for (int num : factors) {
    System.out.print(num + " ");
}
        }
}


204. Count Primes
Solved
Medium
Topics
conpanies icon
Companies
Hint
Given an integer n, return the number of prime numbers that are strictly less than n.

 

Example 1:

Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
Example 2:

Input: n = 0
Output: 0
Example 3:

Input: n = 1
Output: 0
 

Constraints:

0 <= n <= 5 * 106

import java.util.Arrays;

class Solution {
    public int countPrimes(int n) {
        if (n <= 2) return 0; // No primes less than 2
        
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime
        
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }

        return count;
    }
}


Prime Factorization using Sieve
Difficulty: MediumAccuracy: 47.01%Submissions: 29K+Points: 4Average Time: 30m
You are given a positive number N. Using the concept of Sieve, compute its prime factorisation.

Example:

Input: 
N = 12246
Output: 
2 3 13 157
Explanation: 
2*3*13*157 = 12246 = N.
Your Task:

Complete the function findPrimeFactors(), which takes a positive number N as input and returns a vector consisting of prime factors. You should implement Sieve algorithm to solve this problem.


Expected Time Complexity: O(Nlog(log(N)).

Expected Auxiliary Space: O(N).

Constraints:

2<=N<=2*105

// User function Template for Java

class Solution {
    // You must implement this function
    static void sieve() {}

    static List<Integer> findPrimeFactors(int N) {
        
        int[] spf = new int[N+1];

        for(int i = 0; i <= N; i++){
            spf[i]=i;
        }

        for(int i = 2; i*i < N; i++){

            if(spf[i]==i){
                for(int j = i*i; j <= N; j++){
                    if(spf[j]==j)spf[j]=i;
                }
            }
        }

        List<Integer> res = new ArrayList<>();

        for(int i = 2; i <= N; i++){

            if(spf[i]==i)res.add(i);
        }

        return res;


        
    }
}
