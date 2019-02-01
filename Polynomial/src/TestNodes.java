import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.ArrayList;

//  Virginia Pollock
//  CIS 2353
//  Fall 2018
//  Project 2

public class TestNodes {
    public static void main(String[] args) {
    ArrayList<Polynomial> myArrayList = new ArrayList<>();
  
  try {
   Scanner scanner = new Scanner(new File("polynomials.txt"));
   while(scanner.hasNext()){
    myArrayList.add(new Polynomial(scanner.nextLine()));
  }
   scanner.close();
  }
  catch (FileNotFoundException e) {
         System.out.println("Cannot read from file.");
    }
  
  int input = 0;
  int input1;
  Scanner scan = new Scanner(System.in);
  while(true){
   System.out.println("\nList of Polynomials:");
   
   for(int i = 0; i < myArrayList.size(); i++) {
    System.out.print(i+": ");
    myArrayList.get(i).print();
    System.out.println();
   }
   
   System.out.println("Which do you wish to add? Press -1 to Exit.");
   input = scan.nextInt();
   if(input < 0){
    break;
   }
   input1 = scan.nextInt();
   
   if(input >= 0 && input < myArrayList.size() && input1 >= 0 && input1 < myArrayList.size()) {
    Polynomial poly1 = Polynomial.add(myArrayList.get(input),myArrayList.get(input1));
    
    myArrayList.get(input).print();
    System.out.print(" + ");
    myArrayList.get(input1).print();
    System.out.print(" = ");
    poly1.print();
   }
   else{
    System.out.println("That didn't work. Try another input.");
   }
  }
}
}