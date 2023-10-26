/*
 * 
 1. 늑대는 가중치를 *0.5, *2를 계속 곱하여 더하기 때문에 처음에 여우와 늑대의 거리를 받을때 *2하여 짝수로 받는다.  
 2. 달빛여우는 노드 1에서 시작하는 정석 다익스트라
 3. 달빛늑대는 홀수번째에 0.5의 가중치, 짝수번째에 *2의 가중치로 이동한다.
 4. 그렇다면  짝수번째에 가중치가 큰 노드를 방문할 때에는 최단거리가 아닐 수 있다.
 4-1. 예시 1)1 3 500, 1 2 1
 4-2. 예시 2) 
		1 2 1 
		2 3 2
		3 1 3
		1 4 4
		4 5 500 
 5. 홀수번째 방문할때(홀수개의 사이클)와 짝수번째 방문할 때 거리를 따로 저장하자.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable {
	int index;
	int cost;
	int oddEven;

	Node(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}

	@Override
	public int compareTo(Object o) {
		return (int) (cost - ((Node) o).cost);
	}
}

public class Main {
	static int N, M;
	static int[] distFox;
	static int[][] distWolf;
	static int answer;
	static List<List<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		answer = 0;
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Node>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			// 늑대는 가중치를 *0.5, *2를 계속 곱하여 더하기 때문에 처음에 여우와 늑대의 거리를 받을때 *2하여 짝수로 받는다. 
			graph.get(a).add(new Node(b, d * 2));
			graph.get(b).add(new Node(a, d * 2));

		}

		dijkstraFox();
		dijkstraWolf();

		for(int i=2; i<=N; i++) {
			if(distFox[i]<Math.min(distWolf[i][0], distWolf[i][1])) answer++;
		}

		System.out.println(answer);

	}
	
	// 달빛여우는 노드 1에서 시작하는 정석 다익스트라
	private static void dijkstraFox() {
		distFox = new int[N + 1];
		Arrays.fill(distFox, Integer.MAX_VALUE);

		PriorityQueue<Node> pq = new PriorityQueue<>();
		distFox[1] = 0;
		pq.add(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 지금까지 방문해온 누적 거리보다 현재 저장한 거리가 이미 최단거리인 경우 갱신하지 않는다.
			if (distFox[cur.index] < cur.cost)
				continue;

			for (Node next : graph.get(cur.index)) {
				if (distFox[next.index] > distFox[cur.index] + next.cost) {
					distFox[next.index] = distFox[cur.index] + next.cost;
					pq.add(new Node(next.index, distFox[next.index]));
				}
			}
		}

	}
	
	private static void dijkstraWolf() {
		// 달빛늑대는 홀수번째에 0.5의 가중치, 짝수번째에 *2의 가중치로 이동한다.
		// 홀수번째 방문할때(홀수개의 사이클)와 짝수번째 방문할 때 거리를 따로 저장하자.
		distWolf = new int[N + 1][2];
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(distWolf[i], Integer.MAX_VALUE);
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		distWolf[1][0] = 0; // 시작점은 0이므로 짝수
		Node start = new Node(1, 0);
		start.oddEven = 0; // 짝수번째 방문했다는 정보를 노드에 저장
		pq.add(start);
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			// 홀수번째 방문할 경우
			if (cur.oddEven == 1) {
				
				// 지금까지 방문해온 누적 거리보다 현재 저장한 거리가 이미 최단거리인 경우 갱신하지 않는다.
				if (distWolf[cur.index][1] < cur.cost)
					continue;

				// 다음번은 짝수이므로 2배 느리게, 즉 거리가 2배이다.
				for (Node next : graph.get(cur.index)) {
					if (distWolf[next.index][0] > distWolf[cur.index][1] + next.cost * 2) {
						distWolf[next.index][0] = distWolf[cur.index][1] + next.cost * 2;
						Node newNode = new Node(next.index, distWolf[next.index][0]);
						// // 현재 업데이트한 노드를 짝수번째에 방문했음을 저장하고 큐에 삽입한다.
						newNode.oddEven = 0;
						pq.add(newNode);
					}
				}
			}
			// 짝수번째 방문할 경우
			else {
				
				// 지금까지 방문해온 누적 거리보다 현재 저장한 거리가 이미 최단거리인 경우 갱신하지 않는다.
				if (distWolf[cur.index][0] < cur.cost)
					continue;

				// 다음번은 홀수이므로 2배 빠르게, 즉 거리가 1/2이다.
				for (Node next : graph.get(cur.index)) {
					if (distWolf[next.index][1] > distWolf[cur.index][0] + next.cost / 2) {
						distWolf[next.index][1] = distWolf[cur.index][0] + next.cost / 2;
						Node newNode = new Node(next.index, distWolf[next.index][1]);
						// 현재 업데이트한 노드를 홀수번째에 방문했음을 저장하고 큐에 삽입한다.
						newNode.oddEven = 1;
						pq.add(newNode);
					}
				}
			}

		}
	}

}
