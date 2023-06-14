import Footer from "../components/Footer";
import ProductForm from "../components/ProductForm";

const MainLayout = ({children}) => {



    return(
        <main className={"main_layout"}>
            {children}

            <Footer />
        </main>
    )
}
export default MainLayout;