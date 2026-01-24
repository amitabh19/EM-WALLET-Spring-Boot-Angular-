import java.io.*;
import java.util.*;

public class StockMarketSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        for (int t_i = 0; t_i < T; t_i++) {
            int N = Integer.parseInt(br.readLine().trim());
            String[] arr_A = br.readLine().split(" ");
            int[] A = new int[N];
            for (int i_A = 0; i_A < arr_A.length; i_A++) {
                A[i_A] = Integer.parseInt(arr_A[i_A]);
            }
            int K = Integer.parseInt(br.readLine().trim());
            long out_ = solve(N, A, K);
            System.out.println(out_);
        }
        wr.close();
        br.close();
    }

    /**
     * Optimal O(N*K) DP Solution
     * 
     * Key insight: When partitioning into K subarrays:
     * - Odd-indexed subarrays (1st, 3rd, ...) = buying price (subtracted)
     * - Even-indexed subarrays (2nd, 4th, ...) = selling price (added)
     * - Profit = selling_price - buying_price
     * 
     * DP State: dp[i][j] = max profit using first i elements with j partitions
     * 
     * For j-th partition covering [p, i):
     * - If j is odd: contributes -(prefix[i] - prefix[p])
     * - If j is even: contributes +(prefix[i] - prefix[p])
     * 
     * Optimization: Maintain running maximum to achieve O(N*K) instead of O(N²*K)
     */
    static long solve(int N, int[] A, int K) {
        // Compute prefix sums
        long[] prefix = new long[N + 1];
        for (int i = 0; i < N; i++) {
            prefix[i + 1] = prefix[i] + A[i];
        }
        
        final long NEG_INF = Long.MIN_VALUE / 2;
        long[][] dp = new long[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], NEG_INF);
        }
        dp[0][0] = 0;
        
        for (int j = 1; j <= K; j++) {
            long maxPrev = NEG_INF;
            if (j % 2 == 1) {
                // Odd partition: contributes negatively
                // dp[i][j] = -prefix[i] + max_{p<i}(dp[p][j-1] + prefix[p])
                for (int i = 1; i <= N; i++) {
                    maxPrev = Math.max(maxPrev, dp[i - 1][j - 1] + prefix[i - 1]);
                    dp[i][j] = -prefix[i] + maxPrev;
                }
            } else {
                // Even partition: contributes positively
                // dp[i][j] = prefix[i] + max_{p<i}(dp[p][j-1] - prefix[p])
                for (int i = 1; i <= N; i++) {
                    maxPrev = Math.max(maxPrev, dp[i - 1][j - 1] - prefix[i - 1]);
                    dp[i][j] = prefix[i] + maxPrev;
                }
            }
        }
        
        return dp[N][K];
    }
}
