package com.example.exercices.ex14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exercices.ex14.entities.Room;
import com.example.exercices.ex14.entities.repositories.RoomRepository;

@RestController
@RequestMapping("/api/rooms")
@Profile("ex14")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping()
    public Iterable<Room> getRooms() {
        return roomRepository.findAll();
    }

    @PostMapping()
    public Room createRoom(@RequestBody Room room) {
        // CREER L'ID +1
        String randomId = String.valueOf(System.currentTimeMillis());
        room.setId(randomId);
        return roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable String id) {
        roomRepository.delete(id);
    }
}
