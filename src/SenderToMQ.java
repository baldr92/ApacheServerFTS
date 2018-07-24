
import java.beans.PropertyVetoException;
import java.rmi.Naming;
import java.sql.SQLException;
import java.text.ParseException;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.omg.PortableServer.ServantManagerOperations;

import com.ibm.mq.headers.MQMD;
import com.ibm.mq.jms.*;
import com.ibm.msg.client.wmq.WMQConstants;


public class SenderToMQ {
	
	protected String target_queue = "KPSPP.INCOME";
	
	public void createQueue(XMLCreator xmlCreator) throws ParseException {
		
		try {
			

		
		Context context = (Context) new InitialContext().lookup("java:comp/env");
		MQConnectionFactory qFactory = (MQConnectionFactory) context.lookup("jms/FTS.INCOME");
		
		Connection conFromFactory = qFactory.createConnection();
		Session sess = conFromFactory.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
			

			
		MQQueue mqQueue = (MQQueue) context.lookup("jms/"+ target_queue);
		mqQueue.setTargetClient(WMQConstants.WMQ_CLIENT_NONJMS_MQ);		
		
		MessageProducer msgPrd = sess.createProducer(mqQueue);
		
		
		Message msg = sess.createTextMessage(xmlCreator.modifyXML);
		
		msgPrd.send(msg);
		//System.out.println(randomMsg);
		sess.close();
		configDb(xmlCreator);
		
		} catch (JMSException  | NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	public void configDb(XMLCreator xmlCreator) throws ParseException {
		Config_JDBC jdbc = new Config_JDBC();
		try {
			jdbc.insertToDb(xmlCreator);
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
	
	public void sleeper(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
}

