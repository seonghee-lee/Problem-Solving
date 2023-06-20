package twopointer;

import java.util.*;
import java.io.*;

public class Main_G4_16472_고냥이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		
		int[] used = new int[26];
		int usedCnt = 0;
		
		int start = 0;
		int end = 0;
		int max = Integer.MIN_VALUE;
		
		//init
		used[arr[start] - 'a']++;
		usedCnt = 1;
		
		while(end < arr.length) {
			if(usedCnt <= N) {
				end++;
				max = Math.max(max, end - start);
				if(end >= arr.length) break;
				used[arr[end] - 'a']++;
				if(used[arr[end] - 'a'] == 1) usedCnt++;
			} else {
				used[arr[start] - 'a']--;
				if(used[arr[start] - 'a'] == 0) usedCnt--;
				start++;
			}
		}
		
		if(max == Integer.MIN_VALUE) {
			max = 1;
		}
		
		System.out.println(max);
	}

}
