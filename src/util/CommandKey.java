package util;

import java.time.LocalDate;
import java.util.Scanner;

public class CommandKey {
    private static Scanner scan = new Scanner(System.in);
    private static String line;
    private static Integer number;

    public static void nextLine() {
        line = scan.next();
    }

    public static String getLine() {
        return line;
    }

    public static void nextNumber() {
        if (scan.hasNextInt()) {
            number = scan.nextInt();
        } else {
            System.err.println("Введенно некоректное значение. ВВЕДИТЕ ЧИСЛО!!!!");
            scan.next();
            nextNumber();
        }
    }

    public static Integer getNumber() {
        return number;
    }

    public static boolean isCorrectInputeDate() {
        System.out.println("Введите дату в формате гггг-мм-дд");
        nextLine();

        if (!getLine().contains("-")) return false;

        char[] data = getLine().toCharArray();
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 48 || data[i] > 57) {
                if (data[i] != 45) {
                    return false;
                }
            }
        }
        return inCorrectDate();
    }

    public static boolean inCorrectDate() {
        String[] date = line.split("-");
        int years = Integer.valueOf(date[0]);
        int mouth = Integer.valueOf(date[1]);
        int day = Integer.valueOf(date[2]);

        if (!(years < LocalDate.now().getYear() && years > LocalDate.now().getYear() - 120)) return false;
        if (day < 0 || day > 31) return false;
        if (mouth < 0 || mouth > 12) return false;

        if (mouth == 4 || mouth == 6 || mouth == 9 || mouth == 11) {
            if (day <= 30) return true;
        }
        if (years % 4 == 0) {
            if (mouth == 2 && day <= 29) return true;
        } else if (mouth == 2 && day <= 28) return true;
        return false;
    }
    public static LocalDate nextDate () {
            boolean next = !isCorrectInputeDate();
            while (next) {
                System.err.println("Некоректная дата попробуйте снова ");
                next = !isCorrectInputeDate();
            }
            return LocalDate.parse(getLine());
    }

}

