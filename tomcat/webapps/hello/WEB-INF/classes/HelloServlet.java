
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class HelloServlet extends HttpServlet {
   private static boolean loaded = false;
   private static ArrayList<Food> foods;
   private static Knapsack knap;
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {

      // Set the response MIME type of the response message
      response.setContentType("text/html");
      // Allocate a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      // Write the response message, in an HTML page
      try {
         int cal = Integer.parseInt(request.getParameter("cal"));
         int prot = Integer.parseInt(request.getParameter("prot"));

         //TODO:
         
         // Load in datasets
         if (!loaded) {
        	// Load in food
             this.foods = ReadCSV.readFile();
         	 // Build knapsack
         	 knap = new Knapsack(foods);
         	 // Load in Nutrient Info
         	 NutrientInfo.init_info();
         	 // System has now loaded
         	 loaded = true;
         }
         
     	 // Build NT
     	 NutrientTarget nt = new NutrientTarget(cal);
     	 // Fit NT with data
     	 Meal best = Fitter.fitNutrTar(knap, nt);
     	 // Get first food (Note: this is just returning the first tag of the first food in meal for testing purposes)
     	 //out.println(best.get(0).getDescriptor()[0]);
     	 out.println(NutrientInfo.get(654).getName());
     	 
         //Here we call the knapsack algorithm on "cals"
         //String foods = "{Chicken, Lettuce}";
         //NutrientTarget nt = new NutrientTarget(cal);
         //ArrayList<Integer> f = nt.getNutrients();
         //out.println("<p>Calories required: " + request.getParameter("cal") + "</p>");
         //out.println("<p>Protein required: " + request.getParameter("prot") + "</p>");
         //out.println(nt);
         //END TODO

         // out.println("<html>");
         // out.println("<head><title>Hello, World</title></head>");
         // out.println("<body>");
         // out.println("<h1>Hello, world!</h1>");  // says Hello
         // // Echo client's request information
         // out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
         // out.println("<p>Request Header: " + request.getHeaderNames() + "</p>");
         // out.println("<p>Request Method: " + request.getMethod() + "</p>");
         // out.println("<p>Protocol: " + request.getProtocol() + "</p>");
         // out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
         // out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");
         // // Generate a random number upon each request
         // out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
         // out.println("</body></html>");
      } finally {
         out.close();  // Always close the output writer
      }
   }
  @Override
   public void doPost(HttpServletRequest request,
                            HttpServletResponse response)
              throws IOException, ServletException {
      doGet(request, response);
         String user = request.getParameter("cal");
         //Knapsack.find()
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