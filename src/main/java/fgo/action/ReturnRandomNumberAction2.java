package fgo.action;

@Deprecated
public class ReturnRandomNumberAction2 {
    public static int ReturnRandomNumber() {
        int min = (int)Math.ceil(1.0D);
        int max = (int)Math.floor(100.0D);

        return (int) (Math.floor(Math.random() * (max - min)) + min);
    }
}