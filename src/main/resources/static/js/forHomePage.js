let basket = {}; // Инициализация пустого объекта
let basketCount = {}; // Инициализация пустого объекта

function changeButtonColor1(button) {
    var articul = $(button).attr('dataArt')
    if (basket[articul] == undefined){
        button.style.background =  'linear-gradient(to right, #800080, #ffc0cb)';
        button.innerHTML = 'Корзина';
    }
    else{
        button.style.background = 'grey';
        button.innerHTML = 'Добавлено';
    }
}

let sortedCart = {};
let cart = {};

$(document).ready(function(){
    getTotalLength(function(newLength) {
        length = newLength;
        loadGoods(0);
    });
});

function getTotalLength(callback) {
    let xhr = new XMLHttpRequest();
    let url = '/getLength';
    xhr.open('GET', url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let newLength = parseInt(xhr.responseText);
                callback(newLength);
            } else {
                console.error('Request failed with status:', xhr.status);
            }
        }
    };
    xhr.send();
}

function loadData(callback, page_number, countPerPage) {
    let xhr = new XMLHttpRequest();
    let url = '/getData?page_number=' + page_number + '&countPerPage=' + countPerPage;
    xhr.open('GET', url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let response = JSON.parse(xhr.responseText);
            callback(response.content);
        }
    };
    xhr.send();
}

let length = 0;

function checkLocalStorage(){
    if (localStorage.getItem('sortedCart') != null)
        sortedCart = JSON.parse(localStorage.getItem('sortedCart'))

    if (Object.keys(sortedCart).length === 0)
        sortedCart = Object.assign({}, cart);

    sortedCart = Object.assign({}, cart);
}

let special = {
    "66666": {
        "name": "Настольная игра Шакал: Остров сокровищ",
        "cost": 2799,
        "image": "img/shakal.jpg",
        "link": "product_page.html",
        "dataArt": "66666"
    }
}

function loadGoods(page_number){
    let out = '';
    let num = '66666';

    $("button.buy").remove();

    for (let i = 0; i < 6; i++){
        out += '<li>';
        out += '<a href="'+special[num].link+'">';
        out += '<img src="'+special[num].image+'" alt="Игрушки" width="200" height="200">';
        out += '</a>';
        out += '<div id="Price">';
        out += '<p>'+special[num]['name']+'</p>';
        out += '<p><b>'+toCurrency(special[num]['cost'])+'</b></p>'
        out += '<button dataArt="'+num+'" class="buy" onclick="changeButtonColor1(this)">Корзина</button>';
        out += '</div>';
        out += '</li>';
    }


    let countPerPage = $("#count_1").val();
    countPerPage = parseInt(countPerPage);
    if (countPerPage === null) {
        countPerPage = 3;
    }

    loadData(function(data) {
        cart = data;
        checkLocalStorage();
        checkGoods();
        generateProductList(page_number, countPerPage); // Вызов функции обновления контента здесь
    }, page_number, countPerPage);

}

function generateProductList(page_number, countPerPage) {

    sortedCart = onChange(page_number)

    let out = '';

    if (length < countPerPage)
        countPerPage = length

    let pagesCount = Math.ceil(length / countPerPage);

    for (let i = 0; i < Math.min(countPerPage, Object.keys(sortedCart).length); i++) {
        out += '<li class="pic-container" draggable="true">';
        out += '<a href="'+sortedCart[i]['link']+'">';
        out += '<img src="'+sortedCart[i]['image']+'" alt="Игрушки" width="200" height="200">';
        out += '</a>';
        out += '<div class="Inform">';
        out += '<p>'+sortedCart[i]['name']+'</p>';
        out += '<div class="toy">';
        out += '<p><b>'+toCurrency(sortedCart[i]['cost'])+'</b></p>'
        out += '<button dataArt="'+sortedCart[i]['dataArt']+'" class="buy" onclick="changeButtonColor(this)"><img alt="ошибка" src="/static/img/basket.png"></button>';
        out += '</div>';
        out += '</div>';
        out += '</li>';
    }

    $('#product_list').html(out);

    $('button.buy').click();
    $('button.buy').on('click', addToCart);

    $('button.buy').each(function() {
        changeButtonColor(this);
    });

    // remove all existing paging buttons
    $("button.pgn-bnt-styled").remove();

    //add paging buttons
    for (let i = 0; i < pagesCount; i++) {
        let button_tag = "<button>" + (i + 1) + ("</button>");
        let btn = $(button_tag)
            .attr('id', "paging_button_" + i)
            .attr('onclick', "loadGoods(" + i + ")")
            .addClass('pgn-bnt-styled');
        $('#paging_buttons').append(btn);
    }

    // make current page
    if (page_number !== null) {
        let identifier = "#paging_button_" + page_number;
        $(identifier).css("color", "red").css("font-weight", "bold");
    } else {
        $("#paging_button_0").css("color", "red").css("font-weight", "bold");
    }
}

