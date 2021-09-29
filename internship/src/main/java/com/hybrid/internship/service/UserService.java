package com.hybrid.internship.service;

import com.hybrid.internship.exception.EntityNotFoundException;
import com.hybrid.internship.model.User;

import java.util.List;

public interface UserService {
    /**
     *
     * @return - All users within the system
     */
    List<User> findAll();

    /**
     *
     * @param newUser - User to be added
     * @return - Newly added user
     */
    User insert(User newUser);

    /**
     *
     * @param id - ID of the user that we're looking for, cannot be null
     * @return - User with the given id
     * @throws EntityNotFoundException if no user with given id is found
     */
    User findById(Long id) throws EntityNotFoundException;

    /**
     *
     * @param id - ID of the user that we're deleting, cannot be null
     */
    void delete(Long id);

    /**
     *
     * @param id - ID of the user that we're updating, cannot be null
     * @param newUser - New user to replace the one with given ID, fields cannot be null
     * @return - Newly replaced user
     */
    User update(Long id, User newUser);

    User insertAdmin(User user);
}
