package dp2;

import java.util.*;
import java.io.*;
/**
 * 증가하는 최대 길이의 수열은 고정되어 있고,
 * 나머지것들을 움직이면, 그게 가장 적게 움직이는 방법이다.
 */
public class Main_G4_2631_줄세우기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N+1];	//dp[i] : i까지 왔을 때 증가하는 수열의 최대 길이(LIS길이)
		
		for(int i = 1; i<=N; i++) {
			dp[i] = 1;	//자기 자신으로 무조건 길이1인 lis를 만들 수 있다.
			for(int j = 1; j<i; j++) {	//현재보다 앞쪽을 보며 제일 긴 lis 길이를 만든다.
				if(arr[j] < arr[i]) {	//현재 배열값보다 작다면(lis를 이전 원소로 만들 수 있다면)
					dp[i] = Math.max(dp[i], dp[j] + 1);	//만들어보고 그 중 가장 max값을 취한다.
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 1; i<=N; i++) {
			if(dp[i] > max) max = dp[i];
		}
		
		System.out.println(N - max);
	}
}
