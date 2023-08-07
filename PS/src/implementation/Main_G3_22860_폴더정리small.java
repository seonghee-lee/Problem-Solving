package implementation;

import java.util.*;
import java.io.*;

public class Main_G3_22860_폴더정리small {
	static int N, M;
	static int category, total;
	static HashMap<String, FolderInfo> map;
	static Set<String> set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		
		N = Integer.parseInt(NM[0]);	//main 하위의 폴더의 총 개수
		M = Integer.parseInt(NM[1]);	//main 하위의 파일의 총 개수
		
		map = new HashMap<>();
		
		for(int i = 0; i<N+M; i++) {
			String[] inputs = br.readLine().split(" ");
			String P = inputs[0];	//상위 폴더의 이름
			String F = inputs[1];	//폴더 또는 파일의 이름
			int C = Integer.parseInt(inputs[2]);	//폴더: 1, 파일: 0
			
			if(C == 1) {
				if(map.containsKey(P)) {
					map.get(P).childFolderList.add(F);
					if(!map.containsKey(F)) {
						map.put(F, new FolderInfo());
					}
				} else {
					map.put(P, new FolderInfo());
					map.get(P).childFolderList.add(F);
					if(!map.containsKey(F)) {
						map.put(F, new FolderInfo());
					}
				}
			} else {
				if(map.containsKey(P)) {
					map.get(P).fileList.add(F);
				} else {
					map.put(P, new FolderInfo());
					map.get(P).fileList.add(F);
				}
			}
		}
		
		int Q = Integer.parseInt(br.readLine());	//쿼리의 개수
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<Q; i++) {
			category = 0;	//파일 종류의 개수
			total = 0;		//파일의 총 개수
			String[] query = br.readLine().split("/");
			String fName = query[query.length - 1];
			
			set = new HashSet<>();
			
			go(fName);
			
			category = set.size();
			
			sb.append(category).append(" ").append(total).append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static void go(String name) {
		
		FolderInfo folder = map.get(name);
		if(!folder.fileList.isEmpty()) {
			for(String file : folder.fileList) {
				total++;
				set.add(file);
			}
		}
		
		if(!folder.childFolderList.isEmpty()) {
			for(String next : folder.childFolderList) {
				go(next);
			}
		}
	}
}

class FolderInfo{
	List<String> childFolderList = new ArrayList<>();
	List<String> fileList = new ArrayList<>();
}
