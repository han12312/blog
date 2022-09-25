package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.common.lang.vo.BlogInfo;
import com.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hanxiaofei
 * @date 2021-04-08
 */
@Mapper
@Repository
public interface BlogMapper extends BaseMapper<Blog> {
    /**
     * 根据分类查询博客
     */
    List<BlogInfo> getBlogByTypeName(String typeName);

}
