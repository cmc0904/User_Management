import React, { useEffect, useRef, useState } from 'react';
import Header from '../../component/Header';
import AdminSideBar from '../../component/AdminSideBar';

import '../../style/admin/AdmimChat.css';

import axios from 'axios';

const ChatAdminView = () => {

    const [users, setUsers] = useState([]);
    const [selectedUserInformation, setSelectedUserInformation] = useState({});
    const [repairs, setRepairs] = useState([]);

    const [selectedUser, setSelectedUser] = useState("");
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');
    const [name, setName] = useState('');
    const ws = useRef(null);

    useEffect(() => {
        getAllUser();
    }, []);

    const getAllUser = async () => {

        const response = await axios.get('http://43.203.11.194:8081/api/user/getAllUsers',
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
                }
            }
        );


        setUsers(response.data);

    };

    const getSelectedUserInformation = async (roomId) => {

        const response = await axios.get('http://43.203.11.194:8081/api/user/getUserByUserId?userId=' + roomId,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
                }
            }
        );

       // console.log(response.data.result)
        setSelectedUserInformation(response.data.result);
    };

    const getRepairs = async (roomId) => {
        const response = await axios.get('http://43.203.11.194:8081/api/repair/getRepairStatusByUserId?userId=' + roomId,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
                }
            }
        );

        setRepairs(response.data);
        console.log(response.data);
    };


    useEffect(() => {
        setName(window.localStorage.getItem("name"));
        ws.current = new WebSocket('wss://5jaz64g45h.execute-api.us-east-1.amazonaws.com/production/');
        ws.current.onopen = () => {
            ws.current.send(JSON.stringify({ "action": "login", "name": name }));
        };
        ws.current.onmessage = (msg) => {
            console.log(msg);
            console.log(JSON.parse(msg.data));
            const parsedMsg = JSON.parse(msg.data);

            if (parsedMsg.sender === window.localStorage.getItem("userId")) {
                // Show messages sent by the user
                setMessages((prev) => [...prev, parsedMsg]);
            }

            if (parsedMsg.to === "Admin" && selectedUser === parsedMsg.sender) {
                // Show messages sent to Admin from the selected user
                setMessages((prev) => [...prev, parsedMsg]);
            }
        };

        // WebSocket 연결을 정리합니다
        return () => {
            if (ws.current) {
                ws.current.close();
                ws.current = null;
            }
        };
    }, [name, selectedUser]);

    const sendMessage = () => {
        if (input == "") {
            return;
        }

        if (ws.current && ws.current.readyState === WebSocket.OPEN) {
            console.log(selectedUser)
            ws.current.send(JSON.stringify({ "action": "sendToAll", "msg": input, "name": name, "to": selectedUser, "sender": window.localStorage.getItem("userId") }));
            setInput('');
        }
    };

    const selectChatRoom = async (roomId) => {
        setSelectedUser(roomId);
        setMessages([]);
        getSelectedUserInformation(roomId);
        getRepairs(roomId);
    };

    return (
        <>
            <Header content="Management"></Header>
            <AdminSideBar content={window.localStorage.getItem("name")} selectedMenu={{'admin': false, 'user': false, "as" : false, "board" : false, "chat" : true, "faq" : false}}/>
            <section id="main">
                <div className="title">1:1 실시간 상담</div>

                <div class="chat-container">
                    <div class="chat-room">
                        {users.map((item, index) => (
                            <div class="room" onClick={() => selectChatRoom(item.userId)}>
                                <i class="fas fa-user"></i> {item.userName}({item.userId})
                            </div>
                        ))}

                    </div>

                    <div class="chatting-container">
                        <div class="chatting">
                            <div id="messages">
                                {messages.map((message, i) => (
                                    <div key={i}><strong>{message.name} : </strong> {message.msg}</div>
                                ))}

                            </div>
                        </div>
                        <div class="chat-input-container">
                            <input type="text" class="chat-input" placeholder="메시지를 입력하세요" value={input} onChange={(e) => setInput(e.target.value)} />
                            <button class="chat-send-btn" onClick={sendMessage}>전송</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <div>
                            <div class="user-info-title">사용자 정보</div>
                            {selectedUserInformation == {} ?
                                <>
                                    <div class="user-info-item">아이디 : 선택된 정보 없음</div>
                                    <div class="user-info-item">성함 : 선택된 정보 없음</div>
                                    <div class="user-info-item">전화번호 : 선택된 정보 없음</div>
                                    <div class="user-info-item">이메일 : 선택된 정보 없음</div>
                                    <div class="user-info-item">성별 : 선택된 정보 없음</div>
                                    <div class="user-info-item">서비스 가입일자: 선택된 정보 없음</div>
                                </>
                                : 
                                <>
                                    <div class="user-info-item">아이디 : {selectedUserInformation.userId}</div>
                                    <div class="user-info-item">성함 : {selectedUserInformation.userName}</div>
                                    <div class="user-info-item">전화번호 : {selectedUserInformation.userPhone}</div>
                                    <div class="user-info-item">주소 : {selectedUserInformation.userAddress}</div>
                                    <div class="user-info-item">이메일 : {selectedUserInformation.userEmail}</div>
                                    <div class="user-info-item">성별 : {selectedUserInformation.userGender}</div>

                                </>
                            }

                        </div>

                        <div>
                            <div class="user-info-title">A/S 접수 내역</div>
                            {repairs.map((item, index) => (
                                <div class="user-info-item">접수 내용 : {item.problemComment}</div>
                            ))}

                        </div>
                    </div>
                </div>
            </section>

        </>

    );
};

export default ChatAdminView;
