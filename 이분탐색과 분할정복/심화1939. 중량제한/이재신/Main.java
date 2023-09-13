import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int nIsland, nBridge;
	static int start_factory, end_factory;
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, mid;
	static boolean visited[];
	static ArrayList<int[]>[] bridge;

	static boolean BFS(int mid) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start_factory);
		visited[start_factory] = true;
		
		while(!q.isEmpty()) {
			int curIsland = q.poll();
			
			//무사히 목적지에 도착한 경우 true 반환
			if(curIsland == end_factory)
				return true;
			
			for(int i = 0; i < bridge[curIsland].size(); i++) {
				// 현재 중량제한보다 다리의 중량 제한이 크고 방문하지 않은 섬
				if(bridge[curIsland].get(i)[1] >= mid && !visited[bridge[curIsland].get(i)[0]]) {
					q.add(bridge[curIsland].get(i)[0]);
					visited[bridge[curIsland].get(i)[0]] = true;;
				}
			}
		}
		// 목적지에 도착하지 못하고 BFS가 끝난 경우 false 반환
		return false;
	}

	static void solution() {
		// 최종 결과값
		int result = 0;
		
		// 최솟값이 최댓값과 같아 질 때 까지
		while (min <= max) {
			visited = new boolean[nIsland + 1];
			mid = min + (max - min) / 2;

			// BFS가 true면 
			if (BFS(mid)) {
				min = mid + 1;
				result = mid;
			} else {
				max = mid - 1;
			}
		}

		System.out.println(result);
	}

	// 노드 : 섬 / 엣지 : 다리 / 가중치 : 중량제한
	static void setGraph() throws IOException {
		for (int i = 0; i < nBridge; i++) {
			st = new StringTokenizer(br.readLine());
			int island1 = init(st.nextToken());
			int island2 = init(st.nextToken());
			int limit = init(st.nextToken());
			//이분 탐색을 위한  최소 최대값 탐색
			min = Math.min(min, limit);
			max = Math.max(max, limit);

			bridge[island1].add(new int[] { island2, limit });
			bridge[island2].add(new int[] { island1, limit });
		}
	}

	// 그래프 인접 리스트 방식 생성
	static void makeGraph() throws IOException {
		bridge = new ArrayList[nIsland + 1];
		for (int i = 0; i <= nIsland; i++) {
			bridge[i] = new ArrayList<>();
		}
	}

	// 기본 변수 및 그래프 선언 및 초기화
	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		nIsland = init(st.nextToken());
		nBridge = init(st.nextToken());

		makeGraph();
		setGraph();

		st = new StringTokenizer(br.readLine());
		start_factory = init(st.nextToken());
		end_factory = init(st.nextToken());
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
