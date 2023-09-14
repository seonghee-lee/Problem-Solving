package simulation;

import java.util.*;
import java.io.*;

public class Main_G5_14891_톱니바퀴 {
	static int[][] wheel;
	static boolean[] selected;
	static List<Wheel> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wheel = new int[5][9];
		
		for(int i = 1; i<=4; i++) {
			String[] inputs = br.readLine().split("");
			for(int j = 0; j<8; j++) {
				wheel[i][j+1] = Integer.parseInt(inputs[j]);
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for(int k = 1; k<=K; k++) {
			String[] order = br.readLine().split(" ");
			int num = Integer.parseInt(order[0]);
			int dir = Integer.parseInt(order[1]);
			
			selected = new boolean[5];
			list.clear();
			
			findWheel(num, dir);
			
			for(int i = 0, size = list.size(); i<size; i++) {
				Wheel cur = list.get(i);
				rotate(cur.num, cur.dir);
			}
		}
		
		int score = 0;
		for(int i = 1; i<=4; i++) {
			if(wheel[i][1] == 1) score += Math.pow(2, i-1);
		}
		
		System.out.println(score);
	}
	
	private static void findWheel(int n, int d) {
		if(selected[n]) return;
		
		selected[n] = true;
		list.add(new Wheel(n, d));
		
		if(n == 1) {
			if(wheel[1][3] != wheel[2][7]) findWheel(2, d * (-1));
		} else if(n == 2) {
			if(wheel[2][7] != wheel[1][3]) findWheel(1, d * (-1));
			if(wheel[2][3] != wheel[3][7]) findWheel(3, d * (-1));
		} else if(n == 3) {
			if(wheel[3][7] != wheel[2][3]) findWheel(2, d * (-1));
			if(wheel[3][3] != wheel[4][7]) findWheel(4, d * (-1));
		} else
			if(wheel[4][7] != wheel[3][3]) findWheel(3, d * (-1));
	}
	
	private static void rotate(int n, int d) {
		if(d == 1) {	//시계방향
			int temp = wheel[n][8];
			for(int i = 8; i>1; i--) {
				wheel[n][i] = wheel[n][i-1];
			}
			wheel[n][1] = temp;
			
		} else {
			int temp = wheel[n][1];
			for(int i = 1; i<8; i++) {
				wheel[n][i] = wheel[n][i+1];
			}
			wheel[n][8] = temp;
		}
	}
}

class Wheel {
	int num, dir;
	Wheel(int num, int dir){
		this.num = num;
		this.dir = dir;
	}
}
