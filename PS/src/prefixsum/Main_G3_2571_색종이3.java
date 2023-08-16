package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G3_2571_색종이3 {
	static int N;
	static int[][] paper;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	//색종이의 개수
		paper = new int[101][101];
		
		for(int q = 0; q<N; q++) {
			String[] inputs = br.readLine().split(" ");
			int c = Integer.parseInt(inputs[0]);
			int r = Integer.parseInt(inputs[1]);
			
			for(int i = r; i < r + 10; i++) {
				for(int j = c; j < c + 10; j++) {
					paper[i][j] = 1;
				}
			}
		}
		
		//(r, c)이 직사각형의 왼쪽하단 꼭짓점일 때, 만들 수 있는 직사각형들의 넓이 중 최대 넓이를 구하고 싶다.
		//(r, c)가 직사각형 왼쪽 하단 점일때 직사각형의 높이를 구하고 (최솟값)
		//가로 길이를 하나씩 넓혀가면서(높이는 항상 최솟값으로) 모든 직사각형 넓이들이 최댓값을 구한다.
		
		//누적합으로 (r,c)가 왼쪽하단 점일 때의 높이들을 모두 구해놓는다.
		for(int i = 0; i<=100; i++) {
			for(int j = 0; j<=100; j++) {
				if(paper[i][j] == 0) continue;
				paper[i][j] += paper[i-1][j];	//세로 누적합을 구한다.
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		for(int r = 0; r<=100; r++) {
			for(int c = 0; c<=100; c++) {
				if(paper[r][c] == 0) continue;
				int height = Integer.MAX_VALUE;
				
				for(int width = c; width < 100; width++) {
					
					if(paper[r][width] == 0) break;
					
					height = Math.min(height, paper[r][width]);
					
					int space = (width - c + 1) * height;
					max = Math.max(max, space);
				}
			}
		}
		
		System.out.print(max);
	}
}
