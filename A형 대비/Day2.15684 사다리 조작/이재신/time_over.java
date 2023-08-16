import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int cnt = 0;
    static int minCnt = Integer.MAX_VALUE;
    static int cycle = 0;
    static boolean add = false;

    // 가로선 입력 할 위치 찾아 입력
    static void inputLadder(int depth, int col, int row, boolean[][] ladder, int M) {
	if (checkLadder(col, row, ladder)) {
	    minCnt = Math.min(minCnt, cnt);
	    return;
	}
	if (cnt >= 3) {
	    return;
	}
	if (depth == col - 1) {
	    depth = 0;
	    cycle++;
	    if (cycle > row / 2) {
		return;
	    }
	}
	
	for (int j = 0; j < row; j++) {
	    if ((depth < col - 1 && depth > 0 && !ladder[j][depth - 1] && !ladder[j][depth] && !ladder[j][depth + 1])
		    || (depth == 0 && !ladder[j][depth] && !ladder[j][depth + 1])
		    || (depth == col - 1 && !ladder[j][depth - 1])) {
		ladder[j][depth] = true;
		cnt++;
		add = true;
		inputLadder(depth + 1, col, row, ladder, M);
		ladder[j][depth] = false;
		cnt--;
		add = false;
		inputLadder(depth + 1, col, row, ladder, M);
	    }
	}

    }

    static boolean checkLadder(int col, int row, boolean[][] ladder) {
	for (int i = 0; i < col; i++) {
	    int tempR = i;
	    for (int j = 0; j < row; j++) {
		// i-1이 true 왼쪽으로 이동
		if (tempR > 0 && ladder[j][tempR - 1]) {
		    tempR--;
		}
		// i true 오른쪽으로 이동
		else if (tempR < col && ladder[j][tempR]) {
		    tempR++;
		}
	    }
	    if (i != tempR) {
		return false;
	    }
	}
	return true;
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;

	st = new StringTokenizer(br.readLine());
	// 세로선 크기
	int col = init(st.nextToken());
	// 가로선 개수
	int M = init(st.nextToken());
	// 가로선 크기
	int row = init(st.nextToken());

	boolean[][] ladder = new boolean[row][col];

	for (int i = 0; i < M; i++) {
	    st = new StringTokenizer(br.readLine());
	    int a = init(st.nextToken()) - 1;
	    int b = init(st.nextToken()) - 1;
	    ladder[a][b] = true;
	}

	inputLadder(0, col, row, ladder, M);

	System.out.println(minCnt > 3 ? "-1" : minCnt);

    }

    static int init(String str) {
	return Integer.parseInt(str);
    }
}
