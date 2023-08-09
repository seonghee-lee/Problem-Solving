package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_14567_선수과목 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		List<Course> list = new ArrayList<>();
		for(int i = 0; i<M; i++) {
			String[] inputs = br.readLine().split(" ");
			int pre = Integer.parseInt(inputs[0]);
			int post = Integer.parseInt(inputs[1]);
			list.add(new Course(pre, post));
		}
		
		Collections.sort(list);
		
		
		int[] dp = new int[N+1];	//dp[i]: i과목을 수강하는 학기
		Arrays.fill(dp, 1);
		
		for(Course c : list) {
			if(dp[c.pre] >= dp[c.post]) {
				dp[c.post] = dp[c.pre] + 1;
			} 
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<=N; i++) {
			sb.append(dp[i]).append(" ");
		}
		
		System.out.print(sb.toString().trim());
	}
}

class Course implements Comparable<Course> {
	int pre, post;
	Course(int pre, int post){
		this.pre = pre;
		this.post = post;
	}
	
	public int compareTo(Course o) {
		if(this.pre == o.pre) {
			return this.post - o.post;
		} else {
			return this.pre - o.pre;
		}
	}
}
