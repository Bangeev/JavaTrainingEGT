public class Main {

    public static final int ZERO_IN_ASCII_DEC = 48;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] myDigits = new int[10];
        int firstNumber = scanner.nextInt();
        String inputNum = scanner.next();
        boolean isTheEndOfSequence = false;

        for (int i = 1; i <= myDigits.length - 1; i++) {
            myDigits[i] = scanner.nextInt();
        }
        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i < firstNumber; i++) {
            int numInAsciiDec = inputNum.charAt(i) - ZERO_IN_ASCII_DEC;
            int numFromArray = myDigits[numInAsciiDec];

            if (numInAsciiDec < numFromArray || (isTheEndOfSequence && numInAsciiDec == numFromArray)) {
                isTheEndOfSequence = true;
                stringBuilder.append(numFromArray);
            } else {
                if (isTheEndOfSequence) {
                    for (int j = i; j < firstNumber; j++) {
                        stringBuilder.append(inputNum.charAt(j) - ZERO_IN_ASCII_DEC);
                    }
                    break;
                }
                stringBuilder.append(numInAsciiDec);
            }
        }
        System.out.println(stringBuilder);

    }
}
