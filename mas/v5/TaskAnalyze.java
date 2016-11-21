package mas.v5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TaskAnalyze extends UI{
	
	public void analyze() {
	    
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
//						int size2 = storeMethod.get(countMethod).size();			
															
//						System.out.println(entry3.getName());
//						System.out.println(size);
						
						
						//check same method or not
						for (int m=0; m<size; m++) {
//							storeMethod.add(new ArrayList<String>());
//							System.out.println(storeMethod.get(m).get(0));
//							System.out.println(storeMethod.get(m).get(0));
//							System.out.println(sbm2.toString());
							
							if (storeMethod.get(m).get(0).equals(sbm2.toString()) && temp!=1) {											
//								System.out.println("***same***");
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
//									storeMethod.get(m).set(size2-1, sbl2.toString());
//									countLocation++;
//									System.out.println(storeMethod.get(m).set(size2-1, sbl2.toString()));
									temp = 1;
									checkIfSame = 1;
								} else {
									break;
								}
								
								
							} else if (m+1==size && temp!=1) {
//								System.out.println("***not same***");
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
//						System.out.println(line3);
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
//									System.out.println(n);
//									System.out.println(size3);
									boolean endRemove = false;
									for (int n2=0; n2<size3-1; n2++) {
//										System.out.println(storeMethod.get(n).get(n2));
										if (storeMethod.get(n).get(n2).equals(sbl2.toString()) && !endRemove) {
//											System.out.println(storeMethod.get(n).get(n2));
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
    		} finally {
    		    try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
    		}
	    }
	}
	
	
}
