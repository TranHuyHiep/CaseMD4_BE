package com.codegym.service.accountService;

import com.codegym.model.Account;
import com.codegym.model.AppUser;
import com.codegym.model.Role;
import com.codegym.repository.IAccountRepo;
import com.codegym.repository.IAppUserRepo;
import com.codegym.service.appUserService.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AccountService implements IAccountService {
    private final String NOT_VERIFIED = "0";
    @Autowired
    IAccountRepo accountRepo;
    @Autowired
    IAppUserRepo iAppUserRepo;

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    /**
     *
     * @param account include username, email, password
     * @return True if username & email are not existed
     */
    @Override
    public boolean save(Account account) {
//        boolean check = accountRepo.existsAccountByUsername(account.getUsername()) &&
//                accountRepo.existsAccountByEmail(account.getEmail());
        boolean check = accountRepo.existsAccountByUsernameOrEmail(account.getUsername(), account.getEmail());
        if (!check) {
            accountRepo.save(account);
            AppUser newUser = new AppUser();
            newUser.setAccount(account);
            newUser.setAddress("Ha Giang");
            newUser.setEmail(account.getEmail());
            newUser.setBGImage("https://image.thanhnien.vn/w1024/Uploaded/2022/hgnatm/2022_09_22/yamaha-r152-3948.jpg");
            newUser.setAboutMe("Cute");
            newUser.setDisplayName(account.getUsername());
            iAppUserRepo.save(newUser);
            //accountRepo.save(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (accountRepo.existsById(id)) {
            accountRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Account findById(Long id) {
        Optional<Account> account = accountRepo.findById(id);
        boolean c = account.isPresent();
        return account.get();
    }

    public Account findByUserName(String username) {
        return accountRepo.findAccountByUsername(username);
    }

    /**
     * @param account include username and password
     * @return account if existed, null if not found this account
     */
    public Account checkLogin(Account account) {
        Account accountRs = accountRepo.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        return accountRs;
    }

    public List<GrantedAuthority> getAuthorities(Long accountID) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roleList = findById(accountID).getRoles();
        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
