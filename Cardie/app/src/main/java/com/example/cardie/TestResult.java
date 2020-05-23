package com.example.cardie;

public class TestResult {
    private String UserID;
    private String SetID;
    private String Result;
    private String TestID;
    //private int time;
    private int Point;

    public TestResult (String userID, String setID, String result, String testID, int point){
        UserID=userID;
        SetID=setID;
        Result=result;
        TestID=testID;
        Point=point;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getSetID() {
        return SetID;
    }

    public void setSetID(String setID) {
        SetID = setID;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getTestID() {
        return TestID;
    }

    public void setTestID(String testID) {
        TestID = testID;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        Point = point;
    }
}
