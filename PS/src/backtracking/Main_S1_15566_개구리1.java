package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 개구리 : 대화에 대한 흥미도 존재, 선호하는 연꽃 존재
 * 연꽃 : 연결된 연꽃 존재, 연결된 연꽃(통나무)에 따라 대화 주제가 정해져있음
 * 개구리를 연꽃에 배치했을 때 원활하게 배치되고, 대화가 이루어질 수 있는가? => 있다면 배치를, 없다면 NO를 출력
 * 
 * 배치 -> 현재 개구리가 선호하는 연꽃이 아니면 안된다.
 * 배치에 따른 개구리가 대화할 수 있는 지 확인
 */
public class Main_S1_15566_개구리1 {
	static int N, M;
	static int[][] interest;		//개구리의 대화 흥미도
	static int[][] likeLeaf;		//개구리가 선호하는 연꽃잎
	static int[][] log;				//연결된 통나무 대화 주제
	static boolean[] visited;		//사용된 연꽃잎
	static int[] set;
	static boolean finish;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		interest = new int[N][4];
		likeLeaf = new int[N][2];
		log = new int[M][3];
		visited = new boolean[N+1];
		set = new int[N+1];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			interest[i][0] = Integer.parseInt(st.nextToken());
			interest[i][1] = Integer.parseInt(st.nextToken());
			interest[i][2] = Integer.parseInt(st.nextToken());
			interest[i][3] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			likeLeaf[i][0] = Integer.parseInt(st.nextToken());
			likeLeaf[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			log[i][0] = Integer.parseInt(st.nextToken());
			log[i][1] = Integer.parseInt(st.nextToken());
			log[i][2] = Integer.parseInt(st.nextToken());
		}
		
		go(0);
		if(!finish) System.out.println("NO");	
	}
	
	//cnt: 개구리번호
	private static void go(int cnt) {
		
		//개구리를 모두 배치했다면,
		if(cnt == N) {
			boolean possible = true;
			//통나무만큼 반복한다.
			for(int i = 0; i<M; i++) {
				//대화가 되려면, 연결된 연꽃잎에 있는 개구리들이 해당 대화주제에 동일한 흥미도를 갖고 있어야 한다.
				if(interest[set[log[i][0]]][log[i][2]-1] != interest[set[log[i][1]]][log[i][2]-1]) {
					possible = false;
					break;
				}
			}
			
			//가능한 배치라면
			if(possible) {
				StringBuilder result = new StringBuilder(30);
				result.append("YES").append("\n");
				for(int i = 1; i<=N; i++) {
					result.append(set[i] + 1).append(" ");
				}
				System.out.println(result);
				finish = true;
			}
			return;
		}
		
		//연꽃잎만큼 반복한다.
		for(int i = 1; i<=N; i++) {
			if(visited[i]) continue;	//사용된 연꽃잎이라면 continue
			
			//개구리가 선호하는 연꽃잎일때만 배치
			if(i == likeLeaf[cnt][0] || i == likeLeaf[cnt][1]) {
				visited[i] = true;
				set[i] = cnt;	//개구리번호 1번부터 시작
				go(cnt+1);
				visited[i] = false;
			}
		}
		
		if(finish) return;
	}
}
