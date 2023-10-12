package com.example.service.before;

import com.example.entity.BUser;
import com.example.repository.before.UserRepository;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String register(BUser bUser) {
        //对密码MD5加密
        bUser.setBpwd(MD5Util.MD5(bUser.getBpwd()));
        if (userRepository.register(bUser) > 0) {
            return "user/login";
        }
        return "user/register";
    }

    @Override
    public String login(BUser bUser, HttpSession session, Model model) {
        //对密码MD5加密
        bUser.setBpwd(MD5Util.MD5(bUser.getBpwd()));
        String rand = (String) session.getAttribute("rand");
        if (!rand.equalsIgnoreCase(bUser.getCode())) {
            model.addAttribute("errorMessage", "验证码错误！");
            return "user/login";
        }
        List<BUser> list = userRepository.login(bUser);
        if (list.size() > 0) {
            session.setAttribute("bUser", list.get(0));
            return "redirect:/";//到首页
        }
        model.addAttribute("errorMessage", "用户名或密码错误！");
        return "user/login";
    }
}

