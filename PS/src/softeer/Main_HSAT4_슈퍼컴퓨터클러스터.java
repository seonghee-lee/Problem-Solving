package softeer;

import java.util.*;
import java.io.*;

public class Main_HSAT4_슈퍼컴퓨터클러스터 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nb = br.readLine().split(" ");
		int N = Integer.parseInt(nb[0]);
		long B = Long.parseLong(nb[1]);
		
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		long min = arr[0];	//최저 성능
		long max = arr[N-1] + (long)Math.sqrt(B);	//최고 성능
		long ans = 0;
		
		while(min <= max) {
			
			long mid = (min + max) / 2;
			
			//mid를 최저성능으로 했을 때 비용을 계산한다.
			boolean flag = true;
			long cost = 0;
			for(int i = 0; i<N; i++) {
				if(arr[i] <= mid) {
					long d2 = (long)(mid - arr[i]) * (long)(mid - arr[i]);
					cost += d2;
					if(cost > B) {
						flag = false;	//왜 이렇게 연산을 멈추지 않으면 오답인거지?? ... long형도 넘어서는건가..
						break;
					}
				}
			}
			
			if(!flag) {	//비용이 예산을 뛰어 넘는다면, 성능을 줄인다.
				max = mid - 1;
			} else {		//비용이 남는다면, 성능을 키운다.
				ans = mid;
				min = mid + 1;
			}
		}
		
		System.out.print(ans);
	}

}
