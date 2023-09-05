import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 숫자 카드의 개수
		
		// 숫자 입력 받기
		int[] arr= new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬
		Arrays.sort(arr);
		
		// 출력
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<M; i++) {
			System.out.print(binarySearch(arr,Integer.parseInt(st.nextToken()),0,N-1)+" ");
		}
		
	}
	
	// 이진탐색
	static int binarySearch(int[] array, int target, int start, int end) {
		if(start>end) {
			return 0;
		}
		
		int mid = (start+end)/2;
		
		if(array[mid]==target) {
			return 1;
		}
		
		else if(array[mid]<target) {
			return binarySearch(array,target,mid+1,end);
		}
		else {
			return binarySearch(array,target,start,mid-1);
		}

	}

}