function changeButtonColor(button) {
    let articul = $(button).attr('dataArt')

    if (basket[articul] == undefined){
        button.style.background =  'linear-gradient(to right, #800080, #ffc0cb)';
        button.innerHTML = '<img alt="ошибка" src="/static/img/basket.png">';
    }
    else{
        button.style.background = 'grey';
        button.innerHTML = '<img alt="ошибка" src="/static/img/checkmark.png">';
    }
}

function toCurrency(num) {
    return new Intl.NumberFormat("ru-RU", {
        style: "currency",
        currency: "RUB",
        minimumFractionDigits: 0,
    }).format(num);
}

//Добавление в корзину с помощью localStorage
function addToCart(){
    let articular = $(this).attr('dataArt')

    if (articular === undefined)
        return;

    if (basket[articular] === undefined) {
        for (let i = 0; i < Object.keys(sortedCart).length; i++) {
            if (sortedCart[i]['dataArt'] == articular) {
                basket[articular] = sortedCart[i];
                basketCount[articular] = 1;
                found = true;
                break;
            }
        }
    }
    else{
        delete basket[articular];
        delete basketCount[articular];
    }


    localStorage.setItem('basket', JSON.stringify(basket));
    localStorage.setItem('basketCount', JSON.stringify(basketCount));

    changeButtonColor(this);
}

function checkGoods(){
    if (localStorage.getItem('basket') != null) {
        basket = JSON.parse(localStorage.getItem('basket'))
    }

    if (localStorage.getItem('basketCount') != null) {
        basketCount = JSON.parse(localStorage.getItem('basketCount'))
    }
}

let dropdownList = document.getElementById("typeSorting");

dropdownList.onchange = onChange()
onChange()

function onChange(page_number) {
    let value = dropdownList.value;

    if (value === "increase"){
        sortCartByCost(true);
    }
    else if(value === "decrease"){
        sortCartByCost(false);
    }
    else{
        sortedCart = Object.assign({}, cart);
    }

    return sortedCart;
}

function sortCartByCost(flag) {
    var keyArray = Object.keys(sortedCart);
    let Cost;
    for (let i = 0; i < keyArray.length - 1; i++){
        Cost = keyArray[i];
        for (let j = i + 1; j < keyArray.length; j++){
            if (flag){
                if (sortedCart[Cost].cost > sortedCart[keyArray[j]].cost)
                    Cost = keyArray[j];
            }else{
                if (sortedCart[Cost].cost < sortedCart[keyArray[j]].cost)
                    Cost = keyArray[j];
            }
        }

        if (Cost !== keyArray[i])
            swap(sortedCart, Cost, keyArray[i]);
    }
}

function swap(sortedCart, key1, key2){
    [sortedCart[key1], sortedCart[key2]] = [sortedCart[key2], sortedCart[key1]]
}

/*Драгон дроп*/

/*var total = 0;
var elem;

const dragAndDrop = () => {

    $(document).ready(function(){
        const items = document.querySelectorAll('.pic-container');

        items.forEach((item) =>{
            item.addEventListener("dragstart", dragStart);
        })

        cell = $(".basket")

        cell.on("dragover", dragOver)
        cell.on("drop", dragDrop)
    });

};

function dragStart(){
    elem = this
}

function dragOver(evt){
    evt.preventDefault();
}

function dragDrop(){
    var childElement = $(elem).find(".buy");
    addToCartDragonDrop(childElement);
}

function addToCartDragonDrop(product){
    var articul = $(product).attr('dataArt')

    if (articul === undefined)
        return;

    if (basket[articul] === undefined){
        basket[articul] = 1;
    }

    localStorage.setItem('basket', JSON.stringify(basket));
    loadGoods();
}

dragAndDrop();*/