package mini.twter;

import mini.twter.util.CommandUtil;

public class Main {
    public static void main(String[] args) {
        System.out.println("Старт программы");
            while (CommandUtil.commandAction()){}
        System.out.println("Завершение программы");
    }
}
