package com.inhatc.dev_folio.member.exception;

public class EmailAlreadyException extends RuntimeException{
//    그냥 메소드 
    public EmailAlreadyException(){

    }
//    메세지를 던진다
    public EmailAlreadyException(String msg){
        super(msg);
    }
//    메세지랑 throw를 던진다
    public EmailAlreadyException(String msg, Throwable e){
        super(msg,e);
    }
}
