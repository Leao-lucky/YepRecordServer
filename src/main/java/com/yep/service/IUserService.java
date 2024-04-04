package com.yep.service;

import com.yep.bean.UserInfo;
import com.yep.exception.ResultResponse;

public interface IUserService {

    ResultResponse loginByName(String userName, String pwd);

    ResultResponse loginByEmail(String email, String  pwd);

    ResultResponse sendVerifyWord(String email);

    ResultResponse registerUserInfo(UserInfo userInfo);
}
