import java.util.Random;

public class RandomGenerator {

    public static String getRandomEmail() {
        String emailMask = "wpt@wriketask.qaa";
        return getRandomWord()+emailMask;
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
        int r;
        switch (questionNumber){
            case 1:{
                 r = random.nextInt(2);
                    answer = firstQuestion[r];

                break;
            }
            case 2:{
                 r = random.nextInt(5);
                answer = secondQuestion[r];
                break;
            }
            case 3:{
                 r = random.nextInt(1);
                answer = thirdQuestion[r];
                break;
            }
        }
        return answer;
    }

    public static String getRandomWord(){
        Random random = new Random();
        char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        StringBuilder randomWord = new StringBuilder();
        for (int j = 0; j < word.length; j++) {
            randomWord.append((char) ('a' + random.nextInt(26)));
        }
        return randomWord.toString();
    }
}
