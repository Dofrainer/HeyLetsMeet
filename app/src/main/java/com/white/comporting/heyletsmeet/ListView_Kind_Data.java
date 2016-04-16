package com.white.comporting.heyletsmeet;

/**
 * Created by Baek on 2016-04-17.
 */
public class ListView_Kind_Data { //각 사람의 주소를 저장하는 데이터 클래스
    private int KindSelect ;


    public ListView_Kind_Data()
    {
        KindSelect = 0;
    }
    public void setKind(int Kind) {
        KindSelect = Kind ;
    }

    public int getKind() {
        return this.KindSelect ;
    }


}

