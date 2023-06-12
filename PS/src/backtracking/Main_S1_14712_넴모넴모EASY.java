package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 4칸을 만들면 게임이 종료되므로, 4칸이 되지 않는 상황들만 개수를 센다. 즉,
 * 전략: 4칸을 만들지 않는다.
 * 
 * [오답노트]
 * 아이디어 생각이 쉽지 않았다. 되려 너무 복잡하게 생각할 필요가 없지 않았나.. 싶기도..
 * "넴모"가 없으면 게임을 그만두기로 했다. 게임을 그만두었을 때 나올 수 있는 배치 수는? => 게임이 끝나지 않는 배치 수는? => 게임이 끝나는 경우만 제외하고 모두 세자!
 * 이런 방법으로 재귀를 사용할 수 있구나~ 라는 걸 느끼고 가자.
 */
public class Main_S1_14712_넴모넴모EASY {
	static int N, M, count;
	static boolean[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		map = new boolean[N+1][M+1];		//1 index 부터 시작. 계산 편하게 하기 위해서.
		
		go(0);
		System.out.println(count);
	}
	
	//cnt: 재귀타는 횟수
	private static void go(int cnt) {
		
		if(cnt == N * M) {
			count++;
			return;
		}
		
		int row = cnt / M + 1;
		int col = cnt % M + 1;
		
		//4개가 만들어지면 바로 넘겨야한다.
		//현재 [row][col] 기준으로 왼쪽, 왼쪽위, 위 칸들이 모두 true면 4개가 만들어지므로 그 자리에는 배치를 고려하지 않는다.
		//(놓거나/놓지않거나의 선택 행동을 하지 않음. 그냥 놓지마!)
		if(map[row][col-1] && map[row-1][col-1] && map[row-1][col]) {
			go(cnt+1);
		}else {
			map[row][col] = true;
			go(cnt+1);
			
			map[row][col] = false;
			go(cnt+1);
		}
		
		
		
		
	}
	
}
