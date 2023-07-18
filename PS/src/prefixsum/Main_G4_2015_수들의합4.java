package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G4_2015_수들의합4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		int N = Integer.parseInt(nk[0]);
		int K = Integer.parseInt(nk[1]);
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Long count = 0L;
		Map<Long, Integer> map = new HashMap<>();	//key: 누적합 수, value: 등장 횟수
		int[] sum = new int[N+1];	//누적합 배열
		
		//누적합 배열을 채워가면서, 내 앞에 나를 K로 만드는 수가 몇 번 등장했는지 센다.
		for(int i = 1; i<=N; i++) {
			sum[i] = sum[i-1] + arr[i];
			
			Long minus = Long.valueOf(sum[i] - K);
			if(minus == 0) count++;		//1부터 현재까지의 누적합이 K를 만족하므로 count++
			
			if(map.containsKey(Long.valueOf(minus))) {	//내 앞에 나를 K로 만드는 수가 존재한다면
				count += map.get(Long.valueOf(minus));	//그 수의 출현 횟수만큼 count에 더한다.
			}
			
			if(map.containsKey(Long.valueOf(sum[i]))) {		//누적합 sum[i]가 map에 있으면
				map.put(Long.valueOf(sum[i]), map.get(Long.valueOf(sum[i])) + 1);	//출현 횟수를 1 더해준다.
			} else {
				map.put(Long.valueOf(sum[i]), 1);	//최초 등장
			}
		}
		System.out.print(count);
	}
}

