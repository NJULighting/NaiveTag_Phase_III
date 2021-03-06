package top.minecode.web.workertask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.minecode.domain.task.RankType;
import top.minecode.domain.task.TaskTag;
import top.minecode.domain.task.TaskType;
import top.minecode.service.workertask.WorkerTaskSearchService;
import top.minecode.web.common.BaseController;
import top.minecode.web.common.WebConfig;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/5/19.
 * Description:
 *
 * @author iznauy
 */
@Controller
@RequestMapping(value = "/worker/task")
public class WorkerTaskSearchController extends BaseController {

    private WorkerTaskSearchService service;

    public WorkerTaskSearchService getService() {
        return service;
    }

    @Autowired
    public void setService(WorkerTaskSearchService service) {
        this.service = service;
    }

    @RequestMapping("/search")
    @ResponseBody
    public String searchTasks(HttpServletRequest request,  int taskType,
                              String taskTag,
                              String rankType, int begin, int step,
                              String key, boolean canAccept) {
        String email = getUserEmail(request);
        TaskType taskTp = TaskType.convert(taskType);
        TaskTag taskTg = TaskTag.valueOf(taskTag);
        RankType rankTp = RankType.valueOf(rankType);
        return WebConfig.getGson().toJson(service
                .searchTask(email, taskTp, taskTg, rankTp, begin, step, key, canAccept));
    }

}
