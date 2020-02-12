package com.gis_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * CAS的配置参数
 */
@Component
public class CasProperties {

    @Value("${security.cas.server.host.url}")
    private String casServerUrl;

    @Value("${security.cas.server.host.login_url}")
    private String casServerLoginUrl;

    @Value("${security.cas.server.host.logout_url}")
    private String casServerLogoutUrl;

    @Value("${security.app.server.host.url}")
    private String appServerUrl;

    @Value("${security.app.login.url}")
    private String appLoginUrl;

    @Value("${security.app.logout.url}")
    private String appLogoutUrl;

    public String getCasServerUrl() {
        return casServerUrl;
    }

    public void setCasServerUrl(String casServerUrl) {
        this.casServerUrl = casServerUrl;
    }

    public String getCasServerLoginUrl() {
        return casServerLoginUrl;
    }

    public void setCasServerLoginUrl(String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public String getCasServerLogoutUrl() {
        return casServerLogoutUrl;
    }

    public void setCasServerLogoutUrl(String casServerLogoutUrl) {
        this.casServerLogoutUrl = casServerLogoutUrl;
    }

    public String getAppServerUrl() {
        return appServerUrl;
    }

    public void setAppServerUrl(String appServerUrl) {
        this.appServerUrl = appServerUrl;
    }

    public String getAppLoginUrl() {
        return appLoginUrl;
    }

    public void setAppLoginUrl(String appLoginUrl) {
        this.appLoginUrl = appLoginUrl;
    }

    public String getAppLogoutUrl() {
        return appLogoutUrl;
    }

    public void setAppLogoutUrl(String appLogoutUrl) {
        this.appLogoutUrl = appLogoutUrl;
    }

}
