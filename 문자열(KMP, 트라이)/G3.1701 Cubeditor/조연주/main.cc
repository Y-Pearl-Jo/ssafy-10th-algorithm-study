// 메모리: 2160 KB, 시간: 80 ms
#include<iostream>
#include<vector>
#include<algorithm>
#include<cstring>
using namespace std;

// 접두사와 접미사 일치하는 최대 일치 길이 테이블 구하기
int makeTable(string str) { // str : 검색할 문자열
	int strLen = str.size();
	vector<int>	table(strLen, 0);
	int len = 0;
	int maxLen = 0;

	for (int end = 1; end < strLen; end++) {
		
		// 불일치
		while (len > 0 && str[end] != str[len]) {
			maxLen = max(maxLen, len);
			len = table[len - 1];
		}
		// 일치
		if (str[end] == str[len]) {
			table[end] = ++len;
			// 끝까지 간 경우
			if (end == strLen - 1) {
				maxLen = max(maxLen, len);
			}
		}
	}
	return maxLen;
}


int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	string str;
	cin >> str;

	int ans = 0;

	for (int i = 0; i < str.length()-1; i++) {
		int len = makeTable(str.substr(i, str.length() - i));
		ans = max(ans, len);
	}

	cout << ans;

	return 0;
}
