package com.j13.evelynn.net;

import com.j13.evelynn.core.AdminConstants;
import com.j13.evelynn.core.config.PropertiesConfiguration;

public class BaseServerManager {

    protected String getServerUrl() {
        return PropertiesConfiguration.getInstance().getStringValue(AdminConstants.GAREN_SERVER_URL_KEY);
    }
}
