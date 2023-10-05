// 11544kb 80ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{
    static int N,ans;
    static ArrayList<Integer> num;
    static ArrayList<Character> opr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 수식의 길이
        ans = Integer.MIN_VALUE; // 이거 못함 1
        num = new ArrayList<>();
        opr = new ArrayList<>();

        // 수식 입력받기
        String str = br.readLine();
        for(int i=0; i<str.length(); i++) {
            if(i%2==0) {
                num.add(str.charAt(i)-'0');
            }
            else {
                opr.add(str.charAt(i));
            }
        }
      
        DFS(0, num.get(0));
        System.out.println(ans);

    }

    // res : 이전 연산 결과
    static void DFS(int idx, int res){
        // 종료 조건
        if(idx==opr.size()){
            ans = Math.max(ans,res);
            return;
        }

        // 현재 연산을 먼저 하는 경우
        int now = calc(opr.get(idx), res, num.get(idx+1));
        DFS(idx+1, now);

        // 건너뛰고 다음 연산을 하는 경우 -> 2번 연속 X
        if(idx+1<opr.size()){
            int next = calc(opr.get(idx+1), num.get(idx+1), num.get(idx+2));
            int nowww = calc(opr.get(idx), res, next); // 이거 못함2
            DFS(idx+2, nowww);
        }
    }

    static int calc(char o, int n1, int n2){
        if (o=='+') {
            return n1+n2;
        }
        else if(o=='-'){
            return n1-n2;
        }
        else {
            return n1*n2;
        }
    }
}
