package com.example.service.before;

import com.example.entity.Goods;
import com.example.entity.Order;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface CartService {
    public String updateUpwd(HttpSession session, String bpwd);
    public String selectCart(Model model, HttpSession session, String act);
    public String clearCart(HttpSession session);
    public String deleteCart(HttpSession session, Integer gid);
    public String putCart(Goods goods,Model model,HttpSession session);
    public String focus(Model model, HttpSession session, Integer gid);
    public String myFocus(Model model, HttpSession session);
    public String myOder(Model model, HttpSession session);
    public String pay(Order order);

}
