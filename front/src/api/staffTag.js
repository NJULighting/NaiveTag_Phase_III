import {getUrl} from "./tool";
import {getTaskIdToServer} from "./taskTypeName";

export function staffTaskInfo(taskId,subTaskId,taskType,callback) {

	$.ajax({
		url: getUrl('staff/tag/taskInfo.html'),
		type: 'GET',
		// xhrFields:{withCredentials:true},
		data: {
			'token': localStorage.token,
			"taskId" : taskId,
			"subTaskId": subTaskId,
			"taskType": getTaskIdToServer(taskType),
		},
		success: function(result){
			callback(JSON.parse(result));
		},
		// contentType:"application/x-www-form-urlencoded; charset=utf-8"
	})
}
export function staffLabelInfo(taskId,subTaskId,taskType, picUrl, callback) {

	$.ajax({
		url: getUrl('staff/tag/getLabelInfo.html'),
		type: 'GET',
		// xhrFields:{withCredentials:true},
		data: {
			'token': localStorage.token,
			"taskId" : taskId,
			"subTaskId": subTaskId,
			"taskType": getTaskIdToServer(taskType),
			"url" : picUrl
		},
		success: function(result){
			callback(JSON.parse(result));
		},
		// contentType:"application/x-www-form-urlencoded; charset=utf-8"
	})
}
export function staffSave(taskId,subTaskId,taskType,picUrl,JSONObject,callback) {

	$.ajax({
		url: getUrl('staff/tag/save.html'),
		type: 'POST',
		// xhrFields:{withCredentials:true},
		data: {
			'token': localStorage.token,
			"taskId": taskId,
			"subTaskId": subTaskId,
			"taskType": getTaskIdToServer(taskType),
			"url": picUrl, // 图片的url
			"data": JSONObject //迭代一中已经定义好的图片标注信息
		},
		success: function(result){
			callback(result);
		},
		// contentType:"application/x-www-form-urlencoded; charset=utf-8"
	})
}
export function staffNext(taskId,subTaskId,taskType, picUrl, callback) {

	$.ajax({
		url: getUrl('staff/tag/next.html'),
		type: 'GET',
		// xhrFields:{withCredentials:true},
		data: {
			'username': localStorage.username,
			"taskId" : taskId,
			"subTaskId": subTaskId,
			"taskType": getTaskIdToServer(taskType),
			"url": picUrl // 当前图片的URL
		},
		success: function(result){
			callback(JSON.parse(result));
		},
		// contentType:"application/x-www-form-urlencoded; charset=utf-8"
	})
}
export function staffPrevious(taskId,subTaskId,taskType, picUrl, callback) {

	$.ajax({
		url: getUrl('staff/tag/previous.html'),
		type: 'GET',
		// xhrFields:{withCredentials:true},
		data: {
			'username': localStorage.username,
			"taskId" : taskId,
			"subTaskId": subTaskId,
			"taskType": getTaskIdToServer(taskType),
			"url": picUrl // 当前图片的URL
		},
		success: function(result){
			callback(JSON.parse(result));
		},
		// contentType:"application/x-www-form-urlencoded; charset=utf-8"
	})
}