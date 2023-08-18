package graph;

import java.util.*;
import java.io.*;
/*
 * bfs 기본: "방문처리" 까먹지말기!
 * 문제 의도는 다익스트라로 푸는 게 맞다만, 방문 처리 순서만 잘 해주면 우연히 해결할 수 있는 문제.
 * *2, -1, +1 순서로 방문해야 건너뜀 없이 순수한 최솟값을 구할 수 있음
 * 어떤 시점에 최솟값이 나올 지 알 수 없기 때문에 queue는 무조건 빌 때까지 끝까지 돌아줘야 한다.
 * */
public class Main_G5_13549_숨바꼭질3 {
	static int N, K, min;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);
		visited = new boolean[100001];
		min = Integer.MAX_VALUE;
		
		go(); // bfs 시작
		System.out.println(min);
	}

	private static void go() {
		Queue<mmyPos> queue = new ArrayDeque<>();
		queue.offer(new mmyPos(N, 0));
		visited[N] = true;

		while (!queue.isEmpty()) {
			mmyPos cur = queue.poll();

			if (cur.x == K) {
				min = Math.min(min, cur.time);
			}

			if (cur.x * 2 <= 100000 && !visited[cur.x * 2]) {
				queue.offer(new mmyPos(cur.x * 2, cur.time));
				visited[cur.x * 2] = true;
			}
			
			if (cur.x - 1 >= 0 && !visited[cur.x - 1]) {
				queue.offer(new mmyPos(cur.x - 1, cur.time + 1));
				visited[cur.x - 1] = true;
			}

			if (cur.x + 1 <= 100000 && !visited[cur.x + 1]) {
				queue.offer(new mmyPos(cur.x + 1, cur.time + 1));
				visited[cur.x + 1] = true;
			}
		}
	}

}

class mmyPos {
	int x;
	int time;

	mmyPos(int x, int time) {
		this.x = x;
		this.time = time;
	}

}
