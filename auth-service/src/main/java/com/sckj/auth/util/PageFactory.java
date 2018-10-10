package com.sckj.auth.util;

import com.baomidou.mybatisplus.plugins.Page;


/**
 * BootStrap Table默认的分页参数创建
 *
 * @author wangheduo
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> defaultPage(String limitStr, String offsetStr, String sortStr, String orderStr) {
        int limit = Integer.valueOf(limitStr);     //每页多少条数据
        int offset = Integer.valueOf(offsetStr);   //每页的偏移量(本页当前有多少条)
        String sort = sortStr;         //排序字段名称
        String order = orderStr;       //asc或desc(升序或降序)
        if (ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>((offset / limit + 1), limit);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>((offset / limit + 1), limit, sort);
            if (Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }

}
