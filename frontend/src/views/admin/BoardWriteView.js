import React, { useState } from 'react';
import Header from '../../component/Header';
import AdminSideBar from '../../component/AdminSideBar';
import '../../style/customer/BoardWriteView.css';


import { Link, useNavigate  } from 'react-router-dom';

import axios from 'axios';

const BoardWriteView = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const navigate = useNavigate();

    const uploadContent = async () => {
        if (title == "" || content == "") {
            alert("비어있는 입력란이 있습니다.")
            return;
        }


        try {
            const response = await axios.post('http://43.203.11.194:8081/api/board/postBoard', {
                boardTitle: title,
                boardDetail: content
            },
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
                    }
                }
            );
        
            if (response.data.result === "ADD_BOARD_COMPLETE") {
                navigate('/admin/board');
            };
        } catch(e) {
            console.log(e)
        }
    };

    return (
        <>
            <Header content="Management"></Header>
            <AdminSideBar content={window.localStorage.getItem("name")} selectedMenu={{'admin': false, 'user': false, "as" : false, "board" : true, "chat" : false, "faq" : false}}/>
            <section id="main">
                <div class="board_wrap">
                    <div class="board_title">
                        <strong>게시판</strong>
                        <p>문의사항을 적어주세요.</p>
                    </div>

                    <div class="board_list_wrap">
                        <div class="board_write">
                            <div class="title">
                                <dl>
                                    <dt>제목</dt>
                                    <dd><input type="text" placeholder="제목입력" value={title} onChange={(e) => setTitle(e.target.value)} required autofocus/></dd>
                                </dl>
                            </div>

                            <div class="cont">
                                <textarea placeholder="내용입력" value={content} onChange={(e) => setContent(e.target.value)} required autofocus></textarea>
                            </div>

                        </div>

                        <div class="bt_wrap">
                            <a className="on" onClick={uploadContent}>등록</a>
                            <Link className="on" to={"/admin/board"}>취소</Link>
                        </div>
                    </div>
                </div>
            </section>

        </>

    );
};

export default BoardWriteView;
