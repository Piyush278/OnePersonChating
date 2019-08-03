package com.example.chatbox;

public class messages {
    String from,to,message;

    public messages(String from,String to,String message){
        this.from=from;
        this.to=to;
        this.message=message;



    }

    @Override
    public String toString() {
        return("From="+from+",to="+to+ ",message = " + message);
    }
}
