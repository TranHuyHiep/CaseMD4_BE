package com.codegym.service;

import com.codegym.model.Role;
import com.codegym.model.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class UserService {
    public static List<Account> listAccount = new ArrayList<Account>();

    static {
        Account accountKai = new Account();
        accountKai.setId(1L);
        accountKai.setUsername("kai");
        accountKai.setPassword("123456");

        Role role_admin = Role.builder().name("ROLE_ADMIN").build();
        HashSet<Role> setRole = new HashSet<>();
        setRole.add(role_admin);
        accountKai.setRoles(setRole);

        Role role_user = Role.builder().name("ROLE_USER").build();
        HashSet<Role> senaRoles = new HashSet<>();
        senaRoles.add(role_user);

        Account accountSena = Account.builder()
                .id(2L).username("sena").password("123456").roles(senaRoles)
                .build();



        System.out.println(accountSena.getPassword());
        listAccount.add(accountKai);
        listAccount.add(accountSena);
    }

    public List<Account> findAll() {
        return listAccount;
    }

    public Account findById(Long id) {
        for (Account account : listAccount) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    public boolean add(Account account) {
        for (Account accountExist : listAccount) {
            if (account.getId() == accountExist.getId() || StringUtils.equals(account.getUsername(), accountExist.getUsername())) {
                return false;
            }
        }
        listAccount.add(account);
        return true;
    }

    public void delete(int id) {
        listAccount.removeIf(user -> user.getId() == id);
    }

    public Account loadUserByUsername(String username) {
        for (Account account : listAccount) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

//    public boolean checkLogin(Account account) {
//        for (Account accountExist : listAccount) {
//            if (StringUtils.equals(account.getUsername(), accountExist.getUsername())
//                    && StringUtils.equals(account.getPassword(), accountExist.getPassword())) {
//                return true;
//            }
//        }
//        return false;
//    }
}