import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //단지 수 오름차순 출력을 위한 최소 힙
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    //사방 탐색
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    //방문 여부 및 지도 배열
    static int N;
    static int[][] map;
    static boolean[][] visited;
    //단지 수
    static int cnt;

    static Queue<int[]> q = new LinkedList<>();
    static void BFS(int r, int c) {
	cnt = 0;
	q.add(new int[] {r, c});
	visited[r][c] = true;
	cnt++;

	while (!q.isEmpty()) {
	    int[] now = q.poll();
	    for(int i = 0; i < 4; i++) {
		int x = now[0] + dr[i];
		int y = now[1] + dc[i];
		
		if(x >= 0 && x < N && y >= 0 && y < N) {
		    if(!visited[x][y] && map[x][y] == 1) {
			q.add(new int[] {x, y});
			visited[x][y] = true;
			cnt++;
		    }
		}
	    }
	}
	
	minHeap.add(cnt);
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;

	N = init(br.readLine());
	map = new int[N][N];
	visited = new boolean[N][N];

	for (int i = 0; i < N; i++) {
	    String str = br.readLine();
	    for (int j = 0; j < N; j++) {
		map[i][j] = init(str.charAt(j));
	    }
	}

	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
		if (map[i][j] == 1 && !visited[i][j]) {
		    BFS(i, j);
		}
	    }
	}

	System.out.println(minHeap.size());
	while (!minHeap.isEmpty()) {
	    System.out.println(minHeap.poll());
	}

    }

    static int init(String str) {
	return Integer.parseInt(str);
    }

    static int init(char c) {
	return c - '0';
    }
}
