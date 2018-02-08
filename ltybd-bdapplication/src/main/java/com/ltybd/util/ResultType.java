package com.ltybd.util;

public enum ResultType {

	FAILD(-1,"Faild","操作失败"),
	CONFIRM(2,"Confirm","再次确认"),
	SUCCESS(0,"Success","操作成功"),
	SOMESUCCESS(10,"SomeSuccess","部分成功"),
	ERROR(1,"Unknown error","操作失败,未知错误"),
	INVALID_KEY(101,"Invalid API key","apiKey无效"),
	TOO_MANY_PARAMS(102,"Too many parameters","参数过多"),
	INVALID_PARAMS(100,"Invalid parameter","参数无效或缺失"),
	COMFIRM_AGAIN(105,"Please confirm again","请再次确认"),
	NOT_FOUNDDATA(4,"not found data","没有查询到所需数据");
	private int result ;
	private String resultMsg ;
	private String remark ;
	
	private ResultType(int result,String resultMsg,String remark ){
		this.result = result;
		this.resultMsg = resultMsg;
		this.remark = remark;
	}
	
	public int getResult() {
		return result;
	}

  public void setResult(int result) {
    this.result = result;
  }

  public String getResultMsg() {
    return resultMsg;
  }

  public void setResultMsg(String resultMsg) {
    this.resultMsg = resultMsg;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public static String getResultMsg(int result){
    for(ResultType c :ResultType.values()){
      if(c.getResult() == result){
        return c.getResultMsg() ;
      }
    }
    return null ;
  }
  
  public static String getResultRemark(int result){
    for(ResultType c :ResultType.values()){
      if(c.getResult() == result){
        return c.getRemark() ;
      }
    }
    return null ;
  }
}
