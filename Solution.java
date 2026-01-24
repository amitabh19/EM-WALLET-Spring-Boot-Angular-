// Searching Google Pay saved Account - Smallest Optimal Solution

static String[][] solve(int N, String[][] A, String D, String V){
    int i=D.contains("first")?1:D.contains("last")?2:D.contains("mobile")?3:D.contains("branch")?4:0;
    return java.util.Arrays.stream(A).filter(a->a[i].equals(V)).sorted((x,y)->x[0].compareTo(y[0])).toArray(String[][]::new);
}
