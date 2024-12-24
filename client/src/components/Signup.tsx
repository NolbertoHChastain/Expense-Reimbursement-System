import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import FloatingLabel from 'react-bootstrap/esm/FloatingLabel';
import axiosInstance from '../util/Axios';
import { useNavigate, useOutletContext } from 'react-router';

interface ContextProps {
    setLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
}

const Signup: React.FC = () => {
    const navigate = useNavigate();
    const {setLoggedIn} = useOutletContext<ContextProps>();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        
        const formData = new FormData(e.currentTarget);
        const resp = await axiosInstance.post("/users", {
            firstname: formData.get("firstname"),
            lastname: formData.get("lastname"),
            username: formData.get("username"),
            password: formData.get("password"),
            role: formData.get("role")
        });

        localStorage.setItem("user", JSON.stringify(resp.data));
        setLoggedIn(true);
        navigate("/dashboard");
    };

    const handleLogin = () => {
        navigate("/login");
    }

    return (
        <Form onSubmit={handleSubmit}>
            <Row className="mb-3">
                <Col md>
                    <FloatingLabel controlId="Firstname" label="Firstname">
                        <Form.Control name="firstname" type="text" placeholder="Firstname" />
                    </FloatingLabel>
                </Col>
                <Col md>
                    <FloatingLabel controlId="Lastname" label="Lastname">
                        <Form.Control name="lastname" type="text" placeholder="Lastname" />
                    </FloatingLabel>
                </Col>
            </Row>
            <Form.Floating className="mb-3">
                <FloatingLabel controlId="Username" label="Username">
                    <Form.Control type="text" name="username" placeholder="Username" />
                </FloatingLabel>
            </Form.Floating>
            <Form.Floating className="mb-3">
                <FloatingLabel controlId="Password" label="Password">
                    <Form.Control type="password" name="password" placeholder="Password" />
                </FloatingLabel>
            </Form.Floating>
            <Row>
                <Col md>
                    <Form.Check type="radio" label="employee" name="role" value="employee"/>
                    <Form.Check type="radio" label="manager" name="role" value="manager"/>
                </Col>
            </Row>
            <button type="button" className="btn btn-outline-dark" onClick={handleLogin}>have a login?</button>
            <button type="submit">Signup</button>
        </Form>
    );
};

export default Signup;