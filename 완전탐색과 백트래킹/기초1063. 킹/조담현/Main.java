import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String king = st.nextToken();
		String stone = st.nextToken();
		int move = Integer.parseInt(st.nextToken());
		
		
		
		int kingRow = (int)(king.charAt(1)-'1');
		int kingCol = (int)(king.charAt(0)-'A');
		int stoneRow = (int)(stone.charAt(1)-'1');
		int stoneCol = (int)(stone.charAt(0)-'A');
				
		for(int i=0; i<move; i++) {
			String dir = br.readLine();
			
			switch(dir) {
			case "R":
				// 이동 가능 검사
				if(kingCol+1<8) {
					// king 이동
					kingCol++;
					// 위치가 같다면
					if(kingRow==stoneRow && kingCol==stoneCol) {
						// 이동 가능 검사
						if(stoneCol+1<8) {
							// stone 이동
							stoneCol++;
						} else {
							// king을 움직였을때 stone 이동이 불가능하면 복귀
							kingCol--;
						}
					}
				}
				break;
			case "L":
				if(kingCol-1>=0) {
					// king 이동
					kingCol--;
					// 위치가 같다면
					if(kingRow==stoneRow && kingCol==stoneCol) {
						// 이동 가능 검사
						if(stoneCol-1>=0) {
							// stone 이동
							stoneCol--;
						} else {
							kingCol++;
						}
					}
				}
				break;
			case "B":
				if(kingRow-1>=0) {
					// king 이동
					kingRow--;
					// 위치가 같다면
					if(kingRow==stoneRow && kingCol==stoneCol) {
						// 이동 가능 검사
						if(stoneRow-1>=0) {
							// stone 이동
							stoneRow--;
						} else {
							kingRow++;
						}
					}
				}
				break;
			case "T":
				if(kingRow+1<8) {
					// king 이동
					kingRow++;
					// 위치가 같다면
					if(kingRow==stoneRow && kingCol==stoneCol) {
						// 이동 가능 검사
						if(stoneRow+1<8) {
							// stone 이동
							stoneRow++;
						} else {
							kingRow--;
						}
					}
				}
				break;
			case "RT":
				if(kingRow+1<8 && kingCol+1<8) {
					// king 이동
					kingRow++;
					kingCol++;
					// 위치가 같다면
					if(kingRow==stoneRow && kingCol==stoneCol) {
						// 이동 가능 검사
						if(stoneRow+1<8 && stoneCol+1<8) {
							// stone 이동
							stoneRow++;
							stoneCol++;
						} else {
							kingRow--;
							kingCol--;
						}
					}
				}
				break;
			case "LT":
				if(kingRow+1<8 && kingCol-1>=0) {
					// king 이동
					kingRow++;
					kingCol--;
					// 위치가 같다면
					if(kingRow==stoneRow && kingCol==stoneCol) {
						// 이동 가능 검사
						if(stoneRow+1<8 && stoneCol-1>=0) {
							// stone 이동
							stoneRow++;
							stoneCol--;
						} else {
							kingRow--;
							kingCol++;
						}
					}
				}
				break;
			case "RB":
				if(kingRow-1>=0 && kingCol+1<8) {
					// king 이동
					kingRow--;
					kingCol++;
					// 위치가 같다면
					if(kingRow==stoneRow && kingCol==stoneCol) {
						// 이동 가능 검사
						if(stoneRow-1>=0 && stoneCol+1<8) {
							// stone 이동
							stoneRow--;
							stoneCol++;
						} else {
							kingRow++;
							kingCol--;
						}
					}
				}
				break;
			case "LB":
				if(kingRow-1>=0 && kingCol-1>=0) {
					// king 이동
					kingRow--;
					kingCol--;
					// 위치가 같다면
					if(kingRow==stoneRow && kingCol==stoneCol) {
						// 이동 가능 검사
						if(stoneRow-1>=0 && stoneCol-1>=0) {
							// stone 이동
							stoneRow--;
							stoneCol--;
						} else {
							kingRow++;
							kingCol++;
						}
					}
				}
				break;
			}
		}
		king = "" + (char)(kingCol+'A') + (kingRow+1);
		stone = "" + (char)(stoneCol+'A') + (stoneRow+1);
		System.out.println(king);
		System.out.println(stone);
	}

}
