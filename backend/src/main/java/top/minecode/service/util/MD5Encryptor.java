package top.minecode.service.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;
import top.minecode.web.user.SignupCommand;

/**
 * Created on 2018/5/22.
 * Description:
 * @author Liao
 */
public class MD5Encryptor implements Encryptor {

    private int iteration;

    @Override
    public String encrypt(SignupCommand signupCommand) {
        return new Md5Hash(signupCommand.getPassword(), signupCommand.getEmail(), iteration).toString();
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
}
