package com.example.demo.presentation;

import com.example.demo.application.UrlService;
import com.example.demo.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ShortUrlViewController {

    private final UrlService urlService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/short")
    public String shortening(@RequestParam(value = "originalUrl") String originalUrl,
        RedirectAttributes redirectAttributes) {
        ResponseDto responseDto = urlService.shorteningUrl(originalUrl);

        redirectAttributes.addFlashAttribute("responseDto", responseDto);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String result(@ModelAttribute(value = "responseDto") ResponseDto responseDto,
        Model model) {
        model.addAttribute("shortenUrl", responseDto.getShortenUrl());
        model.addAttribute("request", responseDto.getRequestCount());
        return "result";
    }

    @GetMapping("/{shortenUrl}")
    public String redirection(@PathVariable("shortenUrl") String shortenUrl){
        String originalUrl = urlService.getOriginalUrl(shortenUrl);
        return "redirection:/" + originalUrl;
    }

}
