import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/school";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "yourpassword";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentName = request.getParameter("student_name");
        String subject = request.getParameter("subject");
        String date = request.getParameter("attendance_date");
        String status = request.getParameter("status");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            String sql = "INSERT INTO attendance (student_name, subject, attendance_date, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, studentName);
            ps.setString(2, subject);
            ps.setDate(3, Date.valueOf(date));
            ps.setString(4, status);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                out.println("<h3>Attendance recorded successfully!</h3>");
            } else {
                out.println("<h3>Error saving attendance.</h3>");
            }

            ps.close();
            conn.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }

        out.println("<br><a href='attendance.jsp'>Back to Form</a>");
        out.close();
    }
}
