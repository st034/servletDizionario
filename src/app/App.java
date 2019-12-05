package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class App extends HttpServlet{
    /**
     *
     */
    private static final long serialVersionUID = -3967314453512919811L;

    private String mymsg;
    public void init() throws ServletException 
    {      
       mymsg = "Http Servlet Demo";   
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
       // Setting up the content type of web page      
        res.setContentType("text/html");
        String word = req.getParameter("word");
        if(word != null){
            isMatch(word);

        }else{

        }
    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException{
        res.setContentType("text/html");
        addWord(req, res);
    }

    public void doPost((HttpServletRequest req, HttpServletResponse res) throws IOException{
        res.setContentType("text/html");
        eraseWord(req, res);
    }

    public void isMatch(String x) throws IOexception{
        PrintWriter out = res.getWriter();
        dictionary d=new dictionary();
        String dt=d.serchWord(x);
        out.println("<h1>Meaning of "+word+":</h1>");
        out.println("<p>"+dt+"</p");
        
    }

    public void addWord(HttpServletRequest req, HttpServletResponse res)throws IOException{
        res.setContentType("text/html");
        String word=req.getParameter("addword");
        String sinonimi =req.getParameter("sinonimi");
        String contrari=req.getParameter("contrari");
        dictionary d=new dictionary();
        boolean c=d.addWord(word, sinonimi,contrari);
        if (c==true){
            PrintWriter out = res.getWriter();
            out.print("<h2>Added:</h2>"+"<ul><li>"+word+": "+sinonimi+contrari+"</li></ul>");
        }else{PrintWriter out = res.getWriter();
            out.print("i have a problem, sorry");
        }

        }
    public void eraseWord((HttpServletRequest req, HttpServletResponse res) throwsm IOException{
        res.setContentType("text/html");
        String word = req.getParameter("word");
        dictionary d=new dictionary();
        boolean e=d.eraseWord(word);
        if(e==true){
            PrintWriter out = res.getWriter();
            out.println("erased");

        }else{PrintWriter out = res.getWriter();
            out.println("i have a problem, sorry");

        }
    }
    }