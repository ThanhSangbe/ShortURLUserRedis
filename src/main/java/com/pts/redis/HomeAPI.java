package com.pts.redis;

import java.nio.charset.StandardCharsets;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;

@RestController
public class HomeAPI {
//
		@Autowired
		RedisTemplate<String,String> redisTemplate;
//   Map<String, String> params = new HashMap<String, String>();
//	@GetMapping("/{id}")
//	public String index(@PathVariable String id,Model model)
//	{
//		String url = params.get(id);
//        System.out.println("URL Retrieved: " + url);
//        model.addAttribute("index", url);
//
//        return "index";
//	}
//	@PostMapping("/api/url")
//	public String get(@RequestBody String url)
//	{
//		UrlValidator urlValidator = new UrlValidator(
//                new String[]{"http", "https"}
//        );
//
//        if (urlValidator.isValid(url)) {
//            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
//            System.out.println("URL Id generated: "+ id);
//            params.put(id, url);
//            return id;
//        }
//
//        throw new RuntimeException("URL Invalid: " + url);
//	}
	
	@Autowired
	private URLRepository urlRepository;
	
	@PostMapping("/")
	public URL index(@RequestBody String url)
	{
		UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );

        if (urlValidator.isValid(url)) {
		String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
//		redisTemplate.opsForValue().set(id, url);
		return this.urlRepository.save(new URL(id,url));
        }
        throw new RuntimeException("URL Invalid: " + url);
	}
	@GetMapping("/{id}")
	public URL get(@PathVariable String id)
	{
		return this.urlRepository.findById(id).get();
//		return redisTemplate.opsForValue().get(id);
	}
	
}
