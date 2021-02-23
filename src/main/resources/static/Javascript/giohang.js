url_product = 'http://93.188.162.82:8081/product/search';

var listSelect= localStorage.getItem("list_select_keep")==null?[]:JSON.parse(localStorage.getItem("list_select_keep"));

var pageProducts;

function keepData(){
    localStorage.setItem("list_select_keep",JSON.stringify(listSelect));
}

function showSelect(){
    var html_ = '';
    var sum = 0;
    for(i=0;i<listSelect.length;i++)
    for(j = 0;j<pageProducts.length;j++){
        var pageProduct = pageProducts[j];
        if(listSelect[i].id == pageProduct.id) {
        html_ += `
        <li title="title" class="header__cart-item1">
        <div class="header__cart-img-wrapper">
        <img src="${pageProduct.img}" class="header__cart-img">
        </div>
        <div class="header__cart-item-info">
        <div class="list_select_body">
            <div class="list_info">
                <span class="list_select_ten">
                    ${pageProduct.name}
                    </span>
                <span class="list_select_phanloai">
                    Phân loại: Hàng Quốc tế
                    </span>
            </div>
        <span class="list_gia">${convertMoney(pageProduct.newPrice)} </span>
        <span class="list_select_sub" onclick="addSoLuong(${pageProduct.id},-1)">_</span>
        <span class="list_soluong">${listSelect[i].s}</span>
        <span class="list_select_add" onclick="addSoLuong(${pageProduct.id},1)">+</span>
        <span class="list_tien">${convertMoney(pageProduct.newPrice*listSelect[i].s)}</span>
        <span class="list_select_remove" onclick="removeSelect(1)">Xóa</span>
        </div>
        </div>
		</li>`;
        sum += pageProduct.newPrice*listSelect[i].s;
        }
    }
    document.getElementById('bill_id').innerHTML = html_;

    html_ = `<span style="color: tomato;font-size: 20px;"
     id="tongtien2">${convertMoney(sum)}</span>`;
    document.getElementById('tongtien2').innerHTML = html_;
}

function addSoLuong(id,value){
    for(i=0;i<listSelect.length;i++){
        if(listSelect[i].id == id){
            if(listSelect[i].s == 0 & value == -1)
            return;
            listSelect[i].s+=value;
            showSelect();
            keepData();
            return;
        }
    }
    showSelect();
}

function removeSelect(id){
    var a1 = listSelect.slice(0,id);
    var a2 = listSelect.slice(id+1,listSelect.length);
    listSelect = a1.concat(a2);
    keepData();
    showSelect();
}

function convertMoney(money){
    var price = money+' đ';
    var len = price.length;
    if (len < 5)
    return price;
    if (len < 8)
    return price.substring(0,len-5)+"."+price.substring(len-5);
    if (len < 11)
      return price.substring(0,len-8)+"."+price.substring(len-8,len-5)+"."+price.substring(len-5);
    if (len < 14)
      return price.substring(0,len-11)+"."+price.substring(0,len-8)+"."+price.substring(len-8,len-4)+"."+price.substring(len-4);
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