package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.lang.vo.BlogInfo;
import com.blog.entity.Blog;

import java.util.List;

/**
 * 服务类
 *
 * @author hanxiaofei
 * @date  2021/4/5
 */
public interface BlogService extends IService<Blog> {

    /**
     * 通过分类名查找属于该分类的博客list
     *
     * @param categoryName 分类名
     * @return 博客list
     */
    List<BlogInfo> getBlogInfoListByCategoryName(String categoryName);
}
