package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.namespace.QName;
import java.io.IOException;

@WebServlet(name="requestHeaderServlet", urlPatterns="/request-header")
public class RequestHeaderServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);

        printHeaderUtils(request);
    }

    private static void printHeaderUtils(HttpServletRequest request) {
        System.out.println("Host header = " + request.getServerName());
        System.out.println("Host header = " + request.getServerPort());

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator().forEachRemaining(locale -> System.out.println("locale: "+locale));

        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                System.out.println("cookie.getCookie() +" + ":" + cookie.getValue());
            }
        }
    }

    private static void printHeaders(HttpServletRequest request) {
        System.out.println("--- HEADERS - start ---");
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": "
                        + request.getHeader(headerName)));

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    private static void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http

        System.out.println("request.getRequestURL() = " + request.getRequestURL());       // http://localhost:8080/request-header

        System.out.println("request.getRequestURI() = " + request.getRequestURI());// /request-header

        System.out.println("request.getQueryString() = " + request.getQueryString());//username=hi
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

}