package backtracking;

import java.util.*;
import java.io.*;

public class Main_G4_6987_월드컵 {
	static int[][] game, match;
	static boolean possible;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		for(int t = 1; t<=4; t++) {
			game = new int[6][3];
			String[] inputs = br.readLine().split(" ");
			int idx = 0;
			boolean initFlag = true;
			for(int i = 0; i<6; i++) {
				int total = 0;
				for(int j = 0; j<3; j++) {
					game[i][j] = Integer.parseInt(inputs[idx++]);
					total += game[i][j];
				}
				if(total != 5) initFlag = false;
			}
			
			//팀 선택: a-b,c,d,e,f / b-c,d,e,f / c-d,e,f / ... => 총 15경기
			match = new int[15][2];	//경기 경우의 수
			idx = 0;
			for(int i = 0; i<6; i++) {
				for(int j = i+1; j<6; j++) {
					match[idx][0] = i;
					match[idx][1] = j;
					idx++;
				}
			}
			
			if(!initFlag) {
				result.append(0).append(" ");
			} else {
				possible = false;
				go(0);
				if(possible) result.append(1).append(" ");
				else result.append(0).append(" ");
			}
		}
		System.out.print(result);
	}
	
	private static void go(int matchCount) {
		if(possible) return;
		
		if(matchCount == 15) {
			possible = true;
			return;
		}
		
		int curTeam = match[matchCount][0];
		int vsTeam = match[matchCount][1];
		
		//승-패
		if(game[curTeam][0] > 0 && game[vsTeam][2] > 0) {
			game[curTeam][0]--;
			game[vsTeam][2]--;
			go(matchCount+1);
			game[curTeam][0]++;
			game[vsTeam][2]++;
		}
		
		//무-무
		if(game[curTeam][1] > 0 && game[vsTeam][1] > 0) {
			game[curTeam][1]--;
			game[vsTeam][1]--;
			go(matchCount + 1);
			game[curTeam][1]++;
			game[vsTeam][1]++;
		}
		
		
		//패-승
		if(game[curTeam][2] > 0 && game[vsTeam][0] > 0) {
			game[curTeam][2]--;
			game[vsTeam][0]--;
			go(matchCount + 1);
			game[curTeam][2]++;
			game[vsTeam][0]++;
		}
	}
}
