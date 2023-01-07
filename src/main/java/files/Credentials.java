package files;

public class Credentials {
    private static String key="8c64879dd15c805ae6995b52925e9b6f";
    private static String token="ATTA5930db51adc291c09d36cb07e286ba90474784110793799ac5506ff660a4d74f2DD25B0F";

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        Credentials.key = key;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Credentials.token = token;
    }
}
