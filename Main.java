import java.util.Scanner;
public class Main
{
    public static int[] task1(int[] arr,int n) {
        int[] result = new int[3]; //result contains start_index, end_index, max_sum 
        result[0] = result[1] = -1; // Set the start_index and end_index to -1
        result[2] = Integer.MIN_VALUE;  // Set the maximum sum seen till now to - INFINITY
        for(int i = 0; i < n; i++) //Here i is a pointer for starting index of subarray
        {
            for(int j = i; j < n; j++) // Here j is a pointer for ending index of subarray
            {
                int currSum = 0;
                for(int k = i; k <= j; k++) // Compute the sum for the subarray arr[i] to arr[j]
                {
                    currSum += arr[k];
                }
                if (currSum>result[2]) //If the sum of subarray from i to j is maximum, we update result
                {
                    result[0] = i+1;
                    result[1] = j+1;
                    result[2] = currSum;
                }
            }
        }
        return result;
    }
    //We can observe that we need not calculate the sum each time from i to j.
    //So, instead of initializing the sum to 0 each time, we just increment it.
    public static int[] task2(int[] arr,int n) {
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
    public static int[] task3a(int[] arr,int n) {
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
    public static int[] task3b(int[] arr,int n) 
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
    public static int[] task4(int[][] arr,int m,int n){
        int []res = new int[5];
        res[4] = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {   //Start row
		    for (int j = 0; j < n; j++) {   //Start column
			    for (int k = i; k < m; k++) {   //End row
				    for (int l = j; l < n; l++) {   //End column
    					int sumSubmatrix = 0;
					    // Iterate the submatrix row-wise and calculate its sum
					    for (int r = i; r<= k; r++) {
						    for (int c = j; c <= l; c++) {
							    sumSubmatrix += arr[r][c];
						    }
					    }
					   // Update the maximum sum
					    if(sumSubmatrix>res[4])
					    {
					        res[4] = sumSubmatrix;
					        res[0] = i+1;
					        res[1] = j+1;
					        res[2] = k+1;
					        res[3] = l+1;
					    }
				    }
			    }
		    }
	    }
	    return res;
    }
    public static int[] task5(int[][] arr,int m, int n){
        int [][]prefix_sum = new int [m+1][n+1];
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                if (i == 0 || j == 0) {
                    prefix_sum[i][j] = 0;
            }
            else 
                prefix_sum[i][j] = prefix_sum[i-1][j] + prefix_sum[i][j-1] - prefix_sum[i-1][j-1] + arr[i-1][j-1];
            }
        }
        
        int[] res = new int[5];
        res[4] = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {   //Start row
		    for (int j = 0; j < n; j++) {   //Start column
			    for (int k = i; k < m; k++) {   //End row
				    for (int l = j; l < n; l++) {   //End column
				        int sumSubmatrix= prefix_sum[k+1][l+1] - prefix_sum[k+1][j] - prefix_sum[i][l+1] + prefix_sum[i][j];
				        if(sumSubmatrix>res[4])
					    {
					        res[4] = sumSubmatrix;
					        res[0] = i+1;
					        res[1] = j+1;
					        res[2] = k+1;
					        res[3] = l+1;
					    }
				    }
			    }
		    }
        }
        return res;
    }
	
    public static int[] task6(int[][] arr,int m,int n) {
        int left,right;
        int[] prefix_sum = new int[m];
        int[] curr_res = new int[3];
        int[] res = new int[5];
        res[4] = Integer.MIN_VALUE;
        
        for(left = 0;left<n;left++)
        {
            for(int i=0;i<m;i++)
                prefix_sum[i] = 0;
                
            for(right = left;right<n;right++)
            {
                for(int i=0;i<m;i++)
                    prefix_sum[i]+=arr[i][right];
                curr_res = task3a(prefix_sum,m);
                
                if(curr_res[2]>res[4])
                {
                    res[4] = curr_res[2];
                    res[0] = curr_res[0];
                    res[1] = left+1;
                    res[2] = curr_res[1];
                    res[3] = right+1;
                }
            }
        }
        return res;
        
    }
	public static void main(String[] args) {
	    if(args.length!=1)
	    {
	        System.out.println("Invalid no.of command line arguments");
	        System.out.println("Only 1 command line argument allowed!!");
	        return;
	    }
	    String prgm = args[0];
	    Scanner sc = new Scanner(System.in);
	    if(prgm.equals("1") || prgm.equals("2") || prgm.equals("3a") || prgm.equals("3b"))
	    {
	        //System.out.print("Enter the number of integers(n): ");  
            int n=sc.nextInt();  
            int[] arr = new int[n];
            //System.out.println("Enter the n integers separated by space: ");  
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int []res = new int[3];
            switch (prgm){
                case "1":
                    res = task1(arr,n);
                    break;
                case "2":
                    res = task2(arr,n);
                    break;
                case "3a":
                    res = task3a(arr,n);
                    break;
                case "3b":
                    res = task3b(arr,n);
                    break;
            }
            for (int i: res) 
                System.out.print(i+" ");
            System.out.println();
	    }
	    else if (prgm.equals("4") || prgm.equals("5") || prgm.equals("6"))
	    {
	        int m,n;
		    m = sc.nextInt();
    		n = sc.nextInt();
    		int[][] arr = new int[m][n];
    		for(int i=0; i<m ;i++) {
    			for(int j=0;j<n;j++) {
    				arr[i][j] = sc.nextInt();
    			}
    		}
    		int []res = new int[5];
            switch (prgm){
                case "4":
                    res = task4(arr,m,n);
                    break;
                case "5":
                    res = task5(arr,m,n);
                    break;
                case "6":
                    res = task6(arr,m,n);
                    break;
            }
    		for(int i:res)
    		    System.out.print(i+" ");
    		System.out.println();
	    }
	    else
	        System.out.println("Incorrect Input\nValid inputs are 1,2,3a,3b,4,5,6");
	}
}
