package twopointer;

import java.util.*;
import java.io.*;

public class Main_G5_1484_다이어트 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		List<Integer> result = new ArrayList<>();
		
		int left = 0;
		int right = 1;
		
		int[] arr = new int[G];
		for(int i = 0; i< G; i++) {
			arr[i] = i + 1;		//1 2 3 4 ... G
		}
		
		long cur = 0;
		while(left <= right && right < G) {
			
			cur = (arr[right] * arr[right]) - (arr[left] * arr[left]);
			
			if(cur == G) {
				result.add(arr[right]);
			}
			
			if(cur > G) {
				left++;
			} else right++;
		}
		
		Collections.sort(result);
		StringBuilder sb = new StringBuilder(10000);
		
		if(result.size() == 0) System.out.println(-1);
		else {
			for(Integer i: result) {
				sb.append(i).append("\n");
			}
			System.out.println(sb);
		}
	}
}


