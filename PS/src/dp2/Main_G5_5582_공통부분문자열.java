package dp2;
/**
 * [LCS 참고자료]
 * https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence
 */
import java.util.*;
import java.io.*;

public class Main_G5_5582_공통부분문자열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		
		int N = s1.length;
		int M = s2.length;
		
		int[][] dp = new int[N+1][M+1];
		char letter1 = ' ';
		char letter2 = ' ';
		int max = 0;
		
		for(int i = 1; i<=N; i++) {
			letter1 = s1[i-1];
			for(int j = 1; j<=M; j++) {
				letter2 = s2[j-1];
				if(letter1 == letter2) {
					dp[i][j] = dp[i-1][j-1] + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		System.out.print(max);
	}

}
