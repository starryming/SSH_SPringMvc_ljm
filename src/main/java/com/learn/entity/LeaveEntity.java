package com.learn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "leave", schema = "ordersystemssh")
public class LeaveEntity {
    private int leaveid;
    private String leaveMessage;
    private Integer leaveDay;
    private Timestamp time;

    @Id
    @Column(name = "leaveid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(int leaveid) {
        this.leaveid = leaveid;
    }

    @Basic
    @Column(name = "leave_message", nullable = true, length = 255)
    public String getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage;
    }

    @Basic
    @Column(name = "leave_day", nullable = true)
    public Integer getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(Integer leaveDay) {
        this.leaveDay = leaveDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaveEntity that = (LeaveEntity) o;
        return leaveid == that.leaveid &&
                Objects.equals(leaveMessage, that.leaveMessage) &&
                Objects.equals(leaveDay, that.leaveDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leaveid, leaveMessage, leaveDay);
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
