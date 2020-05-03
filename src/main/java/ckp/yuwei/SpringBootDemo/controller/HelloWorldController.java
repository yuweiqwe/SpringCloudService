package ckp.yuwei.SpringBootDemo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @ProjectName: SpringBootDemo
 * @Package: ckp.yuwei.SpringBootDemo.controller
 * @ClassName: HelloWorldController
 * @Description: java类作用描述
 * @Author: 余巍
 * @CreateDate: 2020/3/27 13:26
 * @UpdateUser: yuwei2
 * @UpdateDate: 2020/3/27 13:26
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
@RestController
public class HelloWorldController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/doBiz", method = RequestMethod.GET)
    public String doBiz() {
        System.out.println("doBiz start");
        try {
            Thread.sleep(30 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("doBiz end");
        }
        return "do business";
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return this.discoveryClient.getInstances("spring-cloud-provider");
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        System.out.println("hello");
        return "Hello World Service";
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(@RequestParam String name) {
        System.out.println("hello1");
        return "Hello World Service1 : name = " + name;
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2(@RequestHeader String name, @RequestHeader Integer age) {
        System.out.println("hello2");
        return "Hello World Service2 : name = " + name + ", age = " + age;
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello3(@RequestBody Map<String, String> parameterMap) {
        System.out.println("hello3");
        return "Hello World Service3 : parameterMap size = " + parameterMap.size();
    }

    @HystrixCommand(fallbackMethod = "errorFallback",
            commandKey = "errorKey", groupKey = "helloWorldGroupKey", threadPoolKey = "poolKey1")
    @RequestMapping("/test")
    public String test() throws Exception {
        System.out.println("test");
        throw new Exception("error");
    }

    public String errorFallback() {
        return "error";
    }

}
