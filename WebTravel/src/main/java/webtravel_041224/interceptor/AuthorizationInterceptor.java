package webtravel_041224.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("userOnline") != null) {
            System.out.println("Bạn đang đăng nhập với tài khoản: " + session.getAttribute("userOnline"));

            return true;
        } else {
            response.sendRedirect("../dang-nhap");
        }

        return false;
    }
}
