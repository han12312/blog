package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.VisitLog;
import com.blog.mapper.VisitLogMapper;
import com.blog.service.VisitLogService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author hanxiaofei
 * @date 2021-04-08
 */
@Service
public class VisitLogServiceImpl extends ServiceImpl<VisitLogMapper, VisitLog> implements VisitLogService {

}
