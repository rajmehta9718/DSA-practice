Design Twitter
Implement a simplified version of Twitter which allows users to post tweets, follow/unfollow each other, and view the 10 most recent tweets within their own news feed.

Users and tweets are uniquely identified by their IDs (integers).

Implement the following methods:

Twitter() Initializes the twitter object.
void postTweet(int userId, int tweetId) Publish a new tweet with ID tweetId by the user userId. You may assume that each tweetId is unique.
List<Integer> getNewsFeed(int userId) Fetches at most the 10 most recent tweet IDs in the user's news feed. Each item must be posted by users who the user is following or by the user themself. Tweets IDs should be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId follows the user with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId unfollows the user with ID followeeId.
Example 1:

Input:
["Twitter", "postTweet", [1, 10], "postTweet", [2, 20], "getNewsFeed", [1], "getNewsFeed", [2], "follow", [1, 2], "getNewsFeed", [1], "getNewsFeed", [2], "unfollow", [1, 2], "getNewsFeed", [1]]

Output:
[null, null, null, [10], [20], null, [20, 10], [20], null, [10]]

Explanation:
Twitter twitter = new Twitter();
twitter.postTweet(1, 10); // User 1 posts a new tweet with id = 10.
twitter.postTweet(2, 20); // User 2 posts a new tweet with id = 20.
twitter.getNewsFeed(1);   // User 1's news feed should only contain their own tweets -> [10].
twitter.getNewsFeed(2);   // User 2's news feed should only contain their own tweets -> [20].
twitter.follow(1, 2);     // User 1 follows user 2.
twitter.getNewsFeed(1);   // User 1's news feed should contain both tweets from user 1 and user 2 -> [20, 10].
twitter.getNewsFeed(2);   // User 2's news feed should still only contain their own tweets -> [20].
twitter.unfollow(1, 2);   // User 1 follows user 2.
twitter.getNewsFeed(1);   // User 1's news feed should only contain their own tweets -> [10].

class Pair{
    int userid, int tweetid;
    public Pair(int userid, int tweetid){
        this.userid = userid;
        this.tweetid = tweetid;
    }
}
class Twitter {
    Map<Integer,List<Integer>> followMap;
    PriorityQueue<Pair> maxheap;
    public Twitter() {
        followMap = new HashMap<>();
        maxheap = new PriorityQueue<>(
            (p1,p2)-> Integer.compare(p2.tweetid,p1.tweetid)
        );
    }
    
    public void postTweet(int userId, int tweetId) {
        maxheap.add(new Pair(userId,tweetId));
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> recentTweets = new ArrayList<>();
        List<Pair> list = new ArrayList<>();
        int i = 0;
        if(!followMap.containsKey(userId)){
            i = 0;
            while(i<10){
                if(maxheap.isEmpty())return recentTweets;
                Pair popped = maxheap.poll();
                list.add(popped);
                if(popped.userid==userId)recentTweets.add(popped.tweetid);
                i++;
            }
        }
        else{
        	i = 0;
        	List<Integer> followeeList = followMap.get(userId);
        	while(i<10){
                if(maxheap.isEmpty())return recentTweets;
                Pair popped = maxheap.poll();
                list.add(popped);
                if(popped.userid==userId || followeeList.contains(popped.userid))recentTweets.add(popped.tweetid);
                i++;
            }
        }

        for(Pair p : list)maxheap.add(p);
        return recentTweets;
    }
    
    public void follow(int followerId, int followeeId) {
        if(followMap.containsKey(followerId)){
        	List<Integer> followeeList = followMap.get(followerId);
        	followeeList.add(followeeId);
        	map.put(followerId,followeeList);
        }
        else{
        	List<Integer> followeeList = new ArrayList<>();
        	followeeList.add(followeeId);
        	map.put(followerId,followeeList);
        }
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(followMap.containsKey(followerId)){
        	List<Integer> followeeList = followMap.get(followerId);
        	followeeList.remove(Integer.valueOf(followeeId));
        	map.put(followerId,followeeList);
        }
    }
}



Combination Sum
You are given an array of distinct integers nums and a target integer target. Your task is to return a list of all unique combinations of nums where the chosen numbers sum to target.

The same number may be chosen from nums an unlimited number of times. Two combinations are the same if the frequency of each of the chosen numbers is the same, otherwise they are different.

You may return the combinations in any order and the order of the numbers in each combination can be in any order.

Example 1:

Input: 
nums = [2,5,6,9] 
target = 9

Output: [[2,2,5],[9]]



class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
    	Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        solve(0,nums,new ArrayList<>(),res,target);
        return res;
    }

    public void solve(int idx, int[] nums, List<Integer> list, List<List<Integer>> res, int target){
    	if(target==0){
    		res.add(new ArrayList<>(list));
    	}
    	for(int i = idx; i < nums.length && nums[i]<=target; i++){
    		list.add(nums[i]);
    		solve(i,nums,list,res,target-nums[i]);
    		list.remove(list.size()-1);
    	}
    }
}

Combination Sum II
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


