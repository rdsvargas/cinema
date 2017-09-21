package util;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Console {

    public static String scanString(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextLine());
    }

    public static int scanInt(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        int result;
        String teclado;
        try{
            teclado = scanner.nextLine();
            result = Integer.parseInt(teclado);
        } catch (NumberFormatException e){
            result = -1;
        }
        return result;
    }

    public static LocalTime scanTime(Object out){
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        LocalTime result;
        String teclado;
        try{
        teclado = scanner.nextLine();
        result = DateUtil.stringToTime(teclado);
        } catch (DateTimeParseException e){
            result = null;
        }
        return result;
    }
    
    public static double scanDouble(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextDouble());
    }

    public static float scanFloat(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextFloat());
    }

    public static boolean scanBoolean(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextBoolean());
    }

    public static char scanChar(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.next().charAt(0));
    }

}
