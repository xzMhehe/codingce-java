package cn.com.codingce.demo.conrtoller;

import cn.com.codingce.demo.utils.OpenAiUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import com.theokanning.openai.completion.CompletionChoice;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mxz
 */
@RestController
@RequestMapping("index")
@Slf4j
public class IndexController {

    @Autowired
    private OpenAiUtil openAiUtil;

    /**
     * default
     */
    @GetMapping(value = "/default", produces = "text/html;charset=utf-8")
    @ApiOperation(value = "default")
    public String getDefault() throws InterruptedException {
        return "服务运行正常...";
    }

    /**
     * web
     *
     * @param content
     * @return
     */
    @GetMapping(value = "/api/{content}", produces = "text/html;charset=utf-8")
    public String outWeb(@PathVariable("content") String content, HttpServletRequest request) {
        String clientIp = ServletUtil.getClientIP(request, null);
        log.info("接口 outWeb goto content: {}, ip: {}", content, clientIp);
        String res = "系统思考中...";
        try {
            List<CompletionChoice> completionChoices = openAiUtil.sendComplete(content);
            res = JSONUtil.toJsonStr(completionChoices.stream().map(CompletionChoice::getText).collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("出错了 e:{}", e.getMessage());

            return "抱歉服务器出错了~";
        }
        log.info("res:{}", res);
        res.replaceAll("\n", "").replaceAll("\"", "").replaceAll("\\]", "").replaceAll("\\[", "");
        log.info("replaceAll res:{}", res);

        return res;
    }

    @PostMapping("/receive")
    public String helloRobots(@RequestBody(required = false) JSONObject json) {
        System.out.println(JSONUtil.toJsonStr(json));
        String content = json.getJSONObject("text").get("content").toString().replaceAll(" ", "");
        String sessionWebhook = json.getStr("sessionWebhook");
        DingTalkClient client = new DefaultDingTalkClient(sessionWebhook);
        if ("text".equals(json.getStr("msgtype"))) {
            text(client, content);
        }

        return null;
    }

    /**
     * DingTalk
     *
     * @param client  DingTalkClient
     * @param content String
     */
    private void text(DingTalkClient client, String content) {
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            List<CompletionChoice> completionChoices = openAiUtil.sendComplete(content);
            System.out.println(JSONUtil.toJsonStr(completionChoices.stream().map(it -> it.getText()).collect(Collectors.toList())));
            text.setContent(completionChoices.stream().map(it -> it.getText()).collect(Collectors.joining("")));
            request.setText(text);
            OapiRobotSendResponse response = client.execute(request);
            System.out.println(response.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


}
