package group4.MJMeet.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
public class RoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;

//    @ManyToOne
//    private Room room;

    private Long roomId;
    private String mondayTimetable;
    private String tuesdayTimetable;
    private String wednesdayTimetable;
    private String thursdayTimetable;
    private String fridayTimetable;
    private String saturdayTimetable;
    private String sundayTimetable;

    public RoomMember(){

    }
    public RoomMember(String a, String b, String c, String d, String e, String f, String g) {
        this.mondayTimetable = a;
        this.tuesdayTimetable = b;
        this.wednesdayTimetable = c;
        this.thursdayTimetable = d;
        this.fridayTimetable = e;
        this.saturdayTimetable = f;
        this.sundayTimetable = g;
    }
    private static int len = 32;
    public static boolean[] changeBool(String t) {

        boolean bool[] = new boolean[len];


        for (int i = 0; i < len; i++) {
            if (t.charAt(i) == '1') {
                bool[i] = true;
            } else {
                bool[i] = false;
            }
        }


        return bool;
    }

    public static boolean[] merge(ArrayList<boolean[]> day) {
        boolean a = true;

        boolean merge[] = new boolean[len];

        for (int j = 0; j < len; j++) {
            for (int i = 0; i < day.size(); i++) {
                a = a && day.get(i)[j];

            }
            merge[j] = a;
            a = true;
        }
        return merge;
    }

    public static String boolToString(boolean[] bool) {
        String str = "";

        for(int i =0;i < len; i++) {
            if(bool[i] == true) {
                str = str + "1";
            }
            else {
                str = str + "0";
            }
        }

        return str;
    }

    public int countMeetingTime(int meet) {
        return 0;
    }

    public static RoomMember mergeTimetable(RoomMember t1, RoomMember t2) {
//        System.out.println("비교할테이블1:" + t1.getMondayTimetable());
//        System.out.println("비교할테이블2:" + t2.getMondayTimetable());
        ArrayList<RoomMember> tList = new ArrayList<>();
        tList.add(t1);
        tList.add(t2);

        String[] week = new String[7];


        ArrayList<boolean[]> mon = new ArrayList<>();

        for (int j = 0; j < tList.size(); j++) {
            mon.add(changeBool(tList.get(j).getMondayTimetable()));
        }
        week[0] = boolToString(merge(mon));

        ArrayList<boolean[]> tue = new ArrayList<>();

        for (int j = 0; j < tList.size(); j++) {
            tue.add(changeBool(tList.get(j).getTuesdayTimetable()));
        }
        week[1] = boolToString(merge(tue));

        ArrayList<boolean[]> wed = new ArrayList<>();

        for (int j = 0; j < tList.size(); j++) {
            wed.add(changeBool(tList.get(j).getWednesdayTimetable()));
        }
        week[2] = boolToString(merge(wed));

        ArrayList<boolean[]> thu = new ArrayList<>();

        for (int j = 0; j < tList.size(); j++) {
            thu.add(changeBool(tList.get(j).getThursdayTimetable()));
        }
        week[3] = boolToString(merge(thu));

        ArrayList<boolean[]> fri = new ArrayList<>();

        for (int j = 0; j < tList.size(); j++) {
            fri.add(changeBool(tList.get(j).getFridayTimetable()));
        }
        week[4] = boolToString(merge(fri));

        ArrayList<boolean[]> sat = new ArrayList<>();

        for (int j = 0; j < tList.size(); j++) {
            sat.add(changeBool(tList.get(j).getSaturdayTimetable()));
        }
        week[5] = boolToString(merge(sat));

        ArrayList<boolean[]> sun = new ArrayList<>();

        for (int j = 0; j < tList.size(); j++) {
            sun.add(changeBool(tList.get(j).getSundayTimetable()));
        }
        week[6] = boolToString(merge(sun));



        RoomMember mergeT = new RoomMember(week[0],week[1],week[2],week[3],week[4],week[5],week[6]);

        return mergeT;

    }

    //초기값인지 확인하는 함수
    public boolean isFirst(){
        String init = "11111111111111111111111111111111";
        return (this.getMondayTimetable().equals(init)&&
                this.getTuesdayTimetable().equals(init)&&
                this.getWednesdayTimetable().equals(init)&&
                this.getThursdayTimetable().equals(init)&&
                this.getFridayTimetable().equals(init)&&
                this.getSaturdayTimetable().equals(init)&&
                this.getSundayTimetable().equals(init)) ? true : false;
    }
    public void setFail(){
        this.setMondayTimetable("-1");
        this.setTuesdayTimetable("-1");
        this.setWednesdayTimetable("-1");
        this.setThursdayTimetable("-1");
        this.setFridayTimetable("-1");
        this.setSaturdayTimetable("-1");
        this.setSundayTimetable("-1");

    }
}
