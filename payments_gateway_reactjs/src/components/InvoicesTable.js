import ProductForm from "./ProductForm";
import ProductTableRow from "../elements/ProductTableRow";
import {useSelector} from "react-redux";
import InvoiceTableRow from "../elements/InvoiceTableRow";

const InvoicesTable = () => {

    const invoices = useSelector(state => state.simpleReducer.invoices);

    return(
        <section className="bg-gray-50 dark:bg-gray-900 p-3 sm:p-5">

            <div className="mx-auto max-w-screen-xl px-4 lg:px-12">
                <div className="bg-white dark:bg-gray-800 relative shadow-md sm:rounded-lg overflow-hidden overflow-y-scroll" style={{maxHeight: "600px"}}>

                    <div className="overflow-x-auto">
                        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                            <thead
                                className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                            <tr>
                                <th scope="col" className="px-4 py-3">ID</th>
                                <th scope="col" className="px-4 py-3">Product name</th>
                                <th scope="col" className="px-4 py-3">Payment Method</th>
                                <th scope="col" className="px-4 py-3">Cryptocurrency</th>
                                <th scope="col" className="px-4 py-3">Paid</th>
                                <th scope="col" className="px-4 py-3">Created at</th>
                            </tr>
                            </thead>
                            <tbody>
                            {invoices.map((invoice, i) =>
                                <InvoiceTableRow invoice={invoice} />
                            )}

                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </section>
    )
}
export default InvoicesTable;