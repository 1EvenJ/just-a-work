package com.example.repository.before;

import com.example.entity.BUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    public int register(BUser bUser);
    public List<BUser> login(BUser bUser);
}
