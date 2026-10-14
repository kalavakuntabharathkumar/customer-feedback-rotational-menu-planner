package com.portfolio.menuplanner.api;
import com.portfolio.menuplanner.service.MenuPlannerService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/menu")
public class MenuController {
 private final MenuPlannerService service;
 public MenuController(MenuPlannerService service){this.service=service;}
 @PostMapping("/plan") public Map<String,Object> plan(@RequestBody List<Map<String,Object>> dishes){
   return service.plan(dishes);
 }
}
