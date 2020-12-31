package me.caneva20.c20core.modules.messagedispatcher.configurations;

import me.caneva20.messagedispatcher.configurations.IConfiguration;

public class ParameterTokenConfiguration implements IConfiguration {
    public String tokenName;

    public ParameterTokenConfiguration() {
        tokenName = "par";
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}
