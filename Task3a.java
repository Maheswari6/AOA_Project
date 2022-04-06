import java.util.Scanner;

public class Task3a
{
	public static int[] MaxSubArray(int[] arr,int n) {
        int[] result = new int[3]; //result contains start_index, end_index, max_sum 
        result[2] = Integer.MIN_VALUE;// Set the maximum sum seen till now to - INFINITY
        
        int s=0; //s denotes the start of current subarray
        int[] dp = new int[n];
        dp[0] = arr[0];
        result[0] = result[1] = 1; // At index 0, the max sum is provided by arr[0] only
        result[2] = arr[0];
        
        for (int i = 1; i < n; i++)
        {
            dp[i] = arr[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            if (result[2] < dp[i])
            {
                result[2] = dp[i];
                result[0] = s+1;
                result[1] = i+1;
            }
            if (dp[i] < 0)
                 s = i + 1;
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