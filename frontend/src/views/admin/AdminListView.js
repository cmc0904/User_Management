import React, { useState, useEffect  } from 'react';
import Header from '../../component/Header.js';
import AdminSideBar from '../../component/AdminSideBar.js';
import axios from 'axios';




import '../../style/admin/adminMemberList.css';
import 'bootstrap/dist/css/bootstrap.min.css'; // Bootstrap 스타일 import


const AdminList = () => {
    const [admins, setAdmins] = useState([]);


    useEffect(() => {
        getAllAdmin();
    }, []); 

    const getAllAdmin = async () => {

        const response = await axios.get('http://43.203.11.194:8081/api/user/getAllAdmins',
        {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
            }
        }
        );

        setAdmins(response.data);

    };

    return (
        <>
            <Header content="Management"></Header>
            <AdminSideBar content={window.localStorage.getItem("name")} selectedMenu={{'admin': true, 'user': false, "as" : false, "board" : false, "chat" : false, "faq" : false}}/>
            <section id="main">
                <div className="title">관리자 관리</div>
                <div className='container'>
                    <table className="member_table" style={{"margin" :"auto"}}>
                        <thead>
                            <tr>
                                <td>IDX</td>
                                <td>아이디</td>
                                <td>성명</td>
                                <td>주소</td>
                                <td>전화번호</td>
                                <td>이메일</td>
                                <td>성별</td>
                            </tr>
                        </thead>
                        <tbody>
                            {admins.map((item, index) => (
                                <tr>
                                    <th>{index + 1}</th>
                                    <th>{item.userId}</th>
                                    <th>{item.userName}</th>
                                    <th>{item.userAddress}</th>
                                    <th>{item.userPhone}</th>
                                    <th>{item.userEmail}</th>
                                    <th>{item.userGender}</th>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                 
                </div>
            </section>
        </>    

    );
};

export default AdminList;