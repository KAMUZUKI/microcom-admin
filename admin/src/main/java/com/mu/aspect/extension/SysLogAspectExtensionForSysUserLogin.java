package com.mu.aspect.extension;

import com.mu.module.sys.annotation.SysLog;
import com.mu.module.sys.extension.impl.DefaultSysLogAspectExtension;
import com.mu.module.sys.model.request.AdminInsertSysLogDTO;
import com.mu.module.sys.model.request.SysUserLoginDTO;
import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;

/**
 * SysLog 切面实现类扩展 for 登录后台用户
 */
@NoArgsConstructor
public class SysLogAspectExtensionForSysUserLogin extends DefaultSysLogAspectExtension {

    @Override
    public void beforeSaving(AdminInsertSysLogDTO insertSysLogDTO, JoinPoint joinPoint, SysLog annotation, Throwable e, Object ret) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof SysUserLoginDTO) {
                SysUserLoginDTO argDTO = (SysUserLoginDTO) arg;
                insertSysLogDTO.setUsername(argDTO.getUsername());
            }
        }
    }
}
