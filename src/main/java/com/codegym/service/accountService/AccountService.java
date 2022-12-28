package com.codegym.service.accountService;

import com.codegym.model.Account;
import com.codegym.model.AppUser;
import com.codegym.model.Role;
import com.codegym.repository.IAccountRepo;
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
    IAppUserService appUserService;

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
//            AppUser newUser = AppUser.builder()
//                    .account(findByUserName(account.getUsername()))
//                    .status(NOT_VERIFIED)
//                    .displayName("Nana")
//                    .build();
//            newUser.setEmail(account.getEmail());

            AppUser newUser = new AppUser();
            newUser.setAccount(account);
            newUser.setBGImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJwxK-xA3172T2FLzdwm1YjDK2X_r49wNVcw&usqp=CAU");
            newUser.setDOB(LocalDate.now());
            newUser.setAboutMe("HipCG");
            newUser.setDisplayName("Hip Chan");
            newUser.setEmail(account.getEmail());
            newUser.setGender("Male");
            newUser.setImage("https://static2.yan.vn/YanNews/202105/202105170329163774-0f5aef49-3c30-420b-8ccd-b983d0d3553a.jpeg");
            newUser.setPhoneNumber("19001001");
            newUser.setStatus("active");
            newUser.setAddress("Ha Giang");
            appUserService.save(newUser);
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
