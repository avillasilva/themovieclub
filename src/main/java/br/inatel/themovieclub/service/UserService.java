package br.inatel.themovieclub.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.themovieclub.model.User;
import br.inatel.themovieclub.repository.UserRepository;

@Service
public class UserService {

    private static final String USER_NOT_FOUND_MESSAGE = "User with id %d not found.";

    @Autowired
    private UserRepository userRepository;

    public Page<User> search(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User read(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(formatMessage(userId)));
    }

   @Transactional
   public User save(User user) {
       return userRepository.save(user);
   }

   @Transactional
   public void delete(Long userId) {
       try {
           userRepository.deleteById(userId);
           userRepository.flush();
       } catch (EmptyResultDataAccessException e) {
           throw new EntityNotFoundException(formatMessage(userId));
       }
   }

   private String formatMessage(Long userId) {
       return String.format(USER_NOT_FOUND_MESSAGE, userId);
   }

}
