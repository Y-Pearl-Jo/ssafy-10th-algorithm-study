import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Fireball {
	// 행, 열, 질량, 속도, 방향
	int r;
	int c;
	int m;
	int s;
	int d;
	
	Fireball() {
		this(0, 0, 0, 0, 0);
	}
	Fireball(int r, int c, int m, int s, int d) {
		this.r = r;
		this.c = c;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}

public class Main {
	static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1 };
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1 };
	static ArrayList<ArrayList<ArrayList<Fireball>>> arr;
	static int N;
	static int M;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			arr.add(new ArrayList<ArrayList<Fireball>>());
			for (int j = 0; j < N; j++) {
				arr.get(i).add(new ArrayList<Fireball>());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			arr.get(r - 1).get(c - 1).add(new Fireball(r-1, c-1, m, s, d));
		}

		for (int i = 0; i < K; i++) {
			arr = order(arr);
		}
		
		int total = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ArrayList<Fireball> fbList = arr.get(i).get(j);
				for(int k=0; k<fbList.size(); k++) {
					total+=fbList.get(k).m;
				}
			}
		}
		System.out.println(total);
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				ArrayList<Fireball> fbList = arr.get(i).get(j);
//				for(int k=0; k<fbList.size(); k++) {
//					
//					System.out.println(fbList.get(k).r + " " + fbList.get(k).c);
//				}
//			}
//		}

	}

	/*
	 * 마법사 상어가 모든 파이어볼에게 이동을 명령하면 다음이 일들이 일어난다.
	 * 
	 * 1. 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동한다. 
	 * - 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
	 * 2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다. 
	 * 2-1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
	 * 2-2. 파이어볼은 4개의 파이어볼로 나누어진다. 
	 * 3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다. 
	 * 3-1. 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5 이다.⌋.
	 * 3-2  속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
	 * 3-3. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다. 
	 * 4. 질량이 0인 파이어볼은 소멸되어 없어진다.
	 * 
	 * 마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 구해보자.
	 */

	public static ArrayList<ArrayList<ArrayList<Fireball>>> order(ArrayList<ArrayList<ArrayList<Fireball>>> arr) {
		ArrayList<ArrayList<ArrayList<Fireball>>> newArr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			newArr.add(new ArrayList<ArrayList<Fireball>>());
			for (int j = 0; j < N; j++) {
				newArr.get(i).add(new ArrayList<Fireball>());
			}
		}
		// 1. 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동한다.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 2차원에 있는 파이어볼 리스트에서 파이어볼 꺼내오기
				ArrayList<Fireball> fbList = arr.get(r).get(c);
				for (int i = fbList.size() - 1; i >= 0; i--) {
					Fireball fb = fbList.get(i);
					// 파이어볼의 방향 d로 s칸만큼 이동한다.
					int d = fb.d;
					int nr = fb.r + (dr[d] * fb.s);
					int nc = fb.c + (dc[d] * fb.s);
					// 넘어간 경우와 넘어가지 않은 경우 모두 (N+(idx%N))%N로 정리 가능
					nr = (N + (nr % N)) % N;
					nc = (N + (nc % N)) % N;

					fb.r = nr;
					fb.c = nc;
//					System.out.println("moved from : " + r + "," + c + " to : " + nr + ", " + nc);
					newArr.get(nr).get(nc).add(fb);
					fbList.remove(i);
				}
			}
		}

//		2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				ArrayList<Fireball> fbList = newArr.get(r).get(c);
				if(fbList.size()>=2) {
//		2-1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
					Fireball newFb = new Fireball();
					newFb.r = r;
					newFb.c = c;
					boolean [] isOddEven = new boolean[fbList.size()]; // 홀수면 true, 짝수면 false로 설정하여 배열 내의 boolean값이 모두 같은지 확인
					for (int i = fbList.size() - 1; i >= 0; i--) {
							newFb.m += fbList.get(i).m;
							newFb.s += fbList.get(i).s;
							if(fbList.get(i).d%2==1) {
								isOddEven[i] = true;
							} else {
								isOddEven[i] = false;
							}
					}
					int count = fbList.size(); // 합쳐진 파이어볼의 개수 (기존에 있던 파이어볼의 개수)
					fbList.clear();
					
//		2-2. 파이어볼은 4개의 파이어볼로 나누어진다.
//		3-3. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
					int[] dArr = {0, 2, 4, 6};
					for(int i=1; i<isOddEven.length; i++) {
						if(isOddEven[i]!=isOddEven[i-1]) {
							dArr = new int[] {1,3,5,7};
							break;
						}
					}
//		3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
					for(int i=0; i<4; i++) {
						Fireball fb = new Fireball();
						fb.r = r;
						fb.c = c;
//		3-1. 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5 이다.⌋
						fb.m = newFb.m/5;
//		3-2 .속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
						fb.s = newFb.s/count;
						fb.d = dArr[i];
						fbList.add(fb);
					}
//		4. 질량이 0인 파이어볼은 소멸되어 없어진다.
					for(int i=fbList.size()-1; i>=0; i--) {
						if(fbList.get(i).m==0) fbList.remove(i);
					}
				}
			}
		}

		return newArr;
	}

}
