package listeners.skt;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.jms.*;
import javax.jms.IllegalStateException;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ibm.jms.JMSBytesMessage;
import com.ibm.mq.MQC;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.jms.*;
import com.ibm.msg.client.wmq.WMQConstants;


public class ListenerMQMsg2 {	
	public static void main(String args[]) throws InterruptedException {
		ListenerMQMsg2 listener = new ListenerMQMsg2();
		try {
			listener.getMessageFromMq();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String dateDb;
	static String mqId;
	static String envId;
	
	
	
	public void getMessageFromMq() throws InterruptedException, IOException {		
		Properties prop = new Properties();
		prop.load(new FileInputStream(".\\prop.prop"));
		final String host = prop.getProperty("host");
		final String qManager = prop.getProperty("qManager");
		final int port = Integer.parseInt(prop.getProperty("port"));
		final String channel = prop.getProperty("channel");
		final String queue = prop.getProperty("queue");
		//final String host = "10.22.93.120";
//		final String qManager = "RU.FTS";
//		final int port = 1409;
//		final String channel = "SKT.READ";
//		final String queue = "DEAD.LETTER.QUEUE";
		
		
		while(true) {
		try {						
			MQConnectionFactory mqFactory = new MQConnectionFactory();
			mqFactory.setHostName(host);
			mqFactory.setPort(port);
			mqFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
			mqFactory.setQueueManager(qManager);
			mqFactory.setChannel(channel);
			
			MQQueue mqDestination = new MQQueue(queue);
			Connection connection = mqFactory.createConnection();
			connection.start();
			Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			
			
			
			MessageConsumer lastMsg = session.createConsumer(mqDestination);
			//TextMessage bytesTextMsg = (TextMessage) lastMsg.receiveNoWait();
			JMSBytesMessage bytesTextMsg = (JMSBytesMessage) lastMsg.receiveNoWait();
			
			one:{ // метка го то
			if (bytesTextMsg != null) {
			byte[] byteData = null;
			byteData = new byte[(int) bytesTextMsg.getBodyLength()];
			bytesTextMsg.readBytes(byteData);
			bytesTextMsg.reset();
			String	textMsgString = new String (byteData);
			
			System.out.println(textMsgString);
			
			if (textMsgString.contains("EnvelopeID>")) {
				envId = finderEnvId(textMsgString);
				System.out.println("envId = " + envId);
			} else if (textMsgString.contains("MessageID>")) {
				mqId = finderMQ(textMsgString);
				System.out.println("mqId = " + mqId);
			} else {
				System.out.println("mqId and envId not found!");
			}
			
			
//			try {
//				mqId = finderMQ(textMsgString);
//				System.out.println("mqId = " + mqId);
//			} catch (Exception e) {
//				envId = finderEnvId(textMsgString);
//				System.out.println("envId = " + envId);
//			}
			//date
			SimpleDateFormat formatForDb = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			dateDb = formatForDb.format(new Date());
			//Thread.sleep(1000);
			msgUpdateDb(textMsgString);							
			} else {
				break  one;	//go to  one
			}
			}
			
			session.commit();
			connection.close();
		} catch (JMSException jmse) {
			jmse.printStackTrace();
		}
		catch (NullPointerException npe) {
			
		}
		
		}
		
	}
	
	public void msgUpdateDb(String line) {
		//Config_JDBC jdbc = new Config_JDBC();
		UpdateJDBC updater = new UpdateJDBC();
		
		try {
			try {
				updater.updateDB(line);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String finderMQ(String line) throws IllegalStateException {
		java.util.regex.Pattern pattern =  java.util.regex.Pattern.compile("MessageID>(.+?)</MessageID");
		Matcher matcher = pattern.matcher(line);
		matcher.find();		
		String mqId = matcher.group(1);	
		
		return mqId;
	}
	public String finderEnvId(String line) throws IllegalStateException {
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("EnvelopeID>(.+?)</");
		Matcher matcher = pattern.matcher(line);
		matcher.find();
		String envID = matcher.group(1);
		return envID;
	}
	
	
	
	public String replacerMesWithEnv(String xml) {
		java.util.regex.Pattern pat1 = java.util.regex.Pattern.compile("MessageID>.*</MessageID");
		Matcher match1 = pat1.matcher(xml);		
		String newTemplate = match1.replaceAll("EnvelopeID>.*</EnvelopeID");
		return newTemplate;
	}
	
}
