export function convertMoney(money){
    if (money < 999) 
    return money;
    if (money < 999999) 
    return (''+money).substring(0,len-3)+"."+price.substring(len-3);
    if (money < 999999999) 
    return (money+'').substring(0,len-9)+"."+price.substring(0,len-6)+"."+price.substring(len-6,len-3)+"."+price.substring(len-3);
       return 0;
}