package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FrontContrllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();
    public FrontContrllerServletV4(){
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;;
        }
        Map<String, String> paraMap = createParaMap(req);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paraMap, model);

        Myview view = viewResolver(viewName);

    }

    private Myview viewResolver(String viewName) {
        return new Myview("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParaMap(HttpServletRequest req) {
        Map<String, String> paraMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paraName -> paraMap.put(paraName, req.getParameter(paraName)));
        return paraMap;
    }
}
