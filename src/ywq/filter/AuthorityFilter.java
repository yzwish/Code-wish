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
          // 实现初始化方法
    public void init(FilterConfig fConfig) throws ServletException {
              config = fConfig;
}
    // 实现过滤方法
    public void doFilter(ServletRequest request, ServletResponse response,
 FilterChain chain) throws IOException, ServletException {
             // 获取该过滤器的配置参数
       String encoding = config.getInitParameter("encoding");
       // 设置请求request的编码字符集
       request.setCharacterEncoding(encoding);
       HttpServletRequest hrequest = (HttpServletRequest)request;
       HttpSession session = hrequest.getSession(true);
       chain.doFilter(request, response);
        }
    // 实现销毁方法
public void destroy() {
              config = null;
           }
}