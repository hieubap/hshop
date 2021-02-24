const url_product = 'http://93.188.162.82:8081/product/search?name=';

var listSelect= localStorage.getItem("list_select_keep")==null?[]:JSON.parse(localStorage.getItem("list_select_keep"));

var pageProducts;

function fetProducts(){
    var name = document.getElementById('search_input').value;
    fetch(url_product+name,{
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


// handle and render products
function renderPageProducts(pageProducts) {
    let productElsCtn = document.querySelector('.home-product .row'); // get element of product container
    let productEls = ''; // save page products 

    for (let pageProduct of pageProducts) {
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
    
    if (productElsCtn) {
        productElsCtn.innerHTML = productEls;
    }
}


// sort price
function sortPrice() {
    let firstSelection = document.querySelector('.select-input__list > li:first-child'); // get the element of the low to high sort button 
    let lastSelection = document.querySelector('.select-input__list > li:last-child'); // get the element of the high to low sort button

    // Remove dots and convert to numbers from price
    function fixPrice(num) {
        return Number.parseInt(num.replace(/[\.]/g, ''));
    }

    // Sort from low to high
    if (firstSelection) {
        firstSelection.onclick = () => {
            pageProducts.sort((a, b) => {
                a = fixPrice(a.curPrice);
                b = fixPrice(b.curPrice);
                return a - b;
            })
            renderPageProducts();
        }
    }

    // Sort from high to low
    if (lastSelection) {
        lastSelection.onclick = () => {
            pageProducts.sort((a, b) => {
                a = fixPrice(a.curPrice);
                b = fixPrice(b.curPrice);
                return b - a;
            })
            renderPageProducts();
        }
    }
}

sortPrice();

// sort products
function sortProducts() {
    let btnClassList = document.getElementsByClassName('home-filter__btn'); // get button class list
    let cmBtn = btnClassList[0]; // get element of the common button
    let newBtn = btnClassList[1]; // get element of the newest button
    let sellBtn = btnClassList[2]; // get element of best-selling button

    // remove background class of button
    function removeBgrClass() {
        for (let el of btnClassList) {
            el.classList.remove('btn--primary');
        }
    }

    if (cmBtn) {
        cmBtn.onclick = () => {
            removeBgrClass();
            cmBtn.classList.add('btn--primary');
            pageProducts.sort((a, b) => {
                return b.sold - a.sold;
            })
            renderPageProducts();
        }
    }

    if (newBtn) {
        newBtn.onclick = () => {
            location.reload();
        }
    }
    
    if (sellBtn) {
        sellBtn.onclick = () => {
            removeBgrClass();
            sellBtn.classList.add('btn--primary');
            pageProducts.sort((a, b) => {
                return b.sold - a.sold;
            })
            renderPageProducts();
        }
    }
}

sortProducts();

// CATEGORY ITEMS
// handle and render category items
function renderCgrItems() {
    const cgrItems = [
        {
            name: 'Sản phẩm',
            link: '#',
            active: true
        },
        {
            name: 'Deal hot',
            link: '#',
            active: false
        },
        {
            name: 'Top bán chạy',
            link: '#',
            active: false
        },
        {
            name: 'Hàng mới',
            link: '#',
            active: false
        },
        {
            name: 'Sản phẩm Hot',
            link: '#',
            active: false
        },
        {
            name: 'Phụ kiện',
            link: '#',
            active: false
        },
        {
            name: 'Tai nghe',
            link: '#',
            active: false
        },
        {
            name: 'Pin sạc dự phòng',
            link: '#',
            active: false
        },
        {
            name: 'Áo quần',
            link: '#',
            active: false
        },
        {
            name: 'Hàng khuyến mãi',
            link: '#',
            active: false
        }
    ] // save category items info
    
    let cgrElsCtn = document.querySelector('.category-pc .category-list'); // get element of category list
    let cgrEls = ''; // save category items

    for (let cgrItem of cgrItems) {
        if (cgrItem.active === true) {
            cgrEls +=
                `<li class="category-item category-item--active">
                    <div class="category-item__icon">
                        <i class="fas fa-caret-right"></i>
                    </div>
                    <a href="${cgrItem.link}" class="category-item__link">${cgrItem.name}</a>
                </li>`
        } else {
            cgrEls +=
                `<li class="category-item">
                    <div class="category-item__icon">
                        <i class="fas fa-caret-right"></i>
                    </div>
                    <a href="${cgrItem.link}" class="category-item__link">${cgrItem.name}</a>
                </li>`
        }
    }
    
    cgrElsCtn.innerHTML = cgrEls;
}

renderCgrItems();

// handle event when the user hovers the category item
function hoverCategoryItems() {
    let cgrElIdx = -1; // initialize the ordinal numbers of category items
    let actEl = 'category-item--active'; // set active property of item
    
    let cgrPC_Ctn = document.querySelector('.category-pc > .category'); // get element of category container on PC
    let cgrElsCtn = cgrPC_Ctn.querySelector('.category-list'); // get element of category list
    let cgrEls = cgrElsCtn.querySelectorAll('.category-item'); // get element of category items

    // remove active property of item 
    let removeColor = () => {
        for (let cgrEl of cgrEls) {
            if (cgrEl.classList.contains(actEl)) {
                cgrEl.classList.remove(actEl);
            }
        }
    }

    // handle hover
    for (let cgrEl of cgrEls) {
        cgrEl.onmouseover = () => {                                                                                                                                               
            removeColor();
            cgrEl.classList.add(actEl);
        }
    }

    // loop through all items until reach the active item
    for (let cgrEl of cgrEls) {
        cgrElIdx++;
        if (cgrEl.classList.contains(actEl)) {
            break;
        }
    }

    // when hovering out container will reset the original active state
    cgrElsCtn.onmouseleave = () => {
        let cgrActChild = cgrElsCtn.children[cgrElIdx]; // get element of original active item
        
        removeColor();
        cgrActChild.classList.add(actEl);
    }
}

hoverCategoryItems();

function addButtonAction(){
    document.getElementById('xem_gio_hang').addEventListener('click',function (e){
        location.replace('http://93.188.162.82:8081/giohang.html');
    })
    document.getElementById('search_input').addEventListener('keypress',function (e){
        if (event.keyCode === 13) {
            fetProducts();
            this.blur();
            console.log('btn click');
            document.getElementById('qc').innerHTML = '';
      }
    })
    document.getElementById('btn_search').addEventListener('click',function (e){
            fetProducts();
            this.blur();
            console.log('btn click');
            document.getElementById('qc').innerHTML = '';
    })
    
    
}

addButtonAction();
