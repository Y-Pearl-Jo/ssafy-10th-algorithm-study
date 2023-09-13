// 84368kb 916ms
import java.util.*;
import java.io.*;

public class Main {
    // 전역 변수
    static int N, target;
    static long ans;
    static List<Integer> li1, li2;
    static int[] arr;

    // main ----------------------------
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정수의 개수
        target = Integer.parseInt(st.nextToken()); // 목표합

        // 배열에 정수 입력받기
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 부분 수열의 합
        li1 = new ArrayList<Integer>();
        func_sum(0,0,N/2,li1);

        li2 = new ArrayList<Integer>();
        func_sum(N/2,0,N,li2);

        // 투 포인터
        Collections.sort(li1);
        Collections.sort(li2);
        func_2p();

        // 정답 출력
        if(target==0) ans--;
        System.out.println(ans);

    }
    // main end ----------------------------

    // 부분 수열의 합
    static void func_sum(int idx, int sum, int end, List<Integer> li) {
        if(idx==end){
            li.add(sum);
            return;
        }
        func_sum(idx+1,sum+(arr[idx]),end,li);
        func_sum(idx+1,sum,end,li);

    }

    // 투 포인터
    static void func_2p(){
        // ans 구하기
        int left = 0; // li1 포인터(왼쪽)
        int right = li2.size()-1; // li2 포인터(오른쪽)

        // 두 포인터 모두 인덱스 범위일 때
        while(left<li1.size() && right>=0){
            int sum = li1.get(left) + li2.get(right);

            // 목표합과 같을 때
            if(sum==target){
                // 같은 수 세기
                long cnt1 = 0;
                for(int i=left; i<li1.size(); i++){
                    if(li1.get(i).equals(li1.get(left))) cnt1++;
                    else break;
                }

                long cnt2 = 0;
                for(int i=right; i>=0; i--){
                    if(li2.get(i).equals(li2.get(right))) cnt2++;
                    else break;
                }

                // 인덱스 조정
                left += (int) cnt1;
                right -= (int) cnt2;

                // 카운트
                ans += cnt1*cnt2;

            }
            // 목표합보다 작을 때
            else if(sum<target){
                left++;
            }
            // 목표합보다 클 때
            else{
                right--;
            }
        }

    }

}
