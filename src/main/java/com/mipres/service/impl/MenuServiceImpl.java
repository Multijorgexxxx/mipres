package com.mipres.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mipres.entity.Menu;
import com.mipres.repository.MenuRepository;
import com.mipres.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
    
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getMenus(String role) {
        return menuRepository.findMenusPrincipales();
    }

}
