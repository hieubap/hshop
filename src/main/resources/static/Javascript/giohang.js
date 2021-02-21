url_product = 'http://93.188.162.82:8081/product/search';

var listSelect= localStorage.getItem("list_select_keep")==null?[]:JSON.parse(localStorage.getItem("list_select_keep"));

var pageProducts;

function keepData(){
    localStorage.setItem("list_select_keep",JSON.stringify(listSelect));
    // location.replace("http://127.0.0.1:5500/giohang.html");
}

function showSelect(){
    var html_ = '';
    for(i = 0;i<listSelect.length;i++){
        var pageProduct = pageProducts[i];
        html_ += `
        <li title="Mũ chụp ngược Minecraft Dungeons" class="header__cart-item">
        <div class="header__cart-img-wrapper">
        <img src="${pageProduct.img}" class="header__cart-img">
        </div>
<div class="header__cart-item-info">
<div class="header__cart-item-head">
<div class="header__cart-item-name">${pageProduct.name}</div>
<div class="header__cart-item-price-wrap">
<span class="header__cart-item-price">${convertMoney(pageProduct.newPrice)}</span>
<span class="header__cart-item-multiply">x</span>
<span class="header__cart-item-qnt">1</span>
</div>												
</div>
<div class="header__cart-item-body">
<span class="header__cart-item-description">
Phân loại: Hàng Quốc tế
</span>
<span class="header__cart-item-remove" onclick="removeSelect(${i})">Xóa</span>
</div>
</div></li>`;
    }
    document.getElementById('bill_id').innerHTML = html_;
}


function removeSelect(id){
    var a1 = listSelect.slice(0,id);
    var a2 = listSelect.slice(id+1,listSelect.length);
    listSelect = a1.concat(a2);
    keepData();
    showSelect();
}

