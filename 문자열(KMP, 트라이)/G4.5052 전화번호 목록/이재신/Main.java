import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static TrieNode trie;
    static List<String> phNumList;

    static class TrieNode {
        Map<Character, TrieNode> childNode = new HashMap<>();
        boolean isLastChar;

        TrieNode() {
        }

        public void insert(String word) {
            TrieNode thisNode = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                thisNode.childNode.putIfAbsent(c, new TrieNode());
                thisNode = thisNode.childNode.get(c);

                if (i == word.length() - 1) {
                    thisNode.isLastChar = true;
                    return;
                }
            }
        }

        public boolean contains(String word) {
            TrieNode thisNode = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode node = thisNode.childNode.get(c);

                if (node == null) {
                    return false;
                }
                thisNode = node;
            }

            // 해당 단어로 종료하는 문자가 있는 경우 false
            if (thisNode.isLastChar) {
                return !thisNode.childNode.isEmpty();
            }
            return true;
        }
    }

    public static void solution() {
        // 일관성 여부
        boolean isConsist = true;
        for (String phNum : phNumList) {
            if (trie.contains(phNum)) {
                isConsist = false;
                break;
            }
        }
        sb.append(isConsist ? "YES" : "NO").append("\n");
    }

    public static void make() throws IOException {
        // 전화번호 개수
        int N = init();
        trie = new TrieNode();
        // 전화번호 목록
        phNumList = new ArrayList<>();
        for (int j = 0; j < N; j++) {
            // 전화번호
            String phNum = br.readLine();
            trie.insert(phNum);
            phNumList.add(phNum);
        }
    }

    public static void main(String[] args) throws IOException {
        int T = init();
        for (int tc = 0; tc < T; tc++) {
            make();
            solution();
        }
        System.out.print(sb);
    }

    static int init() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int init(int n) {
        return Integer.parseInt(st.nextToken());
    }
}
