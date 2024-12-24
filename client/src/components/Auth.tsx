import { Navigate } from "react-router";

interface Props {
    children: JSX.Element;
}

const Auth: React.FC<Props> = ({children}) => {
    
    if (localStorage.getItem('user')) {
        return children;
    } else return <Navigate to="/" replace />;
};

export default Auth;