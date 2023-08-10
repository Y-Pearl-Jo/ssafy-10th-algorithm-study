import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int K;
    static int MIN = Integer.MAX_VALUE;
    static int[][] arrOfK;
    static int level;
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	N = Integer.parseInt(st.nextToken());
	M = Integer.parseInt(st.nextToken());
	K = Integer.parseInt(st.nextToken());
	level = K;
	
	int[][] arr = new int[N][M];
	for (int i = 0; i < N; i++) {
	    st = new StringTokenizer(br.readLine());
	    for (int j = 0; j < M; j++) {
		arr[i][j] = Integer.parseInt(st.nextToken());
	    }
	}
	arrOfK = new int[K][4];
	for (int i = 0; i < K; i++) {
	    st = new StringTokenizer(br.readLine());
	    arrOfK[i][0] = Integer.parseInt(st.nextToken());
	    arrOfK[i][1] = Integer.parseInt(st.nextToken());
	    arrOfK[i][2] = Integer.parseInt(st.nextToken());
	    arrOfK[i][3] = 1; // 연산 사용했는지 여부 확인
	}
	
	
	// 백트래킹
	backTracking(arr);
	System.out.println(MIN);
    }

    public static void backTracking(int[][] arr) {
	// base condition, 연산 횟수를 모두 사용하면 최솟값 탐색
	if (level == 0) {
	    for (int i = 0; i < arr.length; i++) {
		int sum = 0;
		for (int j = 0; j < arr[0].length; j++) {
		    sum += arr[i][j];
		    
		}
		
		MIN = Math.min(MIN, sum);
		
	    }
	    return;
	}

	
	for (int i = 0; i < K; i++) {
//	    System.out.println(i + "?");
//	    System.out.println(arrOfK[1][3] + " !");
	    
	    // i번째가 이미 사용한 회전 연산이 아니면 해당 연산으로 회전 후 재귀 탐색
	    if (arrOfK[i][3] != 0) {
		// 해당 회전연산 사용, K를 줄이고 해당 연산을 사용함을 3번 인덱스로 카운트
		level--;
		arrOfK[i][3] = 0;
		
		// arr과 rcs로 회전하여 newArr 생성
		int[][] newArr = new int[N][M];
		int r = arrOfK[i][0];
		int c = arrOfK[i][1];
		int s = arrOfK[i][2];
		newArr = rotate(arr, r, c, s);

		// 백트래킹 : 인자로 변환한 새로운 배열 넘겨주기
		backTracking(newArr);
		// 회전 연산 복구
		level++;
		arrOfK[i][3] = 1;
	    }

	}

    }

    public static int[][] rotate(int[][] arr, int r, int c, int s) {

	int[][] newArr = new int[N][M];

	// arr로부터 배열 복사
	for (int i = 0; i < arr.length; i++) {
	    newArr[i] = arr[i].clone();
	}
	
	int sRow = r - s - 1;
	int sCol = c - s - 1;
	int move = 2*s;
	Pointer p = new Pointer(sRow, sCol);

	while (move!=0) {
	    // 정사각형의 가장 외부층부터 내부층으로 들어가며 바꾸기

	    // 포인터 오른쪽으로
	    for (int i = 0; i < move; i++) {
		p.c++;
		newArr[p.r][p.c] = arr[p.r][p.c-1];
	    }
	    // 포인터 아래쪽으로
	    for (int i = 0; i < move; i++) {
		p.r++;
		newArr[p.r][p.c] = arr[p.r-1][p.c];
	    }
	    // 포인터 왼쪽으로
	    for (int i = 0; i < move; i++) {
		p.c--;
		newArr[p.r][p.c] = arr[p.r][p.c+1];
	    }
	    // 포인터 위쪽으로
	    for (int i = 0; i < move; i++) {
		p.r--;
		newArr[p.r][p.c] = arr[p.r+1][p.c];
	    }
	    
	    //내부 사각형으로
	    p.r++;
	    p.c++;
	    move-=2;

	}
	for(int i=0; i<newArr.length; i++) {
	    for(int j=0; j<newArr[0].length; j++) {
	    }
	}
	return newArr;

    }
}

class Pointer {
    int r;
    int c;

    Pointer() {
    };

    Pointer(int r, int c) {
	this.r = r;
	this.c = c;
    }
}
