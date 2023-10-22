package com.siloam.home.domain.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

/**
 * 시스템 상수
 */
public final class SysConstant {
    private static Logger log = LoggerFactory.getLogger(SysConstant.class);
    public static final String SYSTEM = "SYSTEM";

    public static Timestamp getSysTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }
}