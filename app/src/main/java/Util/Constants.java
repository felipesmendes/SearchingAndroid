package Util;

public class Constants {

    public static final String FIREBASE_URL = "https://torrid-inferno-78.firebaseio.com/";

    public static String mapUrl(String serial) {
        //Como não tivemos acesso aos rastreadores reais, estamos apenas marcando o mapa
        //com pontos estáticos embeddados em WebView

        switch (serial) {
            case ("33499223342"):
                return "<iframe src=\"https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d12599.428560119635!2d-43.99407232747284!3d-19.90182227718706!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0000000000000000%3A0x291ea28fe52406ec!2sAeroporto+Belo+Horizonte+-+Carlos+Prates!5e0!3m2!1sen!2sbr!4v1466373027532\" width=\"400\" height=\"350\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>";
            case ("64998472"):
                return "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3752.5640278791625!2d-43.98119614962135!3d-19.85839164114899!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xa69055aec6c8cb%3A0x167abfa32a405734!2sCapela+Curial+de+S%C3%A3o+Francisco+de+Assis!5e0!3m2!1sen!2sbr!4v1466373890636\" width=\"400\" height=\"350\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>";
            case ("3945637"):
                return "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3749.805872715172!2d-43.94693784961897!3d-19.974664644791897!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xa69818dc26fefb%3A0x8d174716ed2afa87!2sBH+Shopping!5e0!3m2!1sen!2sbr!4v1466373984705\" width=\"400\" height=\"350\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>";
            case ("6996"):
                return "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3750.7151149405945!2d-43.96849624961978!3d-19.936406443591036!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xa6970bbf3f5be1%3A0x76fc4c9a2db9d473!2sFaculdade+COTEMIG!5e0!3m2!1sen!2sbr!4v1466374242515\" width=\"400\" height=\"350\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>";
            default:
                return "<iframe src=\"https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d42909.37931115597!2d-43.968274103229696!3d-19.879961693249776!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0000000000000000%3A0xbb1391cea62811dd!2sFederal+University+of+Minas+Gerais!5e0!3m2!1sen!2sbr!4v1466374329966\" width=\"400\" height=\"350\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>";
        }
    }
}
