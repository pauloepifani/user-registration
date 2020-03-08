package br.com.userregistration.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.userregistration.dao.PhoneDao;
import br.com.userregistration.dao.UserDao;
import br.com.userregistration.exception.EmailAlreadyExistException;
import br.com.userregistration.exception.NullDataException;
import br.com.userregistration.model.PhoneNumber;
import br.com.userregistration.model.User;

@WebServlet(name = "logged", urlPatterns = "/logged/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("name");
		
		if (name != null) {
			
			String action;
			if(request.getRequestURI() != null) {
				String[] endPoint = request.getRequestURI().split("/");
				action = "/" + endPoint[endPoint.length - 1];
			} else {
				action = "/logged";
			}
			
			switch (action) {
			
			case "/new":
				showNewForm(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/logout":
				response.setStatus(200);
				session=request.getSession(false);
				session.invalidate();
				break;
			default:
				showAllUsers(request, response);
				break;
			}
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
			rd.forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User newUser = new User();
		
		try {
			prepareUserData(request, newUser);
		} catch (EmailAlreadyExistException e) {
			
			response.setStatus(400);
			showNewForm(request, response);
		} catch (NullDataException nde) {

			response.setStatus(400);
			showEditForm(request, response);
		}
		
		UserDao.getInstance().saveUser(newUser);
		
		response.setStatus(200);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = UserDao.getInstance().findUserById(id);
		
		try {
			prepareUserData(request, existingUser);
		} catch (EmailAlreadyExistException e) {
			
			response.setStatus(400);
			showEditForm(request, response);
			
		} catch (NullDataException nde) {
			
			response.setStatus(400);
			showEditForm(request, response);
		}
		
		PhoneDao.getInstance().deletePhonesByUserId(id);
		UserDao.getInstance().updateUser(existingUser);
		
		response.setStatus(200);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao.getInstance().deleteUser(id);
		
		response.setStatus(200);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User userToUpdate = UserDao.getInstance().findUserById(id);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/update-user.jsp");
		request.setAttribute("user", userToUpdate);
		rd.forward(request, response);
		
	}

	private void showAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = UserDao.getInstance().findAllUsers();
		request.setAttribute("Users", users);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/new-user-list.jsp");
		rd.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/new-user.jsp");
		rd.forward(request, response);
	}
	
	private void prepareUserData(HttpServletRequest request, User newUser) throws EmailAlreadyExistException, NullDataException {
		
		int id;
		if(request.getParameter("id") != null) {
			
			id = Integer.parseInt(request.getParameter("id"));
		} else {
			
			id = 0;
		}
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		if(name == null || name.equals("")|| email == null || email.equals("")) {
			throw new NullDataException("Dados inválidos!");
		}
		
		try {
			
			UserDao.getInstance().findUserByEmail(email, id);
			
		} catch (NoResultException nre) {
			
			List<PhoneNumber> newPhoneList = new ArrayList<PhoneNumber>();
			
			try {
				
				String[] dddArray = request.getParameterValues("ddd");
				String[] numberArray = request.getParameterValues("number");
				String[] phoneTypeArray = request.getParameterValues("type");
				
				int index = 0;
				for(String ddd : dddArray) {
					
					PhoneNumber phone = new PhoneNumber();
					phone.setDdd(ddd);
					phone.setNumber(numberArray[index]);
					phone.setType(phoneTypeArray[index]);
					phone.setUser(newUser);
					newPhoneList.add(phone);
					
					index++;
				}
				
			} catch (NullPointerException npe) {
				
			} finally {
				
				newUser.setName(name);
				newUser.setEmail(email);
				newUser.setPhoneNumbers(newPhoneList);
			}
		}
	}
		

}
