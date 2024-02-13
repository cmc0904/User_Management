import React, { Component } from 'react';
import PropTypes from 'prop-types'; // 추가
import { Link } from 'react-router-dom';


import '../style/component/adminSideBar.css';


class AdminSideBar extends Component {
    logout = () => {
        // 로컬 스토리지에서 jwt_token 삭제
        localStorage.removeItem('jwt_token');
        localStorage.removeItem('name');

        // 로그인 페이지로 이동
        window.location.href = '/'; // 새로고침을 통해 라우팅을 다시 시작
    };



    render() {
        const { content, selectedMenu } = this.props; // 변경


        return (
        <nav id="side-bar">
            <div class="welcome">
                {content}님, 환영합니다!
            </div>
    
            <div class="nav-bar">
                <div class="select-item">
                    <div class="category-name">회원 관리</div>
                    <div class="items">
                        <Link to="/admin/members" className={`menu ${selectedMenu.user == true ? 'active' : ''}`}>사용자</Link>
                        <Link to="/admin/admins"  className={`menu ${selectedMenu.admin == true ? 'active' : ''}`}>관리자</Link>
                    </div>
                </div>
    
                <div class="select-item">
                    <div class="category-name">고객 센터</div>
                    <div class="items">
                        <Link to="/admin/repaireprocess" className={`menu ${selectedMenu.as == true ? 'active' : ''}`}>A/S접수</Link>
                        <Link to="/admin/board" className={`menu ${selectedMenu.board == true ? 'active' : ''}`}>게시판</Link>
                        <Link to="/admin/chat" className={`menu ${selectedMenu.chat == true ? 'active' : ''}`}>상담</Link>
                        <Link to="/customer/board" className="menu">사용자 페이지 전환</Link>
                    </div>
                </div>
    
                <div class="select-item">
                    <div class="category-name">콘텐츠 관리</div>
                    <div class="items">
                        <Link to="/admin/asklist" className={`menu ${selectedMenu.faq == true ? 'active' : ''}`}>자주 묻는 질문</Link>
                    </div>
                </div>
            </div>
    
            <div class="bottom-nav">
                <a onClick={this.logout}>로그아웃 하기</a>
            </div>
        </nav>
        );
    }
}

AdminSideBar.propTypes = {
    content: PropTypes.node.isRequired, // 변경
    selectedMenu: PropTypes.string.isRequired,

};

export default AdminSideBar;