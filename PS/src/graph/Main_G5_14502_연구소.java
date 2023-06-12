package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 안전영역이 최대가 되도록 벽 3개를 세우는 문제 그래프를 탐색하면서 0인 점을 만나면 벽을 세운다. 세운 벽의 개수가 3개가 되면
 * 바이러스를 퍼트려본다. 안전영역(0으로 남아있는 영역)의 크기를 구한다. (2차원배열에서 단순히 세기) 퍼트린 바이러스를 원상복구 한다.
 * 세운 벽을 원상복구한다.
 */
public class Main_G5_14502_연구소 {
	static int N, M, max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		max = 0;

		go(0);

		System.out.println(max);
	}

	private static void go(int cnt) {
		if (cnt == 3) {
			int count = 0;

			int[][] original = new int[N][M]; // 바이러스 퍼트리기 전 맵
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					original[i][j] = map[i][j];
				}
			}

			// 바이러스 퍼트리기
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 2 && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}

			// 안전영역 세기
			count = countSafe();

			// 맵 되돌리기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = original[i][j];
				}
			}

			max = Math.max(max, count);

			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1; // 벽을 세운다.
					go(cnt + 1);
					map[i][j] = 0; // 원상복구
				}
			}
		}
	}

	private static int countSafe() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					count++;
			}
		}
		return count;
	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { r, c });
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			int nr, nc;
			for (int i = 0; i < 4; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M 
						|| visited[nr][nc] || map[nr][nc] >= 1)
					continue;

				map[nr][nc] = 2;
				queue.offer(new int[] { nr, nc });
				visited[nr][nc] = true;
			}
		}
	}
}
