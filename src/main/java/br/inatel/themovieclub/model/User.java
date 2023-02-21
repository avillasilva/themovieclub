package br.inatel.themovieclub.model;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<User> friendshipRequests = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<User> friends = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<MovieList> movieLists = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Profile> profiles = new HashSet<>();

    public void addFriendshipRequests(User user) {
        if (user.getId() != id) {
            friendshipRequests.add(user);
        }
    }

    public void addFriend(User user) {
        if (user.getId() != id) {
            friends.add(user);
        }
    }

    public void unfriend(User user) {
        friends.remove(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
