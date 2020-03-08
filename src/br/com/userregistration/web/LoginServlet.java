package br.com.userregistration.web;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.userregistration.dao.UserDao;
import br.com.userregistration.exception.InvalidLoginException;
import br.com.userregistration.model.User;

@WebServlet(name = "login", urlPatterns = {"/index", "/login", "/"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao.getInstance();
		String action = request.getServletPath();
		
		if(action.equals("/login")) {
			System.out.println("/login");
			
			try {

				login(request, response);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				showLoginPage(request, response);
			}
		
		} else if (action.equals("/logged") || action.equals("/")) {
			
			showLoginPage(request, response);
		} else if(action.equals("/register")) {
			
			showRegisterPage(request, response);
		}
	}

	private void showRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("register");
		
	}

	private void showLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
		rd.forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvalidLoginException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try{
			
			User user = UserDao.getInstance().findUserLogin(email, password);
			
			HttpSession session = request.getSession();
			session.setAttribute("name", user.getName());
			
			response.sendRedirect("logged");
			
		} catch (NoResultException nre) {
			response.setStatus(400);

			throw new InvalidLoginException("E-mail e/ou senha inválidos!");
		}
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
