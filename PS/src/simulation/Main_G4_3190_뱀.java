package simulation;

import java.util.*;
import java.io.*;

public class Main_G4_3190_뱀 {
	static int N, K, L;
	static boolean[][] map, apple;
	static char[] turn = new char[10001];
	static int[] dr = {0, 1, 0, -1};	//우, 하, 좌, 상
	static int[] dc = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new boolean[N+1][N+1];
		apple = new boolean[N+1][N+1];
		for(int i = 0; i<K; i++) {
			String[] pos = br.readLine().split(" ");
			int r = Integer.parseInt(pos[0]);
			int c = Integer.parseInt(pos[1]);
			apple[r][c] = true;
		}
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i<L; i++) {
			String[] input = br.readLine().split(" ");
			int t = Integer.parseInt(input[0]);
			char dir = input[1].charAt(0);
			turn[t] = dir;
		}
		
		map[1][1] = true;
		int dir = 0;
		int time = 0;
		int headR = 1; int headC = 1;
		int tailR = 1; int tailC = 1;
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		while(true) {
			time++;
			//몸길이를 늘려 머리를 다음칸에 위치시킨다.
			int nheadR = headR + dr[dir];
			int nheadC = headC + dc[dir];
			
			//만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
			if(nheadR <= 0 || nheadR > N || nheadC <= 0 || nheadC > N || map[nheadR][nheadC]) break;
			headR = nheadR;
			headC = nheadC;
			map[headR][headC] = true;
			queue.offer(new int[] {headR, headC});
			
			//만약 이동한 칸에 사과가 있다면
			if(apple[headR][headC]) {
				//그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
				apple[headR][headC] = false;
			} else {	//만약 이동한 칸에 사과가 없다면
				//몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
				map[tailR][tailC] = false;
				int[] newTail = queue.poll();
				tailR = newTail[0];
				tailC = newTail[1];
			}
			
			//회전
			if(turn[time] == 'L') {
				dir -= 1;
				if(dir == -1) dir = 3;
			} else if(turn[time] == 'D') {
				dir += 1;
				if(dir == 4) dir = 0;
			}
		}
		System.out.println(time);
	}
}
