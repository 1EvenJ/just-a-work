package com.example.repository.admin;

import com.example.entity.AUser;
import jdk.Exported;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AdminRepository {
    List<AUser> login(AUser aUser);
}
