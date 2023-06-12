package dp;

import java.util.*;
import java.io.*;

public class Main_S1_12101_123더하기2 {
	static int N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);
		
		//N을 1,2,3으로 나타내는 K번째 식
		List<DP> list = new ArrayList<>();
		list.add(new DP(1));
		list.add(new DP(2));
		list.add(new DP(3));
		
		list.get(0).fomula.add("1");
		list.get(1).fomula.add("1+1");
		list.get(1).fomula.add("2");
		list.get(2).fomula.add("1+1+1");
		list.get(2).fomula.add("1+2");
		list.get(2).fomula.add("2+1");
		list.get(2).fomula.add("3");
		
		for(int i = 3; i<N; i++) {
			list.add(new DP(i));
			for(int j = 1; j <= 3; j++) {
				for(int k = 0, size = list.get(i-j).fomula.size(); k<size; k++) {
					String fomula = j + "+" + list.get(i-j).fomula.get(k);
					list.get(i).fomula.add(fomula);
				}
			}
		}
		Collections.sort(list.get(N-1).fomula);
		
		if(list.get(N-1).fomula.size() < K) {
			System.out.println(-1);
		} else{
			System.out.println(list.get(N-1).fomula.get(K-1));
		}
	}

}

class DP{
	int num;
	List<String> fomula = new ArrayList<>();
	
	DP(int num){
		this.num = num;
	}
}
