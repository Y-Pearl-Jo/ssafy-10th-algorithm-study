import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N,ans;
	static int[][] people,map;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		// 인구수 입력받기
		people = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				people[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 계산
		ans = Integer.MAX_VALUE;
		
		// 경계 길이(d1,d2) 정하기
		for(int d1=1; d1<N-1; d1++) {
			for(int d2=1; d2<N-d1; d2++) {
				start(d1, d2); // 기준점 정하고 나누기
				
			}
		}
		
		System.out.println(ans);
		
	}
	
	// 1. 기준점(r,c) 정하기
	static void start(int d1, int d2){
		for(int r=1; r<N-(d1+d2); r++) {
			for(int c=d1; c<N-d2; c++) {
				fill5(r,c,d1,d2);
				fillNums(r,c,d1,d2);
				ans = Math.min(ans, count()); // 인구수 차이 최솟값 저장
			}
		}
	}
	
	// 2. 경계선 만들기
	static void fill5(int x, int y, int d1, int d2) {
		map = new int[N][N];
		
		for(int i=0; i<=d1; i++) {
			map[x+i][y-i] = 5; // 1
			map[x+d2+i][y+d2-i] = 5; // 4
		}
		
		for(int i=0; i<=d2; i++) {
			map[x+i][y+i] = 5; // 2
			map[x+d1+i][y-d1+i] = 5; // 3
		}
		
		// 5 채우기
		for(int r=x+1; r<x+(d1+d2); r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c]==5) {
					c++;
					while(map[r][c]!=5) {
						map[r][c] = 5;
						c++;
					}
				}
			}
		}
	}

	// 3. 숫자 1234 채우기
	static void fillNums(int x, int y, int d1, int d2) {
		// 1
		for(int r=0; r<x+d1; r++) {
			for(int c=0; c<=y; c++) {
				if(map[r][c]==5) break;
				map[r][c] = 1;
			}
		}
		// 2
		for(int r=0; r<=x+d2; r++) {
			for(int c=N-1; c>y; c--) {
				if(map[r][c]==5) break;
				map[r][c] = 2;
			}
		}
	
		// 3
		for(int r=x+d1; r<N; r++) {
			for(int c=0; c<y-d1+d2; c++) {
				if(map[r][c]==5) break;
				map[r][c] = 3;
			}
		}
		
		// 4
		for(int r=x+d2+1; r<N; r++) {
			for(int c=N-1; c>=y-d1+d2; c--) {
				if(map[r][c]==5) break;
				map[r][c] = 4;
			}
		}
	}
	
	// 인구 차이 계산
	static int count() {
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		for(int num=1; num<=5; num++) {
			int cnt = 0;
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(map[r][c]==num) {
						cnt += people[r][c];
					}
				}
			}
			max = Math.max(max, cnt);
			min = Math.min(min, cnt);
		}

		return max-min;
	}	
}

class Node{
	int r;
	int c;
	public Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
