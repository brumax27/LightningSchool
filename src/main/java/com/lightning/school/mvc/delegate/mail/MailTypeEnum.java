package com.lightning.school.mvc.delegate.mail;

import lombok.Getter;

public enum MailTypeEnum {

    RECOVERY("recovery", " {{site}} : Recovery password"),
    CONFIRM_PASSWORD_CHANGE("password_change", "{{site}} : Password has changed"),
    REFUSE_CHANGE_PASSWORD("refuse_password", "{{site}} : refuse password change");


    @Getter
    private String template;
    @Getter
    private String subject;

    MailTypeEnum(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

}
