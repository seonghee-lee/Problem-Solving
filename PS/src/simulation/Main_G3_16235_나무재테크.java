package simulation;

import java.util.*;
import java.io.*;

public class Main_G3_16235_나무재테크 {
	static int N, M, K;
	static int[][] A;	//겨울에 추가되는 양분
	static int[][] energy;
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static Deque<Tree> trees = new LinkedList<>();
	static List<Tree> dieTrees = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmk = br.readLine().split(" ");
		N = Integer.parseInt(nmk[0]);
		M = Integer.parseInt(nmk[1]);
		K = Integer.parseInt(nmk[2]);
		
		A = new int[N+1][N+1];
		energy = new int[N+1][N+1];
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j<=N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				energy[i][j] = 5;
			}
		}
		
		for(int m = 1; m<=M; m++) {
			String[] treeInput = br.readLine().split(" ");
			int x = Integer.parseInt(treeInput[0]);
			int y = Integer.parseInt(treeInput[1]);
			int age = Integer.parseInt(treeInput[2]);
			trees.add(new Tree(x, y, age));
		}
		
		for(int k = 1; k<=K; k++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		int count = trees.size();
		System.out.println(count);
	}
	
	private static void spring() {
		//모든 나무가 양분을 먹는다.
		for(int i = 0, size = trees.size(); i<size; i++) {
			Tree t = trees.poll();
			if(energy[t.r][t.c] < t.age) {	//양분을 먹지 못하고 죽음
				dieTrees.add(t);
			} else {
				energy[t.r][t.c] -= t.age;	//자신의 나이만큼 양분을 먹고
				t.age += 1;		//나이가 1증가한다.
				trees.add(t);
			}
		}
	}
	
	private static void summer() {
		//올해 봄에 죽은 나무가 양분으로 변한다.
		for(Tree t : dieTrees) {
			energy[t.r][t.c] += (int)(t.age / 2);
		}
		
		dieTrees.clear();
	}
	
	private static void fall() {
		//나이가 5의 배수인 나무가 인접 8칸에 번식한다.
		int nr, nc;
		List<Tree> temp = new ArrayList<>();	//나이가 5의 배수인 나무들
		
		for(Tree t : trees) {
			if(t.age % 5 == 0) {
				temp.add(t);
			}
		}
		
		for(Tree t : temp) {
			for(int d = 0; d<8; d++) {
				nr = t.r + dr[d];
				nc = t.c + dc[d];
				
				if(nr <= 0 || nr > N || nc <= 0 || nc > N) continue;
				trees.addFirst(new Tree(nr, nc, 1));
			}
		}
	}
	
	private static void winter() {
		//땅에 양분을 추가한다.
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				energy[i][j] += A[i][j];
			}
		}
	}
}

class Tree {
	int r, c, age;
	Tree(int r, int c, int age){
		this.r = r;
		this.c = c;
		this.age = age;
	}
}
