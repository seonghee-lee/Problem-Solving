package backtracking;

import java.util.*;
import java.io.*;

/**
 * 조건함수: 현재 숫자가 map에 들어갔을 때, 가능/불가능을 검사
 * -> 가능할 경우, 그 숫자를 map에 넣고 재귀를 타서 다음 (r,c)로 간다.
 * -> 불가능할경우 반복문의 다음 숫자로 넘어간다.
 * -> 반복문이 종료된 후에는 map을 다시 0으로 바꿔준다. : 이 부분이 실행되면 이전에 넣은 숫자를 바꿔야 하는거니까
 * 
 * 조건함수
 * 1. 행 검사
 * 2. 열 검사
 * 3. 3*3 검사
 *
 * 재귀함수
 * - col >= 9이면, (row+1, 0)로 간다.
 * - row >= 9이면, 모든 칸을 다 채웠으므로 출력한다. 시스템을 종료한다.
 * - map[row][col] == 0이면, 조건함수를 실행한다.
 * - 아니면, go(row, col+1)로 간다.
 */

public class Main_G4_2580_스도쿠 {
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		
		StringTokenizer st;
		for(int i = 0; i<9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		go(0, 0);
	}
	
	private static void go(int r, int c) {
		
		if(c >= 9) {
			go(r+1, 0);
			return;
		}
		
		if(r >= 9) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i<9; i++) {
				for(int j = 0; j<9; j++) {
					sb.append(map[i][j]).append(" ");
				}sb.append("\n");
			}
			System.out.print(sb);
			System.exit(0);
		}
		
		if(map[r][c] == 0) {
			for(int i = 1; i<=9; i++) {
				if(possible(r, c, i)) {
					map[r][c] = i;
					go(r, c+1);
				}
			}
			map[r][c] = 0;
		} else {
			go(r, c+1);
		}
	}
	
	private static boolean possible(int row, int col, int num) {
		
		//행 체크
		for(int i = 0; i<9; i++) {
			if(map[row][i] == num) return false;
		}
		
		//열 체크
		for(int i = 0; i<9; i++) {
			if(map[i][col] == num) return false;
		}
		
		//3*3 체크
		int sr = (row / 3) * 3;
		int sc = (col / 3) * 3;
		
		for(int i = sr; i<sr+3; i++) {
			for(int j = sc; j<sc+3; j++) {
				if(map[i][j] == num) return false;
			}
		}
		
		return true;
	}
}
