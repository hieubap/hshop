// var listSelect = [];
var listSelect= localStorage.getItem("list_select_keep")==null?[]:JSON.parse(localStorage.getItem("list_select_keep"));

var pageProducts;

const url_product = 'http://93.188.162.82:8081/product/search';
const url_giohanghtml = 'http://93.188.162.82:5500/src/main/resources/static/giohang.html';

// var pageProducts;
function callApi(){
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

    var url_get_info = url_product+window.location.search;
    console.log(url_get_info);
    fetch(url_get_info,{
        method: 'get'
    }).then(function (response){
        if (response.status === 200)
        {
            response.json().then(function (text){
                var data_info = JSON.parse(JSON.stringify(text)).data[0];
                showInfor(data_info);
                afterCallApi();
                console.log('load ok');
            })
        }
    })
}

function afterCallApi(){
    document.getElementById('btn_themhang').addEventListener('click',function (e){
        var param = new URLSearchParams(window.location.search).get('id');
        themhang(param);
        console.log('đã thêm');
    })

    document.getElementById('mua_ngay').addEventListener('click',function (e){
        var param = new URLSearchParams(window.location.search).get('id');
        themhang(param);
        console.log('mua ngay');
        window.location()
    })
}

callApi();

function showSelect(){
    var html_ = '';
    for(i=0;i<listSelect.length;i++)
    for(j = 0;j<pageProducts.length;j++){
        var pageProduct = pageProducts[j];
        if(listSelect[i].id == pageProduct.id) {
        html_ += `
        <li title="${pageProduct.title}" class="header__cart-item">
        <div class="header__cart-img-wrapper">
        <img src="${pageProduct.img}" class="header__cart-img">
        </div>
        <div class="header__cart-item-info">
        <div class="header__cart-item-head">
        <div class="header__cart-item-name">${pageProduct.name}</div>
        <div class="header__cart-item-price-wrap">
        <span class="header__cart-item-price">${convertMoney(pageProduct.newPrice)}</span>
        <span class="header__cart-item-multiply">x</span>
        <span class="header__cart-item-qnt">${listSelect[i].s}</span>
        </div>												
        </div>
        <div class="header__cart-item-body">
        <span class="header__cart-item-description">
        Phân loại: Hàng Quốc tế
        </span>
        <span class="header__cart-item-remove" onclick="removeSelect(${pageProduct.id})">Xóa</span>
        </div>
        </div></li>`;
        }
    }
    document.getElementById('list_select').innerHTML = html_;
    document.getElementById('number_select').innerHTML = listSelect.length;
}

function themhang(id){
    window.alert('đã thêm vào giỏ hàng')
    for(j=0;j<listSelect.length;j++)
    {   
        if(listSelect[j].id == id)
        {
            return console.log('đã thêm vào giỏ hàng')
        }

    };

    var str = '{"id":'+id+',"s":1}';
    console.log(str);
    listSelect.push(JSON.parse(str));
    showSelect();
    keepData();
}

function removeSelect(id){
    for(i=0;i<listSelect.length;i++)
    {
        if(listSelect[i].id == id){
            var a1 = listSelect.slice(0,i);
            var a2 = listSelect.slice(i+1,listSelect.length);
            listSelect = a1.concat(a2);
            showSelect();
            keepData();
        }
    }
}

function keepData(){
    localStorage.setItem("list_select_keep",JSON.stringify(listSelect));
}

// handle and render products
function renderPageProducts(pageProducts) {
    let suggestion1 = document.querySelector('.suggestion1 .row'); // get element of product container
    let suggestion2 = document.querySelector('.suggestion2 .row'); // get element of product container
    let suggestion3 = document.querySelector('.suggestion3 .row'); // get element of product container
    
    let productEls = ''; // save page products 
    var i;
    for (i = 0;i< pageProducts.length ; i++) {
        if(i > 4 ) break;
        var pageProduct = pageProducts[i];

        productEls += 
        `<div class="col l-2-4 m-4 c-6">
            <a href="product-info.html?id=${pageProduct.id}" id="${pageProduct.id}" class="home-product-item">
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
            <a href="product-info.html?id=${pageProduct.id}" id="${pageProduct.id}" class="home-product-item">
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
            <a href="product-info.html?id=${pageProduct.id}" id="${pageProduct.id}" class="home-product-item">
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

function addButtonAction(){
    document.getElementById('xem_gio_hang').addEventListener('click',function (e){
        location.replace(url_giohanghtml);
    })
}


addButtonAction();



function showInfor(body){
    var html_ = `<div class="item_information content" id="item_info_2">
    <div>
        <div class="head_str">${body.title}</div>
        <div class="head_str">${body.name}</div>
        <div>
            <div style="margin-top:17px;float: left;">
                <span style="padding-left:20px;white-space:pre-wrap;font-size: 18px;"> ${body.star} </span>
            </div>
            <div class="home-product-item__rating" style="float: left;margin-top: 13px;">
                <i class="home-product-item__star--gold fas fa-star icon_star" ></i>
                <i class="home-product-item__star--gold fas fa-star icon_star" ></i>
                <i class="home-product-item__star--gold fas fa-star icon_star" ></i>
                <i class="home-product-item__star--gold fas fa-star icon_star" ></i>
                <i class="home-product-item__star--gold fas fa-star icon_star" ></i>
                </div>
            <div style="margin-top:17px;float: left;">
                <span style="white-space:pre-wrap;font-size: 18px;"> | ${body.numberComment} đánh giá | ${convertDaBan(body.numberSell)} đã bán</span>
            </div>
            <div style="clear:both;"></div>
        </div>
    </div>
    <div id="id_3">
    <div class="price_old">${convertMoney(body.oldPrice)}</div>    
    <div class="price_new">${convertMoney(body.newPrice)}</div>    
    </div>
    <div class="type_">
        phân loại
    </div>
    <div class="type_">
        số lượng: ${body.number}
    </div>
    <div class="type_">
        màu sắc
    </div>
    <div class="type_">
        <button class="btn_order btn_green" id="btn_themhang">thêm vào giỏ hàng</button>
        <button class="btn_order btn_green" id="mua_ngay">mua ngay</button>
    </div>
</div>`;
document.getElementById('id_1').innerHTML = html_;
document.getElementById('id_2').innerHTML = `<img src="${body.img}" style="width: 100%;"></img>`;
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

function convertDaBan(number){
    var price = number+'';
    var len = price.length;
    if (len < 4)
    return price+'k';
    if (len < 7)
    return price.substring(0,len-3)+"."+price.substring(len-3,len-2)+'k';
    if (len < 11)
      return price.substring(0,len-7)+"."+price.substring(len-7,len-6)+'k';
    if (len < 14)
      return price.substring(0,len-10)+"."+price.substring(0,len-7)+"k";
}