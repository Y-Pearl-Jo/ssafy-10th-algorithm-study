import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	
	int n = Integer.parseInt(st.nextToken());
	int k = Integer.parseInt(st.nextToken());
	int[] arr = new int[n];
	st = new StringTokenizer(br.readLine());
	for(int i=0; i<n; i++) {
	    int num = Integer.parseInt(st.nextToken());
	    arr[i] = num;
	}
	
	bubbleSort(arr, k);
	
    }
    
    
    public static void bubbleSort(int[]arr, int k) {
	int count = 0;
	for(int i=arr.length-1; i>0; i--) {
	    for(int j=0; j<arr.length-1; j++) {
		if(arr[j]>arr[j+1]) {
		    count++;
		    if(count == k) {
			System.out.println(arr[j+1]+" "+arr[j]);
			return;
		    }
		    int tmp = arr[j+1];
		    arr[j+1] = arr[j];
		    arr[j] = tmp;
		}
	    }
	}
	
	System.out.println(-1);
    }
}
