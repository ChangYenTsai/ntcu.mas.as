package mas.v5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskParse extends UI{

	public ArrayList<ArrayList<String>> parsing(ArrayList<ArrayList<String>> storeWholeSentence) {
		
		File reverseFile = new File(fcf + "\\reverseFile");
		
		storeWholeSentence.add(new ArrayList<String>());
		storeWholeSentence.get(0).add("");
		
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
				
				
				
//				storeMethod.add(line2);
//				line2 = br.readLine();
				
				while (line2 != null) {
					String[] tokens = line2.split("\\s+|\\.");
					//filter method name
					if (!line2.isEmpty() && line2.charAt(0)=='+'){
						for (String token: tokens) {
								if (token.contains("(") &&
									token.contains(");") &&
									!token.contains("+") &&
									!token.contains("-") &&
									!token.contains("*") &&
									!token.contains("/") &&
									!token.contains("|") &&
									!token.contains("&") &&
									!token.contains("%") &&
									!token.contains("!") &&
									!token.contains("?") &&
									!token.contains("get") &&
									!token.contains("set") &&
									!token.contains("super") &&
									!token.contains("execute") &&
									!token.contains("println") &&
									!token.contains("length") &&
									!token.contains("return") &&
									!Character.isUpperCase(token.charAt(0))) {
									
//									System.out.println(storeWholeSentence.size());
									for (int i=0; i<storeWholeSentence.size(); i++) {
										if (token.equals(storeWholeSentence.get(i).get(0))) {
											line2 = line2.replaceFirst("\\+", "");
											line2 = line2.replaceAll("^\\s+|\\s+$", "");
											storeWholeSentence.get(i).add(line2);
											break;
										} else if (i==storeWholeSentence.size()-1){
											storeWholeSentence.add(new ArrayList<String>());
											storeWholeSentence.get(i+1).add("");
//											token.replaceFirst("+", "");
//											token.replaceAll("^\\s+|\\s+$", "");
											line2 = line2.replaceFirst("\\+", "");
											line2 = line2.replaceAll("^\\s+|\\s+$", "");
											
											storeWholeSentence.get(i).set(0, token);
											storeWholeSentence.get(i).add(line2);
											break;
										} else {
											continue;
										}
									}
									
									int state = 1;
										
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
												if (token2.contains("(") && token. contains(")")) {    
													storeMethod.add("(Add)M: " + token);
													storeMethod.add("Location: " + token2);
													storeMethod.add(" ");
//													System.out.println(", Location: " + templine);
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
							if (token.contains("(") &&
								token.contains(");") &&
								!token.contains("+") &&
								!token.contains("-") &&
								!token.contains("*") &&
								!token.contains("/") &&
								!token.contains("|") &&
								!token.contains("&") &&
								!token.contains("%") &&
								!token.contains("!") &&
								!token.contains("?") &&
								!token.contains("get") &&
								!token.contains("set") &&
								!token.contains("super") &&
								!token.contains("execute") &&
								!token.contains("println") &&
								!token.contains("length") &&
								!token.contains("return") &&
								!Character.isUpperCase(token.charAt(0))) {
									int state = 1;
//									storeMethod.add("(Del)M: " + token);
//									System.out.print("M: " + line2);
									
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
												if (token2.contains("(") && token.contains(")")) {    
													storeMethod.add("(Del)M: " + token);
													storeMethod.add("Location: " + token2);
													storeMethod.add(" ");
//													System.out.println(", Location: " + templine);
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
		System.out.println("Parsing done.");
/*		
		for (int i=0; i<storeWholeSentence.size(); i++) {
			System.out.println(storeWholeSentence.get(i));
		}
*/		
		return storeWholeSentence;
	}
}
