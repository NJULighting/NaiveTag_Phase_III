package top.minecode.dao.workertask;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import top.minecode.dao.utils.CommonOperation;
import top.minecode.dao.utils.HibernateUtils;
import top.minecode.domain.task.SubTaskParticipation;
import top.minecode.po.worker.FinishedTaskParticipationPO;
import top.minecode.po.worker.OnGoingTaskParticipationPO;
import top.minecode.po.worker.SubTaskParticipationPO;

import java.util.List;

/**
 * Created on 2018/5/20.
 * Description:
 *
 * @author iznauy
 */
@Repository
public class TaskParticipationDao {

    private CommonOperation<OnGoingTaskParticipationPO> onGoingTaskParticipationHelper =
            new CommonOperation<OnGoingTaskParticipationPO>(OnGoingTaskParticipationPO.class.getName());

    private CommonOperation<FinishedTaskParticipationPO> finishedTaskParticipationHelper =
            new CommonOperation<FinishedTaskParticipationPO>(FinishedTaskParticipationPO.class.getName());

    private CommonOperation<SubTaskParticipationPO> subPartHelper =
            new CommonOperation<SubTaskParticipationPO>(SubTaskParticipationPO.class.getName());

    public void addOnGoingTaskParticipation(OnGoingTaskParticipationPO po) {
        System.out.println(onGoingTaskParticipationHelper.add(po));
    }

    public SubTaskParticipationPO getSubTaskParticipationById(int id) {
        return subPartHelper.getBySingleField("id", id);
    }

    public OnGoingTaskParticipationPO getOnGoingTaskParticipation(int id) {
        return onGoingTaskParticipationHelper.getBySingleField("id", id);
    }

    public FinishedTaskParticipationPO getFinishedTaskParticipation(int id) {
        return finishedTaskParticipationHelper.getBySingleField("id", id);
    }

    public void updateOnGoingTaskParticipation(OnGoingTaskParticipationPO po) {
        onGoingTaskParticipationHelper.update(po);
    }

    public List<OnGoingTaskParticipationPO> getOnGoingParticipation(String email) {
        return onGoingTaskParticipationHelper.getListBySingleField("userEmail", email);
    }

    public SubTaskParticipationPO getWorkerSubTaskParticipation(String email, int taskId, int subTaskId) {
        String hql = "select t from " + SubTaskParticipationPO.class.getName() +
                " t where t.email = ? and t.taskId = ? and t.subTaskId = ?";
        Session session = HibernateUtils.getCurrentSession();
        SubTaskParticipationPO po = null;
        try {
            session.getTransaction().begin();
            po = (SubTaskParticipationPO) session.createQuery(hql).setParameter(0, email)
                    .setParameter(1, taskId).setParameter(2, subTaskId).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.closeSession();
        }
        return po;
    }

    public void updateSubTaskParticipation(SubTaskParticipationPO po) {
        subPartHelper.update(po);
    }

}
