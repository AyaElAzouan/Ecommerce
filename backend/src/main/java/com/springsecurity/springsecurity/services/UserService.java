package com.springsecurity.springsecurity.services;

import com.springsecurity.springsecurity.EXception.DataInvalid;
import com.springsecurity.springsecurity.EXception.NotFoundException;
import com.springsecurity.springsecurity.auth.AuthenticationSercvice;
import com.springsecurity.springsecurity.auth.RegisterRequest;
import com.springsecurity.springsecurity.entities.User;
import com.springsecurity.springsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private AuthenticationSercvice authenticationSercvice;
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user)throws DataInvalid {
        if (user == null) throw new DataInvalid("Il faut remplir les champs !!");
        // Enregistre l'utilisateur
        return authenticationSercvice.register(user);
    }

    public boolean isEmailTaken(String email) {
        // Utilisez la méthode findByEmail pour vérifier si l'email existe
        return userRepository.findByEmail(email).isPresent();
    }
    public void deleteUser(Integer id_user) throws DataInvalid {
        boolean existe = userRepository.existsById(id_user);
        if (!existe){
            throw new DataInvalid(
                    " User with id "+id_user+" does not exist ");
        }
        userRepository.deleteById(id_user);

    }

    public User editUser(Integer id_user, User newUser){
        User existingUser = userRepository.findById(id_user)
                .orElseThrow(() -> new NotFoundException("User non trouvée avec l'ID : " + id_user));

        existingUser.setEmail(newUser.getEmail());
        existingUser.setPassword(newUser.getPassword());

        return userRepository.save(existingUser);

    }

}

