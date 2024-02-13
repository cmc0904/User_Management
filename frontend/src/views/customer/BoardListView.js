import React, { useState, useEffect  } from 'react';

import Header from '../../component/Header';
import SideBar from '../../component/SideBar';

import '../../style/customer/Board.css';


import { Link } from 'react-router-dom';
import axios from 'axios';

const Board = () => {
    const [data, setData] = useState([]);

    

    useEffect(() => {
        getAllBoard();
    }, []); 
    
    const getAllBoard = async () => {
        try {
            const response = await axios.get('http://43.203.11.194:8081/api/board/getAllBoard',
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
                    }
                }
            );
        
            console.log(response.data)

            setData(response.data)
        } catch(e) {
            console.log(e)
        }
    };

    return (
        <>
            <Header content="고객서비스관리"></Header>
            <SideBar content={window.localStorage.getItem("name")} selectedMenu={{"as" : false, "board" : true, "chat" : false, "faq" : false, 'my' : false}}/>
            <section id="main">
                <div className="board_wrap">
                    <div className="board_title">
                        <strong>게시판</strong>
                        <p>문의사항을 적어주세요.</p>
                    </div>

                    <div className="board_list_wrap">
                        <div className="board_list">
                            <div className = "top">
                                <div className = "num">번호</div>
                                <div className = "b_title">제목</div>
                                <div className=  "writer">글쓴이</div>
                                <div className = "date">작성일</div>
                            </div>
 
                            {data.map((item, index) => (
                                <div>
                                    <div className = "num">{item.boardIdx}</div>
                                    <div className = "b_title"><Link to={`/customer/board/boardView/${item.boardIdx}`}>{item.boardTitle}</Link></div>
                                    <div className=  "writer">{item.writerId}</div>
                                    <div className = "date">{item.createAt.replaceAll("-", ".")}</div>
                                </div>
                            ))}
                        </div>    
                        <div className="bt_wrap">
                            <Link className="on" to={"/customer/board/boardwrite"}>등록</Link>
                        </div>
                    </div>
                </div>
            </section>

            
        </>    

    );
};

export default Board;
