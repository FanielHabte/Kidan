package io.kidan.guardian.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractController {

    @GetMapping("/string/type/dto")
    public String getStringDto (HttpSession session, Model model) {
        int testNum = (Integer) session.getAttribute("Test");
        testNum++;
        session.setAttribute("Test", testNum);
        model.addAttribute("Test", testNum);

        return "/fragments/contract-dto-types :: replaceable-frag";
    }

//    @GetMapping("/numeric/type/dto")
//    public String getNumericDto () {
//
//
//        return "";
//    }
//
//    @GetMapping("/string/type/dto")
//    public String getDateDto () {
//
//
//        return "";
//    }
//
//    @GetMapping("/string/type/dto")
//    public String getTimeStampDto () {
//
//
//        return "";
//    }
}
