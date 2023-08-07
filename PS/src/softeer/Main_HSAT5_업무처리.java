package softeer;

import java.util.*;
import java.io.*;

public class Main_HSAT5_업무처리 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		int H = Integer.parseInt(inputs[0]);	//조직도의 높이
		int K = Integer.parseInt(inputs[1]);	//말단에 대기하는 업무의 개수
		int R = Integer.parseInt(inputs[2]);	//업무가 진행되는 날짜 수
		
		int leaf = (int)Math.pow(2, H);		//말단 직원의 수
		int normal = (int)Math.pow(2, H+1) - 1 - leaf;	//일반 직원의 수
		
		//일반 직원의 업무 큐
		Queue<Integer>[][] normalQueue = new ArrayDeque[normal+1][2];
		for(int i = 0; i<=normal; i++) {
			for(int j = 0; j<2; j++) {
				normalQueue[i][j] = new ArrayDeque<>();
			}
		}
		
		//말단 직원의 업무 큐
		Queue<Integer>[] leafQueue = new ArrayDeque[normal+leaf+1];
		for(int i = 0; i<=normal+leaf; i++) {
			leafQueue[i] = new ArrayDeque<>();
		}
		
		StringTokenizer st;
		for(int i = normal+1; i<=normal+leaf; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<K; j++) {
				int w = Integer.parseInt(st.nextToken());
				leafQueue[i].offer(w);
			}
		}
		
		int day = 0;
		int sum = 0;
		
		while(day < R) {
			day++;
			
			//홀수번째 날
			if(day % 2 == 1) {
				//부서장 업무 처리 (왼쪽 업무)
				if(!normalQueue[1][0].isEmpty()) {
					sum += normalQueue[1][0].poll();
				}
				//일반 직원이 왼쪽 직원으로 부터 받은 업무 올림
				for(int i = 2; i<=normal; i++) {
					if(!normalQueue[i][0].isEmpty()) {
						int work = normalQueue[i][0].poll();
						if(i % 2 == 0) {
							normalQueue[i/2][0].offer(work);
						} else {
							normalQueue[i/2][1].offer(work);
						}
					}
				}
			} else {	//짝수번째 날
				if(!normalQueue[1][1].isEmpty()) {
					sum += normalQueue[1][1].poll();
				}
				
				for(int i = 2; i<=normal; i++) {
					if(!normalQueue[i][1].isEmpty()) {
						int work = normalQueue[i][1].poll();
						if(i % 2 == 0) {
							normalQueue[i/2][0].offer(work);
						} else {
							normalQueue[i/2][1].offer(work);
						}
					}
				}
			}
			
			//말단 직원이 업무 올림
			for(int i = normal+1; i<=normal+leaf; i++) {
				if(!leafQueue[i].isEmpty()) {
					int work = leafQueue[i].poll();
					if(i % 2 == 0) {
						normalQueue[i/2][0].offer(work);
					} else {
						normalQueue[i/2][1].offer(work);
					}
				}
			}
		}
		
		System.out.print(sum);
	}

}
