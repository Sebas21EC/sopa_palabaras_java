
import java.util.Random;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author sebas
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int tamanio_palabra = 5;
        int numero_palabras = 18;

        String[] palabras = Palabras_aleatoreas(numero_palabras, tamanio_palabra);
        imprimir_arreglo(palabras);
        int[] factores = Factores_multiplicativos(numero_palabras);
        System.out.println("\nSe generará una matriz de:" + factores[0] + "x" + factores[1] + "\n");
        String[][] matriz_palabras = Crear_matriz_palabras(factores[0], factores[1]);
        Llenar_matriz_palabras(palabras, matriz_palabras);
        imprimir_matriz(matriz_palabras);

        System.out.print("Ingresar patron de reemplazo: ");
        String patron = (input.next()).toUpperCase();
        System.out.println("");
        Buscar_patron(patron, matriz_palabras);
        System.out.println("");
        imprimir_matriz(matriz_palabras);

        long inicio = System.nanoTime();
        Ordenar_matriz_palabra(matriz_palabras);
        long fin = System.nanoTime();
        long tiempo_total = fin - inicio;
        System.out.printf("El tiempo de ejecución del método es de %.9f microsegundos.%n", (tiempo_total / 1e9) * 1000000.0);
        System.out.println("\nMatriz Ordenado: ");
        imprimir_matriz(matriz_palabras);

    }

    /**
     * Metodo que imprime un arreglo en pantalla
     *
     * @param arreglo referencia del arreglo
     */
    public static void imprimir_arreglo(String[] arreglo) {

        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(" " + arreglo[i]);
        }
        System.out.println("");

    }

    /**
     * Metodo que imprime una matriz en pantalla
     *
     * @param matriz referencia de la matriz
     */
    public static void imprimir_matriz(String[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print("\t" + matriz[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");

    }

    /**
     * Metodo que genera palabras aleatoreas y las asigna en un arreglo
     *
     * @param numero_palabras la cantidad de palabras a generar
     * @param tamanio_palabra el tamanio de la palabra, osea los caracteres
     * @return retorna arreglo con palabras aleatoreas
     */
    public static String[] Palabras_aleatoreas(int numero_palabras, int tamanio_palabra) {
        Random numero_random = new Random();
        String palabra = "";
        String[] arreglo_palabra = new String[numero_palabras];
        for (int i = 0; i < numero_palabras; i++) {
            for (int j = 0; j < tamanio_palabra; j++) {
                palabra += (char) (numero_random.nextInt(25) + 65);
            }
            arreglo_palabra[i] = palabra;
            palabra = "";
        }

        return arreglo_palabra;
    }

    /**
     * Metodo para encontrar multiplos para generar la matriz
     *
     * @param numero el valor total
     * @return retorna arreglo con los dos multiplicactios
     */
    public static int[] Factores_multiplicativos(int numero) {
        int[] arreglo = new int[2];

        if (numero % 2 != 0) {
            arreglo[0] = numero;
            arreglo[1] = 1;
            return arreglo;
        }
        if (numero == 4) {
            arreglo[0] = 2;
            arreglo[1] = 2;
            return arreglo;
        }

        for (int i = 3; i < numero; i++) {
            for (int j = 2; j < numero; j++) {
                if (i * j == numero) {
                    arreglo[0] = i;
                    arreglo[1] = j;
                    return arreglo;
                }
            }

        }
        return arreglo;

    }

    /**
     * Metodo para una matriz de string
     *
     * @param filas nuemro de filas
     * @param columnas numero de columnas
     * @return retorna una referencia de matriz vacia
     */
    public static String[][] Crear_matriz_palabras(int filas, int columnas) {
        return new String[filas][columnas];

    }

    /**
     * Metodo para llenar la matriz con palabaras de un arreglo
     *
     * @param arreglo_palabras referencia de arreglo que contiene las palabras
     * @param matriz referencia de una matriz en donde se va llenar
     * @return retorna la refencia de matriz
     */
    public static String[][] Llenar_matriz_palabras(String[] arreglo_palabras, String[][] matriz) {
        int aumentador = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = arreglo_palabras[aumentador];
                aumentador++;
            }
        }

        return matriz;
    }

    /**
     * metodo para buscar el patron en cada alor de la matriz y remmplazarlo
     *
     * @param patron cadena de estrin a buscar
     * @param matriz referencia de matriz en doned se va buscar y reemplazar
     * @return retorna la referencia de la matriz
     */
    public static String[][] Buscar_patron(String patron, String[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j].contains(patron)) {
                    matriz[i][j] = patron;
                    System.out.println("Se reemplazó en la fila: " + i + ", columna: " + j);
                }
            }
        }
        return matriz;
    }

    /**
     * Metodo para ordenar los valores de la matris en orden ascendente
     *
     * @param matriz referencia de la matriz
     * @return retonra la refenrencia de la matriz
     */
    public static String[][] Ordenar_matriz_palabra(String[][] matriz) {

        int numero_palabras = matriz.length * matriz[0].length;
        String[] arreglo_palabras = new String[numero_palabras];
        String auxiliar;
        int aumentador = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                arreglo_palabras[aumentador] = matriz[i][j];
                aumentador++;
            }
        }

        for (int i = 0; i < arreglo_palabras.length; i++) {
            for (int j = 1; j < arreglo_palabras.length - i; j++) {
                if (arreglo_palabras[j].compareTo(arreglo_palabras[j - 1]) < 0) {
                    auxiliar = arreglo_palabras[j];
                    arreglo_palabras[j] = arreglo_palabras[j - 1];
                    arreglo_palabras[j - 1] = auxiliar;
                }
            }
        }
        aumentador = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = arreglo_palabras[aumentador];
                aumentador++;
            }
        }

        return matriz;
    }

}
