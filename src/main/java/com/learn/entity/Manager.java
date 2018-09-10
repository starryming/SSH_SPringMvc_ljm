package com.learn.entity;

/**
 * 责任链 设计模式
 * 请假处理人
 * @author LJM
 */
public abstract class Manager {
    protected String name;
    protected Manager nextManager; //责任链上的后继对象，即这个对象无法处理，就转移给下一个Leader

    public Manager(String name) {
        super();
        this.name = name;
    }
    // 设定责任链上的后继对象
    public void setNextManager(Manager nextManager) {
        this.nextManager = nextManager;
    }

    /**
     * 核心业务
     * 不同继承该类的身份自己实现
     */
    public abstract void handleRequest(LeaveRequest request);
}
