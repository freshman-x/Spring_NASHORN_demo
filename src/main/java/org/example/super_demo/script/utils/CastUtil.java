package org.example.super_demo.script.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

//import com.alitrip.agent.business.accounting.model.enums.CurrencyCodeEnum;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

public final class CastUtil {
    private static final Map<String, ThreadLocal<SimpleDateFormat>> DATE_FORMAT_MAP = Maps.newHashMap();

    private static void registerDateFormat(String dateFormat,Locale locale) {
        DATE_FORMAT_MAP.put(dateFormat, ThreadLocal.withInitial(() -> new SimpleDateFormat(dateFormat,locale)));
    }

    private static void registerDateFormat(String dateFormat) {
        DATE_FORMAT_MAP.put(dateFormat, ThreadLocal.withInitial(() -> new SimpleDateFormat(dateFormat)));
    }

    static {
        registerDateFormat("yyyy-MM-dd HH:mm:ss");
        registerDateFormat("EEE MMM dd hh:mm:ss z yyyy");
        registerDateFormat("MM/dd/yy HH:mm");
        registerDateFormat("yyyy-MM-dd");
        registerDateFormat("yyyyMMdd");
        registerDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
    }

    private CastUtil() {

    }

    public static String castToString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static Date castToDate(String value) {
        return castToTime(value);
    }

    public static Date castToTime(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (Entry<String, ThreadLocal<SimpleDateFormat>> stringThreadLocalEntry : DATE_FORMAT_MAP.entrySet()) {
            if(value.length() == stringThreadLocalEntry.getKey().length()){
                try {
                    return stringThreadLocalEntry.getValue().get().parse(value);
                } catch (ParseException e) {
                    // nop
                }
            }
        }
        for (Entry<String, ThreadLocal<SimpleDateFormat>> stringThreadLocalEntry : DATE_FORMAT_MAP.entrySet()) {
            if(value.length() != stringThreadLocalEntry.getKey().length()){
                try {
                    return stringThreadLocalEntry.getValue().get().parse(value);
                } catch (ParseException e) {
                    // nop
                }
            }
        }
//        LogUtil.warn("date:{} parse fail.",value);
        return null;
    }

    public static Boolean castToBoolean(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Boolean.valueOf(value);
    }

    public static Integer castToInteger(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Integer.valueOf(value);
    }

    public static Long castToLong(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Long.valueOf(value);
    }

    public static BigDecimal castToBigDecimal(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return new BigDecimal(value);
    }

//    public static String castToCurrencyCode(String currencyDesc) {
//        CurrencyCodeEnum currencyCodeEnum = CurrencyCodeEnum.descOf(currencyDesc);
//        if (currencyCodeEnum == null) {
//            return null;
//        }
//        return currencyCodeEnum.getCode();
//    }

    public static void main(String[] args) throws ParseException {
        System.out.println(castToDate("14-Jan-2024"));

    }
}
