package sh.hellospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "my name is sanha~");
        return "hello_view"; // model(data)를 view에 넘겨준다.
    }

    // HTTP GET 방식에서 param(key-value) 받는 컨트롤러
    // localhost:8080/hello-mvc?name="sldkfjalskdj"
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name", required=false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-templates";
    }

    @GetMapping("hello-api")
    @ResponseBody // model 없이(?) 응답 body 안에 data를 직접 넣어주겠다는 뜻
    public String helloApi(@RequestParam("name") String name){
        return "hello " + name; // f12로 페이지 내용 띄워봤을 때 <body> 태그 안에 내용만 달랑 들어가 있음
        // 위의 mvc 패턴처럼 특정 문서 (html)를 반환하는게 아니라, 내용만 반환 
    }

    @GetMapping("hello-api2")
    @ResponseBody //
    public String helloApi2(@RequestParam("name") String name){
        // 원한다면 이런식으로 html 문서를 반환해줄 수 있음
        return "<html> <title>this is title</title> <body> <p>hello " + name + "</p></body></html>";
    }

    @GetMapping("hello-api3")
    @ResponseBody
    public Hello helloApi3(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 문자가 아닌 객체를 리턴 -> json 형태로
    }

    static class Hello{
        private String name;
        
        // getter setter --> java bean 규약
        // property 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

/*
MVC 패턴 맛보기! - Controller 편
<View 반환>
- 기본: 컨트롤러가 반환한 "문자열"에 맞는 view를 찾아줌
- spring boot 템플릿엔진: resources:templates/{ViewName}.html

<Request Mapping>
- 기본: http 메소드와 url에 맵핑된 컨트롤러가 호출된다.
- @GetMapping은 HTTP Get method를 의미하며,
- 우리가 일반적으로 데이터 전달 없이 url 문자열만 입력하면 GET 메소드로 요청 보내는 것임

<Model>
- 컨트롤러 내부에서 model에 넘겨줄 데이터를 저장한다.
- 반환하는 view에 해당 model를 넘겨준다. (model -> map 형식으로 데이터를 저장?)

 */
