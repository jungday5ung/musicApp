package com.music777.app.playlist.controller;

public class MelonChart {
    private String rank;
    private String imageUrl;
    private String songTitle;
    private String artist;
    private String album;

    public MelonChart(String rank, String imageUrl, String songTitle, String artist, String album) {
        this.rank = rank;
        this.imageUrl = imageUrl;
        this.songTitle = songTitle;
        this.artist = artist;
        this.album = album;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
