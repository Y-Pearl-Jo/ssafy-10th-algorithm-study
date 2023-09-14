import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 1. DFS로 갈 수 있는 모든 경로의 수를 찾은 다음
 * 경로당 중량의 최솟값을 구해서 리스트에 저장한다.
 * 이 최솟값 리스트에서 최댓값을 찾는다.
 * DFS 시간복잡도 O(V+E)
 * 모든 경로의 경우의 수가 최악의 경우에는 n-2! (시작점과 목적지 제외,  n-2개의 출발점에서 그다음 n-3개를 갈 수 있고.. 이런식)
 * n-2!번 재귀해야하므로 시간초과

 * 2. 이분탐색으로 중량제한 C를 10억부터 설정하여 갈수 있는 시작점부터 도착점까지 갈 수 있는 경로가 있는지 BFS로 확인
 * 목적지에 도착할 수 있다면 업데이트 후 C를 더 큰값(right)에서 확인, 없다면 C를 더 작은 값(left)에서 확인
 * 시간복잡도는 O(log10억*(V+E)) log10억은 29
 */

// 섬의 정보를 저장하는 클래스
class Island {
	int depart;
	int arrival;
	int weight;
	
	Island(int depart, int arrival, int weight) {
		this.depart = depart;
		this.arrival = arrival;
		this.weight = weight;
	}
}

public class Main {
	
	static int N;
	static int M;
	static int max = Integer.MIN_VALUE;
	static int answer = Integer.MIN_VALUE;
	static ArrayList<ArrayList<Island>> list = new ArrayList<>();
	static int start;
	static int end;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<Island>());
		}

    // 이차원 리스트에 그래프(노드는 섬)의 정보를 저장한다.
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int depart = Integer.parseInt(st.nextToken());
			int arrival = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
      // 이분탐색의 범위를 찾기 위해 모든 섬 중 중량제한의 최댓값을 구한다.
			max = Math.max(max, weight);
			list.get(depart).add(new Island(depart, arrival, weight));
			list.get(arrival).add(new Island(arrival, depart, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		binarySearch(1,max);
		System.out.println(answer);
		
	}

  // bfs로 출발지부터 목적지까지, 갈 수 있는지 확인하는 메소드
	public static boolean bfs(int weight) {
    // 방문한 섬들을 체크하는 visited 배열
		boolean [] visited = new boolean[N+1];
		Queue<Island> queue = new LinkedList<>();
		visited[start] = true;
		queue.add(list.get(start).get(0));
		while(!queue.isEmpty()) {
			Island island = queue.poll();
			
			Iterator<Island> iter = list.get(island.depart).iterator();
			while(iter.hasNext()) {
				Island next = iter.next();
        // 방문하지 않았으며, 중량제한이 weight 이상인 섬만 방문한다.
				if(!visited[next.arrival]&&weight<=next.weight) {
					visited[next.arrival] = true;
					queue.add(list.get(next.arrival).get(0));
				}
			}
		}

    // 만약 도착지를 방문했다면 true
		if(visited[end]) {
			return true;
		}
		return false;
	}

  // 이분탐색으로 중량제한의 최댓값을 구한다.
	public static void binarySearch(int start, int end) {
		while(start<=end) {
			int mid = (start+end)/2;

      // 만약 mid에 해당하는 중량에서 방문할 수 있었다면, 더 중량이 큰 범위에서 찾아본다.
			if(bfs(mid)) { 
				answer = mid;
				start = mid+1;
				continue;
			}
			// 방문할 수 없었다면, 중량을 낮춰본다.
			end = mid -1;
		}
	}
}
