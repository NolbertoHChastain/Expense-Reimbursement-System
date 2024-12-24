import Form from 'react-bootstrap/Form';
import FloatingLabel from 'react-bootstrap/esm/FloatingLabel';
import { useNavigate, useOutletContext } from 'react-router';
import axiosInstance from '../util/Axios';

interface ContextProps {
    setLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
}

const Login: React.FC = () => {
    const navigate = useNavigate();
    const {setLoggedIn} = useOutletContext<ContextProps>();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        
        const formData = new FormData(e.currentTarget);
        console.log("Login form submitted");
        const resp = await axiosInstance.post("/users/login", {
            username: formData.get("username"),
            password: formData.get("password")
        })

        localStorage.setItem("user", JSON.stringify(resp.data));
        setLoggedIn(true);
        navigate("/dashboard");
    };

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Floating className="mb-3">
                <FloatingLabel controlId="Username" label="Username">
                    <Form.Control name="username" type="text" placeholder="Username" />
                </FloatingLabel>
            </Form.Floating>
            <Form.Floating className="mb-3">
                <FloatingLabel controlId="Password" label="Password">
                    <Form.Control name="password" type="password" placeholder="Password" />
                </FloatingLabel>
            </Form.Floating>
            <button type="button" className="btn btn-outline-dark" onClick={() => navigate("/")}>no account?</button>
            <button type="submit">Login</button>
        </Form>
    );
};

export default Login;