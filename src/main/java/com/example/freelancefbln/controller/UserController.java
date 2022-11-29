package com.example.freelancefbln.controller;

import com.example.freelancefbln.entity.User;
import com.example.freelancefbln.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.groups.Default;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Integer id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Validated({Default.class}) @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") Integer id,
                              @Validated({Default.class}) @RequestBody User user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return userService.deleteById(id)
                ? noContent().build()
                : notFound().build();
    }

}
