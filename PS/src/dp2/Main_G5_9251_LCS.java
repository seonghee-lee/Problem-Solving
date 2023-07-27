package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_9251_LCS {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int N = str1.length();
		int M = str2.length();
		
		char[] arr1 = new char[N + 1];
		char[] arr2 = new char[M + 1];
		
		
		for(int i = 1; i<=N; i++) {
			arr1[i] = str1.charAt(i-1);
		}
		
		for(int i = 1; i<=M; i++) {
			arr2[i] = str2.charAt(i-1);
		}
		
		
		int[][] dp = new int[M+1][N+1];	//dp[i][j] : arr1[j] 까지의 문자열에서 arr2[i] 까지의 문자열을 고려했을 때 LCS 길이
		
		for(int i = 1; i<=M; i++) {		//arr2의 문자열 글자를 하나씩 늘려나간다.
			for(int j = 1; j<=N; j++) {	//arr1의 문자열 글자를 하나씩 늘려나간다.
				if(arr2[i] != arr1[j]) {		//arr1의 문자와 arr2의 문자가 다르면
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);	//arr1에서의 최댓값과 arr2에서 이전까지의 최댓값 중 최댓값 선택
				} else {		//arr1의 문자와 arr2의 문자가 같다면,
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1] + 1);	//이 문자를 선택하지 않았을 때, 선택했을 때 중 최댓값 선택
				}
			}
		}
		
		System.out.print(dp[M][N]);		//두 문자열의 끝까지 비교했을 때의 LCS 길이의 최댓값
	}
	

}
