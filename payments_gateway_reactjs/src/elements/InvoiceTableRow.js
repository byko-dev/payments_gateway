import {Button} from "flowbite-react";

const InvoiceTableRow = ({invoice}) => {

    return(<tr className="border-b dark:border-gray-700">
        <th scope="row"
            className="px-4 py-3 font-medium text-gray-900 whitespace-nowrap dark:text-white">{invoice.id}</th>
        <td className="px-4 py-3">{invoice.product.name}</td>
        <td className="px-4 py-3">{invoice.paymentMethod}</td>
        <td className="px-4 py-3">{invoice.cryptocurrency ? invoice.cryptocurrency: "NULL"}</td>
        <td className="px-4 py-3">{invoice.paid ?
            <span className="bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-green-400 border border-green-400">Paid</span> :
            <span className="bg-red-100 text-red-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-red-400 border border-red-400">Unpaid</span>}
        </td>
        <td className="px-4 py-3">{invoice.createdAt}</td>
    </tr>)
}
export default InvoiceTableRow;