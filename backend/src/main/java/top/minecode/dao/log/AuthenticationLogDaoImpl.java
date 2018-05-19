package top.minecode.dao.log;

import org.springframework.stereotype.Repository;
import top.minecode.dao.utils.CommonOperation;
import top.minecode.domain.user.UserType;
import top.minecode.po.log.LoginLogPO;
import top.minecode.po.log.RegisterLogPO;

import java.util.Date;

/**
 * Created on 2018/5/19.
 * Description:
 * @author Liao
 */
@Repository
public class AuthenticationLogDaoImpl implements AuthenticationLogDao {

    private CommonOperation<LoginLogPO> loginOperation = new CommonOperation<LoginLogPO>(LoginLogPO.class);
    private CommonOperation<RegisterLogPO> registerOperation = new CommonOperation<RegisterLogPO>(RegisterLogPO.class);

    @Override
    public void recordLogin(String userEmail, Date loginTime, UserType userType) {
        loginOperation.add(new LoginLogPO(userEmail, loginTime, userType));
    }

    @Override
    public void recordSignup(String userEmail, Date registerDate, UserType userType) {
        registerOperation.add(new RegisterLogPO(userEmail, registerDate, userType));
    }
}