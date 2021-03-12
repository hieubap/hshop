package com.hshop.enums;

public enum RoleType {
  ADMIN((short) 0),
  SELLER((short) 1),
  USER((short) 2);

  private Short value;

  RoleType(Short value) {
    this.value = value;
  }

  public Short getValue(){
    return value;
  }

  public String getName(){
    switch (value){
      case 0: return ADMIN.name();
      case 1: return SELLER.name();
      case 2: return USER.name();
    }
    return null;
  }
}
