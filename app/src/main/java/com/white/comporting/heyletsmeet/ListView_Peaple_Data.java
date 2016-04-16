package com.white.comporting.heyletsmeet;

/**
 * Created by Baek on 2016-04-17.
 */
public class ListView_Peaple_Data { //각 사람의 주소를 저장하는 데이터 클래스
    private String AddressStr ;

    public ListView_Peaple_Data()
    {
        AddressStr = "주소를 입력하세요.";
    }
    public void setAddress(String Address) {
        AddressStr = Address ;
    }


    public String getAddress() {
        return this.AddressStr ;
    }

}