class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
       Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        solve(0,candidates,new ArrayList<>(),res,target);
        return res;
    }

    public void solve(int idx, int[] nums, List<Integer> list, List<List<Integer>> res, int target){
    	if(target==0){
    		res.add(new ArrayList<>(list));
    	}
    	for(int i = idx; i < nums.length && nums[i]<=target; i++){
    		if(i>0 && nums[i]==nums[i-1])continue;
    		list.add(nums[i]);
    		solve(i+1,nums,list,res,target-nums[i]);
    		list.remove(list.size()-1);
    	}
    }
}


Permutations
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
        solve(nums,new ArrayList<>(),res);
        return res;
    }

    public void solve(int[] nums, List<Integer> list, List<List<Integer>> res){
    	if(list.size()==nums.length){
    		res.add(new ArrayList<>(list));
    		return;
    	}
    	for(int i = 0; i < nums.length; i++){
    		if(list.contains(nums[i]))continue;
    		list.add(nums[i]);
    		solve(nums,list,res);
    		list.remove(list.size()-1);
    	}
    }
}


Subsets II
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
        solve(0,nums,new ArrayList<>(),res);
        return res;
    }

    public void solve(int i, int[] nums, List<Integer> list, List<List<Integer>> res){
    	if(i==nums.length){

    		if(!res.contains(list))res.add(new ArrayList<>(list));
    		return;
    	}
    		list.add(nums[i]);
    		solve(i+1, nums, list,res);
    		list.remove(list.size()-1);
    		solve(i+1, nums, list,res);
    	}
    }
}

You are given an m x n grid rooms initialized with these three possible values.

-1 A wall or an obstacle.
0 A gate.
INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

 

Example 1:


Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
Example 2:

Input: rooms = [[-1]]
Output: [[-1]]
 

Constraints:

m == rooms.length
n == rooms[i].length
1 <= m, n <= 250
rooms[i][j] is -1, 0, or 231 - 1. 

Pacific Atlantic Water Flow
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

        int[][] pacific = new int[heights.length][heights[0].length];
        int[][] atlantic = new int[heights.length][heights[0].length];


        for(int[] row : pacific)Arrays.fill(row,-1);
        for(int[] row : atlantic)Arrays.fill(row,-1);

        for(int i = 0; i < heights.length; i++){
            for(int j = 0; j < heights[0].length; j++){
                if(i==0 || j==0)pacific[i][j]=1;
                if(i==heights.length-1 || j==heights[0].length-1)atlantic[i][j]=1;
            }
        }

        for(int i = 0; i < heights.length; i++){
            for(int j = 0; j < heights[0].length; j++){
                if(pacific[i][j]==-1)pacific[i][j] = dfsP(i,j,heights,pacific,Integer.MAX_VALUE);
                if(atlantic[i][j]==-1)atlantic[i][j] = dfsA(i,j,heights,atlantic,Integer.MAX_VALUE);
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < heights.length; i++){
            for(int j = 0; j < heights[0].length; j++){
                if(pacific[i][j]==1 && atlantic[i][j]==1){
                    List<Integer> qualified = new ArrayList<>();
                    qualified.add(i);
                    qualified.add(j);
                    res.add(new ArrayList<>(qualified));
                }
            }
        }
        return res;
    }

    public int dfsP(int i, int j, int[][] heights, int[][] pacific, int prevP){
        if(i<0 || j<0 || i>=heights.length || j>=heights[0].length || heights[i][j]>prevP || pacific[i][j]==0)return -1;
        if(pacific[i][j]==1)return 1;
        pacific[i][j]=0;
        int left = dfsP(i,j-1,heights,pacific,heights[i][j];);
        int right = dfsP(i,j+1,heights,pacific,heights[i][j];);
        int up = dfsP(i-1,j,heights,pacific,heights[i][j];);
        int down = dfsP(i+1,j,heights,pacific,heights[i][j];);
        return pacific[i][j]= (left==1 || right==1 || up==1 || down==1) ? 1 : -1;
    }

    public int dfsA(int i, int j, int[][] heights, int[][] atlantic, int prevA){
        if(i<0 || j<0 || i>=heights.length || j>=heights[0].length || heights[i][j]>prevA || pacific[i][j]==0)return -1;
        if(atlantic[i][j]==1)return 1;
        atlantic[i][j]=0;
        int left = dfsA(i,j-1,heights,atlantic,heights[i][j];);
        int right = dfsA(i,j+1,heights,atlantic,heights[i][j];);
        int up = dfsA(i-1,j,heights,atlantic,heights[i][j];);
        int down = dfsA(i+1,j,heights,atlantic,heights[i][j];);
        return atlantic[i][j]= (left==1 || right==1 || up==1 || down==1) ? 1 : -1;
    }



}



Redundant Connection
You are given a connected undirected graph with n nodes labeled from 1 to n. Initially, it contained no cycles and consisted of n-1 edges.

We have now added one additional edge to the graph. The edge has two different vertices chosen from 1 to n, and was not an edge that previously existed in the graph.

