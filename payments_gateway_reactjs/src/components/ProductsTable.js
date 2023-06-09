import ProductForm from "./ProductForm";
import {useSelector} from "react-redux";
import ProductTableRow from "../elements/ProductTableRow";
import {useState} from "react";

const ProductsTable = () => {

    const products = useSelector(state => state.simpleReducer.products);

    const [searchTerm, setSearchTerm] = useState("");

    const filteredData = products.filter(item =>
        item.name.toLowerCase().includes(searchTerm.toLowerCase()));


    return (
        <section className="bg-gray-50 dark:bg-gray-900 p-3 sm:p-5">

            <div className="mx-auto max-w-screen-xl px-4 lg:px-12">
                <div className="bg-white dark:bg-gray-800 relative shadow-md sm:rounded-lg overflow-hidden">
                    <div
                        className="flex flex-col md:flex-row items-center justify-between space-y-3 md:space-y-0 md:space-x-4 p-4">
                        <div className="w-full md:w-1/2">
                            <div className="flex items-center">
                                <label htmlFor="simple-search" className="sr-only">Search</label>
                                <div className="relative w-full">
                                    <div
                                        className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                                        <svg aria-hidden="true" className="w-5 h-5 text-gray-500 dark:text-gray-400"
                                             fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                                            <path fillRule="evenodd"
                                                  d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                                                  clipRule="evenodd"/>
                                        </svg>
                                    </div>
                                    <input type="text" id="simple-search" value={searchTerm} onChange={e => setSearchTerm(e.target.value)}
                                           className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full pl-10 p-2 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
                                           placeholder="Search" required="" />
                                </div>
                            </div>
                        </div>
                        <div
                            className="w-full md:w-auto flex flex-col md:flex-row space-y-2 md:space-y-0 items-stretch md:items-center justify-end md:space-x-3 flex-shrink-0">

                            <ProductForm />
                        </div>
                    </div>
                    <div className="overflow-x-auto">
                        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                            <thead
                                className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                            <tr>
                                <th scope="col" className="px-4 py-3">Product name</th>
                                <th scope="col" className="px-4 py-3">Category</th>
                                <th scope="col" className="px-4 py-3">Brand</th>
                                <th scope="col" className="px-4 py-3">Price</th>
                                <th scope="col" className="px-4 py-3">
                                    <span className="sr-only">Actions</span>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            {filteredData.map((product, i) =>
                                    <ProductTableRow product={product} key={i} />
                            )}

                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </section>
    )
}

export default ProductsTable;
