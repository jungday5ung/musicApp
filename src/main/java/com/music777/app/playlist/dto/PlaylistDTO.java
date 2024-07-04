package com.music777.app.playlist.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaylistDTO {

//    private int playlistNo;
//    private String playlistTitle;
//    private String title;
//    private String artist;
//    private String image;
//    private String path;
//    private String playlistDel;
//    private String createdAt;
//    private int userNo;

    private int playlistId;
    private String playlistTitle;
    private List<SongDTO> songs;
    private int userNo;
}
