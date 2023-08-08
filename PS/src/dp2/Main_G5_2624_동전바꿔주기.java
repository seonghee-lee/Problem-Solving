package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_2624_동전바꿔주기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	//지폐의 금액
		int k = Integer.parseInt(br.readLine());	//동전의 가지 수
		
		List<Coin> list = new ArrayList<>();
		for(int i = 0; i<k; i++) {
			String[] inputs = br.readLine().split(" ");
			int price = Integer.parseInt(inputs[0]);
			int count = Integer.parseInt(inputs[1]);
			list.add(new Coin(price, count));
		}
		
		Collections.sort(list);
		
		int[] p = new int[k+1];		//동전의 금액
		int[] n = new int[k+1];		//동전의 가지수
		
		int index = 1;
		for(Coin c : list) {
			p[index] = c.price;
			n[index] = c.count;
			index++;
		}
		
		int[][] dp = new int[k+1][T+1];	//dp[i][j]: i원까지를 최대로 사용하여 j원을 만드는 경우의 수
		for(int i = 0; i<=k; i++) {
			dp[i][0] = 1;
		}
			
		for(int i = 1; i<=k; i++) {
			int curCoin = p[i];	//현재 사용하는 동전
			for(int j = 1; j<=T; j++) {		//j원을 만든다.
				if(curCoin > j) dp[i][j] = dp[i-1][j];	//현재 동전으로 j원을 만들 수 없으면, 이전 경우의 수를 사용
				else {
					int cnt = 1;
					int sum = dp[i-1][j];
					while(true) {
						int val = j - curCoin * cnt;
						if(val < 0 || cnt > n[i]) break;
						sum += dp[i-1][val];
						cnt++;
					}
					dp[i][j] = sum;
				}
			}
		}
		
		System.out.print(dp[k][T]);
	}
}

class Coin implements Comparable<Coin>{
	int price, count;
	Coin(int price, int count){
		this.price = price;
		this.count = count;
	}
	
	public int compareTo(Coin o) {
		return this.price - o.price;
	}
}
