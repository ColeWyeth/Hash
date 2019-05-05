import java.util.Scanner;

class PList implements PListInterface{
  private ObjectNode head;
  private int length; // I'll keep track of this so the length method
              // becomes very easy to implement and order 1.

  public PList(){
    head = new ObjectNode("head",null);
    length = 0; // head doesn't count, we'll pretend it doesn't exist
  }
  public ObjectNode getHead(){
    return(head);
  }
  public static void printUsage(){
    System.out.println("Usage: \n");
    System.out.println("Exit                  -- quit");
    System.out.println("Create                -- Creates an empty PList");
    System.out.println("Select (number)       -- choose a PList to operate on");
    System.out.println("Add (input)           -- Append to beginning of the PList");
    System.out.println("Append (input)        -- Append to the end of the PList");
    System.out.println("Delete (index)        -- Remove item at index from the PList");
    System.out.println("Get (index)           -- Returns the item at index from the PList");
    System.out.println("Insert (item) (index) -- Inserts item at index (or the end of the PList)");
    System.out.println("Length                -- Returns the length of the PList");
    System.out.println("Print                 -- Prints the current PList");
    System.out.println("Remove (item)         -- Removes the data entry that matches item from the PList");
    System.out.println("Sort                  -- Sorts the PList by comparing toString");
    System.out.println("Concatenate (number)  -- Add the data in the Plist at number to the end of the current PList.");
  }
  public void add(Object item){
    ObjectNode next = new ObjectNode(item, head.getNext());
    head.setNext(next);
    length += 1;
  }
      // adds item to the start of the PList.

  public void append(Object item){
    ObjectNode next = new ObjectNode(item, null);
    ObjectNode ptr = head;
    while(true){
      if(ptr.getNext() == null){
        ptr.setNext(next);
        break;
      }
      ptr = ptr.getNext();
    }
    length += 1;

  }
     // places item at the end of the PList.

  public void concatenate(PListInterface plist){
    int i = 0;
    while(true){
      if(plist.get(i) == null){
        break;
      }
      append(plist.get(i));
      i++;
    }
  }
    // joins plist to this PList to create a longer list.

  public void delete(int index){
    ObjectNode ptr = head;
    for(int i = 0; i <= index; i++){
      if(ptr.getNext() == null){
        break;
      }
      if(i == index){
        ptr.setNext(ptr.getNext().getNext());
      }
      ptr = ptr.getNext();
    }
  }
    // removes the item at index from the Plist.
    // if the index does not exist, nothing happens.

  public Object get(int index){
    ObjectNode ptr = head.getNext();
    for(int i = 0; i <= index; i++){
      if(ptr == null){
        return(null);
      }
      if(i == index){
        return(ptr.getData());
      }
      ptr = ptr.getNext();
    }
    return(null); // To get past the compiler
  }
    // returns the data item at index.

  public void insert(Object item, int index){
    ObjectNode ptr = head;
    if(index < 0){
      return;
    }
    for(int i = 0; i <= index; i++){
      if(i == index || ptr.getNext() == null){
        ObjectNode next = new ObjectNode(item, ptr.getNext());
        ptr.setNext(next);
        break;
      }
      ptr = ptr.getNext();
    }
    length += 1;

  }
    // places item at the specified index.
    // if the index is past the end of the list,
    // item is added at the end of the PList.

  public int length(){
    return(length);
  }
    // returns the length of the PList.

  public void print(){
    ObjectNode ptr = head.getNext();
    while(ptr != null){
      System.out.println(ptr.toString());
      ptr = ptr.getNext();
    }
  }
  public String toString(){
    ObjectNode ptr = head.getNext();
    String out = "";
    while(ptr != null){
      out += ptr.toString() + "\n";
      ptr = ptr.getNext();
    }
    return(out);
  }
    // displays to the screen the items in PList from beginning to end.
    // this is an option used for testing and verification.

  public void remove(Object item){
    ObjectNode ptr = head;
    while(true){
      if(ptr.getNext() == null){
        return; // We reached the end
      }
      if(ptr.getNext().getData().equals(item)){
        ptr.setNext(ptr.getNext().getNext());
        break;
      }
      ptr = ptr.getNext();
    }
    length -= 1;
  }
    // removes the data entry that matches item from the PList.

  public void sort(){
    PList sorted = new PList();
    ObjectNode unsorted = head.getNext();
    while(true){ // Outer loop: go through plist moving each item to sorted
      if(unsorted == null){
        break;
      }
      ObjectNode ptr = sorted.getHead().getNext();
      int i = 0;
      while(true){ // Inner loop: go through sorted to find the spot for the item
        if(ptr == null || unsorted.toString().compareTo(ptr.toString()) < 0){
          sorted.insert(unsorted.getData(), i);
          break;
        }
        ptr = ptr.getNext();
        i++;
      }
      unsorted = unsorted.getNext();
    }
    head = sorted.getHead(); // Set itself equal to the sorted list
  }

    // sorts all items in PList by comparing the toString() values
    // of each data item.

  public boolean equals(PListInterface other){
    if(length != other.length()){
      return(false);
    }
    for(int i = 0; i < length; i++){
      if(get(i) != other.get(i)){
        return(false);
      }
    }
    return(true);
  }

