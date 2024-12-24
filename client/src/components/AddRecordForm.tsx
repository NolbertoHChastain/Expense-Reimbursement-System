import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import FloatingLabel from 'react-bootstrap/esm/FloatingLabel';
import Button from 'react-bootstrap/Button';
import axiosInstance from '../util/Axios';

interface Props {
    showModal: boolean;
    onHide: () => void,
    update: () => void
}

const AddRecordForm: React.FC<Props> = ({showModal, onHide, update}) => {

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const user = JSON.parse(localStorage.getItem("user") || "{}");
        //console.log('form submitted');
        const formData = new FormData(e.currentTarget);
        //console.log(formData.get('amount'));
        //console.log(formData.get('description'));
        const reimbursement = await axiosInstance.post("/reimbursements", {
            description: formData.get('description'),
            amount: formData.get('amount'),
            userId: user.userId
        });
        
        update();
        onHide();
    };

    return (
        <Modal show={showModal} centered >
            <Modal.Header >
                <Modal.Title>Add Reimbursement</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <FloatingLabel controlId="floatingInput" label="Amount" className="mb-3">
                        <Form.Control name="amount" type="number" placeholder="" />
                    </FloatingLabel>
                    <FloatingLabel controlId="floatingInput" label="Description">
                        <Form.Control name="description" type="text" placeholder="Description" />
                    </FloatingLabel>
                    <Button type="submit" >Submit</Button>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <button onClick={onHide}>Close</button>
            </Modal.Footer>
        </Modal>
    );
}

export default AddRecordForm;