package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    //controller mapping 예제
    @GetMapping("hello") //http의 get/post의 get. 임의의 url에 쳐서 들어오는 방법이 get 방식.
    public String hello(Model model){   //MVC의 M. spring이 Model 이라는 걸 만들어서 넣어주는 거.
        model.addAttribute("data", "hello!!");
        return "hello"; //templates 폴더 아래에서 리턴 문자열이랑 일치하는 화면 찾아서 랜더링(model 들구감)
    }

    //MVC 예제
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model){ //model도 받아서 넘겨줘야함. model에 데이터 담어서 화면으로 넘겨주면 화면에서 받아서 렌더링할 때 씀.
        model.addAttribute("name", name);
        return "hello-template"; //view resolver가 'hello-template'이라는 이름을 가진 뷰파일을 templates하위 폴더에서 찾음.
    }


    /**
     * static 파일 mapping 예제
     * 1. static 하위에 정적파일을 호출하고 싶으면 .html 확장자까지 입력
     * 2. 그런데 controller의 hello-static.html과 매핑되는 메소드가 있으면 컨트롤러가 우선순위를 가짐.
     */
    //static 파일 mapping 예제 1-1
    @GetMapping("hello-static")
    public String helloStatic(Model model){
        model.addAttribute("data", "hello!!");
        return "hello-static"; //templates 폴더 아래에서 리턴 문자열이랑 일치하는 화면 찾아서 랜더링(model 들구감)
    }

//    //static 파일 mapping 예제 1-2
//    @GetMapping("hello-static.html")
//    public String helloStaticHTML(Model model){
//        model.addAttribute("data", "hello!!");
//        return "hello-static"; //templates 폴더 아래에서 리턴 문자열이랑 일치하는 화면 찾아서 랜더링(model 들구감)
//    }


    /**
     * API 예제
     * 1. 메소드위에 @ResponseBody 어노테이션이 붙으면 스프링 컨테이너에서 viewResolver로 넘기지 않고 HttpMessageConverter한테 보냄
     * 2. 반환 타입이 String 형이면 그냥 문자 그대로 넘김.
     * 3. 반환 타입이 객체다? 그럼 객체를 json 데이터 형식으로 바꿔서 넘김. (spring-boot 에서는 jackson 라이브러리가 기본 탑재)
     */
    //API 예제 1-1 (string)
    @GetMapping("hello-string")
    @ResponseBody //HTTP의 body 부에 return 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam(value = "name", required = true) String name){ //model도 받아서 넘겨줘야함. model에 데이터 담어서 화면으로 넘겨주면 화면에서 받아서 렌더링할 때 씀.
        return "hello " + name;
    }

    //API 예제 1-2 (Object)
    @GetMapping("hello-api")
    @ResponseBody //HTTP의 body 부에 return 데이터를 직접 넣어주겠다.
    public Hello helloApi(@RequestParam(value = "name", required = true) String name){ //model도 받아서 넘겨줘야함. model에 데이터 담어서 화면으로 넘겨주면 화면에서 받아서 렌더링할 때 씀.
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }


    static class Hello{
        private String name;

        //name 이라는 멤버변수가 private로 선언되어있기 때문에 외부에서 바로 접근을 못함. 그래서 getter, setter를 통해서 접근해야함.
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
