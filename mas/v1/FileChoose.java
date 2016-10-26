package mas.v1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;  
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
  
public class FileChoose extends JFrame {
	
	/**
	 * v 0.1
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String filepath = null;
	private String formalPath = null;
	private File selectedFile = null;
	private File needFormalFile = null;
	private FileWriter fwriter;
	private int countFile = 1;
//	private String[] storeContent = null;

	
//	private String line;
	
	public static void main(String[] args){		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileChoose frame = new FileChoose();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	public FileChoose(){
		setTitle("AMS v0.5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 150, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSelect = new JButton("Select Folder");
		btnSelect.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnSelect.setBounds(10, 10, 110, 20);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				// For Directory
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        // For File
		        //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		        	//get the path
		        	selectedFile = fileChooser.getSelectedFile();
		        	filepath = selectedFile.getAbsolutePath();        	
		    	    }		        	
		        }
		});
		
		JButton btnParser = new JButton("Parsing");
		btnParser.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnParser.setBounds(10, 40, 110, 20);
		btnParser.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent event) {
				if(filepath!=null){
					for (final File fileEntry : selectedFile.listFiles()) {	
		        		//print all file's content
			        	BufferedReader br = null;
						try {
							br = new BufferedReader(new FileReader( filepath + "\\" + fileEntry.getName()));
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
			    		try {
//			    			StringBuilder sb = new StringBuilder();			    		    
			    		    String line = null;
//			    		    List<String> line = new ArrayList<String>();
//			    		    String[] fileArray;
			    		    
							try {
								
								line = br.readLine();						
								
							} catch (IOException e1) {
								e1.printStackTrace();
							}
/*							
							while (line != null) {
//	    		    			sb.append(line); //讀一行
			    		    		if (!line.isEmpty() && sb.charAt(0) != '+'){
			    		    			System.out.println();
			    		    			try {
											line = br.readLine();
										} catch (IOException e) {
											e.printStackTrace();
										}
			    		    		}
			    		    		else {
			    		    			System.out.println(line);
				    		        	try {
				    		        		line = br.readLine();
										} catch (IOException e) {
											e.printStackTrace();
										}				    		        			
			    		    		}
							}
*/														
//							fileArray = new String[50];							
							List<String> storeLine = new ArrayList<String>();							
							while (line != null) {
								if (!line.isEmpty() && line.charAt(0) != '+') {
//									System.out.println();
									try {
										line = br.readLine();
									} catch (IOException e) {
										e.printStackTrace();
									}
								} else {
//									storeContent = Integer.toString(line);
//									String[] fileArray = line.toArray(new String[0]);									
//									System.out.println(line);
									storeLine.add(line);
									
//									System.out.println(storeLine);
//									System.out.println("***************************************");									
									try {
										line = br.readLine();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}								
							}							
//							String everything = sb.toString();
//							System.out.println(everything);
							CutFunction(storeLine);
//							System.out.println(storeLine);
//	    		    		System.out.println("***********************************************");		    		    
			    		} finally {
			    		    try {
								br.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
			    		}
//		        		System.out.println(fileEntry.getName());
		    	        }					
				}
			}		
		});	
		
		JButton btnFormal = new JButton("Formalize");
		btnFormal.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnFormal.setBounds(10, 70, 110, 20);
		btnFormal.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				if(filepath!=null){
					for (final File fileNeedNormal : selectedFile.listFiles()) {	
//						System.out.println(selectedFile);
		        		//print all file's content
			        	BufferedReader br2 = null;
						try {
							br2 = new BufferedReader(new FileReader( filepath + "\\" + fileNeedNormal.getName()));
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						String line2 = null;
						try {
							line2 = br2.readLine();						
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						List<String> storeLine2 = new ArrayList<String>();
						String temp = null;
						int c = 5;
						
						while (line2 != null) {
							if (!line2.isEmpty() && line2.charAt(0) == '+') {
								temp = line2.replaceFirst("[+]", "");
//								storeLine2.add(line2);
								System.out.println(line2.length());
//								System.out.println(temp);
								
								if (line2.charAt(0) == '+' && line2.charAt(1) == '+' && line2.charAt(line2.length()-2) == 'a')                           
								{   
									temp = line2;
									line2.replace(temp, "public class main() {");
								}
								
								
								try {
									line2 = br2.readLine();
								} catch (IOException e) {
									e.printStackTrace();
								}							
							} else {
								try {
									line2 = br2.readLine();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}							
					}
				}
			}
		});		
		contentPane.add(btnSelect);
		contentPane.add(btnParser);
		contentPane.add(btnFormal);
	}
	
/*	
	public void start(){
		final File folder = new File("testContent/");
		listFilesForFolder(folder);
	}
	
*/	
	
	public void CutFunction(List<String> storeLine)
	  {
		  File saveFile = new File("countFile\\commit_" + countFile + ".java");
		  try
		  {
			  fwriter = new FileWriter(saveFile);
			  for (String str: storeLine) {
				  fwriter.write(str);
				  fwriter.append("\n");
//				  fwriter.close();				  
			  }			  
			  fwriter.close();
			  System.out.println("commit_" + countFile + "Done!");
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  countFile++;
	  }
	
	public void FormalFunction(List<String> storeLine2)
	  {
		  File saveFile = new File("countFile\\commit_" + countFile + ".java");
		  try
		  {
			  fwriter = new FileWriter(saveFile);
			  for (String str2: storeLine2) {
				  fwriter.write(str2);
				  fwriter.append("\n");
//				  fwriter.close();
				  }			  
			  fwriter.close();
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  countFile++;
	  }
	
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}	
} 