package com.music777.app.playlist.dto;

import lombok.Data;

@Data
public class SongDTO {
    private int songId;
    private String rank;
    private String title;
    private String artist;
    private String album;
    private String imageUrl;
    private int playlistId;

}
