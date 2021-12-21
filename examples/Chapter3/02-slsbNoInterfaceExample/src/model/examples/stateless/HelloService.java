package examples.stateless;

import jakarta.ejb.Stateless;

@Stateless
public class HelloService {
    public String sayHello(String name) {
        return "Hello, "  + name;
    }
}