  public void removeEvery(int n){
    int index = n-1; // Counting starts at 0
    while(get(index) != null){
      delete(index);
      index += n-1; // Length decreased by 1
    }
  }

  public void removeDuplicates(){
    int i = 0;
    while(true){
      Object data = get(i);
      if(data == null){
        break;
      }
      int j = i+1;
      while(true){
        Object next = get(j);
        if(next == null){
          break;
        }
        if(next.equals(data)){
          delete(j);
        }else{
          j++;
        }
      }
      i++;
    }
  }

  public static void main(String[] args){
    // TEST CASES BELOW:

    // PList plist1 = new PList();
    // PList plist2 = new PList();
    // plist1.add("Me");
    // plist1.add("The Doctor");
    // plist1.append(1);
    // plist2.add("Someone else");
    // plist2.add("The Doctor");
    // System.out.println("plist1: \n"+plist1.toString());
    // System.out.println("plist2: \n"+plist2.toString());
    // System.out.println("plist1 == plist2: " + plist1.equals(plist2));
    // System.out.println("plist1 == plist1: " + plist1.equals(plist1));
    // plist2.remove("Someone else");
    // plist2.append("Me");
    // plist1.remove(1);
    // System.out.println("plist1: \n"+plist1.toString());
    // System.out.println("plist2: \n"+plist2.toString());
    // System.out.println("plist1 == plist2: " + plist1.equals(plist2));
    //
    // PList plist3 = new PList();
    // plist3.insert(7, 0);
    // plist3.insert("Skulduggery Pleasant", 3);
    // plist3.insert(2.5, 1);
    // plist3.insert("Zzzzzz",10);
    // System.out.println("plist3: \n"+plist3.toString());
    // System.out.println("Concatenate plist1 and plist3: ");
    // plist1.concatenate(plist3);
    // System.out.println("plist1: \n"+plist1.toString());
    //
    // System.out.println("Sort plist 1");
    // System.out.println("plist1: \n"+plist1.toString());

    Scanner s = new Scanner(System.in);
    PList current = new PList(); // Need to start with one already created or everything throws errors.
    ObjectNode currentNode = new ObjectNode(current, null);
    ObjectNode plists = new ObjectNode("head",currentNode);
    ObjectNode ptr;
    int number; // Refers to index of PList among PLists
    while(true){
      System.out.print("PList>");
      String[] input = s.nextLine().split(" ");
      input[0] = input[0].toLowerCase();
      if(input[0].equals("exit")){
        break;
      }
      switch(input[0]){
        case "help":
                  printUsage();
                  break;
        case "create":
                  PList nextPList = new PList();
                  ObjectNode nextNode = new ObjectNode(nextPList, null);
                  ptr = plists;
                  while(true){
                    if(ptr.getNext() == null){
                      ptr.setNext(nextNode);
                      break;
                    }
                    ptr = ptr.getNext();
                  }
                  break;
        case "select":
                  if(input.length < 2){
                    printUsage();
                    break;
                  }
                  number = Integer.valueOf(input[1]);
                  ptr = plists.getNext();
                  for(int i = 0; i <= number; i++){
                     if(ptr == null){
                       System.out.println("Error: Out of bounds. Counting starts at 0.");
                       break;
                     }
                     if(i == number){
                       current = (PList) ptr.getData();
                     }
                     ptr = ptr.getNext();
                  }
                  break;
        case "add":
                  if(input.length < 2){
                    printUsage();
                    break;
                  }
                  current.add(input[1]);
                  break;
        case "append":
                  if(input.length < 2){
                    printUsage();
                    break;
                  }
                  current.append(input[1]);
                  break;

        case "get":
                  if(input.length < 2){
                    printUsage();
                    break;
                  }
                  System.out.println(current.get(Integer.valueOf(input[1])));
                  break;
        case "insert":
                  if(input.length < 3){
                    printUsage();
                    break;
                  }
                  current.insert(input[1], Integer.valueOf(input[2]));
                  break;
        case "remove":
                  if(input.length < 2){
                    printUsage();
                    break;
                  }
                  current.remove(input[1]);
                  break;
        case "length":
                  System.out.println(current.length());
                  break;
        case "concatenate":
                  // I didn't need to overload concatenate.
                  // I just step through the list of plists to find the one at the index.
                  // Then I pass that to the concatenate.
                  if(input.length < 2){
                    printUsage();
                    break;
                  }
                  ptr = plists.getNext();
                  number = Integer.valueOf(input[1]);
                  for(int i = 0; i <= number; i++){
                      if(ptr == null){
                        break; // We've hit the end so we bail and do nothing
                      }
                      if(i == number){
                        current.concatenate((PListInterface) ptr.getData());
                      }
                      ptr = ptr.getNext();
                  }
                  break;

        case "print":
                  current.print();
                  break;
        case "sort":
                  current.sort();
                  break;
        case "delete":
                  if(input.length < 2){
                    printUsage();
                    break;
                  }
                  current.delete(Integer.valueOf(input[1]));
                  break;
        case "removeevery":
                  if(input.length < 2){
                    printUsage();
                    break;
                  }
                  current.removeEvery(Integer.valueOf(input[1]));
                  break;
        case "removeduplicates":
                  current.removeDuplicates();
                  break;
        default: printUsage();
      }
    }
  }
}
