import java.util.Scanner;

public class Task5
{
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
		final long startTime = System.nanoTime();
		int[] res = task5(arr,m,n);
		for(int i:res)
		    System.out.print(i+" ");
		System.out.println();
		final long duration = System.nanoTime()-startTime;
		System.out.println("Duration: "+duration);
	}
}