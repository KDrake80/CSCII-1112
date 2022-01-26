/*
 * Author: Kevin Drake
 * Date 1/25/22
 * This tests my remove duplicates program
 */
import java.util.*;
public class Exercise19_03 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(14);
    list.add(24);
    list.add(14);
    list.add(42);
    list.add(25);
    
    ArrayList<Integer> newList = RemoveDuplicates.removeDuplicates(list);
    
    System.out.print(newList);
  }
}