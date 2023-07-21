package dp;

import java.util.*;
import java.io.*;

public class Main_G5_15486_퇴사2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] time = new int[N+1];
		int[] price = new int[N+1];
		
		for(int i = 1; i<=N; i++) {
			String[] inputs = br.readLine().split(" ");
			time[i] = Integer.parseInt(inputs[0]);
			price[i] = Integer.parseInt(inputs[1]);
		}
		
		int[] dp = new int[N+2];	//dp[i] : i~N 일까지 얻을 수 있는 최대 이익
		
		for(int i = N; i>0; i--) {
			int next = i + time[i];
			if(next > N + 1) {
				dp[i] = dp[i+1];	//i를 선택했을 때 날짜가 넘어가는 경우에는, 다음 날짜의 최대 이익을 그대로 사용 : 어차피 dp배열은 범위 안에서의 최대를 의미하니까.
				continue;
			}
			
			dp[i] = Math.max(price[i] + dp[next], dp[i+1]);	//i날짜를 선택하거나, 선택하지 않거나
		}

		int max = Integer.MIN_VALUE;
		for(int i = 1; i<=N; i++) {
			if(dp[i] > max) max = dp[i];
		}
		
		System.out.print(max);
	}

}
