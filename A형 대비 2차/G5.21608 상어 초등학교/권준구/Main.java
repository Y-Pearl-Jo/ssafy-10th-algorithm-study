import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Student> students = new ArrayList<>();
    static Student[][] map;
    static int[] dx = new int[] { 0, 0, 1, -1 }; // 상하좌우 방향 이동을 위한 배열
    static int[] dy = new int[] { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine()); // 학생 수 및 교실 크기 입력
        map = new Student[N][N]; // 교실을 나타내는 배열 초기화
        StringTokenizer st;

        // 학생 정보 입력 받기
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = stoi(st.nextToken()); // 학생 번호
            students.add(new Student(num, new int[] { stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()) }));
        }

        placement(); // 학생들을 교실에 배치
        System.out.println(satisfaction()); // 만족도 계산 및 출력
    }

    private static int satisfaction() {
        int sum = 0;
        // 교실을 돌면서 각 학생의 만족도를 계산하여 합산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += Math.pow(10, check(i, j, map[i][j].prefer).like - 1);
            }
        }
        return sum;
    }

    private static void placement() {
        for (Student s : students) {
            findSeat(s.num, s.prefer); // 학생을 좌석에 배치
        }
    }

    private static void findSeat(int num, int[] prefer) {
        // 사방탐색을 통해 좋아하는 학생 수, 빈 자리 수, 행, 열 순으로 정렬된 리스트 생성
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != null) {
                    continue;
                }
                seats.add(check(i, j, prefer));
            }
        }
        Collections.sort(seats); // 리스트를 정렬하여 가장 적합한 좌석 선택
        map[seats.get(0).x][seats.get(0).y] = new Student(num, prefer); // 학생을 선택한 좌석에 배치
    }

    private static Seat check(int x, int y, int[] prefer) {
        Seat seat = new Seat(x, y);
        for (int i = 0; i < 4; i++) { // 상하좌우 방향에 대해 확인
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isValid(nx, ny)) { // 교실 범위를 벗어나면 스킵
                continue;
            }
            if (map[nx][ny] == null) { // 인접한 자리가 비어있으면 빈 자리 수 증가
                seat.empty++;
            } else { // 인접한 자리에 다른 학생이 있으면
                for (int j = 0; j < prefer.length; j++) {
                    if (prefer[j] == map[nx][ny].num) {
                        seat.like++; // 좋아하는 학생 수 증가
                    }
                }
            }
        }
        return seat;
    }

    static class Student {
        int num; // 학생 번호
        int[] prefer; // 학생이 좋아하는 학생들의 번호 배열

        Student(int num, int[] prefer) {
            this.num = num;
            this.prefer = prefer;
        }
    }

    static class Seat implements Comparable<Seat> {
        int x; // 행
        int y; // 열
        int like; // 좋아하는 학생 수
        int empty; // 빈 자리 수

        public Seat(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Seat o) {
            if (this.like != o.like) {
                return o.like - this.like;
            }
            if (this.empty != o.empty) {
                return o.empty - this.empty;
            }
            if (this.x != o.x) {
                return this.x - o.x;
            }
            return this.y - o.y;
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < N && y < N; // 교실 범위 내에 있는지 확인
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
