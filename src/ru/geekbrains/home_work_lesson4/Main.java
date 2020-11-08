package ru.geekbrains.home_work_lesson4;

import java.util.Scanner;

import java.util.Random;


public class Main {

    static char[][] map;
    static final int SIZE = 3;

    static final char DOT_EMPTY = '.';
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';


    public static void main(String[] args) {

        // Инициализация поля
        initMap();
        // Вывод поля
        printMap();

        while (true) {
            //Ход человека
            humanTurn();
            //куда сходил
            printMap();
            //проверка на победу
            if (checkWin(DOT_X) || checkDiagonal(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            //проверка на ничью
            isMapFull();

            //Ход ИИ
            aiTurn();
            //куда сходил
            printMap();
            //проверка на победу
            if (checkWin(DOT_O) || checkDiagonal(DOT_O)) {
                System.out.println("Победил ИИ");
                break;
            }
            //проверка на ничью
            isMapFull();
        }
    }

    static void initMap() {
        map = new char[SIZE][SIZE];
        for (int row = 0; row < map.length; row++) {
            for (int colums = 0; colums < map.length; colums++) {
                map[row][colums] = DOT_EMPTY;
            }
        }
    }

    static void printMap() {
        for (int i = 0; i <= map.length; i++) {
            System.out.print(i + " ");

        }
        System.out.println();
        for (int row = 0; row < map.length; row++) {
            System.out.print(row + 1 + " ");

            for (int colm = 0; colm < map.length; colm++) {
                System.out.print(map[row][colm] + " ");
            }
            System.out.println();
        }
    }

    static void humanTurn() {

        Scanner scanner = new Scanner(System.in);
        int x;
        int y;

        do {
            System.out.println("Введите кординаты, в формате x y");

            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;

    }

    static boolean isCellValid(int x, int y) {

        if (x < 0 || x >= map.length || y < 0 || y >= map.length) {
            return false;
        }
        if (map[y][x] == DOT_EMPTY) {
            return true;
        }
        return false;
    }

    static void aiTurn() {

        Random random = new Random();
        int x;
        int y;

        do {
            System.out.println("Введите кординаты, в формате x y");

            x = random.nextInt(map.length);
            y = random.nextInt(map.length);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
        System.out.println("Компьютер сходил в точку " + (x + 1) + " " + (y + 1));
    }


    static boolean checkWin(char dot) {
            boolean rows, colums;
            for (int colum = 0; colum < map.length; colum++) {
                rows = true;
                colums = true;
                for (int row = 0; row < map.length; row++) {
                    colums = colums && (map[colum][row] == dot);
                    rows = rows && (map[row][colum] == dot);

                }
                if (colums || rows) return true;
            }
            return false;
        }




    static boolean isMapFull() {
        for (int row = 0; row <= map.length; row++) {
            for (int colum = 0; colum <= map.length; colum++) {
                if (map[row][colum] == DOT_EMPTY) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagonal(char dot) {
        boolean leftdiagonal = true;
        boolean rightdiagonal = true;
        for (int i = 0; i < map.length; i++)
            {
            leftdiagonal = leftdiagonal && (map[i][i] == dot);
            rightdiagonal = rightdiagonal && (map[map.length - i - 1][i] == dot);
            }
            if (leftdiagonal || rightdiagonal) return true;


            return false;
        }


    }




