There is a map with n segments. Every segment on the map can either be a red segment or a black segment. There is no circle(closed loop) in the map. Initially, all segments on the map are red segments. You need to perform m operations on the map, and there are two types of operations:

Given two stations a and b. First, for every station x on the path from a to b (including a and b), you should change all segments connected to x into red segments. Then, convert all the segments on the path from a to b into black segments.
Given two stations a and b, you need to calculate how many black segments there are on the path from a to b.
Input Format:

The input is an array of string.
The first string contains two integers n and m, where n represents the number of stations and m represents the number of operations.
The next n - 1 strings, each string contains two integers u and v, indicating there is a segment between station u and station v.
The next m strings, each string contains integers opi, ai and bi. opi represents an operation: opi=1 means the first operation, and opi=2 means the second operation. ai is not equal to bi.
Output Format:

The output is an array of integer. For every second type of operation, output an integer as the answer.

Example 1:

Input: operations = ["8 5", "1 2", "1 3", "3 4", "4 5", "4 6", "2 8", "2 9", "1 1 6", "1 2 4", "2 1 6", "1 1 5", "2 2 6"]
Output: [2, 2]
Explanation:

      

        For the first operation (1 1 6), the path from station 1 to station 6 is changed to black segments.
        For the second operation (1 2 4), the path from station 2 to station 4 is changed to black segments.
        For the third operation (2 1 6), the number of black segments on the path from station 1 to station 6 is 2.
        For the fourth operation (1 1 5), the path from station 1 to station 5 is changed to black segments.
        For the fifth operation (2 2 6), the number of black segments on the path from station 2 to station 6 is 2.

complete the function below:


public int[] changeSegmentColors(String[] operations) {
  // write your code here
}
