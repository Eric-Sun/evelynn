package com.j13.evelynn.util;

import com.j13.evelynn.core.AdminConstants;
import com.j13.evelynn.core.config.PropertiesConfiguration;

public class ImgUtil {

    public static String getImgFullUrl(String fileName) {
        String prefix = PropertiesConfiguration.getInstance().getStringValue(AdminConstants.IMG_URL_PREFIX);
        return prefix + fileName;

    }
}