The graph is represented as an array edges of length n where edges[i] = [ai, bi] represents an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the graph is still a connected non-cyclical graph. If there are multiple answers, return the edge that appears last in the input edges.

Example 1:

Input: edges = [[1,2],[1,3],[3,4],[2,4]]

Output: [2,4]

class Solution {

    public int find(int element, int[] parent){
        if(parent[element]==element)return element;
        return parent[element]=find(parent[element],parent);    
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        int[] parent = new int[n+1];

        for(int i = 1; i < parent.length; i++){
            parent[i]=i;
        }
        int[] rank = new int[n+1];

        for(int[] edge : edges){

            int elementA = edge[0];
            int elementB = edge[1];

            int parentA = find(elementA,parent);
            int parentB = find(elementB,parent);

            if(parentA==parentB){
                return new int[]{elementA,elementB};
            }

            if(rank[parentA]==rank[parentB]){
                parent[parentB] = parentA;
                rank[parentA]++;
            }
            else if(rank[parentA]<rank[parentB]){
                parent[parentA]=parentB;
            }
            else parent[parentB] = parentA;
        }

        return new int[]{-1,-1};
    }
}


1 2 3 4

1 1 1 1

1 0 0 0

Word Ladder
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

class Pair{
    String word;
    int len;
    public Pair(String word, int len){
        this.word=word;
        this.len=len;
    }
}
class Solution {
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
      if(!wordList.contains(endWord))return 0;
      Set<String> set = new HashSet<>();
      for(String s : wordList)set.add(s);
      Queue<Pair> queue = new LinkedList<>();
      queue.add(new Pair(beginWord,1));

      while(!queue.isEmpty()){
        Pair popped = queue.poll();
        String prev = popped.word;
        int len = popped.len;
        char[] prevArr = prev.toCharArray();
        if(prev.equals(endWord))return len;
        for(int i = 0; i < prev.length(); i++){
            for(int c = 0; c < 25; c++){
                char temp  = prevArr[i];
                prevArr[i] = (char) (c + 'a');
                String newStr = new String(prevArr);
                if(set.contains(newStr)){
                    queue.add(new Pair(newStr, len+1));
                    set.remove(newStr);
                }
                prevArr[i] = temp;
            }
        }
      }
      return 0;
    }
}


Jump Game
You are given an integer array nums where each element nums[i] indicates your maximum jump length at that position.

Return true if you can reach the last index[0] starting from index[0] 0, or false otherwise.

Example 1:

Input: nums = [1,2,0,1,0]

Output: true
Explanation: First jump from index[0] 0 to 1, then from index[0] 1 to 3, and lastly from index[0] 3 to 4.

Example 2:

Input: nums = [1,2,1,0,1]

Output: false
Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 1000



Jump Game II
You are given an array of integers nums, where nums[i] represents the maximum length of a jump towards the right from index[0] i. For example, if you are at nums[i], you can jump to any index[0] i + j where:

j <= nums[i]
i + j < nums.length
You are initially positioned at nums[0].

Return the minimum number of jumps to reach the last position in the array (index[0] nums.length - 1). You may assume there is always a valid answer.

Example 1:

Input: nums = [2,4,1,1,1,1]

Output: 2
Explanation: Jump from index[0] 0 to index[0] 1, then jump from index[0] 1 to the last index[0].

Example 2:

Input: nums = [2,1,2,1,0]

Output: 2
Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 100

Non-Cyclical Number
A non-cyclical number is an integer defined by the following algorithm:

Given a positive integer, replace it with the sum of the squares of its digits.
Repeat the above step until the number equals 1, or it loops infinitely in a cycle which does not include 1.
If it stops at 1, then the number is a non-cyclical number.
Given a positive integer n, return true if it is a non-cyclical number, otherwise return false.

Example 1:

Input: n = 100

Output: true
Explanation: 1^2 + 0^2 + 0^2 = 1

Example 2:

Input: n = 101

Output: false
Explanation:
1^2 + 0^2 + 1^2 = 2
2^2 = 4
4^2 = 16
1^2 + 6^2 = 37
3^2 + 7^2 = 58
5^2 + 8^2 = 89
8^2 + 9^2 = 145
1^2 + 4^2 + 5^2 = 42
4^2 + 2^2 = 20
2^2 + 0^2 = 4 (This number has already been seen)

Constraints:

1 <= n <= 1000

100
class Solution {
    Set<Integer> set = new HashSet<>();
    public boolean isHappy(int n) {
        
        if(n==1)return true;
        long sumsquares = 0;
        while(n>0){
            int x = n%10;
            sumsquares+=Math.pow(x,2);
            n/=10;
        }
        if(set.contains((int) sumsquares))return false;
        return isHappy(sumsquares);
    }
}



Plus One
You are given an integer array digits, where each digits[i] is the ith digit of a large integer. It is ordered from most significant to least significant digit, and it will not contain any leading zero.

Return the digits of the given integer after incrementing it by one.

Example 1:

Input: digits = [1,2,3,4]

Output: [1,2,3,5]
Explanation 1234 + 1 = 1235.

Example 2:

Input: digits = [9,9,9]

