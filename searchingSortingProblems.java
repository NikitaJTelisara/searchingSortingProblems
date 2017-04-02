import com.sun.org.apache.xpath.internal.FoundIndex;
import com.sun.scenario.effect.Merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.SortedMap;

public class searchingSortingProblems {

    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 6, 7, 8, 9};
        int k = 7;
        int result = binarySearchIterative(arr, k);
        System.out.println(k + " is at index " + result);

        result = binarySearchRecursion(arr, k, 0, arr.length - 1);
        System.out.println(k + " is at index " + result);

        int[] arrA = new int[8];
        arrA[0] = 2;
        arrA[1] = 6;
        arrA[2] = 9;
        arrA[3] = 11;
        int[] arrB = {1, 3, 8, 9};
        merge2SortedArr(arrA, arrB, 3);

        System.out.println("Merge SortedMap Arrays");
        for (int r : arrA) {
            System.out.println(r);
        }

        System.out.println("Print anagrams in groups");
        String[] arr1 = {"abc", "def", "gh", "hg", "eft", "fed", "cab"};
        String[] result1 = sortArrayOfAnagram(arr1);
        for (String r : result1) {
            System.out.println(r);
        }

        /* rotated arr */
        int[] arr11 = {6, 8, 10, 11, 2, 3};
        int re = findElementInRotatedArr(arr11, 3);
        System.out.println(3 + " is at index " + re);

        int[][] m = {{15, 20, 43, 45},
                {20, 35, 80, 99},
                {30, 55, 95, 105},
                {40, 80, 100, 125},};

        Point p = getIndexOfElement(m,100);
        System.out.println("Element is at x: "+ p.x +" y :"+p.y);
        System.out.println(m[p.x][p.y]);

        /* 11.8 Imagine you are reading in a stream of integers. Periodically, you wish to be able to
         look up the rank of a number x (the number of values less than or equal to x). Implement
         the data structures and algorithms to support these operations. That is, implement
         the method track(int x), which is called when each number is generated,
         and the methodgetRankOf'Number (int x), which returns the number of values
         less than or equal to x (not including x itself).*/

         RankNode n = new RankNode(20);
         n.insert(15);
         n.insert(25);
         n.insert(23);
         n.insert(24);
         n.insert(11);

         System.out.print("Rank of 24 "+n.getRank(24));
    }

    /* get index of k using iterative binarySearch */
    public static int binarySearchIterative(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;

        while (high >= low) {
            int mid = low + (high - low);
            if (arr[mid] == k) {
                return mid;
            } else if (arr[mid] > k) {
                high = mid - 1;
            } else if (arr[mid] < k) {
                low = mid + 1;
            }
        }
        return -1; // not found
    }

    /* get index of k using recursive binarySearch */
    public static int binarySearchRecursion(int[] arr, int k, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == k) {
                return mid;
            }
            if (arr[mid] > k) {
                return binarySearchRecursion(arr, k, low, mid - 1);
            }
            if (arr[mid] < k) {
                return binarySearchRecursion(arr, k, mid + 1, high);
            }
        }
        return -1;
    }

    /* 11.1 You are given two sorted arrays, A and B, where A has a large enough buffer at
the end to hold B. Write a method to merge B into A in sorted order.  */

    public static void merge2SortedArr(int[] arr1, int[] arr2, int lastIndexA) {
        int lastIndexB = arr2.length - 1;
        int maxIndexA = lastIndexA + lastIndexB + 1;

        while (lastIndexA >= 0 && lastIndexB >= 0) {
            if (arr1[lastIndexA] > arr2[lastIndexB]) {
                arr1[maxIndexA] = arr1[lastIndexA];
                lastIndexA--;

            } else {
                arr1[maxIndexA] = arr2[lastIndexB];
                lastIndexB--;
            }
            maxIndexA--;
        }
        if (lastIndexB >= 0) {
            arr1[maxIndexA] = arr2[lastIndexB];
            lastIndexB--;
            maxIndexA--;
        }
    }

    /* 11.2 Write a method to sort an array of strings so that all the anagrams are next to each other.*/

    public static String[] sortArrayOfAnagram(String[] arr) {
        String[] result = new String[arr.length];
        int j = 0;
        Hashtable<String, ArrayList<String>> table = new Hashtable<String, ArrayList<String>>();
        for (String str : arr) {
            String tempArr = sort(str);
            if (!table.containsKey(tempArr)) {
                ArrayList list = new ArrayList();
                list.add(str);
                table.put(tempArr, list);
            } else {
                ArrayList list = table.get(tempArr);
                list.add(str);
            }
        }

        for (String a : table.keySet()) {
            ArrayList<String> list = table.get(a);
            for (int i = 0; i < list.size(); i++) {
                result[j] = list.get(i);
                j++;
            }
        }
        return result;
    }

    public static String sort(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return (new String(a));
    }

    /* 11.3 Given a sorted array of n integers that has been rotated an unknown number of
           times, write code to find an element in the array. You may assume that the array was
           originally sorted in increasing order.*/

    public static int findElementInRotatedArr(int[] arr, int n) {
        int pivotIndex = 0;
        /* get dividing point */
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[i + 1]) {
                pivotIndex = i;
                break;
            }
        }

        if (n > arr[arr.length - 1]) {
            int high = pivotIndex;
            int low = 0;
            while (high >= low) {
                int mid = low + (high - low);
                if (arr[mid] == n) {
                    return mid;
                } else if (arr[mid] > n) {
                    high = mid - 1;
                } else if (arr[mid] < n) {
                    low = mid + 1;
                }
            }
        }

        if (n <= arr[arr.length - 1]) {
            int low = pivotIndex + 1;
            int high = arr.length - 1;
            while (high >= low) {
                int mid = low + (high - low);
                if (arr[mid] == n) {
                    return mid;
                } else if (arr[mid] > n) {
                    high = mid - 1;
                } else if (arr[mid] < n) {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /* 11.5 Given a sorted array of strings which is interspersed with empty strings, write a
method to find the location of a given string.


    public static int findIndexOfStr(String[] arr, int low, int high, String searchStr) {
        int mid = low + (high - low);
        if (arr[mid].isEmpty()) {
            if (arr[mid] == searchStr) {
                return mid;
            }
            while ((arr[mid].isEmpty())) {
                mid++;
            }
            if(arr[mid]searchStr){

            }

        }
    }

    */

    /* 11.6 Given an MX N matrix in which each row and each column is sorted in ascending
order, write a method to find an element. */
    public static Point getIndexOfElement(int[][] m, int n) {
        for (int i = 0; i < m.length; i++) {
            System.out.println(m[i][0]);
            System.out.println(m[i][m[0].length-1]);
            if (n >= m[i][0] || n <= m[i][m[0].length-1]) {
                int low = 0;
                int high = m[0].length - 1;
                while (high >= low) {
                    int mid = low + (high - low) / 2;
                    if (m[i][mid] == n) {
                        Point p = new Point(i,mid);
                        return p;
                    } else if (m[i][mid] > n) {
                        high = mid - 1;
                    } else if (m[i][mid] < n) {
                        low = mid + 1;
                    }
                }
            }
        }
        return null;
    }
}

