import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
          Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the chess board size");
          int size = 0;
        boolean validEntry2 = false;
        while (!validEntry2) {

            try {
                size = scanner.nextInt();
                validEntry2 = true;
            } catch (InputMismatchException s) {
                System.out.println("unvalid entry");
                System.out.println("enter a valid number  ");
                scanner.next();

            }
        }
        // TODO
        System.out.println("Enter the start column as char");
          char column = scanner.next().charAt(0);
        System.out.println("Enter the start row as number");
          int row =0;
        boolean validEntry3 = false;
        while (!validEntry3) {

            try {
                row = scanner.nextInt();
                validEntry3 = true;
            } catch (InputMismatchException s) {
                System.out.println("unvalid entry");
                System.out.println("enter a valid number  ");
                scanner.next();

            }
        }
        //TODO
        System.out.println("Enter variant S for Simple C for Classic");
          char variant = scanner.next().charAt(0);
        String v = Character.toString(variant);
          while ((v.equals("S") ||v.equals("C")) ? false:true ){
              System.out.println("bitch u entered a false value try again mother fucker @@");
              v=scanner.next();
             // scanner.nextLine();
          }

        System.out.println("Enter 1 to get determinate number of solutions else all solutions");
        int solutionType = 0;
        boolean validEntry5 = false;
        while (!validEntry5) {

            try {
                solutionType = scanner.nextInt();
                validEntry5 = true;
            } catch (InputMismatchException s) {
                System.out.println("unvalid entry");
                System.out.println("enter a valid number  ");
                scanner.next();

            }
        }

        int soluationAmount = 0;
        if(solutionType == 1){
        System.out.println("Enter the number of solution");
           soluationAmount = scanner.nextInt();
            boolean validEntry6 = false;
            while (!validEntry6) {

                try {
                    soluationAmount = scanner.nextInt();
                    validEntry6 = true;
                } catch (InputMismatchException s) {
                    System.out.println("unvalid entry");
                    System.out.println("enter a valid number  ");
                    scanner.next();

                }
            }}




        Springerproblem s = new Springerproblem(size, soluationAmount,solutionType);
        s.start(column, row, Version.valueOf(v));
    }
}
