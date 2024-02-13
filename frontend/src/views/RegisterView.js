import React, { useState } from 'react';
import '../style/auth/LoginRegister.css';
import Header from '../component/Header';
import { Link, useNavigate  } from 'react-router-dom';
import axios from 'axios';


const Register = () => {
  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [gender, setGender] = useState('');
  const [address, setAddress] = useState('');
  const navigate = useNavigate();


  const [checkUserId, setCheckUserId] = useState(false);
  const [checkPassword, setCheckPassword] = useState(false);
  const [checkName, setCheckName] = useState(false);
  const [checkEmail, setCheckEmail] = useState(false);
  const [checkPhone, setCheckPhone] = useState(false);
  const [checkGender, setCheckGender] = useState(false);
  const [checkAddress, setCheckAddress] = useState(false);

  const handleGenderChange = (event) => {
    // 라디오 버튼의 값(event.target.value)을 상태에 업데이트합니다.
    setGender(event.target.value);
  };


  const getTodayDate = () => {
    const today = new Date();
    const year = today.getFullYear();
    const month = ('0' + (today.getMonth() + 1)).slice(-2);
    const day = ('0' + today.getDate()).slice(-2);
    
    return year + month + day;
  }

  

  const handleRegister = async () => {
    if(userId === "") {
      setCheckUserId(true)
      return
    } else if (password === "") {
      setCheckPassword(true)
      return
    } else if (name === "") {
      setCheckName(true)
      return
    } else if (email === "") {
      setCheckEmail(true)
      return
    } else if (phone === "") {
      setCheckPhone(true)
      return
    } else if (gender === "") {
      setCheckGender(true)
      return
    } else if (!address) {
      setCheckAddress(true)
      return
    }
    
    const response = await axios.post('http://43.203.11.194:8081/api/user/register',
      {
        "userId" : userId,
        "userPassword" : password,
        "userName" : name,
        "userEmail" : email,
        "userAddress" : address,
        "userPhone" : phone,
        "userGender" : gender,
        "createAt" : getTodayDate(),        
      }
    );

    console.log(response.data);

    if(response.data.result === "REGISTER_COMPLETE") {
      navigate('/');
    } else if (response.data.message === "DUPLICATE_USER") {
      setCheckUserId(true)
    }
  };


  const goPopup = () => {
    // 주소 팝업을 열거나 다른 동작 수행
  };

  return (
    <>
      <Header content="Register"></Header>
      <div className="my-login-page">
        <section className="h-100">
          <div className="container h-100">
            <div className="row justify-content-md-center h-100">
              <div className="card-wrapper">
                <div className="brand">
                </div>
                <div className="card fat">
                  <div className="card-body">

                      <div className="form-group">
                        <label htmlFor="id">아이디</label>
                        <input id="id" type="text" className="form-control" name="id" value={userId} onChange={(e) => setUserId(e.target.value)} required autoFocus />
                        {checkUserId && (
                          <div className="invalid-feedback show">
                            아이디를 확인 해주세요. 
                          </div>
                        )}
                      </div>

                      <div className="form-group">
                        <label htmlFor="password">비밀번호</label>
                        <input id="password" type="password" className="form-control" name="password" value={password} onChange={(e) => setPassword(e.target.value)} required data-eye />
                        {checkPassword && (
                          <div className="invalid-feedback show">
                            비밀번호를 입력해 주세요.
                          </div>
                        )}
                      </div>

                      <div className="form-group">
                        <label htmlFor="name">이름</label>
                        <input id="name" type="text" className="form-control" name="name" value={name} onChange={(e) => setName(e.target.value)} required autoFocus />
                        {checkName && (
                          <div className="invalid-feedback show">
                            이름을 입력해 주세요.
                          </div>
                        )}
                      </div>
                      <div className="form-group">
                        <label htmlFor="email">이메일</label>
                        <input id="email" type="email" className="form-control" name="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                        {checkEmail && (
                        <div className="invalid-feedback show">
                          이메일을 입력해 주세요.
                        </div>
                        )}
                      </div>

                      <div className="form-group">
                        <label htmlFor="phone">전화번호</label>
                        <input id="phone" type="text" className="form-control" name="phone" value={phone} onChange={(e) => setPhone(e.target.value)} required autoFocus />
                        {checkPhone && (
                          <div className="invalid-feedback show">
                            전화번호를 입력해 주세요.
                          </div>
                        )}

                      </div>

                      <div className="form-group">
                        <label>성별</label>
                        <div className="d-flex align-items-center">
                          <p className="mr-2 mb-0">남성</p>
                          <input
                            id="genderM"
                            type="radio"
                            value="M"
                            name="gender"
                            required
                            autoFocus
                            checked={gender === 'M'} // 체크 여부를 상태값에 따라 설정합니다.
                            onChange={handleGenderChange} // 변경이벤트가 발생할 때 핸들러 함수를 호출합니다.
                          />
                          <p className="mr-2 mb-0">여성</p>
                          <input
                            id="genderM"
                            type="radio"
                            value="F"
                            name="gender"
                            required
                            autoFocus
                            checked={gender === 'F'} // 체크 여부를 상태값에 따라 설정합니다.
                            onChange={handleGenderChange} // 변경이벤트가 발생할 때 핸들러 함수를 호출합니다.
                          />              
                        </div>
                        {checkGender && (
                          <div className="invalid-feedback show">
                            성별을 입력해 주세요.
                          </div>
                        )}
                      </div>

                      <div className="form-group">
                        <label htmlFor="address">주소</label>
                        <div style={{ display: 'flex', flexDirection: 'row' }}>
                          <input style={{ flex: 1, marginRight: 5 }} id="address" type="text" className="form-control" name="address" value={address} onChange={(e) => setAddress(e.target.value)} required autoFocus />
                          {/* <input type="button" className="btn btn-outline-primary" value="주소" onClick={goPopup} /> */}
                        </div>
                        {checkAddress && (
                          <div className="invalid-feedback show">
                            주소를 입력해 주세요.
                          </div>
                        )}
                      </div>

                      <div className="form-group m-0">
                        <button type="button" className="btn btn-primary btn-block" onClick={handleRegister}>
                          회원가입
                        </button>
                      </div>
                      <div className="mt-4 text-center">
                        <Link to="/">로그인 하기</Link>
                      </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </>
  );
};

export default Register;