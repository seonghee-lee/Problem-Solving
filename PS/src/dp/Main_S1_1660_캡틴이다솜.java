package dp;

import java.util.*;

public class Main_S1_1660_캡틴이다솜 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();		//총 대포 수 
		
		int[] arr1 = new int[1001];
		int[] arr2 = new int[1001];	//사면체 크기에 따른 사면체에 들어가는 대포 수
		
		for(int i = 1; i<=1000; i++) {
			arr1[i] = arr1[i-1] + i;
			arr2[i] = arr2[i-1] + arr1[i];
		}
		
		int[] dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 1; i<=N; i++) {	//i: 대포알 수
			for(int j = 1; j<=1000; j++) {
				if(i < arr2[j]) break;
				dp[i] = Math.min(dp[i], dp[i - arr2[j]] + 1);
			}
		}
		System.out.print(dp[N]);
	}
}
