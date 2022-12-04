import {useState} from "react";
import axios from "axios";

const LoginPage = () =>{
    const [account, setAccount] = useState({
        id:"",
        passwd:"",
    })
    const onChangeId = (e) =>{
        setAccount({
            ...account,
            id:e.target.value,
        })
    }
    const onChangePasswd = (e) =>{
        setAccount({
            ...account,
            passwd:e.target.value,
        })
    }
    const login = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/login",
            headers: {
                "Content-Type": `application/json`,
            },
            data:{
                id:account.id,
                passwd:account.passwd,
                name:null
            }

        }).then( (response) => {
            console.log(response.data);
        })
    }
    const createRoom = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/room",
            headers: {
                "Content-Type": `application/json`,
            },
            data:{
                id:account.id,
                roomName:"방이름",
                meetingTime: "2"
            }

        }).then( (response) => {
            console.log(response.data);
        })
    }

    return(
        <div className="wrapper">
            <p className="title">Login</p>
            <input className="id" type="text" onChange={onChangeId}/>
            <input className="password" type="text" onChange={onChangePasswd}/>
            <button className="nextButton" type="button" onClick={createRoom}>로그인</button>
        </div>
    )


}
export default LoginPage;