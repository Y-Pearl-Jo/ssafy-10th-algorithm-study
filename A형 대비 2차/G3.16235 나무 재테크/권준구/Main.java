import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

class Tree {
    int r, c, age;

    public Tree(int r, int c, int age) {
        this.r = r;
        this.c = c;
        this.age = age;
    }
}

public class Main {
    static int N, M, K;
    static int[][] map, S2D2;
    static List<Tree> list = new ArrayList<>();
    static List<Tree> dieList = new ArrayList<>();

    static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 양분 초기화
        map = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = 5;
            }
        }

        // 겨울에 머신이 뿌릴 양분
        S2D2 = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                S2D2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        // 어린 나무부터 정렬
        Collections.sort(list, new Comparator<Tree>() {
            @Override
            public int compare(Tree o1, Tree o2) {
                return o1.age - o2.age;
            }
        });

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        System.out.println(list.size());
    }

    // 봄
    public static void spring() {
        Iterator<Tree> iter = list.iterator();
        while (iter.hasNext()) {
            Tree tree = iter.next();
            if (map[tree.r][tree.c] >= tree.age) { // 양분이 충분한 경우
                map[tree.r][tree.c] -= tree.age; // 양분을 먹음
                tree.age += 1; // 나이가 1 증가
            } else { // 양분이 부족한 경우
                dieList.add(new Tree(tree.r, tree.c, tree.age)); // 죽은 나무 리스트에 추가
                iter.remove(); // 살아있는 나무 리스트에서 제거
            }
        }
    }

    // 여름
    public static void summer() {
        for (int i = 0; i < dieList.size(); i++) {
            map[dieList.get(i).r][dieList.get(i).c] += dieList.get(i).age / 2; // 죽은 나무가 양분으로 변함
        }
        dieList.clear(); // 죽은 나무 리스트 초기화
    }

    // 가을
    public static void autumn() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).age % 5 == 0) { // 나이가 5의 배수인 경우
                for (int d = 0; d < 8; d++) {
                    int nr = list.get(i).r + dr[d];
                    int nc = list.get(i).c + dc[d];
                    if (nr >= 1 && nc >= 1 && nr <= N && nc <= N) {
                        list.add(new Tree(nr, nc, 1)); // 인접한 8개의 칸에 나이가 1인 나무 생성
                    }
                }
            }
        }
    }

    // 겨울
    public static void winter() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                map[i][j] += S2D2[i][j]; // 겨울에 양분 추가
            }
        }
    }
}
