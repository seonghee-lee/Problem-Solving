package simulation;

import java.util.*;
import java.io.*;

public class Main_S1_13335_트럭 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		int n = Integer.parseInt(inputs[0]);	//트럭 수
		int w = Integer.parseInt(inputs[1]);	//다리의 길이
		int L = Integer.parseInt(inputs[2]);	//다리의 최대하중
		int[] truck = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}
		
		Queue<Truck> bridge = new ArrayDeque<>();
		
		bridge.offer(new Truck(truck[0], 1));
		
		int time = 1;
		int totalWeight = truck[0];
		int lastIndex = 1;
		
		while(lastIndex <= n && !bridge.isEmpty()) {
			time++;
			//다리 가장 앞에 있는 트럭 나갈 수 있으면 나간다.
			Truck front = bridge.peek();
			if(time - front.enter >= w) {
				bridge.poll();
				totalWeight -= front.weight;
			}
			
			if(lastIndex == n) continue;
			
			//새로 트럭이 들어올 수 있으면 들어온다.
			//다음 트럭이 들어왔을 때 하중을 버틸 수 있고, 단위 길이 안에 다음 트럭이 들어오는 게 가능하면, 
			if(totalWeight + truck[lastIndex] <= L && bridge.size() < w) {
				bridge.offer(new Truck(truck[lastIndex], time));
				totalWeight += truck[lastIndex];
				lastIndex++;
			}
		}
		System.out.println(time);
	}
}

class Truck{
	int weight, enter;
	Truck(int weight, int enter){
		this.weight = weight;
		this.enter = enter;
	}
}
