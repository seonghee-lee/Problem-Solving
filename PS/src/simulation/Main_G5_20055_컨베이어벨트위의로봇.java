package simulation;

import java.io.*;
import java.util.*;

public class Main_G5_20055_컨베이어벨트위의로봇 {
	static int N, K, upIdx, downIdx;
	static int[] belt;
	static boolean[] robot;
	static Queue<Integer> queue = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);
		
		belt = new int[2 * N];		//칸의 내구도 저장
		robot = new boolean[2 * N]; 	//로봇이 있고 없고를 판단
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i<2*N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		upIdx = 0;
		downIdx = N - 1;
		
		int step = 0;
		while(true) {
			step++;
			
			rotate();
			
			move();
			
			carry();
			
			if(isFin()) break;
		}
		
		System.out.println(step);
	}
	
	static void carry() {
		if(belt[0] != 0) {
			int rIdx = 0;
			queue.offer(rIdx);
			robot[rIdx] = true;
			belt[rIdx]--;
		}
	} 
	
	static void move() {
		int qSize = queue.size();
		while(qSize-- > 0) {
			int rIdx = queue.poll();
			if(!robot[rIdx + 1] && belt[rIdx + 1] >= 1) {
				robot[rIdx] = false;
				
				//로봇 이동
				rIdx++;
				
				belt[rIdx]--;
				
				if(rIdx >= downIdx) continue;	//내리는 위치에 도달하면 바로 내린다.
				
				robot[rIdx] = true;
				queue.offer(rIdx);
			}else {
				queue.offer(rIdx);
			}
		}
	}
	
	static void rotate() {
		//칸 이동
		int last = belt[2*N-1];
		for(int i = 2*N-2; i>= 0; i--) {
			belt[i+1] = belt[i];
		}
		belt[0] = last;
		
		//로봇 위치도 이동
		int qSize = queue.size();
		while(qSize-- > 0) {
			int rIdx = queue.poll();
			robot[rIdx] = false;
			rIdx++;
			if(rIdx == downIdx) continue;	//내리는 위치에 도달하면 바로 내린다.
			robot[rIdx] = true;
			queue.offer(rIdx);
		}
	}
	
	static boolean isFin() {
		boolean fin = false;
		
		int count = 0;
		for(int i = 0, len = 2*N; i<len; i++) {
			if(belt[i] == 0) count++;
		}
		
		if(count >= K) fin = true;
		
		return fin;
	}

}
