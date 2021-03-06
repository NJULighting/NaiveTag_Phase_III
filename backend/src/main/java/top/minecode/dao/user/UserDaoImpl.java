package top.minecode.dao.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import top.minecode.dao.utils.CommonOperation;
import top.minecode.domain.user.User;
import top.minecode.po.requester.RequesterPO;
import top.minecode.po.worker.WorkerPO;

import java.util.Date;

/**
 * Created on 2018/5/19.
 * Description:
 * @author Liao
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    private CommonOperation<WorkerPO> workerOperation = new CommonOperation<>(WorkerPO.class.getName());
    private CommonOperation<RequesterPO> requesterOperation = new CommonOperation<>(RequesterPO.class.getName());

    @Override
    public User getUser(String email) {
        WorkerPO workerPO = workerOperation.getBySingleField("email", email);

        // If the user is a worker
        if (workerPO != null) {
            return User.worker(email, workerPO.getName(), workerPO.getDollars(),
                    workerPO.getJoinTime(), workerPO.getAvatar(), workerPO.getScore());
        }

        // If the user is a requester
        RequesterPO requesterPO = requesterOperation.getBySingleField("email", email);
        if (requesterPO != null) {
            return User.requester(email, requesterPO.getName(), requesterPO.getDollars(),
                    requesterPO.getJoinTime(), requesterPO.getAvatar());
        }

        // The user is neither a worker nor a requester
        return null;
    }

    @Override
    public void addWorker(String email, String pwd, String name, Date joinTime, String avatar) {
        WorkerPO workerPO = new WorkerPO(email, pwd, name, joinTime, avatar);
        if (!workerOperation.add(workerPO))
            log.debug("worker: " + email + " fail to add to the database");
    }

    @Override
    public void addRequester(String email, String pwd, String name, Date joinTime, String avatar) {
        RequesterPO requesterPO = new RequesterPO(email, name, pwd, joinTime, avatar);
        if (!requesterOperation.add(requesterPO))
            log.debug("requester: " + email + " fail to add to the database");

    }
}
