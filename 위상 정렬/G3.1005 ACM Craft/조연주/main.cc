// 메모리: 2816 KB, 시간: 168 ms
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    int T; // 테스트 케이스
    cin >> T;

    while (T--) {
        int N, K; // 정점 개수, 간선 개수
        cin >> N >> K;

        // 정점 정보 입력받기
        int time[1001];
        for (int i = 1; i <= N; i++) {
            cin >> time[i];
        }

        // 간선 정보 입력받기
        vector<int> graph[1001];
        int inDegree[1001] = {0};
        while (K--) {
            int X, Y;
            cin >> X >> Y;
            graph[X].push_back(Y);
            inDegree[Y]++;
        }

        int W; // 건설해야 할 건물 번호
        cin >> W;

        // 위상 정렬
        queue<int> q;
        int sum[1001] = {0};

        for (int i = 1; i <= N; i++) {
            // 진입차수가 0인 노드 큐에 넣기
            if (inDegree[i] == 0) {
                q.push(i);
            }
            sum[i] = time[i]; // 해당 노드까지 지었을 때, 건설시간 합
        }

        // 큐가 빌 때까지 반복
        while (!q.empty()) {
            int curNode = q.front();
            q.pop();

            // 인접 노드 개수만큼 반복
            for (int i = 0; i < graph[curNode].size(); i++) {
                int adjNode = graph[curNode][i];
                sum[adjNode] = max(sum[adjNode], sum[curNode] + time[adjNode]); // 최댓값 저장

                inDegree[adjNode]--; // 인접 노드의 진입 차수 감소시키기
                if (inDegree[adjNode] == 0) {  // 진입 차수가 0이면 큐에 넣기
                    q.push(adjNode);
                }
            }
        }

        // 정답 출력
        cout << sum[W] << "\n";

    }

    return 0;
}
