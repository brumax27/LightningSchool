package com.lightning.school.mvc.delegate.crud;

import com.lightning.school.mvc.api.out.UserItem;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCrudServiceImpl {

    private UserRepository userRepository;

    public UserCrudServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserItem> getAll(){
        return userRepository.findAll().stream().map(UserItem::new).collect(Collectors.toList());
    }

    public UserItem getById(Integer id){
        return new UserItem(userRepository.getUserById(id));
    }

    public User getByIdUser(Integer id){
        return userRepository.getUserById(id);
    }

    public UserItem save(User user){
        return new UserItem(userRepository.save(user));
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getByMail(String mail) {
        return userRepository.getUserByMail(mail);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
