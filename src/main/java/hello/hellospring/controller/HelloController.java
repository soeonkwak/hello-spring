package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //http의 get/post의 get. 임의의 url에 쳐서 들어오는 방법이 get 방식.
    public String hello(Model model){   //MVC의 M. spring이 Model 이라는 걸 만들어서 넣어주는 거.
        model.addAttribute("data", "hello!!");
        return "hello"; //templates 폴더 아래에서 리턴 문자열이랑 일치하는 화면 찾아서 랜더링(model 들구감)
    }

}
