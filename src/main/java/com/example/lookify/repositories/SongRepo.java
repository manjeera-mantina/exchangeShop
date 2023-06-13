package com.example.lookify.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lookify.models.Song;

@Repository
public interface SongRepo extends CrudRepository<Song, Long> {
	
	List<Song> findAll();
	
	List<Song> findByArtistContaining(String artist);
	List<Song> findByTitleContaining(String title);
	
	List<Song> findAllByOrderByRatingDesc();
	List<Song> findTopByOrderByRatingDesc();
	
	@Query(value = "SELECT * FROM songs ORDER BY rating DESC LIMIT 10", nativeQuery = true) 
	List<Song> getTopTen();
}
