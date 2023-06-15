import types from "./types";

const setProducts = items => ({
    type: types.SET_PRODUCTS, items
})

const addProduct = item => ({
    type: types.ADD_PRODUCT, item
})

const setSelectedProduct = item => ({
    type: types.SET_SELECTED_PRODUCT, item
})

const setInvoices = items => ({
    type: types.SET_INVOICES, items
})

export default {setProducts, addProduct, setSelectedProduct, setInvoices}