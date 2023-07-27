package textAnalyzerNelson;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class wordSearchTest {

	@Test
	public void test() throws IOException {
		File test1 = new File("C:\\Users\\swann\\OneDrive\\Desktop\\testCase1.txt");
		textAnalyzerNelson test = new textAnalyzerNelson();
		String output = test.wordSearch(test1);
		assertEquals("1. yes=7\n2. haha=5\n3. no=4\n4. 1=3\n5. 2=2\n", output);
		
		File test2 = new File("C:\\Users\\swann\\OneDrive\\Desktop\\testCase2.txt");
		textAnalyzerNelson test21 = new textAnalyzerNelson();
		String output2 = test21.wordSearch(test2);
		assertEquals("1. test=7\n2. testing=5\n3. tested=4\n4. 5=3\n5. nine=2\n", output2);
		
		File test3 = new File("C:\\Users\\swann\\OneDrive\\Desktop\\testCase3.txt");
		textAnalyzerNelson test31 = new textAnalyzerNelson();
		String output3 = test31.wordSearch(test3);
		assertEquals("1. tester=6\n2. this=5\n3. is=4\n4. my=3\n5. test=2\n", output3);
		
		
}
}
