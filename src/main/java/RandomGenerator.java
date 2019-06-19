import java.util.Random;

public class RandomGenerator {
    private static final String emailMask = "wpt@wriketask.qaa";

    public static String getRandomEmail() {
        Random random = new Random();
        char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        StringBuilder randomEmail = new StringBuilder();
        for (int j = 0; j < word.length; j++) {
            randomEmail.append((char) ('a' + random.nextInt(26)));
        }

        return randomEmail+emailMask;
    }

    public static String getRandomAnswer(int questionNumber){
        Random random = new Random();
        String answer = new String();
        String[] firstQuestion = new String[]{
                Xpaths.VERY_INTERESTED_PATH,
                Xpaths.JUST_LOOKING_PATH
        };
        String[] secondQuestion = new String[]{
                Xpaths.ONE_FIVE_PATH,
                Xpaths.SIX_FIFTEEN_PATH,
                Xpaths.SIXTEEN_TWENTYFIVE_PATH,
                Xpaths.TWENTYSIX_FIFTY_PATH,
                Xpaths.FIFTY_PLUS_PATH
        };
        String[] thirdQuestion = new String[]{
          Xpaths.YES_PATH,
          Xpaths.NO_PATH,
          Xpaths.OTHER_PATH
        };
        switch (questionNumber){
            case 1:{
                    answer = firstQuestion[random.nextInt(2)];
                break;
            }
            case 2:{
                answer = secondQuestion[random.nextInt(5)];
                break;
            }
            case 3:{
                answer = thirdQuestion[random.nextInt(3)];
                break;
            }
        }
        return answer;
    }
}
