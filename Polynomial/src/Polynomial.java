import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

//  Virginia Pollock
//  CIS 2353
//  Fall 2018
//  Project 2

public class Polynomial{
    
private Node firstNode;
    
private class Node {
    public int coefficient;
    public int exponent;
    private Node next;
    
    public Node() {
        
    }
    
  @Override
  public String toString(){
   String terms = "";
   if(coefficient < 0){
    terms = "("+coefficient+")";
   }
   else{
    terms = coefficient+"";
   }
   if(exponent == 1){
    terms += "x";
   }
   else if(exponent < 0){
    terms += "x^("+exponent+")";
   }
   else if(exponent > 0){
    terms += "x^"+exponent;
   }
   return terms;
  }
}

public Polynomial(){
  firstNode = null;
}

public Polynomial(String poly){
  firstNode = null;
  poly = poly.replace("-","+-");
  String[] str = poly.split("\\+");
  for(int i = 0; i <str.length; i++) {
   String[] data = str[i].split("[x]");
   Node newNode = new Node();
   newNode.coefficient = Integer.parseInt(data[0]);
   newNode.exponent = 0;
   
   if(str[i].indexOf('x') != -1) {
    newNode.exponent = 1;
   }
   if(data.length == 2) {
    data[1] = data[1].replace('^',' ');
    newNode.exponent = Integer.parseInt(data[1].trim());
   }
   add(newNode);
  }
}

public Polynomial(Polynomial poly){
  firstNode = null;

  Node node = poly.getHead();
  while(node != null){
   Node newNode = new Node();
   newNode.coefficient = node.coefficient;
   newNode.exponent = node.exponent;
   add(newNode);
  }
}

public void add(Node newNode){
  if(firstNode == null){
   firstNode = newNode;
   return;
  }
  Node node = firstNode;
  while(node.next != null){
   node = node.next;
  }
  node.next = newNode;
}

public void add(int coefficientf,int exp){
  if(firstNode == null){
   firstNode = new Node();
   firstNode.coefficient = coefficientf;
   firstNode.exponent = exp;
   return;
  }
  Node node = firstNode;
  while(node.next != null){
   node = node.next;
  }
  node.next = new Node();
  node.next.coefficient = coefficientf;
  node.next.exponent = exp;
}

public void print(){
  Node node = firstNode;
  while(node != null){
   System.out.print(node);
   if(node.next != null){
    System.out.print("+");
   }
   node = node.next;
  }
}

public static Polynomial add(Polynomial poly1,Polynomial poly2){
  Polynomial poly = new Polynomial();
  Node head1 = null;
  Node head2 = null;
  
  if(poly1 != null) {
   head1 = poly1.getHead();
  }
  if(poly2 != null) {
   head2 = poly2.getHead();
  }
  int coefficient = 0;
  int exponent = 0;
  
  while(head1 != null && head2 != null) {
   if(head1 == null){
    coefficient = head2.coefficient;
    exponent = head2.exponent;
    poly.add(coefficient,exponent);
    head2 = head2.next;
   }
   else if(head2 == null) {
    coefficient = head1.coefficient;
    exponent = head1.exponent;
    poly.add(coefficient,exponent);
    head1 = head1.next;
   }
   else if(head1.exponent > head2.exponent){
    coefficient = head1.coefficient;
    exponent = head1.exponent;
    poly.add(coefficient,exponent);
    head1 = head1.next;
   }
   else if(head1.exponent < head2.exponent){
    coefficient = head2.coefficient;
    exponent = head2.exponent;
    poly.add(coefficient,exponent);
    head2 = head2.next;
   }
   else{
    coefficient = head2.coefficient + head1.coefficient;
    exponent = head2.exponent;
    poly.add(coefficient,exponent);
    head1 = head1.next;
    head2 = head2.next;
   }
  }
  return poly;
}

public Node getHead(){
  return firstNode;
}


}