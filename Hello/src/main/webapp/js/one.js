

// document.write("弹邓成成小JJ<br/>");
//
// console.log("hello");
// var a = confirm("确定退出吗？");
// i = 1;
//
// function f2() {
//     if(i === 1) {
//         document.fgColor = "#38ff6c";
//         i = 2;
//     }
//     else if(i === 2) {
//         document.fgColor = "#ff3645";
//         i = 3;
//     }
//     else {
//         document.fgColor = "#fdff21";
//         i = 1;
//     }
//     setTimeout("f2()",100);
// }
//
// f2();
var a=0;
var imgs=document.getElementById("ID")
function f() {
    a++;
    if (a===9) a=1;


    imgs.src="img/rightRun/r0"+a+".png";
setTimeout("f()",100);
}
f();