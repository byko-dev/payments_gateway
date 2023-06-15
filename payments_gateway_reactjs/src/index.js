import React from 'react';
import ReactDOM from 'react-dom/client';
import './assets/main.scss';
import reportWebVitals from './reportWebVitals';
import {Provider} from "react-redux";
import store from "./redux/store";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import NotFound from "./pages/NotFound";
import MainLayout from "./layouts/MainLayout";
import Main from "./pages/Main";
import Payment from "./pages/Payment";
import SuccessPayment from "./pages/SuccessPayment";
import CancelPayment from "./pages/CancelPayment";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <Provider store={store}>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainLayout> <Main /> </MainLayout>}/>
                <Route path="/pay" element={<MainLayout> <Payment /> </MainLayout>}></Route>
                <Route path="*" element={<NotFound />}/>
                <Route path="/stripe/success" element={<MainLayout> <SuccessPayment paymentMethod={"stripe"} /> </MainLayout>}></Route>
                <Route path="/paypal/success" element={<MainLayout> <SuccessPayment paymentMethod={"paypal"} /> </MainLayout>}></Route>
                <Route path="/cancel" element={<MainLayout> <CancelPayment /> </MainLayout>} ></Route>
            </Routes>
        </BrowserRouter>
    </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
