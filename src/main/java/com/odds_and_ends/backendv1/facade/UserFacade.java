package com.odds_and_ends.backendv1.facade;

import com.odds_and_ends.backendv1.entity.user.User;

public interface UserFacade {
    User getCurrentUser();
    User getUserById(Long id);
    User getUserByUsername(String username);
}
