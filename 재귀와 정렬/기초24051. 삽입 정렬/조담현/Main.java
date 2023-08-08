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
    	
    	insertionSort(arr, k);
    	
        }
        
        
        public static void insertionSort(int[]arr, int k) {
    	int count = 0;
    	for(int i=1; i<arr.length; i++) {
    	    int loc = i-1;
    	    int newItem = arr[i];
    	    
    	    while(0<=loc && newItem < arr[loc]) {
    		arr[loc+1] = arr[loc];
    		count++;
    		if(count == k) {
        		System.out.println(arr[loc+1]);
        		return;
        	}
    		loc--;
    	    }
    	    
    	    
    	    
    	    // 삽입이 일어나는 경우에만 count를 올리기 위해
    	    if(loc+1 != i) { 
    	    arr[loc+1] = newItem;
    	    count++;
		if (count == k) {
		    System.out.println(newItem);
		    return;
		}
    	    }
    	    
    	    
    	}
    	System.out.println(-1);
        }
    }
