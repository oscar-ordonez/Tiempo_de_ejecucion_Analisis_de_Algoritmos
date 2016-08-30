/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiempo.de.ejecucion;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Oscar Ordoñez
 */
public class TiempoDeEjecucion {

    public static int [] arreglo;
    
    public static void main(String[] args) {
        int resp = 0, resp2 = 0;
        long timer = 0, timer2 = 0;
        Scanner sc =  new Scanner(System.in);
        do{
            System.out.println("********** MENU **********");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Insertion Sort");
            System.out.println("3. Selection Sort");
            System.out.println("4. Merge Sort");
            System.out.println("5. Heap Sort");
            System.out.println("6. Quick Sort");
            System.out.println("7. Radix Sort");
            System.out.print("Respuesta: ");
            resp = sc.nextInt();
            if (resp < 1 || resp > 7) {
                System.out.println("\nERROR, ingrese numero del 1 al 7\n");
            }
        }while(resp < 1 || resp > 7);
        do{
            System.out.println("********** MENU **********");
            System.out.println("1. 10");
            System.out.println("2. 100");
            System.out.println("3. 1000");
            System.out.println("4. 10000");
            System.out.println("5. 100000");
            System.out.println("6. 1000000");
            System.out.print("Respuesta: ");
            resp2 = sc.nextInt();
            if (resp2 < 1 || resp2 > 6) {
                System.out.println("\nERROR, ingrese numero del 1 al 6\n");
            }
        }while(resp2 < 1 || resp2 > 6);
        for (int i = 0; i < 10; i++) {
            switch (resp2) {
                case 1:  
                    llenarArreglo(10);
                    break;
                case 2:  
                    llenarArreglo(100);
                    break;
                case 3:  
                    llenarArreglo(1000);
                    break;
                case 4:  
                    llenarArreglo(10000);
                    break;
                case 5:  
                    llenarArreglo(100000);
                    break;
                case 6:  
                    llenarArreglo(1000000);
                    break;
            }
            switch (resp) {
                case 1:  
                    timer = System.nanoTime();
                    BubbleSort(arreglo);
                    timer2 = System.nanoTime();
                    break;
                case 2: 
                    timer = System.nanoTime();
                    InsertionSort(arreglo);
                    timer2 = System.nanoTime();
                    break;
                case 3: 
                    timer = System.nanoTime();
                    SelectionSort(arreglo);
                    timer2 = System.nanoTime();
                    break;
                case 4:  
                    timer = System.nanoTime();
                    MergeSort(arreglo);
                    timer2 = System.nanoTime();
                    break;
                case 5:  
                    timer = System.nanoTime();
                    HeapSort(arreglo);
                    timer2 = System.nanoTime();
                    break;
                case 6:  
                    timer = System.nanoTime();
                    QuickSort(arreglo, 0, arreglo.length-1);
                    timer2 = System.nanoTime();
                    break;
                case 7:  
                    timer = System.nanoTime();
                    RadixSort(arreglo);
                    timer2 = System.nanoTime();
                    break;
            }
            System.out.println(timer2 - timer);
        }
        //print();
    }
    
    public static void llenarArreglo(int n){
        arreglo = new int[n];
        for (int i = 0; i < n; i++) {
            arreglo[i] = (int) ((Math.random() * 9999999) + 1);
        }
    }
    
    public static void print(){
        System.out.print(arreglo[0]);
        for (int i = 1; i < arreglo.length; i++) {
            System.out.print(", " + arreglo[i]);
        }
        System.out.println();
    }
    
/******************************************************************************/
    
    /*http://www.algolist.net/Algorithms/Sorting/Bubble_sort*/
    public static void BubbleSort(int[] arr) {
        boolean swapped = true;
        int j = 0;
        int tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < arr.length - j; i++) {                                       
                if (arr[i] > arr[i + 1]) {                          
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                    swapped = true;
                }
            }                
        }
    }
    
/******************************************************************************/
    
    /*http://courses.cs.washington.edu/courses/cse373/01wi/slides/Measurement/sld010.htm*/
    public static void InsertionSort(int[] a){
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int j;
            for (j = i-1; j >= 0 && temp < a[j]; j--) {
                a[j+1] = a[j];
            }
            a[j+1] = temp;
        }
    }
    
/******************************************************************************/
    
    /*http://stackoverflow.com/questions/8362640/java-selection-sort-algorithm*/
    public static void SelectionSort(int[] input) {
        int minimumValue = Integer.MAX_VALUE;
        for (int i = 0; i < input.length; ++i) {
            for (int j = i; j < input.length; ++j) {
                if (input[j] <= minimumValue) {
                    minimumValue = input[j];
                    input[j] = input[i];
                    input[i] = minimumValue;
                }
            }
            minimumValue = Integer.MAX_VALUE;
        }
    }
    
/******************************************************************************/
    
    /*http://stackoverflow.com/questions/13727030/mergesort-in-java*/
    static void MergeSort(int[] A) {
        if (A.length > 1) {
            int q = A.length/2;

            int[] leftArray = Arrays.copyOfRange(A, 0, q);
            int[] rightArray = Arrays.copyOfRange(A,q,A.length);

            MergeSort(leftArray);
            MergeSort(rightArray);

            merge(A,leftArray,rightArray);
        }
    }
    static void merge(int[] a, int[] l, int[] r) {
        int totElem = l.length + r.length;
        //int[] a = new int[totElem];
        int i,li,ri;
        i = li = ri = 0;
        while ( i < totElem) {
            if ((li < l.length) && (ri<r.length)) {
                if (l[li] < r[ri]) {
                    a[i] = l[li];
                    i++;
                    li++;
                }
                else {
                    a[i] = r[ri];
                    i++;
                    ri++;
                }
            }
            else {
                if (li >= l.length) {
                    while (ri < r.length) {
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= r.length) {
                    while (li < l.length) {
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }
        //return a;
    }
    
/******************************************************************************/
    
    /*http://www.sanfoundry.com/java-program-implement-heap-sort/*/
    private static int N;
    public static void HeapSort(int arr[]){       
        heapify(arr);        
        for (int i = N; i > 0; i--){
            swap(arr,0, i);
            N = N-1;
            maxheap(arr, 0);
        }
    }     
    /* Function to build a heap */   
    public static void heapify(int arr[]){
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(arr, i);        
    }
    /* Function to swap largest element in heap */        
    public static void maxheap(int arr[], int i){ 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i])
            max = left;
        if (right <= N && arr[right] > arr[max])        
            max = right;
 
        if (max != i){
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }    
    /* Function to swap two numbers in an array */
    public static void swap(int arr[], int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp; 
    }    
    
/******************************************************************************/
    
    /*http://www.algolist.net/Algorithms/Sorting/Quicksort*/
    public static int partition(int arr[], int left, int right){
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }
    public static void QuickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            QuickSort(arr, left, index - 1);
        if (index < right)
            QuickSort(arr, index, right);
    }
    
/******************************************************************************/
    
    /*https://rosettacode.org/wiki/Sorting_algorithms/Radix_sort*/
    public static void RadixSort(int[] old) {
        for (int shift = Integer.SIZE - 1; shift > -1; shift--) {
            int[] tmp = new int[old.length];
            int j = 0;
            for (int i = 0; i < old.length; i++) {
                boolean move = old[i] << shift >= 0;
                if (shift == 0 ? !move : move) {
                    tmp[j] = old[i];
                    j++;
                } else {
                    old[i - j] = old[i];
                }
            }
            for (int i = j; i < tmp.length; i++) {
                tmp[i] = old[i - j];
            }
            old = tmp;
        }
    }

/******************************************************************************/
    
}
