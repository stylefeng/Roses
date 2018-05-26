package com.stylefeng.roses.core.context;

import com.stylefeng.roses.api.common.constants.RosesConstants;
import com.stylefeng.roses.core.util.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * header中的spanId的上下文,获取上个请求的spanId，和holder的区别是，holder放的是本应用的spanId
 *
 * @author fengshuonan
 * @date 2018-05-09-下午6:25
 */
public class SpanIdContext {

    private static Logger logger = LoggerFactory.getLogger(SpanIdContext.class);

    public static String getSpanId() {

        try {

            HttpServletRequest request = HttpContext.getRequest();

            String spanId = request.getHeader(RosesConstants.SPAN_ID_HEADER_NAME);

            if (spanId == null) {
                return "";
            } else {
                return spanId;
            }

        } catch (NullPointerException e) {
            logger.debug("没有spanId !");
            return "";
        }

    }

}
