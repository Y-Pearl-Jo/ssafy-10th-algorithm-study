import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        
        // 시작이 대문자이거나 _이거나 마지막이 _이면 에러 
        if (str.charAt(0) <= '_' || str.charAt(str.length() - 1) == '_') {
            System.out.println("Error!");
            return;
        }
        
        // __, _A, A_ 이면 에러
        boolean upperCase = false; 
        boolean toJava = false; // false라면 c++, true라면 자바로 변환
        char pre = str.charAt(0); // 이전 문자 파악
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (ch <= 'Z') {
                if (toJava) { // _A로 끝나면 안되므로 _ 나왔는지 판단 
                    System.out.println("Error!");
                    return;
                } 
                upperCase = true; // 대문자 true 설정
            }
            
            if (ch == '_') {  // _이라면 toJava
                if (upperCase || pre == '_') { // 만약 대문자가 있다면 c++로만 바꿀 수 있으므로 에러
                    System.out.println("Error!");
                    return;
                }
                toJava = true; 
            }
            pre = ch;
        }
        
        
        if (toJava) { // 자바로 바꾼다면
            boolean nextUpper = false; // 다음문자가 대문자인지 체크
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (nextUpper) {
                    sb.append(String.valueOf(ch).toUpperCase()); 
                    nextUpper = false; 
                } else {
                    if (str.charAt(i) == '_') nextUpper = true;
                    else sb.append(ch);
                }
            }
        }
        else { // c++로 바꾼다면
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch <= 'Z') { 
                    sb.append("_"); 
                    sb.append(String.valueOf(ch).toLowerCase()); // "_소문자"로 바꾸기
                } else sb.append(ch);
            }            
        }
        
        System.out.println(sb);
    }
}
