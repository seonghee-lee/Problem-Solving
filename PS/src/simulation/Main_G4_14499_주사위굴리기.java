package simulation;

import java.util.*;
import java.io.*;

/**
 * 주사위를 굴린다 => 칸 이동 + 주사위 방향(주사위의 인덱스) 변환
 */

public class Main_G4_14499_주사위굴리기 {
	static int N, M, r, c, K;
	static int[][] map;
	static int[] dice = new int[7];	//dice[i]: i면에 쓰여있는 수
	static int[] dr = {0, 0, 0, -1, 1};	//동, 서, 북, 남
	static int[] dc = {0, 1, -1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		map = new int[N][M];
		r = Integer.parseInt(inputs[2]);
		c = Integer.parseInt(inputs[3]);
		K = Integer.parseInt(inputs[4]);
		
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<K; i++) {
			int order = Integer.parseInt(st.nextToken());
			
			int nr = r + dr[order];
			int nc = c + dc[order];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			
			rolling(order);
			
			if(map[nr][nc] == 0) {
				//주사위의 바닥면에 쓰여있는 수가 맵에 복사된다.
				map[nr][nc] = dice[6];
			} else {
				//맵에 쓰여있는 수가 주사위의 바닥면으로 복사된다. 맵의 수는 0이 된다.
				dice[6] = map[nr][nc];
				map[nr][nc] = 0;
			}
			
			r = nr;
			c = nc;
			
			sb.append(dice[1]).append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static void rolling(int order) {
		int temp = dice[1];
		switch(order) {
		case 1:
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = temp;
			break;
		case 2:
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = temp;
			break;
		case 3:
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = temp;
			break;
		case 4:
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = temp;
			break;
		}
	}
	
}
