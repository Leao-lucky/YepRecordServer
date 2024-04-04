package com.yep.service;

import com.yep.bean.UserInfo;
import com.yep.exception.ResultResponse;

public interface IUserService {

    ResultResponse loginService(String userName, String pwd);

    ResultResponse loginService(String email, int verfyWord);

    ResultResponse sendVerifyWord(String email);

    ResultResponse registerUserInfo(UserInfo userInfo);
}
