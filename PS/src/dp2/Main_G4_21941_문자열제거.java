package dp2;

import java.util.*;
import java.io.*;

public class Main_G4_21941_문자열제거 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int M = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new HashMap<>();
		
		for(int i = 0; i<M; i++) {
			String[] inputs = br.readLine().split(" ");
			map.put(inputs[0], Integer.parseInt(inputs[1]));
		}
		
		int N = str.length();
		int[] dp = new int[N+1];
		
		for(int i = 1; i<=N; i++) {
			dp[i] = dp[i-1] + 1;
			String cur = str.substring(0, i);
			
			if(map.containsKey(cur)) dp[i] = Math.max(dp[i], map.get(cur));
			
			for(int j = i-1; j>0; j--) {
				cur = str.substring(j, i);
				if(!map.containsKey(cur)) continue;
				dp[i] = Math.max(dp[i], map.get(cur) + dp[j]);
			}
		}
		System.out.println(dp[N]);
	}
}
