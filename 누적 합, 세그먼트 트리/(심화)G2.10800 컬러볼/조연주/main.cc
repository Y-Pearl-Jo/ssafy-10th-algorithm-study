// 8332 KB, 88 ms
#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

struct ball {
	int c, s, idx; // 색, 크기, 순서
};

vector <ball> v;

int N;
int C[200001]; // 색깔별 합
int S[2001]; // 크기별 합
int ans[200001]; // 정답 배열

// 정렬 기준
bool compare(ball& a, ball& b) {
	if (a.s == b.s) {
		return a.c < b.c;
	}
	return a.s < b.s;
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
    
	cin >> N; // 공 개수

	// 공 정보 입력받기
	for (int i = 0; i < N; i++) {
		int c, s;
		cin >> c >> s;
		v.push_back({ c,s,i });
	}

	// 정렬
	sort(v.begin(), v.end(),compare);

	// 누적합
	int sum = 0;

	for (int i = 0; i < N; i++) {
		int color = v[i].c;
		int size = v[i].s;
		int idx = v[i].idx;

		sum += size;
		C[color] += size;
		S[size] += size;

		// 이전 공과 크기, 색이 모두 같은 경우
		if (i != 0 && color == v[i - 1].c && size == v[i - 1].s) {
			ans[idx] = ans[v[i - 1].idx];
		}
		// 그렇지 않은 경우
		else {
			// 현재까지의 합 - 같은 색 합 - 같은 크기 합 - 자신의 크기
			ans[idx] = sum - C[color] - S[size] + size;
		}
	}

	// 출력
	for (int i = 0; i < N; i++) {
		cout << ans[i] << "\n";
	}

	return 0;
}
