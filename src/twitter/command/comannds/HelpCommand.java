package twitter.command.comannds;

import twitter.command.CommandExistential;

public class HelpCommand implements CommandExistential {
    @Override
    public boolean commandActive() {
        System.out.println("1.\texit — Выход из программы.\n" +
                "2.\tregister — Регистрация нового пользователя в системе\n" +
                "3.\tlogin — Вход в систему под определенным пользователем.\n" +
                "4.\tlogout — Выход из авторизации определенного пользователя.\n" +
                "5.\tinfo — Вывод информации об авторизированном пользователе.\n" +
                "6.\tinfo_by_login — Вывод информации о пользователе по его логину.\n" +
                "7.\t info_all — Вывод информации о всех пользователях системы.\n" +
                "8.\tadd_post — Добавить новую публикацию от текущего пользователя.\n" +
                "9.\tmy_posts -  Вывод всех публикаций текущего пользователя.\n" +
                "10.\tall_posts — Вывод всех публикаций в системе.\n" +
                "11.\tposts_by_tag — Вывод публикаций по тэгу.\n" +
                "12.\tposts_by_login — Вывод публикаций по логину пользователя.\n" +
                "13.\tposts_by_user_type — Вывод публикаций по типу пользователя.\n"
        );
        return false;
    }
}
