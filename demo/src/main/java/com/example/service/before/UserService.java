package com.example.service.before;

import com.example.entity.BUser;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface UserService {
    public String register(BUser bUser);
    public String login(BUser bUser, HttpSession session, Model model);
}
