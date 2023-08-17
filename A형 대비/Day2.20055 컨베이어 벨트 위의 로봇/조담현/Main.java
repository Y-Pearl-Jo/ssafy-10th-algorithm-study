import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 2N개
		int K = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸이 K개 이상이면 종료
		int up = 0;
		int down = N - 1;
		int[] belt = new int[2 * N];
		int[] robot = new int[2 * N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		// 반복문이 도는 단계를 세는 변수
		int phase = 0;
		

		// 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 즉시 1만큼 감소
		while (true) {
			phase++;
			
			// 언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다.
			robot[down] = 0; 
			
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			// ( 앞에서 부터 i+1번째에 i번째를 복사하면 i번째가 무한히 복사되므로 뒤에서부터 복사한다. )
			int tempB = belt[2 * N - 1];
			for (int i = 2*N-1; i >=1; i--) {
				belt[i] = belt[i - 1];
				robot[i] = robot[i - 1];
				
			}
			
			// 언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다.
			robot[down] = 0;
			
			// ( N번부터 2*N-1번 까지는 로봇이 없지만, 회전하면 0번째에 로봇이 있었더라도 없던게 되어야하므로 0을 삽입)
			belt[0] = tempB;
			robot[0] = 0;


			// 2. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
			// 2-1 . 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 내구도가 1 이상 남아있어야 한다.
			// ( 앞에서 부터 i+1번째에 i번째를 복사하면 i번째가 무한히 복사되므로 뒤에서부터 복사한다. )
			for (int i = down-1; i >=0; i--) {
				if(belt[i+1]>=1 && robot[i+1] == 0 && robot[i]==1) {
					robot[i+1] = 1;
					robot[i] = 0;
					belt[i+1]--;
				}
			}


			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면, (그리고 올리는 위치에 로봇이 없다면!) 올리는 위치에 로봇을 올린다.
			if (belt[up] != 0 && robot[up] == 0) {
				robot[up] = 1;
				belt[up]--;
			}
			
			// 4. 내구도가 0인 칸의 개수가 K개 이상이면 종료
			// 내구도가 0인 칸의 개수
			int count = 0;
			for (int i = 0; i < 2 * N; i++) {
				if (belt[i] == 0)
					count++;
			}
			if (count >= K)
				break;
			
			// 출력 확인용
//			System.out.println("belt : " + Arrays.toString(belt));
//			System.out.println("robot : " + Arrays.toString(robot));
			
		}

		System.out.println(phase);

	}

}
