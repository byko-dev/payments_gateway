import {Button} from "flowbite-react";
import {useDispatch} from "react-redux";
import {setSelectedProduct} from "../redux/operations";
import {useNavigate} from "react-router-dom";
const ProductTableRow = ({product}) => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const buyProduct = () => {
        dispatch(setSelectedProduct(product))
        navigate("/pay");
    }

    return(
        <tr className="border-b dark:border-gray-700">
            <th scope="row"
                className="px-4 py-3 font-medium text-gray-900 whitespace-nowrap dark:text-white">{product.name}</th>
            <td className="px-4 py-3">{product.category}</td>
            <td className="px-4 py-3">{product.brand}</td>
            <td className="px-4 py-3">${product.price}</td>
            <td className="px-4 py-3 flex items-center justify-end">
                <Button onClick={() => buyProduct()} gradientDuoTone="cyanToBlue">
                    Buy!
                </Button>
            </td>
        </tr>
    )
}
export default ProductTableRow;