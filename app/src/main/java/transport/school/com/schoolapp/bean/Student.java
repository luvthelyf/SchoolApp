
package transport.school.com.schoolapp.bean;

import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("class")
    private String mClass;
    @SerializedName("profile_pic")
    private Object mProfilePic;
    @SerializedName("routeid")
    private String mRouteid;
    @SerializedName("schoolid")
    private String mSchoolid;
    @SerializedName("schoolname")
    private String mSchoolname;
    @SerializedName("stopid")
    private String mStopid;
    @SerializedName("studentid")
    private String mStudentid;
    @SerializedName("studentname")
    private String mStudentname;

    public String getmClass() {
        return mClass;
    }

    public void setClass(String mClass) {
        this.mClass = mClass;
    }

    public Object getProfilePic() {
        return mProfilePic;
    }

    public void setProfilePic(Object profilePic) {
        mProfilePic = profilePic;
    }

    public String getRouteid() {
        return mRouteid;
    }

    public void setRouteid(String routeid) {
        mRouteid = routeid;
    }

    public String getSchoolid() {
        return mSchoolid;
    }

    public void setSchoolid(String schoolid) {
        mSchoolid = schoolid;
    }

    public String getSchoolname() {
        return mSchoolname;
    }

    public void setSchoolname(String schoolname) {
        mSchoolname = schoolname;
    }

    public String getStopid() {
        return mStopid;
    }

    public void setStopid(String stopid) {
        mStopid = stopid;
    }

    public String getStudentid() {
        return mStudentid;
    }

    public void setStudentid(String studentid) {
        mStudentid = studentid;
    }

    public String getStudentname() {
        return mStudentname;
    }

    public void setStudentname(String studentname) {
        mStudentname = studentname;
    }

}
