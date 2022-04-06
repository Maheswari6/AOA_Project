import java.util.Scanner;

public class Task3b
{
    public static int[] MaxSubArray(int[] arr,int n) 
	{
	    int[] res = new int[3];
	    res[0] = res[1] = -1;
	    res[2] = Integer.MIN_VALUE;
	    helper(arr,n,res);
	    return res;
	}
	static int currStart = 0;
	public static int helper(int[] arr, int n, int[] res)
	{
	    if (n == 1)
            return arr[0];   
        int currMaxSum = helper(arr, n - 1,res);
		if (currMaxSum < 0)    // same as: currMaxSum + nums[n - 1] < nums[n - 1]
		{
		    currMaxSum = arr[n - 1];
		    currStart = n-1;
		}
		else 
		    currMaxSum += arr[n - 1];
		
		if (res[2] < currMaxSum)
		{
			res[2] = currMaxSum;
			res[0] = currStart+1;
			res[1] = n;
		}
		return currMaxSum;
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