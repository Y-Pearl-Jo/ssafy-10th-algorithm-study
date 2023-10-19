import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class island {
	char animal;
	int number;

	public island(char animal, int number) {
		super();
		this.animal = animal;
		this.number = number;
	}
}

public class Main {
	static int N;
	static ArrayList<ArrayList<Integer>> graph;
	static island[] islands;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 섬 갯수
		N = Integer.parseInt(br.readLine());

		// 선언
		graph = new ArrayList<>();
		// 섬 정보를 받을 배열
		islands = new island[N + 1];

		// index 햇갈리니까 앞에 0 신경안쓰고 인덱스랑 섬 번호 맞출거임
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 2; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			char animal = st.nextToken().charAt(0);
			int number = Integer.parseInt(st.nextToken());
			int islandNum = Integer.parseInt(st.nextToken());

			islands[i] = new island(animal, number);
			// 그림 그려보면 이렇게 넣어야 트리가 됨
			graph.get(islandNum).add(i);
		}

		// index 오류를 피하고자 1에 임의의 값 넣은거임
		islands[1] = new island('S', 0);

		System.out.println(dfs(1));

	}

	static long dfs(int index) {
		long result = 0;
		for (int i = 0; i < graph.get(index).size(); i++) {
			// 자식노드를 더해가며 결과값을 result에 넣음
			result += dfs(graph.get(index).get(i));
		}
		if (islands[index].animal == 'S') {
			result += islands[index].number;
		} else {
			result -= islands[index].number;
		}
		if (result < 0) {
			result = 0;
		}
		return result;
	}
}
