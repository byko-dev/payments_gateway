import {useEffect, useState} from "react";
import ProductsTable from "../components/ProductsTable";
import { Button } from 'flowbite-react';
import {useDispatch} from "react-redux";
import {getProducts, getInvoices} from "../redux/operations";
import InvoicesTable from "../components/InvoicesTable";

const Main = () => {

    const dispatch = useDispatch();

    useEffect( () => {
        dispatch(getProducts());
        dispatch(getInvoices());
    }, [])

    const [table, setTable] = useState();

    return(
        <div className="pt-6 text-center h-full">
            <h1 className="mb-4 text-4xl font-extrabold leading-none tracking-tight text-gray-900 md:text-5xl lg:text-6xl dark:text-white">Create new payment!</h1>
            <p className="mb-6 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400"> Choose
                <span className="text-blue-600 dark:text-blue-500"> product</span> to buy by payment gateway! </p>

            <Button.Group>
                <Button color="gray" onClick={() => setTable("products")}>
                    Products
                </Button>
                <Button color="gray" onClick={() => setTable("invoices")}>
                    Invoices
                </Button>
            </Button.Group>

            {table === "products"? <ProductsTable /> : <InvoicesTable />}

        </div>
    )
}
export default Main;