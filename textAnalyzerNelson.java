package textAnalyzerNelson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class textAnalyzerNelson implements ActionListener {
	/**
	 * Constructing the GUI for the word occurrences 
	 * I made sure to input the original file on my computer.
	 *
	 * 
	 */
	public static void constructGUI()
	{
		File file = new File("C:/users/swann/downloads/TheRavenPoemWithHTMLTags (1).txt");
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Word Occurrences");
		
		frame.setLayout(new GridLayout(1, 4));
		
		
		JLabel fn = new JLabel("Word Occurences");
		
		JLabel sn = new JLabel("Text: The Raven");
		JTextArea res = new JTextArea();
		
		JButton next = new JButton("Next");
		frame.setBounds(10,40,700,500);
		frame.add(fn);
		
		frame.add(sn);
		
		frame.add(new JLabel(" "));
		
		frame.add(new JLabel(" "));
		frame.add(res);
		res.setLineWrap(true);
		res.setWrapStyleWord(true);
	
		frame.add(next);
		
		next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fn.setBounds(0, 10, 659, 51);
				fn.setText("Results:");
				
            try {
				res.setText(wordSearch(file));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            next.setVisible(false);
            sn.setVisible(false);
            }
        });
		
		
		int frameWidth = 700;
		int frameHeight = 500;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setBounds(
				(int) (screenSize.getWidth()/2) - frameWidth,
				(int) (screenSize.getHeight()/2) - frameHeight,
				frameWidth,
				frameHeight);
		frame.setVisible(true);
	}
	public static void main(String[] args) throws Exception
	{
		getConnection();
		File file = new File("C:/users/swann/downloads/TheRavenPoemWithHTMLTags (1).txt");
		wordSearch(file);
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run() {
				constructGUI();
			}
				});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static String wordSearch(File input) throws Exception{
		String str = "";
		db(input);
		Map map1 = new HashMap();
		try (BufferedReader bufferr = new BufferedReader(new FileReader("C:/users/swann/downloads/TheRavenPoemWithHTMLTags (1).txt"))){
			StringBuilder stbuilder = new StringBuilder();
			StringBuilder sg = new StringBuilder();
			String line = bufferr.readLine();
			
			 while (line != null) {
	                String[] w = line.split(" ");
	                for (int i = 0; i < w.length; i++) {
	                    if (map1.get(w[i]) == null) {
	                        map1.put(w[i], 1);
	                    } else {
	                        int newValue = Integer.valueOf(String.valueOf(map1.get(w[i])));
	                        newValue++;
	                        map1.put(w[i], newValue);
	                    }
	                }
	                stbuilder.append(System.lineSeparator());
	                line = bufferr.readLine();
	            }
			 Set<Map.Entry<String, Integer>> list = map1.entrySet();
				ArrayList<Entry<String, Integer>> sort = new ArrayList<Map.Entry<String, Integer>>(list); 
				Collections.sort( sort, new Comparator<Map.Entry<String, Integer>>() 
		            {
		                public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) 
		                {
		                    return (b.getValue()).compareTo( a.getValue() ); 
		                   
		                   

		                }
		            } );
				 
				 for( int i=0;i< 20;i++) {
					 sg.append((i+1) + ". " + sort.get(i) + "\n");
				 }
				
				 str = sg.toString();
				 System.out.println(str);
				return str;
	        }
	      
			
	

}
	
	 public static void db(File in) throws Exception{
		   
	        try{
	        	Connection con = getConnection();
	        	Scanner s = new Scanner(in);
				 while (s.hasNext()) //
				    {
				        String word = s.next(); 
				        PreparedStatement ins = con.prepareStatement("INSERT into wordoccurences.word (word) values ('"+word+"');");
			            ins.executeUpdate();

				    }
				 s.close();
	            
	        } catch(Exception e){System.out.println(e);}
	        finally {
	            System.out.println("Inserted.");
	        }
	        
	    }
	
	
	 
	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/wordoccurences";
			String username = "root";
			String password = "MyN3wP4ssw0rd";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		} catch(Exception e){System.out.println(e);}
		
		return null;
	}

}
