import Header from '../../component/Header';
import SideBar from '../../component/SideBar';
import '../../style/customer/ConsultingView.css';

import React, { useEffect, useRef, useState } from 'react';


const CunsultingView = () => {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');
    const [name, setName] = useState('');
    const ws = useRef(null);

    useEffect(() => {
        setName(window.localStorage.getItem("name"));
        ws.current = new WebSocket('wss://5jaz64g45h.execute-api.us-east-1.amazonaws.com/production/        ');
        ws.current.onopen = () => {
            ws.current.send(JSON.stringify({ "action": "login", "name": name }));
        };
        ws.current.onmessage = (msg) => {
            console.log(msg);
            console.log(JSON.parse(msg.data));
            if(JSON.parse(msg.data).to == window.localStorage.getItem("userId") || JSON.parse(msg.data).sender == window.localStorage.getItem("userId")) {
                setMessages((prev) => [...prev, JSON.parse(msg.data)]);
            }
            
        };

        // WebSocket 연결을 정리합니다
        return () => {
            if (ws.current) {
                ws.current.close();
                ws.current = null;
            }
        };
    }, [name]);

    const sendMessage = () => {
        if(input == "") {
            return;
        }

        if (ws.current && ws.current.readyState === WebSocket.OPEN) {
            ws.current.send(JSON.stringify({ "action": "sendToAll", "msg": input, "name": name, "to" : "Admin", "sender" : window.localStorage.getItem("userId")}));
            setInput('');
        }
    };


    return (
        <>
            <Header content="고객서비스"></Header>
            <SideBar content={window.localStorage.getItem("name")} selectedMenu={{"as" : false, "board" : false, "chat" : true, "faq" : false, 'my' : false}}/>
            <section id="main">
                <h2 style={{ 'textAlign': 'center', 'marginTop': '10px', 'padding': '40px' }}>1:1 실시간 채팅 상담</h2>
                <div id="chat-container">

                    <div id="messages">
                    {messages.map((message, i) => (
                        <div key={i}><strong>{message.name} : </strong> {message.msg}</div>
                    ))}

                    </div>
                    <div id="user-input">
                        <input type="text" placeholder="메시지 입력" value={input} onChange={(e) => setInput(e.target.value)} />
                        <button id="send-btn" onClick={sendMessage}>전송</button>
                    </div>
                </div>


            </section>

        </>

    );
};

export default CunsultingView;
