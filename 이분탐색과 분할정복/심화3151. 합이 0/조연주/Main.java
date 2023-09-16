// 14320kb 352ms
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 학생 수
        long cnt = 0; // 정답 저장할 변수

        // 코딩 실력 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr); // 정렬

      
        // 첫번째 인덱스를 정한 뒤, 투 포인터로 두번째/세번째 인덱스 찾기
        for(int first=0; first<N-2; first++) {
            int second = first+1;
            int third = N-1;

            while(second<third) {
                int sum = arr[first]+arr[second]+arr[third];
                // 세 수의 합 : 0일 때
                if(sum==0) {
                    // second의 오른쪽, third의 왼쪽으로 같은 수가 얼마나 있는지 세어줘야 함
                    int lNum = arr[second];
                    int rNum = arr[third];
                  
                    // second ~ third 까지 모든 수가 같은 경우
                    if(lNum==rNum){
                        long n = third-second+1;
                        cnt += n*(n-1)/2; // nC2
                        break;
                    }
                      
                    // 아닌 경우
                    else{
                        long lCnt = 0;
                        long rCnt = 0;
                        while(second<N-1) {
                            if (arr[second] == lNum) {
//                                System.out.println("second = " + second);
                                lCnt++;
                                second++;
                            }
                            else{
                                break;
                            }
                        }
                        while(first+1<third) {
                            if (arr[third] == rNum) {
                                rCnt++;
                                third--;
                            }
                            else{
                                break;
                            }
                        }

                        cnt += lCnt * rCnt;
                    }

                }
                // 세 수의 합 : 0보다 작을 때
                else if(sum<0) {
                    second++;
                }
                // 세 수의 합 : 0보다 클 때
                else if(sum>0) {
                    third--;
                }

            }

        }

        // 정답 출력
        System.out.println(cnt);

    }

}
