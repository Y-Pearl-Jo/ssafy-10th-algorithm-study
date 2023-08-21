import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<fireball>[][] fire;
	static ArrayList<fireball>[][] move;

	//파이어볼의 정보
	static class fireball {
		int mass;
		int scalar;
		int direction;

		public fireball(int mass, int scalar, int direction) {
			this.mass = mass;
			this.scalar = scalar;
			this.direction = direction;
		}
	}
	//문제에 주어진 번호에 맞는 방향 벡터
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	// i번 파이버 볼의 위치는 (r,c) 질량은 m 방향은 d, 속력은 s
	// 자신의 방향으로 속력 s 만큼 이동
	static void moveFire() {
		//움직인 파이어볼이 또 움직이지 않도록 새로운 2차원 배열 생성
		ArrayList<fireball>[][] move = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				move[i][j] = new ArrayList<fireball>();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//파이어볼이 존재하는 칸이면
				if (fire[i][j].size() >= 1) {
					//각각의 파이어볼을 정보에 맞게 움직인 후 move에 추가
					for (fireball fb : fire[i][j]) {
						int sca = fb.scalar % N;
						int r = (i + sca * dr[fb.direction]);
						if (r < 0)
							r += N;
						else if (r >= N)
							r -= N;
						int c = (j + sca * dc[fb.direction]);
						if (c < 0)
							c += N;
						else if (c >= N)
							c -= N;
						move[r][c].add(new fireball(fb.mass,fb.scalar, fb.direction));
					}
				}
			}
		}
		fire = move;
		checkFire();
	}

	// 이동이 끝난 뒤 2개 이상의 파이어볼이 있는 경우
	// 1. 파이어볼을 모두 하나로 합친다.
	// 2. 파이어볼을 4개로 나눈다.
	// 3. 나누어진 파이어볼의 질량, 속력
	// 질량 = 질량의 총합 / 5
	// 속력 = 속력의 총합 / 합쳐진 파이어볼의 개수
	// 방향 = 합쳐지는 파이어볼의 방향이 모두 홀수 || 모두 짝수 ? 0,2,4,6 : 1, 3, 5, 7
	// 4. 질량이 0인 파이어볼은 사라진다.
	static void checkFire() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 파이어볼이 여러개 있는 경우
				if (fire[i][j].size() > 1) {
					int sumMass = 0;
					int sumScalar = 0;
					boolean[] sumDirec = new boolean[fire[i][j].size()];
					boolean flag = true;
					
					//중첩된 개수만큼 반복
					for (int idx = 0; idx < fire[i][j].size(); idx++) {
						sumMass += fire[i][j].get(idx).mass;
						sumScalar += fire[i][j].get(idx).scalar;
						
						//방향이 모두 짝수인지 혹은 모두 홀수 인지 확인
						if (fire[i][j].get(idx).direction % 2 == 0)
							sumDirec[idx] = true;
						else
							sumDirec[idx] = false;
						if (idx > 0 && sumDirec[idx] != sumDirec[idx - 1])
							flag = false;
					}
					
					int newMass = sumMass / 5;
					int newScalar = sumScalar / fire[i][j].size();
					int[] newDirec = flag ? new int[] { 0, 2, 4, 6 } : new int[] { 1, 3, 5, 7 };
					
					//새로 분리한 파이어볼 넣기 전 초기화
					fire[i][j].clear();

					//새 파이어볼의 질량이 0이라면 대입하지 않고 다음 반복
					if (newMass == 0) {
						continue;
					}
					
					for (int idx = 0; idx < 4; idx++) {
						fire[i][j].add(new fireball(newMass, newScalar, newDirec[idx]));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		// 격자 크기
		N = init(st.nextToken());
		fire = new ArrayList[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				fire[i][j] = new ArrayList<fireball>();
			}
		}
		// 파이어 볼의 개수
		int M = init(st.nextToken());
		// 이동 명령 횟수
		int K = init(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			fire[init(st.nextToken()) - 1][init(st.nextToken()) - 1]
					.add(new fireball(init(st.nextToken()), init(st.nextToken()), init(st.nextToken())));
		}

		for (int i = 0; i < K; i++) {
			moveFire();
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(fire[i][j].size() > 0) {
					for(fireball fb : fire[i][j]) {
						sum = sum + fb.mass;
					}
				}
			}
		}
		System.out.println(sum);
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}

}
