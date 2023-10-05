import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
*/

// 학생 번호, 인접한 좋아하는 학생 수, 빈자리 수를 포함한 자리 클래스
class Seat {
    int number = 0;
    int like = 0;
    int empty = 0;

}

public class Main {
    public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N = Integer.parseInt(br.readLine());
	StringTokenizer st;
	int[][] students = new int[N * N][5];
	Seat[][] room = new Seat[N][N];

	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
		room[i][j] = new Seat();
	    }
	}
	// 학생 저장
	// 0번 인덱스는 학생 번호, 1~4는 좋아하는 학생
	for (int i = 0; i < N * N; i++) {
	    st = new StringTokenizer(br.readLine());
	    students[i][0] = Integer.parseInt(st.nextToken());
	    students[i][1] = Integer.parseInt(st.nextToken());
	    students[i][2] = Integer.parseInt(st.nextToken());
	    students[i][3] = Integer.parseInt(st.nextToken());
	    students[i][4] = Integer.parseInt(st.nextToken());
	}

	// 상하좌우 탐색할 델타
	int[] dr = { -1, 1, 0, 0 };
	int[] dc = { 0, 0, -1, 1 };

	// 자리배정하는 for문
	// 학생 수 만큼
	for (int i = 0; i < N * N; i++) {
	    int r = 0;
	    int c = 0;
	    int nr = 0;
	    int nc = 0;
	    // 배열에서 탐색
	    for (int j = 0; j < N; j++) {
		for (int k = 0; k < N; k++) {
		    room[j][k].like = 0;
		    room[j][k].empty = 0;
		    // 현재 탐색하는 자리의 인덱스
		    r = j;
		    c = k;
		    // 탐색하려는 자리가 앉을 수 있는 빈자리라면 인접한 자리 탐색
		    if (room[r][c].number == 0) {
			// 델타 탐색
			for (int d = 0; d < 4; d++) {
			    nr = r + dr[d];
			    nc = c + dc[d];
			    // 탐색 가능한 자리라면
			    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
//				 인접한 자리에 있는게 좋아하는 학생인지 확인
				if (room[nr][nc].number == students[i][1] || room[nr][nc].number == students[i][2]
					|| room[nr][nc].number == students[i][3]
					|| room[nr][nc].number == students[i][4]) {
				    room[r][c].like++;
				}
				// 인접한 자리가 빈자리인지 확인
				else if (room[nr][nc].number == 0) {
				    room[r][c].empty++;
				}
			    }
			}
		    }
		}
	    }
	    int a = 0;
	    int b = 0; // 앉을 자리의 인덱스를 저장할 임시 변수
	    int maxLike = Integer.MIN_VALUE; // 남은 자리중 좋아하는 학생수가 최대인 곳을 찾기 위한 변수
	    for (int j = 0; j < N; j++) {
		for (int k = 0; k < N; k++) {

		    // 이미 차지한 자리면 업데이트 X
		    if (room[j][k].number != 0)
			continue;
		    // 좋아하는 학생수가 더 많다면 앉을 자리 업데이트
		    if (room[j][k].like > maxLike) {
			a = j;
			b = k;
			maxLike = room[j][k].like;
		    }

		    // 좋아하는 학생 수가 같은데 빈자리가 더 많다면 앉을 자리 업데이트
		    if (room[j][k].like == maxLike) {
			if (room[j][k].empty > room[a][b].empty) {
			    a = j;
			    b = k;
			}
		    }
		}
	    }
	    // 빈칸이라면 자리를 찾았으므로 삽입
	    if (room[a][b].number == 0) {
		room[a][b].number = students[i][0];
	    } 
	    

	    // 배열 확인
//	    for (int m = 0; m < N; m++) {
//		for (int n = 0; n < N; n++) {
//		    System.out.print(room[m][n].number + " ");
//		}
//		System.out.println();
//	    }
//	    System.out.println();
	} // 가장 바깥 for문 끝.

	Arrays.sort(students, (o1, o2) -> {
	    return o1[0] - o2[0];
	});

	int total = 0; // 총 만족도
	for (int i = 0; i < N; i++) {
	    int r = 0;
	    int c = 0;
	    int nr = 0;
	    int nc = 0;
	    for (int j = 0; j < N; j++) {
		// 현재 탐색하는 자리의 인덱스 r, c
		r = i;
		c = j;
		int count = 0;
		// 해당 자리에 앉은 학생 주변을 탐색하여 좋아하는 학생이라면 count 추가
		int stdNum = room[i][j].number;
		for (int d = 0; d < 4; d++) {
		    nr = r + dr[d];
		    nc = c + dc[d];

		    // 탐색 가능한 자리라면
		    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			// 해당 자리에 앉은 좋아하는 학생인지 확인하고 맞다면 count 증가
			if (room[nr][nc].number == students[stdNum - 1][1]
				|| room[nr][nc].number == students[stdNum - 1][2]
				|| room[nr][nc].number == students[stdNum - 1][3]
				|| room[nr][nc].number == students[stdNum - 1][4]) {
			    count++;
			}
		    }
		}
		// count 만큼 total 증가
		switch (count) {
		case 0:
		    break;
		case 1:
		    total += 1;
		    break;
		case 2:
		    total += 10;
		    break;
		case 3:
		    total += 100;
		    break;
		case 4:
		    total += 1000;
		    break;
		}
	    }
	}
	System.out.println(total);
    }

}


