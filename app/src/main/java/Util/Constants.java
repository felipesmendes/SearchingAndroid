package Util;

public class Constants {

    public static final String FIREBASE_URL = "https://torrid-inferno-78.firebaseio.com/";

    public static String mapUrl(String serial) {
        //Como não tivemos acesso aos rastreadores reais, estamos apenas marcando o mapa
        //com pontos estáticos embeddados em WebView

        switch (serial) {
            case ("33499223342"):
                return "-19.901822,-43.99407232";
            case ("64998472"):
                return "-19.858391,-43.98119614";
            case ("3945637"):
                return "-19.974664,-43.94693784";
            case ("6996"):
                return "-19.9364064,-43.96849624";
            default:
                return "-19.8799616,-43.96827410";
        }
    }
}
