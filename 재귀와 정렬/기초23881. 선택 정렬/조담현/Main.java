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
	
	selectionSort(arr, k);
	
    }
    
    
    public static void selectionSort(int[]arr, int k) {
	int count = 0;
	for(int i=arr.length-1; i>0; i--) {
	    int max = 0;
	    int idx = 0;
	    for(int j=0; j<=i; j++) {
		if(arr[j]>max) {
		    max = arr[j];
		    idx = j;
		}
	    }
	    if(arr[i]!=max) {
		count++;
		if(count==k) {
		    System.out.println(arr[i]+" "+max);
		    return;
		}
		int temp = arr[i];
		arr[i] = max;
		arr[idx] = temp;
	    }
	} 
	System.out.println(-1);
    }
}