Output: [1,0,0,0]
Constraints:

1 <= digits.length <= 100
0 <= digits[i] <= 9


class Solution {
    public int[] plusOne(int[] digits) {
        if(digits[digits.length-1]!=9){
            digits[digits.length-1]++;
            return digits;
        }
        long num = 0;
        for(int i : digits){
            num = (num*10)+i;
        }

        num+=1;
        List<Integer> list = new ArrayList<>();
        while(num>0){
            list.add(num%10);
            num/=10;
        }

        int[] res = new int[list.size()];
        int index[0] = 0;
        for(int x = list.size()-1; x>=0; x--){
            res[index[0]++]=list.get(x);
        }
        return res;

    }
}


Multiply Strings
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

class Solution {
    public String multiply(String num1, String num2) {
        double num1n = Double.parseDouble(num1);
        double num2n = Double.parseDouble(num2);

        return String.valueOf((long)num1n * num2n);
    }
}

A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index[0]] where:

val: an integer representing Node.val
random_index[0]: the index[0] of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

 

Example 1:


Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:


Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
Example 3:



Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
 

Constraints:

0 <= n <= 1000
-104 <= Node.val <= 104
Node.random is null or is pointing to some node in the linked list.


/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        Node dummyNode = new Node(-1);
        Node newNode = new Node(-5);
        dummyNode.next = newNode;
        Node curr = head;
        List<Node> list = new ArrayList<>();
        while(curr!=null){
            newNode.next = new Node(curr.val);
            newNode = newNode.next;
            curr = curr.next;
            list.add(newNode);
        }

        Node newHead = dummyNode.next.next;
        while(head!=null){
            if(head.random!=null){
                newHead.random = list.get(head.random.val);

            }
            head = head.next;
            newHead = newHead.next;
        }
        return dummyNode.next.next;
    }
}



528. Random Pick with Weight
Medium
Topics
Companies
You are given a 0-index[0]ed array of positive integers w where w[i] describes the weight of the ith index[0].

You need to implement the function pickindex[0](), which randomly picks an index[0] in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index[0] i is w[i] / sum(w).

For example, if w = [1, 3], the probability of picking index[0] 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index[0] 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 

Example 1:

