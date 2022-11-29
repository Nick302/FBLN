package com.example.freelancefbln.service;

import com.example.freelancefbln.dao.UserRepository;
import com.example.freelancefbln.entity.Role;
import com.example.freelancefbln.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(Integer id){
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public Optional<User> update(Integer id, User user){
        return userRepository.findById(id)
                .map(entity -> {
                    return userRepository.save(user);
                });
    }

    @Transactional
    public User save(User user){
        user.setRole(Role.valueOf("USER"));
        String a = passwordEncoder.encode(user.getPassword());
        user.setPassword(a);
        return userRepository.save(user);
    }

}