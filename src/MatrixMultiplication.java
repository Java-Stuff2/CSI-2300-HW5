import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class MatrixMultiplication {
    public static void main(String[] args) throws Exception {
        String file;
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please input file name:");
        file = scnr.next();

        if (isNumberthree(file)) {
            Integer.parseInt(file);
        }
        System.out.println("Please input file name:");
        scnr.next();
        int numb;
        System.out.println("Input dimension");
        numb = scnr.nextInt();
        int[][] matrix1 = new int[numb][numb];
        scnr.close();
        // try {
        // BufferedReader reader = new BufferedReader(new FileReader(file));
        // String line;
        // while ((line = reader.readLine()) != null) {
        // System.out.println(line);
        // }
        // reader.close();
        // } catch (IOException ex) {
        // System.out.println("Error reading file'" + file + "'");
        // }
        // String str = "mat";
        // if(file == "matrix1.txt"){
        // java.lang.String numbers;
        // Scanner scnnr = new Scanner(System.in);
        // System.out.println("Input numbers, please");
        // numbers = scnnr.next();
        // int number = Integer.parseInt(str);
        // System.out.println(numbers);
        // scnnr.close();
        // }

        // int rows = 3, cols = 4;
        // int[][] matrix1 = new int[rows][cols];
        String filepath = "matrix1.txt";
        matrix1 = readMatrixFromFile(file);

        if (isNumberthree(file)) {
            Integer.parseInt(filepath);
        }
        initializeMatrix(matrix1);
        writeMatrixToFile(matrix1, filepath);
        assert areArraysEqual(matrix1, readMatrixFromFile(filepath));
        int[][] matrix2 = new int[numb][numb];
        initializeMatrix(matrix2);
        writeMatrixToFile(matrix2, "matrix2.txt");
        int[][] result = multiplyMatrix(matrix1, matrix2);
        writeMatrixToFile(result, "matrix3.txt");
        printMatrix(matrix1);
        printMatrix(matrix2);
        printMatrix(result);
    }

    public static boolean areArraysEqual(int[][] array1, int[][] array2) {
        if (array1.length != array2.length || array1[0].length != array2[0].length) {
            return false;
        }

        int rows = array1.length;
        int cols = array1[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (array1[row][col] != array2[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void initializeMatrix(int[][] matrix) throws IOException {
        Random random = new Random();
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Initialize the matrix with random values
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = random.nextInt(5);
            }
        }
        // Store the matrix in a file
        FileWriter f = new FileWriter(new File("matrix1.txt"));
        for (int j = 0; j < matrix.length; j++) {
            if (j > 0) {
                f.write("\n");
            } /* w ww. j a v a 2s. c o m */
            for (int i = 0; i < matrix[0].length; i++) {
                if (i > 0) {
                    f.write(",");
                }
                f.write(String.valueOf(matrix[j][i]));
            }
        }
        System.out.println("Saved matrix sucessfully.");

        f.flush();
        f.close();
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

    public static boolean isNumberthree(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
