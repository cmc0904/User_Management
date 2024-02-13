import LoginView from './views/LoginView.js';
import RegisterView from './views/RegisterView.js';
import BoardListView from './views/customer/BoardListView.js';
import BoardView from './views/customer/BoardView.js';
import UserRequestRepairView from './views/customer/UserRequestRepairView.js';
import ChatUserView from './views/customer/ChatUserView.js';
import UserInformationView from './views/customer/UserInformationView.js';
import BoardWriteView from './views/customer/BoardWriteView.js';
import FaQListView from './views/customer/FaQListView.js';

import MemberListView from './views/admin/MemberListView.js';
import AdminListView from './views/admin/AdminListView.js';
import RepairReceptionView from './views/admin/RepairReceptionView.js';
import FAQMagementView from './views/admin/FAQMagementView.js';
import AdminBoardView from './views/admin/BoardListView.js';
import AdminBoardWriteView from './views/admin/BoardWriteView.js';
import AdminBoardDetailView from './views/admin/BoardView.js';
import AdminUserInformationView from './views/admin/AdminUserInformationView.js';



import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ChatAdminView from './views/admin/ChatAdminView.js';

function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginView />} />
        <Route path="/register" element={<RegisterView />} />

        {/* 사용자(Customer) 부분 */}
        <Route path="/customer/board" element={<BoardListView />} />
        <Route path="/customer/board/boardView/:paramName" element={<BoardView />} />
        <Route path="/customer/as" element={<UserRequestRepairView />} />
        <Route path="/customer/board/boardwrite" element={<BoardWriteView />} />
        <Route path="/customer/consulting" element={<ChatUserView />} />
        <Route path="/customer/myinfo" element={<UserInformationView />} />
        <Route path="/customer/faq" element={<FaQListView />} />

        {/* 관리자(Admin) 부분 */}
        <Route path="/admin/members" element={<MemberListView />} />
        <Route path="/admin/admins" element={<AdminListView />} />
        <Route path="/admin/repaireprocess" element={<RepairReceptionView />} />
        <Route path="/admin/asklist" element={<FAQMagementView />} />
        <Route path="/admin/chat" element={<ChatAdminView />} />
        <Route path="/admin/board" element={<AdminBoardView />} />
        <Route path="/admin/board/boardwrite" element={<AdminBoardWriteView />} />
        <Route path="/admin/board/boardview/:paramName" element={<AdminBoardDetailView />} />
        <Route path="/admin/user/:paramName" element={<AdminUserInformationView />} />


      </Routes>
    </Router>
  );
}

export default App;
