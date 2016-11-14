package main;

/**
 * Created by andrey on 12.11.16.
 */
public class View {
    public static void printScreen(char[][] table) {
        for (char[] aTable : table) {
            for (char anATable : aTable) {
                System.out.print(anATable);
            }
            System.out.println();
        }
    }
}
