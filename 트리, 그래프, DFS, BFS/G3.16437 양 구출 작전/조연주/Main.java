import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static long ans;
	static Node[] nodes;
	static ArrayList<Integer>[] child;
	
	public static void main(String[] args) throws IOException {
		N = input(); // 섬 개수
		nodes = new Node[N+1];
		child = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			child[i] = new ArrayList<Integer>();
		}
		
		nodes[1] = new Node("S",0);
		
		// 2번 ~ N번 섬
		for(int i=2; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			long cnt = input(st);
			int parent = input(st);
			
			nodes[i] = new Node(s,cnt);
			child[parent].add(i);
		}
		
		System.out.println(DFS(1));
	
		
	}
	
	static long DFS(int idx) {	
		long sum = 0;

		for(int i=0; i<child[idx].size(); i++) {
			sum += DFS(child[idx].get(i));
		}
		
		if(nodes[idx].animal.equals("S")) {
			sum += nodes[idx].cnt;
		}
		else {
			sum -= nodes[idx].cnt;
		}
		
		if(sum<0) sum = 0;
		return sum;
		
	}
	
	static int input() throws IOException{
		return Integer.parseInt(br.readLine());
	}
	
	static int input(StringTokenizer st){
		return Integer.parseInt(st.nextToken());
	}
}

class Node{
	String animal;
	long cnt;
	public Node(String a, long c) {
		animal = a;
		cnt = c;
	}
}
