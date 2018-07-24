import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;


public class GraphicViewer {
	ListenerMQMsg listener;

	public static void main(String[] args) {
		ListenerMQMsg listenerMQMsg = new ListenerMQMsg();
		
		//String guid = listenerMQMsg.mqId;
		JFrame frame = new JFrame();
		JButton btnStart = new JButton("Start Listener");
		//btnStart.addActionListener(new ButtonStartListener());
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		frame.setVisible(true);
		JTextArea textArea = new JTextArea(40,70);		
		JScrollPane scroller  = new JScrollPane(textArea); 	
		textArea.setText("The listener has been started!");
		
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		
		
		panel.add(scroller);
		
		frame.getContentPane().add(BorderLayout.SOUTH, btnStart);
		frame.getContentPane().add(BorderLayout.CENTER, scroller);
	}	
	/*	
	static class ButtonStartListener implements ActionListener  {
		public void actionPerformed(ActionEvent ev) {
			listener = new ListenerMQMsg();
			try {
			listener.getMessageFromMq();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	*/	
	
}
