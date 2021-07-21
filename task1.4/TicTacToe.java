package task4;

import task3.Main;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 1. Уйти от проверок в лоб, переделать на циклы
 *      Создаём массив счётчиков вариантов подеды, заполняем пройдясь по полю
 *      Перебираем счётчики, смотрим кто достиг значения длины поля
 * 2. Увеличить поле до 5
 * 3. добавить ИИ, чтобы он мог блокировать ходы игрока.
 *      Тоже самое, что и при проверке на победу, пусть ИИ ставит 0 туда,
 *      где в массиве счётчиков (длина поля-1) и нет его ходов в своём массиве побед
 */

public class TicTacToe {

    private static char CELL_EMPTY = '-';


    public static void start(int field_length) {
        char[][] field = new char[field_length][field_length];

        for (int i = 0; i < field_length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = CELL_EMPTY;
            }
        }

        drawField(field);

        do {
            doMove(field);
            drawField(field);
            if (isWin(field,'X')) {
                System.out.println("Congratulation!!! You are winner!");
                break;
            }
            if (isDraw(field)) {
                System.out.println("This is draw!!!!");
                break;
            }

            boolean is_win = doBot(field);
            drawField(field);
            if (is_win) {
                System.out.println("Sorry!!! You are looser!");
                break;
            }
            if (isDraw(field)) {
                System.out.println("This is draw!!!!");
                break;
            }

        } while (true);
    }

    private static boolean isDraw(char[][] field) {
        for (int v = 0; v < field.length; v++) {
            for (int h = 0; h < field.length; h++) {
                if (isEmptyCell(field, v, h)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] calcWin(char[][] field, char sign) {
        int[] moovies = {};

        for (int i = 0; i < field.length * 2 + 2; i++) {
            moovies = Arrays.copyOf(moovies, moovies.length+1);
            moovies[i] = 0;
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (i == j && field[i][j] == sign) {
                    moovies[moovies.length-2] ++ ;
                }
                if (i + j + 1 == field.length && field[i][j] == sign) {
                    moovies[moovies.length-1] ++ ;
                }
                if (field[i][j] == sign) {
                    moovies[i] ++ ;
                    moovies[j + field.length] ++ ;
                }
            }
        }
        return moovies;
    }

    private static boolean isWin(char[][] field, char sign) {

        int[] moovies = calcWin(field, sign);

        for (int i = 0; i < moovies.length; i++) {
            if (moovies[i] >= field.length) {
                return true;
            }
        }

        return false;
    }

    static boolean doBot(char[][] field) {
        int[] human_moovies = calcWin(field, 'X');
        int[] bot_moovies = calcWin(field, '0');

        int danger_line = -1;
        for (int i = 0; i < human_moovies.length; i++) {
            if (
                    human_moovies[i] >= field.length-1
                    && bot_moovies[i] < 1
            ) {
                danger_line = i;
                break;
            }
        }

        int v = -1, h = -1;
        if (danger_line > 0) {
            if (danger_line < field.length) {
                // Hor
                v = danger_line;
                for (int i = 0; i < field.length; i++) {
                    if (isEmptyCell(field, v, i)) {
                        h = i;
                    }
                }
            }
            else if (danger_line >= field.length && danger_line < human_moovies.length - 2) {
                h = danger_line - field.length;
                for (int i = 0; i < field.length; i++) {
                    if (isEmptyCell(field, i, h)) {
                        v = i;
                    }
                }
            }
            else if (danger_line == human_moovies.length - 2) {
                // Diag 1
                for (int i = 0; i < field.length; i++) {
                    if (isEmptyCell(field, i, i)) {
                        v = h = i;
                    }
                }
            }
            else {
                // Diag 2
                for (int i = 0; i < field.length; i++) {
                    for (int j = 0; j < field.length; j++) {
                        if (i + j + 1 == field.length && isEmptyCell(field, i, j)) {
                            v = i;
                            h = j;
                        }
                    }
                }
            }
        }

        if (danger_line < 0 || v < 0 || h < 0) {
            Random random = new Random();
            do {
                v = random.nextInt(field.length);
                h = random.nextInt(field.length);
            } while (isNotEmptyCell(field, v, h));
        }

        field[v][h] = '0';

        return isWin(field,'0');
    }

    static boolean doMove(char[][] field) {
        int v, h;
        do {
            v = getCoordinate(field, 'V');
            h = getCoordinate(field, 'H');
        } while (isNotEmptyCell (field, v, h));

        field[v][h] = 'X';

        return false;
    }


    private static void drawField(char[][] field) {
        for (int i=0; i < field.length; i++) {
            for (int j=0; j < field.length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isEmptyCell(char[][] field, int v, int h) {
        return field[v][h] == CELL_EMPTY;
    }

    private static boolean isNotEmptyCell(char[][] field, int v, int h) {
        return !isEmptyCell(field, v, h);
    }

    private static int getCoordinate(char[][] field, char coord_name) {
        Scanner scanner = new Scanner(System.in);
        int coord;
        do {
            System.out.printf("Enter %s-coordinate [1..%s]%n",coord_name, field.length);
            coord = scanner.nextInt() - 1;
        } while (coord >= field.length || coord < 0);
        return coord;
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
            if (i != 0 && i % 20 == 0) {
                System.out.println("...");
            }
        }
        System.out.println();
    }

}
