import types from "./types";

const INITIAL_STATES = {
    products: [],
    invoices: [],
    selectedProduct: {}
}

function reducer(state = INITIAL_STATES, action) {

    switch (action.type) {
        case(types.SET_PRODUCTS):
            return {...state, products: action.items}
        case(types.ADD_PRODUCT):
            return {...state, products: [...state.products, action.item]}
        case(types.SET_SELECTED_PRODUCT):
            return {...state, selectedProduct: action.item}
        case(types.SET_INVOICES):
            return {...state, invoices: action.items}
        default:
            return state;
    }

}
export default reducer;