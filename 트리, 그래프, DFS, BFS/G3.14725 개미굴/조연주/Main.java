// 18996kb 272ms
import java.io.*;
import java.util.*;

class TrieNode{
    Map<String, TrieNode> childNodes = new HashMap<>();
    boolean isLastChar;
}

public class Main {
    static int N;
    static TrieNode rootNode = new TrieNode();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = input(br.readLine());

        // 입력
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int n = input(st.nextToken());
            insert(st);
        }

        // 출력
        print(rootNode,0);
        bw.flush();
        bw.close();
    }

    static void insert(StringTokenizer st){
        TrieNode thisNode = rootNode;

        while(st.hasMoreTokens()){
            thisNode = thisNode.childNodes.computeIfAbsent(st.nextToken(), c-> new TrieNode());
        }
        thisNode.isLastChar = true;
    }

    static void print(TrieNode node, int cnt) throws IOException {
        List<String> word = new ArrayList<>(node.childNodes.keySet());
        Collections.sort(word);

        for (String s : word) {
            for (int k=0; k<cnt; k++) {
                bw.write("--");
            }
            bw.write(s + "\n");

            print(node.childNodes.get(s), cnt + 1);
        }
    }

    static int input(String s){
        return Integer.parseInt(s);
    }
}
