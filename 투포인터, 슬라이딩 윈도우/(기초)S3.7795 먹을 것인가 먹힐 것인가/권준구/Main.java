import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int T; // 테스트 케이스의 수
    public static int N, M; // 두 배열의 길이
    public static int[] Aarr, Barr; // 두 배열
    public static int answer = 0; // 정답을 저장하는 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken()); // 테스트 케이스 수 입력

        for (int tc = 1; tc <= T; tc++) { // 각 테스트 케이스에 대해 반복
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 첫 번째 배열의 길이
            M = Integer.parseInt(st.nextToken()); // 두 번째 배열의 길이

            Aarr = new int[N]; // 첫 번째 배열
            Barr = new int[M]; // 두 번째 배열

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Aarr[i] = Integer.parseInt(st.nextToken()); // 첫 번째 배열 입력
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                Barr[i] = Integer.parseInt(st.nextToken()); // 두 번째 배열 입력
            }
            Arrays.sort(Barr); // 두 번째 배열을 정렬

            answer = 0; // 정답 초기화

            for (int i = 0; i < N; i++) {
                binarySearch(Aarr[i]); // 이진 탐색을 통해 현재 첫 번째 배열의 원소를 두 번째 배열에서 찾음
            }
            System.out.println(answer); // 정답 출력
        }
    }

    public static void binarySearch(int target) {
        int start = 0;
        int end = M - 1;
        int middle = 0;
        while (start <= end) {
            middle = (start + end) / 2; // 중간 인덱스 계산

            if (target > Barr[middle]) {
                start = middle + 1; // 중간 값보다 큰 경우, 범위를 오른쪽으로 축소
            } else {
                end = middle - 1; // 중간 값보다 작거나 같은 경우, 범위를 왼쪽으로 축소
            }
        }
        answer += start; // 찾은 위치(오른쪽 끝)를 누적하여 정답에 더함
    }
}
