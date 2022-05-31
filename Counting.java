public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Прочитаме входните данни.
        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());

        //Принтирам
        System.out.println(findWinner(n, m));

    }

    //Методът който ни изпълнява алгоритъма.
    private static int findWinner(int n, int m) {
        //При невалиден брой хора да върнем 0.
        if (n <= 0) {
            return 0;
        }

        //Създавам си една променлива, която ще държи резултат за хората които са последно преброени.
        //(Последният отброен човек (с номер m) излиза от кръга.)
        int result = 0;

        //Минавам през всички и записвам всеки, който е последно преброен в result.
        //(Повтаряме стъпка 2 (продължавайки да броим от следващият участник))
        for (int i = 1; i <= n; i++) {
            result = (result + m) % i;
        }

        //Връщам резултат като му домавям + 1 понеже е последният останал човек,
        //а ние сме започнали да броим от 1 до n.
        return result + 1;
    }

}
