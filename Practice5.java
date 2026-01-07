20 2 7 12 15 1 6 8
Pi i      
2  20  7 12 15 1 6 8
   Pi  i  

2  7  20 12 15 1 6 8
      Pi       i

2  7  1  12 15 20 6 8
         Pi       i

2  7  1  6  15 20 12 8
            Pi       i

2  7  1  6  8  20  12  15
            Pi 




2 7 1 6

    i
  Pi

2 1 7   6
    Pi  i

2 1 6 7




2 7 1 6 8 20 12  15
                 i
        Pi	     pivot



class Solution{

public void swap(int i, int Pi, int[] nums){
	int temp = nums[i];
	nums[i] = nums[Pi];
	nums[Pi] = temp;
}
public int partition(nums, i, j){
int pivot = j;
int Pi = i;
while(i<j){
	if(nums[i]<nums[pivot]){
	swap(i,Pi,nums);
	Pi++;
	}
    i++;
}
swap(Pi,Pivot,nums);
return Pi;
}


public void qs(nums){
	int i = 0;
	int j = nums.length-1;
	quicksort(nums,i,j);
}
public void quicksort(nums,i,j){
	if(i>=j){
	return;
} 

int Pi = partition(nums, i, j);
	quicksort(nums,i,Pi-1);
	quicksort(nums,Pi+1,j);
}
}


Palindromic substrings:

s = "abc"

a b c




abc



class Solution{
int[][] memo;
    int numPalindromes;

    public Solution() {
        memo = new int[1000][1000];
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
    }
	public int countPalindromes(String s){
	for(int i = 0; i < s.length(); i++){
	for(int j = i; j < s.length(); j++){
		if(isPalindrome(i,j,s))numPalindromes++;

	}
	}
	return numPalindromes;
}


public boolean isPalindrome(int i, int j, String s){
	if(i>=j){
	return true;
	}
	if(memo[j][j]!=-1){
	return memo[i][j] == 1;
	}
	if(s.charAt(i)==s.charAt(j)){
		memo[i][j] = isPalindrome(i+1,j-1,s) ? 1 : 0;
	}
	else{
		memo[i][j] = 0;
	}
	return memo[i][j] == 1;
}
}


memo for : "badab"

[
-1  0  0 0  1
-1 -1  0 1  0
-1 -1 -1 0  0
-1 -1 -1 -1 0
-1 -1 -1 -1 -1
]



i/p: 1 -> 2 -> 3 -> 4 -> 5

O/P: 1 -> 5 -> 2 -> 4 -> 3

1 -> 2 -> 3 -> 4 -> 5
                    f
          s



firstNext  3 
secondNext 4

class Solution{

public void reorder(ListNode head){

	ListNode slow = head;
	ListNode fast = head;

while(fast!=null && fast.next!=null){
	fast = fast.next.next;
	slow = slow.next;
}

ListNode curr = slow.next;
ListNode prev = null;
slow.next = null;

while(curr!=null){
	ListNode next = curr.next;
	curr.next = prev;
	prev = curr;
	curr = next;
}


ListNode first = head;
ListNode second = prev;

while(second!=null){
	ListNode firstTemp = first.next;
	ListNode secondTemp = second.next;
	first.next = second;
	second.next = firstTemp;
	first = firstTemp;
	second = secondTemp;
}

}
}


class Solution{
	
	
	public int uniquePaths(int m, int n){
		findPaths(0,0,m,n);
	}

	public void findPaths(int i, int j, int m, int n){
		
	}
	
}


5 4 -1 7 8

s         
Max = 5


7 1 5 3 6 4


1 6 9 0 0 0

2 5 6

1 6 9 6 6 9


900 940  950  1100 1500 1800
910 1200 1120 1130 1900 2000


935  1000 1100
1130 1200 1240


                                                aabb                                                              

                              a|abb                             aa|bb



                         a|a|bb                         aa|b|b           aa|bb|  



                   a|a|b|b       a|a|bb|         aa|b|b|      



               a|a|b|b|    


f(0---> n)





Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the 
frequency
 of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

 

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []


                                   2367


                             2


                        2,2





Permutations leetcode:

									                                         123

								                    1                                             
 
							             




							          1,2                   1,3

						         1,2,3   1,2          


						         [1,2,3]


						           ""



