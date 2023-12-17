// 5928 KB, 28 ms
#include <iostream>
#include <algorithm>
using namespace std;

int N, H, cnt; // 장애물 개수, 높이, 최소 장애물 구간 수
int ans = 200000; // 최소 장애물 구간 높이
int top[500002];
int bottom[500002];

void prefix() {
	// bottom 1 -> H
	for (int i = 2; i <= H; i++) {
		bottom[i] += bottom[i - 1];
	}

	// top H -> 1
	for (int i = H - 1; i >= 1; i--) {
		top[i] += top[i + 1];
	};
}

void getAnswer() {
	// 석순: bottom[H] - bottom[N-1]
	// 종유석: top[1] - top[N+1]

	for (int i = 1; i <= H; i++) {
		int sum = bottom[H] - bottom[i - 1] + top[1] - top[i + 1];
		if (sum < ans) {
			ans = sum;
			cnt = 1;
		}
		else if (sum == ans) {
			cnt++;
		}
	}

};

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
    
	cin >> N >> H;
	
	// 높이에 따라 개수 세기 (index = 높이)
	for (int i = 0; i < N; i++) {
		int h;
		cin >> h;
	
		switch (i % 2) {
			// 짝수 : bottom
			case 0:
				bottom[h]++;
				break;
			// 홀수 : top
			case 1:
				top[H + 1 - h]++;
		}
	}
	
	prefix(); // 누적 합 구하기
	getAnswer(); // 정답 구하기
	
	// 정답
	cout << ans << " " << cnt;
}
