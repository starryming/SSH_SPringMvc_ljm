package com.learn.entity.Extend;

import com.learn.entity.LeaveRequest;
import com.learn.entity.Manager;

/**
 * 辅导员 三级处理
 */
public class President extends Manager {

    public President(String name) {
        super(name);
    }

    /**
     * 核心业务
     * 不同继承该类的身份自己实现
     *
     * @param request
     */
    @Override
    public void handleRequest(LeaveRequest request) {
        int days = request.getLeaveDays(); //获取请假天数
        String name = request.getName(); //获取请假人姓名
        String reason = request.getReason(); // 获取请假理由

        if(days <= 7) { //如果满足7天内的要求，院长直接审批
            System.out.println("学生" + name + "请假" + days + "天，理由：" + reason);
            System.out.println("院长" + this.name + "审批通过");
        } else {//否则无法审批 需要另外办理
            System.out.println("请假天数过多，院长" + this.name + "没法直接审批，需另外办手续。");
            if(this.nextManager != null) { //否则，如果链上存在下一个Leader，就让他处理
                this.nextManager.handleRequest(request);
            } else {
                System.out.println("请假不成功");
            }
        }
    }
}
