package prefixsum;
/**
 * 누적합 배열을 구하는 방법이 핵심인 문제 : imos 법
 * i~j까지 value를 더하는 방법
 * (1) 0으로 초기화 된 누적합 배열을 만든다.
 * (2) sum[i]에 val을 더하고, sum[j+1]에 -val을 더한다.
 *     => 이렇게 한 다음에 sum의 누적합 배열을 구하면 i~j까지 value를 더한 형태가 된다.
 * (3) 원본 배열에 누적합 배열을 더해준다.
 */
import java.util.*;
import java.io.*;

public class Main_G5_19951_태상이의훈련소생활 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		int[] arr = new int[N+1];
		String[] inputs = br.readLine().split(" ");
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(inputs[i-1]);
		}
		
		int[] sum = new int[N+2];	//연병장 각 칸의 누적합을 저장할 배열
		
		//조교의 명령
		for(int i = 1; i<=M; i++) {
			String[] order = br.readLine().split(" ");
			int s = Integer.parseInt(order[0]);
			int e = Integer.parseInt(order[1]);
			int val = Integer.parseInt(order[2]);
			
			sum[s] += val;
			sum[e+1] += -val;
		}
		
		//누적합 배열 구하기
		for(int i = 1; i<=N; i++) {
			sum[i] = sum[i-1] + sum[i];
		}
		
		//원본 배열에 누적합 결과 더하기
		for(int i = 1; i<=N; i++) {
			arr[i] += sum[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<=N; i++) {
			sb.append(arr[i]).append(" ");
		}
		
		System.out.print(sb);
	}

}
