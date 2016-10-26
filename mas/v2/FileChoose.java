package mas.v2;

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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FileChoose extends JFrame {
	
	/**
	 * v0.1
	 */
//	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String filePath;
	private File selectedFile = null;
	private FileWriter fwriter;
	private int countFile = 1;
	
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
	
	public FileChoose () {
		setTitle("MAS v0.1");
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
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		        	//get the path
		        	selectedFile = fileChooser.getSelectedFile();
		        	filePath = selectedFile.getAbsolutePath();        	
		    	    }		        	
		        }
		});
		
		
		JButton btnParse = new JButton("FunctionCut");
		btnParse.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnParse.setBounds(10, 40, 110, 20);
		btnParse.addActionListener(new ActionListener() {
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
			    		    int symbol = 0;
			    		    
							try {								
								line = br.readLine();														
							} catch (IOException e1) {
								e1.printStackTrace();
							}		
							
							List<String> storeLine = new ArrayList<String>();
							
							while (line != null) {
								if (!line.isEmpty() &&
									line.contains("class") == false &&
									(line.contains("public") == true ||
									line.contains("private") == true ||
									line.contains("protected") == true)) {
									storeLine.add(line);
									System.out.println(line);
									while (symbol != 1) {
										if (line.contains("{") == true) {
											symbol++;
											try {
												line = br.readLine();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											storeLine.add(line);
										} else {
											try {
												line = br.readLine();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											storeLine.add(line);
										}
									}
									
									while (symbol != 0) {
										try {
											line = br.readLine();
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										storeLine.add(line);
										if (line.contains("{") == true) {
											symbol++;
										}
										if (line.contains("}") == true) {
											symbol--;
										}
									}
								} else {
									try {
										line = br.readLine();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							}
							CutFile(storeLine);  
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
		});
		contentPane.add(btnSelect);
		contentPane.add(btnParse);
	}
	
	public void CutFile(List<String> storeLine)
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
	
	
	
}