function convertMoney(money){
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

// var pageProducts;
function fetProducts(){
    fetch(url_product,{
        method: 'get'
    }).then(function (response){
        if (response.status === 200)
        {
            response.json().then(function (text){
                pageProducts = JSON.parse(JSON.stringify(text)).data;
                console.log(pageProducts);
                renderPageProducts(pageProducts);
                showSelect();
            })
        }
    })
}

fetProducts();

// handle and render products
function renderPageProducts(pageProducts) {
    let suggestion1 = document.querySelector('.suggestion1 .row'); // get element of product container
    let suggestion2 = document.querySelector('.suggestion2 .row'); // get element of product container
    let suggestion3 = document.querySelector('.suggestion3 .row'); // get element of product container
    
    let productEls = ''; // save page products 

    for (i = 0;i< pageProducts.length ; i++) {
        if(i > 4 ) break;
        pageProduct = pageProducts[i];

        productEls += 
        `<div class="col l-2-4 m-4 c-6">
            <a href="product-info.html" id="${pageProduct.id}" class="home-product-item">
                <img src=" ${pageProduct.img}" class="home-product-item__img">
                <div class="home-product-item__name"> ${pageProduct.name} </div>
                <div class="home-product-item__price">
                    <span class="home-product-item__price-old"> ${convertMoney(pageProduct.oldPrice)} </span>
                    <span class="home-product-item__price-current"> ${convertMoney(pageProduct.newPrice)} </span>
                </div>
                <div class="home-product-item__action">
                    <span class="home-product-item__like home-product-item__like--liked">
                        <i class="home-product-item__like-icon-empty far fa-heart"></i>
                        <i class="home-product-item__like-icon-fill fas fa-heart"></i>
                    </span>
                    <div class="home-product-item__rating">
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="fas fa-star"></i>
                    </div>
                    <span class="home-product-item__sold"> ${pageProduct.numberSell + ' đã bán'} </span>
                </div>
                <div class="home-product-item__origin">
                    <span class="home-product-item__brand"> NEW </span>
                    <span class="home-product-item__origin-name"> ${pageProduct.manufactureCountry} </span>
                </div>
                <div class="product-favourite">
                    <i class="fas fa-check"></i>
                    <span> Yêu thích </span>
                </div>
                <div class="product-sale-off">
                    <span class="product-sale-off__percent"> ${pageProduct.per}% </span>
                    <span class="product-sale-off__label"> GIẢM </span>
                </div>
                
            </a>
        </div>`
    }
    if (suggestion1) {
        suggestion1.innerHTML = productEls;
    }

    let productEls2 = '';

    for (i = 5;i< pageProducts.length ; i++) {
        if(i > 9 ) break;
        pageProduct = pageProducts[i];

        productEls2 += 
        `<div class="col l-2-4 m-4 c-6">
            <a href="product-info.html" id="${pageProduct.id}" class="home-product-item">
                <img src=" ${pageProduct.img}" class="home-product-item__img">
                <div class="home-product-item__name"> ${pageProduct.name} </div>
                <div class="home-product-item__price">
                    <span class="home-product-item__price-old"> ${convertMoney(pageProduct.oldPrice)} </span>
                    <span class="home-product-item__price-current"> ${convertMoney(pageProduct.newPrice)} </span>
                </div>
                <div class="home-product-item__action">
                    <span class="home-product-item__like home-product-item__like--liked">
                        <i class="home-product-item__like-icon-empty far fa-heart"></i>
                        <i class="home-product-item__like-icon-fill fas fa-heart"></i>
                    </span>
                    <div class="home-product-item__rating">
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="fas fa-star"></i>
                    </div>
                    <span class="home-product-item__sold"> ${pageProduct.numberSell + ' đã bán'} </span>
                </div>
                <div class="home-product-item__origin">
                    <span class="home-product-item__brand"> NEW </span>
                    <span class="home-product-item__origin-name"> ${pageProduct.manufactureCountry} </span>
                </div>
                <div class="product-favourite">
                    <i class="fas fa-check"></i>
                    <span> Yêu thích </span>
                </div>
                <div class="product-sale-off">
                    <span class="product-sale-off__percent"> ${pageProduct.per}% </span>
                    <span class="product-sale-off__label"> GIẢM </span>
                </div>
                
            </a>
        </div>`
    }
    if (suggestion2) {
        suggestion2.innerHTML = productEls2;
    }

    let productEls3 = '';

    for (i = 10;i< pageProducts.length ; i++) {
        if(i > 14 ) break;
        pageProduct = pageProducts[i];

        productEls3 += 
        `<div class="col l-2-4 m-4 c-6">
            <a href="product-info.html" id="${pageProduct.id}" class="home-product-item">
                <img src=" ${pageProduct.img}" class="home-product-item__img">
                <div class="home-product-item__name"> ${pageProduct.name} </div>
                <div class="home-product-item__price">
                    <span class="home-product-item__price-old"> ${convertMoney(pageProduct.oldPrice)} </span>
                    <span class="home-product-item__price-current"> ${convertMoney(pageProduct.newPrice)} </span>
                </div>
                <div class="home-product-item__action">
                    <span class="home-product-item__like home-product-item__like--liked">
                        <i class="home-product-item__like-icon-empty far fa-heart"></i>
                        <i class="home-product-item__like-icon-fill fas fa-heart"></i>
                    </span>
                    <div class="home-product-item__rating">
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="home-product-item__star--gold fas fa-star"></i>
                        <i class="fas fa-star"></i>
                    </div>
                    <span class="home-product-item__sold"> ${pageProduct.numberSell + ' đã bán'} </span>
                </div>
                <div class="home-product-item__origin">
                    <span class="home-product-item__brand"> NEW </span>
                    <span class="home-product-item__origin-name"> ${pageProduct.manufactureCountry} </span>
                </div>
                <div class="product-favourite">
                    <i class="fas fa-check"></i>
                    <span> Yêu thích </span>
                </div>
                <div class="product-sale-off">
                    <span class="product-sale-off__percent"> ${pageProduct.per}% </span>
                    <span class="product-sale-off__label"> GIẢM </span>
                </div>
                
            </a>
        </div>`
    }
    if (suggestion3) {
        suggestion3.innerHTML = productEls3;
    }

}


function clickthemhang(id){
    listSelect.push(id);
    showSelect();
}