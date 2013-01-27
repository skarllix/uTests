package com.uTests.jms;

import java.io.IOException;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JmsServlet
 */
public class JmsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String machineIp;
	private String cfJndi;
	private String qJndi;
	private long jmsDelay;
	private long inMsgDelay;
	private String msgContent;
	private String msgType;
	private String action;
	private String result;//result to return to the user
	private QueueMngr qManager;

    public JmsServlet() {
        super();
        result=new String();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = "<link href=\"myStyle.css\" type=\"text/css\" rel=\"stylesheet\" /> ";//add a link to the style sheet page
		response.setContentType("text/html");//return each response as html page
		
		//get params from webpage
		machineIp = request.getParameter("machineIp");
		cfJndi = request.getParameter("cfJndi");
		qJndi = request.getParameter("qJndi");
		jmsDelay = Long.parseLong(request.getParameter("jmsDelay"));
		inMsgDelay = Long.parseLong(request.getParameter("inMsgDelay"));//add this as a key in the message
		msgContent = request.getParameter("msgContent");
		msgType = request.getParameter("msgType");
		action = request.getParameter("action");
		
		result += "Input was :<br>"+machineIp+" | "+cfJndi+" | "+qJndi+" | "+jmsDelay+" | "+inMsgDelay+" | "+msgContent+" | "+msgType+" | "+action+"<br>";
		result += "Result is :<br>";
		
		qManager = new QueueMngr();//new Q manager
		String connResult = qManager.createConnection(machineIp,cfJndi, qJndi,jmsDelay);//try to establish connection
		result += connResult;
		System.out.println("Ok Connected .....");
		
		if(action.equals("send")){
			if(connResult.equals("connected successfully")){
				try {
					System.out.println("Trying to send the message .....");
					result += qManager.sendMsg(msgType,msgContent,inMsgDelay);
					System.out.print("Ok send done boss .....");
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				result += connResult;
			}
			response.getWriter().print(result);
		}
		if(action.equals("brose")){
			try {
				qManager.browseQ(msgType);
			} catch (JMSException e) {
				System.out.println("Browse queue failed\n");
			}
			response.getWriter().print(result);
		}
		if(action.equals("clear")){
			result +="Clear";
			response.getWriter().print(result);
		}
	}

}
