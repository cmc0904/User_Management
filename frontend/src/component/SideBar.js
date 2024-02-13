import React, { Component } from 'react';
import PropTypes from 'prop-types'; // 추가

import '../style/component/adminSideBar.css';

import { Link } from 'react-router-dom';
class SideBar extends Component {

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
                    <div class="category-name">고객 센터</div>
                    <div class="items">
                        
                        <Link to="/customer/board" className={`menu ${selectedMenu.board == true ? 'active' : ''}`}>게시판</Link>
                        <Link to="/customer/as" className={`menu ${selectedMenu.as == true ? 'active' : ''}`}>A/S접수</Link>
                        <Link to="/customer/consulting" className={`menu ${selectedMenu.chat == true ? 'active' : ''}`}>상담</Link>
                     
                    </div>
                </div>
    
                <div class="select-item">
                    <div class="category-name">관리</div>
                    <div class="items">
                        <Link to="/customer/myinfo" className={`menu ${selectedMenu.my == true ? 'active' : ''}`}>MY정보</Link>
                        <Link to="/customer/faq" className={`menu ${selectedMenu.faq == true ? 'active' : ''}`}>FAQ</Link>
                    </div>
                </div>
            </div>
    
            <div class="bottom-nav"><a onClick={this.logout}>로그아웃 하기</a></div>
        </nav>
        );
    }
}

SideBar.propTypes = {
    content: PropTypes.node.isRequired, // 변경
    selectedMenu: PropTypes.string.isRequired
};

export default SideBar;
