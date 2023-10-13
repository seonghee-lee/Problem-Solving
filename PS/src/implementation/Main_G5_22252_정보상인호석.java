package implementation;

import java.util.*;
import java.io.*;

public class Main_G5_22252_정보상인호석 {
	private static Map<String, PriorityQueue> map = new HashMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		
		long valueSum = 0;
		
		for(int q = 1; q<=Q; q++) {
			String[] inputs = br.readLine().split(" ");
			int ord = Integer.parseInt(inputs[0]);
			String name = inputs[1];
			int k = Integer.parseInt(inputs[2]);
			
			if(ord == 1) {	//이름이 name인 고릴라가 k개의 정보를 얻었으며, 각 가치는...
				int[] vals = new int[k];
				for(int i = 0; i<k; i++)	vals[i] = Integer.parseInt(inputs[3+i]);
				
				if(map.containsKey(name)) {
					for(int i = 0; i<k; i++) map.get(name).offer(vals[i]);
				} else {
					map.put(name, new PriorityQueue<Integer>((o1, o2) -> Integer.compare(o2, o1)));
					for(int i = 0; i<k; i++) map.get(name).offer(vals[i]);
				}
			} else {	//호석이가 이름이 name인 고릴라에게 b개의 정보를 구매한다.
				while(k-- > 0) {
					if(!map.containsKey(name)) break;
					if(map.get(name).isEmpty()) break;
					valueSum += (int)map.get(name).poll();
				}
			}
		}
		System.out.println(valueSum);
	}
}
