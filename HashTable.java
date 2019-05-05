public class HashTable{
  private PListInterface[] table;
  private int mod;
  public HashTable(int size){
    table = new PList[size];
    for(int i = 0; i < table.length; i++){
      table[i] = new PList();
    }
    mod = table.length;
    while(!isPrime(mod)){
      mod++;
    }
  }

  public boolean isPrime(int n){
    for(int i = 2; i < n; i++){
      if(n%i == 0){
        return(false);
      }
    }
    return(true);
  }
  public int hashPlus(String key){
    int val = 0;
    char[] letters = key.toCharArray();
    for(int i = 0; i < letters.length; i++){
      val += Character.valueOf(letters[i]); // Geeksforgeeks
    }
    if(val%mod >= table.length){
      return(val%(table.length));
    }
    return(val%mod);
  }

  public int hashSpecific(String key){
    int val = 0;
    char[] letters = key.toCharArray();
    val += letters[0];
    val += letters[1];
    if(val%mod >= table.length){
      return(val%(table.length));
    }
    return(val%mod);
  }


  // public int hashTimes(String key){
  //   int val = 1;
  //   char[] letters = key.toCharArray();
  //   for(int i = 0; i < letters.length; i++){
  //     val *= Character.valueOf(letters[i]); // Geeksforgeeks
  //   }
  //   if(val%mod >=table.length){
  //     return(val%(table.length));
  //   }
  //   return(val%mod);
  // }

  public void add(String key){
    PListInterface list = table[hashPlus(key)];
    // Check if it's alread in there
    for(int i = 0; i < list.length(); i++){
      if(list.get(i).equals(key)){
        return;
      }
    }
    list.add(key);
  }

  public void print(){
    System.out.println("Hash Table\n");
    for(int i = 0; i < table.length; i++){
      System.out.printf("%d: ", i);
      table[i].print();
      System.out.println();
    }
  }

}
