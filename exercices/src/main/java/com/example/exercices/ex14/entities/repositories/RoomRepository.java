package com.example.exercices.ex14.entities.repositories;

import com.example.exercices.ex14.entities.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("ex14")
public class RoomRepository {
    private List<Room> rooms;

    public RoomRepository() {
        this.rooms = new ArrayList<>();
        initializeMockData();
    }

    private void initializeMockData() {
        rooms.add(new Room("1", "Salle de conférence A"));
        rooms.add(new Room("2", "Salle de conférence B"));
        rooms.add(new Room("3", "Bureau de direction"));
        rooms.add(new Room("4", "Salle de réunion"));
    }

    public List<Room> findAll() {
        return new ArrayList<>(rooms);
    }

    public Optional<Room> findById(String id) {
        return rooms.stream()
                .filter(room -> room.getId().equals(id))
                .findFirst();
    }

    public Room save(Room room) {
        rooms.add(room);
        return room;
    }

    public void delete(String id) {
        rooms.removeIf(room -> room.getId().equals(id));
    }
}
