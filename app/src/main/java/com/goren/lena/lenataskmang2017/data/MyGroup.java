package com.goren.lena.lenataskmang2017.data;

import java.util.HashMap;

/**
 * Created by user on 12/07/2017.
 */

public class MyGroup
{
    private String gKey;
    private String mngrUKey;
    private String name;
    private HashMap<String,Object>usersKeys=new HashMap<String, Object>();
    private HashMap<String,Object>tasksKeys=new HashMap<String, Object>();

    public MyGroup() {
    }

    public String getgKey() {
        return gKey;
    }

    public void setgKey(String gKey) {
        this.gKey = gKey;
    }

    public String getMngrUKey() {
        return mngrUKey;
    }

    public void setMngrUKey(String mngrUKey) {
        this.mngrUKey = mngrUKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Object> getUsersKeys() {
        return usersKeys;
    }

    public void setUsersKeys(HashMap<String, Object> usersKeys) {
        this.usersKeys = usersKeys;
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

    public void addUsersKeys (String userKey)
    {
        this.usersKeys.put(userKey,true);
    }

    @Override
    public String toString() {
        return "MyGroup{" +
                "gKey='" + gKey + '\'' +
                ", mngrUKey='" + mngrUKey + '\'' +
                ", name='" + name + '\'' +
                ", usersKeys=" + usersKeys +
                ", tasksKeys=" + tasksKeys +
                '}';
    }
}
