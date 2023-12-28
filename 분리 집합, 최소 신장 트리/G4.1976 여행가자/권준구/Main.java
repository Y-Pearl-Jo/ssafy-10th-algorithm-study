import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] parents;
	static int[] root;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		make();
		
		root = new int[M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) union(i, j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			root[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		boolean flag = true;
		
		for(int i = 1 ; i < M ; i++) {
			if(parents[root[i]] != parents[root[i-1]]) flag = false;
		}
		
		if(flag) System.out.println("YES");
		else System.out.println("NO");
	}
	
	private static void union(int x, int y) {
		int rx = find(x);
		int ry = find(y);
		
		if(rx < ry) parents[ry] = rx;
		else if(rx > ry) parents[rx] = ry;
	}
	
	private static int find(int x) {
		if(x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	private static void make() {
		parents = new int[N];
		for(int i = 0; i < N ; i++) {
			parents[i] = i;
		}
	}

}
