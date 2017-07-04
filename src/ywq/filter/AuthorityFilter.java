package ywq.filter;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;
 
@WebFilter(
           filterName = "authorityFilter",
     urlPatterns = { "/*" },
     initParams = {
              @WebInitParam(name = "encoding", value = "UTF-8"),
           })
public class AuthorityFilter implements Filter {
    private FilterConfig config;
          // ʵ�ֳ�ʼ������
    public void init(FilterConfig fConfig) throws ServletException {
              config = fConfig;
}
    // ʵ�ֹ��˷���
    public void doFilter(ServletRequest request, ServletResponse response,
 FilterChain chain) throws IOException, ServletException {
             // ��ȡ�ù����������ò���
       String encoding = config.getInitParameter("encoding");
       // ��������request�ı����ַ���
       request.setCharacterEncoding(encoding);
       HttpServletRequest hrequest = (HttpServletRequest)request;
       HttpSession session = hrequest.getSession(true);
       chain.doFilter(request, response);
        }
    // ʵ�����ٷ���
public void destroy() {
              config = null;
           }
}