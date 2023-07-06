package graph;

import java.util.*;
import java.io.*;

//이동 또는 "현재 위치에 서 있는 경우"를 어떻게 표현할지

public class Main_G3_16954_움직이는미로탈출 {
	static char[][] map = new char[8][8];
	static boolean[][][] visited = new boolean[8][8][100];	//마지막은 방문한 시간을 의미
	static boolean possible;
	static int[] dr = {-1, 0, 1, 0, -1, 1, 1, -1};
	static int[] dc = {0, 1, 0, -1, -1, -1, 1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i<8; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		bfs(7, 0, 0);
		if(!possible) System.out.print(0);
		else {
			move();
			if(map[0][7] == '#') System.out.print(0);
			else System.out.print(1);
		}
	}
	
	private static void bfs(int startR, int startC, int time) {
		Queue<Ukje> queue = new ArrayDeque<>();
		queue.offer(new Ukje(startR, startC));
		visited[startR][startC][0] = true;
		
		while(!queue.isEmpty()) {
			time++;
			if(time >= 100) break;
			for(int q = 0, size = queue.size(); q<size; q++) {
				Ukje cur = queue.poll();
				
				if(cur.r == 0 && cur.c == 7) {
					possible = true;
					break;
				}
				
				//인접한 칸으로 이동
				int nr, nc;
				for(int i = 0, len = dr.length; i<len; i++) {
					nr = cur.r + dr[i];
					nc = cur.c + dc[i];
					if(nr < 0 || nr >= 8 || nc < 0 || nc >= 8
							|| visited[nr][nc][time] || map[nr][nc] == '#') continue;
					
					queue.offer(new Ukje(nr, nc));
					visited[nr][nc][time] = true;
				}
				
				//현재 위치에 서 있음
				queue.offer(new Ukje(cur.r, cur.c));
				visited[cur.r][cur.c][time] = true;
			}
			
			//벽 아래로
			move();
			
			//욱제 살아있는 지 확인
			int qSize = queue.size();
			for(int q = 0; q<qSize; q++) {
				Ukje temp = queue.poll();
				if(map[temp.r][temp.c] != '#') {
					queue.offer(temp);
				}
			}
		}
	}
	
	private static void move() {
		for(int i = 7; i>=0; i--) {
			for(int j = 0; j<8; j++) {
				if(map[i][j] == '#') {
					if(i < 7) {
						map[i][j] = '.';
						map[i+1][j] = '#'; 
					} else {
						map[i][j] = '.';
					}
				}
			}
		}
	}
}

class Ukje{
	int r, c;
	Ukje(int r, int c){
		this.r = r;
		this.c = c;
	}
}
