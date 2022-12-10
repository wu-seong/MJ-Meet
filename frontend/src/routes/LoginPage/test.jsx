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
                meetingTime: "3"
            }

        }).then( (response) => {
            console.log(response.data);
        })
    }
    const lookupRoom = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/rooms",
            headers: {
                "Content-Type": `application/json`,
            },
            data:account.id

        }).then( (response) => {
            console.log(response.data);
        })
    }

    const getRoomTimetable = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/roomTimetable",
            headers: {
                "Content-Type": `application/json`,
            },
            data:"38"

        }).then( (response) => {
            console.log(response.data);
        })
    }
    const getRoomInfo = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/roomInfo",
            headers: {
                "Content-Type": `application/json`,
            },
            data:"67" //방 리스트를 조회하여 얻은 방 id를 요청하여 방정보 받아옴

        }).then( (response) => {
            console.log(response.data);
        })
    }
    const saveTimetable = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/timetable",
            headers: {
                "Content-Type": `application/json`,
            },
            data:{
                userId:account.id,
                roomId:"67",
                mondayTimetable:"00110110111100111111001111111111",
                tuesdayTimetable:"00110110111100111111001111111111",
                wednesdayTimetable:"00110110111100111111001111111111",
                thursdayTimetable:"00110110111100111111001111111111",
                fridayTimetable:"00110110111100111111001111111111",
                saturdayTimetable:"00110110111100111111001111111111",
                sundayTimetable:"00110110111100111111001111111111"
            }
        }).then( (response) => {
            console.log(response.data);
        })
    }
    const saveTimetable2 = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/timetable",
            headers: {
                "Content-Type": `application/json`,
            },
            data:{
                userId:account.id,
                roomId:"67",
                mondayTimetable:"00000000000000000000000000011111",
                tuesdayTimetable:"00000000000000000000000000011111",
                wednesdayTimetable:"00000000000000000000000000011111",
                thursdayTimetable:"00000000000000000000000000011111",
                fridayTimetable:"00000000000000000000000000011111",
                saturdayTimetable:"00000000000000000000000000011111",
                sundayTimetable:"00000000000000000000000000011111"
            }
        }).then( (response) => {
            console.log(response.data);
        })
    }
    const enrollMember = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/room/user",
            headers: {
                "Content-Type": `application/json`,
            },
            data:{
                roomId:"67",
                userId:account.id
            }

        }).then( (response) => {
            console.log(response.data);
        })
    }
    const getPriorityResult = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/priorityTime",
            headers: {
                "Content-Type": `application/json`,
            },
            data:{
                roomId:account.id
            }

        }).then( (response) => {
            console.log(response.data);
        })
    }
    const getLongResult = () => {
        axios({
            method:"post",
            url:"http://localhost:8080/api/longTime",
            headers: {
                "Content-Type": `application/json`,
            },
            data:{
                roomId:account.id
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
            <button className="nextButton" type="button" onClick={login}>로그인</button>
            <button className="nextButton" type="button" onClick={createRoom}>방 생성</button>
            <button className="nextButton" type="button" onClick={lookupRoom}>방 리스트 조회</button>
            <button className="nextButton" type="button" onClick={getRoomTimetable}>방 시간 조회</button>
            <button className="nextButton" type="button" onClick={getRoomInfo}>방 정보 조회</button>
            <button className="nextButton" type="button" onClick={saveTimetable}>시간표 저장</button>
            <button className="nextButton" type="button" onClick={saveTimetable2}>시간표 저장2</button>
            <button className="nextButton" type="button" onClick={enrollMember}>방에 유저 등록</button>
            <button className="nextButton" type="button" onClick={getPriorityResult}>가장 빠른 시간 결과</button>
            <button className="nextButton" type="button" onClick={getLongResult}>가장 여유있는 시간 결과</button>
        </div>
    )

}
export default LoginPage;