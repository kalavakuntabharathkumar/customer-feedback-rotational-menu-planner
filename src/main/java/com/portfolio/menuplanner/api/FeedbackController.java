package com.portfolio.menuplanner.api;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/feedback")
public class FeedbackController {
 @PostMapping("/analyze") public Map<String,Object> analyze(@RequestBody Map<String,String> body){
   String text=body.getOrDefault("text","").toLowerCase();
   String sentiment = (text.contains("bad")||text.contains("cold")||text.contains("slow")||text.contains("poor"))
      ? "NEGATIVE" : (text.contains("good")||text.contains("great")||text.contains("fresh")) ? "POSITIVE" : "NEUTRAL";
   String topic = text.contains("price") ? "PRICE" : text.contains("taste") ? "TASTE" :
      text.contains("service") ? "SERVICE" : text.contains("portion") ? "PORTION" : "OTHER";
   return Map.of("sentiment", sentiment, "topic", topic, "model", "DJL-DistilBERT-compatible fallback");
 }
}
