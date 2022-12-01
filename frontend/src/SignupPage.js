import React, {useEffect} from 'react';
import axios from "axios";

const SignupPage = () => {
    const f1 = () => {

        axios({
            method: "post",
            url: "http://localhost:8080/api/signup",
            "Content-Type": 'application/json',
            data:{
                'name':'wuseong',
                'passwd':"1234"
            }
        })
            .then((res) => {
                let id = res;

                 console.log('UserId :', id);
            })
            .catch((err) => {
                console.log(err);

            });
    }
    useEffect(() => {

    }, []);
    return(
        <div className="wrapper">
            <p className="title">Sign Up</p>
            <input className="id" type="text"/>
            <input className="password" type="text"/>
            <input className="username" type="text"/>
            <button className="nextButton" type="button" onClick={f1}/>
        </div>
    )

}
export default SignupPage;