export function convertMoney(money){
    var price = money+'đ';
    var len = price.length;
    if (len < 5)
    return price;
    if (len < 8)
    return price.substring(0,len-4)+"."+price.substring(len-4);
    if (len < 11)
      return price.substring(0,len-7)+"."+price.substring(len-7,len-4)+"."+price.substring(len-4);
    if (len < 14)
      return price.substring(0,len-10)+"."+price.substring(0,len-7)+"."+price.substring(len-7,len-3)+"."+price.substring(len-4);
}