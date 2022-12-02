import axios from "axios";
import {useState} from "react";

const SignupPage = () => {

    const [account, setAccount] = useState({
        id:"",
        passwd:"",
        name:"",
    })
    const onChangeId = (e) =>{
        setAccount({
            ...account,
            id:e.target.value
        })
    }
    const onChangeName = (e) =>{
        setAccount({
            ...account,
            name:e.target.value,
        })
    }
    const onChangePasswd = (e) =>{
        setAccount({
            ...account,
            passwd:e.target.value,
        })
    }
    const signup = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/signup",
            headers: {
                "Content-Type": `application/json`,
            },
        data:{
                id:account.id,
                passwd:account.passwd,
                name:account.name
            }

        }).then( (response) => {
            console.log(response.data);
        })
    }

    return(
        <div className="wrapper">
            <p className="title">Sign Up</p>
            <input className="id" type="text" onChange={onChangeId} />
            <input className="password" type="text" onChange={onChangePasswd}  />
            <input className="username" type="text" onChange={onChangeName} />
            <button className="nextButton" type="button" onClick= { event => signup(event)} >회원가입</button>
        </div>
    )

}
export default SignupPage;