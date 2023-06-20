package twopointer;

import java.util.*;
import java.io.*;

/*
 * 아이디어 못 낸 문제..
 * 투포인터라고 탐색에 갇혀서 생각하지 말고, 그리디적인 사고방식도 항상 고려해보자...
 * */

public class Main_G4_22945_팀빌딩 {
	static int[] dev;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dev = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			dev[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = Integer.MIN_VALUE;
		int left = 0;
		int right = N - 1;
		int cur = 0;
		
		while(left < right) {
			cur = (right - left - 1) * Math.min(dev[left], dev[right]);
			max = Math.max(max, cur);
			if(dev[left] < dev[right]) {	//목표는 최댓값을 구하는 건데, right를 움직여서는 값이 변하지 않으니까
				left++;
			} else right--;		//left를 움직여서는 값이 변하지 않으니까.
		}
		
		System.out.println(max);
	}
	
	

}
