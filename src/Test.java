
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Test {

    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please input file name or integer:");
        String input = scnr.next();

        if (isNumber(input)) {
            int number = Integer.parseInt(input);
            int[][] matrix1 = generateRandomMatrix(number);
            int[][] matrix2 = generateRandomMatrix(number);
            writeMatrixToFile(matrix1, "1matrix1.txt");
            writeMatrixToFile(matrix2, "2matrix2.txt");
            int[][] result = multiplyMatrix(matrix1, matrix2);
            writeMatrixToFile(result, "3matrix3.txt");
            printMatrix(result);
        } else {
            String file1 = input;
            String file2 = scnr.next();
            int[][] matrix1 = readMatrixFromFile(file1);
            int[][] matrix2 = readMatrixFromFile(file2);
            int[][] result = multiplyMatrix(matrix1, matrix2);
            writeMatrixToFile(result, "3matrix3.txt");
            printMatrix(result);
        }
        scnr.close();
    }

    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static int[][] readMatrixFromFile(String fileName) throws IOException {
        Scanner fileScanner = new Scanner(new FileReader(fileName));
        int rows = fileScanner.nextInt();
        int cols = fileScanner.nextInt();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = fileScanner.nextInt();
            }
        }

        fileScanner.close();
        return matrix;
    }

    public static void writeMatrixToFile(int[][] matrix, String fileName) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));
        int rows = matrix.length;
        int cols = matrix[0].length;

        fileWriter.write(rows + " " + cols + "\n");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                fileWriter.write(matrix[i][j] + " ");
            }
            fileWriter.write("\n");
        }

        fileWriter.close();
    }

    public static int[][] generateRandomMatrix(int rows) {
        int[][] matrix = new int[rows][rows];
        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                matrix[i][j] = rand.nextInt(10);
            }
        }

        return matrix;
    }

    public static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        if (cols1 != rows2) {
            throw new IllegalArgumentException(
                    "The number of columns in the first matrix must be equal to the number of rows in the second matrix.");
        }

        int[][] result = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}