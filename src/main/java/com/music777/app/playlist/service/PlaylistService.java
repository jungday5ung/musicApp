package com.music777.app.playlist.service;

import com.music777.app.playlist.dto.PlaylistDTO;
import com.music777.app.playlist.mapper.PlaylistMapper;
import com.music777.app.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistMapper playlistMapper;

    @Autowired
    private UserMapper userMapper;

//    @Transactional
//    public PlaylistDTO savePlaylist(PlaylistDTO playlistDTO) {
//        try {
//            // 플레이리스트를 삽입하고, 삽입된 ID를 가져옵니다.
//            playlistMapper.insertPlaylist(playlistDTO);
//            int playlistId = playlistDTO.getPlaylistId();
//
//            // playlistDTO에 playlistId 설정
//            playlistDTO.setPlaylistId(playlistId);
//
//            playlistDTO.getSongs().forEach(song -> {
//                playlistMapper.insertSong(
//                        song.getRank(),
//                        song.getTitle(),
//                        song.getArtist(),
//                        song.getAlbum(),
//                        song.getImageUrl(),
//                        playlistId
//                );
//            });
//
//            return playlistDTO;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    public List<PlaylistDTO> getPlaylistsByUserNo(int userNo) {
//        return playlistMapper.getPlaylistsByUserNo(userNo);
//
//    }
@Transactional
public PlaylistDTO savePlaylist(PlaylistDTO playlistDTO) {
    try {
        // 플레이리스트를 삽입하고, 삽입된 ID를 가져옵니다.
        playlistMapper.insertPlaylist(playlistDTO);
        int playlistId = playlistDTO.getPlaylistId();

        // playlistDTO에 playlistId 설정
        playlistDTO.setPlaylistId(playlistId);

        playlistDTO.getSongs().forEach(song -> {
            song.setPlaylistId(playlistId); // 추가된 코드: SongDTO에 playlistId 설정
            playlistMapper.insertSong(
                    song.getRank(),
                    song.getTitle(),
                    song.getArtist(),
                    song.getAlbum(),
                    song.getImageUrl(),
                    playlistId
            );
        });

        return playlistDTO;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

    public List<PlaylistDTO> getPlaylistsByUserNo(int userNo) {
        return playlistMapper.getPlaylistsByUserNo(userNo);
    }
}
