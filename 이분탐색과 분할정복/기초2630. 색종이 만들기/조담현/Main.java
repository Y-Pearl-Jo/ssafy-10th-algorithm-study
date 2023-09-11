import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 1. 재귀 함수의 시작마다 반복문으로 사각형을 확인한다.
 * 2. 배열 내부에서 모두 1이거나 모두 0인경우 blue 혹은 white를 증가시키고 return한다.
 * 3. 아니라면 지금 사각형을 4등분하여 4개의 재귀를 시작한다.
 * 4. 함수의 인자로는 행의 시작, 열의 시작, 한 변의 길이를 지정한다.
 */



public class Main {
	
	
	static int N;
	static int[][] arr;
	static int white;
	static int blue;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 한 변의 길이
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		confetti(0,0,N);
		System.out.println(white);
		System.out.println(blue);
	}
	
	// 지금 확인하는 사각형의 시작행, 시작열, 사각형 길이를 인자로 하는 함수
	public static void confetti(int row, int col, int length) {
		boolean isWhite = true;
		boolean isBlue = true;
		// 지금 확인하는 사각형이 모두 1인 파란색 종이인지, 모두 0 인 하얀색 종이인지 반복문으로 체크
		for(int i=row; i<row+length; i++) {
			for(int j=col; j<col+length; j++) {
				if(arr[i][j]==1) isWhite = false;
				else isBlue = false;
			}
		}
		
		// 파란색종이나 하얀색 종이라면 해당 종이 count
		if(isWhite||isBlue) {
			if(isWhite) white++;
			else blue++;
			return;
		} 
		// 둘다 아니라면 그 사각형의 내부에서 다시 4개의 사각형으로 잘라 확인
		else {
			confetti(row,col,length/2);
			confetti(row,col+length/2,length/2);
			confetti(row+length/2,col,length/2);
			confetti(row+length/2,col+length/2,length/2);
		}
		
	}
	
}
