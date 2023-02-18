package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        int numberOfRows = getNumberOfRows();
        int numberOfSeats = getNumberOfSeats();
        int purchasedTickets = 0;
        int currentIncome = 0;

        String[][] room = mapOfCinema(numberOfRows+1, numberOfSeats+1);

        loop:while (true) {
            int chosenOperation = getOperationNumber();

            switch (chosenOperation) {
                case 0:
                    break loop;
                case 1:
                    showCurrentRoom(room);
                    break;
                case 2:
                    boolean repeat = true;
                    while (repeat){
                        int rowNumber = getRowNumber();
                        int seatNumber = getSeatNumber();

                        if (rowNumber > numberOfRows || seatNumber > numberOfSeats) {
                            System.out.printf("%nWrong input!%n");
                        }else if (room[rowNumber][seatNumber].equals("B")) {
                            System.out.printf("%nThat ticket has already been purchased!%n");
                        }else {
                            int price = displayTicketPrice(numberOfSeats, numberOfRows, rowNumber);

                            System.out.printf("%nTicket price: $%d%n", price);
                            roomUpdate(room, seatNumber, rowNumber);
                            purchasedTickets = ticketsCount(purchasedTickets);
                            currentIncome += price;
                            repeat = false;
                        }
                    }
                    break;
                case 3:
                    System.out.printf("%nNumber of purchased tickets: %d", purchasedTickets);
                    System.out.printf("%nPercentage: %.2f%%", percentageOfTickets(purchasedTickets, numberOfRows, numberOfSeats));
                    System.out.printf("%nCurrent income: $%d", currentIncome);
                    System.out.printf("%nTotal income: $%d%n", totalIncome(numberOfRows, numberOfSeats));
            }
        }
    }
    public static int totalIncome (int rows, int seats) {
        if (rows * seats <= 60) {
            return (rows * seats * 10);
        }else {
            return (((rows / 2) * seats * 10)) + ((rows - (rows / 2)) * seats * 8);
        }
    }
    public static float percentageOfTickets(float tickets, int rows, int seats) {
        return (tickets / (rows * seats)) * 100;
    }
    public static int ticketsCount(int purchased) {
        purchased++;
        return purchased;
    }
    public static void roomUpdate(String[][] seating, int seat, int row) {
        seating[row][seat] = "B";
    }
    public static void showCurrentRoom(String[][] mapOfRoom) {
        System.out.printf("%nCinema:%n");
        for (int row = 0; row < mapOfRoom.length; row++) {
            for (int seat = 0; seat < mapOfRoom[row].length; seat++) {
                System.out.printf("%s ",mapOfRoom[row][seat]);
            }
            System.out.println();
        }
    }
    public static String[][] mapOfCinema(int rows, int seats) {
        String[][] room = new String[rows][seats];

        for (int row = 0; row < rows; row++) {
            for (int seat = 0; seat < seats; seat++) {
                if (row == 0) {
                    if (seat == 0) {
                        room[row][seat] = " ";
                    }else {
                        room[row][seat] = seat + "";
                    }
                }else {
                    if (seat == 0) {
                        room[row][seat] = row + "";
                    }else {
                        room[row][seat] = "S";
                    }
                }
            }
        }
        return room;
    }
    public static int getNumberOfRows() {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter the number of rows:%n> ");
        return scanner.nextInt();
    }
    public static int getNumberOfSeats() {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter the number of seats in each row:%n> ");
        return scanner.nextInt();
    }
    public static int getRowNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("%nEnter a row number:%n> ");
        return scanner.nextInt();
    }
    public static int getSeatNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter a seat number in that row:%n> ");
        return scanner.nextInt();
    }
    public static int displayTicketPrice(int seats, int rows, int row) {
        if (seats * rows <= 60) {
            return 10;
        }else {
            if (row <= rows / 2){
                return 10;
            }else {
                return 8;
            }
        }
    }
    public static int getOperationNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("%n1. Show the seats");
        System.out.printf("%n2. Buy a ticket");
        System.out.printf("%n3. Statistics");
        System.out.printf("%n0. Exit");
        System.out.printf("%n> ");
        return scanner.nextInt();
    }
}