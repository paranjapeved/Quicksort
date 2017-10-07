package Assignment2;
/*
Quicksort
Author: Ved Paranjape
ID: 801016930
 */

import java.io.*;
import java.util.*;
import java.util.Random;
import java.nio.file.*;
import java.nio.charset.*;

public class Qsort {
    private double[] array;                                     //array to be sorted
    private int size = 0;                                       //size of array

    public void readFile(String InputFileName)                  //function for reading input array from file
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(InputFileName));
            String line = new String();
            ArrayList<Double> UnsortedStr = new ArrayList<>();
            line = br.readLine();
            while (line != null)                                //logic for reading line by line from input file
            {
                String[] temp = line.split(";");
                for (String str1 : temp) {
                    UnsortedStr.add(Double.parseDouble(str1));
                    this.size += 1;
                }
                line = br.readLine();
            }
            //this.size = UnsortedStr.size();                   //initializing size of array
            //System.out.println(this.size);
            System.out.println(UnsortedStr);
            array = new double[size];                           //initializing object of array of size
            for (int i = 0; i < UnsortedStr.size(); i++)        //logic for storing into array
            {
                array[i] = UnsortedStr.get(i);
            }
            this.size = array.length;
            System.out.println(this.size);
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void display()                               //function for displaying array
    {
        for(int i = 0;i<size;i++)
        {
            System.out.println(array[i]);
        }
    }

    public void quickSort(int p,int q)                  //Quicksort function defintion
    {
        if(p<q)
        {
            int r = partition(p,q);
            quickSort(p,r-1);
            quickSort(r+1,q);
        }
    }

    public int partition(int p,int q)                   //partition function definition
    {
        double piv = array[q];
        int i = p-1;
        double temp = 0;
        for (int j = p;j<=q-1;j++)
        {
            if(array[j] <= piv)
            {
                i = i + 1;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;

            }
        }
        temp = array[i+1];
        array[i+1] = array[q];
        array[q] = temp;
        return i+1;
    }

    public void write(String filename,long diff)                                 //function for writing sorted array to output file
    {
        String ToWrite = new String();
        try {
            FileWriter fw = new FileWriter("answer.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out  = new PrintWriter(bw);
            out.write("\n");
            out.write("Sorting result:\n"+filename+" sorted:"+ Arrays.toString(array) +"\n");
            out.write("Performance Analysis: "+filename+"\n");
            out.write("Sorting Time: "+diff+"ms\n");
            out.write("Size "+filename+": "+this.size+"\n\n");
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {

        for(int i=0;i<args.length;i++)
        {
            Qsort obj = new Qsort();                                        //Object creation
            obj.readFile(args[i]);                                          //readFile function call
            long starttime = System.currentTimeMillis();
            obj.quickSort(0,obj.size-1);                             //quicksort function call
            long endtime = System.currentTimeMillis();                      //time analysis
            obj.write(args[i],endtime - starttime);                    //writing results to output file
            obj = null;
        }

    }
}

/*

				Readme file for Qsort.java
				
Programming Language: Java
Platform: Mac OS X
Compiler: javac
IDE: IntelliJ Idea
Filename: Qsort.java
Class: Qsort
Class variables:
	1. Double array for storing and sorting
	2. Size of array
Functions:
	1. readFile(): For reading array from input file
	2. quickSort(): For sorting array using quick sort
	3. partition(): For finding and returning the correct position for the pivot
	4. display(): Display array
	5. write(): Write sorted array to output file

Algorithm Working:
	The quick sort algorithm consists of two functions, quicksort() and partition(). The partition function is called from within the quick sort function. The partition function selects the last element of the array as pivot and places the pivot in its correct position in the array so that all the elements greater than pivot are to its right and which are smaller than pivot are to the left and returns the new position of the pivot. The quicksort function makes recursive calls by calling itself twice, first time for the array from first index position to pivot-1 and second time for pivot+1 to last index position.

Program Scope:
	The program takes input from multiple files at a time for any number of input files. The file may contain single or multiple lines. Code executes correctly irrespective of that. As mentioned in the instructions, the program takes the name of input files as argument and performs sorting operation and writes the sorted array to “answer.txt”. The program can take any number of semicolons separated input files; it writes the sorted array and the performance analysis to the output file and separates the output according to input files. The program is equipped to handle validation checks such as incorrect program arguments, file not found exception, IOException, etc. The program works for both integers and decimal numbers. If the size of array element exceeds the maximum permitted range for data type double, the program might fail.

Program Data Structure Summary:
	A simple double array has been used for storing and sorting the array. A temporary Arraylist has been used to read each element of the array from the input file line by line which requires dynamic array data structure and ArrayList serves the purpose. After the end of the input file all the elements are copied from the Arraylist to the double array.
  array.



 */