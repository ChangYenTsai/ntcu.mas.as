package mas.v5;

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
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class UI extends JFrame{	
	/**
	 * v5
	 */
	private JPanel contentPane;
	static protected String filePath;
	static protected File selectedFile = null;
	
	private FileWriter fwriter;

	static protected String storeEndName = null;
	static protected String storeReverseName = null;
		
	protected ArrayList<ArrayList<String>> storeMethod = new ArrayList<ArrayList<String>>();
	protected ArrayList<ArrayList<String>> storeAnalyzedMethod = new ArrayList<ArrayList<String>>();
	protected ArrayList<ArrayList<String>> storeWholeSentence = new ArrayList<ArrayList<String>>();
	static protected int countMethod;
	protected int checkIfSame = 0;
	
//	private JTabbedPane tp;
	protected JTextArea textArea;
	private JScrollPane scrollPane;
	
	private TaskSelect taskSelect;
	private TaskReverse taskReverse;
	private TaskParse taskParse;
	private TaskAnalyze taskAnalyze;
	private TaskOutput taskOutput;
	
	static protected String fcs = new String(System.getProperty("user.dir"));
	static protected File fcf = new File(System.getProperty("user.dir"));	
	static protected String fcs2 = new String(System.getProperty("user.dir"));
	static protected File fcf2 = new File(System.getProperty("user.dir"));
	private File[] endFile = new File(fcf2 + "\\endFile").listFiles();
	
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
		setBounds(100, 100, 510, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSelect = new JButton("Select Folder");
		btnSelect.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnSelect.setBounds(10, 10, 110, 20);
		btnSelect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {				
				taskSelect = new TaskSelect();
				taskSelect.selectFolder();		        
		    }
		});
		
		
		JButton btnParse = new JButton("Parsing");
		btnParse.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnParse.setBounds(130, 10, 110, 20);
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Reversing start
				if(filePath!=null){
					taskReverse = new TaskReverse();
					taskReverse.reverseContent();
					
				} else {
					JOptionPane.showMessageDialog(UI.this,
							"Please select package path!",
						    "No path error",
						    JOptionPane.ERROR_MESSAGE);
				}
				
				//Parsing start
				fcs = fcs + "\\reverseFile";
				
				if(fcs!=null){			
					taskParse = new TaskParse();
					storeWholeSentence = taskParse.parsing(storeWholeSentence);
				} else {
					JOptionPane.showMessageDialog(UI.this,
							"Please select package path!",
						    "No path error",
						    JOptionPane.ERROR_MESSAGE);
				}
				
				//Analyzing start
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
					
					//analyzing
					taskAnalyze = new TaskAnalyze();
					storeAnalyzedMethod = taskAnalyze.analyze(storeMethod);
					
					//count location number
					taskOutput = new TaskOutput();
					taskOutput.countAndOutput(storeAnalyzedMethod);
				} else {
					JOptionPane.showMessageDialog(UI.this,
							"Please select package path!",
						    "No path error",
						    JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
/*		
		JButton btnAnalyse = new JButton("Analyzing");
		btnAnalyse.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnAnalyse.setBounds(250, 10, 110, 20);
		btnAnalyse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					
					//analyzing
					taskAnalyze = new TaskAnalyze();
					storeAnalyzedMethod = taskAnalyze.analyze(storeMethod);
					
					//count location number
					taskOutput = new TaskOutput();
					taskOutput.countAndOutput(storeAnalyzedMethod);				
				}
			}			
		});
*/		
		
		
		

/*		
		timeLabel = new JLabel("00:00");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        timeLabel.setBounds(370, 40, 110, 20);
*/		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
//		textArea.setBounds(10, 70, 470, 400);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 70, 470, 400);
		
/*		
		tp = new JTabbedPane();
        tp.addTab("Result", createJScrollPane1(textArea));
        tp.setBounds(10, 70, 470, 400);
*/		
		
		contentPane.add(btnSelect);
		contentPane.add(btnParse);
//		contentPane.add(btnAnalyse);
		contentPane.add(scrollPane);
//		contentPane.add(tp);
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
/*	
	private JScrollPane createJScrollPane1(JTextArea textArea){
        JScrollPane scr = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);// Add your text area to scroll pane 
//        textArea.setBounds(10, 70, 470, 400);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scr.setBounds(10, 70, 470, 400);// You have to set bounds for all the controls and containers in case of null layout
        
        return scr;
	}
*/	
	public void StoreReverse(List<String> storeLine) {		
		  File saveFile = new File(fcs + "\\reverseFile\\"+storeReverseName);
		  try
		  {
			  fwriter = new FileWriter(saveFile);
			  for (String str: storeLine) {
				  fwriter.write(str);
				  fwriter.append("\n");				  
			  }			  
			  fwriter.close();
			  System.out.println(storeReverseName + " Done.");
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
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
			  }			  
			  fwriter.close();
			  System.out.println(storeEndName + " Done.");
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	  }
/*	
	public void FileCatch() {
		File fc = new File(UI.class.getResource("UI.class").toString());
        System.out.println(UI.class.getResource("UI.class").toString());
        System.out.println(fc);
	}
*/
	
	
}


