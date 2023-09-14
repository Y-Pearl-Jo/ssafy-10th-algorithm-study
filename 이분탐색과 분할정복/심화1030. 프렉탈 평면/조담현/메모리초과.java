import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 1. 분할정복으로 풀이
 * 1-1. 재귀를 들어갈 때마다 기존 이차원 배열 arr을 인자로 하며 새로운 이차원 temp 배열을 생성
 * 1-2. temp배열은 arr.length * N이 한 변인 사각형 (기존 사각형의 한변 * N)
 * 1-3. 기존 arr 사각형을 temp에 N*N번 복사
 * 1-4. temp배열의 가운데((temn의 한 변의 길이 - arr.length*K가 한 변인 작은 사각형 길이)/2 부터 시작)부터
 * 1-5. 작은 사각형을 검정으로 칠하기
 * 1-6. depth == s일 때 배열에서 출력 후 종료.
 * 1-7. 메모리 초과. N^s 크기의 배열을 만들어야 하는데, 최대 2^30 * 2^30 크기의 배열을 만들게 됨
 */

public class Main {
	static int s;
	static int N;
	static int K;
	static int R1, R2;
	static int C1, C2;
	
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R1 = Integer.parseInt(st.nextToken());
		R2 = Integer.parseInt(st.nextToken());
		C1 = Integer.parseInt(st.nextToken());
		C2 = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[1][1];
		fractal(arr, 0);
	}
	
	public static void fractal(int[][] arr, int depth) {
		if(depth==s) {
			for(int i=0; i<R2; i++) {
				for(int j=0; j<C2; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}
			return;
		}
		
		int[][] temp = new int[arr.length*N][arr.length*N];
		int tempR = 0;
		for(int i=0; i<N; i++) {
			int tempC = 0;
			for(int j=0; j<N; j++) {
				for(int row = 0; row<arr.length; row++) {
					for(int col = 0; col<arr.length; col++) {
						temp[tempR+row][tempC+col] = arr[row][col];
					}
				}
				tempC += arr.length;
			}
			tempR += arr.length;
		}
		
		int centerR = (temp.length - arr.length*K)/2;
		int centerC = (temp.length - arr.length*K)/2;
		for (int i=centerR; i<centerR + arr.length*K; i++) {
			for (int j=centerC; j<centerC + arr.length*K; j++) {
				temp[i][j] = 1;
			}
		}
		
		fractal(temp, depth+1);
	}
	
}
