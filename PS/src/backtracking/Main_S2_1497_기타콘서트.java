package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_S2_1497_기타콘서트 {
	static int N, M, song, guitar;
	static char[][] inputs;
	static boolean[] selected;
	static boolean possible;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inputs = new char[N][M];
		selected = new boolean[N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			st.nextToken();	//기타 이름 버림
			inputs[i] = st.nextToken().toCharArray();
		}
		
		guitar = Integer.MAX_VALUE;
		subset(0, 0);
		if(song == 0) {
			System.out.println("-1");
		}else {
			System.out.println(guitar);
		}
	}

	private static void subset(int index, int count) {
		if(index == N) {
			int[] temp = new int[M];
			for(int i = 0; i<N; i++) {
				if(selected[i]) {
					for(int j = 0; j<M; j++) {
						int value = 0;
						if(inputs[i][j] == 'Y') value = 1;
						temp[j] += value;
					}
				}
			}
			
			//연주할 수 있는 기타곡의 수가 늘어났을 경우에만 필요한 기타 수를 갱신한다.
			int curSong = 0;
			int curGuitar = count;
			for(int i = 0; i<M; i++) {
				if(temp[i] > 0) {
					curSong++;
				}
			}
			
			if(curSong >= song) {
				guitar = Math.min(guitar, curGuitar);
				song = curSong;
			}
			
			return;
		}
		selected[index] = true;
		subset(index+1, count+1);
		
		selected[index] = false;
		subset(index+1, count);
	}

}
