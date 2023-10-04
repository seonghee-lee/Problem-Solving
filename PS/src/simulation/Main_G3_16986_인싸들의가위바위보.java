package simulation;

import java.util.*;
import java.io.*;

/**
 * 문제 조건 파악 잘하기!
 * 사람 당 최대 20경기까지 할 수 있지만, 모두 다른 손을 내야 하니까 N만큼 하는 게 최선 -> 재귀로 완탐 가능해진다.
 */

public class Main_G3_16986_인싸들의가위바위보 {
	static int N, K;
	static int[][] board, data;
	static boolean[] visited;
	static int[] winCount, playCount;
	static boolean possible;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		N = Integer.parseInt(nk[0]);
		K = Integer.parseInt(nk[1]);
		board = new int[N+1][N+1];	//상성
		data = new int[4][20];	//1: 지우, 2: 경희, 3: 민호	
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 2; i<=3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<20; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		winCount = new int[4];
		playCount = new int[4];
		
		visited = new boolean[N+1];
		jiwoo(0);
		
		if(possible) System.out.println(1);
		else System.out.println(0);
	}
	
	private static void jiwoo(int cnt) {
		if(possible) return;
		
		if(cnt == N) {
			Arrays.fill(winCount, 0);
			Arrays.fill(playCount, 0);
			
			//첫 게임은 지우 vs 경희
			int player1 = 1;
			int player2 = 2;
			int winner = game(player1, player2);
			winCount[winner]++;
			
			while(true) {
				if(winCount[1] == K || winCount[2] == K || winCount[3] == K) {
					if(winCount[1] == K) possible = true;
					break;
				}
				
				if(playCount[1] >= N || playCount[2] >= 20 || playCount[3] >= 20) break;
				
				player1 = 6 - player1 - player2;
				player2 = winner;
				winner = game(player1, player2);
				winCount[winner]++;
			}
			
			return;
		}
		
		for(int i = 1; i<=N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			data[1][cnt] = i;
			jiwoo(cnt + 1);
			visited[i] = false;
		}
	}
	
	private static int game(int p1, int p2) {
		int winner = 0;
		int idx1 = playCount[p1];
		int idx2 = playCount[p2];
		
		if(board[data[p1][idx1]][data[p2][idx2]] == 2) {	//p1 승리
			winner = p1;
		} else if(board[data[p1][idx1]][data[p2][idx2]] == 1) {	//비김
			winner = Math.max(p1, p2);
		} else {	//p2 승리
			winner = p2;
		}
		
		playCount[p1]++;
		playCount[p2]++;
		return winner;
	}
}
