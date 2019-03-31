package accountManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
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
		String sql = "select * from USERDB WHERE email= ?";
		String sql1 = "select ACCNUM FROM USERACCOUNT WHERE EMAIL = ?";
		PreparedStatement pst, pst1;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
					"Ab99636760");
			pst = conn.prepareStatement(sql);
			pst1 = conn.prepareStatement(sql1);
			String userId = (String) request.getSession().getAttribute("username");
			pst.setString(1, userId);
			pst1.setString(1, userId);
			ResultSet rs, rs1;
			rs = pst.executeQuery();
			rs1 = pst1.executeQuery();
			if (rs.next() && rs1.next()) {
				PrintWriter out = response.getWriter();
				out.println("name:" + rs.getString("firstName") + rs.getString("middleName") + rs.getString("lastName")
						+ "<br>\n DOB:" + rs.getString("dateOfBirth") + "<br> gender:" + rs.getString("gender")
						+ "<br> address:" + rs.getString("address") + "<br> email:" + rs.getString("email") + "<br> accnum:"
						+ rs1.getInt("accnum"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
