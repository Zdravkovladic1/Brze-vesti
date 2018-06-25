package framework;

public class Helper {

    public static String getRandomText() {

        int random = (int) (Math.random() * 350 + 1);
        String randomText = "category" + random;
        return randomText;

//        jednostavnije pisanje istog kod-a
//        returm "md" + (int) (Math.random() * 500 + 1);
    }

    public static String getRandomUrl() {
        return "";
    }

    public static int getRandomIntiger(int range) {

        return (int) (Math.random() * range);

    }

//    pubic static int getRandom
//
//    static int getRandomIntiger(){
//        
//    }
//    
}
