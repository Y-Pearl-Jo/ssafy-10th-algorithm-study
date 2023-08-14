import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int min = Integer.MAX_VALUE;
    static boolean[] check;

    public static void recur(int depth, int cnt, int[][] foot, int[] teamA, int[] teamB) {
	if (cnt > N / 2) {
	    return;
	}

	if (depth == N) {
	    if (cnt == N / 2) {
		int idxA = 0;
		int idxB = 0;

		for (int i = 0; i < N; i++) {
		    if (check[i]) {
			teamA[idxA++] = i + 1;
		    } else {
			teamB[idxB++] = i + 1;
		    }
		}

		int sumA = 0;
		int sumB = 0;
		
		for (int i = 0; i < N / 2; i++) {
		    for(int j = 0; j < N/2; j++) {
			sumA += foot[teamA[i]-1][teamA[j]-1]; 
		    }
		}
		
		for (int i = 0; i < N / 2; i++) {
		    for(int j = 0; j < N/2; j++) {
			sumB += foot[teamB[i]-1][teamB[j]-1]; 
		    }
		}
		
		int sumDiff = Math.abs(sumA - sumB);

		min = Math.min(min, sumDiff);
    }
	    return;
	}

	check[depth] = true;
	recur(depth + 1, cnt + 1, foot, teamA, teamB);
	check[depth] = false;
	recur(depth + 1, cnt, foot, teamA, teamB);

    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;

	N = init(br.readLine());
	check = new boolean[N];
	int[][] foot = new int[N][N];
	int[] teamA = new int[N / 2];
	int[] teamB = new int[N / 2];

	for (int i = 0; i < N; i++) {
	    st = new StringTokenizer(br.readLine());
	    for (int j = 0; j < N; j++) {
		foot[i][j] = init(st.nextToken());
	    }
	}

	recur(0, 0, foot, teamA, teamB);
	
	System.out.println(min);
    }

    public static int init(String str) {
	return Integer.parseInt(str);
    }
}
