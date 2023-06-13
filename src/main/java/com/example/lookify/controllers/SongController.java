package com.example.lookify.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lookify.models.Song;
import com.example.lookify.services.SongService;

@Controller
public class SongController {
	
	@Autowired
	SongService songService;
	
	@GetMapping("/")
	public String index() {
		return "index.jsp";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(@ModelAttribute("song") Song song, Model model) {
		List<Song> songs = songService.allSongs();
		model.addAttribute("songs", songs);
		return "dashboard.jsp";
	}
	
	@PostMapping("/dashboard")
	public String dashboard(@RequestParam("artist") String artist, Model model) {
		model.addAttribute("artist", artist);
		return "redirect:/search/"+artist;
	}
	
	@GetMapping("/songs/new")
	public String addSong(@ModelAttribute("song") Song song) {
		return "add.jsp";
	}
	
	@PostMapping("/songs/new")
	public String addSong(@Valid @ModelAttribute("song") Song song, BindingResult result) {
		if(result.hasErrors()) {
			return "add.jsp";
		}else {
			songService.addSong(song);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/songs/{id}")
	public String details(@PathVariable("id") Long id, Model model) {
		Song song = songService.findSong(id);
		model.addAttribute("song", song);
		return "details.jsp";
	}
	
	@GetMapping("/search/topTen")
	public String details(Model model) {
		List<Song> songs = songService.topTen();
		model.addAttribute("songs", songs);
		return "top.jsp";
	}
	
	@GetMapping("/search/{artist}")
	public String search(@PathVariable("artist") String artist, Model model) {
		List<Song> songs = songService.findByArtist(artist);
//		List<Song> songs = songService.findByTitle(artist);
		model.addAttribute("songs", songs);
		return "search.jsp";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteSong(@PathVariable("id") Long id) {
		songService.deleteSong(songService.findSong(id));
		return "redirect:/dashboard";
	}
	
}
