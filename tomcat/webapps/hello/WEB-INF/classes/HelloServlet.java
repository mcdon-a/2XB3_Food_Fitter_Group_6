import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {

      // Set the response MIME type of the response message
      response.setContentType("text/html");
      // Allocate a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      // Write the response message, in an HTML page
      try {
         out.println("<html>");
         out.println("<head><title>Hello, World</title></head>");
         out.println("<body>");
         out.println("<h1>Hello, world!</h1>");  // says Hello
         // Echo client's request information
         out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
         out.println("<p>Request Header: " + request.getHeaderNames() + "</p>");
         out.println("<p>Request Method: " + request.getMethod() + "</p>");
         out.println("<p>Protocol: " + request.getProtocol() + "</p>");
         out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
         out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");
         out.println("<p>Username: " + request.getParameter("username") + "</p>");
         out.println("<p>Password: " + request.getParameter("password") + "</p>");
         // Generate a random number upon each request
         out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
         out.println("</body></html>");
      } finally {
         out.close();  // Always close the output writer
      }
   }
  @Override
          public void doPost(HttpServletRequest request,
                            HttpServletResponse response)
              throws IOException, ServletException {
      doGet(request, response);
         String user = request.getParameter("username");
         String pass = request.getParameter("password");
         // User user = userService.find(username, password);
         // System.out.println(user + pass);

         // if (user != null) {
         //     request.getSession().setAttribute("user", user);
         //     response.sendRedirect("home");
         // }
         // else {
         //     request.setAttribute("error", "Unknown user, please try again");
         //     request.getRequestDispatcher("/login.jsp").forward(request, response);
         // }
      }
      }