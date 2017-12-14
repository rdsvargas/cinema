package util;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Roger
 */
public class Console {

    /**
     *
     * @param out
     * @return
     */
    public static String scanString(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextLine());
    }

    /**
     *
     * @param out
     * @return
     */
    public static int scanInt(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        int result;
        String teclado;
        try {
            teclado = scanner.nextLine();
            result = Integer.parseInt(teclado);
        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }

    /**
     *
     * @param out
     * @return
     */
    public static LocalTime scanTime(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        LocalTime result;
        String teclado;
        try {
            teclado = scanner.nextLine();
            result = DateUtil.stringToTime(teclado);
        } catch (DateTimeParseException e) {
            result = null;
        }
        return result;
    }

    /**
     *
     * @param out
     * @return
     */
    public static double scanDouble(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextDouble());
    }

    /**
     *
     * @param out
     * @return
     */
    public static float scanFloat(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextFloat());
    }

    /**
     *
     * @param out
     * @return
     */
    public static boolean scanBoolean(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextBoolean());
    }

    /**
     *
     * @param out
     * @return
     */
    public static char scanChar(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.next().charAt(0));
    }

    public static String formatString(Object out, int size) {
        String result = "";
        if (out instanceof Integer) {
            result = String.valueOf(out);
        } else {
            result = (String) out;
        }

        if (result.length() > size) {
            result = result.substring(0, size);
        } else {
            while (result.length() < size) {
                result = result + " ";
            }
        }
        return result;
    }
}
