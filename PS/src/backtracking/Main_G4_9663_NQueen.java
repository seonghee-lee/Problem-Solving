package backtracking;

import java.util.*;

public class Main_G4_9663_NQueen {
	static int N, ans;
	static int[] col;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		col = new int[N];	//col[i]: i행에 놓인 queen의 열
		
		queen(0);
		
		System.out.println(ans);
	}
	
	private static void queen(int depth) {
		
		if(depth == N) {	//N개의 queen을 모두 배치했다면,
			ans++;
			return;
		}
		
		for(int i = 0; i<N; i++) {	//depth행 i열에 queen을 배치해본다.
			col[depth] = i;
			
			if(promising(depth)) {	//i열에 배치가 가능하다면,
				queen(depth + 1);	//배치를 확정한다.
			}
		}
	}
	
	private static boolean promising(int cur) {

		//같은 열에 퀸이 있는 지 확인한다.
		for(int i = 0; i<cur; i++) {
			if(col[i] == col[cur]) return false;
		}
		
		//대각선에 퀸이 있는 지 확인한다. (행-행) == (열-열) 이면 대각선에 배치됨
		for(int i = 0; i<cur; i++) {
			if((cur - i) == Math.abs(col[cur] - col[i])) return false;
		}
		
		return true;
	}
}
