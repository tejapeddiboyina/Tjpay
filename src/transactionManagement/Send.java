package transactionManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Send
 */
@WebServlet("/Send")
public class Send extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Send() {
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
		int accnum = Integer.parseInt(request.getParameter("acc_num"));
		String name = request.getParameter("name");
		float amount = Float.parseFloat(request.getParameter("amount"));
		String userId = (String) request.getSession().getAttribute("username");
		String sql = "select balance,accnum from useraccount where email=?";
		PrintWriter out = response.getWriter();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
					"Ab99636760");
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				if (amount <= rs.getFloat(1)) {

					pst = conn.prepareStatement("select email from useraccount where accnum=?");
					pst.setInt(1, accnum);
					ResultSet rs1 = pst.executeQuery();

					if (rs1.next()) {

						// adding amount to the receiver
						pst = conn.prepareStatement(
								"update useraccount set balance=balance+" + amount + " where accnum=?");
						pst.setInt(1, accnum);
						pst.executeUpdate();

						// inserting details into transaction table
						pst = conn.prepareStatement(
								"insert into transaction(email, transactionType, amount, transactionTime, transactionParty) values(?, ?, ?, ?, ?)");
						pst.setString(1, rs1.getString(1));
						pst.setString(2, "add");
						pst.setFloat(3, amount);
						pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
						pst.setString(5, userId);
						pst.executeUpdate();

						// reducing amount from the user
						pst = conn.prepareStatement(
								"update useraccount set balance=balance-" + amount + " where email=?");
						pst.setString(1, userId);
						pst.executeUpdate();

						// inserting details into transaction table
						pst = conn.prepareStatement(
								"insert into transaction(email, transactionType, amount, transactionTime, transactionParty) values(?, ?, ?, ?, ?)");
						pst.setString(1, userId);
						pst.setString(2, "paid");
						pst.setFloat(3, amount);
						pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
						pst.setString(5, name + ":" + accnum);
						pst.executeUpdate();

						out.println("transaction successfull..");

					} else
						out.println("accnum doesnt exists");
				} else
					out.println("insufficient balance");
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
