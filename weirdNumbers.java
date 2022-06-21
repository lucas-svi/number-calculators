public class weirdNumbers {
    private static List<Integer> weirdNums = new ArrayList<>();

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        final Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Please enter a range: ");
            calc(new Scanner(System.in).nextInt());

        } catch (InputMismatchException e) {
            System.out.println("Invalid number!");
            init();
        }
    }

    public static void calc(int range) {
        for (int i = 1; i <= range; i++) {
            int sum = 0;
            List<Integer> divisors = getDivisors(i);
            for (Integer divisor : divisors) {
                sum += divisor;
            }
            if (!isPrime(divisors)) {
                if (sum >= i && !check(i)) {
                    weirdNums.add(i);
                }
            }
        }
        System.out.print("Here are your weird numbers: " + (weirdNums.size() != 0 ? weirdNums : "none :("));
    }

    public static List<Integer> getDivisors(int num) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= Math.ceil(num / 2d); i++) {
            if (num % i == 0) {
                divisors.add(i);
            }
        }
        return divisors;
    }

    /* modified a bit of this code I found online. thanks, stranger!*/

    public static boolean check(int n) {
        List<Integer> v = getDivisors(n);
        Collections.sort(v);
        int r = v.size();
        boolean[][] subset = new boolean[r + 1][n + 1];
        for (int i = 0; i <= r; i++)
            subset[i][0] = true;
        for (int i = 1; i <= n; i++)
            subset[0][i] = false;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= n; j++) {
                subset[i][j] = j < v.get(i - 1) ? subset[i - 1][j] : subset[i - 1][j] || subset[i - 1][j - v.get(i - 1)];
            }
        }
        return subset[r][n];
    }


    public static boolean isPrime(List<Integer> divisors) {
        return divisors.size() == 1;
    }

}
