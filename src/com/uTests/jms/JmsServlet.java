package com.uTests.jms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private List<String> browseResult;

    public JmsServlet() {
        super();
        result=new String();
        browseResult = new ArrayList<String>();
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
		
		if(connResult.equals("connected successfully"))
		{
			if(action.equals("send")){
					try {
						result += qManager.sendMsg(msgType,msgContent,inMsgDelay);
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						result += "Error with sending message :<br>"+e.getMessage();
					}finally{
						response.getWriter().print(result);
					}
				}//end send
			
			if(action.equals("browse")){
				try {
					browseResult = qManager.browseQ(msgType);
					//show build a table to return to the UI
					for(String msg : browseResult){
						result += msg + "<br />";
					}
				} catch (JMSException e) {
					System.out.println("Browse queue failed\n");
				}
				response.getWriter().print(result);
			}//end browse
			
			if(action.equals("clear")){
				try {
					result += qManager.clearQ();
				} catch (JMSException e) {
					result += "Error in clearing the queue : <br>"+ e.getMessage();
				}finally{
				response.getWriter().print(result);}
			}//end clear
		} else {
			result += connResult + "<br />";
			response.getWriter().print(result);
		}	
	}
}
