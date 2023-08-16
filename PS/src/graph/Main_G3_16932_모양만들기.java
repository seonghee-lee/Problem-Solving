package graph;

import java.util.*;
import java.io.*;

/*
 * 기본 맵에서 1로 이루어진 섬들을 저장한다.
 * (섬의 번호, 섬의 크기)로 저장한다.
 * 
 * 0 땅들에서 4방향 탐색을 진행한다.
 * 4방향을 탐색했을 때 인접한 섬들을 HashSet에 담는다. (인접한 1이 같은 섬의 1일 수 있으니 중복을 제거하기 위해서)
 * 인접한 섬들의 크기의 합 + 1이 현재 0을 1로 바꿨을 때 얻을 수 있는 모양의 최대 크기이다.
 * */

public class Main_G3_16932_모양만들기 {
	static int N, M, isize;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};	//상, 좌, 하, 우
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		map = new int[N][M];
		List<Position> zeroList = new ArrayList<>();
		
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					zeroList.add(new Position(i, j));
				}
			}
		}
		
		HashMap<Integer, Integer> island = new HashMap<>();	//key: 섬번호, value: 섬크기
		visited = new boolean[N][M];
		int islandIndex = 2;
		
		//1로 이루어진 섬들을 구한다.
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					isize = 1;
					
					findIsland(i, j, islandIndex);
					
					island.put(islandIndex, isize);
					islandIndex++;
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		for(Position p : zeroList) {
			HashSet<Integer> adjIsland = new HashSet<>();
			int r = p.r;
			int c = p.c;
			
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M 
						|| map[nr][nc] == 0 || adjIsland.contains(map[nr][nc])) continue;
				adjIsland.add(map[nr][nc]);
			}
			
			int sum = 1;
			for(Integer k : adjIsland) {
				sum += island.get(k);
			}
			
			if(sum > max) max = sum;
		}
		
		System.out.print(max);
	}
	
	private static void findIsland(int r, int c, int index) {
		map[r][c] = index;
		visited[r][c] = true;
		
		for(int i = 0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M 
					|| visited[nr][nc] || map[nr][nc] == 0 || map[nr][nc] > 1) continue;
			
			isize++;
			
			findIsland(nr, nc, index);
		}
	}
}

class Position {
	int r, c;
	Position(int r, int c){
		this.r = r;
		this.c = c;
	}
}
