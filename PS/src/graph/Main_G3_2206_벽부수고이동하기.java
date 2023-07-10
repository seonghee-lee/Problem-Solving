package graph;

import java.util.*;
import java.io.*;

public class Main_G3_2206_벽부수고이동하기 {
	static int N, M, ans;
	static boolean possible;
	static int[][] map;
	static boolean[][][] visited; // visited[i][j][k] k: 벽을 부수면서 왔을 때(1), 부수지 않고 왔을 때(0)
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] inputs = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}

		visited = new boolean[N][M][2];
		bfs();

		if (possible)
			System.out.print(ans + 1);
		else
			System.out.print(-1);
	}

	private static void bfs() {
		Queue<Move> queue = new ArrayDeque<>();
		queue.offer(new Move(0, 0, 0, false));
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Move cur = queue.poll();

			if (cur.r == N - 1 && cur.c == M - 1) {
				ans = cur.dist;
				possible = true;
				return;
			}

			int nr, nc;
			for (int i = 0; i < 4; i++) {
				nr = cur.r + dr[i];
				nc = cur.c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (cur.crash && visited[nr][nc][1])
					continue;
				if (!cur.crash && visited[nr][nc][0])
					continue;

				if (map[nr][nc] == 1) {
					if (cur.crash)
						continue;

					queue.offer(new Move(nr, nc, cur.dist + 1, true));
					visited[nr][nc][1] = true;
				} else {
					queue.offer(new Move(nr, nc, cur.dist + 1, cur.crash));

					int k = cur.crash ? 1 : 0;

					visited[nr][nc][k] = true;
				}
			}
		}
	}
}

class Move {
	int r, c, dist;
	boolean crash;

	Move(int r, int c, int dist, boolean crash) {
		this.r = r;
		this.c = c;
		this.dist = dist;
		this.crash = crash;
	}
}
