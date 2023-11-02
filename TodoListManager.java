import java.util.*;
import java.io.*;

// Yunho cho
// TA: Ben Wang
// CSE 122 AF
// July 6 2023
// TodoListManager
// This class is todoListManager. It records all of the todo list by adding them 
// and marks it if its done or save the list as file and load up the todo list file anytime.
public class TodoListManager {
    public static final boolean EXTENSION_FLAG = true;

    public static void main(String[] args) throws FileNotFoundException {
        // TODO: Your Code Here
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to your TODO List Manager!");
        List<String> todos = new ArrayList<String>();
        if(EXTENSION_FLAG) {      
            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit, (R)emove File? ");
        } else {
            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit? ");    
        }    
        String choice = console.nextLine();
        while(!choice.equalsIgnoreCase("Q")) { 
            if(choice.equalsIgnoreCase("A")) {
                addItem(console, todos);
            } else if (choice.equalsIgnoreCase("M")) {
                markItemAsDone(console, todos);
            } else if (choice.equalsIgnoreCase("S")) {
                saveItems(console, todos);                
            } else if (choice.equalsIgnoreCase("L")) {
                loadItems(console, todos);
            } else if (choice.equalsIgnoreCase("R") && EXTENSION_FLAG){
                System.out.println("Would you like to remove any list from you're TODO list files");
                System.out.print("(Y)es or (N)o? ");
                String option = console.nextLine();
                if(option.equalsIgnoreCase("y")) {
                    removeItems(console, todos);
                } else if (option.equalsIgnoreCase("n")) {
                    System.out.print("All done, Relax for today!");
                } else {
                    System.out.print("Unknown input: " + option);
                }
            } else {
                System.out.println("Unknown input: " + choice);
            } 
            printTodos(todos); 
            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit? ");    
            choice = console.nextLine();
        }
    }
    
    // Prints all the todo list unless list is empty, it prints
    // out a statement to remind it there is nothing to finish.
    // no exception
    // no return
    // Parameters:
    //    - todos: list of every todo list
    public static void printTodos(List<String> todos) {
        // TODO: Your Code Here
        System.out.println("Today's TODOs:");
        if(todos.size() > 0) {
            for(int i = 1; i <= todos.size(); i++) {
                System.out.println("  " + i + ": " + todos.get(i - 1));
            }    
        } else {
            System.out.println("  You have nothing to do yet today! Relax!");
        }
    }

    // If EXTENSION_FLAG is false instead its true this method is helper method
    // from main method to pull out specififc files to delete specific lines.
    // excpetion:
    // - Throws FileNotFoundException if specified pathname of file does nto exist
    // - Throw new IndexOutOfBoundsException if user input out of index size of line numbers
    // no return 
    // Parameters:
    //    - todos: To count number of lines from file and pass it all down into list
    //  - console: For use to input number of lines to delete and name of files to pull out
    public static void removeItems(Scanner console, List<String> todos) 
                                          throws FileNotFoundException {
        List<String> temp = new ArrayList<String>();
        System.out.print("File name? ");
        String fileName = console.nextLine();
        Scanner fileInput = new Scanner(new File(fileName));
        while(fileInput.hasNextLine()) {
            String nextList = fileInput.nextLine();
            todos.add(nextList);
            temp.add(nextList);
        }
        System.out.print("Which line would you like to delete the Item? ");
        String input = console.nextLine();
        int lineToDelete = Integer.parseInt(input); 
            if(lineToDelete < 0 || lineToDelete > todos.size()) {
                throw new IndexOutOfBoundsException("Inavlid line number : " + lineToDelete);
            } else {
                temp.remove(lineToDelete - 1);
            }
            PrintStream output = new PrintStream(new File(fileName));
                for(int i = 1; i <= temp.size(); i++) {
                    output.println(temp.get(i - 1));
                }
        
    }
    
    // It let us add specific String into our arrayList 
    // and then calls other method to arrange the order.
    // no exception
    // no return
    // Parameters:
    //  - console: User gets to input a todo list 
    //    - todos: adding user's input into list of todos
    public static void addItem(Scanner console, List<String> todos) {
        // TODO: Your Code Here
        System.out.print("What would you like to add? ");
        String plan = console.nextLine();
            if(todos.size() > 0) {
                System.out.print("Where in the list should it be (1-" + (todos.size() + 1) + ")? (Enter for end): ");
                String input = console.nextLine();
                if(input.isEmpty()) {
                    todos.add(plan);
                } else {
                    int listOrder = Integer.parseInt(input); 
                    swapOrder(todos, listOrder, plan);
                }
            } else {
                todos.add(plan);
            }
    }
    
    // This is helper method for addItem to arrange ArrayList 
    // order if user want to add into different order.
    // no exception
    //   Parameter:
    //     - todos: a list of todo list to arranged it to new todo list
    // - ListOrder: Specific order to add new plan
    //      - plan: String of new arangment list to be added
    //          Return:
    //  - List<String>: It returns new arrangement order of todo ArrayList
    public static List<String> swapOrder(List<String> todos, int listOrder, String plan) {
        todos.add(listOrder - 1, plan);
        return todos;
    }
    
    // This method helps to mark off which todo list are completed if there isn't 
    // anymore list to mark off it will give a statement of reminding no more todolist.
    // no exception
    // no return
    // Parameter:
    // - console: To indicate which order number is going to be marked off
    //   - todos: To remove a String from list after its marked off
    public static void markItemAsDone(Scanner console, List<String> todos) {
        // TODO: Your Code Here
        if (todos.size() > 0) {
            System.out.print("Which item did you complete (1-" + todos.size() + ")? ");
            int complete = console.nextInt();
            console.nextLine();
            todos.remove(complete - 1);
        } else {
            System.out.println("  All done! Nothing left to mark as done!");
        }
    }
    
    // This method is to save it as a file of todo 
    // list that has been addded and marked off.
    // exception:
    // - Throws FileNotFoundException if specified pathname of file does not exist
    // no return
    // Parameter:
    // - console: User inputs the name of the file System
    //   - todos: To print out each String inside of list
    public static void saveItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        // TODO: Your Code Here
        System.out.print("File name? ");
        String fileName = console.nextLine();
        PrintStream output = new PrintStream(new File(fileName));
        for(int i = 1; i <= todos.size(); i++) {
            output.println(todos.get(i - 1));
        }
    }

    // This method is to load up a file by user types a file name
    // and then opens it and prints out untill it has no line afterward.
    // excpetion:
    // - Throws FileNotFoundException if specified pathname of file does not exist
    // no return
    // Parameter:
    // - console: user will input name of file to open it
    //   - todos: an list of todolist that will be uplaoded by each String
    public static void loadItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        // TODO: Your Code Here
        todos.clear();
        System.out.print("File name? ");
        String fileName = console.nextLine();     
        Scanner fileInput = new Scanner(new File(fileName));
        while(fileInput.hasNextLine()) {
            String nextList = fileInput.nextLine();
            todos.add(nextList);
        }
    }
}