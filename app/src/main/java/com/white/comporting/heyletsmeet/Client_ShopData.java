package com.white.comporting.heyletsmeet;

public class Client_ShopData
{
  private String strName;
  private String strCall;
  private String strAddress;
  private String strDescription;
  private String strSubway;
  private String strType;


  public void setName(String Name)
  {
    this.strName = Name;
  }
  
  public String getName()
  {
    return this.strName;
  }

  
  public void setCall(String Call)
  {
    this.strCall = Call;
  }
  
  public String getCall()
  {
    return this.strCall;
  }
  
  public void setSubway(String Subway)
  {
    this.strSubway = Subway;
  }
  
  public String getSubway()
  {
    return this.strSubway;
  }

public String getAddress() {
	return strAddress;
}

public void setAddress(String strAddress) {
	this.strAddress = strAddress;
}

public String getDescription() {
	return strDescription;
}

public void setDescription(String strDescription) {
	this.strDescription = strDescription;
}

public String getType() {
	return strType;
}

public void setType(String strType) {
	this.strType = strType;
}
  
}
