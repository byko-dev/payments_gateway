import {useEffect} from "react";
import * as api from "../api";
import PaymentSummary from "../components/PaymentSummary";
const SuccessPayment = ({paymentMethod}) => {

    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get('token');

        api.checkPayment(paymentMethod, token).then(response => console.log(response));

    }, [])

    return(
        <PaymentSummary title={"Payment successful!"} description={"Your "+paymentMethod+" payment was successful."}/>
    )
}
export default SuccessPayment;