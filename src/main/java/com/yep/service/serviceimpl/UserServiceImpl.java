package com.yep.service.serviceimpl;

import com.yep.bean.Mail;
import com.yep.bean.UserInfo;
import com.yep.dao.UserDao;
import com.yep.exception.ExceptionEnum;
import com.yep.exception.ResultResponse;
import com.yep.service.IUserService;
import com.yep.utils.MailUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;
    @Resource
    private MailUtil mailUtil;
    private long endTime;
    private int verifyCode;

    @Override
    public ResultResponse loginService(String userName, String pwd) {
        ResultResponse response;
        UserInfo userInfo = userDao.findByNameAndPassword(userName, pwd);
        if (userInfo != null) {
            if (userInfo.getPassword().equals(pwd)) {
                //重要信息置空
                userInfo.setPassword("");
                response = ResultResponse.success(userInfo);
            }else {
                response = ResultResponse.error(ExceptionEnum.PASSWORD_NOT_MATCH);
            }

        } else {
            response = ResultResponse.error(ExceptionEnum.INFORMATION_NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResultResponse loginService(String email, int verifyWord) {
        UserInfo userInfo = userDao.findByEmail(email);
        ResultResponse response;
        if (userInfo != null) {
            if (this.verifyCode == verifyWord && new Date().getTime() < endTime) {
                userInfo.setPassword("");
                response = ResultResponse.success(userInfo);
            } else {
                response = ResultResponse.error(ExceptionEnum.PASSWORD_NOT_MATCH);
            }
        } else {
            response = ResultResponse.error(ExceptionEnum.INFORMATION_NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResultResponse sendVerifyWord(String email) {
        UserInfo userInfo = userDao.findByEmail(email);
        if (userInfo != null) {
            return ResultResponse.error(ExceptionEnum.EMAIL_EXIST);
        }
        Mail mail = new Mail();
        verifyCode = (int) ((Math.random() * 9 + 1) * 100000);
        endTime = new Date().getTime() + 10 * 60 * 1000;
        mail.setRecipient(email);
        mail.setSubject("修改邮箱");
        mail.setContent("亲爱的用户：您好！\n" +
                "\n" + "    您收到这封电子邮件是因为您正在注册YepRecord应用。假如这不是您本人所申请, 请不用理会这封电子邮件, 但是如果您持续收到这类的信件骚扰, 请您尽快联络管理员。\n" +
                "\n" +
                "   请使用以下验证码完成注册流程\n" + "\n  " +
                verifyCode + "\n\n" +"  注意：请您收到邮件的十分钟内（"+
                DateFormatUtils.format(endTime, "yyyy-MM-dd HH:mm:ss")+
                "）前使用，否则验证码将会失效。"
        );
        mailUtil.sendSimpleMail(mail);
        return ResultResponse.success();
    }

    @Override
    public ResultResponse registerUserInfo(UserInfo userInfo) {
        ResultResponse response;
        if (!isValid(userInfo)) {
            return ResultResponse.error(ExceptionEnum.BODY_NOT_MATCH);
        }
        UserInfo user = userDao.findByEmail(userInfo.getEmail());
        if (user != null) {
            response = ResultResponse.error(ExceptionEnum.EMAIL_EXIST);
        } else {
            //返回创建好的用户对象(带uid)
            userInfo.setCreateTime(LocalDateTime.now());
            userInfo.setModifiedTime(LocalDateTime.now());
            UserInfo newUser = userDao.save(userInfo);
            newUser.setPassword("");
            response = ResultResponse.success(newUser);
        }
        return response;
    }

    private boolean isValid(UserInfo userInfo) {
        return userInfo.getName() != null && !userInfo.getName().trim().isEmpty() &&
                userInfo.getPassword() != null && !userInfo.getPassword().trim().isEmpty() &&
                userInfo.getEmail() != null && !userInfo.getEmail().trim().isEmpty();
    }
}
