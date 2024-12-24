import RecordTable1 from "./RecordTable1";
import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import { useState } from 'react';
import axiosInstance from "../util/Axios";
import {Record} from "../util/Types";
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from "react-bootstrap/DropdownButton";
import ButtonGroup from "react-bootstrap/ButtonGroup";

const Dashboard: React.FC = () => {
    const [data, setData] = useState<Record[]>([]);
    const [k, setK] = useState<string | null>();

    const role = {
        "manager": {
            "tables": [
                {
                    name: "Manage Reimbursements", 
                    cols: ["username", "amount", "description", "status", "action"],
                    event: "reimbursements",
                    actions: ["accept", "deny"]
                },
                {
                    name: "Manage Employees",
                    cols: ["username", "role", "action"],
                    event: "users",
                    actions: ["rank", "remove user", ]
                }
            ],
        },
        "employee": {
            "tables": [
                {
                    name: "Reimbursements", 
                    cols: ["amount", "description", "status", "action"],
                    event: "reimbursements",
                    actions: ["edit", "delete"]
                }
            ]
        }
    }

    const handleSelect = async (k: string | null ) => {
        const userId = getUserId();
        console.log(k);
        setK(k);
        const resp = await axiosInstance.get(`/${k}/${userId}`);
        console.log(resp.data);
        setData(resp.data);
    }
    const getRole = () => { 
        const user = JSON.parse(localStorage.getItem("user") || "{}");
        console.log(user.role);
        return user.role;
    }

    const getUserId = () => {
        const user = JSON.parse(localStorage.getItem("user") || "{}");
        console.log(user.userId);
        return user.userId;
    }

    const updateTable = async () => {
        const userId = getUserId();
        const resp = await axiosInstance.get(`/${k}/${userId}`);
        console.log(resp.data);
        setData(resp.data);
    }

    const filterData = (eventKey: any, event: object) => {
        console.log(eventKey);
        const filteredData = data.filter(record => record['status'] === eventKey.toUpperCase());
        setData(filteredData);
    }

    const userRole = getRole();
    return (
        <div>
            <Tabs defaultActiveKey={role[userRole].tables[0].name} onSelect={handleSelect} className="nav nav-tabs">
                {role[userRole].tables.map((table) => (
                    <Tab key={table.name} title={table.name} eventKey={table.event} role="tab">
                        {table.event === "reimbursements" ?<DropdownButton as={ButtonGroup} title="Filter By" onSelect={filterData}>
                            <Dropdown.Item eventKey="all">All</Dropdown.Item>
                            <Dropdown.Item eventKey="pending">pending</Dropdown.Item>
                            <Dropdown.Item eventKey="approved">approved</Dropdown.Item>
                            <Dropdown.Item eventKey="denied">denied</Dropdown.Item>
                        </DropdownButton>
                        : ''}
                        <RecordTable1 cols={table.cols} actions={table.actions} data={data} getUserId={getUserId} update={updateTable} event={table.event}/>
                    </Tab>
                ))}
            </Tabs>
        </div>
    )
}

export default Dashboard;