package com.example.demo.presentation;

import com.example.demo.application.UrlService;
import com.example.demo.dto.ResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ShortUrlViewController {

    private final UrlService urlService;

    @Value("${host}")
    private String host;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping
    public String home() {
        logger.info("시나리오 1");
        return "home";
    }

    @PostMapping("/short")
    public String shortening(@RequestParam(value = "originalUrl") String originalUrl,
        RedirectAttributes redirectAttributes) {
        ResponseDto responseDto = urlService.shorteningUrl(originalUrl);

        redirectAttributes.addFlashAttribute("responseDto", responseDto);
        logger.info("post 요청 성공");
        return "redirect:short/result";
    }

    @GetMapping("/short/result")
    public String result(@ModelAttribute ResponseDto responseDto,
        Model model) {
        model.addAttribute("shortenUrl", host + responseDto.getShortenUrl());
        model.addAttribute("request", responseDto.getRequestCount());
        logger.info("결과 창 조회");
        return "result";
    }

    @GetMapping("/{shortenUrl}")
    public String redirection(@PathVariable(name = "shortenUrl", required = false) String shortenUrl){
        logger.info("단축 URL -> {}", shortenUrl);
        String originalUrl = urlService.getOriginalUrl(shortenUrl);
        logger.info("리다이렉션 원본 URL -> {}", originalUrl);
        return "redirect:" + originalUrl;
    }

}
