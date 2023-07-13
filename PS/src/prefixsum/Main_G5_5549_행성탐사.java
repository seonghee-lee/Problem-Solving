package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G5_5549_행성탐사 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		int K = Integer.parseInt(br.readLine());
		
		int[][] jungle = new int[N+1][M+1];
		int[][] ocean = new int[N+1][M+1];
		int[][] ice = new int[N+1][M+1];
		
		for(int i = 1; i<=N; i++) {
			char[] inputs = br.readLine().toCharArray();
			for(int j = 1; j<=M; j++) {
				if(inputs[j-1] == 'J') {
					jungle[i][j] = 1;
				} else if(inputs[j-1] == 'O') {
					ocean[i][j] = 1;
				} else {	//I
					ice[i][j] = 1;
				}
			}
		}
		
		//행 연산
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=M; j++) {
				jungle[i][j] += jungle[i][j-1];
				ocean[i][j] += ocean[i][j-1];
				ice[i][j] += ice[i][j-1];
			}
		}
		
		//열 연산
		for(int i = 1; i<=M; i++) {
			for(int j = 1; j<=N; j++) {
				jungle[j][i] += jungle[j-1][i];
				ocean[j][i] += ocean[j-1][i];
				ice[j][i] += ice[j-1][i];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int k = 1; k<=K; k++) {
			String[] pos = br.readLine().split(" ");
			int r1 = Integer.parseInt(pos[0]);
			int c1 = Integer.parseInt(pos[1]);
			int r2 = Integer.parseInt(pos[2]);
			int c2 = Integer.parseInt(pos[3]);
			
			int j = jungle[r2][c2] - jungle[r2][c1-1] - jungle[r1-1][c2] + jungle[r1-1][c1-1];
			int o = ocean[r2][c2] - ocean[r2][c1-1] - ocean[r1-1][c2] + ocean[r1-1][c1-1];
			int i = ice[r2][c2] - ice[r2][c1-1] - ice[r1-1][c2] + ice[r1-1][c1-1];
			
			sb.append(j).append(" ").append(o).append(" ").append(i).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}

}
