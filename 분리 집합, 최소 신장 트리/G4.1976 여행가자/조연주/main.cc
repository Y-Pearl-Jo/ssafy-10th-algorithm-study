// 메모리: 2024 KB, 시간: 0 ms
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
typedef pair<int,pair<int, int>> pp;

int parent[201];
int plan[1001];

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

	int N, M;
	cin >> N >> M;

	// 부모 노드 초기화
	for (int i = 1; i <= 200; i++) {
		parent[i] = i;
	}

	// 도시 연결
	for (int i = 1; i <= N; i++) {
		for (int k = 1; k <= N; k++) {
			int n;
			cin >> n;
			if (n == 1) {
				uni(i, k);
			}
		}
	}

	// 여행 루트
	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;
		plan[i] = n;
	}

	// 계산
	for (int i = 0; i < M - 1; i++) {
		if (find(plan[i]) != find(plan[i + 1])) {
			cout << "NO";
			return 0;
		}
	}
	cout << "YES";

	return 0;
}
