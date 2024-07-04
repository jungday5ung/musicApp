package com.music777.app.playlist.mapper;

import com.music777.app.playlist.dto.PlaylistDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlaylistMapper {

    // 플레이리스트를 삽입하는 SQL 구문
//    @Insert("INSERT INTO playlists (title, user_no) VALUES (#{playlistTitle}, #{userNo})")
//    @SelectKey(statement = "SELECT playlist_seq.NEXTVAL FROM dual", keyProperty = "playlistId", before = true, resultType = int.class)
//    void insertPlaylist(PlaylistDTO playlistDTO);
//
//    @Insert("INSERT INTO songs (rank, title, artist, album, image_url, playlist_id) VALUES (#{rank}, #{title}, #{artist}, #{album}, #{imageUrl}, #{playlistId})")
//    void insertSong(@Param("rank") String rank,
//                    @Param("title") String title,
//                    @Param("artist") String artist,
//                    @Param("album") String album,
//                    @Param("imageUrl") String imageUrl,
//                    @Param("playlistId") int playlistId);
//
//    @Select("SELECT playlist_id AS playlistId, title AS playlistTitle, user_no AS userNo FROM playlists WHERE user_no = #{userNo}")
//    List<PlaylistDTO> getPlaylistsByUserNo(int userNo);

    // 플레이리스트를 삽입하는 SQL 구문
    @Insert("INSERT INTO playlists (playlist_id, title, user_no) VALUES (playlists_seq.NEXTVAL, #{playlistTitle}, #{userNo})")
    @SelectKey(statement = "SELECT playlists_seq.CURRVAL FROM dual", keyProperty = "playlistId", before = false, resultType = int.class)
    void insertPlaylist(PlaylistDTO playlistDTO);

    @Insert("INSERT INTO songs (song_id, rank, title, artist, album, image_url, playlist_id) VALUES (songs_seq.NEXTVAL, #{rank}, #{title}, #{artist}, #{album}, #{imageUrl}, #{playlistId})")
    @SelectKey(statement = "SELECT songs_seq.CURRVAL FROM dual", keyProperty = "songId", before = false, resultType = int.class)
    void insertSong(@Param("rank") String rank,
                    @Param("title") String title,
                    @Param("artist") String artist,
                    @Param("album") String album,
                    @Param("imageUrl") String imageUrl,
                    @Param("playlistId") int playlistId);

    @Select("SELECT playlist_id AS playlistId, title AS playlistTitle, user_no AS userNo FROM playlists WHERE user_no = #{userNo}")
    List<PlaylistDTO> getPlaylistsByUserNo(int userNo);
}
