package top.minecode.service.workertask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.minecode.dao.worker.WorkerInfoDao;
import top.minecode.dao.workertask.SpecificTaskDao;
import top.minecode.dao.workertask.SubTaskDao;
import top.minecode.dao.workertask.TaskDao;
import top.minecode.dao.workertask.TaskParticipationDao;
import top.minecode.domain.task.*;
import top.minecode.domain.user.worker.Division;
import top.minecode.domain.user.worker.Worker;
import top.minecode.po.task.SpecificTaskPO;
import top.minecode.po.task.TaskPO;
import top.minecode.po.worker.OnGoingTaskParticipationPO;
import top.minecode.po.worker.WorkerPO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2018/5/20.
 * Description:
 *
 * @author iznauy
 */
@Service
public class WorkerTaskBasicService {

    private WorkerInfoDao workerInfoDao;

    private TaskDao taskDao;

    private TaskParticipationDao participationDao;

    private SpecificTaskDao specificTaskDao;

    private SubTaskDao subTaskDao;

    public SubTaskDao getSubTaskDao() {
        return subTaskDao;
    }

    @Autowired
    public void setSubTaskDao(SubTaskDao subTaskDao) {
        this.subTaskDao = subTaskDao;
    }

    public SpecificTaskDao getSpecificTaskDao() {
        return specificTaskDao;
    }

    @Autowired
    public void setSpecificTaskDao(SpecificTaskDao specificTaskDao) {
        this.specificTaskDao = specificTaskDao;
    }

    public TaskParticipationDao getParticipationDao() {
        return participationDao;
    }

    @Autowired
    public void setParticipationDao(TaskParticipationDao participationDao) {
        this.participationDao = participationDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public WorkerInfoDao getWorkerInfoDao() {
        return workerInfoDao;
    }

    @Autowired
    public void setWorkerInfoDao(WorkerInfoDao workerInfoDao) {
        this.workerInfoDao = workerInfoDao;
    }

    public JoinTaskResponse joinTask(String email, int taskId) {

       // 检查时候是一个合法的、正在进行的任务
        TaskPO task = taskDao.getTaskById(taskId);
        if (task == null || task.getTaskState() != TaskState.ON_GOING)
            return new JoinTaskResponse(false, JoinTaskResponse.UNKNOWN_REASON);

        // 检测是否已经接受过任务
        List<String> participators = task.getParticipators();
        if (participators.stream().anyMatch(e -> e.equals(email)))
            return new JoinTaskResponse(false, JoinTaskResponse.HAS_ACCEPTED);


        // 判断段位条件是否满足
        double score = workerInfoDao.getWorkerPOByEmail(email).getScore();
        Division division = Division.convert(score);
        if (Division.difference(division, task.getLowestDivision()) < 0)
            return new JoinTaskResponse(false, JoinTaskResponse.DONT_FELLOW_RELU);

        // 进行接取任务的执行阶段
        participators.add(email);
        if (!taskDao.persist(task)) // error in persist
            return new JoinTaskResponse(false, JoinTaskResponse.UNKNOWN_REASON);

        OnGoingTaskParticipationPO participation = new OnGoingTaskParticipationPO();
        participation.setParticipatedSubTaskResultIds(new ArrayList<>());
        participation.setTaskId(taskId);
        participation.setUserEmail(email);

        participationDao.addOnGoingTaskParticipation(participation);

        WorkerPO worker = workerInfoDao.getWorkerPOByEmail(email);
        worker.getOnGoingTaskParticipation().add(participation.getId());

        workerInfoDao.updateWorkPO(worker);

        return new JoinTaskResponse(true, null);
    }

    public List<SubTask> getAllSubTasks(String email, int taskId, TaskType taskType) {
        TaskPO taskPO = taskDao.getTaskById(taskId);
        int specificTaskId = taskPO.getSpecificTasks().get(taskType);
        SpecificTaskPO specificTaskPO = specificTaskDao.getSpecificTaskById(specificTaskId);
        List<Integer> subTasksIds = specificTaskPO.getSubTasks(); // 获取到子任务的全部id
        return subTaskDao.getSubTasksByIdList(subTasksIds).stream().map(e -> SubTask.fromPO(e, taskId))
                .collect(Collectors.toList());
    }

    public List<SubTaskParticipation> getWorkerParticipation(String email, int taskId, SubTaskState subTaskState) {
        return null;
    }


}
