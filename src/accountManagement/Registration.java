package accountManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("first_name");
		String middleName = request.getParameter("middle_name");
		String lastName = request.getParameter("last_name");
		String dateOfBirth = request.getParameter("date_of_birth");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String mobileNumber = request.getParameter("contact");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String sql = "insert into USERDB(firstName, middleName, lastName, dateOfBirth, gender, address, mobileNumber, email, password) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql1 = "insert into USERACCOUNT(email, balance) values(?,?)";
		PreparedStatement pst, pst1;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
					"Ab99636760");
			pst = con.prepareStatement(sql);
			pst.setString(1, firstName);
			pst.setString(2, middleName);
			pst.setString(3, lastName);
			pst.setString(4, dateOfBirth);
			pst.setString(5, gender);
			pst.setString(6, address);
			pst.setString(7, mobileNumber);
			pst.setString(8, email);
			pst.setString(9, password);
			pst.executeUpdate();
			
			pst1 = con.prepareStatement(sql1);
			pst1.setString(1, email);
			pst1.setFloat(2, 10000);
			pst1.executeUpdate();
			
			response.sendRedirect("Login.jsp");
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
