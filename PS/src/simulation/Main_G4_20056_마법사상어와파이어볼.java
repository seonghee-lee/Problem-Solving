package simulation;

import java.util.*;
import java.io.*;

public class Main_G4_20056_마법사상어와파이어볼 {
	static int N, M, K;
	static int[][] count, mass, speed, direction, dNum;
	static List<Fireball> list = new ArrayList<>();
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmk = br.readLine().split(" ");
		N = Integer.parseInt(nmk[0]);
		M = Integer.parseInt(nmk[1]);
		K = Integer.parseInt(nmk[2]);
		
		count = new int[N+1][N+1];
		mass = new int[N+1][N+1];
		speed = new int[N+1][N+1];
		direction = new int[N+1][N+1];
		dNum = new int[N+1][N+1];
		
		for(int i = 0; i<M; i++) {
			String[] inputs = br.readLine().split(" ");
			int r = Integer.parseInt(inputs[0]);
			int c = Integer.parseInt(inputs[1]);
			int m = Integer.parseInt(inputs[2]);
			int s = Integer.parseInt(inputs[3]);
			int d = Integer.parseInt(inputs[4]);
			
			list.add(new Fireball(r, c, m, s, d));
		}
		
		for(int i = 0; i<K; i++) {	//K번 명령 수행
			move();		//모든 파이어볼 이동
			
			initMap();	//맵 초기화
			
			changeMap();	//리스트를 돌리며 맵 변경
			
			list.clear();
			
			changeFireball();		//2개 이상의 파이어볼이 있는 칸에서의 변화
			
		}
		
		//남아있는 파이어볼 질량의 합 구하기
		int sum = 0;
		for(Fireball f : list) {
			sum += f.m;
		}
		
		System.out.print(sum);
	}
	
	private static void move() {
		for(Fireball f : list) {
			int nr = f.r + dr[f.d] * f.s;
			int nc = f.c + dc[f.d]* f.s;
			
			f.r = (nr + 1000 * N) % N;
			f.c = (nc + 1000 * N) % N;
			
			if(f.r == 0) f.r = N;
			if(f.c == 0) f.c = N;
		}
	}
	
	private static void initMap() {
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				count[i][j] = 0;
				mass[i][j] = 0;
				speed[i][j] = 0;
				direction[i][j] = 0;
				dNum[i][j] = 0;
			}
		}
	}
	
	private static void changeMap() {
		for(Fireball f : list) {
			count[f.r][f.c]++;
			mass[f.r][f.c] += f.m;
			speed[f.r][f.c] += f.s;
			if(f.d % 2 == 0) {
				direction[f.r][f.c] += 0;
			} else {
				direction[f.r][f.c] += 1;
			}
			dNum[f.r][f.c] += f.d; 
		}
	}
	
	private static void changeFireball() {
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(count[i][j] == 1) {
					list.add(new Fireball(i, j, mass[i][j], speed[i][j], dNum[i][j]));
				} else if(count[i][j] >= 2) {
					int newM = mass[i][j] / 5;
					int newSpeed = speed[i][j] / count[i][j];

					if(newM == 0) continue;
					
					if(direction[i][j] == 0 || direction[i][j] == count[i][j]) {
						for(int k = 0; k <= 6; k += 2) {
							list.add(new Fireball(i, j, newM, newSpeed, k));
						}
					} else {
						for(int k = 1; k<=7; k += 2) {
							list.add(new Fireball(i, j, newM, newSpeed, k));
						}
					}
				}
			}
		}
	}
}

class Fireball{
	int r, c, m, s, d;
	Fireball(int r, int c, int m, int s, int d){
		this.r = r;
		this.c = c;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}
