package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * c개 중 l개를 선택한다. 이때 
 * 모음은 최소 1개, 자음은 최소 2개가 반드시 포함되어야 한다.
 * pq에 담아서 정렬되도록 한다.
 *
 */
public class Main_G5_1759_암호만들기 {
	static int L, C;
	static char[] alpha, made;
	static boolean[] visited;
	static Queue<String> pq = new PriorityQueue<String>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		alpha = new char[C];
		made = new char[L];
		visited = new boolean[C];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		
		go(0, 0, 0);
		StringBuilder result = new StringBuilder(100);
		while(!pq.isEmpty()) {
			result.append(pq.poll()).append("\n");
		}
		System.out.println(result);
	}
	
	//cnt: 선택한 알파벳 개수, mCount: 모음 수 , jCount: 자음 수
	private static void go(int cnt, int mCount, int jCount) {
		
		if(cnt == L) {
			if(mCount < 1 || jCount < 2) return;
			
			StringBuilder sb = new StringBuilder(20);
			for(int i = 0; i<L; i++) {
				sb.append(made[i]);
			}
			pq.add(sb.toString());
			return;
		}
		
		for(int i = 0; i<C; i++) {
			if(visited[i]) continue;
			
			if(cnt >= 1 && alpha[i] < made[cnt-1]) continue;
			
			visited[i] = true;
			made[cnt] = alpha[i];
			
			if(isMoum(alpha[i])) {
				go(cnt+1, mCount+1, jCount);
			}else {
				go(cnt+1, mCount, jCount+1);
			}
			
			visited[i] = false;
		}
	}
	
	//모음인지 반환한다.
	private static boolean isMoum(char c) {
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
		return false;
	}

}
