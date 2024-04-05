//package com.ee.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@Slf4j
//public class FrontEndReactFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String path = request.getRequestURI().toLowerCase();
//        log.info("react path "+ path);
//
//
//        if(
//                !path.startsWith("/static")
//                && !path.startsWith("/manifest.json")
//                && !path.startsWith("/favicon.ico")
//                && !path.startsWith("/robots.txt")
//                && !path.startsWith("/xml")
//                && !path.startsWith("/json")
//                && !path.startsWith("/jpg")
//                && !path.startsWith("/jpeg")
//                && !path.startsWith("/gif")
//                && !path.startsWith("/png"))
//        {
//            log.info("foward to v4.html from path " + path);
//            request.getRequestDispatcher("/v4.html").forward(request,response);
//        }
//    }
//}