Input
["Solution","pickindex[0]"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickindex[0](); // return 0. The only option is to return 0 since there is only one element in w.
Example 2:

Input
["Solution","pickindex[0]","pickindex[0]","pickindex[0]","pickindex[0]","pickindex[0]"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickindex[0](); // return 1. It is returning the second element (index[0] = 1) that has a probability of 3/4.
solution.pickindex[0](); // return 1
solution.pickindex[0](); // return 1
solution.pickindex[0](); // return 1
solution.pickindex[0](); // return 0. It is returning the first element (index[0] = 0) that has a probability of 1/4.

Since this is a randomization problem, multiple answers are allowed.
All of the following outputs can be considered correct:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.
 

Constraints:

1 <= w.length <= 104
1 <= w[i] <= 105
pickindex[0] will be called at most 104 times.

class Solution {
    int[] newArr
    public Solution(int[] w) {
    int sum = 0;
        for(int i : w){
            sum+=i;
        }

        newArr = new int[sum];
        int y = 0;

        for(int x = 0; x < w.length; x++){
            int num = x;
            while(num>0){
                newArr[y++]=x;
                num--;
            }
        }
    }
    
    public int pickindex[0]() {
          int randomindex[0] = (Math.random()*newArr.length);
          int randomElement = newArr[randomindex[0]];
          return randomElement;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickindex[0]();
 */

import java.util.Random;


class Solution {
    int[] prefixSum;
    public Solution(int[] w) {
    
        prefixSum = new int[w.length];
        prefixSum[0] = w[0];

        for(int i = 1; i < w.length; i++){
            prefixSum[i]=prefixSum[i-1]+w[i];    

        }
    }
    
    public int pickindex[0]() {
          int random = (int)(Math.random()*prefixSum[prefixSum.length-1]);
          int i = 0, j = prefixSum.length-1;
          while(i<j){
            int mid = i + (j-i)/2;
            if(prefixSum[mid]<randomindex[0]){
            i=j+1;
            }
            else j = i-1;
          }
          return i;
    }
}

346. Moving Average from Data Stream
Easy
Topics
Companies
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:

MovingAverage(int size) Initializes the object with the size of the window size.
double next(int val) Returns the moving average of the last size values of the stream.
 

Example 1:

Input
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output
[null, 1.0, 5.5, 4.66667, 6.0]

Explanation
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3

class MovingAverage {
    int size;
    static int i = 0;
    static int sum = 0;
    public MovingAverage(int size) {
        this.size=size;
        List<Integer> list = new ArrayList<>();
    }
    
    public double next(int val) {
        sum+=val;
        list.add(val);
        while((j-i+1)>size){
         sum-=list.get(i);
         i++;
        }
        double avg = (double) sum / (double) j-i+1;
        j++;
        return avg;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */


398. Random Pick index[0]
Medium
Topics
Companies
Given an integer array nums with possible duplicates, randomly output the index[0] of a given target number. You can assume that the given target number must exist in the array.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the array nums.
int pick(int target) Picks a random index[0] i from nums where nums[i] == target. If there are multiple valid i's, then each index[0] should have an equal probability of returning.
 

Example 1:

Input
["Solution", "pick", "pick", "pick"]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
Output
[null, 4, 0, 2]

Explanation
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // It should return either index[0] 2, 3, or 4 randomly. Each index[0] should have equal probability of returning.
solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(3); // It should return either index[0] 2, 3, or 4 randomly. Each index[0] should have equal probability of returning.

class Solution {
    Map<Integer,List<Integer>> map;
    public Solution(int[] nums) {
        map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            List<Integer> res = map.get(nums[i]);
            if(res==null)res=new ArrayList<>();
            res.add(i);
        }
    }
    
    public int pick(int target) {
        List<Integer> res = map.get(target);
        if(res==null)return -1;
        int randomindex[0] = (int) (Math.random() * res.size()-1);
        return res.get(randomindex[0]);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */


249. Group Shifted Strings
Medium
Topics
Companies
Perform the following shift operations on a string:

Right shift: Replace every letter with the successive letter of the English alphabet, where 'z' is replaced by 'a'. For example, "abc" can be right-shifted to "bcd" or "xyz" can be right-shifted to "yza".
Left shift: Replace every letter with the preceding letter of the English alphabet, where 'a' is replaced by 'z'. For example, "bcd" can be left-shifted to "abc" or "yza" can be left-shifted to "xyz".
We can keep shifting the string in both directions to form an endless shifting sequence.

For example, shift "abc" to form the sequence: ... <-> "abc" <-> "bcd" <-> ... <-> "xyz" <-> "yza" <-> .... <-> "zab" <-> "abc" <-> ...
You are given an array of strings strings, group together all strings[i] that belong to the same shifting sequence. You may return the answer in any order.

 

Example 1:

Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]

Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]

Example 2:

Input: strings = ["a"]

Output: [["a"]]

 

Constraints:

1 <= strings.length <= 200
1 <= strings[i].length <= 50
strings[i] consists of lowercase English letters.

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<Integer> set = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        for(String s : strings)set.add(s);
        for(String str : strings){
            if(set.contains(str)){
                List<String> sublist = new ArrayList<>();
                sublist.add(str);
                set.remove(str);
                char[] chArr = str.toCharArray();
                for(int i = 0; i < 26; i++){
                    for(int j = 0; j < chArr.length; j++)
                    {
                        chArr[j] = (char) ('a'+ (chArr[j]-'a'+1)%26);
                    }
                    
                    if(set.contains(new String(chArr))){
                        sublist.add(new String(chArr));
                        set.remove(new String(chArr));
                    }
                }
                
            res.add(new ArrayList<>(sublist));
            }
        }

    }
}

import java.util.*;

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        // A map to group strings based on their canonical form (shift pattern)
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strings) {
            String key = getCanonicalForm(str); // Calculate the canonical form
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str); // Group by the form
        }

        // Collect all grouped strings into a list
        return new ArrayList<>(map.values());
    }

    // Method to calculate the canonical form of a string
    private String getCanonicalForm(String str) {
        char[] chars = str.toCharArray();
        int shift = chars[0] - 'a'; // Shift the first character to 'a'

        for (int i = 0; i < chars.length; i++) {
            // Adjust characters based on the shift, wrapping around within 'a' to 'z'
            chars[i] = (char) ((chars[i] - shift + 26) % 26 + 'a');
        }

        return new String(chars); // Return the normalized string
    }
}


721. Accounts Merge
Medium
Topics
Companies
Hint
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.


Example 1:

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

=

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node curr, int insertVal) {
        if(curr==null){
            Node head = new Node(insertVal);
            head.next = head;
            return head;
        }
        if(curr.next==curr){
            curr.next = new Node(insertVal);
            curr.next.next=curr;
            return curr;
        }
        Node head = curr;
        while(true){
            if(head.val < insertVal && insertVal < head.next.val){
                Node nextNode = head.next;
                head.next = new Node(insertVal);
                head = head.next;
                head.next = nextNode;
                break;
            }
            head = head.next;
            if(head==curr){
                Node nextNode = head.next;
                head.next = new Node(insertVal);
                head = head.next;
                head.next = nextNode;
                break;
            }
        }
        
    
        return curr;
    }
}



Code
Testcase
Test Result
Test Result
1004. Max Consecutive Ones III
Medium
Topics
Companies
Hint
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

 

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.



class Solution {
    public int longestOnes(int[] nums, int k) {
        int i = 0, j = nums.length-1;
        int count = k;
        int maxOnes = 0;

        while(j<nums.length){

            if(nums[j]==0){
                while(count>=k){
                    if(nums[i]==0){
                        count--;
                    }
                    i++;
                }
                count++;
            }

            maxOnes = Math.max(maxOnes,j-i+1);
            j++;
        }
        return maxOnes;
    }
}


You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.

The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].

 

Example 1:


Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Example 2:

