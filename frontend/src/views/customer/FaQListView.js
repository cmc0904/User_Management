import React, { useState, useEffect } from 'react';
import Header from '../../component/Header';
import SideBar from '../../component/SideBar';
import '../../style/customer/FaqView.css';


import axios from 'axios';

const FaqView = () => {

    const [faq, setFaq] = useState([]);


    useEffect(() => {
        getAllFaq();
    }, []);

    const getAllFaq = async () => {
        const response = await axios.get('http://43.203.11.194:8081/api/faq/getAllFaQ',
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
                }
            }
        );

        setFaq(response.data);
    };


    return (
        <>
            <Header content="고객서비스"></Header>
            <SideBar content={window.localStorage.getItem("name")} selectedMenu={{"as" : false, "board" : false, "chat" : false, "faq" : true, 'my' : false}}/>
            <section id="main">
                <div className="faq_title">자주묻는질문 FAQ</div>
                <div className="faq">
                {faq.map((item, index) => (
                    <div className="faq_item">
                        <div className="faq_question">Q : {item.question}</div>
                        <div className="faq_answer">
                            A : {item.answer}
                        </div>
                    </div>
                ))}
                
                   
                </div>
            </section>
        </>

    );
};

export default FaqView;
