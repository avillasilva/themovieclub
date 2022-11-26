package br.com.inatel.themovieclub.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.inatel.themovieclub.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shoudLoadUserByEmail() {
        String email = "luiz.souza@email.com";
        User user = userRepository.findByEmail(email).get();
        Assert.assertEquals(user.getEmail(), email);
    }

    @Test
    public void shouldNotLoadUserByEmail() {
        String email = "alessandro.nobrega@email.com";
        Optional<User> optional = userRepository.findByEmail(email);

        Assert.assertTrue(optional.isEmpty());
    }

}
