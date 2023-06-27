package graph;

import java.util.*;
import java.io.*;

/*
 * 1번에서 dfs : 리프노드에 도달했을 때 sum을 각각 저장한다.
 * 가장 큰 sum을 갖는 노드를 알아낸다.
 * 그 노드에서 dfs를 다시 시도한다.
 * 리프노드에 도달했을 때 최대 sum을 구한다. => 정답
 * */

public class Main_G4_1967_트리의지름 {
	static int N, max;
	static boolean[] visited;
	static boolean[] parents;
	static int maxNode;
	static ArrayList<tNode>[] tree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N + 1];
		for(int i = 1; i<=N; i++) {
			tree[i] = new ArrayList<tNode>();
		}
		
		parents = new boolean[N+1];
		
		for(int i = 1; i<=N-1; i++) {
			String[] inputs = br.readLine().split(" ");
			int parent = Integer.parseInt(inputs[0]);
			int child = Integer.parseInt(inputs[1]);
			int weight = Integer.parseInt(inputs[2]);
			tree[parent].add(new tNode(child, weight));
			tree[child].add(new tNode(parent, weight));
			parents[parent] = true;
		}
		
		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		max = Integer.MIN_VALUE;
		visited = new boolean[N+1];
		visited[1] = true;
		findMax(1, 0); //루트 노드에서 시작
		
//		System.out.printf("maxNode: %d\n", maxNode);

		max = Integer.MIN_VALUE;
		visited = new boolean[N+1];
		visited[maxNode] = true;
		findMax(maxNode, 0);
		
//		System.out.printf("maxNode: %d\n", maxNode);
		
		System.out.print(max);
		
	}
	
	private static void findMax(int index, int sum) {
		
		if(index == 1 && tree[index].size() == 1) {
			max = sum;
			maxNode = index;
		}
		
		if(!parents[index] && sum != 0) {	//리프노드이면,
			if(sum > max) {
				max = sum;
				maxNode = index;
			}
			return;
		}
		
		for(int i = 0, len = tree[index].size(); i<len; i++) {
			if(visited[tree[index].get(i).idx]) continue;
			visited[tree[index].get(i).idx] = true; 
			findMax(tree[index].get(i).idx, sum + tree[index].get(i).weight);
		}
	}
}

class tNode{
	int idx, weight;
	tNode(int idx, int weight){
		this.idx = idx;
		this.weight = weight;
	}
}
