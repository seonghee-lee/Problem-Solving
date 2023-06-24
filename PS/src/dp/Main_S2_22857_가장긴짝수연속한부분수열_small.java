package dp;

import java.util.*;
import java.io.*;

public class Main_S2_22857_가장긴짝수연속한부분수열_small {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		int N = Integer.parseInt(nk[0]);
		int K = Integer.parseInt(nk[1]);
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] oddCount = new int[N+1];
		oddCount[0] = 0;
		for(int i = 1; i<=N; i++) {
			if(arr[i] % 2 == 0) {
				oddCount[i] = oddCount[i-1];
			} else {
				oddCount[i] = oddCount[i-1] + 1;
			}
		}
		
		int start = 1;
		int end = 1;
		int max = 0;
		int count = 0;		//수열 안에 있는 홀수의 개수
		while(start <= end && end <= N) {
			
			if(start > 1) {
				if(arr[start] % 2 == 0) {
					count = oddCount[end] - oddCount[start];
				}else {
					count = oddCount[end] - oddCount[start] + 1;
				}
			} else {
				count = oddCount[end];
			}
			
			if(count > K) {
				start++;
			} else {
				int len = end - start + 1;	//전체 수열 길이
				max = Math.max(max, len - count);	//짝수 연속 부분 수열 길이 최댓값 구하기
				end++;
			}
		}
		System.out.println(max);
	}

}
