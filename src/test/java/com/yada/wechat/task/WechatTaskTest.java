package com.yada.wechat.task;

import com.yada.wechat.service.WechatService;
import com.yada.wechat.stream.Event;
import com.yada.wechat.stream.PlanTaskProducer;
import com.yada.wechat.stream.WechatProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WechatTask.class})
public class WechatTaskTest {

    @Autowired
    private WechatTask wechatTask;

    @MockBean
    private WechatService wechatService;

    @MockBean
    private WechatProducer wechatProducer;

    @MockBean
    private PlanTaskProducer planTaskProducer;


    @Test
    public void doUnifiedOrder() throws Exception {

    }

    @Test
    public void doRefund() throws Exception {

        Map<String, String> resSuccess = new HashMap<String, String>();
        resSuccess.put("trade_no", "trade_no");

        BDDMockito.given(wechatService.refund(BDDMockito.anyMapOf(String.class, String.class)))
                .willReturn(resSuccess);

        Map<String, String> payload = new HashMap<String, String>();
        wechatTask.doRefund(payload);

        BDDMockito.verify(wechatProducer, times(1)).send(BDDMockito.any(Event.class));
    }

    @Test
    public void doQuery() throws Exception {


    }

}