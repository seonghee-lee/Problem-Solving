package backtracking;

import java.util.*;
import java.io.*;

public class Main_G3_16571_알파틱택토 {
	static int[][] map = new int[3][3];
	static int max = -2;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		int cnt = 0;
		for(int i = 0; i<3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) cnt++;
			}
		}
		
		
		int turn = 0;	//1: x, 2: o
		if(cnt % 2 == 0) turn = 1;
		else turn = 2;
		
		int result = go(turn);
		
		if(result == 1) System.out.println("W");
		else if(result == 0) System.out.println("D");
		else System.out.println("L");
		
	}
	
	//현재 turn의 게임 결과 -> 0: 무승부, 1: 승리, -1: 패배
	private static int go(int turn) {
		
		int min = 2;	//min: 상대방의 최선의 승패
		
		boolean win = getResult(3-turn);
		if(win) {	//이전에 수를 둔 플레이어(현재 기준 상대 턴)가 승리
			return -1;	//나는 더이상 이 판에서 수를 놓는 의미가 없으므로 return 
		}
		
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				if(map[i][j] == 0) {
					map[i][j] = turn;
					
					int result = go(3-turn);	//result==-1: 상대방 패, 나의 승
					min = Math.min(min, result);	//현재 나의 입장에서, 상대방의 결과가 가장 안 좋아야 하므로 min
					
					map[i][j] = 0;
				}
			}
		}
		
		//상대방과 나는 승/패가 반대이다.
		if(min == 1) return -1;		//상대방이 승리했다면, 나는 패배
		else if(min == 2 || min == 0) return 0;
		else return 1;
	}
	
	//turn에 대해서 승, 패를 결정한다.
	private static boolean getResult(int turn) {
		//row로 승리
		for(int r = 0; r<3; r++) {
			if(map[r][0] == turn && map[r][1] == turn && map[r][2] == turn) return true;
		}
		
		//col로 승리
		for(int c = 0; c<3; c++) {
			if(map[0][c] == turn && map[1][c] == turn && map[2][c] == turn) return true;
		}
		
		//대각선으로 승리
		if(map[0][0] == turn && map[1][1] == turn && map[2][2] == turn) return true;
		if(map[0][2] == turn && map[1][1] == turn && map[2][0] == turn) return true;
		
		return false;
	}
}

