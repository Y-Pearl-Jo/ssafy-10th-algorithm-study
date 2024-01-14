#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
using namespace std;

string str, ans;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	cin >> str;

	bool isC = false;
	bool isJava = false;
	bool isError = false;

	// 1. 어떤 언어로 썼는지 판별
	for (int i = 0; i < str.length(); i++) {
		// C++ : '_' 사용, 대문자 X
		if (str[i] == '_') {
			if (i == 0 || i == str.length() - 1 || str[i + 1] == '_') {		// 첫번째나 마지막에 오는 경우, 연속 2개인 경우
				isError = true;
				break;
			}
			isC = true;
		}
		// Java : 띄어쓰기 X, 대문자 O
		else if (isupper(str[i])) {
			if (i == 0) {					// 첫번째 문자가 대문자인 경우
				isError = true;
				break;
			}
			isJava = true;
		}
		else if (str[i] == ' ') {			// 띄어쓰기가 있는 경우
			isError = true;
			break;
		}
	}

	// 2. 에러 발생
	if ((isC&&isJava)||isError) {
		cout << "Error!";
	}

	// 3. 그대로 출력 (모두 소문자인 경우
	else if (!(isC || isJava)) {
		cout << str;
	}

	// 4. 변수명 변경
	else {
		// C++ -> Java
		if (isC) {
			for (int k = 0; k < str.length(); k++) {
				if (str[k] == '_') {
					ans.push_back(toupper(str[k + 1]));
					k++;
				}
				else {
					ans.push_back(str[k]);
				}
			}
		}
		// Java -> C++
		else {
			for (int k = 0; k < str.length(); k++) {
				if (isupper(str[k])) {
					ans.push_back('_');
					ans.push_back(tolower(str[k]));
				}
				else {
					ans.push_back(str[k]);
				}
			}
		}
		cout << ans;
	}
}
