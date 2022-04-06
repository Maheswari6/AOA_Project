import java.util.Scanner;
public class Task2
{
	public static int[] MaxSubArray(int[] arr,int n) {
        int[] result = new int[3]; //result contains start_index, end_index, max_sum 
        result[0] = result[1] = -1; // Set the start_index and end_index to -1
        result[2] = Integer.MIN_VALUE;// Set the maximum sum seen till now to - INFINITY
        int curr_sum = 0;
        for(int i= 0 ;i < n;i++) {
			curr_sum =0;
			for(int j =i; j<n ;j++) {
				curr_sum+=arr[j];
				if(curr_sum > result[2]) {
					result[2] = curr_sum;
					result[0] = i+1;
					result[1] = j+1;
				}
			}
		}
        return result;
    }

	public static void main (String[] args) {
	    Scanner sc = new Scanner(System.in);
	    //System.out.print("Enter the number of integers(n): ");  
        int n=sc.nextInt();  
        int[] arr = new int[n];
        //System.out.println("Enter the n integers separated by space: ");  
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
	    final long startTime = System.nanoTime(); //delete
		int[] res = MaxSubArray(arr,n);
		for (int i: res) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        final long duration = System.nanoTime()-startTime; //delete
        System.out.println("Time in nano seconds: "+duration); //delete
	}
}