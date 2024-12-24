import AddRecordForm from "./AddRecordForm";
import { useState } from "react";
import {Record} from "../util/Types";
import axiosInstance from "../util/Axios";

interface Props {
    cols: string[];
    actions: string[];
    data: Record[],
    getUserId: () => number,
    update: () => void,
    event: string
}

const RecordTable1: React.FC<Props> = ({cols, actions, data, getUserId, update, event}) => {
    const [showModal, setShowModal] = useState(false);

    const runAction = async (action: string, record: Record) => {
        const userId = getUserId();
        
        // could refactor to switch statement:
        if (action === "edit") {
            console.log("edit pressed");
        } else if (action === "delete") {
            const resp = await axiosInstance.delete(`/reimbursements/${record.reimbId}/users/${userId}`);
            update();
        }
        else if (action === "accept") {
            const resp = await axiosInstance.patch(`/reimbursements/${record.reimbId}/users/${userId}`, {
                status: "APPROVED"
            });
            update();
        } else if (action === "deny") {
            const resp = await axiosInstance.patch(`/reimbursements/${record.reimbId}/users/${userId}`, {
                status: "DENIED"
            });
            update();
        } else if (action === "remove user") {
            const resp = await axiosInstance.delete(`/users/${record.userId}/managers/${userId}`);
            update();
        } 

    };

    return (
        <div>
            <table className="table table-dark table-hover">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        {cols.map(col => (
                            <th scope="col">{col}</th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {data.map((record, recordIndex) => (
                        <tr key={recordIndex}>
                            <th scope="row">{recordIndex + 1}</th>
                            {cols.map((col, colIndex) => (
                                <td key={colIndex}>{record[col]}</td>
                            ))}
                            <td key="actions">
                                {actions.map(action => (
                                    <button onClick={() => runAction(action, record)}>{action}</button>
                                ))}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {event === 'reimbursements' ? <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                <button className="btn btn-outline-dark" onClick={() => setShowModal(true)}>Add Reimbursement</button>
                <AddRecordForm showModal={showModal} onHide={() => setShowModal(false)} update={update}/>
            </div> : ''}
        </div>
    );
};

export default RecordTable1;