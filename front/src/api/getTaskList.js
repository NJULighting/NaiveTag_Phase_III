import {getUrl} from "./tool";

export function getTaskList(token,calback) {
  getTaskListFromServer(token,calback);
}

function getTaskListFromServer(token,callback) {
  $.get(getUrl('requester/task/ongoing.html'),{token:token},function (ongoing) {
    $.get(getUrl('requester/task/done.html'),{token:token},function (done) {
      callback(JSON.parse(ongoing),JSON.parse(done));
    })
  })
}


function mock(token,callback){
  let ongoing=[
    {
      "title" : "The First Task",
      "description" : "It is the first task,have a try aaa aaaaaaa aaaaa aaaa aaaaa aaaa aaaaaa aaaaaa aaaaa aaaaaa aaaa aaaa aaaaaaaaaaaa",
      "tags" : ["education", "marketing", "animal","dog"],
      "dollars" : "2,000",
      "participantsNum" : 1234,
      "pictureNum" : 1234,
      "subtaskNum" : 230,
      "cover":"/static/1.png",
      "timeInfo" : "upload 2 months ago, 30 days to go",
      "workerRequirement" : "master",
      "taskId":"0001"
    },
    {
      "title" : "The Second Task",
      "description" : "It is the second task,go on",
      "tags" : ["education", "marketing","dog","software", "animal"],
      "dollars" : "2,000",
      "participantsNum" : 224,
      "cover":"/static/1.png",
      "pictureNum" : 254,
      "subtaskNum" : 20,
      "timeInfo" : "upload 2 months ago, 30 days to go",
      "workerRequirement" : "novice",
      "taskId":"0001"
    },
    {
      "title" : "The Third Task",
      "description" : "It is the last task orz",
      "tags" : ["dog"],
      "dollars" : "2,000",
      "participantsNum" : 134,
      "pictureNum" : 234,
      "cover":"/static/1.png",
      "subtaskNum" : 30,
      "timeInfo" : "upload 2 months ago, 30 days to go",
      "workerRequirement" : "contributor",
      "taskId":"0001"
    }
  ];

  let done=[
    {
      "title" : "The First Task",
      "description" : "This task is done",
      "tags" : ["education", "marketing", "animal","dog"],
      "dollars" : "2,000",
      "participantsNum" : 1234,
      "cover":"/static/1.png",
      "pictureNum" : 1234,
      "subtaskNum" : 230,
      "timeInfo" : "upload 2 months ago, 30 days to go",
      "workerRequirement" : "novice",
      "taskId":"0001"
    },
    {
      "title" : "The First Task",
      "description" : "This task is done",
      "tags" : ["education", "marketing", "animal","dog"],
      "dollars" : "2,000",
      "participantsNum" : 1234,
      "cover":"/static/1.png",
      "pictureNum" : 1234,
      "subtaskNum" : 230,
      "timeInfo" : "upload 2 months ago, 30 days to go",
      "workerRequirement" : "expert",
      "taskId":"0001"
    }

  ];

  callback(ongoing,done);

}
