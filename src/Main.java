import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] display = new String[3][3];
        int count = 0;
        int countX = 0;
        int countO = 0;
        Boolean Xwin = false;
        Boolean Owin = false;
        Boolean draw = false;
        Boolean EmptySpots = false;
        int numX = 0;
        int numO = 0;
        Boolean noString = true;
        Boolean correctSyntax = true;
        Boolean conditions = false;
        int[] correctUserInput = new int[1];
        Boolean gameOver = false;
        int binarySwitch = 1;
        Boolean binaryBoolean = false;




        //Puts input in to String display[][] array
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                display[j][k] = " ";
            }
        }
        count = 0;

        //DisplayOutput
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int k = 0; k < 3; k++) {
                System.out.print(display[i][k]);
                System.out.print(" ");
            }
            if (i < 2) {
                System.out.print("|" + "\n");
            } else {
                System.out.print("|");
            }
        }
        System.out.println("");
        System.out.println("---------");

        //UserInput for X and O
        while (!gameOver) {
            binarySwitch++;
            if (binarySwitch % 2 == 0) {
                binaryBoolean = false;
            } else {
                binaryBoolean = true;
            }
            String userSystemIn = scanner.nextLine();
            String[] userSystemSplit = userSystemIn.split(" ");

            //Checks if user input has strings
            for (int i = 0; i < userSystemSplit.length; i++) {
                if (!userSystemSplit[i].matches("\\d+")) {
                    noString = false;
                }
            }
            //checks if input follow correct syntax
            if (userSystemSplit.length > 2) {
                correctSyntax = false;
            }
            if (noString && correctSyntax) {
                int[] userInput = new int[userSystemSplit.length];
                for (int i = 0; i < userInput.length; i++) {
                    userInput[i] = Integer.parseInt(userSystemSplit[i]);
                }
                //checks if input is not more then 3 or lower then 0
                if (userInput[0] == 0 || userInput[0] > 3 || userInput[1] == 0 || userInput[1] > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    binarySwitch--;
                } else if (!display[userInput[0] - 1][userInput[1] - 1].equals(" ")) {
                    System.out.println("This cell is occupied! Choose another one!");
                    binarySwitch--;
                } else {
                    conditions = true;
                    correctUserInput = userInput;
                    if (!binaryBoolean) {
                        display[userInput[0] - 1][userInput[1] - 1] = "X";
                    } else {
                        display[userInput[0] - 1][userInput[1] - 1] = "O";
                    }
                    //displayer
                    System.out.println("---------");
                    for (int i = 0; i < 3; i++) {
                        System.out.print("| ");
                        for (int k = 0; k < 3; k++) {
                            System.out.print(display[i][k]);
                            System.out.print(" ");
                        }
                        if (i < 2) {
                            System.out.print("|" + "\n");
                        } else {
                            System.out.print("|");
                        }
                    }
                    System.out.println("");
                    System.out.println("---------");

                }
            } else {
                System.out.println("You should enter numbers!");
                binarySwitch--;
            }



            //ComparisonPart Winners
            for (int i = 0; i < 3; i++) {
                if (Xwin == true || Owin == true) {
                    gameOver = true;
                }
                //Checks sides
                countO = 0;
                countX = 0;
                for (int j = 0; j < 3; j++) {
                    if (display[i][j].equals("X")) {
                        countX++;
                    } else if (display[i][j].equals("O")) {
                        countO++;
                    }
                    if (countX == 3) {
                        Xwin = true;
                    } else if (countO == 3) {
                        Owin = true;
                    }
                }
                countO = 0;
                countX = 0;
                //Checks up and down
            }
            for (int i = 0; i < 3; i++) {
                countO = 0;
                countX = 0;
                for (int k = 0; k < 3; k++) {
                    if (display[k][i].equals("X")) {
                        countX++;
                    } else if (display[k][i].equals("O")) {
                        countO++;
                    }
                    if (countX == 3) {
                        Xwin = true;
                    } else if (countO == 3) {
                        Owin = true;
                    }
                }
            }

            //Checks cross pattern
            countO = 0;
            countX = 0;

            if (display[0][0].equals("X") && display[1][1].equals("X") && display[2][2].equals("X")) {
                Xwin = true;
            } else if (display[0][2].equals("X") && display[1][1].equals("X") && display[2][0].equals("X")) {
                Xwin = true;
            }

            if (display[0][0].equals("O") && display[1][1].equals("O") && display[2][2].equals("O")) {
                Owin = true;
            } else if (display[0][2].equals("O") && display[1][1].equals("O") && display[2][0].equals("O")) {
                Owin = true;
            }
            int emptyInt = 0;
            //Checks for empty spots and if its draw
            if (Xwin != true || Owin != true) {
                for (int i = 0; i < 3; i++) {
                    for (int k = 0; k < 3; k++) {
                        if (display[i][k].equals(" ")) {
                            EmptySpots = true;
                            emptyInt++;
                        }
                    }
                }
            }
            if (emptyInt == 0) {
                draw = true;
            }
            //Winner statements
            if (Xwin == true && Owin == true || numO - numX > 1 || numX - numO > 1) {
                System.out.println("Impossible");
                gameOver = true;
            } else if (Owin == true) {
                System.out.println("O wins");
                gameOver = true;
            } else if (Xwin == true) {
                System.out.println("X wins");
                gameOver = true;
            } else if (draw) {
                System.out.println("Draw");
                gameOver = true;
            }
        }

    }
}