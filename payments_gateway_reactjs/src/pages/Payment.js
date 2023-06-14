import productImage from "../assets/photos/watch.png";
import {useSelector} from "react-redux";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import * as api from "../api";
const Payment = () => {

    const navigate = useNavigate();
    const product = useSelector(state => state.simpleReducer.selectedProduct);
    const [paymentMethod, setPaymentMethod] = useState("");

    const [cryptocurrency, setCryptocurrency] = useState("");
    const [email, setEmail] = useState("");
    const [fetch, setFetch] = useState(false);

    useEffect(() => {
        if(Object.keys(product).length === 0)
            navigate("/");
    }, [])

    const createPayment = () => {
        if(!fetch){
            setFetch(true)
            if(paymentMethod === "paypal" || paymentMethod === "stripe"){
                api.createPayment(paymentMethod, product.id)
                    .then((response) => {
                        window.location.replace(response.url)
                        setFetch(false)
                    }).catch(() => setFetch(false))
            }else if(paymentMethod === "crypto"){

            }
        }
    }

    return(
        <div className="bg-gray-50 h-full" style={{textAlign: "center", display: "flex", justifyContent: "center", flexDirection: "column", alignItems: "center"}}>

            <h2 className="text-4xl font-extrabold dark:text-white">
                <span className="text-transparent bg-clip-text bg-gradient-to-r to-emerald-600 from-sky-400">Payments tool </span> for companies</h2>
            <p className="mb-10 mt-3 text-lg font-normal lg:text-xl sm:px-16 xl:px-48"> Choose payment method and buy right now! </p>

            <div className="payment_container">

                <div style={{display: "flex", justifyContent: "center", gap: "10px", width: "300px", flexDirection: "column" }}>

                    <p className="text-lg font-normal lg:text-xl "> Choose delivery method! </p>
                    <div className="flex items-center pl-4 mb-5 border border-gray-200 rounded dark:border-gray-700">
                        <input checked id="bordered-radio-4" type="radio" name="bordered-radio1"
                               className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" />
                        <label htmlFor="bordered-radio-4"
                               className="w-full py-4 ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Online - immediate delivery</label>
                    </div>

                    <p className="text-lg font-normal lg:text-xl "> Choose payment method! </p>
                    <div className="flex items-center pl-4 border border-gray-200 rounded dark:border-gray-700">
                        <input id="bordered-radio-1" type="radio" value="paypal" name="bordered-radio" onChange={() => setPaymentMethod("paypal")}
                               className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" />
                            <label htmlFor="bordered-radio-1"
                                   className="w-full py-4 ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">PayPal</label>
                    </div>
                    <div className="flex items-center pl-4 border border-gray-200 rounded dark:border-gray-700">
                        <input id="bordered-radio-2" type="radio" value="stripe" name="bordered-radio" onChange={() => setPaymentMethod("stripe")}
                               className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" />
                            <label htmlFor="bordered-radio-2"
                                   className="w-full py-4 ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Stripe</label>
                    </div>
                    <div className="flex items-center pl-4 border border-gray-200 rounded dark:border-gray-700">
                        <input id="bordered-radio-3" type="radio" value="crypto" name="bordered-radio" onChange={() => setPaymentMethod("crypto")}
                               className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" />
                        <label htmlFor="bordered-radio-3"
                               className="w-full py-4 ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">CoinPayments - cryptocurrency</label>
                    </div>

                    {paymentMethod === "crypto" ? <div>
                        <label htmlFor="small" className="text-left mt-3 block mb-2 text-sm font-medium text-gray-900 dark:text-white">Choose cryptocurrency to pay!</label>
                        <select id="small"
                                className="block w-full p-2 mb-3 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                            <option selected>Choose a cryptocurrency</option>
                            <option value="ETH">Ethereum</option>
                            <option value="BTC">Bitcoin</option>
                            <option value="USDT">Tether</option>
                            <option value="USDC">USD Coin</option>
                            <option value="LTCT">LTCT - (only for testing)</option>
                        </select>

                        <label htmlFor="input-group-1"
                               className="text-left block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your
                            Email</label>
                        <div className="relative mb-6">
                            <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                                <svg aria-hidden="true" className="w-5 h-5 text-gray-500 dark:text-gray-400"
                                     fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z"></path>
                                    <path d="M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z"></path>
                                </svg>
                            </div>
                            <input type="text" id="input-group-1"
                                   className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                   placeholder="name@gmail.com" />
                        </div>


                    </div>: ""}


                </div>


                <div
                    className="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                    <a href="#">
                        <img className="p-8 rounded-t-lg" src={productImage} alt="product image"/>
                    </a>
                    <div className="px-5 pb-5">
                        <a href="#">
                            <h5 className="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">{product.name}, {product.brand}</h5>
                        </a>
                        <div className="flex items-center mt-2.5 mb-5">
                            <svg aria-hidden="true" className="w-5 h-5 text-yellow-300" fill="currentColor"
                                 viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>First star</title>
                                <path
                                    d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                            </svg>
                            <svg aria-hidden="true" className="w-5 h-5 text-yellow-300" fill="currentColor"
                                 viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>Second star</title>
                                <path
                                    d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                            </svg>
                            <svg aria-hidden="true" className="w-5 h-5 text-yellow-300" fill="currentColor"
                                 viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>Third star</title>
                                <path
                                    d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                            </svg>
                            <svg aria-hidden="true" className="w-5 h-5 text-yellow-300" fill="currentColor"
                                 viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>Fourth star</title>
                                <path
                                    d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                            </svg>
                            <svg aria-hidden="true" className="w-5 h-5 text-yellow-300" fill="currentColor"
                                 viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>Fifth star</title>
                                <path
                                    d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                            </svg>
                            <span
                                className="bg-blue-100 text-blue-800 text-xs font-semibold mr-2 px-2.5 py-0.5 rounded dark:bg-blue-200 dark:text-blue-800 ml-3">5.0</span>
                        </div>
                        <div className="flex items-center justify-between">
                            <span className="text-3xl font-bold text-gray-900 dark:text-white">${product.price}</span>
                            <a onClick={() => createPayment()} className="cursor-pointer text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Pay now!</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    )



}
export default Payment;