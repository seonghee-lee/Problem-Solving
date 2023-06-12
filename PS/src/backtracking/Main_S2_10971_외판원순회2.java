package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [오답노트]
 * - w[i][j] 가 0일 경우 갈 수 없는데, 이거에 대한 처리를 안해줬다.
 *   => 그래서 처리를 해줬다고 생각했는데,
 *      또 틀림 => 마지막 부분에서 다시 해줘야 되는데 또 안해줬다.
 * --> 결론 : 문제 조건을 꼼꼼하게 읽고, 해당되는 부분을 잘 파악해서 코드를 수정하자...
 *
 */
public class Main_S2_10971_외판원순회2 {
	static int N, min;
	static int[][] map;
	static int[] numbers;
	static boolean[] selected;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		numbers = new int[N];
		selected = new boolean[N];
		StringTokenizer st;
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		min = Integer.MAX_VALUE;
		go(0, 0, 0, 0);
		System.out.println(min);
	}

	private static void go(int count, int prev, int sum, int start) {
		
		if(sum > min) return;
		
		if(count == N) {
			if(map[prev][start] != 0) {
				sum += map[prev][start];
				min = Math.min(min, sum);	
			}
			return;
		}
		
		for(int i = 0; i<N; i++) {
			if(selected[i] || (count != 0 && map[prev][i] == 0)) continue;
			
			if(count == 0) start = i;
			
			int s = map[prev][i];
			selected[i] = true;
			go(count+1, i, sum + s, start);
			selected[i] = false;
			
		}
	}

}
