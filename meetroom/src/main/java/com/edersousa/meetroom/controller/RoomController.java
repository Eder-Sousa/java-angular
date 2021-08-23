package com.edersousa.meetroom.controller;

import com.edersousa.meetroom.exception.ResourceNotFoundException;
import com.edersousa.meetroom.model.Room;
import com.edersousa.meetroom.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/rooms")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class RoomController {

    private RoomRepository repository;

    @GetMapping
    public List<Room> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Room room = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found:: " + id));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping
    public Room create(@Valid @RequestBody Room room) {
        return repository.save(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable(value = "id") long id,
                                        @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException {
        Room room = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " + id));
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartHour(roomDetails.getStartHour());
        room.setEndHour(roomDetails.getEndHour());

        final Room updateRoom = repository.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Room room = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " + id));
        repository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
