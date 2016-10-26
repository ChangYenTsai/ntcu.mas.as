package mas.v4.arraylist.notfinish;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UI extends JFrame {	
	/**
	 * v0.1
	 */
	private JPanel contentPane;
	private String filePath;
	private File selectedFile = null;
//	private String reversePath;
//	private File reverseFile = null;
	private FileWriter fwriter;
	private int tmpFile = 1;
//	private int endFile = 1;
	private String storeEndName = null;
	private static String fcs = new String(System.getProperty("user.dir"));
	private static File fcf = new File(System.getProperty("user.dir"));
	
	List<String> storeLine = new ArrayList<String>();
	List<String> storeReverse = new ArrayList<String>();
	
	Map<String, ArrayList<ArrayList<String>>> map = new HashMap<String, ArrayList<ArrayList<String>>>();
	
	public static void main(String[] args){		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UI () {
		setTitle("MAS v0.2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 140);
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
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		        	//get the path
		        	selectedFile = fileChooser.getSelectedFile();
		        	filePath = selectedFile.getAbsolutePath();
		    	    }		        	
		        }
		});
		
		
		JButton btnReverse = new JButton("Reversing");
		btnReverse.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnReverse.setBounds(10, 40, 110, 20);
		btnReverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(filePath!=null){
					
					
					
					for (final File fileEntry : selectedFile.listFiles()) {	
			        	BufferedReader br = null;
						try {
							br = new BufferedReader(new FileReader( filePath + "\\" + fileEntry.getName()));
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
			    		try {			    		    
			    		    String line = null;			    		    
							try {								
								line = br.readLine();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							
							
							
							
							//store original file line
							while (line != null) {								
								storeLine.add(line);
								try {
									line = br.readLine();
								} catch (IOException e1) {
									e1.printStackTrace();
								}	
							}							
/*							//store reverse line
							for (int i=storeLine.size()-1; i>=0; i--) {
								storeReverse.add(storeLine.get(i));
							}
*/							//store reverse line
							int j=storeLine.size()-1;
							ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
							
							for (int i=0; i<storeLine.size(); i++) {
								list.add(new ArrayList<String>());
								list.get(i).add(storeLine.get(j));								
								j--;
							}
							map.put(null, list);
							for(int k=0; k<storeLine.size(); k++){
								System.out.println(map.get(null).get(k).get(0));
								System.out.println("*************************************************");
							}
//							System.out.println(map.get(null).get(i).get(0));
//							StoreReverse(storeReverse);
			    		} finally {
			    		    try {
								br.close();
								System.out.println("---------------------------------------------------------");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
			    		}
		    	    }					
				}				
			}
			
		});
		
		
		JButton btnParse = new JButton("Parsing");
		btnParse.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnParse.setBounds(10, 70, 110, 20);
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//load file from here
	        	File reverseFile = new File(fcf + "\\reverseFile");
	        	fcs = fcs + "\\reverseFile";
				
				if(fcs!=null){
//					for(Map maps: map){
//						
//					}
					for (final File entry2 : reverseFile.listFiles()) {
			        	BufferedReader br = null;
			        	BufferedReader br2 = null;
						try {
							br = new BufferedReader(new FileReader(fcs + "\\" + entry2.getName()));
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
			    		try {	    		    
			    		    String line2 = null;
			    		    String templine = null;
			    		    int counttempline = 0;
			    		    //read first line
							try {								
								line2 = br.readLine();
								counttempline++;
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							List<String> storeMethod = new ArrayList<String>();
							
//							storeMethod.add(line2);
//							line2 = br.readLine();
							
							while (line2 != null) {
								String[] tokens = line2.split("\\s+");
								//filter method name
								for (String token: tokens) {
										if (token.contains("(") == true &&
											token.contains(");") == true &&
											token.contains("+") == false &&
											token.contains("-") == false &&
											token.contains("*") == false &&
											token.contains("/") == false &&
											token.contains("|") == false &&
											token.contains("&") == false &&
											token.contains("%") == false &&
											token.contains("!") == false &&
											token.contains("?") == false &&
											token.contains("get") == false &&
											token.contains("set") == false &&
											!Character.isUpperCase(token.charAt(0))) {
												int state = 1;
												storeMethod.add("M: " + token);
//												System.out.print("M: " + line2);
												
												//initial br2
												try {
													br2 = new BufferedReader(new FileReader(fcs + "\\" + entry2.getName()));
												} catch (FileNotFoundException e2) {
													e2.printStackTrace();
												}
												
												//let templine read from the record of line2
												for (int c=0; c<=counttempline; c++) {
													try {
														templine = br2.readLine();
													} catch (IOException e1) {
														e1.printStackTrace();
													}
												}
												
												//finding location loop
												while (state != 0) {
													
													//filter location name
													if (!templine.isEmpty() &&
														templine.contains("(") == true &&
														templine.contains(")") == true &&
														(templine.contains("public") == true ||
														templine.contains("private") == true ||
														templine.contains("protected") == true)) {
														String[] tokens2 = templine.split("\\s+");
														for (String token2: tokens2) {
															if (token2.contains("(") == true && token.contains(")") == true) {    
																storeMethod.add("Location: " + token2);
																storeMethod.add(" ");
//																System.out.println(", Location: " + templine);
																state = 0;
															}
														}
													} else {
														try {
															templine = br2.readLine();
														} catch (IOException e1) {
															e1.printStackTrace();
														}
														if (templine == null) {
															break;
														}
													}
												}	
										}				
								}
								try {
									line2 = br.readLine();
									counttempline++;
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
							storeEndName = entry2.getName();
							CutMethod(storeMethod);
			    		} finally {
			    		    try {
								br.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
			    		}
		    	    }
				System.out.println("All done.");
				}				
			}
		});
	
		contentPane.add(btnSelect);
		contentPane.add(btnReverse);
		contentPane.add(btnParse);
	}
	
	public void StoreReverse(List<String> storeLine) {		
		  File saveFile = new File(fcs + "\\reverseFile\\commit_" + tmpFile + ".java");
		  try
		  {
			  fwriter = new FileWriter(saveFile);
			  for (String str: storeLine) {
				  fwriter.write(str);
				  fwriter.append("\n");
//				  fwriter.close();				  
			  }			  
			  fwriter.close();
			  System.out.println("commit_" + tmpFile + " Done.");
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  tmpFile++;
	  }
	
	public void CutMethod(List<String> storeLine)
	  {
		  File saveFile = new File("endFile\\" + storeEndName);
		  try
		  {
			  fwriter = new FileWriter(saveFile);
			  for (String str: storeLine) {
				  fwriter.write(str);
				  fwriter.append("\n");
//				  fwriter.close();				  
			  }			  
			  fwriter.close();
			  System.out.println(storeEndName + " Done.");
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
//		  endFile++;
	  }
/*	
	public void FileCatch() {
		File fc = new File(UI.class.getResource("UI.class").toString());
        System.out.println(UI.class.getResource("UI.class").toString());
        System.out.println(fc);
	}
*/	
}


