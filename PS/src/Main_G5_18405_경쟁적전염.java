import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_18405_경쟁적전염 {
	static int N, K, S, X, Y, time;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static Queue<Pos> queue = new ArrayDeque<>();
	static List<Pos> list = new ArrayList<>();
	
	static class Pos implements Comparable<Pos>{
		int r, c, val;
		public Pos(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
		@Override
		public int compareTo(Pos o) {
			return this.val - o.val;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) {
					list.add(new Pos(i, j, map[i][j]));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		Collections.sort(list);
		for(Pos p : list) {
			queue.offer(p);
		}
		list.clear();
		
		bfs(0);
		System.out.println(map[X-1][Y-1]);
	}

	private static void bfs(int start) {
		while(!queue.isEmpty()) {
			if(time == S) {		//S는 0초부터 시작함
				break;
			}
			for(int q = 0, size = queue.size(); q < size; q++) {
				Pos cur = queue.poll();
				int nr, nc;
				for(int i = 0; i<4; i++) {
					nr = cur.r + dr[i];
					nc = cur.c + dc[i];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] > 0) continue;
					map[nr][nc] = cur.val;
					list.add(new Pos(nr, nc, cur.val));
				}
			}
			time++;
			
			Collections.sort(list);
			for(Pos p : list) {
				queue.offer(p);
			}
			list.clear();
		}
		return;
	}
}
