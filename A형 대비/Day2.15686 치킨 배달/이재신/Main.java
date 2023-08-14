import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[] check;
    static int minSum = Integer.MAX_VALUE;

    public static int checkDis(int row, int col, int nChick, int[][] chicken, int[][] city) {
	int dis = Integer.MAX_VALUE;
	for (int i = 0; i < nChick; i++) {
	    if (check[i]) {
		dis = Math.min(dis, Math.abs(chicken[i][0] - row) + Math.abs(chicken[i][1] - col));
	    }
	}
	return dis;
    }

    public static void distance(int depth, int cnt, int nChick, int[][] chicken, int[][] city) {
	if (depth == nChick) {
	    if (cnt == M) {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < N; j++) {
			temp[i][j] = city[i][j];
		    }
		}
		for (int i = 0; i < nChick; i++) {
		    if (!check[i]) {
			temp[chicken[i][0]][chicken[i][1]] = 0;
		    }
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < N; j++) {
			if (temp[i][j] == 1) {
			    sum += checkDis(i, j, nChick, chicken, temp);
			}
		    }
		}
		minSum = Math.min(minSum, sum);
	    }
	    return;
	}

	check[depth] = true;
	distance(depth + 1, cnt + 1, nChick, chicken, city);
	check[depth] = false;
	distance(depth + 1, cnt, nChick, chicken, city);
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;

	st = new StringTokenizer(br.readLine());

	N = init(st.nextToken());
	M = init(st.nextToken());

	int[][] chicken = new int[13][2];
	int[][] city = new int[N][N];
	check = new boolean[13];

	int idx = 0;
	for (int i = 0; i < N; i++) {
	    st = new StringTokenizer(br.readLine());
	    for (int j = 0; j < N; j++) {
		int temp = init(st.nextToken());
		city[i][j] = temp;
		if (temp == 2) {
		    chicken[idx][0] = i;
		    chicken[idx++][1] = j;
		}
	    }
	}

	distance(0, 0, idx, chicken, city);

	System.out.println(minSum);

    }

    public static int init(String str) {
	return Integer.parseInt(str);
    }
}