class Solution {
List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
		solve(0,nums,new HashSet<>());        
    }

    public void solve(int idx, int[] nums, Set<Integer> set){
    	if(set.contains(nums[idx]))


    	for(int i = idx, i < nums.length; i++){
    		
    	}
    }
}


 A = [1, 4, 2, 3]
 B = [2, 5, 1, 6]
 C = 4


class Pair{
	int total = 0;
	int a = 0;
	int b = 0;
	Pair(int total, int a, int b){
	 this.total=total;
	 this.a=a;
	 this.b=b;
	}	
}

import java.util.*;

public class Solution {
    class Pair {
        int sum;
        int a;
        int b;
        Pair(int sum, int a, int b) {
            this.sum = sum;
            this.a = a;
            this.b = b;
        }
    }

    public int[] solve(int[] A, int[] B, int C) {
        Arrays.sort(A);
        Arrays.sort(B);

        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p2.sum - p1.sum);
        Set<String> set = new HashSet<>();
        pq.add(new Pair(A[A.length - 1] + B[B.length - 1], A.length - 1, B.length - 1));
        set.add((A.length - 1) + "," + (B.length - 1));

        int[] res = new int[C];
        int x = 0;

        while(x < C) {
            Pair popped = pq.poll();
            int sum = popped.sum;
            res[x++] = sum;
            int i = popped.a;
            int j = popped.b;

            if(i - 1 >= 0 && !set.contains((i-1) + "," + j)) {
                pq.add(new Pair(A[i-1] + B[j], i-1, j));
                set.add((i-1) + "," + j);
            }

            if(j - 1 >= 0 && !set.contains(i + "," + (j-1))) {
                pq.add(new Pair(A[i] + B[j-1], i, j-1));
                set.add(i + "," + (j-1));
            }
        }

        return res;
    }
}

295. Find Median from Data Stream
Hard
Topics
Companies
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 


class MedianFinder {
	List<Integer> list;
    public MedianFinder() {
        Prior = new ArrayList<>();
    }
    
    public void addNum(int num) {
        list.add(num)
    }
    
    public double findMedian() {
        
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

1
2
3



next greater element

Input: nums1 = [4,1,2], nums2 = [1,3,4,2]

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
    }
}


2
4
3
1


int isBalanced = true;
public boolean isqds{
	checkBalanced(root);
	return isBalanced;
}

public int checkBalanced(TreeNode root){
	if(root==null)return 0;
	int lh = checkBalanced(root.left);
	int rh = checkBalanced(root.right);
	if(lh>rh && (lh-rh)>1)isBalanced = false;
	if(rh>lh && (rh-lh)>1)isBalanced = false;
	return 1+Math.max(lh,rh);
}

boolean foundP = false;
boolean foundQ = false;
public void lca(TreeNode root, int p, int q){
	if(root==null)return;
	if(root.val==p){
	foundP=true;
	}
	if(root.val==q){
	foundQ=true;
	}
	lca(root.left,p,q);
	lca(root.right,p,q);
	if(foundP && foundQ)result = root;
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    TreeNode res = null;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca(root, p, q, false, false);
        return res;
    }

    public boolean lca(TreeNode root, TreeNode p, TreeNode q, boolean foundP, boolean foundQ) {
        if (root == null)
            return false;
        if (root.val == p.val) {
            return true;
        }
        if (root.val == q.val) {
            return true;
        }
        boolean left = lca(root.left, p, q);
        boolean right = lca(root.right, p, q);
        if(root.val==p.val || root.val==q.val){
        	if(left||right){
        	res = root;
        	}
        	return true;
        }
        else{
        	if(left&&right){
        	res=root;
        	}
        	return true;
            }
        return false;
        }
}


populateNxtPointer(TreeNode root){
	

	Queue<TreeNode> queue = new LinkedList<>();
	queue.add(root);
	while(!queue.isEmpty()){

		int size = queue.size();
		for(int i = 0; i < size; i++){
			TreeNode popped = queue.remove();
			if(!queue.isEmpty()){
			popped.next = queue.front();
			}
			else popped.next = null;
			if(popped.left!=null){
			queue.add(popped.left);
			}
			if(popped.right!=null){
			queue.add(popped.right);
			}
			}
		}
	} 
}