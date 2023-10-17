import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb;
	static StringTokenizer st;
	static TrieNode trie = new TrieNode();
	// 메모리 15928 KB, 시간  292 ms
	static class TrieNode {
		Map<String, TrieNode> childNode = new HashMap<>();

		TrieNode() {
		}

		public void insert(String strs) {
			// 시작은 루트노드
			// 루트 노드는 공백
			TrieNode trieNode = this;
			String[] str = strs.split(",");
			// 이번에 입력 받은 먹이 정보 순회
			for (String s : str) {
				// putIfAbsent(key, value) : 기존데이터에 key가 없으면 저장
				trieNode.childNode.putIfAbsent(s, new TrieNode());
				// 새로 생성된 trieNode를 가져오기
				trieNode = trieNode.childNode.get(s);
			}
		}

		public void print(TrieNode cur, int depth) {
			// 이번에 뭘 출력할까
			TrieNode trieNode = cur;
			// 상위 노드부터 출력 될 거임
			if (trieNode.childNode != null) {
				// 연결된 자식 노드가 무엇이 있는지 list 생성
				List<String> list = new ArrayList<>(trieNode.childNode.keySet());
				// 문제 조건에 따라 사전 순 정렬
				Collections.sort(list);
				// list를 순회하며 dfs 형식의 재귀 탐색
				for (String str : list) {
					// 현재 차수에 따라 "--" 출력 
					for (int i = 0; i < depth; i++) {
						System.out.print("--");
					}
					System.out.println(str);
					// 다음 자식노드로 이동
					print(trieNode.childNode.get(str), depth + 1);
				}
			}
		}
	}

	static void make() throws IOException {
		// 먹이의 정보 개수
		int N = init();

		for (int i = 0; i < N; i++) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			// 먹이 정보 개수
			int K = init(st);

			for (int j = 0; j < K; j++) {
				sb.append(st.nextToken()).append(",");
			}

			trie.insert(sb.toString());
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		trie.print(trie, 0);
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
