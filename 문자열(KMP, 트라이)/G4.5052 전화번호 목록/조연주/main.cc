// 메모리: 7300 KB, 시간: 112 ms
#include<iostream>
#include<string>
#include<cstring>
using namespace std;

struct Trie {
	bool finish;
	Trie* node[10];

	// 생성자
	Trie() : finish(false) {
		memset(node, NULL, sizeof(node));
	}

	// 소멸자
	~Trie() {
		for (int i = 0; i < 10; i++) {
			if (node[i]) delete node[i];
		}
	}

	// 문자열 삽입
	void insert(char* str) {
		if (*str == NULL) {
			finish = true;
			return;
		}

		int cur = *str - '0';
		if (node[cur] == NULL) node[cur] = new Trie();
		node[cur]->insert(str + 1);
		
	}

	// 문자열 찾기
	bool search(char* str) {
		if (*str == NULL) {
			return true;
		}
		else if (finish) {
			return false;
		}
		
		int cur = *str - '0';
		return node[cur]->search(str + 1);
	}

};

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int t;
	cin >> t;

	char phoneNumber[10001][11];

	tc:while (t--) {
		int n;
		cin >> n;
		
		Trie* root = new Trie();
		

		// 입력 받기
		for(int i=0; i<n; i++) {
			cin >> phoneNumber[i];
			root->insert(phoneNumber[i]);
		}

		// 짧은 순으로 검사
		for (int i = 0; i < n; i++) {
			if (!root->search(phoneNumber[i])) {
				cout << "NO\n";
				root->~Trie();
				goto tc;
			}
		}
		
		cout << "YES\n";
		root->~Trie();

	}
	//delete[] phoneNumber;

	return 0;
	
}
