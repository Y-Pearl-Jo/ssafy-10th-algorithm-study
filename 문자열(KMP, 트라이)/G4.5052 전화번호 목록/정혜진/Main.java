//113700KB	360ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine()); // 전화번호수
            TrieNode root = new TrieNode();

            boolean isAnswer = true;
            for (int i = 0; i < n; i++) {
                String num = br.readLine();
                if (!root.insert(num)) {
                    isAnswer = false;
                    // break; 여기 break문 넣으면 런타임에러(NullPointer) 난다.
                }
            }
            System.out.println(isAnswer ? "YES" : "NO");

        }//tc


    }


}

class TrieNode {
    private TrieNode[] children;
    private boolean isEndOfWord;

    // TrieNode 생성자
    public TrieNode() {
        children = new TrieNode[10];
        isEndOfWord = false;
    }

    // 단어 삽입
    public boolean insert(String word) {
        TrieNode current = this;
        for (char ch : word.toCharArray()) {
            int index = ch - '0';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
            if (current.isEndOfWord) {
                return false; // 이미 존재하는 번호의 접두사임
            }
        }

        // 모든 자식 노드를 검사하여 현재 번호가 다른 번호의 접두사인지 확인
        for (TrieNode child : current.children) {
            if (child != null) {
                return false;
            }
        }


        current.isEndOfWord = true;
        return true;
    }

}
