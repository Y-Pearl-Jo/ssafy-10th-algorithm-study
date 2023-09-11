import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 1. M개의 숫자에 대해서, 상근이가 가진 숫자카드인지 N개의 카드를 순회하며 확인하려면 O(M*N)의 시간복잡도가 든다.
 * 1-1. M과 N은 최대범위가 500,000이므로 최대 2500억번의 연산을 해야하므로 시간초과
 * 
 * 2. HashMap의 Key에 상근이가 가진 N개의 숫자들을 넣고, (value는 아무거나 상관 없다. 있는지만 확인)
 * 2-1. M개의 숫자에 대해서 HashMap.contains(key) 메소드로 상근이가 가진 숫자인지 확인하면 시간복잡도는 O(M)이다.
 * 2-2. HashMap.contains() 메소드가 시간복잡도 O(1)이기 때문이다.
 * 
 * 
 * 3. M개의 숫자에 대해서, 상근이가 가진 숫자카드인지 N개의 카드를 이분탐색을 통해 확인한다.
 * 3-1. N개의 숫자카드를 정렬하고, M개의 숫자에 대해서 존재하는지 찾는 시간복잡도가 O(logN)인 이분탐색을 하면
 * 3-2. O(MlogN)의 시간복잡도를 가진다.
 * 
 * 이분탐색을 공부하는 주이므로 이분탐색을 이용하여 풀이한다.
 */



public class Main {
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] cards = new int[N];
		
		// 카드 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			cards[i] =  Integer.parseInt(st.nextToken());
		}
		
		// 이분탐색 하기 위해 카드 정렬하기
		Arrays.sort(cards);
		
		// M개의 카드를 하나씩 N개의 카드셋에서 이분탐색하기
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			sb.append(binarySearch(cards,0,N-1,num));
			sb.append(" ");
		}
		System.out.println(sb);
		
	}
	
	public static int binarySearch(int[] cards, int start, int end, int num) {
		while(start<=end) {
			int mid = (start+end)/2;
			if(cards[mid] == num) return 1;
			if(cards[mid]>num) end = mid-1;
			if(cards[mid]<num) start = mid+1;
		}
		return 0;
	}
}
