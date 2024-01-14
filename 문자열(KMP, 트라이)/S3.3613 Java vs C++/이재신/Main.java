import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;
    static boolean isJava = false;
    static boolean isLowerCase = true;

    public static boolean isCpp() {
        if (sb.charAt(sb.length() - 1) == '_') return false;
        else if (sb.charAt(0) == '_') return false;
        else return sb.indexOf("__") < 0;
    }

    public static boolean isJava() {
        return sb.charAt(0) < 'A' || sb.charAt(0) > 'Z';
    }

    public static boolean errorCheck() {
        boolean isUpperCase = false;
        boolean isUnderBar = false;

        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);

            if (ch >= 'A' && ch <= 'Z') isUpperCase = true;
            else if (ch == '_') isUnderBar = true;
        }

        // 대문자도 없고, 언더바도 없으면 다 소문자
        isLowerCase = !isUnderBar && !isUpperCase;

        // Java와 Cpp 형식을 모두 사용한 경우 false
        if (!(isUnderBar && isUpperCase)) {
            isJava = !isUnderBar;
            return true;
        } else return false;
    }

    public static void javaToCpp() {
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            // 대문자라면
            if (ch >= 'A' && ch <= 'Z') {
                // i번째 문자를 소문자로 바꾸고
                sb.replace(i, i + 1, String.valueOf(ch).toLowerCase());
                // 언더바를 추가
                sb.insert(i, "_");
            }
        }
    }

    public static void cppToJava() {
        for (int i = 0; i < sb.length(); i++) {
            // 언더바라면
            if (sb.charAt(i) == '_') {
                // 지우고
                sb.deleteCharAt(i);
                // i번째 문자를 대문자로 변경
                sb.replace(i, i + 1, String.valueOf(sb.charAt(i)).toUpperCase());
            }
        }
    }

    public static void solution() {
        // 어느 형식에도 포함되지 않는 경우
        if (!(isCpp() && isJava() && errorCheck())) {
            System.out.println("Error!");
            return;
        }

        // 모든 문자가 소문자일 경우 그대로 return
        if (isLowerCase) {
            System.out.println(sb);
            return;
        }

        if (isJava) javaToCpp();
        else cppToJava();

        System.out.println(sb);
    }

    public static void make() throws IOException {
        sb = new StringBuilder(br.readLine());
    }

    public static void main(String[] args) throws IOException {
        make();
        solution();
    }
}
