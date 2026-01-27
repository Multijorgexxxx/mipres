package com.mipres.service;

import java.util.List;

import com.mipres.entity.Menu;

public interface MenuService {

    public List<Menu> getMenus(String role);

}
