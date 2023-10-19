import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	HashMap<String, Node> children = new HashMap<>();
}

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Node root = new Node();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			Node parent = root;
			for (int j = 0; j < K; j++) {
				String s = st.nextToken();
				parent.children.putIfAbsent(s, new Node());
				parent = parent.children.get(s);
			}
		}

		print(root, 0);
		
		System.out.println(sb.toString());
	}

	private static void print(Node node, int depth) {
		
		List<String> words = new ArrayList<>(node.children.keySet());
		Collections.sort(words);
		
		
		for(int i=0; i<words.size(); i++) {
			for(int j=0; j<depth; j++) {
				sb.append("--");
			}
			sb.append(words.get(i)).append('\n');
			print(node.children.get(words.get(i)), depth+1);
		}
	}
}
