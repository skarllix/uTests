//http://docs.oracle.com/cd/E15051_01/wls/docs103/jndi/jndi.html
package com.uTests.jms;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/*
 * This Class provide functionality for managing a JMS Queue
 * It can browse , send and clear a queue
 */
public class QueueMngr {
	private InitialContext jndiCntxt;
	private QueueConnectionFactory qConnFctory;//maybe the ConnectionFactory var is better
	private QueueConnection qConnection;
	private QueueSession qSession;
	private Queue queue;
	private QueueSender qSender;
	private QueueBrowser qBrowser;
	private QueueReceiver qReceiver;
	private List<String> result;
	private Hashtable<String, String> env ;

	//default c'tor
	public QueueMngr(){
		env = new Hashtable();//env = new Hashtable<String, String>();
		result = new ArrayList<String>();
	}
	
	// establish a connection to the messaging queue
	public String createConnection(String machineIP,String cfName,String qName,long jmsDelay){
		if(jmsDelay > 0)
		{
			try {
				Thread.sleep(jmsDelay);
			} catch (InterruptedException e) {
				return "Coudn't make a jmsDelay , please insert value 0 to ignore delay";
			}
		}
		try{
			env.put( Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory" );//specified for weblogic
			env.put(Context.PROVIDER_URL, "t3://"+machineIP+":7001");
			jndiCntxt =  new InitialContext(env);//get inital context from the specified machine
			qConnFctory = (QueueConnectionFactory) jndiCntxt.lookup(cfName);//search for the CF
			queue = (Queue) jndiCntxt.lookup(qName);//now for the Queue
		}catch(NamingException jndiNameExc){
			return "Ops, something went wrong : JNDI naming exception" + jndiNameExc.getMessage();
		}
			
		try{
			//connecting to the queue
			qConnection = qConnFctory.createQueueConnection();//this could throw JmsException
			qSession = qConnection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);//new session
			qSender = qSession.createSender(queue);//creating new queue sender
			qBrowser = qSession.createBrowser(queue);// new browser
			qReceiver = qSession.createReceiver(queue);//new receiver
		}catch (JMSException jmsExc){
			return "Ops, something went wrong : jms exception" + jmsExc.getMessage();
		}
		return "connected successfully";
	}
	
	// send messages to the queue
	public String sendMsg(String msgType,String msgContent,long inMsgDelay) throws JMSException
	{
		qConnection.start();//start the connection
		result.clear();

		if(msgType.equals("textMsg"))
		{
			TextMessage msg;
			try {
				msg = qSession.createTextMessage();
				msg.setText(msgContent);
				qSender.send(msg);
				result.add("Message send successfully");
			} catch (JMSException e) {
				result.add("Couldn't create new message");
			}
		}
		
		if(msgType.equals("currentlyNotHandeled"))//this should handle Map messages equals("mapMsg")
		{
			MapMessage msg;
			try {
				msg = qSession.createMapMessage();
				msg.setString("MsgContent", msgContent);//key+val
				qSender.send(msg);
				result.add("Message send successfully");
			} catch (JMSException e) {
				result.add("Couldn't create new message");
			}
		}else
			result.add("it's not map or txt msg");
		
		qConnection.close();
		return result.get(0);
	}
	
	// Browse queue messages
	public List<String> browseQ(String msgType) throws JMSException{
		int i=0;
		qConnection.start();//start the connection
		result.clear();
		
		Enumeration enumMsgs = qBrowser.getEnumeration();
		
		while(enumMsgs.hasMoreElements()){
			TextMessage msg = (TextMessage) enumMsgs.nextElement();
			result.add(++i + " : " + msg.getText()); // adding the msg content to the list
		}
		qConnection.close();
		return result;
	}
	
	
	public String clearQ() throws JMSException{
		result.clear();
		qConnection.start();
		Message m;
		do{
			m = qReceiver.receive(1);
		}while(m != null);
			
		qConnection.close();
		result.add("All Cleared");
		return result.get(0);
	}
}