Input: firstList = [[1,3],[5,9]], secondList = []
Output: []

[[0,2],[1,5],[5,10],[8,12],[13,23],[15,24],[24,25],[25,26]]

[1,2][5,5][8,10][15,23][24,24][25,25]

class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int[][] mergedInOne = new int[firstList.length+secondList.length][2];
        int i = 0;
        for(int[] interval : firstList){
            mergedInOne[i]=interval;
            i++;
        }

        for(int[] intvl : secondList){
            mergedInOne[i]=intvl;
            i++;
        }

        Arrays.sort(mergedInOne, (x1,x2) -> Integer.compare(x1[0],x2[0]));

        List<int[]> res = new ArrayList<>();

        for(int j = 1; j < mergedInOne.length; j++){
            if(mergedInOne[j][0]<=mergedInOne[j-1][1]){
                int[] toAdd = new int[2];
                toAdd[0] = Math.max(mergedInOne[j-1][0],mergedInOne[j][0]);
                toAdd[1] = Math.min(mergedInOne[j-1][1],mergedInOne[j][1]);
                res.add(toAdd);
            }
            else{
                res.add(mergedInOne[j]);
            }
        }

        return res.toArray(new int[res.size()][2]);
    }
}

270. Closest Binary Search Tree Value
Easy
Topics
Companies
Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target. If there are multiple answers, print the smallest.

 

Example 1:


Input: root = [4,2,5,1,3], target = 3.714286
Output: 4
Example 2:

Input: root = [1], target = 4.428571
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 109
-109 <= target <= 109


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int closestValue(TreeNode root, double target) {
        
        double minDiff = Double.MAX_VALUE, diff = 0.0;
        int minVal = root.val;
        while(root!=null){
            diff = Math.abs(target-root.val);
            if(diff<=minDiff){
                if(diff==minDiff){
                    if(root.val < minVal)minVal = root.val;
                }
                else{
                    minDiff = diff;
                minVal = root.val;
                }
                
            } 
            if(target==(double)root.val)return root.val;
            if(target<(double)root.val){
                root=root.left;
            }
            else{
                root = root.right;
            }
        }
        return minVal;
    }
}


415. Add Strings
Attempted
Easy
Topics
Companies
Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.

 

Example 1:

Input: num1 = "11", num2 = "123"
Output: "134"
Example 2:

Input: num1 = "456", num2 = "77"
Output: "533"
Example 3:

Input: num1 = "0", num2 = "0"
Output: "0"

Constraints:

1 <= num1.length, num2.length <= 104
num1 and num2 consist of only digits.
num1 and num2 don't have any leading zeros except for the zero itself.

class Solution {
    public String addStrings(String num1, String num2) {
        


        int i = num1.length()-1, j = num2.length()-1;
        int a = 0, b = 0, carry = 0, sum = 0;
        String res = "";
        while(i>=0 && j>=0){
            a = num1.charAt(i)-'0';
            b = num2.charAt(j)-'0';
            sum = a + b + carry;
            res+=(char) (sum%10 + '0');
            carry = sum/10;
            i--;
            j--;
        }

        while(i>=0){
            a = num1.charAt(i)-'0';
            sum = a + carry;
            res+=(char) (sum%10 + '0');
            carry = sum/10;
            i--;
        }

        while(j>=0){
            b = num2.charAt(j)-'0';
            sum = b + carry;
            res+=(char) (sum%10 + '0');
            carry = sum/10;
            j--;
        }

        if(carry>0){
            res+=(char) (carry%10 + '0');
        }

        return new StringBuilder(res).reverse().toString();

    }
}


636. Exclusive Time of Functions
Medium
Topics
Companies
On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.

Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.

You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.

A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.

Return the exclusive time of each function in an array, where the value at the ith index[0] represents the exclusive time for the function with ID i.


Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3,4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.

1891. Cutting Ribbons
Solved
Medium
Topics
Companies
Hint
You are given an integer array ribbons, where ribbons[i] represents the length of the ith ribbon, and an integer k. You may cut any of the ribbons into any number of segments of positive integer lengths, or perform no cuts at all.

For example, if you have a ribbon of length 4, you can:
Keep the ribbon of length 4,
Cut it into one ribbon of length 3 and one ribbon of length 1,
Cut it into two ribbons of length 2,
Cut it into one ribbon of length 2 and two ribbons of length 1, or
Cut it into four ribbons of length 1.
Your task is to determine the maximum length of ribbon, x, that allows you to cut at least k ribbons, each of length x. You can discard any leftover ribbon from the cuts. If it is impossible to cut k ribbons of the same length, return 0.

 

Example 1:

Input: ribbons = [9,7,5], k = 3
Output: 5
Explanation:
- Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
- Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
- Keep the third ribbon as it is.
Now you have 3 ribbons of length 5.
Example 2:

Input: ribbons = [7,5,9], k = 4
Output: 4
Explanation:
- Cut the first ribbon to two ribbons, one of length 4 and one of length 3.
- Cut the second ribbon to two ribbons, one of length 4 and one of length 1.
- Cut the third ribbon to three ribbons, two of length 4 and one of length 1.
Now you have 4 ribbons of length 4.
Example 3:

