const serverUrl = "http://localhost:8080";


function checkResponse(response){
    if (response.status >= 200 && response.status <= 299) return response.json();
    else throw response.json();
}

const get = (url, headers) =>
    fetch(url, {method: "GET", headers: headers})
        .then(checkResponse)

const request = (url, method, header, body) =>
    fetch(url, {method: method,
        headers: header,
        body: body})
        .then(checkResponse)


export const getProducts = () =>
    get(serverUrl + "/products", {})

export const getInvoices = () =>
    get(serverUrl + "/invoices", {})

export const createProduct = (name, price, brand, category) =>
    request(serverUrl + "/product", "POST", {"Content-Type": "application/json"},
        JSON.stringify({"name": name, "price": price, "brand": brand, "category": category}))

export const createPayment = (method, productId) =>
    request(serverUrl + "/" + method + "/create/payment", "POST", {"Content-Type": "application/json"},
        JSON.stringify({"productId": productId}))

export const checkPayment = (method, token) =>
    request(serverUrl + "/" + method + "/success", "POST", {"Content-Type": "application/json"}, JSON.stringify({"token": token}))

export const createCryptoPayment = (email, productId, cryptocurrency) =>
    request(serverUrl + "/crypto/create/payment", "POST", {"Content-Type": "application/json"},
        JSON.stringify({"email": email, "productId": productId, "cryptocurrency": cryptocurrency}))