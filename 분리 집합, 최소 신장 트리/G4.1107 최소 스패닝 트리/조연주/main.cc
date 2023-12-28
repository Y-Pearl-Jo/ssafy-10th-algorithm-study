// 메모리: 4496 KB, 시간: 36 ms
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
typedef pair<int,pair<int, int>> pp;

int parent[10001];
vector<pp> graph;

int find(int a) {
	if (parent[a] == a) {
		return a;
	}
	return parent[a] = find(parent[a]);
}

void uni(int a, int b) {
	a = find(a);
	b = find(b);
	parent[b] = a;
}

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int V, E;
	cin >> V >> E;

	// 부모 노드 배열
	for (int i = 1; i <= V; i++) {
		parent[i] = i;
	}

	// 간선 정보 입력받기
	for (int i = 0; i < E; i++) {
		int from, to, cost;
		cin >> from >> to >> cost;
		graph.push_back({ cost,{from, to} });
	}
	sort(graph.begin(), graph.end());

	// 간선 생성
	int ans = 0;
	for (int i = 0; i < graph.size(); i++) {
		pp edge = graph[i];
		int cost = edge.first;
		int from = edge.second.first;
		int to = edge.second.second;

		if (find(from) != find(to)) {
			ans += cost;
			uni(from, to);
		}
	}
	cout << ans;
  
	return 0;
}
