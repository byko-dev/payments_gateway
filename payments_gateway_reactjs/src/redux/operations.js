import actions from "./actions";
import * as api from "../api";

export const getProducts = () =>
    async (dispatch) => {
        const products = await api.getProducts();
        dispatch(actions.setProducts(products));
    }

export const addProduct = (product) =>
    (dispatch) => {
        dispatch(actions.addProduct(product))
    }

export const setSelectedProduct = (product) =>
    (dispatch) => {
        dispatch(actions.setSelectedProduct(product))
    }

export const getInvoices = () =>
    async (dispatch) => {
        const invoices = await api.getInvoices();
        dispatch(actions.setInvoices(invoices));
    }