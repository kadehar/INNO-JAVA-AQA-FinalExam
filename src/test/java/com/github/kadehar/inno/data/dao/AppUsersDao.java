package com.github.kadehar.inno.data.dao;

import com.github.kadehar.inno.data.entity.AppUserEntity;

public interface AppUsersDao {
    AppUserEntity create(AppUserEntity user);
    void deleteAppUserById(Long id);
}
