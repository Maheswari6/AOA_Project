import java.util.Scanner;
public class Task4
{
    public static int[] maxSumRectangle(int[][] arr,int m,int n){
        int []res = new int[5];
        res[4] = Integer.MIN_VALUE;
        int maxSubmatrix = 0;
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
		int[] res = maxSumRectangle(arr,m,n);
		for(int i:res)
		    System.out.print(i+" ");
		System.out.println();
	}
}