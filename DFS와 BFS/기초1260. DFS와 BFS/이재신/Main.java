import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static ArrayList<Integer>[] Graph;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;

	st = new StringTokenizer(br.readLine());

	int node = init(st.nextToken());
	int edge = init(st.nextToken());
	int start = init(st.nextToken());

	Graph = new ArrayList[node + 1];
	
	for (int i = 1; i < node + 1; i++) {
	    Graph[i] = new ArrayList<Integer>();
	}
	//그래프 생성
	for (int i = 0; i < edge; i++) {
	    st = new StringTokenizer(br.readLine());
	    int s = init(st.nextToken());
	    int e = init(st.nextToken());
	    Graph[s].add(e);
	    Graph[e].add(s);
	}
	//번호가 작은 노드부터 방문하기 위해 정렬
	for (int i = 1; i < node + 1; i++) {
	    Collections.sort(Graph[i]);
	}
	//DFS
	visited = new boolean[node + 1];
	DFS(start);
	System.out.println();
	//BFS
	visited = new boolean[node + 1];
	BFS(start);
    }

    static void DFS(int node) {
	//이번 노드 출력
	System.out.print(node + " ");
	//방문 처리
	visited[node] = true;
	//연결 된 노드들 탐색
	for (int i : Graph[node])
	    if (!visited[i])
		DFS(i);
    }

    static void BFS(int node) {
	//BFS는 큐로 구현
	Queue<Integer> queue = new LinkedList<>();
	//지금 방문 중인 노드 추가
	queue.offer(node);
	//방문 처리
	visited[node] = true;
	
	while(!queue.isEmpty()) {
	    //제일 앞에 있는 노드
	    int now = queue.poll();
	    //출력
	    System.out.print(now + " ");
	    //연결 된 노드들을 큐에 추가
	    for(int i : Graph[now])
		if(!visited[i]) {
		    visited[i] = true;
		    queue.offer(i);
		}
	}
    }

    static int init(String str) {
	return Integer.parseInt(str);
    }
}
