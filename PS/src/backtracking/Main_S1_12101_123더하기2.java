package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main_S1_12101_123더하기2 {
	static int N, K;
	static int[] used;
	static List<String> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		used = new int[N];
		go(0, N);
		
		Collections.sort(list);
		if(K-1 >= list.size()) {
			System.out.println("-1");
		}else {
			String ans = list.get(K-1);
			ans = ans.substring(0, ans.length()-1);
			System.out.println(ans);
		}
	}
	
	private static void go(int cnt, int sum) {
		
		if(sum == 0) {
			StringBuilder sb = new StringBuilder(100);
			for(int i = 0, len = used.length; i<len; i++) {
				if(used[i] != 0) {
					sb.append(used[i]).append("+");
				}
			}
			list.add(sb.toString());
			return;
		}
		
		for(int i = 3; i>0; i--) {
			if(sum - i < 0) continue;
			used[cnt] = i;
			go(cnt+1, sum - i);
			used[cnt] = 0;
		}
	}
}
