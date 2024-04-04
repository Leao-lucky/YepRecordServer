package com.yep.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Data
@Getter
@Setter
public class Mail implements Serializable {
    private static final long serialVersionUID = 4359709211352400087L;
    private String recipient;//邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容
}
