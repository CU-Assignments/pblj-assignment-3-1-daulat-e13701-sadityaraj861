import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/company";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "yourpassword";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String empId = request.getParameter("id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            if (empId != null && !empId.isEmpty()) {
                rs = stmt.executeQuery("SELECT * FROM employees WHERE id=" + empId);
            } else {
                rs = stmt.executeQuery("SELECT * FROM employees");
            }

            out.println("<h2>Employee Details</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Department</th><th>Email</th></tr>");

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("department") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("</tr>");
            }

            if (!hasResults) {
                out.println("<tr><td colspan='4'>No employee found</td></tr>");
            }

            out.println("</table>");
            out.println("<br><a href='employee.html'>Back to Search</a>");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
