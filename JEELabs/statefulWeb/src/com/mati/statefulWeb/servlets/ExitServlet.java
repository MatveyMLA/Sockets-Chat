package com.mati.statefulWeb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mati.stateful.INumbers;

/**
 * Servlet implementation class ExitServlet
 */
public class ExitServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter respWriter = response.getWriter();

		Object lookup = null;
		try {
			
			InitialContext ict = new InitialContext();
			lookup = ict.lookup("Nums/local");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object numbersManipulatorStab = PortableRemoteObject.narrow(lookup, com.mati.stateful.INumbers.class);

		if(numbersManipulatorStab!=null){
			INumbers numbersManipulator = (INumbers)numbersManipulatorStab;
			numbersManipulator.exit();
		}
		respWriter.append("<h1>Buy</h1>");
	}
}
