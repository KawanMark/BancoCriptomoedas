//package utils;
//
//
//
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.util.Locale;
//
//public class Util {
//    public static double parseDouble(String numero) {
//        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
//        char decimalSeparator = symbols.getDecimalSeparator();
//
//        if (decimalSeparator == ',') {
//            numero = numero.replace(",", ".");
//        }
//        else {
//            numero = numero.replace(".", ",");
//        }
//
//        return Double.parseDouble(numero);
//    }
//
//    public static String formatDouble(double numero) {
//        DecimalFormat df = new DecimalFormat();
//        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.getDefault()));
//        return df.format(numero);
//    }
//}


package utils;

public class Util {

    public static double parseDouble(String str) throws NumberFormatException {
        str = str.replace(",", ".");
        return Double.parseDouble(str);
    }

    public static String formatDouble(double value) {
        return String.format("%.8f", value).replace(",", ".");
    }

    public static String normalizeNumberString(String str) {
        return str.replace(",", ".");
    }
}
