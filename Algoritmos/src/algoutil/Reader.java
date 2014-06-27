package algoutil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Reader implements Iterable<String> {
	private String fileName;

	public Reader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Iterator<String> iterator() {
		try {
			return new FileIterator();			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String... args){
		for (String line : new Reader("files/simpleinput2.txt"))
			System.out.println(line);
	}

	class FileIterator implements Iterator<String> {

		FileReader fr;
		BufferedReader input;

		public FileIterator() throws FileNotFoundException {
			fr = new FileReader(fileName);
			input = new BufferedReader(fr);
		}

		@Override
		public boolean hasNext() {
			try {
				if (input.ready())
					return true;
				else {
					input.close();
					return false;
				}

			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		public String next() {
			try {
				return input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}

		@Override
		public void remove() {
		}
	}
}