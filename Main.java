import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		/*
		 * Basic approach:
		 * scan through the file
		 * for each word, make a lowercase version with all letters sorted in alphabetical order
		 * use the lowercase, sorted string as the key for a hashmap, where the corresponding value is an arraylist of strings
		 * if the lowercase, sorted string already exists as a key, then add this word to that arraylist
		 * if the lowercase, sorted string is not in the array, then create a new arraylist (with only this word)
		 * 
		 * after this scanning is completed, every arraylist in the hashmap which has more than one element is a list of anagrams
		 */
		
		
		Scanner s = new Scanner(new File("wordlist.txt"));
		
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		
		while (s.hasNextLine()){
			String word = s.nextLine();
			String lowcaseword = word.toLowerCase();
			char[] chars = new char[lowcaseword.length()];
			for(int i = 0; i < chars.length; i++){
				chars[i] = lowcaseword.charAt(i);
			}
			Arrays.sort(chars);
			lowcaseword = new String(chars);
			
			if(map.containsKey(lowcaseword)){
				//map already has this key, add to array list (word is an anagram)
				ArrayList<String> list = (ArrayList<String>) map.get(lowcaseword);
				list.add(word);
			}
			else{
				//map doesn't contain key, create new array list with this item
				ArrayList<String> list = new ArrayList<String>(1);
				list.add(word);
				map.put(lowcaseword, list);
			}
			
		}
		
		s.close();
		
		// at this point, everything is in the hash map
		// all araylists in the hashmap which have more than one element contain anagrams 
		
		Collection<ArrayList<String>> col = map.values();
		for (ArrayList<String> list : col){
			if(list.isEmpty() || list.size() == 1){
				continue;
			}
			
			// if reached this point, "list" is a list of anagrams
			String line = "";
			for(String word : list){
				line += word + " ";
			}
			line = line.trim();
			System.out.println(line);
		}
	}

}
