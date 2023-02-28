import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.Random;

public class RedisJava {
    private static final String USERS = "All users";
    private static final String MESSAGE = " оплатил платную услугу";
    private static final String SYMBOLS = "======================================";
    private static int num1 = new Random().nextInt(10) + 1;

    public static void main(String[] args)
    {
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println ("Успешное подключение");
        System.out.println ("Служба работает: " + jedis.ping() + "\n");

        for (int j = 1; j < 21; j++) jedis.zadd(USERS, j, "Пользователь " + j);
        List<String> userList = jedis.zrangeByScore(USERS, "0", "+inf");

        while (true)
        {
            for (int i = 0; i < userList.size(); i++)
            {
                if (i == num1) {
                    int num = new Random().nextInt(20);
                    System.out.println(SYMBOLS + "\n" + userList.get(num) + MESSAGE + "\n" + SYMBOLS);
                    System.out.println(userList.get(num));
                    if (num1 + 10 > 19) num1 -= 10;
                    else num1 += 10;
                }
                System.out.println(userList.get(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}