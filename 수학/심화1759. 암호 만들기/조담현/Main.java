import java.io.BufferedReader;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성
//오름차순
//중복 x
public class Main {
	static String[] vowels = { "a", "e", "i", "o", "u" };
	static String[] consonants = { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v",
			"w", "x", "y", "z" };
	static String[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		arr = new String[C];
		visited = new boolean[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken();
		}
		// 사전 순으로 탐색하기 위해 배열에 저장 후 정렬
		Arrays.sort(arr);

		combination(arr, visited, 0, C, L);
	}

	// 조합 구현
	public static void combination(String[] arr, boolean[] visited, int start, int n, int r) {
		if (r == 0) {
			print(arr, visited, n);
		}
		for (int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}

	// 출력 구현
	public static void print(String[] arr, boolean[] visited, int n) {
		int vowelNum = 0;
		int consonantNum = 0;
		// 모음과 자음 카운트
		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				if(Arrays.asList(vowels).contains(arr[i])) {
					vowelNum++;
				}else if (Arrays.asList(consonants).contains(arr[i])) {
					consonantNum++;
				}
			} 
		}
		// 만약 모음 1개, 자음 2개 이상이면 방문한 노드 출력
		if (vowelNum >= 1 && consonantNum >= 2) {
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					System.out.print(arr[i]);
				}
			}
			System.out.println();
		}
	}
}
