import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배열 크기 n과 목표 합 m을 입력받음
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];

        // 배열 arr에 n개의 정수를 입력
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0; // 현재 부분 배열의 합을 저장
        int start = 0; // 부분 배열의 시작 인덱스
        int end = 0; // 부분 배열의 끝 인덱스
        int count = 0; // 합이 m인 부분 배열의 개수

        while (true) {
            if (sum >= M) {
                sum -= arr[start++]; // sum이 m 이상이면 시작 인덱스를 오른쪽으로 이동하고 부분 합에서 빼줌
            } else if (end == N) {
                break; // end가 배열의 끝에 도달하면 반복 종료
            } else {
                sum += arr[end++]; // sum이 m 미만이면 end를 오른쪽으로 이동하고 부분 합에 값을 더함
            }

            if (sum == M) {
                count++; // 현재 부분 배열의 합이 m과 같으면 count 증가
            }
        }

        System.out.println(count); // 결과 출력
    }
}
