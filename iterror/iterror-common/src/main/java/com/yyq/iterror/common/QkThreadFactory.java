package com.yyq.iterror.common;

import java.util.concurrent.ThreadFactory;

public class QkThreadFactory implements ThreadFactory {

    private String name;

    public QkThreadFactory(String name){
        super();
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, name + r.hashCode());
    }

}

