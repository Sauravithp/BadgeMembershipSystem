package miu.edu.badgesystem.util;

import java.util.Random;

public class RandomNumberUtil {

    private static Random random = new Random();

    public static String createBadgeNumber(){
        return String.valueOf(random.nextInt(999999));
    }

    public static String createTransactionNumber(){
        return String.valueOf(random.nextInt(999999));
    }

}
