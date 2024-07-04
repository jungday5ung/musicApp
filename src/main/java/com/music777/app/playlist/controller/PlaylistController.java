package com.music777.app.playlist.controller;

import com.music777.app.playlist.dto.PlaylistDTO;
import com.music777.app.playlist.service.PlaylistService;
import com.music777.app.user.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    // 플레이리스트 홈 페이지를 보여줍니다.
    @GetMapping("/playlist/playlist-home")
    public String showPlaylistHomePage(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null) {
            model.addAttribute("user", userDTO);
            // 사용자의 플레이리스트를 모델에 추가합니다.
            List<PlaylistDTO> userPlaylists = playlistService.getPlaylistsByUserNo(userDTO.getUserNo());
            model.addAttribute("userPlaylists", userPlaylists);
        }

        // 멜론 차트를 가져와서 모델에 추가합니다.
        List<MelonChart> melonChart = getMelonChart();
        model.addAttribute("melonChart", melonChart);
        return "playlist/playlist-home";
    }

    // 멜론 차트를 가져오는 메소드입니다.
    @GetMapping("/melon-chart")
    @ResponseBody
    public List<MelonChart> getMelonChart() {
        List<MelonChart> chartList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.melon.com/chart/index.htm").get();
            Elements elements = doc.select("div.service_list_song > table > tbody > tr");

            for (Element element : elements) {
                String rank = element.select("span.rank").text();
                String imageUrl = element.select("a.image_typeAll img").attr("src");
                String songTitle = element.select("div.rank01 span a").text();
                String artist = element.select("div.rank02 span a").text();
                String album = element.select("div.rank03 a").text();
                chartList.add(new MelonChart(rank, imageUrl, songTitle, artist, album));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chartList;
    }



    @PostMapping("/playlist/save")
    @ResponseBody
    public ResponseEntity<PlaylistDTO> savePlaylist(@RequestBody PlaylistDTO playlistDTO, HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null) {
            playlistDTO.setUserNo(userDTO.getUserNo());
            PlaylistDTO savedPlaylist = playlistService.savePlaylist(playlistDTO);
            if (savedPlaylist != null) {
                return ResponseEntity.ok(savedPlaylist);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
}

    @GetMapping("/playlist/playlist-search")
    public String showPlaylistSearchPage(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null) {
            model.addAttribute("user", userDTO);
            // 사용자의 플레이리스트를 모델에 추가합니다.
            List<PlaylistDTO> userPlaylists = playlistService.getPlaylistsByUserNo(userDTO.getUserNo());
            model.addAttribute("userPlaylists", userPlaylists);
        }

        return "playlist/playlist-search";
    }

}


