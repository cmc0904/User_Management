import React, { useState, useEffect  } from 'react';
import Header from '../../component/Header';
import AdminSideBar from '../../component/AdminSideBar';
import '../../style/customer/BoardView.css';


import { Link, useParams } from 'react-router-dom';
import axios from 'axios';


const AdminBoardView = ({ match }) => {
    
    const [content, setContent] = useState({});
    const [comment, setComment] = useState('');
    const [commentLists, setCommentLists] = useState([]);

    
    const { paramName } = useParams();

    useEffect(() => {
        getComments();
        getBoardContent();
    }, []); 

    const getBoardContent = async () => {
        try {
            const response = await axios.get('http://43.203.11.194:8081/api/board/getBoardByIdx?boardIdx='+paramName,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
                    }
                }
            );
        
            setContent(response.data)
        } catch(e) {
            console.log(e)
        }
    };


    const addComment = async () => {
        try {
            if (comment == "") {
                alert("비어있는 입력란이 있습니다.")
                return;
            }

            const response = await axios.post('http://43.203.11.194:8081/api/board/addComment',
                {
                    boardIdx: paramName,
                    comment: comment
                },
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
                    }
                }
            );
            
            getComments();
        } catch(e) {
            console.log(e)
        }
    };

    const getComments = async () => {
        try {
            const response = await axios.get('http://43.203.11.194:8081/api/board/getComment?boardIdx='+paramName,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + window.localStorage.getItem("jwt_token"),
                    }
                }
            );
            
            setCommentLists(response.data);
        } catch(e) {
            console.log(e)
        }
    };

    return (
        <>
            <Header content="Management"></Header>
            <AdminSideBar content={window.localStorage.getItem("name")} selectedMenu={{'admin': false, 'user': false, "as" : false, "board" : true, "chat" : false, "faq" : false}}/>
            <section id="main">
                <div className="board_wrap">
                    <div className="board_title">
                        <strong>기타문의</strong>
                        <p>문의사항을 입력해주세요</p>
                    </div>

                    <div className="board_list_wrap">
                        <div className="board_view">
                            <div className="title">
                                {content.boardTitle}
                            </div>
                            <div className="info">
                                <dl>
                                    <dt>번호</dt>
                                    <dd>{content.boardIdx}</dd>
                                </dl>

                                <dl>
                                    <dt>글쓴이</dt>
                                    <dd>{content.writerId}</dd>
                                </dl>


                            </div>
                            <div className="cont">
                                {content.boardDetail}
                            </div>

                            <div className="comment-section">
                                <h2>댓글</h2>
                    
                                
                                <div className="comment-form">
                                    <textarea placeholder="댓글을 입력하세요" value={comment} onChange={(e) => setComment(e.target.value)} required autofocus></textarea>
                                    <button onClick={addComment}>댓글 작성</button>
                                </div>
                    
                                
                                <ul className="comment-list">
                                    {commentLists.map((item, index) => (
                                        <li className="comment-item">
                                            <strong>{item.writerId}</strong>
                                            <div className="comment-content">- {item.comment}</div>
                                        </li>
                                    ))}
                                </ul>
                            </div>

                        </div>    
                        <div className="bt_wrap">
                            <Link className="on" to={"/admin/board"}>목록</Link>
                            
                        </div>
                    </div>
                </div>
            </section>

            
        </>    

    );
};

export default AdminBoardView;
