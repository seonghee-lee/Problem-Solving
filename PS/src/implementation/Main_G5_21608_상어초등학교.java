package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 학생을 배치하는 순서대로 학생을 배치한다.
 * 비어있는 칸 중에서, 좋아하는 학생이 인접한 칸에 가장 많은 칸: 비어있는 칸마다 인접하는 좋아하는 학생 수를 알아야 한다.
 *   - 정할 수 있으면 정한다.
 *   - 정할 수 없다면(여러개인 경우)
 *     - 해당 칸들에서 인접한 비어있는 칸의 개수가 많은 곳으로 정한다.
 *     - 정할 수 없다면
 *       - 행의 번호가 가장 작은 칸으로 정한다.
 *          - 정할 수 없다면, 열의 번호가 가장 작은 칸으로 정한다.
 *
 */

public class Main_G5_21608_상어초등학교 {
	static int N, sum;
	static int[][] map, student, likeCount;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];		//학생을 배치할 공간
		student = new int[N*N][5];
		likeCount = new int[N][N];	//좋아하는 학생 수를 세기 위한 배열
		
		StringTokenizer st;
		for(int i = 0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<5; j++) {
				student[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//N*N명의 학생의 자리를 정한다.
		for(int i = 0; i<N*N; i++) {
			
			initLikeCountArr();		//likeCount배열을 0으로 초기화한다.
			
			setLike(i);				//비어있는 칸들에 대해 i번째 학생이 좋아하는 학생이 인접칸에 몇명 있는지 센다.
			List<int[]> list = new ArrayList<>();
			list = step1();
			
			if(list.size() == 1) {			//좋아하는 학생 수가 가장 많은 칸이 1개여서 정할 수 있다면
				int[] pos = list.get(0);
				map[pos[0]][pos[1]] = student[i][0];
				continue;
			}
			
			//리스트 중 인접한 비어있는 칸의 개수가 가장 많은 곳으로 정한다.
			List<int[]> list2 = new ArrayList<>();
			list2 = step2(list);
			if(list2.size() == 1) {			//인접한 비어있는 칸의 개수가 가장 많은 곳이 1개여서 정할 수 있다면
				int[] pos = list2.get(0);
				map[pos[0]][pos[1]] = student[i][0];
				continue;
			}
			
			
			//리스트2중 행의 번호 -> 열의 번호가 가장 작은 곳으로 정한다.
			int[] pos = step3(list2);
			map[pos[0]][pos[1]] = student[i][0];
			
		}

		//만족도의 총 합을 구한다.
		getSum();
		System.out.println(sum);
	}
	
	//인접한 칸을 보면서 좋아하는 학생이 몇명있는 지 확인하고 점수를 계산한다.
	private static void getSum() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				int x= map[i][j];	//x번 학생
				
				//x번 학생이 student배열에서 몇번째인지 알아야한다.
				int curStudent = findStudentIndex(x);
				
				int count = 0;
				for(int d = 0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if(map[nr][nc] == student[curStudent][1] || map[nr][nc] == student[curStudent][2]
						|| map[nr][nc] == student[curStudent][3] || map[nr][nc] == student[curStudent][4]) count++;
				}
				
				if(count == 0) sum += 0;
				else if(count == 1) sum += 1;
				else if(count == 2) sum += 10;
				else if(count == 3) sum += 100;
				else if(count == 4) sum += 1000;
			}
		}
	}

	private static int findStudentIndex(int x) {
		int idx = -1;
		for(int i = 0; i<N*N; i++) {
			if(student[i][0] == x) {
				idx = i;
				break;
			}
		}
		return idx;
	}

	private static int[] step3(List<int[]> list2) {
		int[] result = new int[2];
		boolean[][] check = new boolean[N][N];
		
		for(int[] p : list2) {
			check[p[0]][p[1]] = true;
		}

		top:
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(check[i][j] == true) {
					result[0] = i;
					result[1] = j;
					break top;
				}
			}
		}
		return result;
	}

	private static List<int[]> step2(List<int[]> list) {
		int max = Integer.MIN_VALUE;
		List<int[]> resultList = new ArrayList<>();
		
		int r, c;
		for(int[] p: list) {
			r = p[0];	c = p[1];
			if(map[r][c] > 0) continue;
			int count = 0;
			for(int d = 0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
						
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] > 0) continue;
				count++;
			}
					
			if(count > max) { 
				if(!resultList.isEmpty()) resultList.clear();
				max = count;
				resultList.add(new int[] {r, c});
			}else if(count == max) {
				max = count;
				resultList.add(new int[] {r, c});
			}
		}
		
		return resultList;
	}

	private static List<int[]> step1() {
		int max = Integer.MIN_VALUE;
		List<int[]> list = new ArrayList<>();
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(likeCount[i][j] > max) {
					if(!list.isEmpty()) list.clear();
					max = likeCount[i][j];
					list.add(new int[] {i, j});
				}else if(likeCount[i][j] == max) {
					max = likeCount[i][j];
					list.add(new int[] {i, j});
				}
			}
		}
		
		return list;
	}
	
	private static void setLike(int s) {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j] == 0) {	//비어있는 칸 일 경우
					int count = 0;
					for(int d = 0; d<4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if(map[nr][nc] == student[s][1] || map[nr][nc] == student[s][2] 
								|| map[nr][nc] == student[s][3] || map[nr][nc] == student[s][4]) count++;
					}
					likeCount[i][j] = count;
				}
			}
		}
	}
	
	private static void initLikeCountArr() {
		for(int i = 0; i<N; i++) {
			Arrays.fill(likeCount[i], 0);
		}
	}

}
