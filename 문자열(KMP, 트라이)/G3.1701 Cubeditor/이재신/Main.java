import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int max = 0;
    static String str;

    static int makeTable() {
        // 원본 문자열 길이
        int strLen = str.length();
        // 부분문자열 시작 인덱스
        for (int end = 0; end < strLen; end++) {
            // 부분문자열
            String part = str.substring(end);
            // 부분문자열의 길이
            int partLen = part.length();

            int[] pi = new int[partLen];
            int idx = 0;
            for (int i = 1; i < partLen; i++) {
                while (idx > 0 && part.charAt(i) != part.charAt(idx)) {
                    idx = pi[idx - 1];
                }
                if (part.charAt(i) == part.charAt(idx)) {
                    pi[i] = ++idx;
                    max = Math.max(max, idx);
                }
            }
        }
        return max;
    }

    static void make() throws IOException {
        str = br.readLine();
        makeTable();
        System.out.print(max);
    }

    public static void main(String[] args) throws IOException {
        make();
    }

    static int init() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int init(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}
