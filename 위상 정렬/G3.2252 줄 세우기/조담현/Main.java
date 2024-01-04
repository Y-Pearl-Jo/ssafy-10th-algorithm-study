import java.util.*;

public class Main {
	static int[] dp;
	static ArrayList<Integer>[] tree;
	static int[] visited;
	static int n;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		n = scan.nextInt();
		int m = scan.nextInt();

		int[] indegree = new int[n + 1];
		LinkedList<Integer>[] dag = new LinkedList[n + 1];

		for (int i = 0; i < m; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();

			if (dag[a] == null)
				dag[a] = new LinkedList<Integer>();

			dag[a].add(b);
			indegree[b]++;
		}
	
    	// indegree가 0인 경우 Queue에 넣고 -1로 만들어 준다. (방문 체크)
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
				indegree[i]--;
				System.out.print(i + " ");
			}
		}

		// 인접한 노드의 indegree 값을 1씩 감소시키고 0인 경우 Queue에 넣는다.
		while (!queue.isEmpty()) {
			int num = (int) queue.poll();
			LinkedList<Integer> l = dag[num];

			if (l == null)
				continue;

			for (Integer i : l) {
				indegree[i]--;

				if (indegree[i] == 0) {
					queue.add(i);
					System.out.print(i + " ");
				}
			}
		}
	}

}
