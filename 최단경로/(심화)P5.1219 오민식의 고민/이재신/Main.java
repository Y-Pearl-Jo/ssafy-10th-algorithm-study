import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리 : 11888 KB, 시간 : 80ms
class Bus {
    int from; // 나가는 정점
    int to; // 들어오는 정점
    int cost; // 정산 금액

    public Bus(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    // 노선 리스트
    static ArrayList<Bus> graph;
    // 십억
    static final int INF = -(int) 1e9;
    // 도시, 버스의 개수
    static int N, M;
    // 출발 도착 도시
    static int cityA, cityB;
    // 도시 별 버는 돈
    static int[] get;
    // 양수 사이클을 돈 후 도착 도시를 갈 수 있는지
    static Queue<Bus> bus = new LinkedList<>();

    // 정점의 개수, 간선의 개수, 출발지
    public static void BellmanFord() {
        long[] money = new long[N];
        Arrays.fill(money, INF);

        money[cityA] = get[cityA];

        // 정점의 개수만큼 반복
        for (int i = 0; i < N; i++) {
            // 간선의 개수만큼 반복
            for (int j = 0; j < M; j++) {
                Bus b = graph.get(j); // 현재 간선

                // 현재 간선의 들어오는 정점에 대해 비교
                if (money[b.from] != INF && money[b.to] < money[b.from] + b.cost) {
                    money[b.to] = money[b.from] + b.cost;
                }
            }
        }

        System.out.println(result(money));
    }

    public static String result(long[] money) {
        // 도착 도시로 갈 수 없는 경우 "gg"
        if (money[cityB] == INF)
            return "gg";
        // 사이클이 존재하여 돈을 무한히 버는 경우 "Gee"
        else if (plus_cycle(money))
            return "Gee";
        // 보통의 경우라면 도착 도시에서 가진 돈
        else
            return money[cityB] + "";
    }

    // 양수 가중치 확인
    // *** 끝 점의 값 변경이 아닌 꼭 BFS를 사용해야 하는 이유 ***
    // 1. money[도착지] = 1억이라고 가정하자
    // 2. 현재 1억이라고 결과가 나온 루트 이외에 도착지로 가는 다른 루트가 존재한다.
    // 3. 두 가지 루트의 갈림길에서 money[갈림길] = 10이었다.
    // 4. 한 곳의 루트에만 사이클이 존재한다
    // 5. 근데 거기서 1씩 밖에 증가를 안한다.
    // 6. M번을 반복문을 돌 던 N * M 번을 돌 던 1억이 될 수 있을까?
    //       (1 <= N, M <= 50) 인데?
    // 7. 기껏해야 2500 증가이다.
    // 8. 아직 갈 길이 한참 멀다.
    // 9. 반복 횟수를 충~분히 주면 BFS를 안해도 된다.
    // 10. 하지만 굳이?
    public static boolean plus_cycle(long[] money) {
        for (int i = 0; i < M; i++) {
            Bus b = graph.get(i); // 현재 간선
            if (money[b.from] != INF && money[b.to] < money[b.from] + b.cost) {
                money[b.to] = money[b.from] + b.cost;
                bus.add(b);
            }
        }

        if (check_INF())
            return true;
        return false;
    }

    // 양수 사이클이 도착 지점에 영향을 주는지 확인
    public static boolean check_INF() {
        boolean visited[] = new boolean[M];
        while (!bus.isEmpty()) {
            Bus now = bus.poll();

            int nfrom = now.to;

            if (nfrom == cityB)
                return true;

            for (int i = 0; i < graph.size(); i++) {
                if (!visited[i] && graph.get(i).from == nfrom) {
                    bus.add(graph.get(i));
                    visited[i] = true;
                }
            }
        }
        return false;
    }

    public static void make() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = init(st);
        cityA = init(st);
        cityB = init(st);
        M = init(st);

        graph = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = init(st);
            int w = init(st);
            int cost = init(st);

            graph.add(new Bus(v, w, -cost));
        }

        get = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            get[i] = init(st);
        }

        // 버스 노선의 가중치 즉 요금을 해당 도시에서 얻을 수 있는 금액과 합산
        // 결국 버스에서 내려 도시에 도착하면 버스 요금을 잃고 해당 도시의 돈을 범
        // 노선의 개수가 50개 밖에 안되기 때문에 시간도 넉넉
        for (int i = 0; i < graph.size(); i++) {
            graph.get(i).cost += get[graph.get(i).to];
        }
    }

    public static void main(String[] args) throws IOException {
        make();
        // 벨만-포드 알고리즘 수행
        BellmanFord();
    }

    static int init() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int init(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}
