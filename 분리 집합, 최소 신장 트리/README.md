

**스패닝 트리는 그래프 내의 모든 정점을 연결하지만 사이클이 없는 트리**입니다.

밑의 그래프는 예시로 만들어본 그냥 트리입니다. 정점 4개와 6개의 간선으로 이루어져있습니다.

[##_Image|kage@cZ6GvM/btsCEHvsFNQ/N3iejvMJNTpnxYe8fIbUc0/img.png|CDM|1.3|{"originWidth":1381,"originHeight":999,"style":"alignCenter","width":431,"height":312,"caption":"그냥 트리"}_##]

다음 그래프는 정점 4개와 3개의 간선으로 이루어져있습니다. **최소한의 간선의 갯수로 이루어져 있고 모든 정점이 연결된 스패닝 트리**의 형태입니다. 스패닝 트리는 다음과 같이 **간선의 갯수가 정점의 갯수 - 1 개**라는 특징이 있습니다.

[##_Image|kage@bP6Jq2/btsCKpnjo8K/TenSAt8EeCyUJDgq7zp3ik/img.png|CDM|1.3|{"originWidth":1426,"originHeight":1000,"style":"alignCenter","width":442,"height":310}_##]

**최소 스패닝 트리는 이러한 스패닝 트리 중 사용된 가중치 합이 최소인 트리**를 뜻합니다.

> **스패닝 트리**  
>  **- 최소한의 간선의 갯수로 이루어져 있고 모든 정점이 연결됨**  
>  **- 간선의 갯수 = 정점의 갯수 - 1**  
>   
> **최소 스패닝 트리**  
>  **- 스패닝 트리 중 사용된 가중치 합이 최소인 트리** 

최소 스패닝 트리를 구하는 알고리즘은 크루스칼, 프림 두가지가 있습니다.

이번 포스팅에서는 프림 알고리즘에 대해 설명하겠습니다.

> **개념**

프림 알고리즘은 **하나의 시작점으로 구성된 트리에 간선을 하나씩 추가**하는 형태입니다.

정점 선택을 기반으로 하며 이전단계에서 만들어진 신장트리를 확장합니다.

정점의 수에 영향을 받기에 **정점의 수가 적고 간선의 수가 많은 그래프에서 효과적**입니다.

**프림**

-   하나의 시작점으로 구성된 트리에 간선을 추가 -> 이전단계에서 만들어진 신장 트리를 확장
-   정점 선택 기반
-   정점(노드)의 수에 영향을 받기에 정점의 수가 적고 간선의 수가 많은 그래프에서 효과적

> **구현 예시**

[##_Image|kage@bIubXq/btsCAgL1B3E/a1hhxiBvCwpHG5899VTZv0/img.png|CDM|1.3|{"originWidth":1214,"originHeight":1000,"style":"alignCenter","width":613,"height":505}_##]

위키백과에서 프림을 설명하기 위한 그래프 예시를 가져왔습니다. 

먼저 임의의 점을 선택해 줍니다.

편하게 A를 시작점으로 설정하겠습니다.

A에는 A와 B를 연결하는 가중치 7인 간선과 A와 D를 연결하는 가중치 5인 간선이 있습니다.

가중치가 적은 간선을 연결합니다.

[##_Image|kage@cDjtB0/btsCKsRUifM/38YT5YmK4GByvlWu72nM81/img.png|CDM|1.3|{"originWidth":1167,"originHeight":1000,"style":"alignCenter","width":610,"height":523}_##]

연결된 정점, 간선은 빨간색으로 표시하고 사용할 수 있는 간선은 파란색으로 표시했습니다.

사용할 수 있는 간선 중 가중치가 가장 낮은 간선을 연결해줍니다.

[##_Image|kage@CUcVn/btsCzS5C6XQ/Zx0VxS8FrzSihKj712xpnK/img.png|CDM|1.3|{"originWidth":1160,"originHeight":1000,"style":"alignCenter","width":643,"height":554}_##]

A, D, F가 연결되었습니다.

다시 파란 간선중 가중치가 가장 낮은 간선을 연결합니다.

[##_Image|kage@cqmHgm/btsCzReBvJw/Unn6uCQapsskonQtiahCpk/img.png|CDM|1.3|{"originWidth":1096,"originHeight":999,"style":"alignCenter","width":622,"height":567}_##]

A, B, D, F가 연결되었습니다.

또한 D와 B를 연결하던 가중치 9인 간선은 의미가 없어지므로 배제합니다.

위의 과정들을 반복하면 다음과 같은 최소 스패닝 트리가 완성됩니다.

[##_Image|kage@bvEPWh/btsCCx7LX3f/SMBiOtSJ7iyaQIhXyZTgU1/img.png|CDM|1.3|{"originWidth":1087,"originHeight":1000,"style":"alignCenter","width":599,"height":551}_##]

과정을 요약해보겠습니다.

> 1\. 임의의 정점을 선택  
> 2\. 가중치가 가장 낮은 간선 연결  
> 3\. 사용할 수 있는 간선 중 연결된 정점 제외하고 가중치가 가장 낮은 간선 연결  
> 4\. 모든 정점이 연결될 때까지 반복

> **문제 예시**


[문제 링크 : https://www.acmicpc.net/problem/1197](https://www.acmicpc.net/problem/1197)

 [1197번: 최소 스패닝 트리

첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다. 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이

www.acmicpc.net](https://www.acmicpc.net/problem/1197)

**정점 사용은 방문배열**을 통해 처리하였고 **가중치 순 정렬을 위해 우선순위 큐**를 사용하였습니다.

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 가중치가 작은 순서부터 정렬
class Node implements Comparable<Node> {
    int v;
    int w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return this.w - o.w;
    }
}


class Main {
    static int sum;                             // 합을 구할 sum
    static ArrayList<ArrayList<Node>> graph;    // 간선 정보를 저장할 그래프
    static boolean[] visit;                     // 방문 확인할 visit

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());   // 정점의 갯수
        int E = Integer.parseInt(st.nextToken());   // 간선의 갯수

        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {           // 인덱스 혼동을 피하고자 V+1
            graph.add(new ArrayList<>());
        }

        visit = new boolean[V + 1];                 // 위와 마찬가지

        // A가 B와 연결되어 있다면 B와 A도 연결되어 있음 -> 양쪽으로 추가
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        // 임의의 정점에서 시작
        prim(new Node(1, 0));
        System.out.println(sum);

    }

    static void prim(Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();     // 우선순위 큐 사용 -> 가중치순 정렬
        pq.add(start);
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (visit[now.v]) {         // 방문했으면 (= 만약 정점이 저장되어있으면) 넘어가라
                continue;
            }
            visit[now.v] = true;        // 방문처리(= 정점 저장)
            sum += now.w;               // 가중치 저장
            for (Node next : graph.get(now.v)) {
                if (!visit[next.v]) {
                    pq.add(next);
                }
            }
        }
    }
}
```

---

> **다익스트라 vs 프림**

구현하다보면 다익스트라 알고리즘과 유사하다는 느낌을 받을 수 있습니다.

두 알고리즘의 차이를 살펴보겠습니다.

**다익스트라 : 두 정점 사이의 촤단거리를 구한다.**

**프림 : 모든 노드들을 최소비용으로 연결한다.**

위의 표를 예시로 들겠습니다.

시작점을 B로 설정해도 프림 알고리즘의 결과는 동일합니다.

완성된 최소 스패닝 트리를 통해 B에서 C로 간다고 할때 가중치의 합은 7 + 5로 12 입니다.

다익스트라로 간다고 쳤을때는 8 입니다.

**프림은 두 노드 사이의 거리가 최소가 아닐 수 있습니다**.

또한 **프림은 방향이 없는 그래프에서만 작용하며, 다익스트라는 방향이 있든 없든 모두 사용 가능**합니다.

결과적으로 비슷한 구현 방법이고 얻어걸려서 같을 수는 있지만

**프림은 다익스트라를 보장하지 않고, 다익스트라 또한 프림을 보장하지 않습니다.**

> **개념**

크루스칼 알고리즘은 **간선선택을 기반**으로 합니다.

간선을 가중치의 오름차순으로 정렬하고 **사이클을 형성하지 않을 때** 간선을 선택합니다.

정점(노드)의 개수가 N개라고 했을때 간선의 갯수가 N-1이 될 때까지 이 과정을 반복합니다.

사이클 판단은 **UnionFind**를 사용합니다. UnionFind는 Union(x,y)연산과 Find(x)연산으로 이루어져 있습니다.

 Union연산은 x와 y를 합치는 연산이고 find연산은 그래프의 부모 노드를 찾는 연산입니다.

**크루스칼**

-   간선선택 기반
-   간선을 가중치 기준 오름차순으로 정렬하여 사이클을 형성하지 않는 선에서 선택
-   UnionFind를 통해서 사이클 확인
-   사실상 정렬+UnionFind

> **구현예시**

다음 그래프의 최소신장트리를 크루스칼 알고리즘을 통해 구해보겠습니다.

[##_Image|kage@otsCt/btsCG5KdJa6/y29noGgbbPgwaYxyRGlPv0/img.png|CDM|1.3|{"originWidth":1375,"originHeight":1000,"style":"alignCenter","width":565,"height":411}_##]

먼저 UnionFind를 위한 부모노드 배열을 설정합니다.

부모노드 배열 초기상태는 다음과 같습니다.

[##_Image|kage@kQO22/btsCDgyq8TC/40BoFIbV7FhMcCbCV3Oku1/img.png|CDM|1.3|{"originWidth":2069,"originHeight":1000,"style":"alignCenter","width":503,"height":243}_##]

후에 가중치 별로 간선을 정렬해줍니다.

가중치가 같은 경우에는 from이 낮은 순서로 정렬했습니다.

[##_Image|kage@kQ386/btsCG3S5zPY/RkXwLVFsrm98GRhCyFpe10/img.png|CDM|1.3|{"originWidth":1000,"originHeight":1184,"style":"alignCenter","width":626,"height":741}_##]

이제 가중치가 낮은 순서부터 선택합니다.

[##_Image|kage@bY990x/btsCK80qnov/48Qc4Kz3Bds2WAY5t3IjL0/img.png|CDM|1.3|{"originWidth":1709,"originHeight":1000,"style":"alignCenter","width":624,"height":365}_##]

간선 선택은 빨간색 동그라미로 표현하였습니다.

선택된 간선은 부모 노드 배열 값을 변경함으로 표시합니다.

[##_Image|kage@b29PYf/btsCIEyKAkq/NRYf51pWy1lRzaDcsDTUJK/img.png|CDM|1.3|{"originWidth":1180,"originHeight":1000,"style":"alignCenter","width":646,"height":547}_##]

2와 4를 연결하는 4번째 간선은 선택하지 않습니다.

2의 부모는 1 이고 4의 부모 또한 1입니다.

**둘의 부모가 같기에 이 간선을 선택하면 사이클이 형성**됩니다.

[##_Image|kage@bwFqYg/btsCKq1ko9I/rFmEaI9EFFF7kgf0NmKOV1/img.png|CDM|1.3|{"originWidth":1077,"originHeight":1000,"style":"alignCenter","width":617,"height":573}_##]

총 **5개의 정점을 가진 상태에서 4개의 간선**을 선택하였습니다.

**부모노드 배열의 값은 모두 같아진 상태**를 확인할 수 있습니다.

[##_Image|kage@bb5u0q/btsCLaKICCH/fOPi3Qu0IKrWv9CNzWHcg0/img.png|CDM|1.3|{"originWidth":1000,"originHeight":1112,"style":"alignCenter","width":588,"height":654}_##]

완성된 최소신장 트리는 다음과 같습니다.

[##_Image|kage@YMEFV/btsCIGDmCp8/yKFKddNXWZXkSK7bbiLX9k/img.png|CDM|1.3|{"originWidth":1245,"originHeight":1000,"style":"alignCenter","width":580,"height":466}_##]

모든 정점이 포함된 것을 확인할 수 있습니다.

과정을 요약해보겠습니다.

> 1\. 가중치가 낮은 순으로 정렬  
> 2\. 사이클을 형성하지 않는 선에서 간선 선택  
> 3\. 정점이 N개일 때 선택한 간선의 갯수가 N-1이 될 때까지 위의 과정 반복 

> **문제 예시**

백준 1197 최소스패닝트리 문제를 사용하였습니다.

[문제 링크 :](https://www.acmicpc.net/problem/1197) [https://www.acmicpc.net/problem/1197](https://www.acmicpc.net/problem/1197)

 [1197번: 최소 스패닝 트리

첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다. 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이

www.acmicpc.net](https://www.acmicpc.net/problem/1197)

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 가중치 오름차순 정렬기준 설정
class Edge implements Comparable<Edge> {
    int from;
    int to;
    int w;


    public Edge(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", w=" + w +
                '}';
    }
}

class Main {
    static ArrayList<Edge> edgeList;    // 간선정보 저장
    static int[] parents;               // 부모 노드 찾기용
    static int V, E, sum, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        parents = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {   // 부모노드 초기설정
            parents[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(A, B, C));
        }

        Kruskal();
        System.out.println(sum);
    }

    static void Kruskal() {
        Collections.sort(edgeList);             // 정렬

        for (int i = 0; i < E; i++) {           // edgeList에 저장된 간선을 차례로 가져오며
            int a = edgeList.get(i).from;       // 사용할 간선을 선택하는 과정
            int b = edgeList.get(i).to;         // a와 b를 union메서드로 사용해도 될 간선인지 확인 후
            if (union(a, b)) {                  // 사용해도 되면 sum에 가중치 값을 더하고
                sum += edgeList.get(i).w;       // count++
                count++;
            }
            if (count == V - 1) {               // count가 정점의 갯수-1이 되면 break
                break;
            }
        }
    }

    // 재귀 형태로 부모를 찾음
    public static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    // x와 y의 부모가 같으면 false 아니면 부모를 같게 한 후에(연결한 후에) true
    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        } else if (x != y) {
            parents[y] = x;
        }
        return true;
    }
}
```
