2327. Number of People Aware of a Secret

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Hint
On day 1, one person discovers a secret.

You are given an integer delay, which means that each person will share the secret with a new person every day, starting from delay days after discovering the secret. You are also given an integer forget, which means that each person will forget the secret forget days after discovering it. A person cannot share the secret on the same day they forgot it, or on any day afterwards.

Given an integer n, return the number of people who know the secret at the end of day n. Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: n = 6, delay = 2, forget = 4
Output: 5
Explanation:
Day 1: Suppose the first person is named A. (1 person)
Day 2: A is the only person who knows the secret. (1 person)
Day 3: A shares the secret with a new person, B. (2 people)
Day 4: A shares the secret with a new person, C. (3 people)
Day 5: A forgets the secret, and B shares the secret with a new person, D. (3 people)
Day 6: B shares the secret with E, and C shares the secret with F. (5 people)
Example 2:

Input: n = 4, delay = 1, forget = 3
Output: 6
Explanation:
Day 1: The first person is named A. (1 person)
Day 2: A shares the secret with B. (2 people)
Day 3: A and B share the secret with 2 new people, C and D. (4 people)
Day 4: A forgets the secret. B, C, and D share the secret with 3 new people. (6 people)
 

Constraints:

2 <= n <= 1000
1 <= delay < forget <= n

class Solution {
    int people = 0;
    public int peopleAwareOfSecret(int n, int delay, int forget) {
      return solve(1,n,delay,forget);
    }


    public int solve(int day, int remaining, int delay, int forget){
      if(remaining == 0){
      return 0;
      }

      if(day > delay && day <= forget){
        people = 1 + solve(day+1,remaining-1,delay,forget);  
      }else{
        people = solve(day+1,remaining-1,delay,forget);
      }

      return people;
    }
}


1792. Maximum Average Pass Ratio

avatar
Discuss Approach
arrow-up
Medium
Topics
companies icon
Companies
Hint
There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.

You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.

The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.

 

Example 1:

Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
Example 2:

Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485


class Solution {

    class Pair{

      double delta;
      int index;

      Pair(double delta, int index){
        this.delta=delta;
        this.index=index;
      }
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1,p2)->Double.compare(p1.delta,p2.delta));

        for(int i = 0; i < classes.length; i++){
          double currentRatio = classes[i][0]/classes[i][1];
          double newRatio = (classes[i][0]+1)/classes[i][1]+1;
          pq.add(new Pair(newRatio-currentRatio,i));
        }

        while(extraStudents > 0){
          Pair popped = pq.remove();
          double maxDelta =popped.delta;
          int maxIndex = popped.index;

          classes[maxIndex][0]++;
          classes[maxIndex][1]++;

          double currentRatio = classes[maxIndex][0]/classes[maxIndex][1];
          double newRatio = (classes[maxIndex][0]+1)/classes[maxIndex][1]+1;
          pq.add(new Pair(newRatio-currentRatio,i));

          extraStudents--;
        }

        double sumRatios = 0;
        int n = classes.length;
        for(int[] class : classes){
            sumRatio += (class[0]/class[1]);
        }

        return sumRatios/n;
    }
}


1733. Minimum Number of People to Teach

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Hint
On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.

You are given an integer n, an array languages, and an array friendships where:

There are n languages numbered 1 through n,
languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the users u​​​​​​​​​​​i​​​​​ and vi.
You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.

Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.
 

Example 1:

Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
Output: 1
Explanation: You can either teach user 1 the second language or user 2 the first language.
Example 2:

Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
Output: 2
Explanation: Teach the third language to users 1 and 3, yielding two users to teach


class Solution {

    public boolean canCommunicate(int a, int b, int[][] languages){
      Set<Integer> set = new HashSet<>();
      for(int i = 0; i < languages[a-1].length; i++){
        set.add(languages[a-1][i]);
      }

      for(int i = 0; i < languages[b-1].length; i++){
        if(set.contains(languages[b-1][i])){
        return true;
        }
      }

      return false;
    }

    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        List<Integer> sadUsers = new ArrayList<>();

        for(int[] friendship : friendships){
          if(!canCommunicate(friendship[0],friendship[1],languages)){
            if(!sadUsers.contains(friendship[0]){
             sadUsers.add(friendship[0]);
             }

            if(!sadUsers.contains(friendship[1]){
            sadUsers.add(friendShip[1]);
            }
          }
        }
        

        int[] freq = new int[n+1];

        int maxFreq = 0;

        for(int i : sadUsers){
          for(int lang : languages[i-1]){
            freq[lang]++;
            if(freq[lang]>maxFreq){
              maxFreq = freq[lang];
            }
          }
        }

        return sadUsers.length - maxFreq;
    }
}


279. Perfect Squares

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

 

Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 

Constraints:

1 <= n <= 104


class Solution {
    public int numSquares(int n) {
      int[] dp = new int[n+1];
      for(int i = 0; i < dp.length; i++){
        dp[i]=Integer.MAX_VALUE;
      }

      dp[0]=0;
      for(int sum = 1; sum <= n; sum++){
        for(int i = 1; i*i < n; i++){
        dp[sum] = Math.min(dp[i],1 + dp[sum-(i*i)];
      }
      }
      

      return dp[n];
    }
}


622. Design Circular Queue

avatar
Discuss Approach
arrow-up
Medium
Topics
conpanies icon
Companies
Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle, and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.

Implement the MyCircularQueue class:

MyCircularQueue(k) Initializes the object with the size of the queue to be k.
int Front() Gets the front item from the queue. If the queue is empty, return -1.
int Rear() Gets the last item from the queue. If the queue is empty, return -1.
boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
boolean isEmpty() Checks whether the circular queue is empty or not.
boolean isFull() Checks whether the circular queue is full or not.
You must solve the problem without using the built-in queue data structure in your programming language. 

 

Example 1:

Input
["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
Output
[null, true, true, true, false, 3, true, true, true, 4]

Explanation
MyCircularQueue myCircularQueue = new MyCircularQueue(3);
myCircularQueue.enQueue(1); // return True
myCircularQueue.enQueue(2); // return True
myCircularQueue.enQueue(3); // return True
myCircularQueue.enQueue(4); // return False
myCircularQueue.Rear();     // return 3
myCircularQueue.isFull();   // return True
myCircularQueue.deQueue();  // return True
myCircularQueue.enQueue(4); // return True
myCircularQueue.Rear();     // return 4
 

Constraints:

1 <= k <= 1000
0 <= value <= 1000
At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.


class MyCircularQueue {

    int[] circular;
    int front;
    int rear;
    int k;
    int size;
    public MyCircularQueue(int k) {
      circular = new int[k];   
      front = -1;
      rear = -1;
      k = k;
      size = 0;
    }
    
    public boolean enQueue(int value) {

        if(size==k){
        return false;
        }
        
        circular[rear] = value;
        rear++;
        rear = rear%k;
        size++;
        return true;

    }
    
    public boolean deQueue() {
        if(size==0){
          return false;
        }

        front++;
        front = front%k;
        size--;
        return true;
    }
    
    public int Front() {
        if(size==0){
          return -1;
        }
        
        return circular[front];
    }
    
    public int Rear() {
        if(size==0){
        return -1;
        }

        return circular[(rear - 1 + k) % k];;
    }
    
    public boolean isEmpty() {
        return size==0;
    }
    
    public boolean isFull() {
        return size == k;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */