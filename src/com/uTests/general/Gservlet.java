package com.uTests.general;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Gservlet
 */
public class Gservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int memKB;
	private String meter;
	private String action;
	private Vector v;
	private int freed;
	private Runtime rt;

	public Gservlet() {
		super();
		action = new String();
		meter = new String();
		v = new Vector();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		memKB = 0;
		if (request.getParameter("Allocate") != null) {
			try {
			memKB = Integer.parseInt(request.getParameter("memKB"));
			}
			catch (NumberFormatException  numExp) {
				response.getWriter().println("Are you sure about the value u've inserted?!\nseems not ok !!\n");
			}
			if (memKB > 0 && memKB < 900) {
				meter = request.getParameter("meter");
				action = "Allocate";
				if (meter.equals("kb")) {
					byte b[] = new byte[memKB * 1024];
					v.add(b);
				}
				if (meter.equals("mb")) {
					byte b[] = new byte[memKB * 1024 * 1024];
					v.add(b);
				}

				response.getWriter().println(
						"ACTION = " + action + " | Meter : " + meter
								+ " \nYou've consumed  " + memKB
								+ "  from the memory"
								+ "\nVictor capacity now : " + v.size());
			}
			else
				response.getWriter().println("memory consume value(x) could be only\n900 > x > 0");
		}

		if (request.getParameter("Free") != null) {
			action = "Free";
			v.clear();
			v.removeAllElements();
			rt = Runtime.getRuntime();
			freed = (int) (rt.freeMemory() / 1024);
			System.gc();// asking nicly to collect the free memory
			response.getWriter().print("ACTION = " + action + "\n" + "free memory: " + freed +" (KB)"
							+ "\nVictor capacity now : " + v.size());
		}

	}

}