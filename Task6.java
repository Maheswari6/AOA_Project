import java.util.Scanner;
public class Task6
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
    public static int[] MaxSubMatrix(int[][] matrix,int m,int n) {
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
                    prefix_sum[i]+=matrix[i][right];
                curr_res = MaxSubArray(prefix_sum,m);
                
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
	    int m,n;
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		int[][] arr = new int[m][n];
		for(int i=0; i<m ;i++) {
			for(int j=0;j<n;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		int[] res = MaxSubMatrix(arr,m,n);
		for(int i:res)
		    System.out.print(i+" ");
		System.out.println();
	}
}