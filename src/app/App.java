package app;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class App extends HttpServlet{
    private static final long serialVersionUID = -3967314453512919811L;

    private String mymsg;
    public void init() throws ServletException
    {
       mymsg = "Http Servlet Demo";
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<h1>Hi</h1>");
            out.println("<p>Search a word:</p>");
            out.println("<form action='http://localhost:8080/WebContent/welcome' method='get'>"+
                        "<input type='text' name='word'><br>"+
                        "<input type='submit' name='cerca' value='cerca'>"+
                        "</form>");
            out.println("<p>Add word:</p>"+
                        "<form action='http://localhost:8080/WebContent/welcome' method='put'>"+
                        "Parola: <input type='text' name='addword'><br>"+
                        "Sinonimi: <input type='text' name='sinonimi'><br>"+
                        "Contrario: <input type='text' name='contrari'><br>"+
                        "<input type='submit' name='aggiungi' value='aggiungi'>"+
						"</form>");
			out.println("<p>Erase Word:</p>"+
                        "<form action='http://localhost:8080/WebContent/welcome' method='delete'>"+
                        "Parola: <input type='text' name='eraseword'><br>"+
                        "<input type='submit' name='erase' value='elimina'>"+
                        "</form>");
                        String word = req.getParameter("word");
						String cerca = req.getParameter("cerca");
						String wordadd = req.getParameter("addword");
                        String sinonimo = req.getParameter("sinonimi");
                        String contrario = req.getParameter("contrari");
						String aggiungi =req.getParameter("aggiungi");
						String erase=req.getParameter("erase");
						String eraseword=req.getParameter("eraseword");
                        if(cerca != null){
							try {
								out.println(this.searchWord(req, res, word));
							} catch (Exception e) {
							}
                        }
                        if(aggiungi != null){
							try {
								out.println(this.addWord(req,res,wordadd,sinonimo,contrario));
							} catch (Exception e) {
								System.out.println(e);
								//TODO: handle exception
							}

							}	
						if(erase != null){
						try {
							out.println(this.eraseWord(req,res,eraseword));
						} catch (Exception e) {
							//TODO: handle exception
						}

						}
    }

    public void doPut(HttpServletRequest req, HttpServletResponse res, String wordadd, String sinonimo, String contrario) throws IOException{
        res.setContentType("text/html");
        this.addWord(req, res,wordadd,sinonimo,contrario);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res,String x) throws IOException{
        res.setContentType("text/html");
        this.eraseWord(req, res,x);
    }

    public String searchWord(HttpServletRequest req, HttpServletResponse res,String parola) throws IOException{
    	FileReader reader = new FileReader("C:/Users/Admin/Desktop/HelloServlet/WebContent/WEB-INF/classes/app/word.txt");
		BufferedReader b=new BufferedReader(reader);
    	String z="null";
    	String s1;
		String s2;
    	Boolean c=true;
       	while(c==true){
       		z=b.readLine();
       		if(z.equals("null")){
    			c=false;
    			break;
    				}
    		if(z.equalsIgnoreCase(parola)){
    			s1=b.readLine();
    			s2=b.readLine();
    			b.close();
    			reader.close();
    			return("<h1>Meaning of "+z+s1+s2+":</h1>");
    		}

    	}
    	b.close();
    	reader.close();
        return("false");

    }
    public boolean addWord(HttpServletRequest req, HttpServletResponse res, String parola, String sinonimo, String contrario )throws IOException{
		System.out.println("ciao");
		File f2 = new File("C:/Users/Admin/Desktop/HelloServlet/WebContent/WEB-INF/classes/app/word.txt");
		f2.setWritable(true,false);
		boolean t = true;
		boolean f = false;
    	try {
		FileOutputStream out = new FileOutputStream("C:/Users/Admin/Desktop/HelloServlet/WebContent/WEB-INF/classes/app/word.txt");
		PrintStream scrivi = new PrintStream(out);
		scrivi.println(parola);
		scrivi.println("-"+sinonimo);
		scrivi.println("-"+contrario);
		scrivi.close();
		out.close();
		return(t);
		}	
		 catch (Exception e) {
			System.out.println(e);
			return(f);
		}
      }
    public boolean eraseWord(HttpServletRequest req, HttpServletResponse res, String parola) throws IOException{
        res.setContentType("text/html");
        File file = new File("C:/Users/Admin/Desktop/HelloServlet/WebContent/WEB-INF/classes/app/word1.txt");
		if (file.createNewFile())
			{
    			System.out.println("File is created!");
			} else {
    			System.out.println("File already exists.");
			}
		FileWriter fWriter=new FileWriter("C:/Users/Admin/Desktop/HelloServlet/WebContent/WEB-INF/classes/app/word1.txt");
		BufferedWriter writer = new BufferedWriter(fWriter);
		FileReader reader = new FileReader("C:/Users/Admin/Desktop/HelloServlet/WebContent/WEB-INF/classes/app/word.txt");
    	BufferedReader b;
    	b=new BufferedReader(reader);
    	String s;
       	while(true){
       		s=b.readLine();
       		if(s==null){
    			break;
    				}
    		if(s.equalsIgnoreCase(parola)){
    			String s1=b.readLine();
    			String s2=b.readLine();
    			s=b.readLine();
    		}
    		if(s==null){
		  		break;
		  		}
		  	writer.append(s);
		  	writer.append("\n");
       		}
		b.close();
		writer.close();
		reader.close();
		fWriter.close();
		File f1 = new File("C:/Users/Admin/Desktop/HelloServlet/WebContent/WEB-INF/classes/app/word.txt");
		boolean d;
		d=f1.delete();
		System.out.println(d);
		File f2 = new File("C:/Users/Admin/Desktop/HelloServlet/WebContent/WEB-INF/classes/app/word1.txt");
		File f3 = new File("C:/Users/Admin/Desktop/HelloServlet/WebContent/WEB-INF/classes/app/word.txt");
		d=f2.renameTo(f3);
		f2.setWritable(true, false);
		return(d);
   }
}