import Footer from "../components/Footer";

const MainLayout = ({children}) => {
    return(
        <main className={"main_layout bg-gray-50"}>
            {children}
            <Footer />
        </main>
    )
}
export default MainLayout;