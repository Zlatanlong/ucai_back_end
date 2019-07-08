package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upctx.qi_back_end.domain.User;
import upctx.qi_back_end.repository.UserRepository;
import upctx.qi_back_end.service.RegisterService;
import upctx.qi_back_end.util.SendRegMailUtil;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SendRegMailUtil sendRegMailUtil;

    @Override
    public Boolean activateEmail(String code) {
        Optional<User> user = userRepository.findByActiveCode(code);
        if (!user.isPresent()) {
            return false;
        } else {
            user.get().setActiveStatus(1);
            user.get().setUpdateTime(new Date());
            userRepository.save(user.get());
            return true;
        }
    }

    @Transactional
    @Override
    public Boolean doRegister(String email, String password, String name) {
        if (!hasEmail(email) && !hasName(name)) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setActiveStatus(0);
            String code = UUID.randomUUID().toString() + UUID.randomUUID().toString();
            user.setActiveCode(code);
            if (sendRegMailUtil.send(email, code)) {
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Boolean hasEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Boolean hasName(String name) {
        return userRepository.findByName(name).isPresent();
    }
}
