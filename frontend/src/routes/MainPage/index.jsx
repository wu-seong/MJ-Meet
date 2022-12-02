import './index.css'

function mainPage(){
    return (
        <div className="wrapper">
            <div className="sideBar">
                <div className="iconwrapper">
                    아이콘 3개 들어갈 곳
                </div>
            </div>
            <div className="contents">
                <div className="roomname">
                    <p className="title">새로운 방 만들기</p>
                    <input className="inputBox"/>
                </div>
                <div className="priority">
                    <p className="title">우선 순위</p>
                    <button className="priorButton" type="button">빠른 시간</button>
                    <button className="priorButton" type="button">많은 시간</button>
                </div><div className="meettime">
                <p className="title">회의 시간</p>
                <input className="inputBox"/>
            </div>
                <button className="nextButton" type="button">
                    GO
                </button>

            </div>
        </div>
    )

}
export default mainPage;