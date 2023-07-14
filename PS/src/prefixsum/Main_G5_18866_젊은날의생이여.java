package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G5_18866_젊은날의생이여 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] happy = new int[N+1];
		int[] tired = new int[N+1];
		
		for(int i = 0; i<N; i++) {
			String[] inputs = br.readLine().split(" ");
			happy[i] = Integer.parseInt(inputs[0]);
			tired[i] = Integer.parseInt(inputs[1]);
		}
		
		//젊은날 행복도의 min > 늙은날 행복도의 max
		//젊은날 피로도의 max < 늙은날 피로도의 min
		
		int[] youngHappyMin = new int[N+2];
		int[] oldHappyMax = new int[N+2];
		int[] youngTiredMax = new int[N+2];
		int[] oldTiredMin = new int[N+2];
		
		//배열 채우기
		int hMin = Integer.MAX_VALUE;
		int tMax = 0;
		for(int i = 1; i<=N; i++) {
			//젊은날의 행복도
			int curHappy = happy[i-1];
			if(curHappy == 0) {
				youngHappyMin[i] = youngHappyMin[i-1];
			} else {
				hMin = Math.min(hMin, curHappy);
				youngHappyMin[i] = hMin;
			}
			
			//젊은날의 피로도
			int curTired = tired[i-1];
			if(curTired == 0) {
				youngTiredMax[i] = youngTiredMax[i-1];
			} else {
				tMax = Math.max(tMax, curTired);
				youngTiredMax[i] = tMax;
			}
		}
		
		int hMax = 0;
		int tMin = Integer.MAX_VALUE;
		for(int i = N; i>=1; i--) {
			//늙은날의 행복도
			int curHappy = happy[i-1];
			if(curHappy == 0) {
				oldHappyMax[i] = oldHappyMax[i+1];
			} else {
				hMax = Math.max(hMax, curHappy);
				oldHappyMax[i] = hMax;
			}
			
			//늙은날의 피로도
			int curTired = tired[i-1];
			if(curTired == 0) {
				oldTiredMin[i] = oldTiredMin[i+1];
			} else {
				tMin = Math.min(tMin, curTired);
				oldTiredMin[i] = tMin;
			}
		}
		
		int ans = 0;
		for(int k = 1; k<N; k++) { 
			
			if(youngHappyMin[k] >= oldHappyMax[k+1] && youngTiredMax[k] <= oldTiredMin[k+1]) {
				ans = k;
			}
		}
		
		if(ans < 1) System.out.print(-1);
		else System.out.print(ans);
		
	}

}
