package binarysearch;

import java.util.*;
import java.io.*;

/**
 * 내가 헷갈렸던 부분: 값이 1개이면 max - mid = 0이다.
 * mid를 왜 구하는 지 이유가 명확해야 함..
 *-> mid를 정했을 때 주어진 조건(M)을 만족하는 지 판별 : 파라매트릭 서치
 *-> 만족하면서도 mid의 최솟값을 찾음. 
 */

public class Main_G4_13397_구간나누기2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		int left = 0;
		int right = 10001;
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] < left) left = arr[i];
			if(arr[i] > right) right = arr[i];
		}
		
		int mid = 0;
		int ans = 0;
		
		while(left <= right) {
			
			mid = (left + right) / 2;	//mid: 구간의 점수의 최댓값
			int count = 0;		//count: 구간의 개수
			
			int min = -1; int max = 100001;
			
			for(int i = 0; i<N; i++) {
				min = Math.min(min, arr[i]);
				max = Math.max(max, arr[i]);
				
				if((max - min) > mid){
					count++;
					min = arr[i];
					max = arr[i];
				}
			}
			
			if(count <= M) {
				right = mid - 1;
				ans = mid;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.print(ans);
		
	}

}
