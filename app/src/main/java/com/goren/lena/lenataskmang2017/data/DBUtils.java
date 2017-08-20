package com.goren.lena.lenataskmang2017.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by user on 16/08/2017.
 */

public class DBUtils
{
    public static FirebaseDatabase database=FirebaseDatabase.getInstance();
    public static DatabaseReference myUsersRef=database.getReference(MyUser.class.getSimpleName());
    public static DatabaseReference myTasksRef=database.getReference(MyTask.class.getSimpleName());
    public static DatabaseReference myGroupRef=database.getReference(MyGroup.class.getSimpleName());
    public static FirebaseAuth auth=FirebaseAuth.getInstance();

}
