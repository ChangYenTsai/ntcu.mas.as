package ntcu.mas.as;

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
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


public class UI extends JFrame{	
	/**
	 * v0.1
	 */
	private JPanel contentPane;
	private String filePath;
	private File selectedFile = null;
//	private String reversePath;
//	private File reverseFile = null;
	private FileWriter fwriter;
//	private int tmpFile = 1;
//	private int endFile = 1;
	private String storeEndName = null;
	private String storeReverseName = null;
	
//	private Timer timer = null;
//	private int runTimeSec = 0;
	
	private static String fcs = new String(System.getProperty("user.dir"));
	private static File fcf = new File(System.getProperty("user.dir"));
	
	private static String fcs2 = new String(System.getProperty("user.dir"));
	private static File fcf2 = new File(System.getProperty("user.dir"));
	
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
		setBounds(100, 100, 510, 140);
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
		        	
//		        	timeLabel.setText("00:00");		        	
		    	    }		        	
		        }
		});
		
		
		JButton btnReverse = new JButton("Reversing");
		btnReverse.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnReverse.setBounds(130, 10, 110, 20);
		btnReverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(filePath!=null){
					
//					runTimeSec = 0;
//			        timer.start();
			        
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
							
							List<String> storeLine = new ArrayList<String>();
							List<String> storeReverse = new ArrayList<String>();
							
							//store original file line
							while (line != null) {								
								storeLine.add(line);
								try {
									line = br.readLine();
								} catch (IOException e1) {
									e1.printStackTrace();
								}	
							}							
							//store reverse line
							for (int i=storeLine.size()-1; i>=0; i--) {
								storeReverse.add(storeLine.get(i));
								try {
									line = br.readLine();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
							storeReverseName = fileEntry.getName();
							StoreReverse(storeReverse);
			    		} finally {
			    		    try {
								br.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
			    		}
					}
//				timer.stop();
				System.out.println("All done.");
				}
			}			
		});
		
		
		JButton btnParse = new JButton("Parsing");
		btnParse.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnParse.setBounds(250, 10, 110, 20);
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//load file from here
	        	File reverseFile = new File(fcf + "\\reverseFile");
	        	fcs = fcs + "\\reverseFile";
				
				if(fcs!=null){
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
								String[] tokens = line2.split("\\s+|\\.");
								//filter method name
								if (!line2.isEmpty() && line2.charAt(0)=='+'){
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
//													storeMethod.add("(Add)M: " + token);
//													System.out.print("M: " + line2);
													
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
															templine.contains("(") &&
															templine.contains(")") &&
															(templine.contains("public") ||
															templine.contains("private") ||
															templine.contains("protected"))) {
															String[] tokens2 = templine.split("\\s+");
															for (String token2: tokens2) {
																if (token2.contains("(") == true && token.contains(")") == true) {    
																	storeMethod.add("(Add)M: " + token);
																	storeMethod.add("Location: " + token2);
																	storeMethod.add(" ");
//																	System.out.println(", Location: " + templine);
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
								} else if(!line2.isEmpty() && line2.charAt(0)=='-'){
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
//												storeMethod.add("(Del)M: " + token);
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
														templine.contains("(") &&
														templine.contains(")") &&
														(templine.contains("public") ||
														templine.contains("private") ||
														templine.contains("protected"))) {
														String[] tokens2 = templine.split("\\s+");
														for (String token2: tokens2) {
															if (token2.contains("(") == true && token.contains(")") == true) {    
																storeMethod.add("(Del)M: " + token);
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
		
		
		JButton btnAnalyse = new JButton("Analyzing");
		btnAnalyse.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnAnalyse.setBounds(370, 10, 110, 20);
		btnAnalyse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				File[] endFile = null;
				endFile = new File(fcf2 + "\\endFile").listFiles();
				fcs2 = fcs2 + "\\endFile";
				
				if(fcs2!=null){
					//read file in reverse numerical
					Arrays.sort(endFile, new Comparator<File>() {
			            @Override
			            public int compare(File o1, File o2) {
			                int n1 = extractNumber(o1.getName());
			                int n2 = extractNumber(o2.getName());
			                return n2 - n1;
			            }
			            private int extractNumber(String name) {
			                int i = 0;
			                try {
			                    int s = name.indexOf('_')+1;
			                    int e = name.lastIndexOf('.');
			                    String number = name.substring(s, e);
			                    i = Integer.parseInt(number);
			                } catch(Exception e) {
			                    i = 0; // if filename does not match the format
			                           // then default to 0
			                }
			                return i;
			            }
			        });
					
					ArrayList<ArrayList<String>> storeMethod = new ArrayList<ArrayList<String>>();
//					ArrayList<ArrayList<String>> storeMethodCount = new ArrayList<ArrayList<String>>();
//					int countLocation = 0;
					int countMethod = 0;
					int checkIfSame = 0;
	    		    
					//read file one by one
					for (final File entry3 : endFile) {
			        	BufferedReader br = null;
			        	
						try {
							br = new BufferedReader(new FileReader(fcs2 + "\\" + entry3.getName()));
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
			    		try {			    		    
			    		    String line3 = null;
			    		    
							try {								
								line3 = br.readLine();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							//store Method Location
							while (line3 != null) {
								
								int temp=0;
								int size2 = storeMethod.size();
								if (!line3.isEmpty() && line3.contains("(Add)")) {
									
									StringBuffer sbm = new StringBuffer();
									sbm.append(line3);
									sbm.reverse();
									
									//limit the form of method
									String reverseMethod = null;
									String space=" ";
									reverseMethod = sbm.substring(sbm.indexOf("(") + 1, sbm.indexOf(space));
																	
									StringBuffer sbm2 = new StringBuffer();
									sbm2.append(reverseMethod);
									sbm2.reverse();																		
									
									if (checkIfSame == 0) {
										storeMethod.add(new ArrayList<String>());
										storeMethod.get(countMethod).add("");
									}
									int size = storeMethod.size();
//									int size2 = storeMethod.get(countMethod).size();			
																		
//									System.out.println(entry3.getName());
//									System.out.println(size);
									
									
									//check same method or not
									for (int m=0; m<size; m++) {
//										storeMethod.add(new ArrayList<String>());
//										System.out.println(storeMethod.get(m).get(0));
//										System.out.println(storeMethod.get(m).get(0));
//										System.out.println(sbm2.toString());
										
										if (storeMethod.get(m).get(0).equals(sbm2.toString()) && temp!=1) {											
//											System.out.println("***same***");
											try {
												line3 = br.readLine();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											
											if (line3.contains("Location:")) {
												StringBuffer sbl = new StringBuffer();
												sbl.append(line3);
												sbl.reverse();
																							
												String Location = sbl.substring(sbl.indexOf("(") + 1, sbl.indexOf(space));
	
												StringBuffer sbl2 = new StringBuffer();
												sbl2.append(Location);
												sbl2.reverse();
												
												storeMethod.get(m).add(sbl2.toString());
//												storeMethod.get(m).set(size2-1, sbl2.toString());
//												countLocation++;
//												System.out.println(storeMethod.get(m).set(size2-1, sbl2.toString()));
												temp = 1;
												checkIfSame = 1;
											} else {
												break;
											}
											
											
										} else if (m+1==size && temp!=1) {
//											System.out.println("***not same***");
											try {
												line3 = br.readLine();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											
											if (line3.contains("Location:")) {
												StringBuffer sbl = new StringBuffer();
												sbl.append(line3);
												sbl.reverse();
												String Location = sbl.substring(sbl.indexOf("(") + 1, sbl.indexOf(":"));

												StringBuffer sbl2 = new StringBuffer();
												sbl2.append(Location);
												sbl2.reverse();

												storeMethod.get(countMethod).set(0, sbm2.toString());
												storeMethod.get(countMethod).add(sbl2.toString());												
/* 
												int allLocation = storeMethod.get(countMethod).size();
												for (int n=0; n<allLocation; n++) {
													System.out.println(storeMethod.get(countMethod).get(allLocation));
												}
*/												countMethod++;
											}
											checkIfSame = 0;
										}
									}																		
									
									
								} else if (!line3.isEmpty() && line3.contains("(Del)") && size2>0) {
//									System.out.println(line3);
									StringBuffer sbm = new StringBuffer();
									sbm.append(line3);
									sbm.reverse();
									
									//limit the form of method
									String reverseMethod = null;
									String space=" ";
									reverseMethod = sbm.substring(sbm.indexOf("(") + 1, sbm.indexOf(space));
																	
									StringBuffer sbm2 = new StringBuffer();
									sbm2.append(reverseMethod);
									sbm2.reverse();
									
									//to find which method == DelMethod
									for (int n=0; n<size2-1; n++) {
										
										if (storeMethod.get(n).get(0).equals(sbm2.toString())) {
											
											try {
												line3 = br.readLine();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											
											if (line3.contains("Location:")) {
												StringBuffer sbl = new StringBuffer();
												sbl.append(line3);
												sbl.reverse();
																							
												String Location = sbl.substring(sbl.indexOf("(") + 1, sbl.indexOf(space));
	
												StringBuffer sbl2 = new StringBuffer();
												sbl2.append(Location);
												sbl2.reverse();
											
												int size3 = storeMethod.get(n).size();
//												System.out.println(n);
//												System.out.println(size3);
												boolean endRemove = false;
												for (int n2=0; n2<size3-1; n2++) {
//													System.out.println(storeMethod.get(n).get(n2));
													if (storeMethod.get(n).get(n2).equals(sbl2.toString()) && !endRemove) {
//														System.out.println(storeMethod.get(n).get(n2));
														storeMethod.get(n).remove(n2);														
														endRemove = true;
													}
												}
											} else {
												n = size2; //to stop loop
											}
										}
										
										
									}
									
									
								}
								
								
								
								
								
							try {
								line3 = br.readLine();
							} catch (IOException e1) {
								e1.printStackTrace();
							}

							}
//							System.out.println(entry3.getName()+" done.");
//							System.out.println(entry3.getName());
//							StoreReverse(storeMethodLocation);
			    		} finally {
			    		    try {
								br.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
			    		}

		    	    }
					
					
				for (int cM=0; cM<countMethod; cM++){
//					System.out.print("M: "+storeMethod.get(cM).get(0));
					int count=1;
					for (int cL=1; cL<storeMethod.get(cM).size(); cL++){
//						System.out.print(" L"+count+":"+storeMethod.get(cM).get(cL));
						count++;
					}
					int sol = count-1;
//					System.out.println("");
					System.out.println("M:"+storeMethod.get(cM).get(0)+" L:"+sol);
//					System.out.println("");
				}
				System.out.println("Analysis Complete.");
				}
			}			
		});	

/*		
		timeLabel = new JLabel("00:00");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        timeLabel.setBounds(370, 40, 110, 20);
*/		
		contentPane.add(btnSelect);
		contentPane.add(btnReverse);
		contentPane.add(btnParse);
		contentPane.add(btnAnalyse);
//		contentPane.add(timeLabel);
/*		
		timer = new Timer(1000, new ActionListener(){      
            public void actionPerformed(ActionEvent event) {
//            	System.out.println("time");
            	runTimeSec += 1;
            	timeLabel.setText((runTimeSec/60) + "m " + (runTimeSec%60) + "s");//分:秒
            }
        });
*/
	}
	
	public void StoreReverse(List<String> storeLine) {		
		  File saveFile = new File(fcs + "\\reverseFile\\"+storeReverseName);
		  try
		  {
			  fwriter = new FileWriter(saveFile);
			  for (String str: storeLine) {
				  fwriter.write(str);
				  fwriter.append("\n");
//				  fwriter.close();				  
			  }			  
			  fwriter.close();
//			  System.out.println(timer.isRunning());
			  System.out.println(storeReverseName + " Done.");
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
//		  tmpFile++;
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


