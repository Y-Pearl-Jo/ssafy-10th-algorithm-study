import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int MIN = Integer.MAX_VALUE;
	static int[][] city;
	static int[][] dist;
	static List<Integer[]> stores = new ArrayList<>();
	static int N;
	static int M;
	static int storeCnt = 0;
	static int count = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N은 도시의 크기, M은 남길 치킨집의 최대 개수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		city = new int[N][N];
		
		
		
		
		// 도시 정보 저장
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				// 0번 인덱스는 방문 여부, 1번 인덱스는 행, 2번 인덱스는 열
				if(city[i][j]==2) {
					storeCnt++;
					stores.add(new Integer [] {0,i,j});
				}
			}
		}
		
		removeChickenStore(0);
		System.out.println(MIN);
		
		
	}
	
	// 치킨집을 M개만 남기고 제거하는 모든 경우의 수를 탐색하는 함수
	public static void removeChickenStore(int k) {
		
		// M개를 선택했으면 최소거리 계산
		if(count == M) {
			MIN = Math.min(calcTotalDistance(), MIN);
			return;
		}
		
		// 가게중에 M개만 선택한다.
		for(int i=k; i<stores.size(); i++) {
			// 가게의 확인 여부를 체크
			if(stores.get(i)[0]==0) {
				count++;
				stores.get(i)[0] = 1;
				
				removeChickenStore(i);
				
				// 확인 한 후 복구
				count--;
				stores.get(i)[0] = 0;
			}
		}
	}
	
	// 도시의 집별 거리를 저장하고, 그 총합을 계산하여 반환하는 함수
	public static int calcTotalDistance() {
		int total = 0;
		dist = new int[N][N];
		for(int m=0; m<stores.size();m++) {
			 
			if(stores.get(m)[0]==1) {
				// 방문 한 M개의 치킨집과 집별 최소 거리를 저장 
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(city[i][j]==1) {
							// 치킨집과 집별 최소거리 저장
							int newdis = Math.abs(stores.get(m)[1]-i) + Math.abs(stores.get(m)[2]-j);
							if(dist[i][j]==0) {
								dist[i][j] = newdis;
							} else {
								dist[i][j] = Math.min(dist[i][j], newdis);
							}
						}
					}
				}
			}
		}
		
		
		// 총 합 구하기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				total+=dist[i][j];
			}
		}
		
		
		
		return total;
	}

}
