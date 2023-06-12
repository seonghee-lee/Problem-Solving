package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S3_19949_영재의시험 {
	static int[] answer, yj;
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		answer = new int[10];
		yj = new int[10];
		
		for(int i = 0; i<10; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}
		
		getAnswer(0, 0, 0);
		System.out.println(count);
	}
	private static void getAnswer(int cnt, int cur, int continuous) {
		if(continuous == 3) return;
		if(cnt == 10) {
			int score = 0;
			for(int i = 0; i<10; i++) {
				if(answer[i] == yj[i]) score++;
			}
			if(score >= 5) count++;
			return;
		}
		for(int i = 1; i<=5; i++) {
			yj[cnt] = i;
			if(cur == i) {
				getAnswer(cnt+1, i, continuous+1);
			}else {
				getAnswer(cnt+1, i, 1);
			}
		}
	}

}
