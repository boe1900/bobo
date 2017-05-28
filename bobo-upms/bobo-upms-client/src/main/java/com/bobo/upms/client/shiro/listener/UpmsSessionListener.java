package com.bobo.upms.client.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huabo on 2017/5/27.
 */
public class UpmsSessionListener implements SessionListener {
    private static Logger _log = LoggerFactory.getLogger(UpmsSessionListener.class);

    @Override
    public void onStart(Session session) {
        _log.debug("会话创建："+session.getId());
    }

    @Override
    public void onStop(Session session) {
        _log.debug("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        _log.debug("会话过期：" + session.getId());
    }
}
