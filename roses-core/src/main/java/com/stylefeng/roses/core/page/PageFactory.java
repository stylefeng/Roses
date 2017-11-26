package com.stylefeng.roses.core.page;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.roses.core.support.HttpKit;
import com.stylefeng.roses.core.utils.ToolUtil;

import javax.servlet.http.HttpServletRequest;


/**
 * 默认分页参数构建
 *
 * @author fengshuonan
 * @date 2017年11月15日13:52:16
 */
public class PageFactory<T> {

    private final String ASC = "asc";

    private final String DESC = "desc";

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int pageSize = 20;
        int pageNo = 1;

        /**
         * 获取pageSize
         */
        String pageSizeString = request.getParameter("pageSize");
        if(ToolUtil.isNotEmpty(pageSizeString)){
            pageSize = Integer.valueOf(pageSizeString);
        }

        /**
         * 获取pageNo
         */
        String pageNoString = request.getParameter("pageNo");
        if(ToolUtil.isNotEmpty(pageNoString)){
            pageNo = Integer.valueOf(pageNoString);
        }

        /**
         * 获取排序字段和排序类型(asc/desc)
         */
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");

        if (ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>(pageNo, pageSize);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>(pageNo, pageSize, sort);
            if (ASC.equalsIgnoreCase(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }
}
