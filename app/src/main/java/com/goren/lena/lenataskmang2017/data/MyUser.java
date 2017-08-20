package com.goren.lena.lenataskmang2017.data;

import java.util.HashMap;

/**
 * Created by user on 12/07/2017.
 */

public class MyUser
{


    private String name;
    private String uKey_email;//key
    private String phone;
    private HashMap<String,Object> groupsKeys=new HashMap<>();
    private HashMap<String,Object> tasksKeys=new HashMap<>();

    public MyUser()
    {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuKey_email() {
        return uKey_email;
    }

    public void setuKey_email(String uKey_email) {
        this.uKey_email = uKey_email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public HashMap<String, Object> getGroupsKeys() {
        return groupsKeys;
    }

    public void setGroupsKeys(HashMap<String, Object> groupsKeys) {
        this.groupsKeys = groupsKeys;
    }

    public HashMap<String, Object> getTasksKeys() {
        return tasksKeys;
    }

    public void setTasksKeys(HashMap<String, Object> tasksKeys) {
        this.tasksKeys = tasksKeys;
    }

    public void addTasksKeys (String taskKey)
    {
        this.tasksKeys.put(taskKey,true);
    }

    public void addGroupKeys (String groupKey)
    {
        this.groupsKeys.put(groupKey,true);
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "name='" + name + '\'' +
                ", uKey_email='" + uKey_email + '\'' +
                ", phone='" + phone + '\'' +
                ", groupsKeys=" + groupsKeys +
                ", tasksKeys=" + tasksKeys +
                '}';
    }
}