Input: ribbons = [5,7,9], k = 22
Output: 0
Explanation: You cannot obtain k ribbons of the same positive integer length.
 

Constraints:

1 <= ribbons.length <= 105
1 <= ribbons[i] <= 105
1 <= k <= 109

class Solution {
    public int maxLength(int[] ribbons, int k) {

        int left = 1, right = 0;
        int res = 0;
        for(int i : ribbons)right = Math.max(right,i);

        while(left<=right){

            int mid = left + (right-left)/2;
            if(canCut(ribbons,mid,k)){
               res = mid;
               left = mid+1;
            }
            else{
             right = mid-1;
            }
        }
        return res;
    }


    public boolean canCut(int[] ribbons, int length, int k){
        int count = 0;
        for(int r : ribbon){
            count+=(r/length);
        }   
        return count>=k;
    }
}


348. Design Tic-Tac-Toe
Medium
Topics
Companies
Hint
Assume the following rules are for the tic-tac-toe game on an n x n board between two players:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Implement the TicTacToe class:

TicTacToe(int n) Initializes the object the size of the board n.
int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move, and the two players alternate in making moves. Return
0 if there is no winner after the move,
1 if player 1 is the winner after the move, or
2 if player 2 is the winner after the move.
 

Example 1:

Input
["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
[[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
Output
[null, 0, 0, 0, 0, 0, 0, 1]

Explanation
TicTacToe ticTacToe = new TicTacToe(3);
Assume that player 1 is "X" and player 2 is "O" in the board.
ticTacToe.move(0, 0, 1); // return 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

ticTacToe.move(0, 2, 2); // return 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

ticTacToe.move(2, 2, 1); // return 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

ticTacToe.move(1, 1, 2); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

ticTacToe.move(2, 0, 1); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

ticTacToe.move(1, 0, 2); // return 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
 

Constraints:

2 <= n <= 100
player is 1 or 2.
0 <= row, col < n
(row, col) are unique for each different call to move.
At most n2 calls will be made to move.
 

Follow-up: Could you do better than O(n2) per move() operation?

class TicTacToe {
    int[][] board;
    int n;
    public TicTacToe(int n) {
        board = new int[n][n];
        this.n=n;
    }
    
    public int move(int row, int col, int player) {
        board[row][col]=player;
        return validate(row,col,player,board);
    }

    public int validate(int row, int col, int player, int[][] board){
        boolean rowWin = true, colWin = true, diagWin = true;
        for(int i = 0; i < n; i++){
            if(board[row][i]!=player)rowWin = false;
            if(board[i][col]!=player)colWin = false;
        }

        if(rowWin || colWin)return player;
        if(row==col && (row==n/2 && col==n/2)){
            for(int a = 0; a < n; a++){
                if(board[a][a]!=player)diagWin = false;           
            }
            if(diagWin) return player;
            diagWin = true;
            int x = 0;
            for(int j = n-1; j >= 0; j--){
                if(board[x++][j]!=player)diagWin = false;           
            }
            if(diagWin)return player;
        }
        else if(row==col){ 
            for(int j = 0; j < n; j++){
                if(board[j][j]!=player)diagWin = false;           
            }   
        } 
        else if((row+col)==(n-1)){
        int x = 0;
            for(int j = n-1; j >= 0; j--){
                if(board[x++][j]!=player)diagWin = false;           
            }   

        }
        else{
            diagWin = false;
        }

        return (rowWin || colWin || diagWin) ? player : 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */

 378. Kth Smallest Element in a Sorted Matrix
Medium
Topics
Companies
Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).

 

Example 1:

Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
Example 2:

Input: matrix = [[-5]], k = 1
Output: -5
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 109
All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
1 <= k <= n2
 

Follow up:

Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int[] row : matrix){
        for(int i : row){
            if(pq.size()==k){
                if(i<pq.peek()){
                    pq.poll();
                    pq.add(i);
                }
            }
            else pq.add(i);
        }
        }
        return pq.peek();
    }
}


class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int smallest = Integer.MIN_VALUE;
        int count = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j]>=smallest){
                    smallest = matrix[i][j];
                }
                count++;
                if(count==k)break;
            }
        }

        return smallest;
    }
}



304. Range Sum Query 2D - Immutable
Medium
Topics
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
Output
[null, 8, 11, 12]

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-104 <= matrix[i][j] <= 104
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
At most 104 calls will be made to sumRegion.


536. Construct Binary Tree from String
Medium
Topics
Companies
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

 

Example 1:


Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]
Example 2:

Input: s = "4(2(3)(1))(6(5)(7))"
Output: [4,2,6,3,1,5,7]
Example 3:

Input: s = "-4(2(3)(1))(6(5)(7))"
Output: [-4,2,6,3,1,5,7]
 

Constraints:

0 <= s.length <= 3 * 104
s consists of digits, '(', ')', and '-' only.
All numbers in the tree have value at most than 230.                                


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode str2tree(String s) {
        if(s==null || s.length()==0)return null;

        return helper(s,new int[]{0});
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



247. Strobogrammatic Number II
Medium
Topics
Companies
Hint
Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).


