package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hello.pojo.Datum;
import hello.pojo.AFPojo;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
      private ObjectMapper mapper= new ObjectMapper();
      

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
   @RequestMapping( value = "/math",method = RequestMethod.POST)
    @ResponseBody
    public Result math(@RequestBody final Request request) {
        final Result result = new Result();
        result.setAddition(request.getLeft() + request.getRight());
        result.setSubtraction(request.getLeft() - request.getRight());
        result.setMultiplication(request.getLeft() * request.getRight());
        return result;
    }
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    public AFPojo[] doSomething(@RequestBody AFPojo[] input) throws IOException{
        
        return input;
    }
}
