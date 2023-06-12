package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G4_17144_미세먼지안녕 {
	static int R, C, T;
	static int[][] map, dust, puri;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		dust = new int[R][C];
		
		puri = new int[2][2];	//공기청정기 좌표
		int idx = 0;
		
		for(int i = 0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					puri[idx][0] = i;
					puri[idx][1] = j;
					idx++;
				}
			}
		}
		
		int time = 0;
		while(time < T) {
			time++;
			
			//dust배열 초기화
			for(int i = 0; i<R; i++) {
				for(int j = 0; j<C; j++) {
					dust[i][j] = 0;
				}
			}
			
			//확산
			spread();
			
			//확산된 결과를 계산하여 맵에 반영
			spreadToMap();
			
			//맵 복사
			int[][] copy = copyMap();
			
			//공기청정기 가동
			airUp(copy);
			airDown(copy);
			
		}
		
		//미세먼지 양 측정
		int result = getSum() + 2;

		System.out.println(result);
	}
	
	private static int getSum() {
		int sum = 0;
		
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				sum += map[i][j];
			}
		}
	
		return sum;
	}

	//공기청정기의 아래쪽으로 인해 공기 확산이 일어난다.
	private static void airDown(int[][] copy) {
		
		//위로 올린다.
		for(int i = R-2; i>= puri[1][0]; i--) {
			map[i][0] = copy[i+1][0];
		}
		
		//오른쪽으로 보낸다.
		for(int i = 1; i<C; i++) {
			map[puri[1][0]][i] = copy[puri[1][0]][i-1]; 
		}
		
		//아래로 내린다.
		for(int i = puri[1][0] +1; i<R; i++) {
			map[i][C-1] = copy[i-1][C-1];
		}
		
		//왼쪽으로 보낸다.
		for(int i = C-2; i>=0; i--) {
			map[R-1][i] = copy[R-1][i+1];
		}
		
		//정화된 공기 0처리
		map[puri[1][0]][1] = 0;
		//공기청정기 위치 -1처리
		map[puri[1][0]][puri[1][1]] = -1;
	}

	//공기청정기의 위쪽으로 인해 공기 확산이 일어난다.
	private static void airUp(int[][] copy) {
		
		//아래로 내린다.
		for(int i = 1; i<=puri[0][0]; i++) {
			map[i][0] = copy[i-1][0];
		}
		
		//오른쪽으로 보낸다.
		for(int i = 2; i<C; i++) {
			map[puri[0][0]][i] = copy[puri[0][0]][i-1]; 
		}
		
		//위쪽으로 보낸다.
		for(int i = puri[0][0]-1; i>=0; i--) {
			map[i][C-1] = copy[i+1][C-1];
		}
		
		//왼쪽으로 보낸다.
		for(int i = C-2; i>= 0; i--) {
			map[0][i] = copy[0][i+1];
		}
		
		//정화된 공기 0 처리
		map[puri[0][0]][1] = 0;
		//공기청정기 위치 -1 처리
		map[puri[0][0]][puri[0][1]] = -1;
	}
	
	//map을 copy에 복사한다.
	private static int[][] copyMap() {
		int[][] copy = new int[R][C];
		
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				copy[i][j] = map[i][j];
			}
		}
		 return copy;
	}

	//dust배열 값을 실제 map에 반영한다.
	private static void spreadToMap() {
		
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(dust[i][j] == 0) continue;
				map[i][j] += dust[i][j];
			}
		}
	}

	//미세먼지를 확산하여 dust배열에 저장
	private static void spread() {
		
		//(i, j)를 중점으로 한 확산
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(map[i][j] == 0) continue;
				
				//사방을 탐색
				int count = 0;
				int amount = map[i][j] / 5;	//한 칸마다 확산되는 양
				for(int d = 0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == -1) continue;	//이러한 방향으로는 확산이 일어나지 않는다.
					count++;	//확산이 일어나는 방향에 대해 카운트를 세준다.
					dust[nr][nc] += amount;
				}
				dust[i][j] -= amount * count;
			}
		}
	}
}
