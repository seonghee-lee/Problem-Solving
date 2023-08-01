package implementation;

import java.util.*;
import java.io.*;

public class Main_G4_22856_트리순회 {
	static int N, end, count, ans;
	static int[][] tree;
	static List<Integer> inorderList = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new int[N+1][2];	//tree[i][0]: i노드의 왼자식, tree[i][1]: i노드의 오른자식
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int node = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			tree[node][0] = left;
			tree[node][1] = right;
		}
		
		//중위 순회할 때 마지막 노드 구하기
		inOrder(1);
		end = inorderList.get(inorderList.size() - 1);
		
		//유사 중위 순회
		similarInOrder(1);
		
		System.out.print(ans);
	}
	
	private static void similarInOrder(int node) {
		if(tree[node][0] != -1) {	//현재 위치한 노드의 왼쪽 자식 노드가 존재하고 아직 방문하지 않았다면,
			count++;	//왼쪽 자식 노드로 이동한다.
			similarInOrder(tree[node][0]);
		}
		
		if(tree[node][1] != -1) {	//그렇지 않고, 현재 위치한 노드의 오른쪽 자식 노드가 존재하고 아직 방문하지 않았따면
			count++;	//오른쪽 자식 노드로 이동한다.
			similarInOrder(tree[node][1]);
		}
		
		if(node == end) {	//그렇지 않고, 현재 노드가 유사 중위 순회의 끝이라면,
			ans = count;
			return;
		}
		
		else {	//그렇지 않고, 부모 노드가 존재한다면
			count++;	//부모 노드로 이동한다.
		}
	}
	
	private static void inOrder(int node) {
		if(tree[node][0] == -1 && tree[node][1] == -1) {	//리프 노드이면,
			inorderList.add(node);
		} else {
			if(tree[node][0] != -1) {
				inOrder(tree[node][0]);
			}
			inorderList.add(node);
			if(tree[node][1] != -1) {
				inOrder(tree[node][1]);
			}
		}
	}
}
