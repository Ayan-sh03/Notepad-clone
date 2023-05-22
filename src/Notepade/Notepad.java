package Notepade;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Notepad extends JFrame implements ActionListener ,WindowListener {
	JTextArea jta = new JTextArea();
	File fnameContainer ;
	public Notepad() {
		Font fnt = new Font("Arial",Font.PLAIN,15);
		Container con = getContentPane();
		JMenuBar jmb = new JMenuBar();
		JMenu jmfile  = new JMenu("File");
		JMenu jmEdit  = new JMenu("Edit");
		JMenu jmHelp  = new JMenu("Help");
		
		con.setLayout(new BorderLayout());
		JScrollPane sbrText = new JScrollPane(jta);
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sbrText.setVisible(true);
		jta.setFont(fnt);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		
		
		con.add(sbrText);
		
		createMenuItem(jmfile,"New");
		createMenuItem(jmfile,"Open");
		createMenuItem(jmfile,"Help");
		createMenuItem(jmfile,"Save");
		jmfile.addSeparator();
		createMenuItem(jmfile,"Exit");
		
		createMenuItem(jmEdit,"Cut");
		createMenuItem(jmEdit,"Copy");
		createMenuItem(jmEdit,"Paste");

		
		createMenuItem(jmHelp,"About Notepad");
		
		jmb.add(jmfile);
		jmb.add(jmEdit);
		jmb.add(jmHelp);
		
		  jmb.add(jmfile);
	        jmb.add(jmEdit);
	        jmb.add(jmHelp);

	        setJMenuBar(jmb);
	        setIconImage(Toolkit.getDefaultToolkit().getImage("notpad.gif"));
	        addWindowListener(this);
	        setSize(500, 500);
	        setTitle("Untitled.txt - Notepad");
	        setVisible(true);

	        // Set dark background
	        jta.setBackground(Color.BLACK);
	        jta.setForeground(Color.WHITE);
	        jmb.setBackground(Color.DARK_GRAY);
	        jmb.setForeground(Color.WHITE);
	        jmfile.setForeground(Color.WHITE);
	        jmEdit.setForeground(Color.WHITE);
	        jmHelp.setForeground(Color.WHITE);
		
		
		setJMenuBar(jmb);
		setIconImage(Toolkit.getDefaultToolkit().getImage("notpad.gif"));
		addWindowListener(this);
		setSize(500,500);
		setTitle("Untitled.txt - Notepad");
		setVisible(true);
		
		
	}
	public void createMenuItem(JMenu jm, String txt) {
			JMenuItem jmi = new JMenuItem(txt);
			jmi.addActionListener(this);
			jm.add(jmi);
			
	}
	
	 public void actionPerformed(ActionEvent e) {
	        JFileChooser jfc = new JFileChooser();

	        if (e.getActionCommand().equals("New")) {
	            this.setTitle("Untitled.txt - Notepad");
	            jta.setText("");
	            fnameContainer = null;
	        } else if (e.getActionCommand().equals("Open")) {
	            int ret = jfc.showDialog(null, "Open");
	            if (ret == JFileChooser.APPROVE_OPTION) {
	                try {
	                    File fyl = jfc.getSelectedFile();
	                    OpenFile(fyl.getAbsolutePath());
	                    this.setTitle(fyl.getName() + " - Notepad");
	                    fnameContainer = fyl;
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        } else if (e.getActionCommand().equals("Save")) {
	            if (fnameContainer != null) {
	                jfc.setCurrentDirectory(fnameContainer);
	                jfc.setSelectedFile(fnameContainer);
	            }

	            int ret = jfc.showSaveDialog(null);
	            if (ret == JFileChooser.APPROVE_OPTION) {
	                try {
	                    File fyl = jfc.getSelectedFile();
	                    SaveFile(fyl.getAbsolutePath());
	                    this.setTitle(fyl.getName() + " - Notepad");
	                    fnameContainer = fyl;
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        } else if (e.getActionCommand().equals("Exit")) {
	            Exiting();
	        } else if (e.getActionCommand().equals("Copy")) {
	            jta.copy();
	        } else if (e.getActionCommand().equals("Paste")) {
	            jta.paste();
	        } else if (e.getActionCommand().equals("Cut")) {
	            jta.cut();
	        }
		else if(e.getActionCommand().equals("About Notepad")) {
			JOptionPane.showMessageDialog(this, "Created by Ayan Sheikh","Notepad",JOptionPane.INFORMATION_MESSAGE);
			
			
		}
	        
		
	}
	
	public void SaveFile(String fname) throws IOException {
	    setCursor(new Cursor(Cursor.WAIT_CURSOR));
	    try (DataOutputStream o = new DataOutputStream(new FileOutputStream(fname))) {
	        o.writeBytes(jta.getText());
	    }
	    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void OpenFile(String fname) throws IOException {
	    try (BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)))) {
	        StringBuilder sb = new StringBuilder();
	        String l;
	        while ((l = d.readLine()) != null) {
	            sb.append(l).append("\r\n");
	        }
	        jta.setText(sb.toString());
	    }
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
	    Exiting();
	}

	public void Exiting() {
	    System.exit(0);
	}

	public static void main(String[] args) {
		 


		
		
	    new Notepad();
	}
	
}