Example 1:

Input: n = 2
Output: ["11","69","88","96"]
Example 2:

Input: n = 1
Output: ["0","1","8"]
 

Constraints:

1 <= n <= 14


class Solution {

    public List<String> findStrobogrammatic(int n) {
        if(n==0) return new ArrayList<>();
        if(n==1) return List.of("0","1","8");
        if(n==2) return List.of("11", "69", "96", "88");
        List<List<String>> mylist = new ArrayList<>();
        mylist.add(new ArrayList<>());
        mylist.add(List.of("0","1","8"));
        mylist.add(List.of("11", "69", "96", "88"));
        for(int i = 3; i <= n; i++){
        List<String> sublist = new ArrayList<>();
            for(String x : mylist.get(i-2)){
                for(String y : mylist.get(2)){
                    StringBuilder sb = new StringBuilder(x);
                    sb.insert(0,y.charAt(0));
                    sb.append(y.charAt(1));
                    sublist.add(sb.toString());
                }
            }
            mylist.add(sublist);
        }
        return mylist.get(n);
    }
}

825. Friends Of Appropriate Ages
Medium
Topics
Companies
There are n persons on a social media website. You are given an integer array ages where ages[i] is the age of the ith person.

A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:

age[y] <= 0.5 * age[x] + 7
age[y] > age[x]
age[y] > 100 && age[x] < 100
Otherwise, x will send a friend request to y.

Note that if x sends a request to y, y will not necessarily send a request to x. Also, a person will not send a friend request to themself.

Return the total number of friend requests made.

 

Example 1:

Input: ages = [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: ages = [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: ages = [20,30,100,110,120]
Output: 3
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.

636. Exclusive Time of Functions
Medium
Topics
Companies
On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.

Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.

You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.

A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.

Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.

 
Example 1:

Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3,4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.


["0:start:0","1:start:2","1:end:5","0:end:6"]
["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]


class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        int prevTime = 0;
        Stack<Integer> stack = new Stack<>();
        for(String str : logs){
            String[] strArr = str.split(":");
            int currFunc = strArr[0];
            int timestamp = strArr[2];
            if(strArr[1].equals("start")){
                if(!stack.isEmpty()){
                    res[stack.peek()]+=timestamp-prevTime;
                }
                stack.push(currFunc);
                prevTime = timestamp;
            }
            else{
                res[stack.peek()]+=(timestamp-prevTime+1);
                prevTime = timestamp+1;
            }
        }
        return res;       
    }
}


934. Shortest Bridge
Medium
Topics
Companies
You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.

You may change 0's to 1's to connect the two islands to form one island.

Return the smallest number of 0's you must flip to connect the two islands.

 

Example 1:

Input: grid = [[0,1],[1,0]]
Output: 1
Example 2:

Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1

Constraints:

n == grid.length == grid[i].length
2 <= n <= 100
grid[i][j] is either 0 or 1.
There are exactly two islands in grid.

class Pair{
    
    int row,col,dist;
    Pair(int row, int col, int num, int dist){
        this.row=row;
        this.col=col;
        this.dist=dist;
    }
}
class Solution {
    public int shortestBridge(int[][] grid) {
       Queue<Pair> queue = new LinkedList<>();
       for(int i = 0; i < grid.length; i++){
        for(int j = 0; j < grid[0].length; j++){
            if(grid[i][j]==1){
                grid[i][j]=2;
                queue.add(new Pair(i,j,0));
                break;
            }
        }
       }
       grid[0][0] = 2;
       while(!queue.isEmpty()){
            Pair popped = queue.poll();
            int thisrow = popped.row;
            int thiscol = popped.col;
            int thisDist = popped.dist;
            int[][] directions = {{0,1}{0,-1}{-1,0}{1,0}};
            for(int[] dir : directions){
                int newrow = thisrow+dir[0];
                int newcol = thiscol+dir[1];
                if(newrow<0 || newcol<0 || newrow>=grid.length || newcol>=grid[0].length || grid[newrow][newcol]==2)continue;
                if(thisDist > 0 && grid[newrow][newcol]==1)return thisDist;
                if(grid[newrow][newcol]==1)queue.add(new Pair(newrow,newcol,thisDist));
                else queue.add(new Pair(newrow,newcol,thisDist+1));            
                grid[newrow][newcol]=2;
            }
       }
       return -1;
    }
}


1094. Car Pooling
Medium
Topics
Companies
Hint
There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.

 

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
 

Constraints:

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 105

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int remainingCapacity = capacity;
        prevEnd = Integer.MIN_VALUE;
        for(int[] trip : trips){
            if(trip[1]<prevEnd){
                remainingCapacity-=trip[0];
            }
            else{
                remainingCapacity = capacity;
                remainingCapacity -= trip[0];
            }
            if(remainingCapacity<0)return false;
            prevEnd = trip[2];
        }
        return true;
    }
}

[[2,2,6],[2,4,7],[8,6,7]]