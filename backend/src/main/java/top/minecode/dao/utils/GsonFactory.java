package top.minecode.dao.utils;

import com.google.gson.*;
import top.minecode.domain.statistic.ChartData;
import top.minecode.domain.task.requester.RequesterTaskItem;
import top.minecode.domain.task.requester.RequesterTaskDetails;
import top.minecode.domain.user.requester.AccountLog;
import top.minecode.domain.utils.TimeMessageConverter;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created on 2018/5/23.
 * Description:
 * @author Liao
 */
public class GsonFactory {

    private static Gson defaultGson = new GsonBuilder().serializeNulls().create();
    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(AccountLog.class, new AccountLog.AccountLogSerializer())
                .registerTypeAdapter(RequesterTaskItem.class, taskItemSerializer())
                .registerTypeAdapter(RequesterTaskDetails.class, taskDetailsJsonSerializer())
                .registerTypeAdapter(ChartData.class, chartDataJsonSerializer())
                .serializeNulls();
        gson = builder.create();
    }

    public static Gson getGson() {
        return gson;
    }

    private static JsonSerializer<RequesterTaskItem> taskItemSerializer() {
        return (taskItem, type, jsonSerializationContext) -> {
            JsonElement element = defaultGson.toJsonTree(taskItem);
            JsonObject object = element.getAsJsonObject();
            // Remove start date and end date
            object.remove("begin");
            object.remove("deadline");

            // Serialize time information
            String timeInfo = TimeMessageConverter.convertStartTime(taskItem.getBegin()) + ", ";
            timeInfo += TimeMessageConverter.convertDeadline(taskItem.getDeadline());
            object.addProperty("timeInfo", timeInfo);

            return object;
        };
    }

    private static JsonSerializer<RequesterTaskDetails> taskDetailsJsonSerializer() {
        return (taskDetails, type, jsonSerializationContext) -> {
            JsonElement element = defaultGson.toJsonTree(taskDetails);
            JsonObject object = element.getAsJsonObject();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            object.addProperty("deadline", dateFormat.format(taskDetails.getDeadline()));

            // Remove begin date and add time information
            object.remove("begin");
            String timeInfo = TimeMessageConverter.convertStartTime(taskDetails.getBegin()) +
                    ", " + TimeMessageConverter.convertDeadline(taskDetails.getDeadline());
            object.addProperty("timeInfo", timeInfo);
            object.addProperty("state", taskDetails.getState().toString().toLowerCase());

            return object;
        };
    }

    private static JsonSerializer<ChartData> chartDataJsonSerializer() {
        return (chartData, type, jsonSerializationContext) -> {
            JsonArray vectors = jsonSerializationContext.serialize(chartData.getVectors()).getAsJsonArray();
            JsonArray fields = jsonSerializationContext.serialize(chartData.getFields()).getAsJsonArray();
            JsonObject result = new JsonObject();

            for (JsonElement element : vectors) {
                // This object represent a vector of ChartData
                JsonObject vector = element.getAsJsonObject();
                result.add(vector.get("name").getAsString(), vector.get("values").getAsJsonArray());
            }

            for (JsonElement element : fields) {
                JsonObject field = element.getAsJsonObject();
                result.add(field.get("name").getAsString(), field.get("value"));
            }

            return result;
        };
    }
}
