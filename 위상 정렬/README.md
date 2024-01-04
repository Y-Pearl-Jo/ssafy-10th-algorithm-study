## 위상 정렬

- ****位相 (자리 위, 서로 상) :**** 어떤 사물이 다른 사물과의 관계 속에서 가지는 위치나 양상
- **순서가 정해져 있는 작업**을 차례로 수행해야 할 때, 작업 순서를 정렬
    
    ![111](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/53993041/5b684ba3-8988-49f9-b6ce-457670fe439c)

## 어떤 문제에 쓰지?

- 뭔가 **먼저** 하고, 뭔가 **나중에** 해야 할 때 (시간, 공간, 우선 순위, 비교...)
  
  ![순서2](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/53993041/9447c612-5dab-4cf5-99b5-18477162c55c)
  ![시작출발](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/53993041/122a8fca-bdcf-41d4-ab33-dc5c037842ce)
  ![위계](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/53993041/005d4fdf-335b-4f76-aa92-aa868b898918)
 
- Directed Acyclic Graph(**DAG, 방향 비순환 그래프**)에만 적용 가능
    - **사이클이 없는** 그래프
    - **시작점** 존재
        

## 위상 정렬로 알 수 있는 것

1. 사이클 존재 여부 (정렬 불가능)
2. 정렬 결과


## 위상정렬 과정

- 시간복잡도: **O(V+E)**
- 자료구조
    - **큐**
- 변수
    - **그래프 간선 정보**
    - **진입차수(**각 정점에 꽃히는 간선의 개수) ****배열
- 순서
    - 1.  그래프 간선 정보, 진입 차수 입력 받기
        
        ![t1.PNG](https://prod-files-secure.s3.us-west-2.amazonaws.com/3fb61ab6-7350-4037-a50a-d8a434b46e3a/14add20f-8b85-45af-8555-a902101d4c34/t1.png)
        
    - 2.  진입 차수가 0인 정점 → 큐에 넣기
        
        ![t2.PNG](https://prod-files-secure.s3.us-west-2.amazonaws.com/3fb61ab6-7350-4037-a50a-d8a434b46e3a/163d787e-9ac1-4dbf-af9e-f9f60ceff456/t2.png)
        
    - 3.  큐에서 노드 하나 꺼내기 → 방문 노드로 설정
        
        ![t3.PNG](https://prod-files-secure.s3.us-west-2.amazonaws.com/3fb61ab6-7350-4037-a50a-d8a434b46e3a/a512dbbb-ba7b-44e3-b3a5-08312918ca31/t3.png)
        
    - 4.  방문 노드와 인접한 노드 사이의 간선 제거 → 인접 노드의 진입차수 -1
        
        ![t4.PNG](https://prod-files-secure.s3.us-west-2.amazonaws.com/3fb61ab6-7350-4037-a50a-d8a434b46e3a/8b2997bc-e1a4-494f-acf7-25e65c8917fb/t4.png)
        
    - 5.  인접 노드의 진입차수가 0이라면? → 큐에 넣기
        
        ![t5.PNG](https://prod-files-secure.s3.us-west-2.amazonaws.com/3fb61ab6-7350-4037-a50a-d8a434b46e3a/6144ed4f-ae3c-4cb5-8f51-dc103c636568/t5.png)
        
    
          6.  큐가 빌 때까지 3~5반복
    
          a. 모든 정점을 방문하기 전에 큐가 빈다면? 사이클 존재
    
    - b. 모든 정점을 방문했다면? 큐에서 꺼낸 순서가 위상정렬 결과
        
        ![t6.PNG](https://prod-files-secure.s3.us-west-2.amazonaws.com/3fb61ab6-7350-4037-a50a-d8a434b46e3a/03de18ca-d6de-4352-b586-a3427e4d6643/t6.png)
        
    
       
 ## 코드   
  - Java
        
        ```java
        import java.util.*;
        
        public class Main {
            public static int v, e; // 정점, 간선 개수
            public static int[] indegree = new int[100001]; // 진입 차수 저장
            public static List<List<Integer>> graph = new ArrayList<ArrayList<Integer>>(); // 간선 정보 저장
        
        		public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
        
                v = sc.nextInt();
                e = sc.nextInt();
        
                // 그래프 초기화
                for (int i = 0; i <= v; i++) {
                    graph.add(new ArrayList<Integer>());
                }
        
                // 간선 정보 입력 받기
                for (int i = 0; i < e; i++) {
                    int a = sc.nextInt();
                    int b = sc.nextInt();
                    graph.get(a).add(b); // 정점 A에서 B로 이동 가능
                    indegree[b] += 1; // 진입차수+1
                }
        
                topology_sort();
            }
        
            // 위상 정렬
            public static void topology_sort() {
                ArrayList<Integer> result = new ArrayList<>(); // 수행 결과를 담을 리스트
                Queue<Integer> q = new LinkedList<>(); // 큐
        
                // 진입차수가 0인 노드를 큐에 넣기
                for (int i = 1; i <= v; i++) {
                    if (indegree[i] == 0) {
                        q.offer(i);
                    }
                }
        
              // 정점 개수만큼 반복
              for(int i=0; i<v; i++){
                  // v번 실행을 마치기 전 큐가 비었다면 -> 사이클 존재
                  if(q.isEmpty){
                    system.out.println("사이클 발생");
                  }
      
                  // 큐에서 원소 꺼내기
                  int now = q.poll();
                  result.add(now);
                  // 해당 원소와 연결된 노드들의 진입차수-1
                  for (int i = 0; i < graph.get(now).size(); i++) {
                      indegree[graph.get(now).get(i)] -= 1;
                      // 새롭게 진입차수가 0이 되는 노드를 큐에 넣기
                      if (indegree[graph.get(now).get(i)] == 0) {
                          q.offer(graph.get(now).get(i));
                      }
                  }
              }
        
                // 결과 출력
                for (int i = 0; i < result.size(); i++) {
                    System.out.print(result.get(i) + " ");
                }
            }
        
        }
        ```
        
- C++
        
        ```cpp
        int n; // 정점의 개수
        vector<int> inDegree; // 진입 차수 저장
        vector<vector<int>> graph; // 간선 정보 저장
        queue<int> q; // 위상 정렬을 위한 큐
        
        void input(){
            cin >> n;
            for(int i = 0; i < n; i++){
                int s, e;
                cin >> s >> e;
                graph[s].emplace_back(e);
                inDegree[e]++; // 진입 차수 +1
            }
        }
        
        void topology_sort(){
            // 위상 정렬을 시작하기 전에 진입 차수가 0인 정점들을 큐에 삽입한다.
            for(int cur = 1; cur <= n; cur++){
                if(inDegree[cur] == 0)    q.push(cur);
            }
            
            // 총 n번 실행
            for(int i = 0; i < n; i++){
                // n번 실행하기 전에 queue가 비면 사이클이 발생했다는 뜻이다.
                if(q.empty()){
                    cout << "사이클 발생\n";
                    return;
                }
                
                // queue에서 뽑아서 현재 방문 노드로 설정
                int cur = q.front();
                q.pop();
                cout << "방문 노드 : " << cur << '\n';
                // 인접한 노드들을 확인하며 진입 차수를 1씩 줄여주고, 만일 진입 차수가 0이라면 queue에 삽입한다.
                for(int k = 0; k < graph[cur].size(); k++){
                    int next = graph[cur][k];
                    if(--inDegree[next] == 0)   q.push(next);
                }
            }
        }
        ```
