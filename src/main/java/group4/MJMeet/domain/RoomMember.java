package group4.MJMeet.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
    public static int[] changeInt(String t) {

        int[] num = new int[t.length()];

        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '1') {
                num[i] = 1;
            } else {
                num[i] = 0;
            }
        }


        return num;
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

    public static String possibleTime(int flag, int check) {
        String str = "";

        for(int i=0 ; i < flag; i++) {
            str += "0";
        }



        for(int i=0; i < check ; i ++) {
            str += "1";
        }


        int a = len - str.length();

        for(int i=0; i < a;i++) {
            str += "0";
        }


        return str;
    }

    public static HashMap<String, String> firstTime(RoomMember t, int meetingTime) {
        //우선 시간을 찾는 함수
        //요일이랑 가장 빠른 시간대 스트링 위치 반환


        LinkedHashMap<String, String> firstList = new LinkedHashMap<String, String>();

        int count = 0;
        int flag = -1;
        int check = 0;

        for (int i = 0; i < len; i++) {

            if (changeInt(t.getMondayTimetable())[i] == 1) {
                count++;

                if (count == meetingTime) {
                    flag = i - 1;
                    break;
                }
            } else {
                count = 0;
            }
        }
        if(flag != -1) {
            for (int i = flag; i < len; i++) {
                if (changeInt(t.getMondayTimetable())[i] == 1) {
                    check++;
                } else {
                    break;
                }
            }
        }


        if(flag == -1) {
            firstList.put("월", "-1");
        }
        else {
            firstList.put("월", possibleTime(flag, check));
        }

        count = 0;
        flag = -1;
        check = 0;

        for (int i = 0; i < len; i++) {

            if (changeInt(t.getTuesdayTimetable())[i] == 1) {
                count++;

                if (count == meetingTime) {
                    flag = i - 1;
                    break;
                }
            } else {
                count = 0;
            }
        }
        if(flag != -1) {
            for (int i = flag; i < len; i++) {
                if (changeInt(t.getTuesdayTimetable())[i] == 1) {
                    check++;
                } else {
                    break;
                }
            }
        }


        if(flag == -1) {
            firstList.put("화", "-1");
        }
        else {
            firstList.put("화", possibleTime(flag, check));
        }
        count = 0;
        flag = -1;
        check = 0;

        for (int i = 0; i < len; i++) {

            if (changeInt(t.getWednesdayTimetable())[i] == 1) {
                count++;

                if (count == meetingTime) {
                    flag = i - 1;
                    break;
                }
            } else {
                count = 0;
            }
        }
        if(flag != -1) {
            for (int i = flag; i < len; i++) {
                if (changeInt(t.getWednesdayTimetable())[i] == 1) {
                    check++;
                } else {
                    break;
                }
            }
        }
        if(flag == -1) {
            firstList.put("수", "-1");
        }
        else {
            firstList.put("수", possibleTime(flag, check));

        }
        count = 0;
        flag = -1;
        check = 0;

        for (int i = 0; i < len; i++) {

            if (changeInt(t.getThursdayTimetable())[i] == 1) {
                count++;

                if (count == meetingTime) {
                    flag = i - 1;
                    break;
                }
            } else {
                count = 0;
            }
        }
        if(flag != -1) {
            for (int i = flag; i < len; i++) {
                if (changeInt(t.getThursdayTimetable())[i] == 1) {
                    check++;
                } else {
                    break;
                }
            }
        }
        if(flag == -1) {
            firstList.put("목", "-1");
        }
        else {
            firstList.put("목", possibleTime(flag, check));
        }
        count = 0;
        flag = -1;
        check = 0;

        for (int i = 0; i < len; i++) {

            if (changeInt(t.getFridayTimetable())[i] == 1) {
                count++;

                if (count == meetingTime) {
                    flag = i - 1;
                    break;
                }
            } else {
                count = 0;
            }
        }
        if(flag != -1) {
            for (int i = flag; i < len; i++) {
                if (changeInt(t.getFridayTimetable())[i] == 1) {
                    check++;
                } else {
                    break;
                }
            }
        }
        if(flag == -1) {
            firstList.put("금", "-1");
        }
        else {
            firstList.put("금", possibleTime(flag, check));
        }
        count = 0;
        flag = -1;
        check = 0;

        for (int i = 0; i < len; i++) {

            if (changeInt(t.getSaturdayTimetable())[i] == 1) {
                count++;

                if (count == meetingTime) {
                    flag = i - 1;
                    break;
                }
            } else {
                count = 0;
            }
        }
        if(flag != -1) {
            for (int i = flag; i < len; i++) {
                if (changeInt(t.getSaturdayTimetable())[i] == 1) {
                    check++;
                } else {
                    break;
                }
            }
        }

        if(flag == -1) {
            firstList.put("토", "-1");
        }
        else {
            firstList.put("토", possibleTime(flag, check));
        }
        count = 0;
        flag = -1;
        check = 0;

        for (int i = 0; i < len; i++) {

            if (changeInt(t.getSundayTimetable())[i] == 1) {
                count++;

                if (count == meetingTime) {
                    flag = i - 1;
                    break;
                }
            } else {
                count = 0;
            }
        }
        if(flag != -1) {
            for (int i = flag; i < len; i++) {
                if (changeInt(t.getSundayTimetable())[i] == 1) {
                    check++;
                } else {
                    break;
                }
            }
        }

        if(flag == -1) {
            firstList.put("일", "-1");
        }
        else {
            firstList.put("일", possibleTime(flag, check));
        }

        for(String i : firstList.keySet()) { //저장된 key값 확인
//            System.out.println("[Key]:" + i + " [Value]:" + firstList.get(i));
            if(!firstList.get(i).equals("-1") ) {
                HashMap<String, String> first = new HashMap<String, String>();
                first.put(i,firstList.get(i));
//                System.out.println(firstList.get(i).getClass());
                return first;
            }

        }
        HashMap<String, String> empty = new HashMap<String, String>();
        empty.put("없음","-1");
        return empty;

    }
    public static String hashToString(HashMap<String, String> first) {

        String temp = "";
        String str = "";
        char c =0;

        temp += first.keySet();
        c = temp.charAt(1);
        str = c + "요일";

        String tempTime = first.values().toString();

        char c2[] = new char[len];

        String[] time = {"8시 00분", "8시 30분", "9시 00분", "9시 30분", "10시 00분", "10시 30분", "11시 00분", "11시 30분",
                "12시 00분", "12시 30분", "13시 00분", "13시 30분", "14시 00분", "14시 30분", "15시 00분", "15시 30분", "16시 00분",
                "16시 30분", "17시 00분", "17시 30분", "18시 00분", "18시 30분", "19시 00분", "19시 30분", "20시 00분", "20시 30분",
                "21시 00분", "21시 30분", "22시 00분", "22시 30분", "23시 00분", "23시 30분", "24시 00분"};

        for(int i=1; i <len +1; i++) {

            c2[i-1] = tempTime.charAt(i);

        }

        ArrayList<String> str2 = new ArrayList<>();
        int n = 0;
        for(int i = 0; i < len; i++) {
            if(c2[i] == '1') {
                str2.add(time[i]);
                n = i + 1;
            }
        }
        str2.add(time[n]);

        str = str +" "+ str2.get(0) + " ~ " + str2.get(str2.size()-1);

        return str;
    }
}
