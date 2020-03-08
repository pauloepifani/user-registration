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

import br.com.userregistration.dao.UserDao;
import br.com.userregistration.exception.EmailAlreadyExistException;
import br.com.userregistration.exception.NullDataException;
import br.com.userregistration.model.PhoneNumber;
import br.com.userregistration.model.User;

@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			User newUser = new User();
			prepareUserData(request, newUser);
			UserDao.getInstance().saveUser(newUser);
			response.setStatus(200);
			
		} catch (EmailAlreadyExistException eaee) {
			
			response.setStatus(400);
		} catch (NullDataException nde) {

			response.setStatus(400);
		}
		
		
		
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
		
		if(name == null || name.equals("") || email == null || email.equals("")) {
			throw new NullDataException("Dados inválidos!");
		}
		
		try {
			
			UserDao.getInstance().findUserByEmail(email, id);
			
		} catch (NoResultException nre) {
			
			String password = request.getParameter("password");
			
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
				newUser.setPassword(password);
				newUser.setPhoneNumbers(newPhoneList);
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/register.jsp");
		rd.forward(request, response);
	}

}
