// 5536kb 92ms
#include <iostream>

using namespace std;
using p = pair<int,int>;

int N,M;
int num[100001];
p tree[400004];

p makeTree(int start, int end, int node){
    if(start == end){
        return tree[node] = {num[start],num[start]};
    }
    
    int mid = (start+end)/2;
    p p1 = makeTree(start,mid,node*2);
    p p2 = makeTree(mid+1,end,(node*2)+1);
    
    return tree[node] = {min(p1.first,p2.first), max(p1.second, p2.second)};
    
}

p getAns(int start, int end, int node, int left, int right){
    if(end < left || start > right){
        return {1e9+1,0};
    }
    
    if(left <= start && end <= right){
        return tree[node];
    }
    
    int mid = (start+end)/2;
    p p1 = getAns(start,mid,node*2,left,right);
    p p2 = getAns(mid+1,end,(node*2)+1,left,right);
    
    return {min(p1.first,p2.first), max(p1.second, p2.second)};
    
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> M;
    for(int i=1; i<=N; i++){
        cin >> num[i];
    }
    
    makeTree(1,N,1);
    
    while(M--){
        int a,b;
        cin >> a >> b;
        p ans = getAns(1,N,1,a,b);
        cout << ans.first << " " << ans.second << "\n";
    }

    return 0;
}
