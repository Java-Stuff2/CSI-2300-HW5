import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class IamOverMatrices {
    public static void main(String[] args) throws Exception {
        String file;
        String file2;
        Scanner scnr = new Scanner(System.in);
        System.out.println("Are you entering a file or row length and column length?");
        int filorint;
        System.out.println("Type 0 for file. Type 1 for integer");
        filorint = scnr.nextInt();
        if (filorint == 0) {
            System.out.print("Please input file name: ");
            file = scnr.next();
            System.out.print("Please input second file name:");
            file2 = scnr.next();

            String i = "matrix1.txt";
            String j = "matrix2.txt";
            while (!i.equals(file) && !j.equals(file2)) {
                System.out.println("Invalid Text file. Try again!");
                System.out.println("Hint: type: matrix1.txt or matrix2.txt");
                System.out.print("Please input file name: ");
                file = scnr.next();
                System.out.println("Please input second file name: ");

                file2 = scnr.next();

            }
            int[][] matrix1 = readMatrixFromFile(file);
            int[][] matrix2 = readMatrixFromFile(file2);
            boolean arr;
            arr = areArraysEqual(matrix1, matrix2);
            if (arr == true) {

                int[][] result = multiplyMatrix(matrix1, matrix2);
                writeMatrixToFile(result, "matrix3.txt");
                System.out.println("\n");
                System.out.println("Matrix1.txt");
                printMatrix(matrix1);
                System.out.println("Matrix2.txt");
                printMatrix(matrix2);
                System.out.println("Matrix3.txt");
                printMatrix(result);
            } else {
                System.out.println("The dimensions you entered are invaild. Please edit yourfiles and try again.");
                while (!i.equals(file) && !j.equals(file2)) {
                    System.out.println("Invalid Text file. Try again!");
                    System.out.println("Hint: type: matrix1.txt or matrix2.txt");
                    System.out.print("Please input file name: ");
                    file = scnr.next();
                    System.out.print("Please input second file name:");
                    file2 = scnr.next();

                }
                matrix1 = readMatrixFromFile(file);
                matrix2 = readMatrixFromFile(file2);
                printMatrix(matrix1);
                printMatrix(matrix2);
                arr = areArraysEqual(matrix1, matrix2);
                if (arr == true) {
                    int[][] result = multiplyMatrix(matrix1, matrix2);
                    writeMatrixToFile(result, "matrix3.txt");
                    printMatrix(matrix1);
                    printMatrix(matrix2);
                    printMatrix(result);
                }
            }

        } else if (filorint == 1) {
            int rows;
            System.out.print("Please input row length:");
            rows = scnr.nextInt();
            int cols;
            System.out.print("Please input column length:");
            cols = scnr.nextInt();
            int[][] matrix1 = new int[rows][cols];
            String filepath = "matrix1.txt";
            initializeMatrix(matrix1);
            writeMatrixToFile(matrix1, filepath);
            assert areArraysEqual(matrix1, readMatrixFromFile(filepath));
            int[][] matrix2 = new int[cols][rows];
            initializeMatrix(matrix2);
            writeMatrixToFile(matrix2, "matrix2.txt");
            int[][] result = multiplyMatrix(matrix1, matrix2);
            writeMatrixToFile(result, "matrix3.txt");
            System.out.println("Matrix1.txt");
            printMatrix(matrix1);
            printMatrix(matrix2);
            printMatrix(result); // for requirement 3

        }

        scnr.close();

    }

    public static boolean areArraysEqual(int[][] array1, int[][] array2) {
        if (array1[0].length != array2.length) {
            return false;
        }

        return true;
    }

    public static void initializeMatrix(int[][] matrix) {
        Random random = new Random();
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Initialize the matrix with random values
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = random.nextInt(5);
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Print the matrix values
        for (int row = 0; row < rows; row++) {
            System.out.print("[");
            for (int col = 0; col < cols; col++) {
                System.out.print(matrix[row][col]);
                if (col < cols - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            System.out.println("\n");
        }
    }

    public static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        // Check if the matrices can be multiplied
        if (cols1 != rows2) {
            System.out.println("The matrices cannot be multiplied!");
            return null;
        }

        // Create the result matrix
        int[][] result = new int[rows1][cols2];

        // Multiply the matrices
        for (int row = 0; row < rows1; row++) {
            for (int col = 0; col < cols2; col++) {
                for (int i = 0; i < cols1; i++) {
                    result[row][col] += matrix1[row][i] * matrix2[i][col];
                }
            }
        }

        return result;
    }

    public static int[][] readMatrixFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int rows = 0;
        int cols = 0;

        // Determine the number of rows and columns in the matrix
        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(" ");
            cols = elements.length;
            rows++;
        }

        // Reset the reader to read from the beginning of the file
        reader.close();
        reader = new BufferedReader(new FileReader(filePath));

        // Create the matrix with the determined size
        int[][] matrix = new int[rows][cols];

        // Read the matrix values from the file
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(" ");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = Integer.parseInt(elements[col]);
            }
            row++;
        }

        reader.close();
        return matrix;
    }

    public static void writeMatrixToFile(int[][] matrix, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        // Write the matrix values to the file
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                writer.write(matrix[row][col] + " ");
            }
            writer.newLine();
        }

        writer.close();
    }
}
