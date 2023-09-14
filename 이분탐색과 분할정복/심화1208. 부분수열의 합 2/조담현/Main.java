import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 1. 백트래킹으로 모든 경우의 수를 확인하면 부분집합의 개수 2^n개를 확인해 보아야 한다.
 * 1-1. n이 최대 40이므로 2^40개의 경우의 수를 확인해야 하므로 시간초과. O(2^n)
 * 
 * 2. N개의 수를 2개의 그룹으로 나눈 뒤, 각 그룹에서 나올 수 있는 누적합을 백트래킹으로 리스트 1, 2에 추가한다.
 * 2-1. 리스트1과 리스트2를 정렬한다.
 * 2-2. 리스트1에서 하나씩의 누적합에 대해 S에서 뺀 나머지 값이 리스트 2에 있는지 이분탐색으로 확인한다.
 * 2-3. 있다면, 리스트 2에서 확인한 누적합의 개수를 더한다.
 * 2-4. O(2^N/2) + O(N*logN)
 *
 * 3. 2-1.까지 동일하게 한 후에 투포인터를 활용하여 두 리스트에서 합이 sum이 되는 경우의 수를 찾는다.
 * 
 */



public class Main {
	
	static int N;
	static int S;
	static int[] arr;
	static ArrayList<Integer> list1 = new ArrayList<>();
	static ArrayList<Integer> list2 = new ArrayList<>();
	static long answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	    
    // 두 그룹으로 나누어 누적합을 두개의 리스트에 저장한다.
    
		dfs(list1, 0, N/2, 0);
		dfs(list2, N/2, N, 0);
    // 2-1. 리스트1과 리스트2를 정렬한다.
		Collections.sort(list1);
		Collections.sort(list2);
		
    // list1의 원소에 대해 list2에 s-list1(i)가 있는지 확인하고, 개수를 더해준다.
    
    for(int i=0; i<list1.size(); i++) {
        // 2-3. 리스트1에서 하나씩의 누적합에 대해 S에서 뺀 나머지 값이 리스트 2에 있는지 이분탐색으로 확인한다.
        int target = S - list1.get(i);
        // target이 등장하는 가장 왼쪽 인덱스와 오른쪽 인덱스를 찾아서 그 차를 더해준다(개수)
        int low = lower_bound(list2, target);
        int high = upper_bound(list2, target);
        answer+= (long)high-low;
    }

    // S가 0이라면 공집합을 선택했을 수 있으므로 1을 빼준다.
		if(S==0) answer--;
		System.out.println(answer);
	}

  // 2-2. N개의 수를 2개의 그룹으로 나눈 뒤, 각 그룹에서 나올 수 있는 누적합을 백트래킹으로 리스트 1, 2에 추가한다.
	public static void dfs(ArrayList<Integer> list, int start, int end, int sum) {
		if(start==end) {
			list.add(sum);
			return;
		}
		dfs(list, start+1, end, sum+arr[start]);
		dfs(list, start+1, end, sum);
	}

  // target이 해당하는 값의 가장 왼쪽 인덱스를 이분탐색하는 함수
	public static int lower_bound(List<Integer> list, int target) {
		int start = 0;
        int end = list.size();
        while(start<end) {
            int mid = (start+end)/2;
            if(list.get(mid)>=target) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        
        return end;
    }
  
    // target이 해당하는 값의 가장 오른쪽 인덱스를 이분탐색하는 함수
    public static int upper_bound(List<Integer> list, int target) {
		int start = 0;
        int end = list.size();
        while(start<end) {
            int mid = (start+end)/2;
            if(list.get(mid)<=target) {
                start = mid+1;
            } else {
                end = mid;
            }
        }
        
        return end;
    }
    
	
}
