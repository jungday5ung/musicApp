package com.music777.app.music.controller;

import com.music777.app.user.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MusicController {



    @GetMapping("/music/music-recommend")
    public String showMusicRecommende(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null) {
            model.addAttribute("user", userDTO);
        }
        return "music/music-recommend"; // mypage.html 템플릿으로 매핑
    }





    @GetMapping("/music/recommend")
    @ResponseBody
    public List<String> getRecommendations(@RequestParam String query) {
        List<String> recommendations = new ArrayList<>();
        String apiKey = "AIzaSyBI9ka_sH-dIaeBk8U2lniYmZZjlpLhwEg"; // API 키 입력
        String apiUrl = "https://www.googleapis.com/youtube/v3/search";

        try {
            String url = apiUrl + "?part=snippet&type=video&q=" + URLEncoder.encode(query, "UTF-8") +
                    "&videoCategoryId=10&key=" + apiKey + "&regionCode=KR&relevanceLanguage=ko" +
                    "&maxResults=5"; // 결과 수 제한

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            JSONObject jsonResponse = new JSONObject(response.getBody());
            JSONArray items = jsonResponse.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String videoId = item.getJSONObject("id").getString("videoId");
                recommendations.add(videoId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recommendations;
    }





}
