import React, { useState } from 'react';
import Header from '../../component/Header.js';
import SideBar from '../../component/SideBar';
import '../../style/customer/AsView.css';


import axios from 'axios';

const AsView = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const registration = async () => {
        if (title == "" || content == "") {
            alert("비어있는 입력란이 있습니다.")
            return
        }


        const response = await axios.post('http://43.203.11.194:8081/api/repair/registrationrepair', {
            problemTitle: title,
            problemComment: content
        },
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
                }
            }
        );
    };

    return (
        <>
            <Header content="고객서비스"></Header>
            <SideBar content={window.localStorage.getItem("name")} selectedMenu={{"as" : true, "board" : false, "chat" : false, "faq" : false, 'my' : false}}/>
            <section id="main">
                <h2 className="title" style={{"text-align" :"center"}}>A/S 접수하기</h2>
                <div id="as-form">
                    <h2>A/S 접수</h2>
                    <form id="asForm">
                        <label htmlFor="customerName">문제명:</label>
                        <input type="text" id="customerName" name="customerName" value={title} onChange={(e) => setTitle(e.target.value)} required/>
                        <label htmlFor="asDescription">문제 설명:</label>
                        <textarea id="asDescription" name="asDescription" value={content} onChange={(e) => setContent(e.target.value)} required></textarea>

                        <button type="submit" onClick={registration}>A/S 신청</button>
                    </form>
                </div>
            </section>

        </>

    );
};

export default AsView;
