package br.cesjf.hotellucena.filter;

import br.cesjf.hotellucena.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Washington
 */
public class LoginF implements Filter {
    
    
    private static final boolean deBug = true;
    
    
    private FilterConfig filterC = null;
    
    
    public LoginF(){
    
        
    }    
    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
    
            throws IOException, ServletException {
            
                if (deBug) {
                    log("LoginFilter:DoBeforeProcessing");
        }       
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
        throws IOException, ServletException {
            if (deBug) {
                log("LoginFilter:DoAfterProcessing");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        if (deBug) {
            log("LoginFilter:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            HttpSession sess = ((HttpServletRequest)request).getSession(false);
            String paginaAcessada = ((HttpServletRequest)request).getRequestURI();
            
            Usuario usuarioLogado = null;
            
            if (sess != null)
                usuarioLogado = (Usuario)sess.getAttribute("usuario");
            
            if (!"/HotelLucena/faces/login.xhtml".equals(paginaAcessada))
            {
                if (usuarioLogado == null) {
                    ((HttpServletResponse)response).sendRedirect("/HotelLucena/faces/login.xhtml");
                }
            }
            else
            {
                if (usuarioLogado != null) {
                    ((HttpServletResponse)response).sendRedirect("/HotelLucena");
                }
            }
            
            chain.doFilter(request, response);
        } catch (Throwable t) {

            problem = t;
            t.printStackTrace();
            
        }
        
        doAfterProcessing(request, response);

        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    public FilterConfig getFilterConfig() {
        return (this.filterC);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterC = filterConfig;
    }

    public void destroy() {        
    }

    @Override
    public String toString() {
        if (filterC == null) {
            return ("LoginFilter()");
        }
        StringBuffer sb = new StringBuffer("LoginFilter(");
        sb.append(filterC);
        sb.append(")");
        return (sb.toString());
    }
    
    public void log(String msg) {
        filterC.getServletContext().log(msg);        
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n");
                
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>");
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public void init(FilterConfig filterConfig) {        
        this.filterC = filterConfig;
        if (filterConfig != null) {
            if (deBug) {                
                log("LoginFilter:Initializing filter");
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
}