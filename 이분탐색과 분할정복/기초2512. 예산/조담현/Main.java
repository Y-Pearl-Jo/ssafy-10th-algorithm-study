import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 0. 예산상한액의 범위는  예산의 범위와 동일. 1 ~ N개 예산 중 최댓값이다 예산 개수 N은 최대 1만개, 예산 당 최댓값 10만.
 * 1. 예산 상한액을 하나씩 경우의 수당 예산 N개의 합을 선택하면 최대 10만 * 1만 = 10억번의 연산
 * 2. 이분탐색을 통해 예산 상한액의 경우의 수를 좁혀가면 최대 log10만 * 1만 = 160만번의 연산
 */



public class Main {
	
	static int N;
	static int[] arr;
	static int M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 예산 요청 개수
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = Integer.MIN_VALUE; 
		// 이진 탐색 범위를 찾기 위해 입력받으며 예산 중 최댓값을 찾는다.
		for(int i=0; i<N; i++) { 
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		M = Integer.parseInt(br.readLine()); // 예산 최댓 값
		
		System.out.println(binarySearch(1,max));
	}
	// 이분탐색. 상한액 cut이 높아질수록 sum이 높아진다.
	public static int binarySearch(int start, int end) {
		int maxCut = 0;
		while(start<=end) {
			int mid = (start+end)/2;
			int sum = 0;
			// 상한액 mid보다 낮은 값은 그냥 더해주고, 높은 값은 상한액만 더해준다.
			for(int i=0; i<N; i++) {
				if(arr[i]<=mid) sum+=arr[i];
				else sum+=mid;
			}
			// 더한 예산의 합이 M과 같다면 최적
			if(sum==M) {
				maxCut = mid;
				break;
			}
			// M보다 많다면 더 낮은 상한액(좌측)으로 탐색한다.
			if(sum>M) {
				end = mid - 1;
				continue;
			}
			// M보다 적다면 최대 상한액을 업데이트하고 더 높은 상한액(우측)으로 탐색한다.
			if(sum<M) {
				start = mid + 1;
				maxCut = mid;
				continue;
			}
			
		}
		return maxCut;
	}
}
