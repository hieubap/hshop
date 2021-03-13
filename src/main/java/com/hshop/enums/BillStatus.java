package com.hshop.enums;

public enum BillStatus {
  WAIT_STORE_CONFIRM((short) 1),
  STORE_CONFIRM((short) 2),
  DELIVERED((short) 3),
  CANCEL((short) 4);

  private Short status;

  BillStatus(Short status) {
    this.status = status;
  }

  public Short getValue(){
    return status;
  }

  public static String getString(Short status){
    switch (status){
      case 1:{
        return "chờ cửa hàng xác nhận";
      }
      case 2:{
        return "cửa hàng đã xác nhận";
      }
      case 3:{
        return "đã giao hàng";
      }
      case 4:{
        return "hủy";
      }
    }
    return null;
  }
}
