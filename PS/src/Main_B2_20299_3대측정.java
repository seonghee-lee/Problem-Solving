import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2_20299_3대측정 {
	static int N, S, M;	//신청한 동아리 수, 팀원 3명 능력에 대한 스마트클럽 가입조건, 개인 능력치에 대한 가입조건
	static int[][] map;	//능력치 입력
	public static void main(String[] args) throws Exception{	//메인 시작
//		System.setIn(new FileInputStream("res/스마트클럽_input_제공파일.txt"));		//파일 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		//표준입력
		StringTokenizer st = new StringTokenizer(br.readLine());	//끊어 읽기
		N = Integer.parseInt(st.nextToken());	//n입력
		S = Integer.parseInt(st.nextToken());	//s입력
		M = Integer.parseInt(st.nextToken());	//m입력
		
		map = new int[N][3];		//배열 생성
		
		for(int i = 0; i<N; i++) {		//n명만큼 반복하며 능력치 입력 받음
			st = new StringTokenizer(br.readLine());	//끊어 읽기
			for(int j = 0; j<3; j++) {		//3명으로 팀원 고정
				map[i][j] = Integer.parseInt(st.nextToken());		//입력
			}
		}
		
		boolean[] result = new boolean[N];	//가능하면 TRUE, 불가능하면 FALSE를 담는다
		int count = 0;		//가능한 동아리 개수 저장
		
		for(int i = 0; i<N; i++) {	//동아리 개수만큼 돌면서
			int scoreSum = 0;		//해당 동아리의 능력치 합을 저장하기 위한 변수
			for(int j = 0; j<3; j++) {		//3명의 능력치를 본다
				if(map[i][j] < M) {			//개인의 능력치가 m보다 작으면
					result[i] = false;		//해당 동아리리는 개설될 수 없다
					break;					//다음 동아리로 넘어감
				}else {						//개인 능력치를 만족한경우
					scoreSum += map[i][j];	//팀 능력치 연산을 위해 개인 능력치를 더해준다.
				}
				if(j == 2 && scoreSum >= S) {		//3번째 팀원까지 왔을 때 팀 능력치의 합이 조건을 넘는다면
					result[i] = true;				//개설할 수 있는 동아리이다.
					count++;						//개설할 수 있는 동아리수 늘려주기
				}
			}
		}
		
        StringBuilder sb = new StringBuilder(100);
		//System.out.println(count);		//가능한 동아리 수 출력
        sb.append(count).append("\n");
		
		for(int i = 0; i<N; i++) {		//n개의 동아리를 돌면서
			if(result[i]) {				//만약 가능한 동아리라면
				//System.out.printf("%d %d %d ",map[i][0], map[i][1], map[i][2]);		//해당 동아리 팀원의 각 능력치를 출력한다.
			    sb.append(map[i][0]).append(" ").append(map[i][1]).append(" ").append(map[i][2]).append(" ");
            }
		}
        
        System.out.print(sb.toString().trim());
	}
}